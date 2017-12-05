package org.jutility.math.geometry;


//@formatter:off
/*
* #%L
 * * jutility-math
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * *
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
//@formatter:on


import org.jutility.math.vectoralgebra.IPoint4;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@code ITriangle4} interface provides a contract for classes implementing
 * triangles in three-dimensional space based on {@link IPoint4 Points} in
 * homogeneous representation.
 * 
 * @param <T>
 *            the {@link Number} type of the {@code ITriangle4}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Triangle4.class, name = "Triangle4") })
public interface ITriangle4<T extends Number> {

    /**
     * Returns the type of the triangle.
     * 
     * @return the type.
     */
    Class<? extends T> getType();

    /**
     * Returns the first point.
     * 
     * @return the first point.
     */
    IPoint4<T> getFirstPoint();


    /**
     * Returns the second point.
     * 
     * @return the second point.
     */
    IPoint4<T> getSecondPoint();


    /**
     * Returns the third point.
     * 
     * @return the third point.
     */
    IPoint4<T> getThirdPoint();
}
