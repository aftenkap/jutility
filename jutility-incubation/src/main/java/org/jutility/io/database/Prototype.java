package org.jutility.io.database;


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

    public Prototype<?> getElementPrototype(PropertyInfo elementProperty) {

        return this.elementPrototypes.get(elementProperty);
    }

    public Collection<PropertyInfo> getElementProperties() {

        return this.elementPrototypes.keySet();
    }

    public Map<PropertyInfo, Prototype<?>> getElementPrototypes() {

        return Collections.unmodifiableMap(this.elementPrototypes);
    }

    public Prototype<?> addElementPrototype(PropertyInfo elementProperty,
            Prototype<?> elementPrototype) {

        return this.elementPrototypes.put(elementProperty, elementPrototype);
    }

    public Prototype<?> removeElementPrototye(PropertyInfo elementProperty) {

        return this.elementPrototypes.remove(elementProperty);
    }

    public void clearElementPrototypes() {

        this.elementPrototypes.clear();
    }


    public List<Prototype<?>> getElementListPrototypes(
            ListPropertyInfo elementListProperty) {

        return this.elementListPrototypes.get(elementListProperty);
    }

    public Collection<ListPropertyInfo> getElementListProperties() {

        return this.elementListPrototypes.keySet();
    }

    public Map<ListPropertyInfo, List<Prototype<?>>> getElementListPrototypes() {

        return Collections.unmodifiableMap(this.elementListPrototypes);
    }

    public List<Prototype<?>> addElementListPrototypes(
            ListPropertyInfo elementListProperty,
            List<Prototype<?>> elementListPrototypes) {

        return this.elementListPrototypes.put(elementListProperty,
                elementListPrototypes);
    }

    public List<Prototype<?>> removeElementListPrototyes(
            ListPropertyInfo elementListProperty) {

        return this.elementListPrototypes.remove(elementListProperty);
    }

    public void clearElementListPrototypes() {

        this.elementListPrototypes.clear();
    }


    public void setReferencePrototype(Prototype<?> referencePrototype) {


        this.referencePrototype = referencePrototype;
    }



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
        this.elementPrototypes = new LinkedHashMap<PropertyInfo, Prototype<?>>();
        this.elementListPrototypes = new LinkedHashMap<ListPropertyInfo, List<Prototype<?>>>();
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

        return new Prototype<T>(type, primaryKeyValueMap);
    }
}
