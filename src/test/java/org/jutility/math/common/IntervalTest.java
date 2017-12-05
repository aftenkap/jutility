package org.jutility.math.common;


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
import org.jutility.common.datatype.range.Interval;
import org.jutility.common.datatype.util.NumberConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The {@code IntervalTest} class provides unit tests for the {@link Interval}
 * class.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@SuppressWarnings({ "ObjectEqualsNull", "EqualsWithItself" })
public class IntervalTest {

    private static final Logger LOG = LoggerFactory
                                                .getLogger(IntervalTest.class);

    private final Integer intMin        = 0;
    private final Integer intMax        = 2;
    private final Integer intWithin     = 1;
    private final Integer intSmaller    = -1;
    private final Integer intLarger     = 3;

    private final Double  doubleMin     = 0.000000000001;
    private final Double  doubleMax     = 0.00000000001;
    private final Double  doubleWithin  = 0.000000000009;
    private final Double  doubleSmaller = 0.0000000000001;
    private final Double  doubleLarger  = 0.00000000002;


    /**
     * Test of the constructor of class Interval.
     */
    @Test
    public void testConstructor() {

        IntervalTest.LOG.info("Constructor");

        this.testConstructor(this.intMin, this.intMax);
        this.testConstructor(this.doubleMin, this.doubleMax);

        IntervalTest.LOG.info("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testConstructor(
            final T min, final T max) {

        final Interval<T> a = new Interval<>(min, min);


        T lowerBound = a.getLowerBound();
        Assert.assertEquals(lowerBound, min);

        T upperBound = a.getUpperBound();
        Assert.assertEquals(upperBound, min);

        Boolean containsLowerBound = a.containsLowerBound();
        Assert.assertEquals(containsLowerBound, true);

        Boolean containsUpperBound = a.containsUpperBound();
        Assert.assertEquals(containsUpperBound, true);


        final Interval<T> b = new Interval<>(max, min, false, false);

        lowerBound = b.getLowerBound();
        Assert.assertEquals(lowerBound, min);

        upperBound = b.getUpperBound();
        Assert.assertEquals(upperBound, max);

        containsLowerBound = b.containsLowerBound();
        Assert.assertEquals(containsLowerBound, false);

        containsUpperBound = b.containsUpperBound();
        Assert.assertEquals(containsUpperBound, false);
    }


    /**
     * Test of containsLowerBound method, of class Interval.
     */
    @Test
    public void testGetContainsLowerBound() {

        IntervalTest.LOG.info("containsLowerBound");

        this.testGetContainsLowerBound(this.intMin, this.intMax);
        this.testGetContainsLowerBound(this.doubleMin, this.doubleMax);

        IntervalTest.LOG.info("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testGetContainsLowerBound(
            final T min, final T max) {

        Interval<T> instance = new Interval<>(min, max);
        Boolean result = instance.containsLowerBound();

        Assert.assertEquals(result, true);

        instance = new Interval<>(min, max, false, true);

        result = instance.containsLowerBound();

        Assert.assertEquals(result, false);
    }


    /**
     * Test of containsUpperBound method, of class Interval.
     */
    @Test
    public void testGetContainsUpperBound() {

        IntervalTest.LOG.info("containsUpperBound");

        this.testGetContainsUpperBound(this.intMin, this.intMax);
        this.testGetContainsUpperBound(this.doubleMin, this.doubleMax);

        IntervalTest.LOG.info("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testGetContainsUpperBound(
            final T min, final T max) {

        Interval<T> instance = new Interval<>(min, max);
        Boolean result = instance.containsUpperBound();

        Assert.assertEquals(result, true);

        instance = new Interval<>(min, max, true, false);

        result = instance.containsUpperBound();

        Assert.assertEquals(result, false);
    }

    /**
     * Test of getLowerBound method, of class Interval.
     */
    @Test
    public void testGetLowerBound() {

        IntervalTest.LOG.info("getLowerBound");

        this.testGetLowerBound(this.intMin, this.intMax);
        this.testGetLowerBound(this.doubleMin, this.doubleMax);

        IntervalTest.LOG.info("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testGetLowerBound(
            final T min, final T max) {

        Interval<T> b = new Interval<>(min, max);
        Assert.assertEquals(b.getLowerBound(), min);

        b = new Interval<>(max, max);
        Assert.assertEquals(b.getLowerBound(), max);

    }

    /**
     * Test of getUpperBound method, of class Interval.
     */
    @Test
    public void testGetUpperBound() {

        IntervalTest.LOG.info("getUpperBound");

        this.testGetUpperBound(this.intMin, this.intMax);
        this.testGetUpperBound(this.doubleMin, this.doubleMax);

        IntervalTest.LOG.info("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testGetUpperBound(
            final T min, final T max) {

        Interval<T> b = new Interval<>(min, max);

        Assert.assertEquals(b.getUpperBound(), max);

        b = new Interval<>(min, min);
        Assert.assertEquals(b.getUpperBound(), min);

    }

    /**
     * Test of contains method, of class Interval.
     */
    @Test
    public void testContains_GenericType() {

        IntervalTest.LOG.info("contains (Generic Type)");

        this.testContains_GenericType(this.intMin, this.intMax, this.intWithin,
                this.intSmaller, this.intLarger);
        this.testContains_GenericType(this.doubleMin, this.doubleMax,
                this.doubleWithin, this.doubleSmaller, this.doubleLarger);

        IntervalTest.LOG.info("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testContains_GenericType(
            final T min, final T max, final T within, final T smaller,
            final T larger) {

        // [min]
        final Interval<T> a = new Interval<>(min, min);
        Assert.assertEquals(Boolean.TRUE, a.contains(min));
        Assert.assertEquals(Boolean.FALSE, a.contains(max));
        Assert.assertEquals(Boolean.FALSE, a.contains(within));
        Assert.assertEquals(Boolean.FALSE, a.contains(smaller));
        Assert.assertEquals(Boolean.FALSE, a.contains(larger));

        // [max]
        final Interval<T> b = new Interval<>(max, max);
        Assert.assertEquals(Boolean.FALSE, b.contains(min));
        Assert.assertEquals(Boolean.TRUE, b.contains(max));
        Assert.assertEquals(Boolean.FALSE, b.contains(within));
        Assert.assertEquals(Boolean.FALSE, b.contains(smaller));
        Assert.assertEquals(Boolean.FALSE, b.contains(larger));

        // [min, max]
        final Interval<T> c = new Interval<>(min, max);
        Assert.assertEquals(Boolean.TRUE, c.contains(min));
        Assert.assertEquals(Boolean.TRUE, c.contains(max));
        Assert.assertEquals(Boolean.TRUE, c.contains(within));
        Assert.assertEquals(Boolean.FALSE, c.contains(smaller));
        Assert.assertEquals(Boolean.FALSE, c.contains(larger));

        // (min, max]
        final Interval<T> d = new Interval<>(min, max, false, true);
        Assert.assertEquals(Boolean.FALSE, d.contains(min));
        Assert.assertEquals(Boolean.TRUE, d.contains(max));
        Assert.assertEquals(Boolean.TRUE, d.contains(within));
        Assert.assertEquals(Boolean.FALSE, d.contains(smaller));
        Assert.assertEquals(Boolean.FALSE, d.contains(larger));

        // [min, max)
        final Interval<T> e = new Interval<>(min, max, true, false);
        Assert.assertEquals(Boolean.TRUE, e.contains(min));
        Assert.assertEquals(Boolean.FALSE, e.contains(max));
        Assert.assertEquals(Boolean.TRUE, e.contains(within));
        Assert.assertEquals(Boolean.FALSE, e.contains(smaller));
        Assert.assertEquals(Boolean.FALSE, e.contains(larger));

        // (min, max)
        final Interval<T> f = new Interval<>(min, max, false, false);
        Assert.assertEquals(Boolean.FALSE, f.contains(min));
        Assert.assertEquals(Boolean.FALSE, f.contains(max));
        Assert.assertEquals(Boolean.TRUE, f.contains(within));
        Assert.assertEquals(Boolean.FALSE, f.contains(smaller));
        Assert.assertEquals(Boolean.FALSE, f.contains(larger));

    }

    /**
     * Test of contains method, of class Interval.
     */
    @Test
    public void testContains_Interval() {

        IntervalTest.LOG.info("contains (Interval)");

        this.testContains_Interval(this.intMin, this.intMax, this.intWithin,
                this.intSmaller, this.intLarger);
        NumberConstants.DELTA_D = 0.00000000000001;
        this.testContains_Interval(this.doubleMin, this.doubleMax,
                this.doubleWithin, this.doubleSmaller, this.doubleLarger);

        IntervalTest.LOG.info("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testContains_Interval(
            final T min, final T max, final T within, final T smaller,
            final T larger) {

        // [smaller]
        final Interval<T> smallerI = new Interval<>(smaller, smaller);
        // [min]
        final Interval<T> minI = new Interval<>(min, min);
        // [within]
        final Interval<T> withinI = new Interval<>(within, within);
        // [max]
        final Interval<T> maxI = new Interval<>(max, max);
        // [larger]
        final Interval<T> largerI = new Interval<>(larger, larger);

        this.simpleContainmentTest(smallerI, minI, false, false);
        this.simpleContainmentTest(smallerI, withinI, false, false);
        this.simpleContainmentTest(smallerI, maxI, false, false);
        this.simpleContainmentTest(smallerI, largerI, false, false);

        this.simpleContainmentTest(minI, withinI, false, false);
        this.simpleContainmentTest(minI, maxI, false, false);
        this.simpleContainmentTest(minI, largerI, false, false);

        this.simpleContainmentTest(withinI, maxI, false, false);
        this.simpleContainmentTest(withinI, largerI, false, false);

        this.simpleContainmentTest(maxI, largerI, false, false);

        this.simpleContainmentTest(minI, withinI, false, false);

        this.simpleContainmentTest(smallerI, smallerI, true, true);
        this.simpleContainmentTest(minI, minI, true, true);
        this.simpleContainmentTest(withinI, withinI, true, true);
        this.simpleContainmentTest(maxI, maxI, true, true);
        this.simpleContainmentTest(largerI, largerI, true, true);


        // [smaller, min]
        final Interval<T> smallerMin = new Interval<>(smaller, min);
        // [smaller, within]
        final Interval<T> smallerWithin = new Interval<>(smaller, within);
        // [smaller, max]
        final Interval<T> smallerMax = new Interval<>(smaller, max);
        // [smaller, larger]
        final Interval<T> smallerLarger = new Interval<>(smaller, larger);
        // [min, within]
        final Interval<T> minWithin = new Interval<>(min, within);
        // [min, max]
        final Interval<T> minMax = new Interval<>(min, max);
        // [min, max]
        final Interval<T> minMaxClone = new Interval<>(min, max);
        // [min, larger]
        final Interval<T> minLarger = new Interval<>(min, larger);
        // [within, max]
        final Interval<T> withinMax = new Interval<>(within, max);
        // [within, larger]
        final Interval<T> withinLarger = new Interval<>(within, larger);
        // [max, larger]
        final Interval<T> maxLarger = new Interval<>(max, larger);


        this.complexContainmentTest(minMax, smallerI, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);
        this.complexContainmentTest(minMax, minI, true, true, true, true,
                false, false, false, false, true, true, true, true, false,
                false, false, false);
        this.complexContainmentTest(minMax, withinI, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        this.complexContainmentTest(minMax, maxI, true, true, true, true, true,
                true, true, true, false, false, false, false, false, false,
                false, false);
        this.complexContainmentTest(minMax, largerI, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        this.complexContainmentTest(smallerMin, minMax, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);
        this.complexContainmentTest(minMax, smallerMin, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        this.complexContainmentTest(smallerWithin, minMax, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);
        this.complexContainmentTest(minMax, smallerWithin, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        this.complexContainmentTest(smallerMax, minMax, true, true, true, true,
                true, true, true, true, false, false, true, true, false, false,
                true, true);
        this.complexContainmentTest(minMax, smallerMax, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        this.complexContainmentTest(smallerLarger, minMax, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);
        this.complexContainmentTest(minMax, smallerLarger, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        this.complexContainmentTest(minWithin, minMax, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);
        this.complexContainmentTest(minMax, minWithin, true, true, true, true,
                false, true, false, true, true, true, true, true, false, true,
                false, true);

        this.complexContainmentTest(minMaxClone, minMax, true, true, true,
                true, false, true, false, true, false, false, true, true,
                false, false, false, true);
        this.complexContainmentTest(minMax, minMaxClone, true, true, true,
                true, false, true, false, true, false, false, true, true,
                false, false, false, true);

        this.complexContainmentTest(minLarger, minMax, true, true, true, true,
                false, true, false, true, true, true, true, true, false, true,
                false, true);
        this.complexContainmentTest(minMax, minLarger, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        this.complexContainmentTest(withinMax, minMax, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);
        this.complexContainmentTest(minMax, withinMax, true, true, true, true,
                true, true, true, true, false, false, true, true, false, false,
                true, true);

        this.complexContainmentTest(withinLarger, minMax, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);
        this.complexContainmentTest(minMax, withinLarger, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        this.complexContainmentTest(maxLarger, minMax, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);
        this.complexContainmentTest(minMax, maxLarger, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);


    }

    private <T extends Number & Comparable<T>> void simpleContainmentTest(
            final Interval<T> left, final Interval<T> right,
            final Boolean leftContainsRight, final Boolean rightContainsLeft) {

        Assert.assertEquals(leftContainsRight, left.contains(right));
        Assert.assertEquals(rightContainsLeft, right.contains(left));
    }

    private <T extends Number & Comparable<T>> void complexContainmentTest(
            final Interval<T> left, final Interval<T> right,
            final Boolean leftULrightUL, final Boolean leftULrightU,
            final Boolean leftULrightL, final Boolean leftULright,
            final Boolean leftUrightUL, final Boolean leftUrightU,
            final Boolean leftUrightL, final Boolean leftUright,
            final Boolean leftLrightUL, final Boolean leftLrightU,
            final Boolean leftLrightL, final Boolean leftLright,
            final Boolean leftrightUL, final Boolean leftrightU,
            final Boolean leftrightL, final Boolean leftright) {


        final Boolean leftContainsLowerBound = left.containsLowerBound();
        final Boolean leftContainsUpperBound = left.containsUpperBound();
        final Boolean rightContainsLowerBound = right.containsLowerBound();
        final Boolean rightContainsUpperBound = right.containsUpperBound();


        Interval<T> leftCopy = new Interval<>(left.getLowerBound(),
                left.getUpperBound(), leftContainsLowerBound,
                leftContainsUpperBound);
        Interval<T> rightCopy = new Interval<>(right.getLowerBound(),
                right.getUpperBound(), rightContainsLowerBound,
                rightContainsUpperBound);



        leftCopy = new Interval<>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), true, true);
        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);

        Assert.assertEquals(leftCopy.contains(rightCopy), leftULrightUL);


        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftULrightU);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftULrightL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftULright);


        leftCopy = new Interval<>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), false, true);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftUrightUL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftUrightU);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftUrightL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftUright);


        leftCopy = new Interval<>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), true, false);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftLrightUL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftLrightU);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftLrightL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftLright);


        leftCopy = new Interval<>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), false, false);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftrightUL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftrightU);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftrightL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        Assert.assertEquals(leftCopy.contains(rightCopy), leftright);
    }


    /**
     * Test of intersects method, of class Interval.
     */
    @Test
    public void testIntersects() {

        IntervalTest.LOG.info("intersects");

        this.testIntersects(this.intMin, this.intMax, this.intWithin,
                this.intSmaller, this.intLarger);
        this.testIntersects(this.doubleMin, this.doubleMax, this.doubleWithin,
                this.doubleSmaller, this.doubleLarger);

        IntervalTest.LOG.info("\tSuccess.");
    }


    private <T extends Number & Comparable<T>> void testIntersects(final T min,
            final T max, final T within, final T smaller, final T larger) {


        // [smaller]
        final Interval<T> smallerI = new Interval<>(smaller, smaller);
        // [min]
        final Interval<T> minI = new Interval<>(min, min);
        // [within]
        final Interval<T> withinI = new Interval<>(within, within);
        // [max]
        final Interval<T> maxI = new Interval<>(max, max);
        // [larger]
        final Interval<T> largerI = new Interval<>(larger, larger);

        this.simpleIntersectionTest(smallerI, minI, false);
        this.simpleIntersectionTest(smallerI, withinI, false);
        this.simpleIntersectionTest(smallerI, maxI, false);
        this.simpleIntersectionTest(smallerI, largerI, false);

        this.simpleIntersectionTest(minI, withinI, false);
        this.simpleIntersectionTest(minI, maxI, false);
        this.simpleIntersectionTest(minI, largerI, false);

        this.simpleIntersectionTest(withinI, maxI, false);
        this.simpleIntersectionTest(withinI, largerI, false);

        this.simpleIntersectionTest(maxI, largerI, false);

        this.simpleIntersectionTest(minI, withinI, false);

        this.simpleIntersectionTest(smallerI, smallerI, true);
        this.simpleIntersectionTest(minI, minI, true);
        this.simpleIntersectionTest(withinI, withinI, true);
        this.simpleIntersectionTest(maxI, maxI, true);
        this.simpleIntersectionTest(largerI, largerI, true);


        // [smaller, min]
        final Interval<T> smallerMin = new Interval<>(smaller, min);
        // [smaller, within]
        final Interval<T> smallerWithin = new Interval<>(smaller, within);
        // [smaller, max]
        final Interval<T> smallerMax = new Interval<>(smaller, max);
        // [smaller, larger]
        final Interval<T> smallerLarger = new Interval<>(smaller, larger);
        // [min, within]
        final Interval<T> minWithin = new Interval<>(min, within);
        // [min, max]
        final Interval<T> minMax = new Interval<>(min, max);
        // [min, max]
        final Interval<T> minMaxClone = new Interval<>(min, max);
        // [min, larger]
        final Interval<T> minLarger = new Interval<>(min, larger);
        // [within, max]
        final Interval<T> withinMax = new Interval<>(within, max);
        // [within, larger]
        final Interval<T> withinLarger = new Interval<>(within, larger);
        // [max, larger]
        final Interval<T> maxLarger = new Interval<>(max, larger);


        this.complexIntersectionTest(minMax, smallerI, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);
        this.complexIntersectionTest(minMax, minI, true, true, true, true,
                false, false, false, false, true, true, true, true, false,
                false, false, false);
        this.complexIntersectionTest(minMax, withinI, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        this.complexIntersectionTest(minMax, maxI, true, true, true, true,
                true, true, true, true, false, false, false, false, false,
                false, false, false);
        this.complexIntersectionTest(minMax, largerI, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        this.complexIntersectionTest(smallerMin, minMax, true, false, true,
                false, true, false, true, false, false, false, false, false,
                false, false, false, false);
        this.complexIntersectionTest(minMax, smallerMin, true, true, false,
                false, false, false, false, false, true, true, false, false,
                false, false, false, false);

        this.complexIntersectionTest(smallerWithin, minMax, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);
        this.complexIntersectionTest(minMax, smallerWithin, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);

        this.complexIntersectionTest(smallerMax, minMax, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);
        this.complexIntersectionTest(minMax, smallerMax, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);

        this.complexIntersectionTest(smallerLarger, minMax, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);
        this.complexIntersectionTest(minMax, smallerLarger, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);

        this.complexIntersectionTest(minWithin, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        this.complexIntersectionTest(minMax, minWithin, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        this.complexIntersectionTest(minMaxClone, minMax, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);
        this.complexIntersectionTest(minMax, minMaxClone, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);

        this.complexIntersectionTest(minLarger, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        this.complexIntersectionTest(minMax, minLarger, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        this.complexIntersectionTest(withinMax, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        this.complexIntersectionTest(minMax, withinMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        this.complexIntersectionTest(withinLarger, minMax, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);
        this.complexIntersectionTest(minMax, withinLarger, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true, true);

        this.complexIntersectionTest(maxLarger, minMax, true, true, false,
                false, false, false, false, false, true, true, false, false,
                false, false, false, false);
        this.complexIntersectionTest(minMax, maxLarger, true, false, true,
                false, true, false, true, false, false, false, false, false,
                false, false, false, false);
    }

    private <T extends Number & Comparable<T>> void simpleIntersectionTest(
            final Interval<T> left, final Interval<T> right,
            final Boolean intersects) {

        Assert.assertEquals(left.intersects(right), intersects);
        Assert.assertEquals(right.intersects(left), intersects);
    }

    private <T extends Number & Comparable<T>> void complexIntersectionTest(
            final Interval<T> left, final Interval<T> right,
            final Boolean leftULrightUL, final Boolean leftULrightU,
            final Boolean leftULrightL, final Boolean leftULright,
            final Boolean leftUrightUL, final Boolean leftUrightU,
            final Boolean leftUrightL, final Boolean leftUright,
            final Boolean leftLrightUL, final Boolean leftLrightU,
            final Boolean leftLrightL, final Boolean leftLright,
            final Boolean leftrightUL, final Boolean leftrightU,
            final Boolean leftrightL, final Boolean leftright) {



        Interval<T> leftCopy = new Interval<>(left.getLowerBound(),
                left.getUpperBound(), true, true);

        Interval<T> rightCopy = new Interval<>(right.getLowerBound(),
                right.getUpperBound(), true, true);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftULrightUL);


        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftULrightU);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftULrightL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftULright);


        leftCopy = new Interval<>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), false, true);


        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftUrightUL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftUrightU);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftUrightL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftUright);


        leftCopy = new Interval<>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), true, false);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftLrightUL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftLrightU);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftLrightL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftLright);


        leftCopy = new Interval<>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), false, false);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftrightUL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftrightU);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftrightL);

        rightCopy = new Interval<>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        Assert.assertEquals(leftCopy.intersects(rightCopy), leftright);
    }

    /**
     * Test of isContainedIn method, of class Interval.
     */
    @Test
    public void testIsContainedIn() {

        IntervalTest.LOG.info("isContainedIn");

        this.testIsContainedIn(this.intMin, this.intMax, this.intWithin,
                this.intSmaller, this.intLarger);
        this.testIsContainedIn(this.doubleMin, this.doubleMax,
                this.doubleWithin, this.doubleSmaller, this.doubleLarger);

        IntervalTest.LOG.info("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testIsContainedIn(
            final T min, final T max, final T within, final T smaller,
            final T larger) {

        final Interval<T> minMax = new Interval<>(min, max);
        final Interval<T> smallerLarger = new Interval<>(smaller, larger);
        final Interval<T> withinWithin = new Interval<>(within, within);

        Assert.assertEquals(minMax.contains(smallerLarger),
                smallerLarger.isContainedIn(minMax));
        Assert.assertEquals(minMax.contains(withinWithin),
                withinWithin.isContainedIn(minMax));
        Assert.assertEquals(smallerLarger.contains(minMax),
                minMax.isContainedIn(smallerLarger));

        Assert.assertEquals(minMax.isContainedIn(null), false);


    }

    /**
     * Test of equals method, of class Interval.
     */
    @Test
    public void testEquals() {

        IntervalTest.LOG.info("equals");

        this.testEquals(this.intMin, this.intMax, this.intWithin,
                this.intSmaller, this.intLarger);
        this.testEquals(this.doubleMin, this.doubleMax, this.doubleWithin,
                this.doubleSmaller, this.doubleLarger);

        IntervalTest.LOG.info("\tSuccess.");
    }

    @SuppressWarnings({ "EqualsBetweenInconvertibleTypes", "EqualsWithItself" })
    private <T extends Number & Comparable<T>> void testEquals(final T min,
            final T max, final T within, final T smaller, final T larger) {

        final Interval<T> interval = new Interval<>(min, max);

        final Interval<T> equalInterval = new Interval<>(min, max);
        final Interval<T> intervalWithoutLowerBound = new Interval<>(min, max,
                false, true);
        final Interval<T> intervalWithoutUpperBound = new Interval<>(min, max,
                true, false);
        final Interval<T> intervalWithoutBounds = new Interval<>(min, max,
                false, false);

        final Interval<T> smallerMax = new Interval<>(smaller, max);
        final Interval<T> minLarger = new Interval<>(within, larger);
        final Interval<T> withinI = new Interval<>(within, within);

        Assert.assertEquals(interval.equals(null), false);
        //noinspection EqualsBetweenInconvertibleTypes
        Assert.assertEquals(interval.equals(max), false);

        Assert.assertEquals(interval.equals(interval), true);
        Assert.assertEquals(interval.equals(equalInterval), true);
        Assert.assertEquals(interval.equals(intervalWithoutLowerBound), false);
        Assert.assertEquals(interval.equals(intervalWithoutUpperBound), false);
        Assert.assertEquals(interval.equals(intervalWithoutBounds), false);

        Assert.assertEquals(interval.equals(smallerMax), false);
        Assert.assertEquals(interval.equals(minLarger), false);
        Assert.assertEquals(interval.equals(withinI), false);
    }

    /**
     * Test of hashCode method, of class Interval.
     */
    @Test
    public void testHashCode() {

        IntervalTest.LOG.info("hashCode");

        this.testHashCode(this.intMin, this.intMax);
        this.testHashCode(this.doubleMin, this.doubleMax);

        IntervalTest.LOG.info("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testHashCode(final T min,
            final T max) {

        final Interval<T> interval = new Interval<>(min, max);
        final Interval<T> equalInterval = new Interval<>(min, max);

        Assert.assertEquals(interval, equalInterval);
        Assert.assertEquals(interval.hashCode(), equalInterval.hashCode());

    }

    /**
     * Test of toString method, of class Interval.
     */
    @Test
    public void testToString() {

        IntervalTest.LOG.info("toString");

        this.testToString(this.intMin, this.intMax);
        this.testToString(this.doubleMin, this.doubleMax);

        IntervalTest.LOG.info("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testToString(final T min,
            final T max) {

        final String squareBracketOpen = "[";
        final String squareBracketClose = "]";
        final String parenthesisOpen = "(";
        final String parenthesisClose = ")";
        final String separator = ", ";


        final Interval<T> a = new Interval<>(min, min);
        final String zeroInterval = squareBracketOpen + min.toString()
                + squareBracketClose;
        Assert.assertEquals(zeroInterval, a.toString());

        Interval<T> b = new Interval<>(min, max);
        String zeroOneInterval = squareBracketOpen + min.toString() + separator
                + max.toString() + squareBracketClose;
        Assert.assertEquals(zeroOneInterval, b.toString());

        b = new Interval<>(b.getLowerBound(), b.getUpperBound(), false,
                b.containsUpperBound());
        zeroOneInterval = parenthesisOpen + min.toString() + separator
                + max.toString() + squareBracketClose;
        Assert.assertEquals(zeroOneInterval, b.toString());

        b = new Interval<>(b.getLowerBound(), b.getUpperBound(),
                b.containsLowerBound(), false);
        zeroOneInterval = parenthesisOpen + min.toString() + separator
                + max.toString() + parenthesisClose;
        Assert.assertEquals(zeroOneInterval, b.toString());

        b = new Interval<>(b.getLowerBound(), b.getUpperBound(), true,
                b.containsUpperBound());
        zeroOneInterval = squareBracketOpen + min.toString() + separator
                + max.toString() + parenthesisClose;
        Assert.assertEquals(zeroOneInterval, b.toString());
    }
}
