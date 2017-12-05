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


/**
 * The {@code IMatrix4} interface provides a contract for all classes
 * implementing 4x4 matrices using four-dimensional {@link ITuple4 Tuples}.
 * 
 * @param <T>
 *            the {@link Number} type of the {@code IMatrix4}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonSubTypes({ @JsonSubTypes.Type(value = Matrix4.class, name = "Matrix4") })
public interface IMatrix4<T extends Number> {

    /**
     * Returns the type of this matrix.
     * 
     * @return the type.
     */
    Class<? extends T> getType();

    /**
     * Getter for the first column of the matrix.
     * 
     * @return The first column of the matrix.
     */
    ITuple4<T> getI();


    /**
     * Getter for the second column of the matrix.
     * 
     * @return The second column of the matrix.
     */
    ITuple4<T> getJ();


    /**
     * Getter for the third column of the matrix.
     * 
     * @return The third column of the matrix.
     */
    ITuple4<T> getK();


    /**
     * Getter for the fourth column of the matrix.
     * 
     * @return The fourth column of the matrix.
     */
    ITuple4<T> getS();


    /**
     * Returns this matrix in a column-major array.
     * 
     * @return the matrix in a column-major array.
     */
    T[] toColumnMajorArray();


    /**
     * Returns this matrix in a row-major array.
     * 
     * @return the matrix in a row-major array.
     */
    T[] toRowMajorArray();

    /**
     * Returns the transpose matrix of this instance.
     * 
     * @return the transpose matrix.
     */
    IMatrix4<T> transpose();
}
