package org.jutility.math.vectoralgebra;


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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The generic {@code IVector4} interface provides a contract for all classes
 * implementing three-dimensional vectors in homogeneous coordinate
 * representation.
 * 
 * @param <T>
 *            the {@link Number} type of the {@code IVector4}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "tupleType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Vector2.class, name = "Vector2") })
public interface IVector2<T extends Number>
        extends ITuple2<T> {

    /**
     * Returns the length of the vector (its Euclidean norm).
     * 
     * @return the length of the vector.
     */
    T length();


    /**
     * Returns the normalized version of this vector.
     * 
     * @return the normalized vector.
     */
    IVector2<T> normalizedVector();


    /**
     * Calculates the dot product of the vector with the provided vector.
     * 
     * @param rhs
     *            the provided vector.
     * @return the dot product.
     */
    T dotProduct(final IVector2<T> rhs);


    /**
     * Returns a vector counterclockwise perpendicular to this vector.
     * 
     * @return a vector counterclockwise perpendicular to this vector.
     */
    IVector2<T> perpendicularVector();
}
