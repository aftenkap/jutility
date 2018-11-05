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
    String getPropertyName();

    /**
     * Returns the property type.
     *
     * @return the property type.
     */
    Class<?> getPropertyType();



    /**
     * Returns the field.
     *
     * @return the field.
     */
    Field getField();

    /**
     * Sets the field.
     *
     * @param field
     *            the field.
     */
    void setField(final Field field);


    /**
     * Returns the getter.
     *
     * @return the getter.
     */
    Method getGetter();


    /**
     * Sets the getter.
     *
     * @param getter
     *            the getter.
     */
    void setGetter(final Method getter);


    /**
     * Returns the setter.
     *
     * @return the setter.
     */
    Method getSetter();


    /**
     * Sets the setter.
     *
     * @param setter
     *            the setter.
     */
    void setSetter(final Method setter);


    /**
     * Determines whether or not this list property is a primitive.
     *
     * @return {@code true} if it is a primitive; {@code false} otherwise.
     */
    boolean isPrimitive();

    /**
     * Determines whether or not this list property is a value type.
     *
     * @return {@code true} if it is a value type; {@code false} otherwise.
     */
    boolean isValueType();

    /**
     * Determines whether or not this list property is an element type.
     *
     * @return {@code true} if it is an element type; {@code false} otherwise.
     */
    boolean isElementType();
}
