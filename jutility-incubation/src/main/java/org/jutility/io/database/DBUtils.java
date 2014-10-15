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


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class DBUtils {


    /**
     * Prepares an {@code INSERT} statement for a key {@link Set}.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keySet
     *            the key {@link Set}.
     * @return a {@code INSERT} statement.
     */
    public static String prepareInsertStatement(String database, String table,
            Set<String> keySet) {

        return DBUtils.createInsertStatement(database, table,
                DBUtils.keySetToKeyMap(keySet), true);
    }

    /**
     * Generates a {@code INSERT} statement from a value {@link Map}.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param valueMap
     *            the value {@link Map}.
     * @return a {@code INSERT} statement.
     */
    public static String insertStatement(String database, String table,
            Map<String, ?> valueMap) {

        return DBUtils.createInsertStatement(database, table, valueMap, false);
    }


    private static String createInsertStatement(String database, String table,
            Map<String, ?> valueMap, boolean prepareStatement) {

        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO `" + database + "`.`" + table + "` (");
        int i = 0;
        for (String key : valueMap.keySet()) {

            if (i > 0) {

                query.append(", ");
            }
            query.append(key);
            i++;
        }
        query.append(") VALUES (");

        int j = 0;
        for (String key : valueMap.keySet()) {

            if (j > 0) {

                query.append(", ");
            }
            if (prepareStatement) {

                query.append("?");
            }
            else {

                query.append(valueMap.get(key));
            }
            j++;
        }

        query.append(");");

        System.out.println("Insert query generated: " + query.toString());

        return query.toString();
    }


    /**
     * Prepares a {@code SELECT *} statement for a {@link Set} of key columns.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyColumns
     *            the {@link Set} of key columns.
     * @return a {@code UPDATE} statement.
     */
    public static String prepareSelectAllQuery(String database, String table,
            Set<String> keyColumns) {

        return DBUtils.createSelectQuery(database, table, null,
                DBUtils.keySetToKeyMap(keyColumns), true);
    }

    /**
     * Generates a {@code SELECT *} query from a key {@link Map}.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyMap
     *            the key {@link Map}.
     * @return a {@code SELECT *} query.
     */
    public static String selectAllQuery(String database, String table,
            Map<String, ?> keyMap) {

        return DBUtils.createSelectQuery(database, table, null, keyMap, false);
    }

    /**
     * Prepares a {@code UPDATE} statement for a {@link Set} of key columns and
     * a {@link Set} of value columns.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyColumns
     *            the {@link Set} of key columns.
     * @param valueColumns
     *            the {@link Set} of value columns.
     * @return a {@code UPDATE} statement.
     */
    public static String prepareSelectQuery(String database, String table,
            Set<String> valueColumns, Set<String> keyColumns) {

        return DBUtils.createSelectQuery(database, table, valueColumns,
                DBUtils.keySetToKeyMap(keyColumns), false);
    }


    /**
     * Generates a {@code SELECT} query from a key {@link Map}.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyMap
     *            the key {@link Map}.
     * @param valueColumns
     *            the {@link Set} of value columns.
     * @return a {@code SELECT} query.
     */
    public static String selectQuery(String database, String table,
            Set<String> valueColumns, Map<String, ?> keyMap) {

        return DBUtils.createSelectQuery(database, table, valueColumns, keyMap,
                false);
    }


    private static String createSelectQuery(String database, String table,
            Set<String> valueColumns, Map<String, ?> keyMap,
            boolean prepareStatement) {


        StringBuilder query = new StringBuilder();


        query.append("SELECT ");

        if (valueColumns == null || valueColumns.isEmpty()) {
            query.append("*");
        }
        else {

            int i = 0;
            for (String column : valueColumns) {

                if (i > 0) {
                    query.append(", ");
                }

                query.append(column);
                i++;
            }

        }
        query.append(" FROM `" + database + "`.`" + table + "`");

        query.append(DBUtils.createWhereStatement(keyMap, prepareStatement));

        query.append("");

        System.out.println("Select query generated: " + query.toString());
        return query.toString();
    }



    /**
     * Prepares a {@code UPDATE} statement for a {@link Set} of key columns and
     * a {@link Set} of value columns.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyColumns
     *            the {@link Set} of key columns.
     * @param valueColumns
     *            the {@link Set} of value columns.
     * @return a {@code UPDATE} statement.
     */
    public static String prepareUpdateStatement(String database, String table,
            Set<String> keyColumns, Set<String> valueColumns) {

        return DBUtils.createUpdateStatement(database, table,
                DBUtils.keySetToKeyMap(keyColumns),
                DBUtils.keySetToKeyMap(valueColumns), true);
    }


    /**
     * Generates a {@code UPDATE} statement from key and value {@link Map Maps}.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyMap
     *            the key {@link Map}.
     * @param valueMap
     *            the value {@link Map}.
     * @return a {@code UPDATE} statement.
     */
    public static String updateStatement(String database, String table,
            Map<String, ?> keyMap, Map<String, ?> valueMap) {

        return DBUtils.createUpdateStatement(database, table, keyMap, valueMap,
                false);
    }


    private static String createUpdateStatement(String database, String table,
            Map<String, ?> keyMap, Map<String, ?> valueMap,
            boolean prepareStatement) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE `" + database + "`.`" + table + "` SET ");


        query.append(DBUtils.createValueAssignment(valueMap, prepareStatement));

        query.append(DBUtils.createWhereStatement(keyMap, prepareStatement));

        query.append(";");

        System.out.println("Update query generated: " + query.toString());

        return query.toString();
    }



    /**
     * Prepares a {@code DELETE} statement for a key {@link Set}.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keySet
     *            the key {@link Set}.
     * @return a {@code DELETE} statement.
     */
    public static String prepareDeleteStatement(String database, String table,
            Set<String> keySet) {

        return DBUtils.createDeleteStatement(database, table,
                DBUtils.keySetToKeyMap(keySet), true);
    }


    /**
     * Generates a {@code DELETE} statement from a key {@link Map}.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyMap
     *            the key {@link Map}.
     * @return a {@code DELETE} statement.
     */
    public static String deleteStatement(String database, String table,
            Map<String, ?> keyMap) {

        return DBUtils.createDeleteStatement(database, table, keyMap, false);
    }


    private static String createDeleteStatement(String database, String table,
            Map<String, ?> keyMap, boolean prepareStatement) {


        StringBuilder query = new StringBuilder();


        query.append("DELETE FROM `" + database + "`.`" + table + "`");

        query.append(DBUtils.createWhereStatement(keyMap, prepareStatement));

        query.append(";");

        System.out.println("Delete query generated: " + query.toString());
        return query.toString();
    }



    /**
     * Prepares a {@code value assignment} statement for a key {@link Set}.
     *
     * @param keySet
     *            the key {@link Set}.
     * @return a {@code value assignment} statement.
     */
    public static String prepareValueAssignment(Set<String> keySet) {

        return DBUtils.createValueAssignment(DBUtils.keySetToKeyMap(keySet),
                true);
    }


    /**
     * Generates a {@code value assignment} statement from a value {@link Map}.
     *
     * @param valueMap
     *            the value {@link Map}.
     * @return a {@code value assignment} statement.
     */
    public static String valueAssignment(Map<String, ?> valueMap) {

        return DBUtils.createValueAssignment(valueMap, false);
    }


    private static String createValueAssignment(Map<String, ?> valueMap,
            boolean prepareStatement) {

        StringBuilder query = new StringBuilder();
        int i = 0;
        for (String key : valueMap.keySet()) {
            if (i > 0) {
                query.append(", ");
            }

            query.append(key);

            if (prepareStatement) {

                query.append("= ?");
            }
            else {

                query.append("= " + valueMap.get(key));
            }
            i++;
        }

        return query.toString();
    }



    /**
     * Prepares a {@code WHERE} statement for a key {@link Set}.
     *
     * @param keySet
     *            the key {@link Set}.
     * @return a {@code WHERE} statement.
     */
    public static String prepareWhereStatement(Set<String> keySet) {


        return DBUtils.createWhereStatement(DBUtils.keySetToKeyMap(keySet),
                true);
    }


    /**
     * Generates a {@code WHERE} statement from a key {@link Map}.
     *
     * @param keyMap
     *            the key {@link Map}.
     * @return a {@code WHERE} statement.
     */
    public static String whereStatement(Map<String, ?> keyMap) {

        return DBUtils.createWhereStatement(keyMap, false);
    }


    private static String createWhereStatement(Map<String, ?> keyMap,
            boolean prepareStatement) {

        StringBuilder query = new StringBuilder();

        if (keyMap != null && !keyMap.isEmpty()) {

            query.append(" WHERE ");

            int j = 0;
            for (String key : keyMap.keySet()) {

                if (j > 0) {
                    query.append(" AND ");
                }
                query.append(key);

                if (prepareStatement) {

                    query.append("= ?");
                }
                else {

                    query.append("= " + keyMap.get(key));
                }
                j++;
            }
        }
        return query.toString();
    }



    /**
     * Prepares a duplicate check query.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyColumns
     *            the key columns.
     *
     * @return the duplicate query.
     */
    public static String prepareDuplicateQuery(String database, String table,
            Set<String> keyColumns) {

        return DBUtils.createDuplicateQuery(database, table,
                DBUtils.keySetToKeyMap(keyColumns), true);
    }


    /**
     * Generates a duplicate query.
     *
     * @param database
     *            the database.
     * @param table
     *            the table.
     * @param keyMap
     *            the key map.
     * @return the duplicate query.
     */
    public static String duplicateQuery(String database, String table,
            Map<String, ?> keyMap) {

        return DBUtils.createDuplicateQuery(database, table, keyMap, false);
    }


    private static String createDuplicateQuery(String database, String table,
            Map<String, ?> keyMap, boolean prepareQuery) {

        String query = "SELECT EXISTS(";

        query += DBUtils.createSelectQuery(database, table, keyMap.keySet(),
                keyMap, prepareQuery);

        query += ");";


        System.out.println("Duplicate query generated: " + query.toString());
        return query;
    }



    /**
     * Converts a {@link Set} of keys into an equivalent {@link Map} with
     * {@code null} values.
     *
     * @param keySet
     *            the key {@link Set} to convert
     * @return an equivalent {@link Map} with {@code null} values
     */
    public static Map<String, ?> keySetToKeyMap(Set<String> keySet) {

        if (keySet == null) {

            return null;
        }

        Map<String, ?> keyMap = new LinkedHashMap<String, Object>();

        for (String key : keySet) {

            keyMap.put(key, null);
        }

        return keyMap;
    }


    /**
     * Converts a key-value {@link Map} into a {@link List} of values.
     *
     * @param keyValueMap
     *            the key-value {@link Map}.
     * @return a {@link List} of values.
     */
    public static List<?> keyValueMapToValueList(Map<String, ?> keyValueMap) {

        List<Object> valueList = new LinkedList<Object>();

        for (String key : keyValueMap.keySet()) {

            valueList.add(keyValueMap.get(key));
        }

        return valueList;

    }
}
