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


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.math.vectorAlgebra.IMatrix4;
import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.ITuple4;
import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Matrix4d;
import org.jutility.math.vectorAlgebra.Point4d;
import org.jutility.math.vectorAlgebra.Tuple4d;
import org.jutility.math.vectorAlgebra.Vector4d;
import org.jutility.math.vectorAlgebra.VectorAlgebraicOperations;


/**
 * Test harness for the NumberComparator class.
 * 
 * @author Peter J. Radics
 * @version STEP_SIZE
 */
public class VectorAlgebraicOperationsTest
        extends VectorAlgebraicOperations {

    private static double STEP_SIZE = 0.3333;

    /**
     * Test method for {@link VectorAlgebraicOperations#add(IVector4, IVector4)}
     * .
     */
    @Test
    public void testAddIVector4IVector4() {

        for (double v_x = -1d; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += STEP_SIZE) {
                for (double v_z = -1d; NumberComparator
                        .smallerOrEqual(v_z, 1.0); v_z += STEP_SIZE) {
                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1.0); w_x += STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1.0); w_y += STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1.0); w_z += STEP_SIZE) {

                                Vector4d v = new Vector4d(v_x, v_y, v_z);
                                Vector4d w = new Vector4d(w_x, w_y, w_z);

                                Vector4d expected = new Vector4d(v_x + w_x, v_y
                                        + w_y, v_z + w_z);
                                assertEquals(expected,
                                        VectorAlgebraicOperations.add(v, w));
                            }
                        }
                    }
                }
            }
        }

    }


    /**
     * Test method for {@link VectorAlgebraicOperations#add(IPoint4, IVector4)}
     * .
     */
    @Test
    public void testAddIPoint4IVector4() {

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += STEP_SIZE) {
                for (double v_z = -1d; NumberComparator
                        .smallerOrEqual(v_z, 1.0); v_z += STEP_SIZE) {
                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1.0); w_x += STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1.0); w_y += STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1.0); w_z += STEP_SIZE) {

                                Point4d v = new Point4d(v_x, v_y, v_z);
                                Vector4d w = new Vector4d(w_x, w_y, w_z);

                                Point4d expected = new Point4d(v_x + w_x, v_y
                                        + w_y, v_z + w_z);
                                assertEquals(expected,
                                        VectorAlgebraicOperations.add(v, w));
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * Test method for {@link VectorAlgebraicOperations#add(IVector4, IPoint4)}
     * .
     */
    @Test
    public void testAddIVector4IPoint4() {

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1d); v_x += STEP_SIZE) {
            for (double v_y = -1.0; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += STEP_SIZE) {
                for (double v_z = -1d; NumberComparator
                        .smallerOrEqual(v_z, 1.0); v_z += STEP_SIZE) {
                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1.0); w_x += STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1.0); w_y += STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1.0); w_z += STEP_SIZE) {

                                Point4d v = new Point4d(v_x, v_y, v_z);
                                Vector4d w = new Vector4d(w_x, w_y, w_z);

                                Point4d expected = new Point4d(v_x + w_x, v_y
                                        + w_y, v_z + w_z);
                                assertEquals(expected,
                                        VectorAlgebraicOperations.add(w, v));
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * Test method for
     * {@link VectorAlgebraicOperations#subtract(IPoint4, IPoint4)} .
     */
    @Test
    public void testSubtractIPoint4IPoint4() {

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += STEP_SIZE) {
                for (double v_z = -1d; NumberComparator
                        .smallerOrEqual(v_z, 1.0); v_z += STEP_SIZE) {
                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1.0); w_x += STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1.0); w_y += STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1d); w_z += STEP_SIZE) {

                                Point4d v = new Point4d(v_x, v_y, v_z);
                                Point4d w = new Point4d(w_x, w_y, w_z);

                                IVector4<Double> expected = new Vector4d(v_x
                                        - w_x, v_y - w_y, v_z - w_z);
                                assertEquals(expected,
                                        VectorAlgebraicOperations
                                                .subtract(v, w));
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * Test method for
     * {@link VectorAlgebraicOperations#subtract(IPoint4, IVector4)} .
     */
    @Test
    public void testSubtractIPoint4IVector4() {

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += STEP_SIZE) {
            for (double v_y = -1.0; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += STEP_SIZE) {
                for (double v_z = -1d; NumberComparator
                        .smallerOrEqual(v_z, 1.0); v_z += STEP_SIZE) {
                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1.0); w_x += STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1.0); w_y += STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1.0); w_z += STEP_SIZE) {

                                if (!NumberComparator.equals(v_z, w_z)) {
                                    Point4d v = new Point4d(v_x, v_y, v_z);
                                    Vector4d w = new Vector4d(w_x, w_y, w_z);

                                    // System.out.println("Operation: " + v
                                    // + " - " + w);

                                    Point4d expected = new Point4d(v_x - w_x,
                                            v_y - w_y, v_z - w_z);

                                    // System.out.println("Expected Result: "
                                    // + expected);
                                    Point4d real = new Point4d(
                                            VectorAlgebraicOperations.subtract(
                                                    v, w, Double.class));

                                    // System.out.println(expected);
                                    // System.out.println(real);

                                    assertEquals(expected, real);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Test method for
     * {@link VectorAlgebraicOperations#multiply(IVector4, Number)} .
     */
    @Test
    public void testMultiplyIVector4Number() {

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += STEP_SIZE) {
            for (double v_y = -1.0; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += STEP_SIZE) {
                for (double v_z = -1.0; NumberComparator.smallerOrEqual(v_z,
                        1.0); v_z += STEP_SIZE) {
                    for (double s = -1.0; NumberComparator.smallerOrEqual(s,
                            1.0); s += STEP_SIZE) {

                        Vector4d v = new Vector4d(v_x, v_y, v_z);

                        Vector4d expected = new Vector4d(v_x * s, v_y * s, v_z
                                * s);
                        assertEquals(expected,
                                VectorAlgebraicOperations.multiply(v, s));
                    }
                }
            }
        }
    }


    /**
     * Test method for
     * {@link VectorAlgebraicOperations#multiply(Number, IVector4)} .
     */
    @Test
    public void testMultiplyNumberIVector4() {

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += STEP_SIZE) {
                for (double v_z = -1.0; NumberComparator.smallerOrEqual(v_z,
                        1.0); v_z += STEP_SIZE) {
                    for (double s = -1d; NumberComparator
                            .smallerOrEqual(s, 1.0); s += STEP_SIZE) {

                        Vector4d v = new Vector4d(v_x, v_y, v_z);

                        Vector4d expected = new Vector4d(v_x * s, v_y * s, v_z
                                * s);
                        assertEquals(expected,
                                VectorAlgebraicOperations.multiply(s, v));
                    }
                }
            }
        }
    }


    /**
     * Test method for
     * {@link VectorAlgebraicOperations#multiply(IMatrix4, ITuple4)} .
     */
    @Test
    public void testMultiplyIMatrix4ITuple4() {

        for (double v_x = -1d; NumberComparator.smallerOrEqual(v_x, 1d); v_x += STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1d); v_y += STEP_SIZE) {
                for (double v_z = -1d; NumberComparator.smallerOrEqual(v_z, 1d); v_z += STEP_SIZE) {
                    for (double v_w = -1d; NumberComparator.smallerOrEqual(v_w,
                            1d); v_w += STEP_SIZE) {


                        Tuple4d v = new Tuple4d(v_x, v_y, v_z, v_w);

                        for (double w_x = -1d; NumberComparator.smallerOrEqual(
                                w_x, 1d); w_x += STEP_SIZE) {
                            for (double w_y = -1d; NumberComparator
                                    .smallerOrEqual(w_y, 1d); w_y += STEP_SIZE) {
                                for (double w_z = -1d; NumberComparator
                                        .smallerOrEqual(w_z, 1d); w_z += STEP_SIZE) {
                                    for (double w_w = -1d; NumberComparator
                                            .smallerOrEqual(w_w, 1d); w_w += STEP_SIZE) {

                                        Tuple4d i = new Tuple4d(w_x, w_y, w_z,
                                                w_w);
                                        Tuple4d j = new Tuple4d(w_y, w_z, w_w,
                                                w_x);
                                        Tuple4d k = new Tuple4d(w_z, w_w, w_x,
                                                w_y);
                                        Tuple4d s = new Tuple4d(w_w, w_x, w_y,
                                                w_z);

                                        Matrix4d matrix = new Matrix4d(i, j, k,
                                                s);


                                        Tuple4d expected = new Tuple4d(
                                                (w_x * v_x) + (w_y * v_y)
                                                        + (w_z * v_z)
                                                        + (w_w * v_w),
                                                (w_y * v_x) + (w_z * v_y)
                                                        + (w_w * v_z)
                                                        + (w_x * v_w),
                                                (w_z * v_x) + (w_w * v_y)
                                                        + (w_x * v_z)
                                                        + (w_y * v_w),
                                                (w_w * v_x) + (w_x * v_y)
                                                        + (w_y * v_z)
                                                        + (w_z * v_w));
                                        assertEquals(expected,
                                                VectorAlgebraicOperations
                                                        .multiply(matrix, v));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * Test method for
     * {@link VectorAlgebraicOperations#multiply(IMatrix4, IPoint4)} .
     */
    @Test
    public void testMultiplyIMatrix4IPoint4() {

        for (double v_x = -1d; NumberComparator.smallerOrEqual(v_x, 1d); v_x += STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1d); v_y += STEP_SIZE) {
                for (double v_z = -1d; NumberComparator.smallerOrEqual(v_z, 1d); v_z += STEP_SIZE) {


                    Point4d v = new Point4d(v_x, v_y, v_z);

                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1d); w_x += STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1d); w_y += STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1d); w_z += STEP_SIZE) {
                                for (double w_w = -1d; NumberComparator
                                        .smallerOrEqual(w_w, 1d); w_w += STEP_SIZE) {

                                    Vector4d i = new Vector4d(w_x, w_y, w_z);
                                    Vector4d j = new Vector4d(w_y, w_z, w_w);
                                    Vector4d k = new Vector4d(w_z, w_w, w_x);
                                    Point4d s = new Point4d(w_w, w_x, w_y);

                                    Matrix4d matrix = new Matrix4d(i, j, k, s);


                                    Point4d expected = new Point4d(
                                            (w_x * v_x) + (w_y * v_y)
                                                    + (w_z * v_z) + (w_w),
                                            (w_y * v_x) + (w_z * v_y)
                                                    + (w_w * v_z) + (w_x),
                                            (w_z * v_x) + (w_w * v_y)
                                                    + (w_x * v_z) + (w_y));
                                    assertEquals(expected,
                                            VectorAlgebraicOperations.multiply(
                                                    matrix, v));
                                }
                            }
                        }
                    }

                }
            }
        }
    }


    /**
     * Test method for
     * {@link VectorAlgebraicOperations#multiply(IMatrix4, IVector4)} .
     */
    @Test
    public void testMultiplyIMatrix4IVector4() {

        for (double v_x = -1d; NumberComparator.smallerOrEqual(v_x, 1d); v_x += STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1d); v_y += STEP_SIZE) {
                for (double v_z = -1d; NumberComparator.smallerOrEqual(v_z, 1d); v_z += STEP_SIZE) {


                    Vector4d v = new Vector4d(v_x, v_y, v_z);

                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1d); w_x += STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1d); w_y += STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1d); w_z += STEP_SIZE) {
                                for (double w_w = -1d; NumberComparator
                                        .smallerOrEqual(w_w, 1d); w_w += STEP_SIZE) {

                                    Vector4d i = new Vector4d(w_x, w_y, w_z);
                                    Vector4d j = new Vector4d(w_y, w_z, w_w);
                                    Vector4d k = new Vector4d(w_z, w_w, w_x);
                                    Point4d s = new Point4d(w_w, w_x, w_y);

                                    Matrix4d matrix = new Matrix4d(i, j, k, s);


                                    Vector4d expected = new Vector4d(
                                            (w_x * v_x) + (w_y * v_y)
                                                    + (w_z * v_z),
                                            (w_y * v_x) + (w_z * v_y)
                                                    + (w_w * v_z), (w_z * v_x)
                                                    + (w_w * v_y) + (w_x * v_z));
                                    assertEquals(expected,
                                            VectorAlgebraicOperations.multiply(
                                                    matrix, v));
                                }
                            }
                        }
                    }

                }
            }
        }
    }


    /**
     * Test method for
     * {@link VectorAlgebraicOperations#multiply(IMatrix4, IMatrix4)} .
     */
    @Test
    public void testMultiplyIMatrix4IMatrix4() {

        for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x, 1d); w_x += STEP_SIZE) {
            for (double w_y = -1d; NumberComparator.smallerOrEqual(w_y, 1d); w_y += STEP_SIZE) {
                for (double w_z = -1d; NumberComparator.smallerOrEqual(w_z, 1d); w_z += STEP_SIZE) {
                    for (double w_w = -1d; NumberComparator.smallerOrEqual(w_w,
                            1d); w_w += STEP_SIZE) {
                        for (double alpha = -1d; NumberComparator
                                .smallerOrEqual(alpha, 1d); alpha += STEP_SIZE) {

                            Tuple4d i1 = new Tuple4d(w_x, w_y, w_z, w_w);
                            Tuple4d j1 = new Tuple4d(w_y, w_z, w_w, w_x);
                            Tuple4d k1 = new Tuple4d(w_z, w_w, w_x, w_y);
                            Tuple4d s1 = new Tuple4d(w_w, w_x, w_y, w_z);

                            Matrix4d matrix1 = new Matrix4d(i1, j1, k1, s1);

                            Tuple4d i2 = new Tuple4d(alpha * w_x, w_x, w_x, w_x);
                            Tuple4d j2 = new Tuple4d(w_y, alpha * w_y, w_y, w_y);
                            Tuple4d k2 = new Tuple4d(w_z, w_z, alpha * w_z, w_z);
                            Tuple4d s2 = new Tuple4d(w_w, w_w, w_w, alpha * w_w);

                            Matrix4d matrix2 = new Matrix4d(i2, j2, k2, s2);

                            Tuple4d i3 = new Tuple4d(w_x
                                    * (alpha * w_x + w_y + w_z + w_w), w_x
                                    * (w_x + alpha * w_y + w_z + w_w), w_x
                                    * (w_x + w_y + alpha * w_z + w_w), w_x
                                    * (w_x + w_y + w_z + alpha * w_w));

                            Tuple4d j3 = new Tuple4d(w_y
                                    * (w_x + alpha * w_y + w_z + w_w), w_y
                                    * (w_x + w_y + alpha * w_z + w_w), w_y
                                    * (w_x + w_y + w_z + alpha * w_w), w_y
                                    * (alpha * w_x + w_y + w_z + w_w));

                            Tuple4d k3 = new Tuple4d(w_z
                                    * (w_x + w_y + alpha * w_z + w_w), w_z
                                    * (w_x + w_y + w_z + alpha * w_w), w_z
                                    * (alpha * w_x + w_y + w_z + w_w), w_z
                                    * (w_x + alpha * w_y + w_z + w_w));

                            Tuple4d s3 = new Tuple4d(w_w
                                    * (w_x + w_y + w_z + alpha * w_w), w_w
                                    * (alpha * w_x + w_y + w_z + w_w), w_w
                                    * (w_x + alpha * w_y + w_z + w_w), w_w
                                    * (w_x + w_y + alpha * w_z + w_w));

                            // System.out.println ("w_x:" + w_x + ", w_y: " +
                            // w_y + ", w_z: " + w_z + ", w_w: " + w_w +
                            // ", alpha: " + alpha);

                            Matrix4d matrix3 = new Matrix4d(i3, j3, k3, s3);
                            // System.out.println ("Matrices: ");
                            // System.out.println (matrix1);
                            // System.out.println (matrix2);
                            // System.out.println (matrix3);
                            assertEquals(matrix3,
                                    VectorAlgebraicOperations.multiply(matrix1,
                                            matrix2));
                        }
                    }
                }
            }
        }
    }
}
