package org.jutility.math.vectorAlgebra;


import static org.junit.Assert.*;

import org.junit.Test;
import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberConstants;
import org.jutility.math.vectorAlgebra.Tuple4f;


/**
 * Test harness for the Tuple4f class.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class Tuple4fTest {

    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Tuple4f#hashCode()}.
     */
    @Test
    public void testHashCode() {

        for (float ux = -1; NumberComparator.smallerOrEqual(ux, 1f); ux += 0.1) {
            for (float uy = -1; NumberComparator.smallerOrEqual(uy, 1f); uy += 0.1) {
                for (float uz = -1; NumberComparator.smallerOrEqual(uz, 1f); uz += 0.1) {
                    for (float uw = -1; NumberComparator.smallerOrEqual(uw, 1f); uw += 0.1) {
                        Tuple4f testTuple = new Tuple4f(ux, uy, uz, uw);

                        int expected = 7 + 13 *new Float(ux).hashCode()
                                + 13 *new Float(uy).hashCode()
                                + 13 *new Float(uz).hashCode()
                                + 13 *new Float(uw).hashCode();
                        assertEquals(expected, testTuple.hashCode());
                    }
                }
            }
        }
    }


    /**
     * Test method for {@link org.jutility.math.vectorAlgebra.Tuple4f#toArray()}
     * .
     */
    @Test
    public void testToArray() {

        for (float ux = -1; NumberComparator.smallerOrEqual(ux, 1f); ux += 0.1) {
            for (float uy = -1; NumberComparator.smallerOrEqual(uy, 1f); uy += 0.1) {
                for (float uz = -1; NumberComparator.smallerOrEqual(uz, 1f); uz += 0.1) {
                    for (float uw = -1; NumberComparator.smallerOrEqual(uw, 1f); uw += 0.1) {
                        Tuple4f testTuple = new Tuple4f(ux, uy, uz, uw);

                        Float expected[] = { ux, uy, uz, uw };
                        Float actual[] = testTuple.toArray();
                        
                        assertNotNull(expected);
                        assertNotNull(actual);
                        
                        assertArrayEquals(expected, actual,
                                NumberConstants.DELTA_F);
                    }
                }
            }
        }
    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Tuple4f#toString()}.
     */
    @Test
    public void testToString() {

        for (float ux = -1; NumberComparator.smallerOrEqual(ux, 1f); ux += 0.1) {
            for (float uy = -1; NumberComparator.smallerOrEqual(uy, 1f); uy += 0.1) {
                for (float uz = -1; NumberComparator.smallerOrEqual(uz, 1f); uz += 0.1) {
                    for (float uw = -1; NumberComparator.smallerOrEqual(uw, 1f); uw += 0.1) {
                        Tuple4f testTuple = new Tuple4f(ux, uy, uz, uw);

                        assertEquals("(" + ux + ", " + uy + ", " + uz + ", "
                                + uw + ")", testTuple.toString());
                    }
                }
            }
        }
    }


    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Tuple4f#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {

        for (float ux = -1; NumberComparator.smallerOrEqual(ux, 1f); ux += 0.1) {
            for (float uy = -1; NumberComparator.smallerOrEqual(uy, 1f); uy += 0.1) {
                for (float uz = -1; NumberComparator.smallerOrEqual(uz, 1f); uz += 0.1) {
                    for (float uw = -1; NumberComparator.smallerOrEqual(uw, 1f); uw += 0.1) {

                        Tuple4f testTuple = new Tuple4f(ux, uy, uz, uw);

                        Tuple4f notTestTuple = new Tuple4f(2 + uw, 2 + uy,
                                2 + uz, 2 + uw);
                        assertEquals(testTuple, testTuple);
                        assertEquals(false, testTuple.equals(notTestTuple));
                        assertEquals(false, testTuple.equals(5));
                        assertEquals(false, testTuple.equals(null));
                    }
                }
            }
        }
    }

    private void assertArrayEquals(Float[] expected, Float[] actual, float delta) {

        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertTrue(NumberComparator.equals(expected[i], actual[i]));
        }

    }
}
