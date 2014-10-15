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


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.jutility.common.reflection.ReflectionUtils;


/**
 *
 * @author Peter J. Radics
 * @version 0.1
 *
 */
public class PropertyInfo
        extends BaseProperty {


    private final Map<String, String> columnMap;
    private final boolean             notNull;



    /**
     * Returns the column map.
     *
     * @return the column map.
     */
    public Map<String, String> getColumnAliasMap() {

        return columnMap;
    }

    /**
     * Returns the columns.
     *
     * @return the columns.
     */
    public Set<String> getColumns() {

        return this.columnMap.keySet();
    }

    /**
     * Returns the aliased columns.
     *
     * @return the aliased columns.
     */
    public Set<String> getAliasedColumns() {

        Set<String> aliasedColumns = new LinkedHashSet<String>();

        for (String key : this.columnMap.keySet()) {

            aliasedColumns.add(this.columnMap.get(key));
        }

        return aliasedColumns;
    }


    /**
     * Returns whether or not the property may be null.
     *
     * @return {@code true}, if the property may be null; {@code false}
     *         otherwise.
     */
    public boolean isNotNull() {

        return this.notNull;
    }


    /**
     * Creates a new instance of the {@link PropertyInfo} class.
     *
     * @param propertyName
     * @param propertyType
     * @param columnMap
     * @param notNull
     */
    public PropertyInfo(String propertyName, Class<?> propertyType,
            Map<String, String> columnMap, boolean notNull) {

        this(propertyName, propertyType, columnMap, notNull, null, null, null);
    }

    /**
     * Creates a new instance of the {@link PropertyInfo} class.
     *
     * @param propertyName
     * @param propertyType
     * @param columnMap
     * @param notNull
     * @param field
     */
    public PropertyInfo(String propertyName, Class<?> propertyType,
            Map<String, String> columnMap, Field field, boolean notNull) {

        this(propertyName, propertyType, columnMap, notNull, field, null, null);
    }

    /**
     * Creates a new instance of the {@link PropertyInfo} class.
     *
     * @param propertyName
     * @param propertyType
     * @param columnMap
     * @param notNull
     * @param getter
     * @param setter
     */
    public PropertyInfo(String propertyName, Class<?> propertyType,
            Map<String, String> columnMap, Method getter, Method setter,
            boolean notNull) {

        this(propertyName, propertyType, columnMap, notNull, null, getter,
                setter);
    }


    /**
     * Creates a new instance of the {@link PropertyInfo} class.
     *
     * @param propertyName
     * @param propertyType
     * @param columnMap
     * @param notNull
     * @param field
     * @param getter
     * @param setter
     */
    public PropertyInfo(String propertyName, Class<?> propertyType,
            Map<String, String> columnMap, boolean notNull, Field field,
            Method getter, Method setter) {

        super(propertyName, propertyType);

        this.columnMap = columnMap;
        this.notNull = notNull;
    }

    /**
     * Returns the column names (The column name in the referenced table
     * (e.g., _id in a mapping _id=tableID).
     *
     * @return the column names.
     */
    public Set<String> getColumnNames() {

        return this.columnMap.keySet();
    }

    /**
     * Returns the aliased key column names (The column name in the link table
     * (e.g., tableID in a mapping _id=tableID).
     *
     * @return the aliased key column names.
     */
    public Set<String> getAliasedColumnNames() {

        Set<String> columnNames = new LinkedHashSet<String>();

        for (String columnName : this.columnMap.values()) {

            columnNames.add(columnName);
        }

        return columnNames;
    }

    /**
     * Returns the column name for an aliased column name (e.g., _id in a
     * mapping _id=tableID).
     *
     * @param aliasedColumnName
     *            the aliased column name.
     * @return the column name.
     */
    public String getColumnName(String aliasedColumnName) {

        for (String columnName : this.columnMap.keySet()) {

            String columnNameAlias = this.columnMap.get(columnName);

            if (columnNameAlias != null && columnNameAlias.equals(aliasedColumnName)) {

                return columnName;
            }

        }
        return null;
    }

    /**
     * Returns the aliased column name for a column name (e.g., tableID in a
     * mapping _id=tableID).
     *
     * @param columnName
     *            the column name.
     * @return the column name.
     */
    public String getAliasedColumnName(String columnName) {

        return this.columnMap.get(columnName);
    }

    /**
     * Returns the mutation signature.
     *
     * @return the mutation signature.
     */
    public String mutationSignature() {

        String signature = "a field";
        if (this.getSetter() != null) {

            signature = ReflectionUtils.getSignature(this.getSetter());
        }
        else if (this.getField() != null) {

            signature = ReflectionUtils.getSignature(this.getField());
        }
        return signature;
    }

    /**
     * Returns the access signature.
     *
     * @return the access signature.
     */
    public String accesssSignature() {

        String signature = "a field";
        if (this.getGetter() != null) {

            signature = ReflectionUtils.getSignature(this.getGetter());
        }
        else if (this.getField() != null) {

            signature = ReflectionUtils.getSignature(this.getField());
        }
        return signature;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof PropertyInfo) {
            PropertyInfo otherEntry = (PropertyInfo) obj;
            return this.getPropertyName().equals(otherEntry.getPropertyName());
        }

        return false;
    }

    @Override
    public int hashCode() {

        return this.getPropertyName().hashCode();
    }

    @Override
    public String toString() {

        String returnValue = this.getPropertyName() + "->";

        returnValue += "{";
        int i = 0;
        for (String primaryKey : this.columnMap.keySet()) {
            if (i > 0) {
                returnValue += ", ";
            }
            returnValue += primaryKey + "=" + this.columnMap.get(primaryKey);
        }

        returnValue += "{";
        returnValue += ": Field: " + (this.getField() != null);

        returnValue += ", Getter: " + (this.getGetter() != null);

        returnValue += ", Setter: " + (this.getSetter() != null);


        return returnValue;
    }

}
