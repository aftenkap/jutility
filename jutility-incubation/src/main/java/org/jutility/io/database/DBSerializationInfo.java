package org.jutility.io.database;


/*
 * #%L jutility-incubation %% Copyright (C) 2013 - 2014 jutility.org %% Licensed
 * under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. #L%
 */


import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.jutility.io.database.annotation.DBElementType;
import org.jutility.io.database.annotation.DBValueType;


/**
 * @author Peter J. Radics
 * @version 0.1
 *
 */
public class DBSerializationInfo {

    private final Class<?>        type;

    private String                table;

    private Set<String>           constructorParameters;

    private Set<PropertyInfo>     primaryKeys;

    private Set<PropertyInfo>     valueTypeProperties;
    private Set<ListPropertyInfo> valueTypeListProperties;


    private Set<PropertyInfo>     elementTypeProperties;
    private Set<ListPropertyInfo> elementTypeListProperties;


    /**
     * Returns the type.
     * 
     * @return the type.
     */
    public Class<?> getType() {

        return type;
    }

    /**
     * @return the ValueType Properties
     */
    public Set<PropertyInfo> getValueTypeProperties() {

        return valueTypeProperties;
    }

    /**
     * Returns the value type property keys.
     * 
     * @return the value type property keys.
     */
    public Set<String> getValueTypePropertyKeys() {

        Set<String> valueTypePropertyKeys = new LinkedHashSet<String>();

        for (PropertyInfo propertyInfo : this.getValueTypeProperties()) {

            valueTypePropertyKeys.addAll(propertyInfo.getAliasedColumns());
        }

        return valueTypePropertyKeys;

    }

    /**
     * @return the ValueType ListProperties
     */
    public Set<ListPropertyInfo> getValueTypeListProperties() {

        return valueTypeListProperties;
    }

    /**
     * @return the ElementType Properties
     */
    public Set<PropertyInfo> getElementTypeProperties() {

        return elementTypeProperties;
    }

    /**
     * @return the ElementType Property keys
     */
    public Set<String> getElementTypePropertyKeys() {

        Set<String> elementTypePropertyKeys = new LinkedHashSet<String>();

        for (PropertyInfo propertyInfo : this.getElementTypeProperties()) {

            elementTypePropertyKeys.addAll(propertyInfo.getAliasedColumns());
        }

        return elementTypePropertyKeys;
    }

    /**
     * @return the ElementType ListProperties
     */
    public Set<ListPropertyInfo> getElementTypeListProperties() {

        return elementTypeListProperties;
    }

    // /**
    // * @return the database
    // */
    // public String getDatabase() {
    //
    // return database;
    // }


    /**
     * @return the table
     */
    public String getTable() {

        return table;
    }

    /**
     * @param table
     *            the table to set
     */
    public void setTable(String table) {

        this.table = table;
    }


    /**
     * Returns the constructor parameters.
     * 
     * @return the constructor parameters.
     */
    public Set<String> getConstructorParameters() {

        return constructorParameters;
    }

    /**
     * @return the primaryKey
     */
    public Set<PropertyInfo> getPrimaryKeys() {

        return primaryKeys;
    }

    /**
     * Returns the primary key set.
     * 
     * @return the primary key set.
     */
    public Set<String> getPrimaryKeySet() {

        Set<String> primaryKeySet = new LinkedHashSet<String>();

        for (PropertyInfo pkInfo : this.getPrimaryKeys()) {

            primaryKeySet.addAll(pkInfo.getColumnAliasMap().values());
        }

        return primaryKeySet;
    }

    /**
     * Returns the primary key map.
     * 
     * @return the primary key map.
     */
    public Map<String, ?> getPrimaryKeyMap() {

        Map<String, Object> propertyMap = new LinkedHashMap<String, Object>();

        for (PropertyInfo propertyInfo : this.primaryKeys) {

            for (String propertyColumnName : propertyInfo.getColumnAliasMap()
                    .values()) {

                propertyMap.put(propertyColumnName, null);
            }
        }

        return propertyMap;
    }

    /**
     * Returns whether or not the described element is a primitive.
     * 
     * @return {@code true}, if the described element is a primitive;
     *         {@code false} otherwise.
     */
    public boolean isPrimitive() {

        return !this.isElementType() && !this.isValueType();
    }

    /**
     * Returns whether or not the described element is a value type.
     * 
     * @return {@code true}, if the described element is a value type;
     *         {@code false} otherwise.
     */
    public boolean isValueType() {

        return this.getType().isAnnotationPresent(DBValueType.class);
    }

    /**
     * Returns whether or not the described element is an element type.
     * 
     * @return {@code true}, if the described element is an element type;
     *         {@code false} otherwise.
     */
    public boolean isElementType() {

        return this.getType().isAnnotationPresent(DBElementType.class);
    }


    /**
     * Creates a new SerializationInfo object for the provided type.
     * 
     * @param type
     *            the type.
     */
    public DBSerializationInfo(Class<?> type) {

        this.type = type;

        this.constructorParameters = new LinkedHashSet<String>();
        this.primaryKeys = new LinkedHashSet<PropertyInfo>();

        this.valueTypeProperties = new LinkedHashSet<PropertyInfo>();
        this.elementTypeProperties = new LinkedHashSet<PropertyInfo>();
        this.valueTypeListProperties = new LinkedHashSet<ListPropertyInfo>();
        this.elementTypeListProperties = new LinkedHashSet<ListPropertyInfo>();
    }


    /**
     * Checks whether the serialization information contains property
     * information for the field with the provided name.
     *
     * @param fieldName
     *            the name of the field to look up.
     * @return <CODE>true</CODE> if the serialization information contains
     *         property information for the field; <CODE>false</CODE> otherwise.
     */
    public boolean containsPropertyInfoForField(String fieldName) {

        for (PropertyInfo primaryKey : this.primaryKeys) {
            if (primaryKey.getPropertyName().equals(fieldName)) {
                return true;
            }
        }

        for (PropertyInfo info : this.valueTypeProperties) {
            if (info.getPropertyName().equals(fieldName)) {
                return true;
            }
        }

        for (PropertyInfo info : this.elementTypeProperties) {
            if (info.getPropertyName().equals(fieldName)) {
                return true;
            }
        }


        return false;
    }


    /**
     * Retrieves the property information for the field with the provided name.
     *
     * @param fieldName
     *            the name of the field to look up.
     * @return the property information if the serialization information
     *         contains property information for the field; <CODE>null</CODE>
     *         otherwise.
     */
    public PropertyInfo getPropertyInfoForField(String fieldName) {


        for (PropertyInfo primaryKey : this.primaryKeys) {
            if (primaryKey.getPropertyName().equals(fieldName)) {
                return primaryKey;
            }
        }

        for (PropertyInfo info : this.valueTypeProperties) {
            if (info.getPropertyName().equals(fieldName)) {
                return info;
            }
        }

        for (PropertyInfo info : this.elementTypeProperties) {
            if (info.getPropertyName().equals(fieldName)) {
                return info;
            }
        }


        return null;
    }


    /**
     * Checks whether the serialization information contains list property
     * information for the field with the provided name.
     *
     * @param fieldName
     *            the name of the field to look up.
     * @return <CODE>true</CODE> if the serialization information contains list
     *         property information for the field; <CODE>false</CODE> otherwise.
     */
    public boolean containsListPropertyInfoForField(String fieldName) {


        for (ListPropertyInfo info : this.valueTypeListProperties) {
            if (info.getPropertyName().equals(fieldName)) {
                return true;
            }
        }
        for (ListPropertyInfo info : this.elementTypeListProperties) {
            if (info.getPropertyName().equals(fieldName)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Retrieves the list property information for the field with the provided
     * name.
     *
     * @param fieldName
     *            the name of the field to look up.
     * @return the property information if the serialization information
     *         contains list property information for the field;
     *         <CODE>null</CODE> otherwise.
     */
    public ListPropertyInfo getListPropertyInfoForField(String fieldName) {


        for (ListPropertyInfo info : this.valueTypeListProperties) {
            if (info.getPropertyName().equals(fieldName)) {
                return info;
            }
        }
        for (ListPropertyInfo info : this.elementTypeListProperties) {
            if (info.getPropertyName().equals(fieldName)) {
                return info;
            }
        }

        return null;
    }

    /**
     * Checks whether the serialization information contains property
     * information for the column with the provided name.
     *
     * @param columnName
     *            the name of the column to look up.
     * @return <CODE>true</CODE> if the serialization information contains
     *         property information for the column; <CODE>false</CODE>
     *         otherwise.
     */
    public boolean containsPropertyInfoForColumn(String columnName) {

        for (PropertyInfo primaryKey : this.primaryKeys) {
            if (primaryKey.getColumnAliasMap().containsValue(columnName)) {
                return true;
            }
        }

        for (PropertyInfo info : this.valueTypeProperties) {
            if (info.getColumnAliasMap().containsValue(columnName)) {
                return true;
            }
        }

        for (PropertyInfo info : this.elementTypeProperties) {
            if (info.getColumnAliasMap().containsValue(columnName)) {
                return true;
            }
        }


        return false;
    }


    /**
     * Retrieves the property information for the column with the provided name.
     *
     * @param columnName
     *            the name of the column to look up.
     * @return the property information if the serialization information
     *         contains property information for the column; <CODE>null</CODE>
     *         otherwise.
     */
    public PropertyInfo getPropertyInfoForColumn(String columnName) {


        for (PropertyInfo primaryKey : this.primaryKeys) {
            if (primaryKey.getColumnAliasMap().containsValue(columnName)) {
                return primaryKey;
            }
        }

        for (PropertyInfo info : this.valueTypeProperties) {
            if (info.getColumnAliasMap().containsValue(columnName)) {
                return info;
            }
        }

        for (PropertyInfo info : this.elementTypeProperties) {
            if (info.getColumnAliasMap().containsValue(columnName)) {
                return info;
            }
        }


        return null;
    }



    /**
     * @param info
     */
    public void addSerializationInfo(DBSerializationInfo info) {

        this.table = info.getTable();

        this.primaryKeys.addAll(info.getPrimaryKeys());

        this.valueTypeProperties.addAll(info.getValueTypeProperties());
        this.elementTypeProperties.addAll(info.getElementTypeProperties());

        this.valueTypeListProperties.addAll(info.getValueTypeListProperties());
        this.elementTypeListProperties.addAll(info
                .getElementTypeListProperties());
    }


    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();

        string.append("  Table: " + this.table + "\n");

        if (!primaryKeys.isEmpty()) {
            string.append("  Primary Keys: \n");
            for (PropertyInfo primaryKey : this.primaryKeys) {
                string.append("    " + primaryKey + "\n");
            }
        }


        string.append("\n  SimpleProperties:\n");

        for (PropertyInfo info : this.valueTypeProperties) {
            string.append("    " + info.toString() + "\n");
        }

        string.append("\n  ComplexProperties:\n");

        for (PropertyInfo info : this.elementTypeProperties) {
            string.append("    " + info.toString() + "\n");
        }

        string.append("\n  SimpleListProperties:\n");

        for (ListPropertyInfo info : this.valueTypeListProperties) {
            string.append("    " + info.toString() + "\n");
        }
        string.append("\n  ComplexListProperties:\n");

        for (ListPropertyInfo info : this.elementTypeListProperties) {
            string.append("    " + info.toString() + "\n");
        }
        return string.toString();
    }
}
