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


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Matrix4d} class provides a convenience implementation of the
 * {@link IMatrix4} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Matrix4d")
@XmlType(name = "Matrix4d")
public class Matrix4d
        extends Matrix4<Double> {

    /**
     * The unit matrix.
     */
    public static final Matrix4d UNIT_MATRIX = new Matrix4d(
                                                     Vector4d.I_UNIT_VECTOR,
                                                     Vector4d.J_UNIT_VECTOR,
                                                     Vector4d.K_UNIT_VECTOR,
                                                     Point4d.ORIGIN);

    /**
     * Creates a new instance of the {@link Matrix4d} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Matrix4d() {

        super();
    }

    /**
     * Creates a new instance of the {@link Matrix4} class with the provided
     * columns.
     * 
     * @param i
     *            The first column of the matrix.
     * @param j
     *            The second column of the matrix.
     * @param k
     *            The third column of the matrix.
     * @param s
     *            The fourth column of the matrix.
     */
    public Matrix4d(ITuple4<Double> i, ITuple4<Double> j, ITuple4<Double> k,
            ITuple4<Double> s) {

        super(i, j, k, s, Double.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param matrixToCopy
     *            the matrix to copy.
     */
    public Matrix4d(IMatrix4<Double> matrixToCopy) {

        super(matrixToCopy);
    }

}
