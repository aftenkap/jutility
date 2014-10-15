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
 * The {@link Matrix4f} class provides a convenience implementation of the
 * {@link IMatrix4} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Matrix4f")
@XmlType(name = "Matrix4f")
public class Matrix4f
        extends Matrix4<Float> {

    /**
     * The unit matrix.
     */
    public static final Matrix4f UNIT_MATRIX = new Matrix4f(
                                                     Vector4f.I_UNIT_VECTOR,
                                                     Vector4f.J_UNIT_VECTOR,
                                                     Vector4f.K_UNIT_VECTOR,
                                                     Point4f.ORIGIN);

    /**
     * Creates a new instance of the {@link Matrix4f} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Matrix4f() {

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
    public Matrix4f(ITuple4<Float> i, ITuple4<Float> j, ITuple4<Float> k,
            ITuple4<Float> s) {

        super(i, j, k, s, Float.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param matrixToCopy
     *            the matrix to copy.
     */
    public Matrix4f(IMatrix4<Float> matrixToCopy) {

        super(matrixToCopy);
    }

}
