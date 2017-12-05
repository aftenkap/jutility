package org.jutility.common.datatype.util;


//@formatter:off
/*
 * #%L
 * jutility-common
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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



/**
 * The {@code NumberComparator} class provides comparison operations between
 * {@link Number Numbers}, including value and {@link Number} type precision.
 * <p>
 * Note: currently, only {@link Double}, {@link Float}, {@link Long}, @{link
 * Integer}, {@link Short}, and {@link Byte} are supported.
 * </p>
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public final class NumberComparator {



    /**
     * Compares two {@link Number Numbers} with the default precision for
     * floating-point comparison defined in {@link NumberConstants#DELTA_F} and
     * {@link NumberConstants#DELTA_D}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return the sum of the numbers.
     */
    public static final <T extends Number, S extends Number> int compareTo(
            final T lhs, final S rhs) {

        Integer result = null;


        if (lhs instanceof Double) {

            result = NumberComparator.compareTo((Double) lhs, rhs);
        }
        else if (rhs instanceof Double) {

            result = NumberComparator.compareTo(lhs, (Double) rhs);
        }
        else if (lhs instanceof Float) {

            result = NumberComparator.compareTo((Float) lhs, rhs);
        }
        else if (rhs instanceof Float) {

            result = NumberComparator.compareTo(lhs, (Float) rhs);
        }
        else if (lhs instanceof Long) {

            result = NumberComparator.compareTo((Long) lhs, rhs);
        }
        else if (rhs instanceof Long) {

            result = NumberComparator.compareTo(lhs, (Long) rhs);
        }
        else if (lhs instanceof Integer) {

            result = NumberComparator.compareTo((Integer) lhs, rhs);
        }
        else if (rhs instanceof Integer) {

            result = NumberComparator.compareTo(lhs, (Integer) rhs);
        }
        else if (lhs instanceof Short) {

            result = NumberComparator.compareTo((Short) lhs, rhs);
        }
        else if (rhs instanceof Short) {

            result = NumberComparator.compareTo(lhs, (Short) rhs);
        }
        else if (lhs instanceof Byte) {

            result = NumberComparator.compareTo((Byte) lhs, rhs);
        }
        else if (rhs instanceof Byte) {

            result = NumberComparator.compareTo(lhs, (Byte) rhs);
        }



        if (result == null) {

            throw new IllegalArgumentException(
                    "Cannot compare a value of type " + lhs.getClass()
                    + " to a value of type " + rhs.getClass() + "!");
        }

        return result;
    }

    /**
     * Compares two {@link Number Numbers} with the provided precision for
     * floating-point comparison.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     * @param delta
     *         the precision of the floating-point comparison (ignored for
     *         non-floating-point {@link Number Numbers}).
     *
     * @return the sum of the numbers.
     */
    public static final <T extends Number, S extends Number> int compareTo(
            final T lhs, final S rhs, final Number delta) {

        Integer result = null;


        if (lhs instanceof Double) {

            result = NumberComparator.compareTo((Double) lhs, rhs,
                    delta.doubleValue());
        }
        else if (rhs instanceof Double) {

            result = NumberComparator.compareTo(lhs, (Double) rhs,
                    delta.doubleValue());
        }
        else if (lhs instanceof Float) {

            result = NumberComparator.compareTo((Float) lhs, rhs,
                    delta.floatValue());
        }
        else if (rhs instanceof Float) {

            result = NumberComparator.compareTo(lhs, (Float) rhs,
                    delta.floatValue());
        }
        else if (lhs instanceof Long) {

            result = NumberComparator.compareTo((Long) lhs, rhs);
        }
        else if (rhs instanceof Long) {

            result = NumberComparator.compareTo(lhs, (Long) rhs);
        }
        else if (lhs instanceof Integer) {

            result = NumberComparator.compareTo((Integer) lhs, rhs);
        }
        else if (rhs instanceof Integer) {

            result = NumberComparator.compareTo(lhs, (Integer) rhs);
        }
        else if (lhs instanceof Short) {

            result = NumberComparator.compareTo((Short) lhs, rhs);
        }
        else if (rhs instanceof Short) {

            result = NumberComparator.compareTo(lhs, (Short) rhs);
        }
        else if (lhs instanceof Byte) {

            result = NumberComparator.compareTo((Byte) lhs, rhs);
        }
        else if (rhs instanceof Byte) {

            result = NumberComparator.compareTo(lhs, (Byte) rhs);
        }



        if (result == null) {

            throw new IllegalArgumentException(
                    "Cannot compare a value of type " + lhs.getClass()
                    + " to a value of type " + rhs.getClass() + "!");
        }

        return result;
    }

    private static <T extends Number> int compareTo(final Double lhs,
            final T rhs) {

        return NumberComparator.compareTo(lhs, rhs, NumberConstants.DELTA_D);
    }

    private static <T extends Number> int compareTo(final Double lhs,
            final T rhs, final double delta) {

        final Double rhsDouble = rhs.doubleValue();

        final Double difference = (lhs - rhsDouble);

        if (Math.abs(difference) < delta) {
            return 0;
        }
        else if (difference < 0) {
            return -1;
        }
        else {
            return 1;
        }
    }

    private static <T extends Number> int compareTo(final T lhs,
            final Double rhs) {

        return NumberComparator.compareTo(lhs, rhs, NumberConstants.DELTA_D);
    }

    private static <T extends Number> int compareTo(final T lhs,
            final Double rhs, final double delta) {

        final Double lhsDouble = lhs.doubleValue();

        final Double difference = (lhsDouble - rhs);

        if (Math.abs(difference) < delta) {
            return 0;
        }
        else if (difference < 0) {
            return -1;
        }
        else {
            return 1;
        }
    }


    private static <T extends Number> int compareTo(final Float lhs,
            final T rhs) {

        return NumberComparator.compareTo(lhs, rhs, NumberConstants.DELTA_F);
    }

    private static <T extends Number> int compareTo(final Float lhs,
            final T rhs, final float delta) {

        final Float rhsFloat = rhs.floatValue();

        final Float difference = (lhs - rhsFloat);

        if (Math.abs(difference) < delta) {
            return 0;
        }
        else if (difference < 0) {
            return -1;
        }
        else {
            return 1;
        }
    }

    private static <T extends Number> int compareTo(final T lhs,
            final Float rhs) {

        return NumberComparator.compareTo(lhs, rhs, NumberConstants.DELTA_F);
    }

    private static <T extends Number> int compareTo(final T lhs,
            final Float rhs, final float delta) {

        final Float lhsDouble = lhs.floatValue();

        final Float difference = (lhsDouble - rhs);

        if (Math.abs(difference) < delta) {
            return 0;
        }
        else if (difference < 0) {
            return -1;
        }
        else {
            return 1;
        }
    }

    private static <T extends Number> int compareTo(final Long lhs,
            final T rhs) {

        final Long rhsLong = rhs.longValue();

        return lhs.compareTo(rhsLong);
    }

    private static <T extends Number> int compareTo(final T lhs,
            final Long rhs) {

        final Long lhsLong = lhs.longValue();

        return lhsLong.compareTo(rhs);
    }

    private static <T extends Number> int compareTo(final Integer lhs,
            final T rhs) {

        final Integer rhsInteger = rhs.intValue();

        return lhs.compareTo(rhsInteger);
    }

    private static <T extends Number> int compareTo(final T lhs,
            final Integer rhs) {

        final Integer lhsInteger = lhs.intValue();

        return lhsInteger.compareTo(rhs);
    }

    private static <T extends Number> int compareTo(final Short lhs,
            final T rhs) {

        final Short rhsShort = rhs.shortValue();

        return lhs.compareTo(rhsShort);
    }

    private static <T extends Number> int compareTo(final T lhs,
            final Short rhs) {

        final Short lhsShort = lhs.shortValue();

        return lhsShort.compareTo(rhs);
    }

    private static <T extends Number> int compareTo(final Byte lhs,
            final T rhs) {

        final Byte rhsByte = rhs.byteValue();

        return lhs.compareTo(rhsByte);
    }

    private static <T extends Number> int compareTo(final T lhs,
            final Byte rhs) {

        final Byte lhsByte = lhs.byteValue();

        return lhsByte.compareTo(rhs);
    }



    /**
     * Returns whether the left-hand side {@link Number} is smaller than the
     * right-hand side {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side is numerically smaller than
     * the right-hand side; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean
    smallerThan(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.compareTo(lhs, rhs);

        return difference < 0;
    }

    /**
     * Returns whether the left-hand side {@link Number} is smaller than or
     * equal to the right-hand side {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side is numerically smaller than
     * or equal to the right-hand side; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean
    smallerOrEqual(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.compareTo(lhs, rhs);

        return difference <= 0;
    }



    /**
     * Returns whether the left-hand side {@link Number} is equal to the
     * right-hand side {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side is numerically equal to the
     * right-hand side; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean equals(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.compareTo(lhs, rhs);

        return difference == 0;
    }

    /**
     * Returns whether the left-hand side {@link Number} is greater than or
     * equal to the right-hand side {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side is numerically greater than
     * or equal to the right-hand side; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean
    greaterOrEqual(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.compareTo(lhs, rhs);

        return difference >= 0;

    }

    /**
     * Returns whether the left-hand side {@link Number} is greater than the
     * right-hand side {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side is numerically greater than
     * the right-hand side; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean
    greaterThan(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.compareTo(lhs, rhs);

        return difference > 0;

    }


    /**
     * Compares the precision of two {@link Number Numbers}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code -1} if the left-hand side precision is smaller; {@code 0}
     * if the precisions are equal; {@code 1} if the left-hand side precision is
     * greater.
     */
    public static final <T extends Number, S extends Number> int
    comparePrecision(
            final T lhs, final S rhs) {


        // lhs instance of Double
        if (lhs instanceof Double) {
            if (rhs instanceof Double) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs not instance of Double
        if (rhs instanceof Double) {
            return -1;
        }


        // lhs instance of Float
        if (lhs instanceof Float) {
            if (rhs instanceof Float) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs not instance of Float
        if (rhs instanceof Float) {
            return -1;
        }


        // lhs instance of Long
        if (lhs instanceof Long) {
            if (rhs instanceof Long) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs not instance of Long
        if (rhs instanceof Long) {
            return -1;
        }


        // lhs instance of Integer
        if (lhs instanceof Integer) {
            if (rhs instanceof Integer) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs not instance of Integer
        if (rhs instanceof Integer) {
            return -1;
        }


        // lhs instance of Short
        if (lhs instanceof Short) {
            if (rhs instanceof Short) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs not instance of Short
        if (rhs instanceof Short) {
            return -1;
        }


        // lhs instance of Byte
        if (lhs instanceof Byte) {
            if (rhs instanceof Byte) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs not instance of Byte
        if (rhs instanceof Byte) {
            return -1;
        }



        throw new IllegalArgumentException(
                "Cannot compare precision of a value of type " + lhs.getClass()
                + " to the precision of a value of type " + rhs.getClass()
                + "!");
    }



    /**
     * Returns whether the precision of the left-hand side {@link Number} is
     * smaller than the precision of the right-hand side {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side precision is smaller than the
     * right-hand side precision; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean
    smallerPrecision(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.comparePrecision(lhs, rhs);

        return difference < 0;

    }

    /**
     * Returns whether the precision of the left-hand side {@link Number} is
     * smaller than or equal to the precision of the right-hand side
     * {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side precision is smaller than or
     * equal to the right-hand side precision; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean
    smallerOrEqualPrecision(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.comparePrecision(lhs, rhs);

        return difference <= 0;

    }

    /**
     * Returns whether the precision of the left-hand side {@link Number} is
     * equal to the precision of the right-hand side {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side precision is equal to the
     * right-hand side precision; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean
    equalPrecision(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.comparePrecision(lhs, rhs);

        return difference == 0;

    }

    /**
     * Returns whether the precision of the left-hand side {@link Number} is
     * greater than or equal to the precision of the right-hand side
     * {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side precision is greater than or
     * equal to the right-hand side precision; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean
    greaterOrEqualPrecision(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.comparePrecision(lhs, rhs);

        return difference >= 0;

    }

    /**
     * Returns whether the precision of the left-hand side {@link Number} is
     * greater than the precision of the right-hand side {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side precision is greater than the
     * right-hand side precision; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean
    greaterPrecision(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.comparePrecision(lhs, rhs);

        return difference > 0;

    }

    /**
     * Returns the {@link Class} of the {@link Number} with the greatest
     * precision of the {@link Number Numbers} provided.
     *
     * @param numbers
     *         the {@link Number Numbers} to compare.
     *
     * @return the {@link Class} with the greater precision.
     */
    public static final Class<? extends Number> greaterPrecisionType(
            final Number... numbers) {

        Class<? extends Number> greatestPrecisionType = null;

        for (final Number number : numbers) {
            if (greatestPrecisionType == null) {
                greatestPrecisionType = number.getClass();
            }
            else {

                final List<Class<? extends Number>> classes = new ArrayList<>(2);
                classes.add(greatestPrecisionType);
                classes.add(number.getClass());
                greatestPrecisionType = NumberComparator.greatestPrecisionType(
                        classes);
            }
        }

        return greatestPrecisionType;
    }


    /**
     * Compares the precision of two {@link Class Classes}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code -1} if the left-hand side precision is smaller; {@code 0}
     * if the precisions are equal; {@code 1} if the left-hand side precision is
     * greater.
     */
    public static final <T extends Number, S extends Number> int
    comparePrecision(
            final Class<? extends T> lhs, final Class<? extends S> rhs) {


        // lhs is Double
        if (lhs == Double.class) {
            if (rhs == Double.class) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs is not Double
        if (rhs == Double.class) {
            return -1;
        }


        // lhs is Float
        if (lhs == Float.class) {
            if (rhs == Float.class) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs is not Float
        if (rhs == Float.class) {
            return -1;
        }


        // lhs is Long
        if (lhs == Long.class) {
            if (rhs == Long.class) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs is not Long
        if (rhs == Long.class) {
            return -1;
        }


        // lhs is Integer
        if (lhs == Integer.class) {
            if (rhs == Integer.class) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs is not Integer
        if (rhs == Integer.class) {
            return -1;
        }


        // lhs is Short
        if (lhs == Short.class) {
            if (rhs == Short.class) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs is not Short
        if (rhs == Short.class) {
            return -1;
        }


        // lhs is Byte
        if (lhs == Byte.class) {
            if (rhs == Byte.class) {
                return 0;
            }
            else {
                return 1;
            }
        }
        // lhs is not Byte
        if (rhs == Byte.class) {
            return -1;
        }



        throw new IllegalArgumentException(
                "Cannot compare precision of  type " + lhs
                + " to the precision of  type " + rhs + "!");
    }


    /**
     * Compares the precision of the provided {@link Class Classes} and returns
     * the {@link Class} with the greatest precision.
     *
     * @param numberTypes
     *         the number types to compare.
     *
     * @return the {@link Class} with the greatest precision.
     */
    public static final Class<? extends Number> greatestPrecisionType(
            final Collection<Class<? extends Number>> numberTypes) {

        Class<? extends Number> greatestPrecisionType = null;

        for (final Class<? extends Number> numberType : numberTypes) {
            if (greatestPrecisionType == null) {

                greatestPrecisionType = numberType;
            }
            else if (NumberComparator.comparePrecision(greatestPrecisionType,
                    numberType) < 0) {

                greatestPrecisionType = numberType;
            }
        }

        return greatestPrecisionType;
    }

    /**
     * Returns the {@link Number} format of the number with the smaller of the
     * precisions of the two {@link Number Numbers}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return the {@link Class} with the smaller precision.
     */
    public static final <T extends Number, S extends Number> Class<? extends
            Number> smallerPrecisionType(
            final T lhs, final S rhs) {

        final int difference = NumberComparator.comparePrecision(lhs, rhs);

        if (difference < 0) {
            return lhs.getClass();
        }

        return rhs.getClass();
    }



    /**
     * Returns whether the precision of the left-hand side {@link Number} is
     * equal to the precision of the right-hand side {@link Number}.
     *
     * @param <T>
     *         the type of the left-hand side number.
     * @param <S>
     *         the type of the right-hand side number.
     * @param lhs
     *         the left-hand side.
     * @param rhs
     *         the right-hand side.
     *
     * @return {@code true}, if the left-hand side value is equal to the
     * right-hand side value, and the left-hand side precision is equal to the
     * right-hand side precision; {@code false} otherwise.
     */
    public static final <T extends Number, S extends Number> boolean
    equalValueAndPrecision(
            final T lhs, final S rhs) {

        final int differenceValue = NumberComparator.compareTo(lhs, rhs);
        final int differencePrecision = NumberComparator.comparePrecision(lhs,
                rhs);

        return (differenceValue == 0) && (differencePrecision == 0);

    }
}
