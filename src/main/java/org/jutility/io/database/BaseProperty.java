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

import org.jutility.io.database.annotation.DBElementType;
import org.jutility.io.database.annotation.DBValueType;


/**
 *
 *
 * @author Peter J. Radics
 * @version 0.1
 */

public class BaseProperty
        implements IProperty {

    private final String   propertyName;
    private final Class<?> propertyType;

    private Field          field;
    private Method         getter;
    private Method         setter;

    // ----------------------------------------------------------
    @Override
    public String getPropertyName() {

        return this.propertyName;
    }

    // ----------------------------------------------------------
    @Override
    public Class<?> getPropertyType() {

        return this.propertyType;
    }



    @Override
    public Field getField() {

        return this.field;
    }

    @Override
    public void setField(Field field) {

        this.field = field;
    }


    @Override
    public Method getGetter() {

        return this.getter;
    }


    @Override
    public void setGetter(Method getter) {

        this.getter = getter;
    }


    @Override
    public Method getSetter() {

        return this.setter;
    }

    @Override
    public void setSetter(Method setter) {

        this.setter = setter;
    }

    @Override
    public boolean isPrimitive() {

        return !this.isElementType() && !this.isValueType();
    }

    @Override
    public boolean isValueType() {

        return getPropertyType().isAnnotationPresent(DBValueType.class);
    }

    @Override
    public boolean isElementType() {

        return getPropertyType().isAnnotationPresent(DBElementType.class);
    }


    /**
     * Creates a new instance of the {@link BaseProperty} class.
     *
     * @param propertyName
     *            the name of the property.
     * @param propertyType
     *            the type of the property.
     */
    public BaseProperty(String propertyName, Class<?> propertyType) {

        this.propertyName = propertyName;
        this.propertyType = propertyType;

        this.field = null;
        this.getter = null;
        this.setter = null;
    }
}
