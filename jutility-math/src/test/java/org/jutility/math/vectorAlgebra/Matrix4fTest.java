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


import static org.junit.Assert.*;

import org.junit.Test;
import org.jutility.common.datatype.util.NumberConstants;
import org.jutility.math.vectorAlgebra.Matrix4f;
import org.jutility.math.vectorAlgebra.Tuple4f;
import org.jutility.math.vectorAlgebra.Vector4f;


/**
 * Test harness for the Matrix4f class.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class Matrix4fTest {

    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4f#Matrix4f(org.jutility.math.vectorAlgebra.Tuple4f, org.jutility.math.vectorAlgebra.Tuple4f, org.jutility.math.vectorAlgebra.Tuple4f, org.jutility.math.vectorAlgebra.Tuple4f)}
     * .
     */
    @Test
    public void testMatrix4fTuple4fTuple4fTuple4fTuple4f() {

        try {

            Matrix4f testMatrix = new Matrix4f(null, null, null, null);

            fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (IllegalArgumentException e) {

            assertEquals("Cannot create a matrix with missing I component!",
                    e.getMessage());
        }
        try {

            Matrix4f testMatrix = new Matrix4f(Vector4f.I_UNIT_VECTOR, null,
                    null, null);

            fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (IllegalArgumentException e) {

            assertEquals("Cannot create a matrix with missing J component!",
                    e.getMessage());
        }
        try {

            Matrix4f testMatrix = new Matrix4f(Vector4f.I_UNIT_VECTOR,
                    Vector4f.I_UNIT_VECTOR, null, null);

            fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (IllegalArgumentException e) {

            assertEquals("Cannot create a matrix with missing K component!",
                    e.getMessage());
        }
        try {

            Matrix4f testMatrix = new Matrix4f(Vector4f.I_UNIT_VECTOR,
                    Vector4f.I_UNIT_VECTOR, Vector4f.I_UNIT_VECTOR, null);

            fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (IllegalArgumentException e) {

            assertEquals("Cannot create a matrix with missing S component!",
                    e.getMessage());
        }
    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4f#Matrix4f(org.jutility.math.vectorAlgebra.Matrix4f)}
     * .
     */
    @Test
    public void testMatrix4fMatrix4f() {

        try {

            Matrix4f testMatrix = new Matrix4f(null);

            fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (Exception e) {

            assertEquals(true, e instanceof NullPointerException);
        }
        Tuple4f i = new Tuple4f(1, 5, 9, 13);
        Tuple4f j = new Tuple4f(2, 6, 10, 14);
        Tuple4f k = new Tuple4f(3, 7, 11, 15);
        Tuple4f s = new Tuple4f(4, 8, 12, 16);

        Matrix4f testMatrix = new Matrix4f(i, j, k, s);

        Matrix4f otherTestMatrix = new Matrix4f(testMatrix);

        assertEquals(testMatrix, otherTestMatrix);
    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4f#toColumnMajorArray()}.
     */
    @Test
    public void testToColumnMajorArray() {

        Tuple4f i = new Tuple4f(1, 2, 3, 4);
        Tuple4f j = new Tuple4f(5, 6, 7, 8);
        Tuple4f k = new Tuple4f(9, 10, 11, 12);
        Tuple4f s = new Tuple4f(13, 14, 15, 16);

        Matrix4f testMatrix = new Matrix4f(i, j, k, s);

        Float expecteds[] = { 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f,
                12f, 13f, 14f, 15f, 16f };

        assertArrayEquals(expecteds, testMatrix.toColumnMajorArray(),
                NumberConstants.DELTA_F);


    }


    private void assertArrayEquals(Float[] expected, Float[] actual, float delta) {

        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertTrue(org.jutility.common.datatype.util.NumberComparator
                    .equals(expected[i], actual[i]));
        }

    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4f#toRowMajorArray()}.
     */
    @Test
    public void testToRowMajorArray() {

        Tuple4f i = new Tuple4f(1, 5, 9, 13);
        Tuple4f j = new Tuple4f(2, 6, 10, 14);
        Tuple4f k = new Tuple4f(3, 7, 11, 15);
        Tuple4f s = new Tuple4f(4, 8, 12, 16);

        Matrix4f testMatrix = new Matrix4f(i, j, k, s);

        Float expecteds[] = { 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f,
                12f, 13f, 14f, 15f, 16f };

        assertArrayEquals(expecteds, testMatrix.toRowMajorArray(),
                NumberConstants.DELTA_F);
    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4f#transpose()}.
     */
    @Test
    public void testTranspose() {

        Tuple4f i = new Tuple4f(1, 2, 3, 4);
        Tuple4f j = new Tuple4f(5, 6, 7, 8);
        Tuple4f k = new Tuple4f(9, 10, 11, 12);
        Tuple4f s = new Tuple4f(13, 14, 15, 16);

        Tuple4f i1 = new Tuple4f(1, 5, 9, 13);
        Tuple4f j1 = new Tuple4f(2, 6, 10, 14);
        Tuple4f k1 = new Tuple4f(3, 7, 11, 15);
        Tuple4f s1 = new Tuple4f(4, 8, 12, 16);

        Matrix4f testMatrix = new Matrix4f(i, j, k, s);
        Matrix4f expected = new Matrix4f(i1, j1, k1, s1);

        assertEquals(expected, testMatrix.transpose());

    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4f#toString()}.
     */
    @Test
    public void testToString() {

        Tuple4f i = new Tuple4f(1, 5, 9, 13);
        Tuple4f j = new Tuple4f(2, 6, 10, 14);
        Tuple4f k = new Tuple4f(3, 7, 11, 15);
        Tuple4f s = new Tuple4f(4, 8, 12, 16);

        Matrix4f testMatrix = new Matrix4f(i, j, k, s);

        String returnValue = "\n|";
        Float array[] = testMatrix.toRowMajorArray();

        for (int v = 0; v < 16; v++) {
            if (v > 0 && v % 4 == 0) {
                returnValue += "\t\t|\n|";
            }
            returnValue += "\t\t" + String.format("%.3f", array[v]);
        }

        returnValue += "\t\t|\n";

        assertEquals(returnValue, testMatrix.toString());
    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4f#equals(java.lang.Object)}
     * .
     */
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

                        Tuple4f testTuple = new Tuple4f(ux, uy, uz, uw);
                        Tuple4f testTuple2 = new Tuple4f(ux + 1, uy, uz, uw);
                        Matrix4f testMatrix = new Matrix4f(testTuple,
                                testTuple, testTuple, testTuple);
                        Matrix4f notTestMatrix = new Matrix4f(testTuple2,
                                testTuple, testTuple, testTuple);

                        assertEquals(testMatrix, testMatrix);
                        assertEquals(false, testMatrix.equals(notTestMatrix));
                        assertEquals(false, testMatrix.equals(5));
                        assertEquals(false, testMatrix.equals(null));
                    }
                }
            }
        }
    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4f#hashCode()}.
     */
    @Test
    public void testHashCode() {

        Tuple4f i = new Tuple4f(1, 5, 9, 13);
        Tuple4f j = new Tuple4f(2, 6, 10, 14);
        Tuple4f k = new Tuple4f(3, 7, 11, 15);
        Tuple4f s = new Tuple4f(4, 8, 12, 16);

        Matrix4f testMatrix = new Matrix4f(i, j, k, s);

        int hashCode = 23;
        float[] thisMatrix = { 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15, 4, 8,
                12, 16 };

        int ihashCode = 7;
        for (int index = 0; index < 4; index++) {

            ihashCode += 13 * new Float(thisMatrix[index]).hashCode();
        }
        ihashCode *= 29;


        int jhashCode = 7;
        for (int index = 4; index < 8; index++) {

            jhashCode += 13 * new Float(thisMatrix[index]).hashCode();
        }
        jhashCode *= 31;


        int khashCode = 7;
        for (int index = 8; index < 12; index++) {

            khashCode += 13 * new Float(thisMatrix[index]).hashCode();
        }
        khashCode *= 37;


        int shashCode = 7;

        for (int index = 12; index < 16; index++) {

            shashCode += 13 * new Float(thisMatrix[index]).hashCode();
        }
        shashCode *= 39;


        hashCode += ihashCode + jhashCode + khashCode + shashCode;

        assertEquals(hashCode, testMatrix.hashCode());
    }


}
