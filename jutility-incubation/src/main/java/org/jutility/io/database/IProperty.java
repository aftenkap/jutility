package org.jutility.io.database;


import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 *
 *
 * @author Peter J. Radics
 * @version 0.1
 */

public interface IProperty {

    /**
     * Returns the property name.
     *
     * @return the property name.
     */
    public String getPropertyName();

    /**
     * Returns the property type.
     *
     * @return the property type.
     */
    public Class<?> getPropertyType();



    /**
     * Returns the field.
     *
     * @return the field.
     */
    public Field getField();

    /**
     * Sets the field.
     *
     * @param field
     *            the field.
     */
    public void setField(final Field field);


    /**
     * Returns the getter.
     *
     * @return the getter.
     */
    public Method getGetter();


    /**
     * Sets the getter.
     *
     * @param getter
     *            the getter.
     */
    public void setGetter(final Method getter);


    /**
     * Returns the setter.
     *
     * @return the setter.
     */
    public Method getSetter();


    /**
     * Sets the setter.
     *
     * @param setter
     *            the setter.
     */
    public void setSetter(final Method setter);


    /**
     * Determines whether or not this list property is a primitive.
     *
     * @return {@code true} if it is a primitive; {@code false} otherwise.
     */
    public boolean isPrimitive();

    /**
     * Determines whether or not this list property is a value type.
     *
     * @return {@code true} if it is a value type; {@code false} otherwise.
     */
    public boolean isValueType();

    /**
     * Determines whether or not this list property is an element type.
     *
     * @return {@code true} if it is an element type; {@code false} otherwise.
     */
    public boolean isElementType();
}
