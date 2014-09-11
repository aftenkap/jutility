package org.jutility.math.common;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.jutility.common.datatype.range.Interval;


/**
 * 
 * @author pr755870
 */
public class IntervalTest {

    private Integer intMin        = 0;
    private Integer intMax        = 2;
    private Integer intWithin     = 1;
    private Integer intSmaller    = -1;
    private Integer intLarger     = 3;

    private Double  doubleMin     = 0.000000000001;
    private Double  doubleMax     = 0.00000000001;
    private Double  doubleWithin  = 0.000000000009;
    private Double  doubleSmaller = 0.0000000000001;
    private Double  doubleLarger  = 0.00000000002;


    /**
     * Test of the constructor of class Interval.
     */
    @Test
    public void testConstructor() {

        System.out.println("Constructor");

        testConstructor(intMin, intMax);
        testConstructor(doubleMin, doubleMax);

        System.out.println("\tSuccess.");

    }

    private <T extends Number & Comparable<T>> void testConstructor(T min, T max) {

        Interval<T> a = new Interval<T>(min, min);


        T lowerBound = a.getLowerBound();
        assertEquals(lowerBound, min);

        T upperBound = a.getUpperBound();
        assertEquals(upperBound, min);

        Boolean containsLowerBound = a.containsLowerBound();
        assertEquals(containsLowerBound, true);

        Boolean containsUpperBound = a.containsUpperBound();
        assertEquals(containsUpperBound, true);


        Interval<T> b = new Interval<T>(max, min, false, false);

        lowerBound = b.getLowerBound();
        assertEquals(lowerBound, min);

        upperBound = b.getUpperBound();
        assertEquals(upperBound, max);

        containsLowerBound = b.containsLowerBound();
        assertEquals(containsLowerBound, false);

        containsUpperBound = b.containsUpperBound();
        assertEquals(containsUpperBound, false);
    }


    /**
     * Test of containsLowerBound method, of class Interval.
     */
    @Test
    public void testGetContainsLowerBound() {

        System.out.println("containsLowerBound");

        testGetContainsLowerBound(intMin, intMax);
        testGetContainsLowerBound(doubleMin, doubleMax);

        System.out.println("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testGetContainsLowerBound(
            T min, T max) {

        Interval<T> instance = new Interval<T>(min, max);
        Boolean result = instance.containsLowerBound();

        assertEquals(result, true);

        instance = new Interval<T>(min, max, false, true);

        result = instance.containsLowerBound();

        assertEquals(result, false);
    }


    /**
     * Test of containsUpperBound method, of class Interval.
     */
    @Test
    public void testGetContainsUpperBound() {

        System.out.println("containsUpperBound");

        testGetContainsUpperBound(intMin, intMax);
        testGetContainsUpperBound(doubleMin, doubleMax);

        System.out.println("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testGetContainsUpperBound(
            T min, T max) {

        Interval<T> instance = new Interval<T>(min, max);
        Boolean result = instance.containsUpperBound();

        assertEquals(result, true);

        instance = new Interval<T>(min, max, true, false);

        result = instance.containsUpperBound();

        assertEquals(result, false);
    }

    /**
     * Test of getLowerBound method, of class Interval.
     */
    @Test
    public void testGetLowerBound() {

        System.out.println("getLowerBound");

        testGetLowerBound(intMin, intMax);
        testGetLowerBound(doubleMin, doubleMax);

        System.out.println("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testGetLowerBound(T min,
            T max) {

        Interval<T> b = new Interval<T>(min, max);
        assertEquals(b.getLowerBound(), min);

        b = new Interval<T>(max, max);
        assertEquals(b.getLowerBound(), max);

    }

    /**
     * Test of getUpperBound method, of class Interval.
     */
    @Test
    public void testGetUpperBound() {

        System.out.println("getUpperBound");

        testGetUpperBound(intMin, intMax);
        testGetUpperBound(doubleMin, doubleMax);

        System.out.println("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testGetUpperBound(T min,
            T max) {

        Interval<T> b = new Interval<T>(min, max);

        assertEquals(b.getUpperBound(), max);

        b = new Interval<T>(min, min);
        assertEquals(b.getUpperBound(), min);

    }

    /**
     * Test of contains method, of class Interval.
     */
    @Test
    public void testContains_GenericType() {

        System.out.println("contains (Generic Type)");

        testContains_GenericType(intMin, intMax, intWithin, intSmaller,
                intLarger);
        testContains_GenericType(doubleMin, doubleMax, doubleWithin,
                doubleSmaller, doubleLarger);

        System.out.println("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testContains_GenericType(
            T min, T max, T within, T smaller, T larger) {

        // [min]
        Interval<T> a = new Interval<T>(min, min);
        assertEquals(Boolean.TRUE, a.contains(min));
        assertEquals(Boolean.FALSE, a.contains(max));
        assertEquals(Boolean.FALSE, a.contains(within));
        assertEquals(Boolean.FALSE, a.contains(smaller));
        assertEquals(Boolean.FALSE, a.contains(larger));

        // [max]
        Interval<T> b = new Interval<T>(max, max);
        assertEquals(Boolean.FALSE, b.contains(min));
        assertEquals(Boolean.TRUE, b.contains(max));
        assertEquals(Boolean.FALSE, b.contains(within));
        assertEquals(Boolean.FALSE, b.contains(smaller));
        assertEquals(Boolean.FALSE, b.contains(larger));

        // [min, max]
        Interval<T> c = new Interval<T>(min, max);
        assertEquals(Boolean.TRUE, c.contains(min));
        assertEquals(Boolean.TRUE, c.contains(max));
        assertEquals(Boolean.TRUE, c.contains(within));
        assertEquals(Boolean.FALSE, c.contains(smaller));
        assertEquals(Boolean.FALSE, c.contains(larger));

        // (min, max]
        Interval<T> d = new Interval<T>(min, max, false, true);
        assertEquals(Boolean.FALSE, d.contains(min));
        assertEquals(Boolean.TRUE, d.contains(max));
        assertEquals(Boolean.TRUE, d.contains(within));
        assertEquals(Boolean.FALSE, d.contains(smaller));
        assertEquals(Boolean.FALSE, d.contains(larger));

        // [min, max)
        Interval<T> e = new Interval<T>(min, max, true, false);
        assertEquals(Boolean.TRUE, e.contains(min));
        assertEquals(Boolean.FALSE, e.contains(max));
        assertEquals(Boolean.TRUE, e.contains(within));
        assertEquals(Boolean.FALSE, e.contains(smaller));
        assertEquals(Boolean.FALSE, e.contains(larger));

        // (min, max)
        Interval<T> f = new Interval<T>(min, max, false, false);
        assertEquals(Boolean.FALSE, f.contains(min));
        assertEquals(Boolean.FALSE, f.contains(max));
        assertEquals(Boolean.TRUE, f.contains(within));
        assertEquals(Boolean.FALSE, f.contains(smaller));
        assertEquals(Boolean.FALSE, f.contains(larger));

    }

    /**
     * Test of contains method, of class Interval.
     */
    @Test
    public void testContains_Interval() {

        System.out.println("contains (Interval)");

        testContains_Interval(intMin, intMax, intWithin, intSmaller, intLarger);
        testContains_Interval(doubleMin, doubleMax, doubleWithin,
                doubleSmaller, doubleLarger);

        System.out.println("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testContains_Interval(
            T min, T max, T within, T smaller, T larger) {

        // [smaller]
        Interval<T> smallerI = new Interval<T>(smaller, smaller);
        // [min]
        Interval<T> minI = new Interval<T>(min, min);
        // [within]
        Interval<T> withinI = new Interval<T>(within, within);
        // [max]
        Interval<T> maxI = new Interval<T>(max, max);
        // [larger]
        Interval<T> largerI = new Interval<T>(larger, larger);

        simpleContainmentTest(smallerI, minI, false, false);
        simpleContainmentTest(smallerI, withinI, false, false);
        simpleContainmentTest(smallerI, maxI, false, false);
        simpleContainmentTest(smallerI, largerI, false, false);

        simpleContainmentTest(minI, withinI, false, false);
        simpleContainmentTest(minI, maxI, false, false);
        simpleContainmentTest(minI, largerI, false, false);

        simpleContainmentTest(withinI, maxI, false, false);
        simpleContainmentTest(withinI, largerI, false, false);

        simpleContainmentTest(maxI, largerI, false, false);

        simpleContainmentTest(minI, withinI, false, false);

        simpleContainmentTest(smallerI, smallerI, true, true);
        simpleContainmentTest(minI, minI, true, true);
        simpleContainmentTest(withinI, withinI, true, true);
        simpleContainmentTest(maxI, maxI, true, true);
        simpleContainmentTest(largerI, largerI, true, true);


        // [smaller, min]
        Interval<T> smallerMin = new Interval<T>(smaller, min);
        // [smaller, within]
        Interval<T> smallerWithin = new Interval<T>(smaller, within);
        // [smaller, max]
        Interval<T> smallerMax = new Interval<T>(smaller, max);
        // [smaller, larger]
        Interval<T> smallerLarger = new Interval<T>(smaller, larger);
        // [min, within]
        Interval<T> minWithin = new Interval<T>(min, within);
        // [min, max]
        Interval<T> minMax = new Interval<T>(min, max);
        // [min, max]
        Interval<T> minMaxClone = new Interval<T>(min, max);
        // [min, larger]
        Interval<T> minLarger = new Interval<T>(min, larger);
        // [within, max]
        Interval<T> withinMax = new Interval<T>(within, max);
        // [within, larger]
        Interval<T> withinLarger = new Interval<T>(within, larger);
        // [max, larger]
        Interval<T> maxLarger = new Interval<T>(max, larger);


        System.out.println("minMax - smallerI");
        complexContainmentTest(minMax, smallerI, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);
        System.out.println("\nminMax - minI");
        complexContainmentTest(minMax, minI, true, true, true, true, false,
                false, false, false, true, true, true, true, false, false,
                false, false);
        System.out.println("\nminMax - withinI");
        complexContainmentTest(minMax, withinI, true, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true);
        System.out.println("\nminMax - maxI");
        complexContainmentTest(minMax, maxI, true, true, true, true, true,
                true, true, true, false, false, false, false, false, false,
                false, false);
        System.out.println("\nminMax - largerI");
        complexContainmentTest(minMax, largerI, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);

        System.out.println("\nsmallerMin - minMax");
        complexContainmentTest(smallerMin, minMax, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);
        System.out.println("\nminMax - smallerMin");
        complexContainmentTest(minMax, smallerMin, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);

        System.out.println("\nsmallerWithin - minMax");
        complexContainmentTest(smallerWithin, minMax, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);
        System.out.println("\nminMax - smallerWithin");
        complexContainmentTest(minMax, smallerWithin, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        System.out.println("\nsmallerMax - minMax");
        complexContainmentTest(smallerMax, minMax, true, true, true, true,
                true, true, true, true, false, false, true, true, false, false,
                true, true);
        System.out.println("\nminMax - smallerMax");
        complexContainmentTest(minMax, smallerMax, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);

        System.out.println("\nsmallerLarger - minMax");
        complexContainmentTest(smallerLarger, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        System.out.println("\nminMax - smallerLarger");
        complexContainmentTest(minMax, smallerLarger, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        System.out.println("\nminWithin - minMax");
        complexContainmentTest(minWithin, minMax, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);
        System.out.println("\nminMax - minWithin");
        complexContainmentTest(minMax, minWithin, true, true, true, true,
                false, true, false, true, true, true, true, true, false, true,
                false, true);

        System.out.println("\nminMaxClone - minMax");
        complexContainmentTest(minMaxClone, minMax, true, true, true, true,
                false, true, false, true, false, false, true, true, false,
                false, false, true);
        System.out.println("\nminMax - minMaxClone");
        complexContainmentTest(minMax, minMaxClone, true, true, true, true,
                false, true, false, true, false, false, true, true, false,
                false, false, true);

        System.out.println("\nminLarger - minMax");
        complexContainmentTest(minLarger, minMax, true, true, true, true,
                false, true, false, true, true, true, true, true, false, true,
                false, true);
        System.out.println("\nminMax - minLarger");
        complexContainmentTest(minMax, minLarger, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);

        System.out.println("\nwithinMax - minMax");
        complexContainmentTest(withinMax, minMax, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);
        System.out.println("\nminMax - withinMax");
        complexContainmentTest(minMax, withinMax, true, true, true, true, true,
                true, true, true, false, false, true, true, false, false, true,
                true);

        System.out.println("\nwithinLarger - minMax");
        complexContainmentTest(withinLarger, minMax, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);
        System.out.println("\nminMax - withinLarger");
        complexContainmentTest(minMax, withinLarger, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false, false);

        System.out.println("\nmaxLarger - minMax");
        complexContainmentTest(maxLarger, minMax, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);
        System.out.println("\nminMax - maxLarger");
        complexContainmentTest(minMax, maxLarger, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);


    }

    private <T extends Number & Comparable<T>> void simpleContainmentTest(
            Interval<T> left, Interval<T> right, Boolean leftContainsRight,
            Boolean rightContainsLeft) {

        assertEquals(left.contains(right), leftContainsRight);
        assertEquals(right.contains(left), rightContainsLeft);
    }

    private <T extends Number & Comparable<T>> void complexContainmentTest(
            Interval<T> left, Interval<T> right, Boolean leftULrightUL,
            Boolean leftULrightU, Boolean leftULrightL, Boolean leftULright,
            Boolean leftUrightUL, Boolean leftUrightU, Boolean leftUrightL,
            Boolean leftUright, Boolean leftLrightUL, Boolean leftLrightU,
            Boolean leftLrightL, Boolean leftLright, Boolean leftrightUL,
            Boolean leftrightU, Boolean leftrightL, Boolean leftright) {


        Boolean leftContainsLowerBound = left.containsLowerBound();
        Boolean leftContainsUpperBound = left.containsUpperBound();
        Boolean rightContainsLowerBound = right.containsLowerBound();
        Boolean rightContainsUpperBound = right.containsUpperBound();


        Interval<T> leftCopy = new Interval<T>(left.getLowerBound(),
                left.getUpperBound(), leftContainsLowerBound,
                leftContainsUpperBound);
        Interval<T> rightCopy = new Interval<T>(right.getLowerBound(),
                right.getUpperBound(), rightContainsLowerBound,
                rightContainsUpperBound);


        int i = 1;

        leftCopy = new Interval<T>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), true, true);
        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);

        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftULrightUL);
        assertEquals(leftCopy.contains(rightCopy), leftULrightUL);


        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftULrightU);
        assertEquals(leftCopy.contains(rightCopy), leftULrightU);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftULrightL);
        assertEquals(leftCopy.contains(rightCopy), leftULrightL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftULright);
        assertEquals(leftCopy.contains(rightCopy), leftULright);


        leftCopy = new Interval<T>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), false, true);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftUrightUL);
        assertEquals(leftCopy.contains(rightCopy), leftUrightUL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftUrightU);
        assertEquals(leftCopy.contains(rightCopy), leftUrightU);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftUrightL);
        assertEquals(leftCopy.contains(rightCopy), leftUrightL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftUright);
        assertEquals(leftCopy.contains(rightCopy), leftUright);


        leftCopy = new Interval<T>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), true, false);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftLrightUL);
        assertEquals(leftCopy.contains(rightCopy), leftLrightUL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");

        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftLrightU);
        assertEquals(leftCopy.contains(rightCopy), leftLrightU);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");

        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftLrightL);
        assertEquals(leftCopy.contains(rightCopy), leftLrightL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftLright);
        assertEquals(leftCopy.contains(rightCopy), leftLright);


        leftCopy = new Interval<T>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), false, false);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftrightUL);
        assertEquals(leftCopy.contains(rightCopy), leftrightUL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");

        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftrightU);
        assertEquals(leftCopy.contains(rightCopy), leftrightU);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftrightL);
        assertEquals(leftCopy.contains(rightCopy), leftrightL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.contains(rightCopy)
                + " Should be: " + leftright);
        assertEquals(leftCopy.contains(rightCopy), leftright);

    }


    /**
     * Test of intersects method, of class Interval.
     */
    @Test
    public void testIntersects() {

        System.out.println("intersects");

        testIntersects(intMin, intMax, intWithin, intSmaller, intLarger);
        testIntersects(doubleMin, doubleMax, doubleWithin, doubleSmaller,
                doubleLarger);

        System.out.println("\tSuccess.");
    }


    private <T extends Number & Comparable<T>> void testIntersects(T min,
            T max, T within, T smaller, T larger) {


        // [smaller]
        Interval<T> smallerI = new Interval<T>(smaller, smaller);
        // [min]
        Interval<T> minI = new Interval<T>(min, min);
        // [within]
        Interval<T> withinI = new Interval<T>(within, within);
        // [max]
        Interval<T> maxI = new Interval<T>(max, max);
        // [larger]
        Interval<T> largerI = new Interval<T>(larger, larger);

        simpleIntersectionTest(smallerI, minI, false);
        simpleIntersectionTest(smallerI, withinI, false);
        simpleIntersectionTest(smallerI, maxI, false);
        simpleIntersectionTest(smallerI, largerI, false);

        simpleIntersectionTest(minI, withinI, false);
        simpleIntersectionTest(minI, maxI, false);
        simpleIntersectionTest(minI, largerI, false);

        simpleIntersectionTest(withinI, maxI, false);
        simpleIntersectionTest(withinI, largerI, false);

        simpleIntersectionTest(maxI, largerI, false);

        simpleIntersectionTest(minI, withinI, false);

        simpleIntersectionTest(smallerI, smallerI, true);
        simpleIntersectionTest(minI, minI, true);
        simpleIntersectionTest(withinI, withinI, true);
        simpleIntersectionTest(maxI, maxI, true);
        simpleIntersectionTest(largerI, largerI, true);


        // [smaller, min]
        Interval<T> smallerMin = new Interval<T>(smaller, min);
        // [smaller, within]
        Interval<T> smallerWithin = new Interval<T>(smaller, within);
        // [smaller, max]
        Interval<T> smallerMax = new Interval<T>(smaller, max);
        // [smaller, larger]
        Interval<T> smallerLarger = new Interval<T>(smaller, larger);
        // [min, within]
        Interval<T> minWithin = new Interval<T>(min, within);
        // [min, max]
        Interval<T> minMax = new Interval<T>(min, max);
        // [min, max]
        Interval<T> minMaxClone = new Interval<T>(min, max);
        // [min, larger]
        Interval<T> minLarger = new Interval<T>(min, larger);
        // [within, max]
        Interval<T> withinMax = new Interval<T>(within, max);
        // [within, larger]
        Interval<T> withinLarger = new Interval<T>(within, larger);
        // [max, larger]
        Interval<T> maxLarger = new Interval<T>(max, larger);


        System.out.println("minMax - smallerI");
        complexIntersectionTest(minMax, smallerI, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);
        System.out.println("\nminMax - minI");
        complexIntersectionTest(minMax, minI, true, true, true, true, false,
                false, false, false, true, true, true, true, false, false,
                false, false);
        System.out.println("\nminMax - withinI");
        complexIntersectionTest(minMax, withinI, true, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true);
        System.out.println("\nminMax - maxI");
        complexIntersectionTest(minMax, maxI, true, true, true, true, true,
                true, true, true, false, false, false, false, false, false,
                false, false);
        System.out.println("\nminMax - largerI");
        complexIntersectionTest(minMax, largerI, false, false, false, false,
                false, false, false, false, false, false, false, false, false,
                false, false, false);

        System.out.println("\nsmallerMin - minMax");
        complexIntersectionTest(smallerMin, minMax, true, false, true, false,
                true, false, true, false, false, false, false, false, false,
                false, false, false);
        System.out.println("\nminMax - smallerMin");
        complexIntersectionTest(minMax, smallerMin, true, true, false, false,
                false, false, false, false, true, true, false, false, false,
                false, false, false);

        System.out.println("\nsmallerWithin - minMax");
        complexIntersectionTest(smallerWithin, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        System.out.println("\nminMax - smallerWithin");
        complexIntersectionTest(minMax, smallerWithin, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        System.out.println("\nsmallerMax - minMax");
        complexIntersectionTest(smallerMax, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        System.out.println("\nminMax - smallerMax");
        complexIntersectionTest(minMax, smallerMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        System.out.println("\nsmallerLarger - minMax");
        complexIntersectionTest(smallerLarger, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        System.out.println("\nminMax - smallerLarger");
        complexIntersectionTest(minMax, smallerLarger, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        System.out.println("\nminWithin - minMax");
        complexIntersectionTest(minWithin, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        System.out.println("\nminMax - minWithin");
        complexIntersectionTest(minMax, minWithin, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        System.out.println("\nminMaxClone - minMax");
        complexIntersectionTest(minMaxClone, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        System.out.println("\nminMax - minMaxClone");
        complexIntersectionTest(minMax, minMaxClone, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        System.out.println("\nminLarger - minMax");
        complexIntersectionTest(minLarger, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        System.out.println("\nminMax - minLarger");
        complexIntersectionTest(minMax, minLarger, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        System.out.println("\nwithinMax - minMax");
        complexIntersectionTest(withinMax, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        System.out.println("\nminMax - withinMax");
        complexIntersectionTest(minMax, withinMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        System.out.println("\nwithinLarger - minMax");
        complexIntersectionTest(withinLarger, minMax, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);
        System.out.println("\nminMax - withinLarger");
        complexIntersectionTest(minMax, withinLarger, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true,
                true, true);

        System.out.println("\nmaxLarger - minMax");
        complexIntersectionTest(maxLarger, minMax, true, true, false, false,
                false, false, false, false, true, true, false, false, false,
                false, false, false);
        System.out.println("\nminMax - maxLarger");
        complexIntersectionTest(minMax, maxLarger, true, false, true, false,
                true, false, true, false, false, false, false, false, false,
                false, false, false);
    }

    private <T extends Number & Comparable<T>> void simpleIntersectionTest(
            Interval<T> left, Interval<T> right, Boolean intersects) {

        assertEquals(left.intersects(right), intersects);
        assertEquals(right.intersects(left), intersects);
    }

    private <T extends Number & Comparable<T>> void complexIntersectionTest(
            Interval<T> left, Interval<T> right, Boolean leftULrightUL,
            Boolean leftULrightU, Boolean leftULrightL, Boolean leftULright,
            Boolean leftUrightUL, Boolean leftUrightU, Boolean leftUrightL,
            Boolean leftUright, Boolean leftLrightUL, Boolean leftLrightU,
            Boolean leftLrightL, Boolean leftLright, Boolean leftrightUL,
            Boolean leftrightU, Boolean leftrightL, Boolean leftright) {


        int i = 1;

        Interval<T> leftCopy = new Interval<T>(left.getLowerBound(),
                left.getUpperBound(), true, true);

        Interval<T> rightCopy = new Interval<T>(right.getLowerBound(),
                right.getUpperBound(), true, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftULrightUL);
        assertEquals(leftCopy.intersects(rightCopy), leftULrightUL);


        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftULrightU);
        assertEquals(leftCopy.intersects(rightCopy), leftULrightU);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftULrightL);
        assertEquals(leftCopy.intersects(rightCopy), leftULrightL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftULright);
        assertEquals(leftCopy.intersects(rightCopy), leftULright);


        leftCopy = new Interval<T>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), false, true);


        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftUrightUL);
        assertEquals(leftCopy.intersects(rightCopy), leftUrightUL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftUrightU);
        assertEquals(leftCopy.intersects(rightCopy), leftUrightU);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftUrightL);
        assertEquals(leftCopy.intersects(rightCopy), leftUrightL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftUright);
        assertEquals(leftCopy.intersects(rightCopy), leftUright);


        leftCopy = new Interval<T>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), true, false);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftLrightUL);
        assertEquals(leftCopy.intersects(rightCopy), leftLrightUL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");

        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftLrightU);
        assertEquals(leftCopy.intersects(rightCopy), leftLrightU);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");

        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftLrightL);
        assertEquals(leftCopy.intersects(rightCopy), leftLrightL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftLright);
        assertEquals(leftCopy.intersects(rightCopy), leftLright);


        leftCopy = new Interval<T>(leftCopy.getLowerBound(),
                leftCopy.getUpperBound(), false, false);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftrightUL);
        assertEquals(leftCopy.intersects(rightCopy), leftrightUL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, true);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");

        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftrightU);
        assertEquals(leftCopy.intersects(rightCopy), leftrightU);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), true, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftrightL);
        assertEquals(leftCopy.intersects(rightCopy), leftrightL);

        rightCopy = new Interval<T>(rightCopy.getLowerBound(),
                rightCopy.getUpperBound(), false, false);
        System.out.print(i + ": ");
        i++;
        System.out.print(rightCopy + " E " + leftCopy + "?");
        System.out.println(" Result: " + leftCopy.intersects(rightCopy)
                + " Should be: " + leftright);
        assertEquals(leftCopy.intersects(rightCopy), leftright);


    }

    /**
     * Test of isContainedIn method, of class Interval.
     */
    @Test
    public void testIsContainedIn() {

        System.out.println("isContainedIn");

        testIsContainedIn(intMin, intMax, intWithin, intSmaller, intLarger);
        testIsContainedIn(doubleMin, doubleMax, doubleWithin, doubleSmaller,
                doubleLarger);

        System.out.println("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testIsContainedIn(T min,
            T max, T within, T smaller, T larger) {

        Interval<T> minMax = new Interval<T>(min, max);
        Interval<T> smallerLarger = new Interval<T>(smaller, larger);

        assertEquals(minMax.contains(smallerLarger),
                smallerLarger.isContainedIn(minMax));
        assertEquals(smallerLarger.contains(minMax),
                minMax.isContainedIn(smallerLarger));

        assertEquals(minMax.isContainedIn(null), false);


    }

    /**
     * Test of equals method, of class Interval.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");

        testEquals(intMin, intMax, intWithin, intSmaller, intLarger);
        testEquals(doubleMin, doubleMax, doubleWithin, doubleSmaller,
                doubleLarger);

        System.out.println("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testEquals(T min, T max,
            T within, T smaller, T larger) {

        Interval<T> interval = new Interval<T>(min, max);

        Interval<T> equalInterval = new Interval<T>(min, max);
        Interval<T> intervalWithoutLowerBound = new Interval<T>(min, max,
                false, true);
        Interval<T> intervalWithoutUpperBound = new Interval<T>(min, max, true,
                false);
        Interval<T> intervalWithoutBounds = new Interval<T>(min, max, false,
                false);

        Interval<T> smallerMax = new Interval<T>(smaller, max);
        Interval<T> minLarger = new Interval<T>(within, larger);
        Interval<T> withinI = new Interval<T>(within, within);

        assertEquals(interval.equals(null), false);
        assertEquals(interval.equals(max), false);

        assertEquals(interval.equals(interval), true);
        assertEquals(interval.equals(equalInterval), true);
        assertEquals(interval.equals(intervalWithoutLowerBound), false);
        assertEquals(interval.equals(intervalWithoutUpperBound), false);
        assertEquals(interval.equals(intervalWithoutBounds), false);

        assertEquals(interval.equals(smallerMax), false);
        assertEquals(interval.equals(minLarger), false);
        assertEquals(interval.equals(withinI), false);
    }

    /**
     * Test of hashCode method, of class Interval.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");

        testHashCode(intMin, intMax);
        testHashCode(doubleMin, doubleMax);

        System.out.println("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testHashCode(T min, T max) {

        Interval<T> interval = new Interval<T>(min, max);
        Interval<T> equalInterval = new Interval<T>(min, max);

        assertEquals(interval, equalInterval);
        assertEquals(interval.hashCode(), equalInterval.hashCode());

    }

    /**
     * Test of toString method, of class Interval.
     */
    @Test
    public void testToString() {

        System.out.println("toString");

        testToString(intMin, intMax);
        testToString(doubleMin, doubleMax);

        System.out.println("\tSuccess.");
    }

    private <T extends Number & Comparable<T>> void testToString(T min, T max) {

        String squareBracketOpen = "[";
        String squareBracketClose = "]";
        String parenthesisOpen = "(";
        String parenthesisClose = ")";
        String separator = ", ";


        Interval<T> a = new Interval<T>(min, min);
        String zeroInterval = squareBracketOpen + min.toString()
                + squareBracketClose;
        assertEquals(zeroInterval, a.toString());

        Interval<T> b = new Interval<T>(min, max);
        String zeroOneInterval = squareBracketOpen + min.toString() + separator
                + max.toString() + squareBracketClose;
        assertEquals(zeroOneInterval, b.toString());

        b = new Interval<T>(b.getLowerBound(), b.getUpperBound(), false,
                b.containsUpperBound());
        zeroOneInterval = parenthesisOpen + min.toString() + separator
                + max.toString() + squareBracketClose;
        assertEquals(zeroOneInterval, b.toString());

        b = new Interval<T>(b.getLowerBound(), b.getUpperBound(),
                b.containsLowerBound(), false);
        zeroOneInterval = parenthesisOpen + min.toString() + separator
                + max.toString() + parenthesisClose;
        assertEquals(zeroOneInterval, b.toString());

        b = new Interval<T>(b.getLowerBound(), b.getUpperBound(), true,
                b.containsUpperBound());
        zeroOneInterval = squareBracketOpen + min.toString() + separator
                + max.toString() + parenthesisClose;
        assertEquals(zeroOneInterval, b.toString());

    }

}
