package org.jutility.math.vectorAlgebra;

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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The generic {@link IVector4} interface provides a contract for all classes
 * implementing three-dimensional vectors in homogeneous coordinate
 * representation.
 * <p/>
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the point.
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "tupleType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Vector4.class, name = "Vector4")
})
public interface IVector4<T extends Number>
        extends ITuple4<T> {

    /**
     * Returns the length of the vector (its Euclidean norm).
     * 
     * @return the length of the vector.
     */
    public abstract T length();

    /**
     * Returns the normalized version of this vector.
     * 
     * @return the normalized vector.
     */
    public abstract IVector4<T> normalizedVector();

    /**
     * Calculates the dot product of the vector with the provided vector.
     * 
     * @param rhs
     *            the provided vector.
     * @return the dot product.
     */
    public abstract T dotProduct(IVector4<T> rhs);


    /**
     * Calculates the cross product of the vectors with the provided vector.
     * 
     * @param rhs
     *            the provided vector.
     * @return the cross product (a vector orthogonal to both vectors).
     */
    public abstract IVector4<T> crossProduct(IVector4<T> rhs);
}