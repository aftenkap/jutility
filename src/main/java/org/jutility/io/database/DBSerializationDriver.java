package org.jutility.io.database;

/*
 * #%L
 * jutility-incubation
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.net.URI;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jutility.io.database.mysql.MySQLDriver;


/**
 *
 *
 *
 * @author Peter J. Radics
 * @version 0.1
 */
public class DBSerializationDriver {


    private String            username;
    private String            password;

    private URI               databaseServer;
    private String            schema;

    private final MySQLDriver databaseDriver;

    /**
     * Sets the database server.
     *
     * @param databaseServer
     *            the database server.
     */
    public void setDatabaseServer(URI databaseServer) {

        this.databaseServer = databaseServer;
    }

    /**
     * Sets the username.
     *
     * @param username
     *            the username.
     */
    public void setUsername(String username) {

        this.username = username;
    }


    /**
     * Sets the password.
     *
     * @param password
     *            the password.
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * Sets the schema.
     *
     * @param schema
     *            the schema.
     */
    public void setSchema(String schema) {

        this.schema = schema;
    }


    /**
     * Creates a new instance of the {@link DBSerializationDriver} class.
     *
     * @throws DBSerializationException
     *             if the MySQLDriver cannot be initialized.
     */
    public DBSerializationDriver()
            throws DBSerializationException {

        try {
            this.databaseDriver = MySQLDriver.Instance();
        }
        catch (ClassNotFoundException e) {

            throw new DBSerializationException(
                    "Could not initialize Database Driver", e);
        }
    }


    /**
     * Returns whether or not the Driver is connected to the database server.
     *
     * @return {@code true} if the driver is connected; {@code false} otherwise.
     */
    public boolean isConnected() {

        return this.databaseDriver.isConnected();
    }

    /**
     * Connects to the database.
     *
     * @throws DBSerializationException
     */
    public void connectToDatabase()
            throws DBSerializationException {

        if (!this.isConnected()) {
            try {

                this.databaseDriver.connect(this.databaseServer.toString(),
                        this.schema, this.username, this.password);
                // "privacyworkbench", "Pr1vacy$ystem");
            }
            catch (SQLException e) {

                throw new DBSerializationException(
                        "Could not connect to the database!", e);
            }
        }
    }

    /**
     * Closes the database connection.
     *
     * @throws DBSerializationException
     */
    public void closeDatabaseConnection()
            throws DBSerializationException {

        if (this.isConnected()) {

            try {

                this.databaseDriver.close();
            }
            catch (SQLException e) {

                throw new DBSerializationException(
                        "Could not close the connection to the database!", e);
            }
        }
    }

    /**
     * Closes the prepared statement.
     *
     * @throws DBSerializationException
     *             if closing the prepared statement fails.
     */
    public void closePreparedStatement()
            throws DBSerializationException {

        try {
            this.databaseDriver.closePreparedStatement();
        }
        catch (SQLException e) {

            throw new DBSerializationException(
                    "Could not close prepared statement!", e);
        }
    }

    /**
     * Closes the prepared statement.
     *
     * @throws DBSerializationException
     *             if closing the prepared statement fails.
     */
    public void closePreparedQuery()
            throws DBSerializationException {

        try {
            this.databaseDriver.closePreparedStatement();
        }
        catch (SQLException e) {

            throw new DBSerializationException(
                    "Could not close prepared statement!", e);
        }
    }

    /**
     * Determines whether an object identified by its primary keys exists in the
     * database table.
     *
     * @param table
     *            the table.
     * @param primaryKeys
     *            the key-value map of the object's primary keys.
     * @return {@code true} if the object is contained in the table;
     *         {@code false} otherwise.
     * @throws DBSerializationException
     *             if the query execution fails.
     */
    public boolean duplicateCheck(String table, Map<String, ?> primaryKeys)
            throws DBSerializationException {

        try {
            return this.databaseDriver.duplicateCheck(this.schema, table,
                    primaryKeys);
        }
        catch (SQLException e) {

            throw new DBSerializationException(
                    "Could not determine containment!", e);
        }
    }


    /**
     * Prepares a duplicate check for the keys in a provided database table.
     *
     * @param table
     *            the table.
     * @param keys
     *            the keys.
     * @throws DBSerializationException
     *             if preparing the statement fails.
     */
    public void prepareDuplicateCheck(String table, Set<String> keys)
            throws DBSerializationException {

        try {

            this.databaseDriver.prepareDuplicateCheck(schema, table, keys);
        }
        catch (SQLException e) {

            throw new DBSerializationException(
                    "Could not determine containment!", e);
        }
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
     * @throws DBSerializationException
     *             if the query execution fails.
     */
    public boolean executeDuplicateCheck(Map<String, ?> keyValueMap)
            throws DBSerializationException {

        try {
            return this.databaseDriver.executeDuplicateCheck(keyValueMap);
        }
        catch (SQLException e) {

            throw new DBSerializationException(
                    "Could not determine containment!", e);
        }
    }

    /**
     * Inserts the values in the provided key value map into the database table.
     *
     * @param table
     *            the table.
     * @param keyValueMap
     *            the key value pair.
     * @return the generated primary key (if applicable).
     * @throws DBSerializationException
     *             if insertion fails.
     */
    public int insert(String table, Map<String, ?> keyValueMap)
            throws DBSerializationException {

        try {
            return this.databaseDriver.insert(this.schema, table, keyValueMap);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Insertion failed!", e);
        }
    }

    /**
     * Prepares the insertion of data into the provided database table.
     *
     * @param table
     *            the table.
     * @param keys
     *            the keys that are to be inserted.
     * @throws DBSerializationException
     *             if creation of the prepared statement fails.
     */
    public void prepareInsertion(String table, Set<String> keys)
            throws DBSerializationException {

        try {
            this.databaseDriver.prepareInsertion(this.schema, table, keys);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Preparing insertion failed!", e);
        }
    }

    /**
     * Executes the prepared insertion with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @return the key generated by the insertion.
     * @throws DBSerializationException
     *             if the execution of the insertion fails.
     */
    public int executeInsertion(Map<String, ?> valueMap)
            throws DBSerializationException {

        try {
            return this.databaseDriver.executeInsertion(valueMap);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Insertion failed!", e);
        }
    }

    /**
     * Selects the values in the provided key value map from the database table.
     *
     * @param table
     *            the table.
     * @param keyValueMap
     *            the key value pair.
     * @return the generated primary key (if applicable).
     * @throws DBSerializationException
     *             if insertion fails.
     */
    public ResultSet selectAll(String table, Map<String, ?> keyValueMap)
            throws DBSerializationException {

        try {

            return this.databaseDriver.selectAll(this.schema, table,
                    keyValueMap);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Selection failed!", e);
        }
    }

    /**
     * Prepares the selection of data from the provided database table.
     *
     * @param table
     *            the table.
     * @param keys
     *            the keys that are to be inserted.
     * @throws DBSerializationException
     *             if creation of the prepared statement fails.
     */
    public void prepareUniversalSelection(String table, Set<String> keys)
            throws DBSerializationException {


        try {

            this.databaseDriver.prepareUniversalSelection(this.schema, table,
                    keys);
        }
        catch (SQLException e) {

            throw new DBSerializationException(
                    "Preparing universal selection failed!", e);
        }
    }

    /**
     * Executes the prepared insertion with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @return the key generated by the insertion.
     * @throws DBSerializationException
     *             if the execution of the insertion fails.
     */
    public ResultSet executeUniversalSelection(Map<String, ?> valueMap)
            throws DBSerializationException {

        try {

            return this.databaseDriver.executeUniversalSelection(valueMap);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Universal selection failed!", e);
        }
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
     * @throws DBSerializationException
     *             if insertion fails.
     */
    public ResultSet select(String database, String table,
            Set<String> valueColumns, Map<String, ?> keyValueMap)
            throws DBSerializationException {

        try {

            return this.databaseDriver.select(schema, table, valueColumns,
                    keyValueMap);
        }
        catch (SQLException e) {
            throw new DBSerializationException("Selection failed!", e);
        }
    }

    /**
     * Prepares the selection of data from the provided database table.
     *
     * @param table
     *            the table.
     * @param valueColumns
     * @param keyColumns
     *            the keys that are to be inserted.
     * @throws DBSerializationException
     *             if creation of the prepared statement fails.
     */
    public void prepareSelection(String table, Set<String> valueColumns,
            Set<String> keyColumns)
            throws DBSerializationException {

        try {

            this.databaseDriver.prepareSelection(schema, table, valueColumns,
                    keyColumns);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Preparing selection failed!", e);
        }
    }

    /**
     * Executes the prepared selection with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @return the key generated by the insertion.
     * @throws DBSerializationException
     *             if the execution of the insertion fails.
     */
    public ResultSet executeSelection(Map<String, ?> valueMap)
            throws DBSerializationException {

        try {
            return this.databaseDriver.executeSelection(valueMap);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Selection failed!", e);
        }
    }



    /**
     * Updates the values in the provided key value map in the database table.
     *
     * @param table
     *            the table.
     * @param keyMap
     *            the key map.
     * @param valueMap
     *            the value map.
     * @throws DBSerializationException
     *             if update fails.
     */
    public void update(String table, Map<String, ?> keyMap,
            Map<String, ?> valueMap)
            throws DBSerializationException {

        try {
            this.databaseDriver.update(schema, table, keyMap, valueMap);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Update failed!", e);
        }

    }

    /**
     * Prepares the update of data in the provided database table.
     *
     * @param table
     *            the table.
     * @param keyColumns
     *            the key columns.
     * @param valueColumns
     *            the value columns.
     * @throws DBSerializationException
     *             if creation of the prepared statement fails.
     */
    public void prepareUpdate(String table, Set<String> keyColumns,
            Set<String> valueColumns)
            throws DBSerializationException {

        try {
            this.databaseDriver.prepareUpdate(schema, table, keyColumns,
                    valueColumns);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Preparing update failed!", e);
        }
    }

    /**
     * Executes the prepared update with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @throws DBSerializationException
     *             if the execution of the update fails.
     */
    public void executeUpdate(Map<String, ?> valueMap)
            throws DBSerializationException {

        try {

            this.databaseDriver.executeUpdate(valueMap);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Update failed!", e);
        }
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
     * @throws DBSerializationException
     *             if deletion fails.
     */
    public void delete(String database, String table, Map<String, ?> keyValueMap)
            throws DBSerializationException {

        try {
            this.databaseDriver.delete(schema, table, keyValueMap);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Delete failed!", e);
        }

    }

    /**
     * Prepares the deletion of data from the provided database table.
     *
     * @param table
     *            the table.
     * @param keys
     *            the keys that are to be inserted.
     * @throws DBSerializationException
     *             if creation of the prepared statement fails.
     */
    public void prepareDeletion(String table, Set<String> keys)
            throws DBSerializationException {

        try {
            this.databaseDriver.prepareDeletion(schema, table, keys);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Preparing delete failed!", e);
        }

    }


    /**
     * Executes the prepared deletion with the provided value map.
     *
     * @param valueMap
     *            the value map.
     * @throws DBSerializationException
     *             if the execution of the deletion fails.
     */
    public void executeDeletion(Map<String, ?> valueMap)
            throws DBSerializationException {

        try {
            this.databaseDriver.executeDeletion(valueMap);
        }
        catch (SQLException e) {

            throw new DBSerializationException("Delete failed!", e);
        }
    }


    /**
     * Converts a result set into a list of maps.
     *
     * @param result
     * @param indexType
     * @return a list of maps.
     * @throws DBSerializationException
     */
    public <T> List<Map<T, ?>> convertResultSet(ResultSet result,
            Class<? extends T> indexType)
            throws DBSerializationException {

        List<Map<T, ?>> resultRows = new LinkedList<Map<T, ?>>();

        try {

            while (result.next()) {

                resultRows.add(this.convertSingleResult(result, indexType));
            }
        }
        catch (SQLException e) {

            throw new DBSerializationException("Could not convert result set!",
                    e);
        }

        return resultRows;
    }

    /**
     * Converts a single result set into a map.
     *
     * @param result
     * @param indexType
     * @return a map.
     * @throws DBSerializationException
     */
    public <T> Map<T, ?> convertSingleResult(ResultSet result,
            Class<? extends T> indexType)
            throws DBSerializationException {

        if (indexType != String.class && indexType != Integer.class
                && indexType != int.class) {

            throw new DBSerializationException(indexType
                    + " is not a valid index type! Use either String "
                    + "or Integer!");
        }

        Map<T, Object> convertedResult = new LinkedHashMap<T, Object>();

        try {
            if (result.isBeforeFirst()) {

                result.next();
            }

            ResultSetMetaData rsmd = result.getMetaData();

            int numberOfColumns = rsmd.getColumnCount();


            for (int columnIndex = 1; columnIndex <= numberOfColumns; columnIndex++) {

                T index = null;
                if (indexType.isAssignableFrom(String.class)) {

                    index = indexType.cast(rsmd.getColumnName(columnIndex));
                }
                else if (indexType.isAssignableFrom(Integer.class)) {

                    index = indexType.cast(columnIndex);
                }
                convertedResult.put(index, result.getObject(columnIndex));

            }
        }
        catch (SQLException e) {

            throw new DBSerializationException("Could not convert result set!",
                    e);
        }

        return convertedResult;
    }

}
