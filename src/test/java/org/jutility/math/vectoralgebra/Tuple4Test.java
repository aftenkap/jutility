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
 * The {@code Tuple4Test} class provides unit tests for the {@link Tuple4}
 * class.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@SuppressWarnings({ "ObjectEqualsNull", "EqualsBetweenInconvertibleTypes" })
public class Tuple4Test {



    /**
     * Test method for {@link Tuple4#toArray()} .
     */
    @Test
    public void testToArray() {

        for (float ux = -1; NumberComparator.smallerOrEqual(ux, 1f); ux += 0.1) {
            for (float uy = -1; NumberComparator.smallerOrEqual(uy, 1f); uy += 0.1) {
                for (float uz = -1; NumberComparator.smallerOrEqual(uz, 1f); uz += 0.1) {
                    for (float uw = -1; NumberComparator.smallerOrEqual(uw, 1f); uw += 0.1) {
                        final Tuple4<Float> testTuple = new Tuple4<>(ux, uy, uz,
                                uw, Float.class);

                        final Float expected[] = { ux, uy, uz, uw };
                        final Float actual[] = testTuple.toArray();

                        Assert.assertNotNull(expected);
                        Assert.assertNotNull(actual);

                        this.assertArrayEquals(expected, actual);
                    }
                }
            }
        }
    }



    /**
     * Test method for {@link Tuple4#equals(Object)}.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    public void testEqualsObject() {

        for (float ux = -1; NumberComparator.smallerOrEqual(ux, 1f); ux += 0.1) {
            for (float uy = -1; NumberComparator.smallerOrEqual(uy, 1f); uy += 0.1) {
                for (float uz = -1; NumberComparator.smallerOrEqual(uz, 1f); uz += 0.1) {
                    for (float uw = -1; NumberComparator.smallerOrEqual(uw, 1f); uw += 0.1) {

                        final Tuple4<Float> testTuple = new Tuple4<>(ux, uy, uz,
                                uw, Float.class);

                        final Tuple4<Float> notTestTuple = new Tuple4<>(2 + uw,
                                2 + uy, 2 + uz, 2 + uw, Float.class);
                        Assert.assertEquals(testTuple, testTuple);
                        Assert.assertEquals(false,
                                testTuple.equals(notTestTuple));
                        Assert.assertEquals(false, testTuple.equals(5));
                        Assert.assertEquals(false, testTuple.equals(null));
                    }
                }
            }
        }
    }


    /**
     * Test method for {@link Tuple4#hashCode()}.
     */
    @Test
    public void testHashCode() {

        for (float ux = -1; NumberComparator.smallerOrEqual(ux, 1f); ux += 0.1) {

            for (float uy = -1; NumberComparator.smallerOrEqual(uy, 1f); uy += 0.1) {

                for (float uz = -1; NumberComparator.smallerOrEqual(uz, 1f); uz += 0.1) {

                    for (float uw = -1; NumberComparator.smallerOrEqual(uw, 1f); uw += 0.1) {

                        final Tuple4<Float> testTuple = new Tuple4<>(ux, uy, uz,
                                uw, Float.class);

                        final int expected = 13
                                + (7 * new Float(ux).hashCode())
                                + (23 * new Float(uy).hashCode())
                                + (31 * new Float(uz).hashCode())
                                + (41 * new Float(uw).hashCode());
                        Assert.assertEquals(expected, testTuple.hashCode());
                    }
                }
            }
        }
    }


    /**
     * Test method for {@link Tuple4#toString()}.
     */
    @Test
    public void testToString() {

        for (float ux = -1; NumberComparator.smallerOrEqual(ux, 1f); ux += 0.1) {
            for (float uy = -1; NumberComparator.smallerOrEqual(uy, 1f); uy += 0.1) {
                for (float uz = -1; NumberComparator.smallerOrEqual(uz, 1f); uz += 0.1) {
                    for (float uw = -1; NumberComparator.smallerOrEqual(uw, 1f); uw += 0.1) {
                        final Tuple4<Float> testTuple = new Tuple4<>(ux, uy, uz,
                                uw, Float.class);

                        Assert.assertEquals("(" + ux + ", " + uy + ", " + uz
                                + ", " + uw + ")", testTuple.toString());
                    }
                }
            }
        }
    }

    private void assertArrayEquals(final Float[] expected, final Float[] actual) {

        Assert.assertEquals(expected.length, actual.length);

        for (int i = 0; i < expected.length; i++) {

            Assert.assertTrue(NumberComparator.equals(expected[i], actual[i]));
        }

    }
}
