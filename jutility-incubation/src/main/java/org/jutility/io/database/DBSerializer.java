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
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jutility.common.reflection.ReflectionException;
import org.jutility.common.reflection.ReflectionUtils;



/**
 * @author Peter J. Radics
 * @version 0.1
 *
 */
public class DBSerializer {

    private final DBSerializationDriver     databaseDriver;
    private final DBSerializationInfoParser parser;


    /**
     * Sets the database server URI.
     *
     * @param databaseServer
     *            the database server URI.
     */
    public void setDatabaseServer(URI databaseServer) {

        this.databaseDriver.setDatabaseServer(databaseServer);
    }

    /**
     * Sets the database schema.
     *
     * @param schemaName
     *            the database schema.
     */
    public void setSchema(String schemaName) {

        this.databaseDriver.setSchema(schemaName);
    }

    /**
     * Sets the database username.
     *
     * @param username
     *            the database username.
     */
    public void setUsername(String username) {

        this.databaseDriver.setUsername(username);
    }


    /**
     * Sets the database password.
     *
     * @param password
     *            the database password.
     */
    public void setPassword(String password) {

        this.databaseDriver.setPassword(password);
    }


    /**
     * Creates and instantiates a new DatabaseSerializer object.
     *
     * @throws DBSerializationException
     *             if the database driver could not be initialized.
     *
     */
    public DBSerializer()
            throws DBSerializationException {

        this(null);
    }

    /**
     * Creates and instantiates a new DatabaseSerializer object.
     *
     * @param theClass
     *            the class type of the class to serialize.
     * @throws DBSerializationException
     *             if the database driver could not be initialized or if parsing
     *             of the provided class fails.
     */
    public DBSerializer(Class<?> theClass)
            throws DBSerializationException {

        this(theClass, "raziel.cs.vt.edu", 3306);
    }


    /**
     * Creates and instantiates a new DatabaseSerializer object.
     *
     * @param theClass
     *            the class type of the class to serialize.
     * @param hostname
     *            the hostname of the database server.
     * @param port
     *            the port of the database management system.
     * @throws DBSerializationException
     *             if the database driver could not be initialized or if parsing
     *             of the provided class fails.
     */
    public DBSerializer(Class<?> theClass, String hostname, Integer port)
            throws DBSerializationException {


        this.databaseDriver = new DBSerializationDriver();

        try {

            URI databaseServer = new URI(hostname + ":" + port);
            this.databaseDriver.setDatabaseServer(databaseServer);
            this.databaseDriver.setSchema("privacyworkbench2");
        }
        catch (URISyntaxException e) {

            throw new DBSerializationException(
                    "Provided hosname and port do not form a valid URI!", e);
        }


        this.parser = new DBSerializationInfoParser(theClass);
    }

    /**
     * Creates and instantiates a new DatabaseSerializer object.
     *
     * @param theClass
     *            the class type of the class to serialize.
     * @param databaseServer
     *            the database server.
     *
     * @throws DBSerializationException
     *             if the database driver could not be initialized or if parsing
     *             of the provided class fails.
     */
    public DBSerializer(Class<?> theClass, URI databaseServer)
            throws DBSerializationException {


        this.databaseDriver = new DBSerializationDriver();
        this.databaseDriver.setDatabaseServer(databaseServer);
        this.databaseDriver.setSchema("privacyworkbench2");

        this.parser = new DBSerializationInfoParser(theClass);
    }

    /**
     * Wrapper for the serialization of a single object. Calls the serialization
     * method for a collection of objects.
     *
     * @see org.jutility.io.database.DBSerializer#serialize(Collection)
     *
     * @param object
     *            the object to serialize.
     * @throws DBSerializationException
     *             if serialization fails.
     */
    public void serialize(Object object)
            throws DBSerializationException {

        List<Object> list = new LinkedList<Object>();
        list.add(object);
        this.serialize(list);
    }


    /**
     * Performs serialization for a collection of objects. If the serialization
     * information of any of the classes the objects are instances of are not
     * available, they are first parsed.
     *
     * @param objects
     *            the objects to serialize.
     * @throws DBSerializationException
     *             if serialization fails.
     */
    public void serialize(Collection<?> objects)
            throws DBSerializationException {

        this.serializeObjects(objects);
    }


    /**
     * Performs the serialization for a collection of objects.
     *
     * @param objects
     *            the objects to serialize.
     * @throws DBSerializationException
     */
    private void serializeObjects(Collection<?> objects)
            throws DBSerializationException {

        Set<Object> objectsToSerialize = new LinkedHashSet<Object>();
        for (Object object : objects) {

            this.crawlObjects(object, objectsToSerialize);
        }

        this.databaseDriver.connectToDatabase();


        // System.out.println("Objects to serialize: ");
        System.out.println("******** Simple Serialization ************");
        for (Object obj : objectsToSerialize) {
            // System.out.println("serializing: " + obj.toString());
            this.performBasicSerialization(obj);
            this.performListSerialization(obj, true);
        }
        System.out.println("******** Complex Serialization ************");
        for (Object obj : objectsToSerialize) {
            // System.out.println("serializing: " + obj.toString());
            this.performComplexSerialization(obj);
            this.performListSerialization(obj, false);
        }


        this.databaseDriver.closeDatabaseConnection();
    }


    private void crawlObjects(Object object, Set<Object> objectsToSerialize)
            throws DBSerializationException {

        if (!objectsToSerialize.contains(object)) {

            objectsToSerialize.add(object);

            // System.out.println("Crawling " + object.toString() + " ("
            // + object.getClass() + ")");
            DBSerializationInfo info = this.parser
                    .getSerializationInformation(object.getClass());

            // Retrieving all the objects of the ElementType properties for
            // serialization
            for (PropertyInfo propertyInfo : info.getElementTypeProperties()) {

                this.crawlObjects(this.getValue(object, propertyInfo),
                        objectsToSerialize);
            }

            // Retrieving all the objects within the ElementType list properties
            for (ListPropertyInfo propertyInfo : info
                    .getElementTypeListProperties()) {
                // System.out.println("Element type list property "
                // + propertyInfo.accesssSignature());
                for (Object newObject : this.getValueCollection(object,
                        propertyInfo)) {
                    // if (newObject.toString().endsWith("DS")) {
                    // System.out.println("Dataset: " + newObject);
                    // }
                    this.crawlObjects(newObject, objectsToSerialize);
                }
            }

        }
    }

    private void performBasicSerialization(Object object)
            throws DBSerializationException {

        System.out.println("Serializing object " + object);
        DBSerializationInfo info = this.parser
                .getSerializationInformation(object.getClass());

        Map<String, Object> primaryKeys = this.getPropertyValueMap(object,
                info.getPrimaryKeys());

        this.databaseDriver.prepareDuplicateCheck(info.getTable(),
                info.getPrimaryKeySet());
        boolean exists = this.databaseDriver.executeDuplicateCheck(primaryKeys);
        this.databaseDriver.closePreparedQuery();

        Map<String, Object> valueMap = this.getPropertyValueMap(object,
                info.getValueTypeProperties());


        if (exists) {

            System.out.println("Exists");
            valueMap.putAll(primaryKeys);

            this.databaseDriver.prepareUpdate(info.getTable(),
                    info.getPrimaryKeySet(), info.getValueTypePropertyKeys());

            this.databaseDriver.executeUpdate(valueMap);
        }
        else {

            System.out.println("Does not exist.");

            Set<String> basicSerializationKeys = info.getPrimaryKeySet();

            basicSerializationKeys.addAll(info.getValueTypePropertyKeys());

            this.databaseDriver.prepareInsertion(info.getTable(),
                    basicSerializationKeys);

            int id = this.databaseDriver.executeInsertion(valueMap);

            for (PropertyInfo primaryKey : info.getPrimaryKeys()) {

                this.setValue(object, primaryKey, id);
            }
            System.out.println("Object with new id: " + object.toString());
        }


        this.databaseDriver.closePreparedStatement();
    }

    private void performListSerialization(Object object, boolean simple)
            throws DBSerializationException {


        DBSerializationInfo info = this.parser
                .getSerializationInformation(object.getClass());

        this.databaseDriver.prepareDuplicateCheck(info.getTable(),
                info.getPrimaryKeySet());

        if (!this.databaseDriver.executeDuplicateCheck(this
                .getPropertyValueMap(object, info.getPrimaryKeys()))) {

            this.databaseDriver.closePreparedQuery();
            throw new DBSerializationException(
                    "Cannot perform list serialization without first "
                            + "performing simple serialization! (I.e., "
                            + "the object has to be added to the database "
                            + "before it can be used in a foreign key "
                            + "relationship.)");
        }

        this.databaseDriver.closePreparedQuery();


        Set<ListPropertyInfo> propertyList;

        if (simple) {
            // TODO: modify so that this handles value type property lists
            // correctly
            System.out.println("Simple");
            System.out.println("  " + info.getValueTypeListProperties());
            propertyList = info.getValueTypeListProperties();
        }
        else {

            System.out.println("element");
            System.out.println("  " + info.getElementTypeListProperties());
            propertyList = info.getElementTypeListProperties();
        }

        if (propertyList.size() != 0) {

            for (ListPropertyInfo listPropertyInfo : propertyList) {

                List<Map<String, ?>> valueMaps = this.getListPropertyValueMap(
                        object, listPropertyInfo);

                List<Map<String, ?>> updateList = this.getUpdateList(valueMaps,
                        listPropertyInfo);


                if (!valueMaps.isEmpty()) {

                    System.out.println("Aliased Key set for list property "
                            + listPropertyInfo.getPropertyName() + ": "
                            + listPropertyInfo.getAliasedKeySet());
                    this.databaseDriver.prepareInsertion(
                            listPropertyInfo.getTableName(),
                            listPropertyInfo.getAliasedKeySet());

                    for (Map<String, ?> valueMap : valueMaps) {

                        this.databaseDriver.executeInsertion(valueMap);
                    }

                    this.databaseDriver.closePreparedStatement();
                }

                if (!updateList.isEmpty() && !listPropertyInfo.isElementType()) {

                    this.databaseDriver.prepareUpdate(
                            listPropertyInfo.getTableName(),
                            listPropertyInfo.getAliasedContainerClassKeySet(),
                            listPropertyInfo.getAliasedListTypeKeySet());

                    for (Map<String, ?> valueMap : updateList) {

                        this.databaseDriver.executeUpdate(valueMap);
                    }

                    this.databaseDriver.closePreparedStatement();
                }
            }
        }
    }

    private void performComplexSerialization(Object object)
            throws DBSerializationException {

        DBSerializationInfo info = this.parser
                .getSerializationInformation(object.getClass());

        this.databaseDriver.prepareDuplicateCheck(info.getTable(),
                info.getPrimaryKeySet());

        boolean exists = this.databaseDriver.executeDuplicateCheck(this
                .getPropertyValueMap(object, info.getPrimaryKeys()));

        this.databaseDriver.closePreparedQuery();

        if (!exists) {
            throw new DBSerializationException(
                    "Cannot perform complex serialization without first "
                            + "performing simple serialization! (I.e., "
                            + "the object has to be added to the database "
                            + "before it can be used in a foreign key "
                            + "relationship.)");
        }

        if (!info.getElementTypeProperties().isEmpty()) {


            Map<String, Object> elementPrimaryKeyMap = new LinkedHashMap<String, Object>();

            for (PropertyInfo propertyInfo : info.getElementTypeProperties()) {

                Object elementTypePropertyValue = this.getValue(object,
                        propertyInfo);
                DBSerializationInfo elementTypeInfo = this.parser
                        .getSerializationInformation(elementTypePropertyValue
                                .getClass());

                Map<String, ?> elementPrimaryKeys = this.getPropertyValueMap(
                        elementTypePropertyValue,
                        elementTypeInfo.getPrimaryKeys());

                System.out
                        .println("Element PrimaryKeys: " + elementPrimaryKeys);
                for (String key : elementPrimaryKeys.keySet()) {

                    elementPrimaryKeyMap.put(
                            propertyInfo.getAliasedColumnName(key),
                            elementPrimaryKeys.get(key));
                }
            }


            // System.out.println("Exists");
            Map<String, ?> primaryKeys = this.getPropertyValueMap(object,
                    info.getPrimaryKeys());

            elementPrimaryKeyMap.putAll(primaryKeys);
            System.out.println("ElementTypePropertyKeys: "
                    + info.getElementTypePropertyKeys());
            System.out.println("ElementPrimaryKeyMap: " + elementPrimaryKeyMap);
            this.databaseDriver.prepareUpdate(info.getTable(),
                    info.getPrimaryKeySet(), info.getElementTypePropertyKeys());


            // System.out.print("Value list after conversion: ");
            // for (Object string : valueList) {
            // System.out.print(string + ", ");
            // }
            // System.out.println();

            // int id = -1;
            this.databaseDriver.executeUpdate(elementPrimaryKeyMap);
            // id =
            // MySQLDriver.Instance().executePreparedStatement(valueList);

            this.databaseDriver.closePreparedStatement();
        }

    }


    private Map<String, Object> getPropertyValueMap(Object object,
            Set<PropertyInfo> properties)
            throws DBSerializationException {


        Map<String, Object> propertyValueMap = new LinkedHashMap<String, Object>();

        for (PropertyInfo property : properties) {

            // retrieve the value of the property
            Object propertyValue = this.getValue(object, property);

            // retrieve the type information of the value
            DBSerializationInfo propertyTypeInfo = this.parser
                    .getSerializationInformation(property.getPropertyType());

            // Primitive Type
            if (propertyTypeInfo == null || propertyTypeInfo.isPrimitive()) {

                propertyValueMap.put(property.getAliasedColumnNames()
                        .iterator().next(), propertyValue);
            }
            // Value or Element Type
            else {

                for (String columnName : property.getColumnNames()) {

                    PropertyInfo columnProperty = propertyTypeInfo
                            .getPropertyInfoForColumn(columnName);

                    Object columnValue = this.getValue(propertyValue,
                            columnProperty);

                    propertyValueMap.put(
                            property.getAliasedColumnName(columnName),
                            columnValue);
                }
            }
        }

        return propertyValueMap;
    }

    private List<Map<String, ?>> getListPropertyValueMap(Object object,
            ListPropertyInfo listPropertyInfo)
            throws DBSerializationException {

        DBSerializationInfo info = this.parser
                .getSerializationInformation(object.getClass());

        Map<String, ?> aliasedPrimaryKeyToValueMap = listPropertyInfo
                .aliasContainerClassKeyValueMap(this.getPropertyValueMap(
                        object, info.getPrimaryKeys()));

        Collection<?> values = this
                .getValueCollection(object, listPropertyInfo);

        List<Map<String, ?>> valueMaps = new LinkedList<Map<String, ?>>();

        if (values.isEmpty()) {

            // System.out.println("  empty");
        }
        else {

            // System.out.println("Values: " + values);
            for (Object value : values) {

                Map<String, Object> valueMap = new LinkedHashMap<String, Object>(
                        aliasedPrimaryKeyToValueMap);

                DBSerializationInfo valueInfo = this.parser
                        .getSerializationInformation(listPropertyInfo
                                .getPropertyType());
                // Primitive or not annotated ValueType
                if (valueInfo == null) {

                    valueMap.put(listPropertyInfo.getListTypeAliasMap()
                            .values().iterator().next(), value);
                }
                // DBValueType
                else if (valueInfo.isValueType()) {

                    for (String listTypePrimaryKey : listPropertyInfo
                            .getListTypeAliasMap().keySet()) {

                        PropertyInfo columnValueInfo = valueInfo
                                .getPropertyInfoForColumn(listTypePrimaryKey);

                        System.out.println(listPropertyInfo.getPropertyName()
                                + ": " + listTypePrimaryKey);
                        System.out.println("Value class " + value.getClass());
                        Object valueTypeObject = this.getValue(value,
                                columnValueInfo);


                        valueMap.put(listPropertyInfo
                                .getAliasForListTypeKey(listTypePrimaryKey),
                                valueTypeObject);
                    }

                }
                // DBElementType
                else {
                    // System.out.println("Element");


                    Map<String, ?> aliasedListTypePrimaryKeyToValueMap = listPropertyInfo
                            .aliasListTypeKeyValueMap(this.getPropertyValueMap(
                                    value, valueInfo.getPrimaryKeys()));

                    valueMap.putAll(aliasedListTypePrimaryKeyToValueMap);
                }

                valueMaps.add(valueMap);
            }

        }

        return valueMaps;

    }

    private List<Map<String, ?>> getUpdateList(
            List<Map<String, ?>> listPropertyValueMaps,
            ListPropertyInfo listPropertyInfo)
            throws DBSerializationException {

        List<Map<String, ?>> updateList = new LinkedList<Map<String, ?>>();



        this.databaseDriver.prepareDuplicateCheck(
                listPropertyInfo.getTableName(),
                listPropertyInfo.getAliasedKeySet());


        for (Map<String, ?> valueMap : listPropertyValueMaps) {

            boolean duplicate = this.databaseDriver
                    .executeDuplicateCheck(valueMap);

            if (duplicate) {

                updateList.add(valueMap);
            }

        }
        this.databaseDriver.closePreparedStatement();

        for (Map<String, ?> update : updateList) {

            listPropertyValueMaps.remove(update);
        }

        return updateList;

    }

    /**
     * Deserializes all instances of the provided class from the database.
     *
     * @param clazz
     *            the class of the instances to deserialize.
     * @return a list of deserialized objects.
     * @throws DBSerializationException
     *             if deserialization fails.
     */
    public <T> List<T> deserialize(Class<? extends T> clazz)
            throws DBSerializationException {

        DBSerializationInfo info = this.parser
                .getSerializationInformation(clazz);


        this.databaseDriver.connectToDatabase();



        List<T> objectList = new LinkedList<T>();
        LinkedList<Prototype<?>> prototypeList = new LinkedList<Prototype<?>>();

        // System.out.println(info.selectStatement(null));

        this.databaseDriver.prepareUniversalSelection(info.getTable(), null
        // info.getPrimaryKeySet()
                );

        ResultSet results = this.databaseDriver.executeUniversalSelection(null);


        List<Map<String, ?>> convertedResults = this.databaseDriver
                .convertResultSet(results, String.class);

        this.databaseDriver.closePreparedQuery();
        for (Map<String, ?> result : convertedResults) {

            Map<String, Object> keyMap = new LinkedHashMap<String, Object>();

            for (PropertyInfo primaryKey : info.getPrimaryKeys()) {
                // System.out.println("PrimaryKey " +
                // primaryKey.getFieldName());

                for (String primaryKeyColumnName : primaryKey
                        .getAliasedColumnNames()) {

                    // System.out.println("Column Name: " +
                    // primaryKeyColumnName);
                    Object key = result.get(primaryKeyColumnName);
                    // System.out.println(primaryKey.getFieldName() + ": "
                    // + key.toString());

                    keyMap.put(primaryKeyColumnName, key);
                }
            }


            // System.out.println("KeyMap: " + keyMap);
            Prototype<T> prototype = new Prototype<T>(clazz, keyMap);

            prototypeList.add(prototype);
        }

        this.deserializePrototypes(prototypeList);



        this.databaseDriver.closeDatabaseConnection();


        for (Prototype<?> prototype : prototypeList) {

            // Setting the value of all element properties
            for (PropertyInfo elementProperty : prototype
                    .getElementProperties()) {


                Prototype<?> elementPrototype = prototype
                        .getElementPrototype(elementProperty);

                Object value = elementPrototype.getDeserializedObject();

                // System.out.println("Trying to set "
                // + prototype.getDeserializedObject() + "'s property "
                // + elementProperty.getFieldName() + " to " + value);
                this.setValue(prototype.getDeserializedObject(),
                        elementProperty, value);
            }

            // System.out.println("LISTSETTING");

            for (ListPropertyInfo elementListProperty : prototype
                    .getElementListProperties()) {

                List<Prototype<?>> elementListPrototypes = prototype
                        .getElementListPrototypes(elementListProperty);

                List<?> elementList = this
                        .assembleElementList(elementListPrototypes);

                // TODO: fix
                // System.out.println("Trying to set "
                // + prototype.getDeserializedObject() + "'s property "
                // + elementListProperty.getFieldName() + " to "
                // + elementList);
                this.setValue(prototype.getDeserializedObject(),
                        elementListProperty, elementList);

                // System.out.println("Value after setting:");

                // System.out.println("\t"
                // + this.getValueCollection(
                // prototype.getDeserializedObject(),
                // elementListProperty));
            }

            // adding only values of the desired type to the list.
            if (clazz.isAssignableFrom(prototype.getType())) {

                objectList.add(clazz.cast(prototype.getDeserializedObject()));
            }
        }


        return objectList;
    }

    /**
     * Deserializes the instances of the provided class with the provided
     * primary key values from the database.
     *
     * @param clazz
     *            the class of the instances to deserialize.
     * @param primaryKeyValueMap
     *            the primary key values of the instances to deserialize.
     * @return a list of deserialized objects.
     * @throws DBSerializationException
     *             if deserialization fails.
     */
    public <T> T deserialize(Class<T> clazz, Map<String, ?> primaryKeyValueMap)
            throws DBSerializationException {

        this.databaseDriver.connectToDatabase();

        this.parser.getSerializationInformation(clazz);

        Prototype<T> prototype = new Prototype<T>(clazz, primaryKeyValueMap);

        LinkedList<Prototype<?>> prototypeList = new LinkedList<Prototype<?>>();

        prototypeList.add(prototype);


        this.deserializePrototypes(prototypeList);

        this.databaseDriver.closeDatabaseConnection();

        return prototype.getDeserializedObject();
    }


    private void deserializePrototypes(LinkedList<Prototype<?>> prototypes)
            throws DBSerializationException {


        LinkedList<Prototype<?>> parsedPrototypes = new LinkedList<Prototype<?>>();

        this.deserializePrototypes(prototypes, parsedPrototypes);


        prototypes.addAll(parsedPrototypes);
    }


    private void deserializePrototypes(
            LinkedList<Prototype<?>> prototypesToParse,
            LinkedList<Prototype<?>> parsedPrototypes)
            throws DBSerializationException {

        if (!prototypesToParse.isEmpty()) {

            Prototype<?> prototype = prototypesToParse.pollFirst();
            parsedPrototypes.add(prototype);

            List<Prototype<?>> elementPrototypes = this
                    .performBasicDeserialization(prototype);

            for (Prototype<?> elementPrototype : elementPrototypes) {

                boolean alreadyParsed = parsedPrototypes
                        .contains(elementPrototype);
                boolean inQueue = prototypesToParse.contains(elementPrototype);

                if (!alreadyParsed && !inQueue) {

                    prototypesToParse.add(elementPrototype);
                }
                else {
                    // System.out.println("This might blow up badly...");
                    Prototype<?> referencePrototye = null;
                    if (alreadyParsed) {


                        referencePrototye = parsedPrototypes
                                .get(parsedPrototypes.indexOf(elementPrototype));

                    }
                    else {
                        referencePrototye = prototypesToParse
                                .get(prototypesToParse
                                        .indexOf(elementPrototype));
                    }
                    elementPrototype.setReferencePrototype(referencePrototye);
                }
            }


            List<Prototype<?>> elementListPrototypes = this
                    .performComplexDeserialization(prototype);

            for (Prototype<?> elementListPrototype : elementListPrototypes) {
                boolean alreadyParsed = parsedPrototypes
                        .contains(elementListPrototype);
                boolean inQueue = prototypesToParse
                        .contains(elementListPrototype);

                if (!alreadyParsed && !inQueue) {

                    // System.out.println("Adding elementListPrototype: "
                    // + elementListPrototype.getType() + " : "
                    // + elementListPrototype.getPrimaryKeyValueMap());
                    prototypesToParse.add(elementListPrototype);
                }
                else {
                    // System.out.println("This might blow up badly...");
                    Prototype<?> referencePrototye = null;
                    if (alreadyParsed) {


                        referencePrototye = parsedPrototypes
                                .get(parsedPrototypes
                                        .indexOf(elementListPrototype));

                    }
                    else {
                        referencePrototye = prototypesToParse
                                .get(prototypesToParse
                                        .indexOf(elementListPrototype));
                    }
                    elementListPrototype
                            .setReferencePrototype(referencePrototye);
                }
            }

            this.deserializePrototypes(prototypesToParse, parsedPrototypes);
        }


    }


    private <T> List<Prototype<?>> performBasicDeserialization(
            Prototype<T> prototype)
            throws DBSerializationException {

        DBSerializationInfo info = this.parser
                .getSerializationInformation(prototype.getType());

        System.out.println("\n\nBasic Deserialization for class "
                + prototype.getType());

        this.databaseDriver.prepareUniversalSelection(info.getTable(),
                prototype.getPrimaryKeyValueMap().keySet());

        ResultSet results = this.databaseDriver
                .executeUniversalSelection(prototype.getPrimaryKeyValueMap());



        Map<String, ?> propertyValueMap = null;

        propertyValueMap = this.databaseDriver.convertSingleResult(results,
                String.class);

        this.databaseDriver.closePreparedStatement();
        System.out.println("Property map: " + propertyValueMap);

        Object[] initargs = this.assembleConstructorParameters(info,
                propertyValueMap);

        System.out.println("Init args:" + Arrays.toString(initargs));

        this.createInstance(prototype, initargs);

        T retrievedInstance = prototype.getDeserializedObject();

        List<Prototype<?>> elementPropertyPrototypes = new LinkedList<Prototype<?>>();

        for (PropertyInfo pkProperty : info.getPrimaryKeys()) {

            this.setValue(pkProperty, retrievedInstance, propertyValueMap);
        }

        System.out.println("RetrievedInstance after setting primary keys: "
                + retrievedInstance);

        for (PropertyInfo valueProperty : info.getValueTypeProperties()) {

            System.out.println("Setting value property "
                    + valueProperty.getPropertyName()
                    + " with propertyValueMap " + propertyValueMap);
            this.setValue(valueProperty, retrievedInstance, propertyValueMap);
        }

        System.out.println("RetrievedInstance after setting value type "
                + "properties: " + retrievedInstance);



        for (PropertyInfo elementProperty : info.getElementTypeProperties()) {

            System.out
                    .println("Creating element property prototype for property "
                            + elementProperty.getPropertyName()
                            + " with propertyValueMap " + propertyValueMap);

            Prototype<?> elementPrototype = this.createPrototypeForProperty(
                    elementProperty, retrievedInstance, propertyValueMap);

            prototype.addElementPrototype(elementProperty, elementPrototype);

            elementPropertyPrototypes.add(elementPrototype);
        }

        System.out.println("Done with simple deserialization.");
        return elementPropertyPrototypes;
    }


    private List<Prototype<?>> performComplexDeserialization(
            Prototype<?> prototype)
            throws DBSerializationException {

        DBSerializationInfo info = this.parser
                .getSerializationInformation(prototype.getType());

        System.out.println("\n\nComplex Deserialization for class "
                + prototype.getType());


        List<Prototype<?>> elementListPropertyPrototypes = new LinkedList<Prototype<?>>();

        Map<String, ?> keyValueMap = prototype.getPrimaryKeyValueMap();

        System.out.println("\tValueListTypes");
        for (ListPropertyInfo valueListProperty : info
                .getValueTypeListProperties()) {

            List<Map<String, ?>> results = this.retrieveListElements(
                    valueListProperty, keyValueMap);

            // System.out.println("Ruh roh!");
            this.setValueList(valueListProperty,
                    prototype.getDeserializedObject(), results);

        }


        System.out.println("\tElementListTypes");
        for (ListPropertyInfo elementListProperty : info
                .getElementTypeListProperties()) {

            List<Map<String, ?>> results = this.retrieveListElements(
                    elementListProperty, keyValueMap);

            System.out.println("RetrievedListElements: " + results);
            // System.out.println("Rah roh!");
            List<Prototype<?>> elementPrototypes = this
                    .createPrototypesForListProperty(elementListProperty,
                            prototype.getDeserializedObject(), results);
            elementListPropertyPrototypes.addAll(elementPrototypes);


            prototype.addElementListPrototypes(elementListProperty,
                    elementPrototypes);
        }


        return elementListPropertyPrototypes;
    }

    private List<Map<String, ?>> retrieveListElements(
            ListPropertyInfo listProperty, Map<String, ?> keyValueMap)
            throws DBSerializationException {

        Map<String, ?> aliasedPrimaryKeys = listProperty
                .aliasContainerClassKeyValueMap(keyValueMap);


        this.databaseDriver.prepareUniversalSelection(listProperty
                .getTableName(), new LinkedHashSet<String>(listProperty
                .getContainerClassAliasMap().values()));

        // System.out.println("\t\t" + query);
        ResultSet resultsSet = this.databaseDriver
                .executeUniversalSelection(aliasedPrimaryKeys);

        List<Map<String, ?>> results = this.databaseDriver.convertResultSet(
                resultsSet, String.class);

        this.databaseDriver.closePreparedQuery();
        // System.out.println("\t\tResults: " + results);
        List<Map<String, ?>> dealiasedResults = new LinkedList<Map<String, ?>>();
        for (Map<String, ?> result : results) {

            // System.out.println("\t\tSerialized element of type "
            // + listProperty.getListType() + ":");

            Map<String, Object> dealiasedResult = new LinkedHashMap<String, Object>();

            for (String key : listProperty.getListTypeAliasMap().keySet()) {

                String aliasedKey = listProperty.getAliasForListTypeKey(key);
                Object value = result.get(aliasedKey);

                // System.out.println("\t\t\t" + key + ":" + aliasedKey + ": "
                // + value);

                dealiasedResult.put(key, value);

            }
            dealiasedResults.add(dealiasedResult);
        }

        return dealiasedResults;
    }

    private Map<String, ?> translatePropertyValueMapKeys(
            PropertyInfo propertyInfo, Map<String, ?> propertyValueMap) {

        Map<String, Object> translatedPropertyValueMap = new LinkedHashMap<String, Object>(
                propertyValueMap.size());

        for (String ownColumnName : propertyInfo.getAliasedColumnNames()) {

            // System.out.println("Own column name: " + ownColumnName);

            String foreignColumnName = propertyInfo
                    .getColumnName(ownColumnName);
            // System.out.println("Foreign column name: " + foreignColumnName);

            Object columnValue = propertyValueMap.get(ownColumnName);

            // System.out.println("Column value: " + columnValue);

            translatedPropertyValueMap.put(foreignColumnName, columnValue);
        }

        return translatedPropertyValueMap;
    }


    private void setValue(PropertyInfo propertyInfo, Object retrievedInstance,
            Map<String, ?> propertyValueMap)
            throws DBSerializationException {

        if (propertyInfo.isPrimitive()) {

            String columnName = propertyInfo.getAliasedColumnNames().iterator()
                    .next();

            Object value = propertyValueMap.get(columnName);


            this.setValue(retrievedInstance, propertyInfo, value);

        }
        // Value type
        else if (propertyInfo.isValueType()) {

            Map<String, ?> parameterValueMap = this
                    .translatePropertyValueMapKeys(propertyInfo,
                            propertyValueMap);

            Class<?> valueType = propertyInfo.getPropertyType();

            DBSerializationInfo valueTypeInfo = this.parser
                    .getSerializationInformation(valueType);


            Object[] initargs = this.assembleConstructorParameters(
                    valueTypeInfo, parameterValueMap);

            Prototype<?> valuePrototype = Prototype.createPrototype(
                    propertyInfo.getPropertyType(), parameterValueMap);

            this.createInstance(valuePrototype, initargs);

            Object value = valuePrototype.getDeserializedObject();


            this.setValue(retrievedInstance, propertyInfo, value);


        }
        // Element type
        else {

            throw new DBSerializationException(
                    "Cannot set value of element type property!");
        }

    }

    private void setValueList(ListPropertyInfo propertyInfo,
            Object retrievedInstance, List<Map<String, ?>> propertyValueMaps)
            throws DBSerializationException {

        this.setValueList(propertyInfo, propertyInfo.getPropertyType(),
                retrievedInstance, propertyValueMaps);
    }

    private <T> void setValueList(ListPropertyInfo propertyInfo,
            Class<? extends T> listType, Object retrievedInstance,
            List<Map<String, ?>> propertyValueMaps)
            throws DBSerializationException {

        Collection<T> propertyCollection = null;

        Class<?> collectionType = propertyInfo.getCollectionType();
        if (collectionType == List.class
                || collectionType == Collection.class
                || Arrays.asList(collectionType.getInterfaces()).contains(
                        List.class)) {

            propertyCollection = ReflectionUtils.createLinkedList(listType);
        }
        else if (collectionType == Set.class
                || Arrays.asList(collectionType.getInterfaces()).contains(
                        Set.class)) {

            propertyCollection = ReflectionUtils.createLinkedHashSet(listType);
        }
        else {
            throw new DBSerializationException(collectionType
                    + " - Unsupported Collection type!");
        }

        if (propertyInfo.isElementType()) {

            throw new DBSerializationException(
                    "Cannot set list of element type properties!");
        }

        for (Map<String, ?> propertyValueMap : propertyValueMaps) {

            Object value = null;

            if (propertyInfo.isPrimitive()) {

                String columnName = propertyInfo.getListTypeAliasMap().keySet()
                        .iterator().next();

                value = propertyValueMap.get(columnName);

            }
            // Value type
            else if (propertyInfo.isValueType()) {


                Class<?> valueType = propertyInfo.getPropertyType();

                DBSerializationInfo valueTypeInfo = this.parser
                        .getSerializationInformation(valueType);


                Object[] initargs = this.assembleConstructorParameters(
                        valueTypeInfo, propertyValueMap);

                Prototype<?> valuePrototype = Prototype.createPrototype(
                        valueType, propertyValueMap);

                this.createInstance(valuePrototype, initargs);

                value = valuePrototype.getDeserializedObject();
            }

            T convertedValue;
            try {

                convertedValue = ReflectionUtils.castValue(listType, value);
            }
            catch (ReflectionException e) {

                throw new DBSerializationException(
                        "Retrieved list element could not be converted to list type "
                                + listType, e);
            }

            propertyCollection.add(convertedValue);
        }

        this.setValue(retrievedInstance, propertyInfo, propertyCollection);
    }

    private Prototype<?> createPrototypeForProperty(PropertyInfo propertyInfo,
            Object retrievedInstance, Map<String, ?> propertyValueMap)
            throws DBSerializationException {

        if (propertyInfo.isPrimitive() || propertyInfo.isValueType()) {

            throw new DBSerializationException(
                    "Cannot create prototype for primitive or value type!");

        }
        // Element type
        else {
            Map<String, Object> primaryKeyValueMap = new LinkedHashMap<String, Object>();

            for (String foreignKeyColumnName : propertyInfo.getColumnNames()) {
                // System.out.println("ForeignKey column name: "
                // + foreignKeyColumnName);

                String ownColumnName = propertyInfo
                        .getAliasedColumnName(foreignKeyColumnName);
                // System.out.println("Own column name: " + ownColumnName);

                Object columnValue = propertyValueMap.get(ownColumnName);

                // System.out.println("Element Type column value: " +
                // columnValue);

                primaryKeyValueMap.put(foreignKeyColumnName, columnValue);
            }

            Prototype<?> elementPrototype = Prototype.createPrototype(
                    propertyInfo.getPropertyType(), primaryKeyValueMap);

            return elementPrototype;
        }
    }

    private List<Prototype<?>> createPrototypesForListProperty(
            ListPropertyInfo propertyInfo, Object retrievedInstance,
            List<Map<String, ?>> propertyValueMaps)
            throws DBSerializationException {

        if (propertyInfo.isPrimitive() || propertyInfo.isValueType()) {

            throw new DBSerializationException(
                    "Cannot create prototype for primitive or value type!");

        }

        List<Prototype<?>> elementPrototypes = new LinkedList<Prototype<?>>();

        for (Map<String, ?> propertyValueMap : propertyValueMaps) {


            // System.out.println("Creating prototype of type "
            // + propertyInfo.getListType() + " with values "
            // + propertyValueMap);
            Prototype<?> elementPrototype = Prototype.createPrototype(
                    propertyInfo.getPropertyType(), propertyValueMap);

            elementPrototypes.add(elementPrototype);

        }
        return elementPrototypes;
    }

    private <T> List<T> assembleElementList(
            List<Prototype<? extends T>> elementListPrototypes)
            throws DBSerializationException {

        List<T> elementList = new LinkedList<T>();

        for (Prototype<? extends T> prototype : elementListPrototypes) {

            elementList.add(prototype.getDeserializedObject());
        }

        return elementList;
    }

    private Object[] assembleConstructorParameters(DBSerializationInfo info,
            Map<String, ?> propertyValueMap)
            throws DBSerializationException {

        Object initargs[] = new Object[info.getConstructorParameters().size()];
        int i = 0;
        // System.out.println("Listing constructor parameters.");
        for (String constructorParameter : info.getConstructorParameters()) {

            // System.out.println("Constructor parameter name: "
            // + constructorParameter);


            if (info.containsPropertyInfoForField(constructorParameter)) {

                PropertyInfo propertyInfo = info
                        .getPropertyInfoForField(constructorParameter);
                for (String columnName : propertyInfo.getAliasedColumnNames()) {

                    if (propertyValueMap.containsKey(columnName)) {

                        initargs[i] = propertyValueMap.get(columnName);
                    }
                }

            }
            else {

                throw new DBSerializationException(
                        "Serialization information does not "
                                + "contain property information "
                                + "for required constructor parameter \""
                                + constructorParameter + "\"");
            }

            i++;
        }
        return initargs;

    }

    private <T> void createInstance(Prototype<T> prototype, Object[] initargs)
            throws DBSerializationException {

        try {

            prototype.setDeserializedObject(ReflectionUtils.createInstance(
                    prototype.getType(), initargs));
        }
        catch (ReflectionException e) {

            throw new DBSerializationException(
                    "Could not create new instance of class "
                            + prototype.getType() + " with primary keys "
                            + prototype.getPrimaryKeyValueMap()
                            + " and initargs " + Arrays.toString(initargs)
                            + "!", e);
        }
    }


    private Object getValue(Object object, PropertyInfo propertyInfo)
            throws DBSerializationException {

        Object newObject;

        System.out.println("PropertyInfo: " + propertyInfo);
        System.out.println("PropertyInfo-field: " + propertyInfo.getField());
        System.out.println("PropertyInfo-getter: " + propertyInfo.getGetter());
        try {
            newObject = ReflectionUtils.getValue(object,
                    propertyInfo.getField(), propertyInfo.getGetter());

        }
        catch (ReflectionException e) {

            throw new DBSerializationException("Could not retrieve value of "
                    + propertyInfo.accesssSignature() + " in " + object + "!",
                    e);
        }

        return newObject;
    }

    private Collection<?> getValueCollection(Object object,
            ListPropertyInfo propertyInfo)
            throws DBSerializationException {

        try {
            return ReflectionUtils.getValueCollection(object,
                    propertyInfo.getField(), propertyInfo.getGetter());
        }
        catch (ReflectionException e) {

            throw new DBSerializationException(
                    "Could not retrieve value collection of "
                            + propertyInfo.accesssSignature() + " in " + object
                            + "!", e);
        }
    }

    private void setValue(Object object, PropertyInfo propertyInfo, Object value)
            throws DBSerializationException {

        try {

            ReflectionUtils.setValue(object, propertyInfo.getField(),
                    propertyInfo.getSetter(), value);
        }
        catch (ReflectionException e) {

            throw new DBSerializationException("Could not set value of "
                    + propertyInfo.mutationSignature() + " in " + object
                    + " to " + value + "!", e);
        }
    }

    private void setValue(Object object, ListPropertyInfo propertyInfo,
            Object value)
            throws DBSerializationException {

        try {

            ReflectionUtils.setValue(object, propertyInfo.getField(),
                    propertyInfo.getSetter(), value);
        }
        catch (ReflectionException e) {

            throw new DBSerializationException("Could not set value of "
                    + propertyInfo.mutationSignature() + " in " + object
                    + " to " + value + "!", e);
        }
    }
}
