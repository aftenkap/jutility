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
import java.util.LinkedHashMap;
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
public class ListPropertyInfo
        extends BaseProperty {

    private final String              tableName;
    private final Map<String, String> containerClassAliasMap;
    private final Map<String, String> listTypeAliasMap;
    private final Class<?>            collectionType;


    /**
     * Returns the table name.
     *
     * @return the table name.
     */
    public String getTableName() {

        return tableName;
    }

    /**
     * Returns the collection type.
     *
     * @return the collection type.
     */
    public Class<?> getCollectionType() {

        return this.collectionType;
    }

    /**
     * Returns the container-class alias map.
     *
     * @return the container-class alias map.
     */
    public Map<String, String> getContainerClassAliasMap() {

        return this.containerClassAliasMap;
    }

    /**
     * Returns the list-type alias map.
     *
     * @return the list-type alias map.
     */
    public Map<String, String> getListTypeAliasMap() {

        return this.listTypeAliasMap;
    }

    /**
     * Returns the alias for a container-class key.
     *
     * @param key
     *            the key.
     * @return the alias.
     */
    public String getAliasForContainerClassKey(String key) {

        return this.containerClassAliasMap.get(key);
    }

    /**
     * Returns the container-class key for an alias.
     *
     * @param alias
     *            the alias.
     * @return the key.
     */
    public String getContainerClassKeyForAlias(String alias) {

        for (String primaryKey : this.containerClassAliasMap.keySet()) {

            if (this.containerClassAliasMap.get(primaryKey).equals(alias)) {

                return primaryKey;
            }
        }

        return null;
    }

    /**
     * Returns the alias for a list-type key.
     *
     * @param key
     *            the key.
     * @return the alias.
     */
    public String getAliasForListTypeKey(String key) {

        return this.listTypeAliasMap.get(key);
    }

    /**
     * Returns the list-type key for an alias.
     *
     * @param foreignKeyAlias
     *            the alias.
     * @return the key.
     */
    public String getListTypeKeyForAlias(String foreignKeyAlias) {

        for (String foreignKey : this.containerClassAliasMap.keySet()) {

            if (this.listTypeAliasMap.get(foreignKey).equals(foreignKeyAlias)) {

                return foreignKey;
            }
        }

        return null;
    }

    /**
     * Returns a list of all aliased keys.
     *
     * @return a list of all aliased keys.
     */
    public Set<String> getAliasedKeySet() {

        Set<String> aliasedKeyList = new LinkedHashSet<>();

        for (String key : this.getContainerClassAliasMap().keySet()) {

            aliasedKeyList.add(this.getAliasForContainerClassKey(key));
        }
        for (String key : this.getListTypeAliasMap().keySet()) {

            aliasedKeyList.add(this.getAliasForListTypeKey(key));
        }

        return aliasedKeyList;
    }


    /**
     * Returns a list of all aliased keys of the container class.
     *
     * @return a list of all aliased keys of the container class.
     */
    public Set<String> getAliasedContainerClassKeySet() {

        Set<String> aliasedKeyList = new LinkedHashSet<>();

        for (String key : this.getContainerClassAliasMap().keySet()) {

            aliasedKeyList.add(this.getAliasForContainerClassKey(key));
        }

        return aliasedKeyList;
    }

    /**
     * Returns a list of all aliased keys of the list type.
     *
     * @return a list of all aliased keys of the list type.
     */
    public Set<String> getAliasedListTypeKeySet() {

        Set<String> aliasedKeyList = new LinkedHashSet<>();

        for (String key : this.getListTypeAliasMap().keySet()) {

            aliasedKeyList.add(this.getAliasForListTypeKey(key));
        }

        return aliasedKeyList;
    }

    /**
     * Creates a new instance of the {@link ListPropertyInfo} class.
     *
     * @param propertyName
     * @param listType
     * @param collectionType
     * @param tableName
     * @param containerClassAliasMap
     * @param listTypeAliasMap
     */
    public ListPropertyInfo(String propertyName, Class<?> listType,
            Class<?> collectionType, String tableName,
            Map<String, String> containerClassAliasMap,
            Map<String, String> listTypeAliasMap) {

        this(propertyName, listType, collectionType, tableName,
                containerClassAliasMap, listTypeAliasMap, null, null, null);
    }

    /**
     * Creates a new instance of the {@link ListPropertyInfo} class.
     *
     * @param propertyName
     * @param listType
     * @param collectionType
     * @param tableName
     * @param containerClassAliasMap
     * @param listTypeAliasMap
     * @param field
     */
    public ListPropertyInfo(String propertyName, Class<?> listType,
            Class<?> collectionType, String tableName,
            Map<String, String> containerClassAliasMap,
            Map<String, String> listTypeAliasMap, Field field) {

        this(propertyName, listType, collectionType, tableName,
                containerClassAliasMap, listTypeAliasMap, field, null, null);
    }


    /**
     * Creates a new instance of the {@link ListPropertyInfo} class.
     *
     * @param propertyName
     * @param listType
     * @param collectionType
     * @param tableName
     * @param containerClassAliasMap
     * @param listTypeAliasMap
     * @param getter
     * @param setter
     */
    public ListPropertyInfo(String propertyName, Class<?> listType,
            Class<?> collectionType, String tableName,
            Map<String, String> containerClassAliasMap,
            Map<String, String> listTypeAliasMap, Method getter, Method setter) {

        this(propertyName, listType, collectionType, tableName,
                containerClassAliasMap, listTypeAliasMap, null, getter, setter);
    }

    /**
     * Creates a new instance of the {@link ListPropertyInfo} class.
     *
     * @param propertyName
     * @param propertyType
     * @param collectionType
     * @param tableName
     * @param containerClassAliasMap
     * @param listTypeAliasMap
     * @param field
     * @param getter
     * @param setter
     */
    public ListPropertyInfo(String propertyName, Class<?> propertyType,
            Class<?> collectionType, String tableName,
            Map<String, String> containerClassAliasMap,
            Map<String, String> listTypeAliasMap, Field field, Method getter,
            Method setter) {

        super(propertyName, propertyType);

        this.tableName = tableName;
        this.containerClassAliasMap = containerClassAliasMap;
        this.listTypeAliasMap = listTypeAliasMap;

        this.collectionType = collectionType;

        this.setField(field);
        this.setGetter(getter);
        this.setSetter(setter);
    }



    /**
     * Aliases a container-class key-value map.
     *
     * @param keyValueMap
     *            the key-value map.
     * @return an aliased key-value map.
     */
    public Map<String, ?> aliasContainerClassKeyValueMap(
            Map<String, ?> keyValueMap) {

        LinkedHashMap<String, Object> aliasedKeyValueMap = new LinkedHashMap<>(
                keyValueMap.size());

        for (String key : keyValueMap.keySet()) {

            Object value = keyValueMap.get(key);
            String alias = this.getAliasForContainerClassKey(key);

            aliasedKeyValueMap.put(alias, value);
        }

        return aliasedKeyValueMap;
    }

    /**
     * De-Aliases a container-class key-value map.
     *
     * @param aliasedKeyValueMap
     *            the aliased key-value map.
     * @return a de-aliased key-value map.
     */
    public Map<String, ?> dealiasContainerClassKeyValueMap(
            Map<String, ?> aliasedKeyValueMap) {

        LinkedHashMap<String, Object> keyValueMap = new LinkedHashMap<>(
                aliasedKeyValueMap.size());

        for (String alias : aliasedKeyValueMap.keySet()) {

            Object value = aliasedKeyValueMap.get(alias);
            String key = this.getContainerClassKeyForAlias(alias);

            keyValueMap.put(key, value);
        }

        return keyValueMap;
    }

    /**
     * Aliases a list-type key-value map.
     *
     * @param keyValueMap
     *            the key-value map.
     * @return an aliased key-value map.
     */
    public Map<String, ?> aliasListTypeKeyValueMap(Map<String, ?> keyValueMap) {

        LinkedHashMap<String, Object> aliasedKeyValueMap = new LinkedHashMap<>(
                keyValueMap.size());

        for (String key : keyValueMap.keySet()) {

            Object value = keyValueMap.get(key);
            String alias = this.getAliasForListTypeKey(key);

            aliasedKeyValueMap.put(alias, value);
        }
        return aliasedKeyValueMap;
    }

    /**
     * De-Aliases a list-type key-value map.
     *
     * @param aliasedKeyValueMap
     *            the aliased key-value map.
     * @return a de-aliased key-value map.
     */
    public Map<String, ?> dealiasListTypeKeyValueMap(
            Map<String, ?> aliasedKeyValueMap) {

        LinkedHashMap<String, Object> keyValueMap = new LinkedHashMap<>(
                aliasedKeyValueMap.size());
        for (String alias : aliasedKeyValueMap.keySet()) {

            Object value = aliasedKeyValueMap.get(alias);
            String key = this.getListTypeKeyForAlias(alias);

            keyValueMap.put(key, value);
        }
        return keyValueMap;
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

        if (obj != null && obj instanceof ListPropertyInfo) {
            ListPropertyInfo otherEntry = (ListPropertyInfo) obj;
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

        String returnValue = this.getPropertyName() + "->`" + tableName + "` "
                + containerClassAliasMap + "-" + listTypeAliasMap;

        returnValue += ": Field: " + (this.getField() != null);

        returnValue += ", Getter: " + (this.getGetter() != null);

        returnValue += ", Setter: " + (this.getSetter() != null);


        return returnValue;

    }
}
