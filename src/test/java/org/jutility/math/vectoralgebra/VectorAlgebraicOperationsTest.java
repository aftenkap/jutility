package org.jutility.math.vectoralgebra;


//@formatter:off
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
//@formatter:on


import org.junit.Assert;
import org.junit.Test;
import org.jutility.common.datatype.util.NumberComparator;


/**
 * The {@code VectorAlgebraicOperationsTest} class provides unit tests for the
 * {@link VectorAlgebraicOperations} class.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class VectorAlgebraicOperationsTest {

    private static final double STEP_SIZE = 0.3333;

    /**
     * Test method for {@link VectorAlgebraicOperations#add(IVector4, IVector4)}
     * .
     */
    @Test
    public void testAddIVector4IVector4() {

        for (double v_x = -1d; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double v_z = -1d; NumberComparator
                        .smallerOrEqual(v_z, 1.0); v_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1.0); w_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1.0); w_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1.0); w_z += VectorAlgebraicOperationsTest.STEP_SIZE) {

                                final Vector4<Double> v = new Vector4<Double>(
                                        v_x, v_y, v_z, Double.class);
                                final Vector4<Double> w = new Vector4<Double>(
                                        w_x, w_y, w_z, Double.class);

                                final Vector4<Double> expected = new Vector4<Double>(
                                        v_x + w_x, v_y + w_y, v_z + w_z,
                                        Double.class);
                                Assert.assertEquals(expected,
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

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double v_z = -1d; NumberComparator
                        .smallerOrEqual(v_z, 1.0); v_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1.0); w_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1.0); w_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1.0); w_z += VectorAlgebraicOperationsTest.STEP_SIZE) {

                                final Point4<Double> v = new Point4<Double>(
                                        v_x, v_y, v_z, Double.class);
                                final Vector4<Double> w = new Vector4<Double>(
                                        w_x, w_y, w_z, Double.class);

                                final Point4<Double> expected = new Point4<Double>(
                                        v_x + w_x, v_y + w_y, v_z + w_z,
                                        Double.class);
                                Assert.assertEquals(expected,
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

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1d); v_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double v_y = -1.0; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double v_z = -1d; NumberComparator
                        .smallerOrEqual(v_z, 1.0); v_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1.0); w_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1.0); w_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1.0); w_z += VectorAlgebraicOperationsTest.STEP_SIZE) {

                                final Point4<Double> v = new Point4<Double>(
                                        v_x, v_y, v_z, Double.class);
                                final Vector4<Double> w = new Vector4<Double>(
                                        w_x, w_y, w_z, Double.class);

                                final Point4<Double> expected = new Point4<Double>(
                                        v_x + w_x, v_y + w_y, v_z + w_z,
                                        Double.class);
                                Assert.assertEquals(expected,
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

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double v_z = -1d; NumberComparator
                        .smallerOrEqual(v_z, 1.0); v_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1.0); w_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1.0); w_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1d); w_z += VectorAlgebraicOperationsTest.STEP_SIZE) {

                                final Point4<Double> v = new Point4<Double>(
                                        v_x, v_y, v_z, Double.class);
                                final Point4<Double> w = new Point4<Double>(
                                        w_x, w_y, w_z, Double.class);

                                final IVector4<Double> expected = new Vector4<Double>(
                                        v_x - w_x, v_y - w_y, v_z - w_z,
                                        Double.class);
                                Assert.assertEquals(expected,
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

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double v_y = -1.0; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double v_z = -1d; NumberComparator
                        .smallerOrEqual(v_z, 1.0); v_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1.0); w_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1.0); w_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1.0); w_z += VectorAlgebraicOperationsTest.STEP_SIZE) {

                                if (!NumberComparator.equals(v_z, w_z)) {
                                    final Point4<Double> v = new Point4<Double>(
                                            v_x, v_y, v_z, Double.class);
                                    final Vector4<Double> w = new Vector4<Double>(
                                            w_x, w_y, w_z, Double.class);


                                    final Point4<Double> expected = new Point4<Double>(
                                            v_x - w_x, v_y - w_y, v_z - w_z,
                                            Double.class);

                                    final Point4<Double> real = new Point4<Double>(
                                            VectorAlgebraicOperations.subtract(
                                                    v, w, Double.class));

                                    Assert.assertEquals(expected, real);
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

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double v_y = -1.0; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double v_z = -1.0; NumberComparator.smallerOrEqual(v_z,
                        1.0); v_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                    for (double s = -1.0; NumberComparator.smallerOrEqual(s,
                            1.0); s += VectorAlgebraicOperationsTest.STEP_SIZE) {

                        final Vector4<Double> v = new Vector4<Double>(v_x, v_y,
                                v_z, Double.class);

                        final Vector4<Double> expected = new Vector4<Double>(
                                v_x * s, v_y * s, v_z * s, Double.class);
                        Assert.assertEquals(expected,
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

        for (double v_x = -1.0; NumberComparator.smallerOrEqual(v_x, 1.0); v_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1.0); v_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double v_z = -1.0; NumberComparator.smallerOrEqual(v_z,
                        1.0); v_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                    for (double s = -1d; NumberComparator
                            .smallerOrEqual(s, 1.0); s += VectorAlgebraicOperationsTest.STEP_SIZE) {

                        final Vector4<Double> v = new Vector4<Double>(v_x, v_y,
                                v_z, Double.class);

                        final Vector4<Double> expected = new Vector4<Double>(
                                v_x * s, v_y * s, v_z * s, Double.class);
                        Assert.assertEquals(expected,
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

        for (double v_x = -1d; NumberComparator.smallerOrEqual(v_x, 1d); v_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1d); v_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double v_z = -1d; NumberComparator.smallerOrEqual(v_z, 1d); v_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                    for (double v_w = -1d; NumberComparator.smallerOrEqual(v_w,
                            1d); v_w += VectorAlgebraicOperationsTest.STEP_SIZE) {


                        final Tuple4<Double> v = new Tuple4<Double>(v_x, v_y,
                                v_z, v_w, Double.class);

                        for (double w_x = -1d; NumberComparator.smallerOrEqual(
                                w_x, 1d); w_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
                            for (double w_y = -1d; NumberComparator
                                    .smallerOrEqual(w_y, 1d); w_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                                for (double w_z = -1d; NumberComparator
                                        .smallerOrEqual(w_z, 1d); w_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                                    for (double w_w = -1d; NumberComparator
                                            .smallerOrEqual(w_w, 1d); w_w += VectorAlgebraicOperationsTest.STEP_SIZE) {

                                        final Tuple4<Double> i = new Tuple4<Double>(
                                                w_x, w_y, w_z, w_w,
                                                Double.class);
                                        final Tuple4<Double> j = new Tuple4<Double>(
                                                w_y, w_z, w_w, w_x,
                                                Double.class);
                                        final Tuple4<Double> k = new Tuple4<Double>(
                                                w_z, w_w, w_x, w_y,
                                                Double.class);
                                        final Tuple4<Double> s = new Tuple4<Double>(
                                                w_w, w_x, w_y, w_z,
                                                Double.class);

                                        final Matrix4<Double> matrix = new Matrix4<Double>(
                                                i, j, k, s, Double.class);


                                        final Tuple4<Double> expected = new Tuple4<Double>(
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
                                                        + (w_z * v_w),
                                                Double.class);
                                        Assert.assertEquals(expected,
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

        for (double v_x = -1d; NumberComparator.smallerOrEqual(v_x, 1d); v_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1d); v_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double v_z = -1d; NumberComparator.smallerOrEqual(v_z, 1d); v_z += VectorAlgebraicOperationsTest.STEP_SIZE) {


                    final Point4<Double> v = new Point4<Double>(v_x, v_y, v_z,
                            Double.class);

                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1d); w_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1d); w_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1d); w_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                                for (double w_w = -1d; NumberComparator
                                        .smallerOrEqual(w_w, 1d); w_w += VectorAlgebraicOperationsTest.STEP_SIZE) {

                                    final Vector4<Double> i = new Vector4<Double>(
                                            w_x, w_y, w_z, Double.class);
                                    final Vector4<Double> j = new Vector4<Double>(
                                            w_y, w_z, w_w, Double.class);
                                    final Vector4<Double> k = new Vector4<Double>(
                                            w_z, w_w, w_x, Double.class);
                                    final Point4<Double> s = new Point4<Double>(
                                            w_w, w_x, w_y, Double.class);

                                    final Matrix4<Double> matrix = new Matrix4<Double>(
                                            i, j, k, s, Double.class);


                                    final Point4<Double> expected = new Point4<Double>(
                                            (w_x * v_x) + (w_y * v_y)
                                                    + (w_z * v_z) + (w_w),
                                            (w_y * v_x) + (w_z * v_y)
                                                    + (w_w * v_z) + (w_x),
                                            (w_z * v_x) + (w_w * v_y)
                                                    + (w_x * v_z) + (w_y),
                                            Double.class);
                                    Assert.assertEquals(expected,
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

        for (double v_x = -1d; NumberComparator.smallerOrEqual(v_x, 1d); v_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double v_y = -1d; NumberComparator.smallerOrEqual(v_y, 1d); v_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double v_z = -1d; NumberComparator.smallerOrEqual(v_z, 1d); v_z += VectorAlgebraicOperationsTest.STEP_SIZE) {


                    final Vector4<Double> v = new Vector4<Double>(v_x, v_y,
                            v_z, Double.class);

                    for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x,
                            1d); w_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
                        for (double w_y = -1d; NumberComparator.smallerOrEqual(
                                w_y, 1d); w_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                            for (double w_z = -1d; NumberComparator
                                    .smallerOrEqual(w_z, 1d); w_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                                for (double w_w = -1d; NumberComparator
                                        .smallerOrEqual(w_w, 1d); w_w += VectorAlgebraicOperationsTest.STEP_SIZE) {

                                    final Vector4<Double> i = new Vector4<Double>(
                                            w_x, w_y, w_z, Double.class);
                                    final Vector4<Double> j = new Vector4<Double>(
                                            w_y, w_z, w_w, Double.class);
                                    final Vector4<Double> k = new Vector4<Double>(
                                            w_z, w_w, w_x, Double.class);
                                    final Point4<Double> s = new Point4<Double>(
                                            w_w, w_x, w_y, Double.class);

                                    final Matrix4<Double> matrix = new Matrix4<Double>(
                                            i, j, k, s, Double.class);


                                    final Vector4<Double> expected = new Vector4<Double>(
                                            (w_x * v_x) + (w_y * v_y)
                                                    + (w_z * v_z),
                                            (w_y * v_x) + (w_z * v_y)
                                                    + (w_w * v_z),
                                            (w_z * v_x) + (w_w * v_y)
                                                    + (w_x * v_z), Double.class);
                                    Assert.assertEquals(expected,
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

        for (double w_x = -1d; NumberComparator.smallerOrEqual(w_x, 1d); w_x += VectorAlgebraicOperationsTest.STEP_SIZE) {
            for (double w_y = -1d; NumberComparator.smallerOrEqual(w_y, 1d); w_y += VectorAlgebraicOperationsTest.STEP_SIZE) {
                for (double w_z = -1d; NumberComparator.smallerOrEqual(w_z, 1d); w_z += VectorAlgebraicOperationsTest.STEP_SIZE) {
                    for (double w_w = -1d; NumberComparator.smallerOrEqual(w_w,
                            1d); w_w += VectorAlgebraicOperationsTest.STEP_SIZE) {
                        for (double alpha = -1d; NumberComparator
                                .smallerOrEqual(alpha, 1d); alpha += VectorAlgebraicOperationsTest.STEP_SIZE) {

                            final Tuple4<Double> i1 = new Tuple4<Double>(w_x,
                                    w_y, w_z, w_w, Double.class);
                            final Tuple4<Double> j1 = new Tuple4<Double>(w_y,
                                    w_z, w_w, w_x, Double.class);
                            final Tuple4<Double> k1 = new Tuple4<Double>(w_z,
                                    w_w, w_x, w_y, Double.class);
                            final Tuple4<Double> s1 = new Tuple4<Double>(w_w,
                                    w_x, w_y, w_z, Double.class);

                            final Matrix4<Double> matrix1 = new Matrix4<Double>(
                                    i1, j1, k1, s1, Double.class);

                            final Tuple4<Double> i2 = new Tuple4<Double>(alpha
                                    * w_x, w_x, w_x, w_x, Double.class);
                            final Tuple4<Double> j2 = new Tuple4<Double>(w_y,
                                    alpha * w_y, w_y, w_y, Double.class);
                            final Tuple4<Double> k2 = new Tuple4<Double>(w_z,
                                    w_z, alpha * w_z, w_z, Double.class);
                            final Tuple4<Double> s2 = new Tuple4<Double>(w_w,
                                    w_w, w_w, alpha * w_w, Double.class);

                            final Matrix4<Double> matrix2 = new Matrix4<Double>(
                                    i2, j2, k2, s2, Double.class);

                            final Tuple4<Double> i3 = new Tuple4<Double>(w_x
                                    * ((alpha * w_x) + w_y + w_z + w_w), w_x
                                    * (w_x + (alpha * w_y) + w_z + w_w), w_x
                                    * (w_x + w_y + (alpha * w_z) + w_w), w_x
                                    * (w_x + w_y + w_z + (alpha * w_w)),
                                    Double.class);

                            final Tuple4<Double> j3 = new Tuple4<Double>(w_y
                                    * (w_x + (alpha * w_y) + w_z + w_w), w_y
                                    * (w_x + w_y + (alpha * w_z) + w_w), w_y
                                    * (w_x + w_y + w_z + (alpha * w_w)), w_y
                                    * ((alpha * w_x) + w_y + w_z + w_w),
                                    Double.class);

                            final Tuple4<Double> k3 = new Tuple4<Double>(w_z
                                    * (w_x + w_y + (alpha * w_z) + w_w), w_z
                                    * (w_x + w_y + w_z + (alpha * w_w)), w_z
                                    * ((alpha * w_x) + w_y + w_z + w_w), w_z
                                    * (w_x + (alpha * w_y) + w_z + w_w),
                                    Double.class);

                            final Tuple4<Double> s3 = new Tuple4<Double>(w_w
                                    * (w_x + w_y + w_z + (alpha * w_w)), w_w
                                    * ((alpha * w_x) + w_y + w_z + w_w), w_w
                                    * (w_x + (alpha * w_y) + w_z + w_w), w_w
                                    * (w_x + w_y + (alpha * w_z) + w_w),
                                    Double.class);


                            final Matrix4<Double> matrix3 = new Matrix4<Double>(
                                    i3, j3, k3, s3, Double.class);
                            Assert.assertEquals(matrix3,
                                    VectorAlgebraicOperations.multiply(matrix1,
                                            matrix2));
                        }
                    }
                }
            }
        }
    }
}
