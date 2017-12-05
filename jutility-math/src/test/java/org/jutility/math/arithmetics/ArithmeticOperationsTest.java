package org.jutility.math.arithmetics;



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


/**
 * The {@code ArithmeticOperationsTest} class provides unit tests for the
 * {@link ArithmeticOperations} utility class.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class ArithmeticOperationsTest {


    /**
     * Test method for
     * {@link ArithmeticOperations#add(java.lang.Number, java.lang.Number)} .
     */
    @Test
    public void testAdd() {

        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals((byte) 3, ArithmeticOperations.add((byte) 1, (byte) 2)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(3.0, ArithmeticOperations.add(1.0, 2.0)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(3.0f, ArithmeticOperations.add(1.0f, 2.0f)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(3, ArithmeticOperations.add(1, 2)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(3L, ArithmeticOperations.add(1L, 2L)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals((short) 3,
                        ArithmeticOperations.add((short) 1, (short) 2)));


        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(3.0, ArithmeticOperations.add(1.0, (byte) 2)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(3.0f, ArithmeticOperations.add(1.0f, 2)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(3L, ArithmeticOperations.add(1L, 2)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(3, ArithmeticOperations.add(1, (short) 2)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals((short) 3,
                        ArithmeticOperations.add((short) 1, (byte) 2)));

        Exception e = null;
        try {
            org.jutility.common.datatype.util.NumberComparator.equals(null,
                    ArithmeticOperations.add(null, null));
            Assert.fail("Operation did not throw an exception!");
        }
        catch (final Exception ex) {

            e = ex;
        }
        Assert.assertNotNull(e);
        Assert.assertTrue(e instanceof NullPointerException);
    }

    /**
     * Test method for {@link ArithmeticOperations#subtract(Number, Number)} .
     */
    @Test
    public void testSubtract() {

        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals((byte) 1, ArithmeticOperations.subtract((byte) 3,
                        (byte) 2)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(1.0, ArithmeticOperations.subtract(3.0, 2.0)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(1.0f, ArithmeticOperations.subtract(3.0f, 2.0f)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(1, ArithmeticOperations.subtract(3, 2)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(1L, ArithmeticOperations.subtract(3L, 2L)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals((short) 1,
                        ArithmeticOperations.subtract((short) 3, (short) 2)));

        Exception e = null;
        try {
            org.jutility.common.datatype.util.NumberComparator.equals(null,
                    ArithmeticOperations.subtract(null, null));
            Assert.fail("Operation did not throw an exception!");
        }
        catch (final Exception ex) {
            e = ex;
        }
        Assert.assertNotNull(e);
        Assert.assertTrue(e instanceof NullPointerException);
    }

    /**
     * Test method for {@link ArithmeticOperations#multiply(Number, Number)} .
     */
    @Test
    public void testMultiply() {

        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals((byte) 2,
                        ArithmeticOperations.multiply((byte) 1, (byte) 2)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(2.0, ArithmeticOperations.multiply(1.0, 2.0)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(2.0f, ArithmeticOperations.multiply(1.0f, 2.0f)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(2, ArithmeticOperations.multiply(1, 2)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(2L, ArithmeticOperations.multiply(1L, 2L)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals((short) 2,
                        ArithmeticOperations.multiply((short) 1, (short) 2)));

        Exception e = null;
        try {
            org.jutility.common.datatype.util.NumberComparator.equals(null,
                    ArithmeticOperations.multiply(null, null));
            Assert.fail("Operation did not throw an exception!");
        }
        catch (final Exception ex) {
            e = ex;
        }
        Assert.assertNotNull(e);
        Assert.assertTrue(e instanceof NullPointerException);
    }

    /**
     * Test method for {@link ArithmeticOperations#divide(Number, Number)} .
     */
    @Test
    public void testDivide() {

        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals((byte) 2,
                        ArithmeticOperations.divide((byte) 6, (byte) 3)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(2.0, ArithmeticOperations.divide(6.0, 3.0)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(2.0f, ArithmeticOperations.divide(6.0f, 3.0f)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(2, ArithmeticOperations.divide(6, 3)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals(2L, ArithmeticOperations.divide(6L, 3L)));
        Assert.assertTrue(org.jutility.common.datatype.util.NumberComparator
                .equals((short) 2,
                        ArithmeticOperations.divide((short) 6, (short) 3)));

        Exception e = null;
        try {
            org.jutility.common.datatype.util.NumberComparator.equals(null,
                    ArithmeticOperations.divide(null, null));
            Assert.fail("Operation did not throw an exception!");
        }
        catch (final Exception ex) {
            e = ex;
        }
        Assert.assertNotNull(e);
        Assert.assertTrue(e instanceof NullPointerException);
    }
}
