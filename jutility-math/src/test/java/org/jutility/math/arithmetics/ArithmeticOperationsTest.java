package org.jutility.math.arithmetics;


import static org.junit.Assert.*;

import org.junit.Test;
import org.jutility.math.arithmetics.ArithmeticOperations;


/**
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class ArithmeticOperationsTest
        extends org.jutility.math.arithmetics.ArithmeticOperations {


    /**
     * Test method for
     * {@link org.jutility.math.arithmetics.ArithmeticOperations#add(java.lang.Number, java.lang.Number)}
     * .
     */
    @Test
    public void testAdd() {

        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals((byte) 3,
                ArithmeticOperations.add((byte) 1, (byte) 2)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(3.0,
                ArithmeticOperations.add(1.0, 2.0)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(3.0f,
                ArithmeticOperations.add(1.0f, 2.0f)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(3,
                ArithmeticOperations.add(1, 2)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(3l,
                ArithmeticOperations.add(1l, 2l)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals((short) 3,
                ArithmeticOperations.add((short) 1, (short) 2)));

        Exception e = null;
        try {
            org.jutility.common.datatype.util.NumberComparator.equals(null,
                    ArithmeticOperations.add(null, null));
            fail("Operation did not throw an exception!");
        }
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        assertTrue(e instanceof NullPointerException);

    }

    /**
     * Test method for
     * {@link org.jutility.math.arithmetics.ArithmeticOperations#subtract(java.lang.Number, java.lang.Number)}
     * .
     */
    @Test
    public void testSubtract() {

        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals((byte) 1,
                ArithmeticOperations.subtract(new Byte((byte) 3), new Byte(
                        (byte) 2))));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(1.0,
                ArithmeticOperations.subtract(3.0, 2.0)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(1.0f,
                ArithmeticOperations.subtract(3.0f, 2.0f)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(1,
                ArithmeticOperations.subtract(3, 2)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(1l,
                ArithmeticOperations.subtract(3l, 2l)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals((short) 1,
                ArithmeticOperations.subtract((short) 3, (short) 2)));

        Exception e = null;
        try {
            org.jutility.common.datatype.util.NumberComparator.equals(null,
                    ArithmeticOperations.subtract(null, null));
            fail("Operation did not throw an exception!");
        }
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        assertTrue(e instanceof NullPointerException);
    }

    /**
     * Test method for
     * {@link org.jutility.math.arithmetics.ArithmeticOperations#multiply(java.lang.Number, java.lang.Number)}
     * .
     */
    @Test
    public void testMultiply() {

        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals((byte) 2,
                ArithmeticOperations.multiply((byte) 1, (byte) 2)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(2.0,
                ArithmeticOperations.multiply(1.0, 2.0)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(2.0f,
                ArithmeticOperations.multiply(1.0f, 2.0f)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(2,
                ArithmeticOperations.multiply(1, 2)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(2l,
                ArithmeticOperations.multiply(1l, 2l)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals((short) 2,
                ArithmeticOperations.multiply((short) 1, (short) 2)));

        Exception e = null;
        try {
            org.jutility.common.datatype.util.NumberComparator.equals(null,
                    ArithmeticOperations.multiply(null, null));
            fail("Operation did not throw an exception!");
        }
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        assertTrue(e instanceof NullPointerException);
    }

    /**
     * Test method for
     * {@link org.jutility.math.arithmetics.ArithmeticOperations#divide(java.lang.Number, java.lang.Number)}
     * .
     */
    @Test
    public void testDivide() {

        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals((byte) 2,
                ArithmeticOperations.divide((byte) 6, (byte) 3)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(2.0,
                ArithmeticOperations.divide(6.0, 3.0)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(2.0f,
                ArithmeticOperations.divide(6.0f, 3.0f)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(2,
                ArithmeticOperations.divide(6, 3)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals(2l,
                ArithmeticOperations.divide(6l, 3l)));
        assertTrue(org.jutility.common.datatype.util.NumberComparator.equals((short) 2,
                ArithmeticOperations.divide((short) 6, (short) 3)));

        Exception e = null;
        try {
            org.jutility.common.datatype.util.NumberComparator.equals(null,
                    ArithmeticOperations.divide(null, null));
            fail("Operation did not throw an exception!");
        }
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        assertTrue(e instanceof NullPointerException);
    }

}
