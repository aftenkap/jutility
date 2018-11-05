package org.jutility.io.database;

// @formatter:off
/*
 * #%L
 * jutility-incubation
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * %% Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * #L%
 */
// @formatter:on


import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jutility.common.reflection.ReflectionException;
import org.jutility.common.reflection.ReflectionUtils;


/**
 *
 *
 * @author Peter J. Radics
 * @version 0.1
 * @param <T>
 *            the type of the prototype.
 *
 */
public class Prototype<T> {

    private final Class<? extends T>                        type;
    private final Map<String, ?>                            primaryKeyValueMap;
    private final Map<PropertyInfo, Prototype<?>>           elementPrototypes;
    private final Map<ListPropertyInfo, List<Prototype<?>>> elementListPrototypes;
    private T                                               deserializedObject;
    private Prototype<?>                                    referencePrototype;

    /**
     * Getter for the type
     *
     * @return the type.
     */
    public Class<? extends T> getType() {

        return this.type;
    }


    /**
     * Getter for the primary key-value map.
     *
     * @return the primary key-value map.
     */
    public Map<String, ?> getPrimaryKeyValueMap() {

        return Collections.unmodifiableMap(this.primaryKeyValueMap);
    }

    /**
     * Returns the prototype of the provided element property.
     *
     * @param elementProperty
     *            the element property.
     * @return the element property prototype.
     */
    public Prototype<?> getElementPrototype(PropertyInfo elementProperty) {

        return this.elementPrototypes.get(elementProperty);
    }

    /**
     * Returns the element properties.
     *
     * @return the element properties.
     */
    public Collection<PropertyInfo> getElementProperties() {

        return this.elementPrototypes.keySet();
    }

    /**
     * Returns the element property prototypes.
     *
     * @return the element property prototypes.
     */
    public Map<PropertyInfo, Prototype<?>> getElementPrototypes() {

        return Collections.unmodifiableMap(this.elementPrototypes);
    }

    /**
     * Adds an element property prototype.
     *
     * @param elementProperty
     *            the element property.
     * @param elementPrototype
     *            the element prototype.
     * @return the previous element prototype.
     */
    public Prototype<?> addElementPrototype(PropertyInfo elementProperty,
            Prototype<?> elementPrototype) {

        return this.elementPrototypes.put(elementProperty, elementPrototype);
    }

    /**
     * Removes an element property prototype.
     *
     * @param elementProperty
     *            the element property.
     * @return the removed element prototype.
     */
    public Prototype<?> removeElementPrototye(PropertyInfo elementProperty) {

        return this.elementPrototypes.remove(elementProperty);
    }

    /**
     * Clears the element prototype map.
     */
    public void clearElementPrototypes() {

        this.elementPrototypes.clear();
    }


    /**
     * Returns the prototype list for the element list property.
     *
     * @param elementListProperty
     *            the element list property.
     * @return the prototype list for the element list property.
     */
    public List<Prototype<?>> getElementListPrototypes(
            ListPropertyInfo elementListProperty) {

        return this.elementListPrototypes.get(elementListProperty);
    }

    /**
     * Returns the element list properties.
     *
     * @return the element list properties.
     */
    public Collection<ListPropertyInfo> getElementListProperties() {

        return this.elementListPrototypes.keySet();
    }

    /**
     * Returns the element list property prototypes.
     *
     * @return the element list property prototypes.
     */
    public Map<ListPropertyInfo, List<Prototype<?>>> getElementListPrototypes() {

        return Collections.unmodifiableMap(this.elementListPrototypes);
    }

    /**
     * Adds element list property prototypes.
     *
     * @param elementListProperty
     *            the element list property.
     * @param elementListPrototypes
     *            the element list prototype.
     * @return the previous element prototypes.
     */
    public List<Prototype<?>> addElementListPrototypes(
            ListPropertyInfo elementListProperty,
            List<Prototype<?>> elementListPrototypes) {

        return this.elementListPrototypes.put(elementListProperty,
                elementListPrototypes);
    }

    /**
     * Removes element list property prototypes.
     *
     * @param elementListProperty
     *            the element list property.
     * @return the removed element prototypes.
     */
    public List<Prototype<?>> removeElementListPrototyes(
            ListPropertyInfo elementListProperty) {

        return this.elementListPrototypes.remove(elementListProperty);
    }

    /**
     * Clears the element list prototypes.
     */
    public void clearElementListPrototypes() {

        this.elementListPrototypes.clear();
    }


    /**
     * Sets the reference prototype.
     *
     * @param referencePrototype
     *            the reference prototype.
     */
    public void setReferencePrototype(Prototype<?> referencePrototype) {


        this.referencePrototype = referencePrototype;
    }



    /**
     * Returns whether or not this is a referenced prototype.
     *
     * @return {@code true} if this is a referenced prototype; {@code false}
     *         otherwise.
     */
    public boolean isReferencePrototype() {

        return this.referencePrototype == null;
    }

    /**
     * Getter for the deserialized object.
     *
     * @return the deserialized object.
     * @throws DBSerializationException
     */
    public T getDeserializedObject()
            throws DBSerializationException {

        if (!this.isReferencePrototype()) {
            try {
                return ReflectionUtils.castValue(this.getType(),
                        this.referencePrototype.getDeserializedObject());
            }
            catch (ReflectionException e) {

                throw new DBSerializationException(
                        "Could not retrieve deserialized object from reference prototype!",
                        e);
            }
        }
        return this.deserializedObject;
    }

    /**
     * Setter for the deserialized object.
     *
     * @param deserializedObject
     *            the new value.
     * @throws DBSerializationException
     */
    public void setDeserializedObject(T deserializedObject)
            throws DBSerializationException {

        this.deserializedObject = deserializedObject;

        if (!this.isReferencePrototype()) {

            throw new DBSerializationException(
                    "Trying to set deserialized value of prototype in non reference prototype");
        }
    }

    /**
     * Creates a new prototype
     *
     * @param type
     *            the type of the prototype.
     * @param primaryKeyValueMap
     *            the primary key-value map.
     */
    public Prototype(Class<? extends T> type, Map<String, ?> primaryKeyValueMap) {

        if (type == null || primaryKeyValueMap == null
                || primaryKeyValueMap.isEmpty()) {

            throw new IllegalArgumentException(
                    "Cannot create Prototype from provided values!");
        }

        this.type = type;
        this.primaryKeyValueMap = primaryKeyValueMap;
        this.elementPrototypes = new LinkedHashMap<>();
        this.elementListPrototypes = new LinkedHashMap<>();
        this.deserializedObject = null;
        this.referencePrototype = null;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof Prototype) {
            Prototype<?> other = (Prototype<?>) obj;

            return (this.getType() == other.getType())
                    && this.getPrimaryKeyValueMap().equals(
                            other.getPrimaryKeyValueMap());

        }
        return false;
    }

    @Override
    public int hashCode() {

        return this.type.hashCode() + this.primaryKeyValueMap.hashCode();
    }

    static <T> Prototype<T> createPrototype(Class<T> type,
            Map<String, ?> primaryKeyValueMap) {

        return new Prototype<>(type, primaryKeyValueMap);
    }
}
