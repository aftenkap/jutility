package org.jutility.math.vectorAlgebra;


import static org.junit.Assert.*;

import org.junit.Test;
import org.jutility.math.vectorAlgebra.Point4f;
import org.jutility.math.vectorAlgebra.Vector4f;


/**
 * Test harness for the Point4f class.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class Point4fTest {

    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Vector4f#Vector4f(org.jutility.math.vectorAlgebra.Tuple4f)}
     * .
     */
    @Test
    public void testPoint4fTuple4f() {

        try {
            Point4f testVector = new Point4f(Vector4f.I_UNIT_VECTOR);

            fail("Shouldn't be able to copy vector into point: " + testVector);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Provided parameter is not a point!", e.getMessage());
        }
    }

}
