package org.jutility.math.vectorAlgebra;


import static org.junit.Assert.*;

import org.junit.Test;
import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Point4f;
import org.jutility.math.vectorAlgebra.Vector4f;


/**
 * Test harness for the Vector4f class.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class Vector4fTest {

    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Vector4f#Vector4f(org.jutility.math.vectorAlgebra.Tuple4f)}
     * .
     */
    @Test
    public void testVector4fTuple4f() {

        try {
            IVector4<Float> testVector = new Vector4f(Point4f.ORIGIN);

            fail("Shouldn't be able to copy point into vector: " + testVector);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Provided parameter is not a vector!", e.getMessage());
        }
    }

}
