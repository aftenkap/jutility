package org.jutility.io.database.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jutility.io.database.DBUtils;
import org.jutility.io.database.ISQLPrivileges;
import org.jutility.io.database.PropertyInfo;


/**
 * @author Peter J. Radics
 * @version 1.0
 * @since 1.0
 *
 */
public class MySQLDriver {

    private static MySQLDriver s_Instance;

    /**
     * @return the instance of the {@link MySQLDriver} singleton class.
     * @throws ClassNotFoundException
     *             if the MySQL driver is not found.
     */
    public static MySQLDriver Instance()
            throws ClassNotFoundException {

        if (s_Instance == null) {
            s_Instance = new MySQLDriver();
        }

        return s_Instance;
    }


    private Connection        connection              = null;
    private Statement         statement               = null;
    private PreparedStatement preparedStatement       = null;
    private Set<String>       preparedStatementKeySet = null;
    private ResultSet         resultSet               = null;

    private MySQLDriver()
            throws ClassNotFoundException {

        // System.out.println("Trying to load jdbc driver.");
        // This will load the MySQL driver.
        Class.forName("com.mysql.jdbc.Driver");

    }

    /**
     * @param url
     * @param schema
     * @param username
     * @param password
     * @throws SQLException
     */
    public void connect(String url, String schema, String username,
            String password)
            throws SQLException {

        String uri = "jdbc:mysql://" + url;
        if (schema != null) {

            uri += "/" + schema;
        }
        this.connection = DriverManager.getConnection(uri, username, password);

    }

    /**
     * @param host
     * @param port
     * @param username
     * @param password
     * @throws SQLException
     */
    public void connect(String host, int port, String username, String password)
            throws SQLException {

        this.connect(host + ":" + port, null, username, password);
    }


    /**
     * @throws SQLException
     */
    public void close()
            throws SQLException {

        if (resultSet != null) {
            resultSet.close();
            resultSet = null;
        }

        if (statement != null) {
            statement.close();
            statement = null;
        }

        if (connection != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
            connection = null;
        }
    }

    /**
     * @return {@code true}, if the connection is open; {@code false} otherwise.
     */
    public boolean isConnected() {

        return (connection != null);
    }


    private int executeStatement(String statement, boolean returnGeneratedKeys)
            throws SQLException {

        if (this.connection != null) {
            this.statement = this.connection.createStatement();

            if (returnGeneratedKeys) {
                this.statement.executeUpdate(statement,
                        Statement.RETURN_GENERATED_KEYS);

                int autoIncKeyFromApi = -1;
                ResultSet rs = this.statement.getGeneratedKeys();
                if (rs.next()) {
                    autoIncKeyFromApi = rs.getInt(1);
                }
                else {

                    throw new SQLException("Could not retrieve generated keys!");
                }
                rs.close();
                rs = null;
                System.out.println("Key returned from getGeneratedKeys():"
                        + autoIncKeyFromApi);

                this.statement.close();
                this.statement = null;

                return autoIncKeyFromApi;
            }
            else {
                this.statement.executeUpdate(statement);

                this.statement.close();
                this.statement = null;
                return -1;
            }
        }
        else {
            throw new IllegalStateException(
                    "Trying to create database without valid connection to the "
                            + "server!");
        }
    }


    private ResultSet executeQuery(String query)
            throws SQLException {

        if (this.connection != null) {

            this.statement = this.connection.createStatement();

            this.resultSet = this.statement.executeQuery(query);

            return this.resultSet;
        }
        else {
            throw new IllegalStateException(
                    "Trying to execute query without valid connection to the "
                            + "server!");
        }
    }

    public int determineNumberOfQueryResults(String query)
            throws SQLException {

        String rewrittenQuery = "SELECT COUNT(*) FROM (" + query + ")";

        int results = 0;

        if (this.connection != null) {
            this.statement = this.connection.createStatement();

            this.resultSet = this.statement.executeQuery(rewrittenQuery);


            while (this.resultSet.next()) {
                results = this.resultSet.getInt(1);
            }

        }
        else {
            throw new IllegalStateException(
                    "Trying to determine number of results of a query to the "
                            + "database without valid connection to the "
                            + "server!");
        }


        return results;
    }


    /**
     * Creates a schema with the provided name.
     *
     * @param schemaName
     *            the schema name.
     * @throws SQLException
     *             if the creation fails.
     */
    public void createSchema(String schemaName)
            throws SQLException {

        if (this.connection != null) {
            this.statement = this.connection.createStatement();

            this.statement.executeUpdate("CREATE DATABASE " + schemaName);

            this.statement.close();
            this.statement = null;
        }
        else {
            throw new IllegalStateException(
                    "Trying to create schema without valid connection to the "
                            + "server!");
        }

    }

    /**
     * Drops the database with the provided name.
     *
     * @param databaseName
     *            the database name.
     * @throws SQLException
     *             if dropping the database fails.
     */
    public void dropDatabase(String databaseName)
            throws SQLException {

        if (this.connection != null) {
            this.statement = this.connection.createStatement();

            this.statement.executeUpdate("DROP DATABASE " + databaseName);

            this.statement.close();
            this.statement = null;
        }
        else {
            throw new IllegalStateException(
                    "Trying to drop database without valid connection to the "
                            + "server!");
        }

    }


    public void createUser(final String username, final String hostname,
            final String password)
            throws SQLException {

        if (this.connection != null) {
            this.statement = this.connection.createStatement();

            StringBuilder queryBuilder = new StringBuilder();

            queryBuilder.append("CREATE USER '");
            queryBuilder.append(username);
            queryBuilder.append("'@'");
            queryBuilder.append(hostname);
            queryBuilder.append("' IDENTIFIED BY '");
            queryBuilder.append(password);
            queryBuilder.append("';");

            System.out.println(queryBuilder);

            this.statement.executeUpdate(queryBuilder.toString());

            this.statement.close();
            this.statement = null;
        }
        else {
            throw new IllegalStateException(
                    "Trying to create user without valid connection to "
                            + "the server!");
        }
    }


    public void dropUser(final String username, final String hostname)
            throws SQLException {

        if (this.connection != null) {
            this.statement = this.connection.createStatement();

            StringBuilder queryBuilder = new StringBuilder();

            queryBuilder.append("DROP USER '");
            queryBuilder.append(username);
            queryBuilder.append("'@'");
            queryBuilder.append(hostname);
            queryBuilder.append("';");

            System.out.println(queryBuilder);

            this.statement.executeUpdate(queryBuilder.toString());

            this.statement.close();
            this.statement = null;
        }
        else {
            throw new IllegalStateException(
                    "Trying to drop user without valid connection to "
                            + "the server!");
        }
    }

    public void grantPrivileges(final Set<ISQLPrivileges> privileges,
            final String object, final String username, final String hostname,
            final String password)
            throws SQLException {

        System.out.println("privileges: " + privileges);
        if (this.connection != null) {
            this.statement = this.connection.createStatement();

            StringBuilder queryBuilder = new StringBuilder();

            queryBuilder.append("GRANT ");

            int index = 0;
            for (ISQLPrivileges privilege : privileges) {
                if (index != 0) {
                    queryBuilder.append(",");
                }
                queryBuilder.append(privilege.toString());
                index++;
            }

            queryBuilder.append(" ON ");
            queryBuilder.append(object);
            queryBuilder.append(" TO '");
            queryBuilder.append(username);
            queryBuilder.append("'@'");
            queryBuilder.append(hostname);
            queryBuilder.append("' IDENTIFIED BY '");
            queryBuilder.append(password);
            queryBuilder.append("';");

            System.out.println(queryBuilder);

            this.statement.executeUpdate(queryBuilder.toString());

            this.statement.close();
            this.statement = null;
        }
        else {
            throw new IllegalStateException(
                    "Trying to grant privileges without valid connection to "
                            + "the server!");
        }
    }

    // REVOKE privilege_name
    // ON object_name
    // FROM {user_name |PUBLIC |role_name}
    public void revokePrivileges(final Set<ISQLPrivileges> privileges,
            final String object, final String username, final String hostname)
            throws SQLException {

        System.out.println("privileges: " + privileges);
        if (this.connection != null) {
            this.statement = this.connection.createStatement();

            StringBuilder queryBuilder = new StringBuilder();

            queryBuilder.append("REVOKE ");

            int index = 0;
            for (ISQLPrivileges privilege : privileges) {
                if (index != 0) {
                    queryBuilder.append(",");
                }
                queryBuilder.append(privilege.toString());
                index++;
            }

            queryBuilder.append(" ON ");
            queryBuilder.append(object);
            queryBuilder.append(" FROM '");
            queryBuilder.append(username);
            queryBuilder.append("'@'");
            queryBuilder.append(hostname);
            queryBuilder.append("';");

            System.out.println(queryBuilder);

            this.statement.executeUpdate(queryBuilder.toString());

            this.statement.close();
            this.statement = null;
        }
        else {
            throw new IllegalStateException(
                    "Trying to revoke privileges without valid connection to "
                            + "the server!");
        }
    }


    /**
     * Returns a list of database schemas from the database server.
     *
     * @return a list of available database schemas.
     * @throws SQLException
     *             if querying the database server fails.
     */
    public List<String> getAvailableDatabaseSchemas()
            throws SQLException {

        ArrayList<String> databaseNames = new ArrayList<String>();

        String query = "SHOW DATABASES;";

        this.statement = connection.createStatement();

        this.resultSet = this.statement.executeQuery(query);

        ResultSetMetaData metadata = this.resultSet.getMetaData();


        String columnLabel = metadata.getColumnLabel(1);
        // System.out.println("The label of the column is " + columnLabel);

        while (resultSet.next()) {

            String name = resultSet.getString(columnLabel);


            System.out.println("Database name: " + name);
            databaseNames.add(name);

        }


        return databaseNames;
    }



    private void prepareQuery(Set<String> keys, String query)
            throws SQLException {

        this.prepareStatement(keys, query, false);
    }

    private void prepareStatement(Set<String> keys, String query,
            boolean returnKeys)
            throws SQLException {

        if (this.connection != null) {

            this.preparedStatement = null;
            System.out.println("Preparing Statement: " + query + " with keys: "
                    + keys);
            if (returnKeys) {

                // System.out.println("With key retrieval");
                this.preparedStatement = this.connection.prepareStatement(
                        query, Statement.RETURN_GENERATED_KEYS);
            }
            else {
                // System.out.println("Without key retrieval");
                this.preparedStatement = this.connection
                        .prepareStatement(query);
            }

            this.preparedStatementKeySet = keys;
        }
        else {
            throw new IllegalStateException(
                    "Trying to prepare statement without valid connection to the "
                            + "server!");
        }
    }


    /**
     * Executes a prepared statement with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @param returnGeneratedKeys
     *            whether or not to return generated keys.
     * @return the key generated through the execution of this statement or -1
     *         if not desired.
     * @throws SQLException
     *             if the execution of the statement fails.
     */
    private int executePreparedStatement(Map<String, ?> valueMap,
            boolean returnGeneratedKeys)
            throws SQLException {

        if (this.preparedStatement != null
                && this.preparedStatementKeySet != null) {

            System.out
                    .println("  Executing prepared statement with value map: "
                            + valueMap);
            int i = 1;
            for (String key : this.preparedStatementKeySet) {

                Object value = valueMap.get(key);

                // System.out.println("Setting value " + i + " to " + value);
                // System.out.println("Value's class: " + value.getClass());
                if (value == null) {

                    this.preparedStatement.setObject(i, null);
                }
                else if (value.getClass().isEnum()) {

                    this.preparedStatement.setObject(i, value.toString());
                }
                else {

                    this.preparedStatement.setObject(i, value);
                }
                i++;
            }
            this.preparedStatement.executeUpdate();

            int autoIncKeyFromApi = -1;

            if (returnGeneratedKeys) {

                ResultSet rs = this.preparedStatement.getGeneratedKeys();

                if (rs.next()) {

                    autoIncKeyFromApi = rs.getInt(1);
                }

                rs.close();
                rs = null;
            }
            return autoIncKeyFromApi;
        }

        else {
            throw new IllegalStateException(
                    "Trying to execute prepared statement that does not exist!");
        }

    }

    private ResultSet executePreparedQuery(Map<String, ?> valueMap)
            throws SQLException {

        if (this.preparedStatement != null) {

            if (this.preparedStatementKeySet == null && valueMap != null) {

                throw new SQLException("Trying to execute unchecked "
                        + "prepared query!");
            }
            if (this.preparedStatementKeySet != null && valueMap == null) {

                throw new SQLException("No parameters provided for "
                        + "prepared query!");
            }

            System.out.println("  Executing prepared query with value map: "
                    + valueMap);
            if (valueMap != null) {
                int i = 1;
                for (String key : this.preparedStatementKeySet) {

                    Object value = valueMap.get(key);
                    ;
                    if (value == null) {

                        this.preparedStatement.setObject(i, null);
                    }
                    else if (value.getClass().isEnum()) {

                        this.preparedStatement.setObject(i, value.toString());
                    }
                    else {

                        this.preparedStatement.setObject(i, value);
                    }
                    i++;
                }
            }
            return this.preparedStatement.executeQuery();

        }
        else {
            throw new SQLException(
                    "Trying to execute prepared statement that does not exist!");
        }
    }

    /**
     * Closes the prepared statement.
     *
     * @throws SQLException
     *             if closing the prepared statement fails.
     */
    public void closePreparedStatement()
            throws SQLException {

        if (this.preparedStatement != null) {

            this.preparedStatement.close();
            this.preparedStatement = null;
            this.preparedStatementKeySet = null;
        }
    }

    /**
     * Closes the prepared statement.
     *
     * @throws SQLException
     *             if closing the prepared statement fails.
     */
    public void closePreparedQuery()
            throws SQLException {

        this.closePreparedStatement();
    }

    /**
     * Determines whether an object identified by its primary keys exists in the
     * database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param primaryKeys
     *            the key-value map of the object's primary keys.
     * @return {@code true} if the object is contained in the table;
     *         {@code false} otherwise.
     * @throws SQLException
     *             if the query execution fails.
     */
    public boolean duplicateCheck(String database, String table,
            Map<String, ?> primaryKeys)
            throws SQLException {

        String query = DBUtils.duplicateQuery(database, table, primaryKeys);

        ResultSet result = this.executeQuery(query);

        boolean exists = false;

        while (result.next()) {

            exists = result.getBoolean(1);
        }

        return exists;
    }

    /**
     * Prepares a duplicate check for the keys in a provided database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keys
     *            the keys.
     * @throws SQLException
     *             if preparing the statement fails.
     */
    public void prepareDuplicateCheck(String database, String table,
            Set<String> keys)
            throws SQLException {

        this.prepareQuery(keys,
                DBUtils.prepareDuplicateQuery(database, table, keys));
    }

    /**
     * Executes a duplicate check determining whether an object identified by
     * its primary keys exists in the database table.
     *
     * @param keyValueMap
     *            the key-value map.
     *
     * @return {@code true} if the object is contained in the table;
     *         {@code false} otherwise.
     * @throws SQLException
     *             if the query execution fails.
     */
    public boolean executeDuplicateCheck(Map<String, ?> keyValueMap)
            throws SQLException {

        ResultSet result = this.executePreparedQuery(keyValueMap);

        boolean exists = false;

        while (result.next()) {

            exists = result.getBoolean(1);
        }

        return exists;
    }

    /**
     * Inserts the values in the provided key value map into the database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyValueMap
     *            the key value pair.
     * @return the generated primary key (if applicable).
     * @throws SQLException
     *             if insertion fails.
     */
    public int insert(String database, String table, Map<String, ?> keyValueMap)
            throws SQLException {

        String insertStatement = DBUtils.insertStatement(database, table,
                keyValueMap);

        return this.executeStatement(insertStatement, true);

    }

    /**
     * Prepares the insertion of data into the provided database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keys
     *            the keys that are to be inserted.
     * @throws SQLException
     *             if creation of the prepared statement fails.
     */
    public void prepareInsertion(String database, String table, Set<String> keys)
            throws SQLException {

        String insertStatement = DBUtils.prepareInsertStatement(database,
                table, keys);

        this.prepareStatement(keys, insertStatement, true);
    }

    /**
     * Executes the prepared insertion with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @return the key generated by the insertion.
     * @throws SQLException
     *             if the execution of the insertion fails.
     */
    public int executeInsertion(Map<String, ?> valueMap)
            throws SQLException {

        return this.executePreparedStatement(valueMap, true);
    }

    /**
     * Selects the values in the provided key value map from the database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyValueMap
     *            the key value pair.
     * @return the generated primary key (if applicable).
     * @throws SQLException
     *             if insertion fails.
     */
    public ResultSet selectAll(String database, String table,
            Map<String, ?> keyValueMap)
            throws SQLException {

        String selectQuery = DBUtils.selectAllQuery(database, table,
                keyValueMap);

        return this.executeQuery(selectQuery);
    }


    /**
     * Prepares the selection of data from the provided database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keys
     *            the keys that are to be inserted.
     * @throws SQLException
     *             if creation of the prepared statement fails.
     */
    public void prepareUniversalSelection(String database, String table,
            Set<String> keys)
            throws SQLException {

        String selectQuery = DBUtils.prepareSelectAllQuery(database, table,
                keys);

        this.prepareQuery(keys, selectQuery);
    }


    /**
     * Executes the prepared insertion with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @return the key generated by the insertion.
     * @throws SQLException
     *             if the execution of the insertion fails.
     */
    public ResultSet executeUniversalSelection(Map<String, ?> valueMap)
            throws SQLException {

        return this.executePreparedQuery(valueMap);
    }


    /**
     * Selects the values in the provided key value map from the database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param valueColumns
     *            the value columns.
     * @param keyValueMap
     *            the key value pair.
     * @return the generated primary key (if applicable).
     * @throws SQLException
     *             if insertion fails.
     */
    public ResultSet select(String database, String table,
            Set<String> valueColumns, Map<String, ?> keyValueMap)
            throws SQLException {

        String selectQuery = DBUtils.selectQuery(database, table, valueColumns,
                keyValueMap);

        return this.executeQuery(selectQuery);
    }

    /**
     * Prepares the selection of data from the provided database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param valueColumns
     * @param keyColumns
     *            the keys that are to be inserted.
     * @throws SQLException
     *             if creation of the prepared statement fails.
     */
    public void prepareSelection(String database, String table,
            Set<String> valueColumns, Set<String> keyColumns)
            throws SQLException {

        String selectQuery = DBUtils.prepareSelectQuery(database, table,
                valueColumns, keyColumns);

        Set<String> keys = new LinkedHashSet<String>();
        keys.addAll(valueColumns);
        keys.addAll(keyColumns);

        this.prepareQuery(keys, selectQuery);
    }

    /**
     * Executes the prepared selection with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @return the key generated by the insertion.
     * @throws SQLException
     *             if the execution of the insertion fails.
     */
    public ResultSet executeSelection(Map<String, ?> valueMap)
            throws SQLException {

        return this.executePreparedQuery(valueMap);
    }



    /**
     * Updates the values in the provided key value map in the database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyMap
     *            the key map.
     * @param valueMap
     *            the value map.
     * @throws SQLException
     *             if update fails.
     */
    public void update(String database, String table, Map<String, ?> keyMap,
            Map<String, ?> valueMap)
            throws SQLException {

        String updateStatement = DBUtils.updateStatement(database, table,
                keyMap, valueMap);


        this.executeStatement(updateStatement, false);

    }

    /**
     * Prepares the update of data in the provided database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyColumns
     *            the key columns.
     * @param valueColumns
     *            the value columns.
     * @throws SQLException
     *             if creation of the prepared statement fails.
     */
    public void prepareUpdate(String database, String table,
            Set<String> keyColumns, Set<String> valueColumns)
            throws SQLException {

        String insertStatement = DBUtils.prepareUpdateStatement(database,
                table, keyColumns, valueColumns);

        Set<String> keys = new LinkedHashSet<String>();

        keys.addAll(valueColumns);
        keys.addAll(keyColumns);

        this.prepareStatement(keys, insertStatement, false);
    }

    /**
     * Executes the prepared update with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @throws SQLException
     *             if the execution of the update fails.
     */
    public void executeUpdate(Map<String, ?> valueMap)
            throws SQLException {

        this.executePreparedStatement(valueMap, false);
    }


    /**
     * Deletes the values in the provided key value map from the database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyValueMap
     *            the key value pair.
     * @throws SQLException
     *             if deletion fails.
     */
    public void delete(String database, String table, Map<String, ?> keyValueMap)
            throws SQLException {

        String deleteStatement = DBUtils.deleteStatement(database, table,
                keyValueMap);

        this.executeStatement(deleteStatement, false);

    }

    /**
     * Prepares the deletion of data from the provided database table.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keys
     *            the keys that are to be inserted.
     * @throws SQLException
     *             if creation of the prepared statement fails.
     */
    public void prepareDeletion(String database, String table, Set<String> keys)
            throws SQLException {

        String deleteStatement = DBUtils.prepareDeleteStatement(database,
                table, keys);

        this.prepareStatement(keys, deleteStatement, false);
    }


    /**
     * Executes the prepared deletion with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @throws SQLException
     *             if the execution of the deletion fails.
     */
    public void executeDeletion(Map<String, ?> valueMap)
            throws SQLException {

        this.executePreparedStatement(valueMap, false);
    }


}