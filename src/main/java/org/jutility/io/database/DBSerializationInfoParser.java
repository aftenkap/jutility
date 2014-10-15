/**
 *
 */
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


import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jutility.common.reflection.ReflectionException;
import org.jutility.common.reflection.ReflectionUtils;
import org.jutility.io.database.annotation.DBElementType;
import org.jutility.io.database.annotation.DBEntry;
import org.jutility.io.database.annotation.DBEntryList;
import org.jutility.io.database.annotation.DBValueType;



/**
 * @author Peter J. Radics
 * @version 0.1
 *
 */
public class DBSerializationInfoParser {


    private final Map<Class<?>, DBSerializationInfo> serializationInformation;

    /**
     * Returns all {@link DBSerializationInfo SerializationInformation}.
     *
     * @return all {@link DBSerializationInfo SerializationInformation}.
     */
    public Map<Class<?>, DBSerializationInfo> getSerializationInformation() {


        return this.serializationInformation;
    }

    /**
     * Returns the {@link DBSerializationInfo SerializationInformation} for the
     * provided class.
     *
     * @param clazz
     *            the class.
     *
     * @return the {@link DBSerializationInfo SerializationInformation}.
     * @throws DBSerializationException
     *             if parsing the class fails.
     */
    public DBSerializationInfo getSerializationInformation(Class<?> clazz)
            throws DBSerializationException {

        if (!this.getSerializationInformation().containsKey(clazz)) {

            // System.out.println("Parsing " + clazz);
            this.parse(clazz);
        }

        return this.getSerializationInformation().get(clazz);
    }

    /**
     * Creates and instantiates a new DatabaseSerializationInfoParserer object.
     *
     * @throws DBSerializationException
     *
     */
    public DBSerializationInfoParser()
            throws DBSerializationException {

        this(null);
    }

    /**
     * Creates and instantiates a new DatabaseSerializationInfoParserer object.
     *
     * @param theClass
     * @throws DBSerializationException
     */
    public DBSerializationInfoParser(Class<?> theClass)
            throws DBSerializationException {

        this.serializationInformation = new LinkedHashMap<Class<?>, DBSerializationInfo>();
        if (theClass != null) {

            this.parse(theClass);
        }
    }

    /**
     * This method creates the class hierarchy of the class to be parsed and
     * either retrieves or parses the required serialization information of the
     * class' ancestors before parsing the class itself.
     *
     * @param theClass
     *            the class to be parsed.
     * @throws DBSerializationException
     */
    public void parse(Class<?> theClass)
            throws DBSerializationException {


        // Check to see whether we need to parse the class.
        if (!this.serializationInformation.containsKey(theClass)) {

            Deque<Class<?>> classHierarchy = new LinkedList<Class<?>>();

            Class<?> current = theClass;

            DBSerializationInfo serializationInfo = new DBSerializationInfo(
                    theClass);
            this.serializationInformation.put(current, serializationInfo);


            // Establishing the class hierarchy for this class (all super
            // classes in reverse order, starting with java.lang.Object and
            // ending with the class to be parsed itself).
            while (current != null) {
                classHierarchy.addFirst(current);
                current = current.getSuperclass();
            }
            // System.out.println("Class hierarchy: " + classHierarchy);


            // parsing the classes in the class hierarchy
            for (Class<?> clazz : classHierarchy) {

                // parsing the class itself (the last to be parsed)
                if (clazz.equals(theClass)) {
                    this.parseClass(clazz, serializationInfo);
                }
                // ancestor class has not been previously parsed
                else if (!this.serializationInformation.containsKey(clazz)) {

                    DBSerializationInfo info = new DBSerializationInfo(clazz);
                    this.serializationInformation.put(clazz, info);

                    this.parseClass(clazz, info);
                    // merging information into the class' information
                    serializationInfo.addSerializationInfo(info);

                }
                // ancestor class has been previously parsed
                else {

                    // merging information into the class' information
                    serializationInfo
                            .addSerializationInfo(this.serializationInformation
                                    .get(clazz));
                }

            }
        }
    }


    /**
     * This method performs the actual parsing of the information required to
     * serialize a class by first parsing all fields, then all methods of the
     * class.
     *
     * @param clazz
     *            the class to be parsed
     * @param serializationInfo
     *            the information required for serializing the class
     * @throws DBSerializationException
     */
    private void parseClass(Class<?> clazz,
            DBSerializationInfo serializationInfo)
            throws DBSerializationException {

        System.out.println("\n\nCurrent Class: " + clazz);

        if (clazz.getName().equals(
                "org.jutility.serialization.database.BaseProperty")) {
            throw new NullPointerException();
        }

        // Class is an element type:
        // Retrieving database schema and table for this class.
        if (serializationInfo.isElementType()) {
            DBElementType typeAnnotation = clazz
                    .getAnnotation(DBElementType.class);


            // System.out.println("  Database Table: " +
            // typeAnnotation.table());
            serializationInfo.setTable(typeAnnotation.table());


            for (String parameter : typeAnnotation.constructorParameters()) {

                serializationInfo.getConstructorParameters().add(parameter);

            }
            // System.out.println("ConstructorParameters: "
            // + serializationInfo.getConstructorParameters());
        }
        // Class is a value type:
        // Retrieving database schema and table for this class.
        else if (serializationInfo.isValueType()) {

            // System.out.println("  VALUE TYPE ");

            DBValueType typeAnnotation = clazz.getAnnotation(DBValueType.class);

            for (String parameter : typeAnnotation.constructorParameters()) {

                serializationInfo.getConstructorParameters().add(parameter);
            }

        }


        // Retrieving all fields defined in this class.
        // This does not include any inherited fields.
        // Fields are ideally only used as fall-back.
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        if (fields.size() == 0) {
            // System.out.println("  Class " + clazz.getName()
            // + " doesn't have fields");
        }
        else {
            // System.out.println("  Fields:");
            // Parsing fields.
            this.parseFields(fields, serializationInfo);
        }


        // Retrieving all methods defined in this class.
        // This does not include any inherited methods.
        // Methods (getters/setters) are preferred to field access.
        List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());

        if (methods.size() == 0) {
            // System.out.println("  Class " + clazz.getName()
            // + " doesn't have methods");
        }
        else {
            // System.out.println("  Methods:");
            // Parsing methods.
            this.parseMethods2(methods, serializationInfo);
        }
    }


    /**
     *
     * @param fields
     * @param serializationInfo
     * @throws DBSerializationException
     */
    private void parseFields(List<Field> fields,
            DBSerializationInfo serializationInfo)
            throws DBSerializationException {

        // this.parseDBEntriesForFields(fields, serializationInfo);
        // this.parseDBListEntriesForFields(fields, serializationInfo);
        this.parseDBEntries(fields, serializationInfo);
        this.parseDBListEntries(fields, serializationInfo);
    }

    /**
     *
     * @param fields
     * @param serializationInfo
     * @throws DBSerializationException
     */
    // private void parseDBEntriesForFields(List<Field> fields,
    // DBSerializationInfo serializationInfo)
    // throws DBSerializationException {
    //
    // for (Field field : fields) {
    //
    // // System.out.print("    " + field.getName() + ":");
    //
    //
    // // The field is a single entry.
    // // That means, it is either part of the primary key, a foreign key
    // // of an element type, or a value of a value type).
    // if (field.isAnnotationPresent(DBEntry.class)) {
    //
    // if (serializationInfo.isPrimitive()) {
    //
    // throw new DBSerializationException("Class "
    // + serializationInfo.getClass()
    // + " does not have a DBElementType or DBValueType "
    // + "annotation, but contains a DBEntry!");
    // }
    // DBEntry elementAnnotation = field.getAnnotation(DBEntry.class);
    //
    //
    // String fieldName = field.getName();
    // String[] columnMappings = elementAnnotation.columnMappings();
    //
    //
    // boolean primaryKey = elementAnnotation.primaryKey();
    // boolean notNull = elementAnnotation.notNull();
    //
    //
    // Map<String, String> columnMap = new LinkedHashMap<>();
    //
    // columnMap = this.parseMappings(columnMappings);
    // if (columnMap.isEmpty()) {
    //
    //
    // String columnName = elementAnnotation.column();
    // if (columnName == null || "".equals(columnName)) {
    //
    // System.err.println("No map and column name");
    // columnMap.put(fieldName, fieldName);
    // }
    // else {
    // columnMap.put(columnName, columnName);
    // }
    // }
    // if (columnMap.isEmpty()) {
    //
    // throw new DBSerializationException(
    // "No column provided for field " + fieldName);
    // }
    //
    // PropertyInfo entry = new PropertyInfo(fieldName,
    // field.getClass(), columnMap, field, notNull);
    //
    // try {
    //
    // entry.setGetter(ReflectionUtils.getAccessor(
    // serializationInfo.getType(), fieldName));
    // if (entry.getGetter() != null) {
    // System.out.println("Set getter to "
    // + entry.getGetter().getName());
    // }
    // }
    // catch (ReflectionException e) {
    // throw new DBSerializationException(
    // "Exception occurred while trying to retrieve accessor for "
    // + fieldName + "!", e);
    // }
    // try {
    //
    // entry.setSetter(ReflectionUtils.getMutator(
    // serializationInfo.getType(), fieldName));
    // if (entry.getSetter() != null) {
    // System.out.println("Set setter to "
    // + entry.getSetter().getName());
    // }
    // }
    // catch (ReflectionException e) {
    // throw new DBSerializationException(
    // "Exception occurred while trying to retrieve mutator for "
    // + fieldName + "!", e);
    // }
    //
    // // Primary Key
    // if (primaryKey == true) {
    //
    // serializationInfo.getPrimaryKeys().add(entry);
    //
    // }
    // // ElementType property
    // else if (this.isElementType(field.getType())) {
    // if (serializationInfo.isValueType()) {
    //
    // throw new DBSerializationException("Class "
    // + serializationInfo.getClass()
    // + " is a DBValueType, but contains a "
    // + "DBElementType entry!");
    // }
    //
    // serializationInfo.getElementTypeProperties().add(entry);
    // }
    // else if (this.isValueType(field.getType())
    // && serializationInfo.isValueType()) {
    // throw new DBSerializationException("Class "
    // + serializationInfo.getClass()
    // + " is a DBValueType, but contains another "
    // + "DBValueType entry!");
    // }
    // // ValueType property or Primitive property
    // else {
    //
    // serializationInfo.getValueTypeProperties().add(entry);
    // }
    //
    // if (this.isElementType(field.getType())
    // || this.isValueType(field.getType())) {
    // this.parse(field.getType());
    // }
    //
    // // System.out.println(" column: " + columnMap +
    // // ", isPrimaryKey: "
    // // + primaryKey);
    //
    //
    // }
    // else {
    // // System.out.println(" is not a DBEntry.");
    // }
    // }
    //
    // }

    /**
     *
     * @param fields
     * @param serializationInfo
     * @throws DBSerializationException
     */
    // private void parseDBListEntriesForFields(List<Field> fields,
    // DBSerializationInfo serializationInfo)
    // throws DBSerializationException {
    //
    // for (Field field : fields) {
    //
    // // System.out.print("    " + field.getName() + ":");
    // if (field.isAnnotationPresent(DBEntryList.class)) {
    // if (serializationInfo.isPrimitive()) {
    //
    // throw new DBSerializationException("Class "
    // + serializationInfo.getClass()
    // + " does not have a DBElementType or DBValueType "
    // + "annotation, but contains a DBListEntry!");
    // }
    // DBEntryList elementListAnnotation = field
    // .getAnnotation(DBEntryList.class);
    //
    // String fieldName = field.getName();
    // Map<String, String> keyMappings = this
    // .parseMappings(elementListAnnotation
    // .containerClassAliasMap());
    //
    //
    // Map<String, String> valueMappings = this
    // .parseMappings(elementListAnnotation.listTypeAliasMap());
    //
    //
    // String tableName = elementListAnnotation.table();
    // String database = elementListAnnotation.database();
    //
    //
    // Type genericType = field.getGenericType();
    // ParameterizedType stringListType = (ParameterizedType) genericType;
    // Class<?> listType = (Class<?>) stringListType
    // .getActualTypeArguments()[0];
    //
    // ListPropertyInfo entry = new ListPropertyInfo(fieldName,
    // listType, database, tableName, keyMappings,
    // valueMappings, field);
    //
    // if (this.isElementType(listType)) {
    //
    // System.out.println("Adding " + entry.getGetter().getName()
    // + " as element type list.");
    // serializationInfo.getElementTypeListProperties().add(entry);
    // }
    // else {
    //
    // System.out.println("Adding " + entry.getGetter().getName()
    // + " as value type list.");
    // serializationInfo.getValueTypeListProperties().add(entry);
    // }
    // if (this.isElementType(listType) || this.isValueType(listType)) {
    //
    // System.out.println("Parsing list type");
    // this.parse(listType);
    // }
    //
    // this.parse(listType);
    //
    // // Identity mapping of container primary keys
    // if (entry.getContainerClassAliasMap().size() == 0) {
    //
    // for (PropertyInfo valuePrimaryKey : serializationInfo
    // .getPrimaryKeys()) {
    //
    // for (String primaryKeyColumnName : valuePrimaryKey
    // .getOwnColumnNames()) {
    //
    // entry.getContainerClassAliasMap().put(
    // primaryKeyColumnName, primaryKeyColumnName);
    // }
    // }
    // }
    //
    //
    // // Identity mapping of list element type
    // if (entry.getListTypeAliasMap().size() == 0) {
    //
    // DBSerializationInfo valueListTypeInfo = this.serializationInformation
    // .get(listType);
    //
    // if (this.isElementType(listType)) {
    //
    //
    // for (PropertyInfo valuePrimaryKey : valueListTypeInfo
    // .getPrimaryKeys()) {
    //
    // for (String primaryKeyColumnName : valuePrimaryKey
    // .getOwnColumnNames()) {
    //
    // entry.getListTypeAliasMap().put(
    // primaryKeyColumnName,
    // primaryKeyColumnName);
    // }
    // }
    // }
    // else if (this.isValueType(listType)) {
    //
    // for (PropertyInfo valueProperty : valueListTypeInfo
    // .getValueTypeProperties()) {
    //
    // for (String valueColumnName : valueProperty
    // .getOwnColumnNames()) {
    //
    // entry.getListTypeAliasMap().put(
    // valueColumnName, valueColumnName);
    // }
    // }
    //
    // for (PropertyInfo elementProperty : valueListTypeInfo
    // .getElementTypeProperties()) {
    //
    // for (String elementForeignKeyColumn : elementProperty
    // .getOwnColumnNames()) {
    //
    // entry.getListTypeAliasMap().put(
    // elementForeignKeyColumn,
    // elementForeignKeyColumn);
    // }
    // }
    // }
    //
    // else {
    // entry.getListTypeAliasMap().put(fieldName, fieldName);
    //
    // }
    //
    // }
    //
    //
    // }
    // else {
    // // System.out.println(" is not a DBEntryList.");
    // }
    // }
    //
    // }

    /**
     *
     * @param fields
     * @param serializationInfo
     * @throws DBSerializationException
     */
    private void parseDBEntries(
            List<? extends AnnotatedElement> annotatedElements,
            DBSerializationInfo serializationInfo)
            throws DBSerializationException {

        for (AnnotatedElement annotatedElement : annotatedElements) {

            if (annotatedElement instanceof Member) {
                Member member = (Member) annotatedElement;
                System.out.print("    " + member.getName() + ":");
            }

            // The field is a single entry.
            // That means, it is either part of the primary key, a foreign key
            // of an element type, or a value of a value type).
            if (annotatedElement.isAnnotationPresent(DBEntry.class)) {

                if (serializationInfo.isPrimitive()) {

                    throw new DBSerializationException("Class "
                            + serializationInfo.getClass()
                            + " does not have a DBElementType or DBValueType "
                            + "annotation, but contains a DBEntry!");
                }
                DBEntry elementAnnotation = annotatedElement
                        .getAnnotation(DBEntry.class);



                String propertyName = this.getPropertyName(annotatedElement);
                Class<?> propertyType = this.getPropertyType(annotatedElement);


                String[] columnMappings = elementAnnotation.columnMappings();


                boolean notNull = elementAnnotation.notNull();


                Map<String, String> columnMap = new LinkedHashMap<String, String>();

                columnMap = this.parseMappings(columnMappings);
                if (columnMap.isEmpty()) {


                    String columnName = elementAnnotation.column();
                    if (columnName == null || "".equals(columnName)) {

                        System.err.println("No map and column name");
                        columnMap.put(propertyName, propertyName);
                    }
                    else {

                        columnMap.put(columnName, columnName);
                    }
                }
                if (columnMap.isEmpty()) {

                    throw new DBSerializationException(
                            "No column provided for field " + propertyName);
                }

                PropertyInfo entry = new PropertyInfo(propertyName,
                        propertyType, columnMap, notNull);

                this.addPropertyAccess(entry, annotatedElement,
                        serializationInfo);


                boolean primaryKey = elementAnnotation.primaryKey();

                // Primary Key
                if (primaryKey == true) {

                    serializationInfo.getPrimaryKeys().add(entry);

                }
                // ElementType property
                else if (this.isElementType(propertyType)) {

                    if (serializationInfo.isValueType()) {

                        throw new DBSerializationException("Class "
                                + serializationInfo.getClass()
                                + " is a DBValueType, but contains a "
                                + "DBElementType entry!");
                    }

                    serializationInfo.getElementTypeProperties().add(entry);
                }
                else if (this.isValueType(propertyType)
                        && serializationInfo.isValueType()) {
                    throw new DBSerializationException("Class "
                            + serializationInfo.getClass()
                            + " is a DBValueType, but contains another "
                            + "DBValueType entry!");
                }
                // ValueType property or Primitive property
                else {

                    serializationInfo.getValueTypeProperties().add(entry);
                }

                if (this.isElementType(propertyType)
                        || this.isValueType(propertyType)) {

                    this.parse(propertyType);
                }

                System.out.println(" column: " + columnMap + ", isPrimaryKey: "
                        + primaryKey);


            }
            else {
                System.out.println(" is not a DBEntry.");
            }
        }

    }

    /**
     *
     * @param fields
     * @param serializationInfo
     * @throws DBSerializationException
     */
    private void parseDBListEntries(
            List<? extends AnnotatedElement> annotatedElements,
            DBSerializationInfo serializationInfo)
            throws DBSerializationException {

        for (AnnotatedElement annotatedElement : annotatedElements) {

            // System.out.print("    " + field.getName() + ":");
            if (annotatedElement.isAnnotationPresent(DBEntryList.class)) {

                if (serializationInfo.isPrimitive()) {

                    throw new DBSerializationException("Class "
                            + serializationInfo.getClass()
                            + " does not have a DBElementType or DBValueType "
                            + "annotation, but contains a DBListEntry!");
                }
                DBEntryList elementListAnnotation = annotatedElement
                        .getAnnotation(DBEntryList.class);

                String propertyName = this.getPropertyName(annotatedElement);

                Type genericType = this
                        .getGenericPropertyType(annotatedElement);
                ParameterizedType stringListType = (ParameterizedType) genericType;

                System.out.println("stringListType: " + stringListType);
                Type[] actualTypeArguments = stringListType
                        .getActualTypeArguments();

                System.out.println("Raw type: " + stringListType.getRawType());
                System.out.println("actualTypeArguments: "
                        + Arrays.toString(actualTypeArguments));
                Type firstType = actualTypeArguments[0];
                System.out.println("First type argument: " + firstType);
                Class<?> listPropertyType = null;
                try {
                    listPropertyType = (Class<?>) firstType;
                }
                catch (ClassCastException e) {

                    System.out.println("Was able to catch it.");
                    ParameterizedType firstParameterizedType = (ParameterizedType) firstType;
                    System.out.println("firstParameterizedType: "
                            + firstParameterizedType);
                    listPropertyType = (Class<?>) firstParameterizedType
                            .getRawType();
                    System.out.println("listPropertyType: " + listPropertyType);
                    // System.exit(-1);
                }


                System.out.println("Container type aliases: "
                        + Arrays.toString(elementListAnnotation
                                .containerClassAliasMap()));
                Map<String, String> keyMappings = this
                        .parseMappings(elementListAnnotation
                                .containerClassAliasMap());

                System.out.println("Container type alias map: " + keyMappings);

                System.out.println("List type aliases: "
                        + Arrays.toString(elementListAnnotation
                                .listTypeAliasMap()));
                Map<String, String> valueMappings = this
                        .parseMappings(elementListAnnotation.listTypeAliasMap());
                System.out
                        .println("Container type alias map: " + valueMappings);


                String tableName = elementListAnnotation.table();

                Class<?> collectionType = this
                        .getPropertyType(annotatedElement);

                ListPropertyInfo entry = new ListPropertyInfo(propertyName,
                        listPropertyType, collectionType, tableName,
                        keyMappings, valueMappings);


                this.addPropertyAccess(entry, annotatedElement,
                        serializationInfo);



                if (this.isElementType(listPropertyType)) {

                    System.out.println("Adding " + entry.getGetter().getName()
                            + " as element type list.");
                    serializationInfo.getElementTypeListProperties().add(entry);
                }
                else {

                    System.out.println("Adding " + entry.getGetter().getName()
                            + " as value type list.");
                    serializationInfo.getValueTypeListProperties().add(entry);
                }
                if (this.isElementType(listPropertyType)
                        || this.isValueType(listPropertyType)) {

                    System.out.println("Parsing list property type");
                    this.parse(listPropertyType);
                }

                // Identity mapping of container primary keys
                if (entry.getContainerClassAliasMap().size() == 0) {

                    for (PropertyInfo valuePrimaryKey : serializationInfo
                            .getPrimaryKeys()) {

                        for (String primaryKeyColumnName : valuePrimaryKey
                                .getAliasedColumnNames()) {

                            entry.getContainerClassAliasMap().put(
                                    primaryKeyColumnName, primaryKeyColumnName);
                        }
                    }
                }


                // Identity mapping of list element type
                if (entry.getListTypeAliasMap().size() == 0) {

                    DBSerializationInfo valueListTypeInfo = this.serializationInformation
                            .get(listPropertyType);

                    if (this.isElementType(listPropertyType)) {


                        for (PropertyInfo valuePrimaryKey : valueListTypeInfo
                                .getPrimaryKeys()) {

                            for (String primaryKeyColumnName : valuePrimaryKey
                                    .getAliasedColumnNames()) {

                                entry.getListTypeAliasMap().put(
                                        primaryKeyColumnName,
                                        primaryKeyColumnName);
                            }
                        }
                    }
                    else if (this.isValueType(listPropertyType)) {

                        for (PropertyInfo valueProperty : valueListTypeInfo
                                .getValueTypeProperties()) {

                            for (String valueColumnName : valueProperty
                                    .getAliasedColumnNames()) {

                                entry.getListTypeAliasMap().put(
                                        valueColumnName, valueColumnName);
                            }
                        }

                        for (PropertyInfo elementProperty : valueListTypeInfo
                                .getElementTypeProperties()) {

                            for (String elementForeignKeyColumn : elementProperty
                                    .getAliasedColumnNames()) {

                                entry.getListTypeAliasMap().put(
                                        elementForeignKeyColumn,
                                        elementForeignKeyColumn);
                            }
                        }
                    }

                    else {
                        entry.getListTypeAliasMap().put(propertyName,
                                propertyName);

                    }

                }


            }
            else {
                // System.out.println(" is not a DBEntryList.");
            }
        }

    }


    private Map<String, String> parseMappings(String[] keyValuePairs)
            throws DBSerializationException {

        Map<String, String> keyMappings = new LinkedHashMap<String, String>();

        for (String keyValuePair : keyValuePairs) {

            String parseString = keyValuePair.trim();
            if (!keyValuePair.isEmpty()) {

                String[] parameters = parseString.split("=");

                if (parameters.length != 2) {

                    throw new DBSerializationException(
                            "Could not parse key mapping string \""
                                    + keyValuePair + "\"!");
                }

                keyMappings.put(parameters[0], parameters[1]);
            }
        }

        return keyMappings;
    }

    private void parseMethods2(List<Method> methods,
            DBSerializationInfo serializationInfo)
            throws DBSerializationException {

        this.parseDBEntries(methods, serializationInfo);
        this.parseDBListEntries(methods, serializationInfo);
    }

    // private void parseMethods(List<Method> methods,
    // DBSerializationInfo serializationInfo) {
    //
    // for (Method method : methods) {
    //
    // String methodName = method.getName();
    //
    //
    // String fieldName = null;
    //
    // if ((methodName.startsWith("get") || methodName.startsWith("set"))
    // && methodName.length() > 3) {
    //
    //
    // fieldName = methodName.substring(3);
    //
    // fieldName = Character.toLowerCase(fieldName.charAt(0))
    // + fieldName.substring(1);
    // }
    // else if (methodName.startsWith("is")) {
    //
    // fieldName = methodName.substring(2);
    //
    // fieldName = Character.toLowerCase(fieldName.charAt(0))
    // + fieldName.substring(1);
    // }
    //
    // if (fieldName != null) {
    //
    // // System.out.print("    " + methodName + ", " + fieldName +
    // // ":");
    //
    // boolean isGetter = false;
    //
    // if (methodName.startsWith("get") || methodName.startsWith("is")) {
    // isGetter = true;
    // }
    //
    // // System.out.print(" isGetter: " + isGetter);
    //
    //
    // if (serializationInfo.containsPropertyInfoForField(fieldName)) {
    // PropertyInfo propertyInfo = serializationInfo
    // .getPropertyInfoForField(fieldName);
    //
    //
    // // System.out.println(" - has property information.");
    // if (isGetter) {
    // propertyInfo.setGetter(method);
    // }
    // else {
    // propertyInfo.setSetter(method);
    // }
    //
    // }
    // else if (serializationInfo
    // .containsListPropertyInfoForField(fieldName)) {
    // ListPropertyInfo listPropertyInfo = serializationInfo
    // .getListPropertyInfoForField(fieldName);
    //
    //
    // // System.out.println(" - has list property information.");
    // if (isGetter) {
    // listPropertyInfo.setGetter(method);
    // }
    // else {
    // listPropertyInfo.setSetter(method);
    // }
    // }
    // else {
    // System.out
    // .println(" - Doesn't have property information for "
    // + fieldName + ".");
    // }
    //
    // }
    // }
    // }

    private boolean isValueType(Class<?> clazz) {

        return clazz.isAnnotationPresent(DBValueType.class);
    }

    private boolean isElementType(Class<?> clazz) {

        return clazz.isAnnotationPresent(DBElementType.class);
    }

    private String getPropertyName(AnnotatedElement annotatedElement)
            throws DBSerializationException {

        String propertyName = null;

        if (annotatedElement instanceof Field) {

            propertyName = ((Field) annotatedElement).getName();
        }
        else if (annotatedElement instanceof Method) {

            Method method = (Method) annotatedElement;

            String methodName = method.getName();

            if ((methodName.startsWith("get") || methodName.startsWith("set"))
                    && methodName.length() > 3) {


                propertyName = methodName.substring(3);

                propertyName = Character.toLowerCase(propertyName.charAt(0))
                        + propertyName.substring(1);
            }
            else if (methodName.startsWith("is") && methodName.length() > 2) {

                propertyName = methodName.substring(2);

                propertyName = Character.toLowerCase(propertyName.charAt(0))
                        + propertyName.substring(1);
            }
            else {

                throw new DBSerializationException("Method " + methodName
                        + " does not follow the Java Bean naming"
                        + " convention!");
            }
        }
        return propertyName;
    }


    private Class<?> getPropertyType(AnnotatedElement annotatedElement)
            throws DBSerializationException {

        Class<?> propertyType = null;

        if (annotatedElement instanceof Field) {

            propertyType = ((Field) annotatedElement).getType();
        }
        else if (annotatedElement instanceof Method) {

            Method method = (Method) annotatedElement;

            Class<?> returnType = method.getReturnType();
            Class<?>[] parameterTypes = method.getParameterTypes();

            if (this.isAccessor(method)) {

                propertyType = returnType;
            }
            else if (this.isMutator(method)) {

                propertyType = parameterTypes[0];
            }
            else {

                throw new DBSerializationException("Method " + method.getName()
                        + " is neither a getter nor a setter, yet is"
                        + " annotated!");
            }
        }
        return propertyType;
    }

    private Type getGenericPropertyType(AnnotatedElement annotatedElement)
            throws DBSerializationException {

        Type genericPropertyType = null;

        if (annotatedElement instanceof Field) {

            genericPropertyType = ((Field) annotatedElement).getGenericType();
        }
        else if (annotatedElement instanceof Method) {

            Method method = (Method) annotatedElement;


            if (this.isAccessor(method)) {

                Type returnType = method.getGenericReturnType();
                genericPropertyType = returnType;
            }
            else if (this.isMutator(method)) {

                Type[] parameterTypes = method.getGenericParameterTypes();
                genericPropertyType = parameterTypes[0];
            }
            else {

                throw new DBSerializationException("Method " + method.getName()
                        + " is neither a getter nor a setter, yet is"
                        + " annotated!");
            }
        }
        return genericPropertyType;
    }

    private boolean isAccessor(Method method) {

        String methodName = method.getName();
        Class<?> returnType = method.getReturnType();
        Class<?> parameterTypes[] = method.getParameterTypes();

        if (returnType != null
                && returnType != void.class
                && parameterTypes.length == 0
                && (methodName.startsWith("get") || methodName.startsWith("is"))) {

            return true;
        }
        return false;
    }

    private boolean isMutator(Method method) {

        String methodName = method.getName();
        Class<?> returnType = method.getReturnType();
        Class<?> parameterTypes[] = method.getParameterTypes();
        if (returnType == void.class && parameterTypes.length == 1
                && methodName.startsWith("set")) {

            return true;
        }

        return false;
    }

    private void addPropertyAccess(IProperty property,
            AnnotatedElement annotatedElement,
            DBSerializationInfo serializationInfo)
            throws DBSerializationException {

        System.out.println("\n**** Adding Property Access for: "
                + property.getPropertyName() + "****");

        try {
            System.out.println("serializationInfo.getType: "
                    + serializationInfo.getType());
            property.setField(ReflectionUtils.getField(
                    serializationInfo.getType(), property.getPropertyName()));
            System.out.println("Set field to " + property.getField().getName());
        }
        catch (ReflectionException e) {

            System.out.println("No field for property "
                    + property.getPropertyName());
            //
            // throw new DBSerializationException(
            // "Exception occurred while trying to retrieve field for "
            // + property.getPropertyName() + "!", e);
        }


        try {

            property.setGetter(ReflectionUtils.getAccessor(
                    serializationInfo.getType(), property.getPropertyName()));

            if (property.getGetter() != null) {

                System.out.println("Set getter to "
                        + property.getGetter().getName());
            }
        }
        catch (ReflectionException e) {


            System.out.println("No getter for property "
                    + property.getPropertyName());
            // throw new DBSerializationException(
            // "Exception occurred while trying to retrieve accessor for "
            // + property.getPropertyName() + "!", e);
        }
        try {

            property.setSetter(ReflectionUtils.getMutator(
                    serializationInfo.getType(), property.getPropertyName()));

            if (property.getSetter() != null) {

                System.out.println("Set setter to "
                        + property.getSetter().getName());
            }
        }
        catch (ReflectionException e) {


            System.out.println("No setter for property "
                    + property.getPropertyName());
            // throw new DBSerializationException(
            // "Exception occurred while trying to retrieve mutator for "
            // + property.getPropertyName() + "!", e);
        }
    }
}
