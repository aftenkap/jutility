package org.jutility.math.geometry;

/*
 * #%L
 * jutility-math
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


import org.jutility.math.vectorAlgebra.IPoint4;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@link ITriangle4} interface provides a contract for classes implementing
 * a triangle.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the triangle.
 * 
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
// @JsonSubTypes({ @JsonSubTypes.Type(value = Triangle4.class,
// name = "Triangle4") })
public interface ITriangle4<T extends Number> {

    /**
     * Returns the type of the triangle.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();

    /**
     * Returns the first point.
     * 
     * @return the first point.
     */
    public abstract IPoint4<T> getFirstPoint();


    /**
     * Returns the second point.
     * 
     * @return the second point.
     */
    public abstract IPoint4<T> getSecondPoint();


    /**
     * Returns the third point.
     * 
     * @return the third point.
     */
    public abstract IPoint4<T> getThirdPoint();

}