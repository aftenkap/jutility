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
 * The {@code Matrix4Test} class provides unit tests for the {@link Matrix4}
 * class.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@SuppressWarnings({ "ObjectEqualsNull", "ConstantConditions",
                    "EqualsBetweenInconvertibleTypes" })
public class Matrix4Test {

    /**
     * Test method for
     * {@link Matrix4#Matrix4(ITuple4, ITuple4, ITuple4, ITuple4, Class)}.
     *
     */
    @Test
    public void testConstructor() {

        try {

            final Matrix4<Float> testMatrix = new Matrix4<>(null, null, null,
                    null, Float.class);

            Assert.fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (final IllegalArgumentException e) {

            Assert.assertEquals(
                    "Cannot create a matrix with missing I component!",
                    e.getMessage());
        }
        try {

            final Matrix4<Float> testMatrix = new Matrix4<>(
                    Vector4.I_UNIT_VECTOR(Float.class), null, null, null,
                    Float.class);

            Assert.fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (final IllegalArgumentException e) {

            Assert.assertEquals(
                    "Cannot create a matrix with missing J component!",
                    e.getMessage());
        }
        try {

            final Matrix4<Float> testMatrix = new Matrix4<>(
                    Vector4.I_UNIT_VECTOR(Float.class),
                    Vector4.J_UNIT_VECTOR(Float.class), null, null, Float.class);

            Assert.fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (final IllegalArgumentException e) {

            Assert.assertEquals(
                    "Cannot create a matrix with missing K component!",
                    e.getMessage());
        }
        try {

            final Matrix4<Float> testMatrix = new Matrix4<>(
                    Vector4.I_UNIT_VECTOR(Float.class),
                    Vector4.J_UNIT_VECTOR(Float.class),
                    Vector4.K_UNIT_VECTOR(Float.class), null, Float.class);

            Assert.fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (final IllegalArgumentException e) {

            Assert.assertEquals(
                    "Cannot create a matrix with missing S component!",
                    e.getMessage());
        }
    }


    /**
     * Test method for {@link Matrix4#Matrix4(IMatrix4)}.
     */
    @Test
    public void testCopyConstructor() {

        try {

            final Matrix4<Float> testMatrix = new Matrix4<>(null);

            Assert.fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (final Exception e) {

            Assert.assertEquals(true, e instanceof NullPointerException);
        }
        final Tuple4<Float> i = new Tuple4<>(1, 5, 9, 13, Float.class);
        final Tuple4<Float> j = new Tuple4<>(2, 6, 10, 14, Float.class);
        final Tuple4<Float> k = new Tuple4<>(3, 7, 11, 15, Float.class);
        final Tuple4<Float> s = new Tuple4<>(4, 8, 12, 16, Float.class);

        final Matrix4<Float> testMatrix = new Matrix4<>(i, j, k, s, Float.class);

        final Matrix4<Float> otherTestMatrix = new Matrix4<>(testMatrix);

        Assert.assertEquals(testMatrix, otherTestMatrix);
    }


    /**
     * Test method for {@link Matrix4#toColumnMajorArray()}.
     */
    @Test
    public void testToColumnMajorArray() {

        final Tuple4<Float> i = new Tuple4<>(1, 2, 3, 4, Float.class);
        final Tuple4<Float> j = new Tuple4<>(5, 6, 7, 8, Float.class);
        final Tuple4<Float> k = new Tuple4<>(9, 10, 11, 12, Float.class);
        final Tuple4<Float> s = new Tuple4<>(13, 14, 15, 16, Float.class);

        final Matrix4<Float> testMatrix = new Matrix4<>(i, j, k, s, Float.class);

        final Float expecteds[] = { 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f,
                11f, 12f, 13f, 14f, 15f, 16f };

        this.assertArrayEquals(expecteds, testMatrix.toColumnMajorArray());
    }


    private void assertArrayEquals(final Float[] expected, final Float[] actual) {

        Assert.assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {

            Assert.assertTrue(NumberComparator.equals(expected[i], actual[i]));
        }
    }


    /**
     * Test method for {@link Matrix4#toRowMajorArray()}.
     */
    @Test
    public void testToRowMajorArray() {

        final Tuple4<Float> i = new Tuple4<>(1, 5, 9, 13, Float.class);
        final Tuple4<Float> j = new Tuple4<>(2, 6, 10, 14, Float.class);
        final Tuple4<Float> k = new Tuple4<>(3, 7, 11, 15, Float.class);
        final Tuple4<Float> s = new Tuple4<>(4, 8, 12, 16, Float.class);

        final Matrix4<Float> testMatrix = new Matrix4<>(i, j, k, s, Float.class);

        final Float expecteds[] = { 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f,
                11f, 12f, 13f, 14f, 15f, 16f };

        this.assertArrayEquals(expecteds, testMatrix.toRowMajorArray());
    }


    /**
     * Test method for {@link Matrix4#transpose()}.
     */
    @Test
    public void testTranspose() {

        final Tuple4<Float> i = new Tuple4<>(1, 2, 3, 4, Float.class);
        final Tuple4<Float> j = new Tuple4<>(5, 6, 7, 8, Float.class);
        final Tuple4<Float> k = new Tuple4<>(9, 10, 11, 12, Float.class);
        final Tuple4<Float> s = new Tuple4<>(13, 14, 15, 16, Float.class);

        final Tuple4<Float> i1 = new Tuple4<>(1, 5, 9, 13, Float.class);
        final Tuple4<Float> j1 = new Tuple4<>(2, 6, 10, 14, Float.class);
        final Tuple4<Float> k1 = new Tuple4<>(3, 7, 11, 15, Float.class);
        final Tuple4<Float> s1 = new Tuple4<>(4, 8, 12, 16, Float.class);

        final Matrix4<Float> testMatrix = new Matrix4<>(i, j, k, s, Float.class);
        final Matrix4<Float> expected = new Matrix4<>(i1, j1, k1, s1,
                Float.class);

        Assert.assertEquals(expected, testMatrix.transpose());

    }


    /**
     * Test method for {@link Matrix4#toString()}.
     */
    @Test
    public void testToString() {

        final Tuple4<Float> i = new Tuple4<>(1, 5, 9, 13, Float.class);
        final Tuple4<Float> j = new Tuple4<>(2, 6, 10, 14, Float.class);
        final Tuple4<Float> k = new Tuple4<>(3, 7, 11, 15, Float.class);
        final Tuple4<Float> s = new Tuple4<>(4, 8, 12, 16, Float.class);

        final Matrix4<Float> testMatrix = new Matrix4<>(i, j, k, s, Float.class);

        String returnValue = "\n|";
        final Float array[] = testMatrix.toRowMajorArray();

        for (int v = 0; v < 16; v++) {
            if ((v > 0) && ((v % 4) == 0)) {
                returnValue += "\t\t|\n|";
            }
            returnValue += "\t\t" + String.format("%.3f", array[v]);
        }

        returnValue += "\t\t|\n";

        Assert.assertEquals(returnValue, testMatrix.toString());
    }


    /**
     * Test method for {@link Matrix4#equals(Object)}.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    public void testEqualsObject() {

        for (float ux = -1; org.jutility.common.datatype.util.NumberComparator
                .smallerOrEqual(ux, 1f); ux += 0.1) {
            for (float uy = -1; org.jutility.common.datatype.util.NumberComparator
                    .smallerOrEqual(uy, 1f); uy += 0.1) {
                for (float uz = -1; org.jutility.common.datatype.util.NumberComparator
                        .smallerOrEqual(uz, 1f); uz += 0.1) {
                    for (float uw = -1; org.jutility.common.datatype.util.NumberComparator
                            .smallerOrEqual(uw, 1f); uw += 0.1) {

                        final Tuple4<Float> testTuple = new Tuple4<>(ux, uy, uz,
                                uw, Float.class);
                        final Tuple4<Float> testTuple2 = new Tuple4<>(ux + 1, uy, uz, uw, Float.class);
                        final Matrix4<Float> testMatrix = new Matrix4<>(
                                testTuple, testTuple, testTuple, testTuple,
                                Float.class);
                        final Matrix4<Float> notTestMatrix = new Matrix4<>(
                                testTuple2, testTuple, testTuple, testTuple,
                                Float.class);

                        Assert.assertEquals(testMatrix, testMatrix);
                        Assert.assertEquals(false,
                                testMatrix.equals(notTestMatrix));
                        Assert.assertEquals(false, testMatrix.equals(5));
                        Assert.assertEquals(false, testMatrix.equals(null));
                    }
                }
            }
        }
    }


    /**
     * Test method for {@link Matrix4#hashCode()}.
     */
    @Test
    public void testHashCode() {

        final Tuple4<Float> i = new Tuple4<>(1, 5, 9, 13, Float.class);
        final Tuple4<Float> j = new Tuple4<>(2, 6, 10, 14, Float.class);
        final Tuple4<Float> k = new Tuple4<>(3, 7, 11, 15, Float.class);
        final Tuple4<Float> s = new Tuple4<>(4, 8, 12, 16, Float.class);

        final Matrix4<Float> testMatrix = new Matrix4<>(i, j, k, s, Float.class);

        int hashCode = 23;
        
        hashCode += 29 *i.hashCode();
        hashCode += 31 * j.hashCode();
        hashCode += 37 * k.hashCode();
        hashCode += 39 * s.hashCode();


        Assert.assertEquals(hashCode, testMatrix.hashCode());
    }
}
