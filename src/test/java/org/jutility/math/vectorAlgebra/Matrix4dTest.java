package org.jutility.math.vectorAlgebra;


import static org.junit.Assert.*;

import org.junit.Test;
import org.jutility.common.datatype.util.NumberConstants;
import org.jutility.math.vectorAlgebra.Matrix4d;
import org.jutility.math.vectorAlgebra.Tuple4d;
import org.jutility.math.vectorAlgebra.Vector4d;


/**
 * Test harness for the Matrix4d class.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class Matrix4dTest {

    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4d#Matrix4d(org.jutility.math.vectorAlgebra.Tuple4d, org.jutility.math.vectorAlgebra.Tuple4d, org.jutility.math.vectorAlgebra.Tuple4d, org.jutility.math.vectorAlgebra.Tuple4d)}
     * .
     */
    @Test
    public void testMatrix4dTuple4dTuple4dTuple4dTuple4d() {

        try {

            Matrix4d testMatrix = new Matrix4d(null, null, null, null);

            fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (IllegalArgumentException e) {

            assertEquals("Cannot create a matrix with missing I component!",
                    e.getMessage());
        }
        try {

            Matrix4d testMatrix = new Matrix4d(Vector4d.I_UNIT_VECTOR, null,
                    null, null);

            fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (IllegalArgumentException e) {

            assertEquals("Cannot create a matrix with missing J component!",
                    e.getMessage());
        }
        try {

            Matrix4d testMatrix = new Matrix4d(Vector4d.I_UNIT_VECTOR,
                    Vector4d.I_UNIT_VECTOR, null, null);

            fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (IllegalArgumentException e) {

            assertEquals("Cannot create a matrix with missing K component!",
                    e.getMessage());
        }
        try {

            Matrix4d testMatrix = new Matrix4d(Vector4d.I_UNIT_VECTOR,
                    Vector4d.I_UNIT_VECTOR, Vector4d.I_UNIT_VECTOR, null);

            fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (IllegalArgumentException e) {

            assertEquals("Cannot create a matrix with missing S component!",
                    e.getMessage());
        }
    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4d#Matrix4d(org.jutility.math.vectorAlgebra.IMatrix4)}
     * .
     */
    @Test
    public void testMatrix4dMatrix4d() {

        try {

            Matrix4d testMatrix = new Matrix4d(null);

            fail("Shouldn't be able to create matrix: " + testMatrix);
        }
        catch (Exception e) {

            assertEquals(true, e instanceof NullPointerException);
        }
        Tuple4d i = new Tuple4d(1, 5, 9, 13);
        Tuple4d j = new Tuple4d(2, 6, 10, 14);
        Tuple4d k = new Tuple4d(3, 7, 11, 15);
        Tuple4d s = new Tuple4d(4, 8, 12, 16);

        Matrix4d testMatrix = new Matrix4d(i, j, k, s);

        Matrix4d otherTestMatrix = new Matrix4d(testMatrix);

        assertEquals(testMatrix, otherTestMatrix);
    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4d#toColumnMajorArray()}.
     */
    @Test
    public void testToColumnMajorArray() {

        Tuple4d i = new Tuple4d(1, 2, 3, 4);
        Tuple4d j = new Tuple4d(5, 6, 7, 8);
        Tuple4d k = new Tuple4d(9, 10, 11, 12);
        Tuple4d s = new Tuple4d(13, 14, 15, 16);

        Matrix4d testMatrix = new Matrix4d(i, j, k, s);

        Double expecteds[] = { 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d,
                12d, 13d, 14d, 15d, 16d };


        assertArrayEquals(expecteds, testMatrix.toColumnMajorArray(),
                NumberConstants.DELTA_D);


    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4d#toRowMajorArray()}.
     */
    @Test
    public void testToRowMajorArray() {

        Tuple4d i = new Tuple4d(1, 5, 9, 13);
        Tuple4d j = new Tuple4d(2, 6, 10, 14);
        Tuple4d k = new Tuple4d(3, 7, 11, 15);
        Tuple4d s = new Tuple4d(4, 8, 12, 16);

        Matrix4d testMatrix = new Matrix4d(i, j, k, s);

        Double expecteds[] = { 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d,
                12d, 13d, 14d, 15d, 16d };

        assertArrayEquals(expecteds, testMatrix.toRowMajorArray(),
                NumberConstants.DELTA_F);
    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4d#transpose()}.
     */
    @Test
    public void testTranspose() {

        Tuple4d i = new Tuple4d(1, 2, 3, 4);
        Tuple4d j = new Tuple4d(5, 6, 7, 8);
        Tuple4d k = new Tuple4d(9, 10, 11, 12);
        Tuple4d s = new Tuple4d(13, 14, 15, 16);

        Tuple4d i1 = new Tuple4d(1, 5, 9, 13);
        Tuple4d j1 = new Tuple4d(2, 6, 10, 14);
        Tuple4d k1 = new Tuple4d(3, 7, 11, 15);
        Tuple4d s1 = new Tuple4d(4, 8, 12, 16);

        Matrix4d testMatrix = new Matrix4d(i, j, k, s);
        Matrix4d expected = new Matrix4d(i1, j1, k1, s1);

        assertEquals(expected, testMatrix.transpose());

    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Matrix4d#toString()}.
     */
    @Test
    public void testToString() {

        Tuple4d i = new Tuple4d(1, 5, 9, 13);
        Tuple4d j = new Tuple4d(2, 6, 10, 14);
        Tuple4d k = new Tuple4d(3, 7, 11, 15);
        Tuple4d s = new Tuple4d(4, 8, 12, 16);

        Matrix4d testMatrix = new Matrix4d(i, j, k, s);

        String returnValue = "\n|";
        Double array[] = testMatrix.toRowMajorArray();

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
     * {@link org.jutility.math.vectorAlgebra.Matrix4d#equals(java.lang.Object)}
     * .
     */
    @Test
    public void testEqualsObject() {

        for (double ux = -1; org.jutility.common.datatype.util.NumberComparator
                .smallerOrEqual(ux, 1.0); ux += 0.1) {
            for (double uy = -1; org.jutility.common.datatype.util.NumberComparator
                    .smallerOrEqual(uy, 1.0); uy += 0.1) {
                for (double uz = -1; org.jutility.common.datatype.util.NumberComparator
                        .smallerOrEqual(uz, 1.0); uz += 0.1) {
                    for (double uw = -1; org.jutility.common.datatype.util.NumberComparator
                            .smallerOrEqual(uw, 1.0); uw += 0.1) {

                        Tuple4d testTuple = new Tuple4d(ux, uy, uz, uw);
                        Tuple4d testTuple2 = new Tuple4d(ux + 1, uy, uz, uw);
                        Matrix4d testMatrix = new Matrix4d(testTuple,
                                testTuple, testTuple, testTuple);
                        Matrix4d notTestMatrix = new Matrix4d(testTuple2,
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
     * {@link org.jutility.math.vectorAlgebra.Matrix4d#hashCode()}.
     */
    @Test
    public void testHashCode() {

        Tuple4d i = new Tuple4d(1, 5, 9, 13);
        Tuple4d j = new Tuple4d(2, 6, 10, 14);
        Tuple4d k = new Tuple4d(3, 7, 11, 15);
        Tuple4d s = new Tuple4d(4, 8, 12, 16);

        Matrix4d testMatrix = new Matrix4d(i, j, k, s);

        int hashCode = 23;
        double[] thisMatrix = { 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15, 4, 8,
                12, 16 };

        int ihashCode = 7;
        for (int index = 0; index < 4; index++) {

            ihashCode += 13 * new Double(thisMatrix[index]).hashCode();
        }
        ihashCode *= 29;


        int jhashCode = 7;
        for (int index = 4; index < 8; index++) {

            jhashCode += 13 * new Double(thisMatrix[index]).hashCode();
        }
        jhashCode *= 31;


        int khashCode = 7;
        for (int index = 8; index < 12; index++) {

            khashCode += 13 * new Double(thisMatrix[index]).hashCode();
        }
        khashCode *= 37;


        int shashCode = 7;

        for (int index = 12; index < 16; index++) {

            shashCode += 13 * new Double(thisMatrix[index]).hashCode();
        }
        shashCode *= 39;


        hashCode += ihashCode + jhashCode + khashCode + shashCode;

        assertEquals(hashCode, testMatrix.hashCode());
    }

    private void assertArrayEquals(Double[] expected, Double[] actual,
            double delta) {

        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertTrue(org.jutility.common.datatype.util.NumberComparator
                    .equals(expected[i], actual[i]));
        }

    }

}
