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
import org.jutility.common.datatype.util.NumberUtils;


/**
 * THe {@code ArithmeticOperations} class provides factory methods for
 * arithmetic operations.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public final class ArithmeticOperations {

    /**
     * Calculates the sum of the provided numbers.
     *
     * @param <T>
     *            the {@link Number} type.
     *
     * @param numbers
     *            the numbers.
     * @return the sum of the numbers.
     */
    @SafeVarargs
    public static final <T extends Number> T sum(final T... numbers) {

        if (numbers.length == 0) {

            throw new IllegalArgumentException(
                    "Need to have at least one operand to calculate sum.");
        }
        else if (numbers.length == 1) {

            return numbers[0];
        }
        else {

            T sum = ArithmeticOperations.add(numbers[0], numbers[1]);

            for (int i = 2; i < numbers.length; i++) {

                sum = ArithmeticOperations.add(sum, numbers[i]);
            }
            return sum;
        }
    }

    /**
     * Calculates the sum of the provided numbers.
     *
     * @param <R>
     *            the {@link Number} type of the return type.
     *
     * @param returnType
     *            the desired return type.
     *
     * @param numbers
     *            the numbers.
     * @return the sum of the numbers.
     */
    public static final <R extends Number> R sum(
            final Class<? extends R> returnType, final Number... numbers) {

        if (numbers.length == 0) {

            throw new IllegalArgumentException(
                    "Need to have at least one operand to calculate sum.");
        }
        else if (numbers.length == 1) {

            return NumberUtils.cast(numbers[0], returnType);
        }
        else {

            R sum = ArithmeticOperations
                    .add(numbers[0], numbers[1], returnType);

            for (int i = 2; i < numbers.length; i++) {

                sum = ArithmeticOperations.add(sum, numbers[i], returnType);
            }

            return sum;
        }
    }

    /**
     * Calculates the product of the provided numbers.
     *
     * @param <T>
     *            the {@link Number} type.
     *
     * @param numbers
     *            the numbers.
     * @return the product of the numbers.
     */
    @SafeVarargs
    public static final <T extends Number> T product(final T... numbers) {

        if (numbers.length == 0) {

            throw new IllegalArgumentException(
                    "Need to have at least one operand to calculate product.");
        }
        else if (numbers.length == 1) {

            return numbers[0];
        }
        else {

            T product = ArithmeticOperations.multiply(numbers[0], numbers[1]);

            for (int i = 2; i < numbers.length; i++) {

                product = ArithmeticOperations.multiply(product, numbers[i]);
            }

            return product;
        }
    }

    /**
     * Calculates the product of the provided numbers.
     *
     * @param <R>
     *            the {@link Number} type of the return type.
     *
     *
     * @param returnType
     *            the desired return type.
     *
     * @param numbers
     *            the numbers.
     * @return the product of the numbers.
     */
    public static final <R extends Number> R product(
            final Class<? extends R> returnType, final Number... numbers) {

        if (numbers.length == 0) {

            throw new IllegalArgumentException(
                    "Need to have at least one operand to calculate product.");
        }
        else if (numbers.length == 1) {

            return NumberUtils.cast(numbers[0], returnType);
        }
        else {

            R product = ArithmeticOperations.multiply(numbers[0], numbers[1],
                    returnType);

            for (int i = 2; i < numbers.length; i++) {
                product = ArithmeticOperations.multiply(product, numbers[i],
                        returnType);
            }
            return product;
        }
    }

    /**
     * Adds two numbers.
     *
     * @param <T>
     *            the {@link Number} type of the left-hand side.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the sum of the numbers.
     */
    @SuppressWarnings("unchecked")
    public static final <T extends Number> T add(final T lhs, final T rhs) {


        final Number result = ArithmeticOperations
                .add(lhs, rhs, lhs.getClass());

        return (T) result;
    }


    /**
     * Adds two numbers and casts the result to the desired return type.
     *
     * @param <R>
     *            the {@link Number} type of the returned number.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the return type.
     * @return the sum of the numbers.
     */
    public static final <R extends Number> R add(final Number lhs,
            final Number rhs, final Class<R> returnType) {

        Number result = null;

        if (lhs instanceof Double) {

            result = ArithmeticOperations.add((Double) lhs, rhs);
        }
        else if (rhs instanceof Double) {

            result = ArithmeticOperations.add(lhs, (Double) rhs);
        }
        else if (lhs instanceof Float) {

            result = ArithmeticOperations.add((Float) lhs, rhs);
        }
        else if (rhs instanceof Float) {

            result = ArithmeticOperations.add(lhs, (Float) rhs);
        }
        else if (lhs instanceof Long) {

            result = ArithmeticOperations.add((Long) lhs, rhs);
        }
        else if (rhs instanceof Long) {

            result = ArithmeticOperations.add(lhs, (Long) rhs);
        }
        else if (lhs instanceof Integer) {

            result = ArithmeticOperations.add((Integer) lhs, rhs);
        }
        else if (rhs instanceof Integer) {

            result = ArithmeticOperations.add(lhs, (Integer) rhs);
        }
        else if (lhs instanceof Short) {

            result = ArithmeticOperations.add((Short) lhs, rhs);
        }
        else if (rhs instanceof Short) {

            result = ArithmeticOperations.add(lhs, (Short) rhs);
        }
        else if (lhs instanceof Byte) {

            result = ArithmeticOperations.add((Byte) lhs, rhs);
        }
        else if (rhs instanceof Byte) {

            result = ArithmeticOperations.add(lhs, (Byte) rhs);
        }

        if (result == null) {

            throw new UnsupportedOperationException(
                    "Cannot add a value of type " + lhs.getClass()
                            + " to a value of type " + rhs.getClass() + "!");
        }

        return NumberUtils.cast(result, returnType);
    }

    private static Double add(final Double lhs, final Number rhs) {

        final Double rhsDouble = rhs.doubleValue();

        return lhs + rhsDouble;
    }

    private static Double add(final Number lhs, final Double rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }

    private static Float add(final Float lhs, final Number rhs) {

        final Float rhsFloat = rhs.floatValue();

        return lhs + rhsFloat;
    }

    private static Float add(final Number lhs, final Float rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }

    private static Long add(final Long lhs, final Number rhs) {

        final Long rhsLong = rhs.longValue();

        return lhs + rhsLong;
    }

    private static Long add(final Number lhs, final Long rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }

    private static Integer add(final Integer lhs, final Number rhs) {

        final Integer rhsInteger = rhs.intValue();

        return lhs + rhsInteger;
    }

    private static Integer add(final Number lhs, final Integer rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }

    private static Short add(final Short lhs, final Number rhs) {

        final Short rhsShort = rhs.shortValue();

        final Integer result = lhs + rhsShort;

        return result.shortValue();
    }

    private static Short add(final Number lhs, final Short rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }

    private static Byte add(final Byte lhs, final Number rhs) {

        final Byte rhsByte = rhs.byteValue();

        final Integer result = lhs + rhsByte;

        return result.byteValue();
    }

    private static Byte add(final Number lhs, final Byte rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }



    /**
     * Subtracts two numbers.
     *
     * @param <T>
     *            the {@link Number} type of the operands.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the difference of the numbers.
     */
    @SuppressWarnings("unchecked")
    public static final <T extends Number> T subtract(final T lhs, final T rhs) {

        final Number result = ArithmeticOperations.subtract(lhs, rhs,
                lhs.getClass());

        return (T) result;
    }

    /**
     * Subtracts two numbers and casts the result to the desired return type.
     *
     * @param <R>
     *            the {@link Number} type of the returned number.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the return type.
     * @return the difference of the numbers.
     */
    public static final <R extends Number> R subtract(final Number lhs,
            final Number rhs, final Class<R> returnType) {

        Number result = null;


        if (lhs instanceof Double) {

            result = ArithmeticOperations.subtract((Double) lhs, rhs);
        }
        else if (rhs instanceof Double) {

            result = ArithmeticOperations.subtract(lhs, (Double) rhs);
        }
        else if (lhs instanceof Float) {

            result = ArithmeticOperations.subtract((Float) lhs, rhs);
        }
        else if (rhs instanceof Float) {

            result = ArithmeticOperations.subtract(lhs, (Float) rhs);
        }
        else if (lhs instanceof Long) {

            result = ArithmeticOperations.subtract((Long) lhs, rhs);
        }
        else if (rhs instanceof Long) {

            result = ArithmeticOperations.subtract(lhs, (Long) rhs);
        }
        else if (lhs instanceof Integer) {

            result = ArithmeticOperations.subtract((Integer) lhs, rhs);
        }
        else if (rhs instanceof Integer) {

            result = ArithmeticOperations.subtract(lhs, (Integer) rhs);
        }
        else if (lhs instanceof Short) {

            result = ArithmeticOperations.subtract((Short) lhs, rhs);
        }
        else if (rhs instanceof Short) {

            result = ArithmeticOperations.subtract(lhs, (Short) rhs);
        }
        else if (lhs instanceof Byte) {

            result = ArithmeticOperations.subtract((Byte) lhs, rhs);
        }
        else if (rhs instanceof Byte) {

            result = ArithmeticOperations.subtract(lhs, (Byte) rhs);
        }



        if (result == null) {

            throw new UnsupportedOperationException(
                    "Cannot subtract a value of type " + rhs.getClass()
                            + " from a value of type " + lhs.getClass() + "!");
        }

        return NumberUtils.cast(result, returnType);
    }


    private static Double subtract(final Double lhs, final Number rhs) {

        final Double rhsDouble = rhs.doubleValue();

        return lhs - rhsDouble;
    }

    private static Double subtract(final Number lhs, final Double rhs) {

        final Double lhsDouble = lhs.doubleValue();

        return lhsDouble - rhs;
    }

    private static Float subtract(final Float lhs, final Number rhs) {

        final Float rhsFloat = rhs.floatValue();

        return lhs - rhsFloat;
    }

    private static Float subtract(final Number lhs, final Float rhs) {

        final Float lhsFloat = lhs.floatValue();

        return lhsFloat - rhs;
    }

    private static Long subtract(final Long lhs, final Number rhs) {

        final Long rhsLong = rhs.longValue();

        return lhs - rhsLong;
    }

    private static Long subtract(final Number lhs, final Long rhs) {

        final Long lhsLong = lhs.longValue();

        return lhsLong - rhs;
    }

    private static Integer subtract(final Integer lhs, final Number rhs) {

        final Integer rhsInteger = rhs.intValue();

        return lhs - rhsInteger;
    }

    private static Integer subtract(final Number lhs, final Integer rhs) {

        final Integer lhsInteger = lhs.intValue();

        return lhsInteger - rhs;
    }

    private static Short subtract(final Short lhs, final Number rhs) {

        final Short rhsShort = rhs.shortValue();

        final Integer result = lhs - rhsShort;

        return result.shortValue();
    }

    private static Short subtract(final Number lhs, final Short rhs) {

        final Short lhsShort = lhs.shortValue();

        final Integer result = lhsShort - rhs;

        return result.shortValue();
    }

    private static Byte subtract(final Byte lhs, final Number rhs) {

        final Byte rhsByte = rhs.byteValue();

        final Integer result = lhs - rhsByte;

        return result.byteValue();
    }

    private static Byte subtract(final Number lhs, final Byte rhs) {

        final Byte lhsByte = lhs.byteValue();

        final Integer result = lhsByte - rhs;

        return result.byteValue();
    }


    /**
     * Multiplies two numbers.
     *
     * @param <T>
     *            the {@link Number} type of the operands.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the product of the numbers.
     */
    @SuppressWarnings("unchecked")
    public static final <T extends Number> T multiply(final T lhs, final T rhs) {

        final Number result = ArithmeticOperations.multiply(lhs, rhs,
                lhs.getClass());

        return (T) result;
    }

    /**
     * Multiplies two numbers and casts the result to the desired return type.
     *
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the return type.
     * @return the product of the numbers.
     */
    public static final <R extends Number> R multiply(final Number lhs,
            final Number rhs, final Class<R> returnType) {

        Number result = null;


        if (lhs instanceof Double) {

            result = ArithmeticOperations.multiply((Double) lhs, rhs);
        }
        else if (rhs instanceof Double) {

            result = ArithmeticOperations.multiply(lhs, (Double) rhs);
        }
        else if (lhs instanceof Float) {

            result = ArithmeticOperations.multiply((Float) lhs, rhs);
        }
        else if (rhs instanceof Float) {

            result = ArithmeticOperations.multiply(lhs, (Float) rhs);
        }
        else if (lhs instanceof Long) {

            result = ArithmeticOperations.multiply((Long) lhs, rhs);
        }
        else if (rhs instanceof Long) {

            result = ArithmeticOperations.multiply(lhs, (Long) rhs);
        }
        else if (lhs instanceof Integer) {

            result = ArithmeticOperations.multiply((Integer) lhs, rhs);
        }
        else if (rhs instanceof Integer) {

            result = ArithmeticOperations.multiply(lhs, (Integer) rhs);
        }
        else if (lhs instanceof Short) {

            result = ArithmeticOperations.multiply((Short) lhs, rhs);
        }
        else if (rhs instanceof Short) {

            result = ArithmeticOperations.multiply(lhs, (Short) rhs);
        }
        else if (lhs instanceof Byte) {

            result = ArithmeticOperations.multiply((Byte) lhs, rhs);
        }
        else if (rhs instanceof Byte) {

            result = ArithmeticOperations.multiply(lhs, (Byte) rhs);
        }



        if (result == null) {

            throw new UnsupportedOperationException(
                    "Cannot multiply a value of type " + lhs.getClass()
                            + " with a value of type " + rhs.getClass() + "!");
        }

        return NumberUtils.cast(result, returnType);
    }


    private static Double multiply(final Double lhs, final Number rhs) {

        final Double rhsDouble = rhs.doubleValue();

        return lhs * rhsDouble;
    }

    private static Double multiply(final Number lhs, final Double rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }

    private static Float multiply(final Float lhs, final Number rhs) {

        final Float rhsFloat = rhs.floatValue();

        return lhs * rhsFloat;
    }

    private static Float multiply(final Number lhs, final Float rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }

    private static Long multiply(final Long lhs, final Number rhs) {

        final Long rhsLong = rhs.longValue();

        return lhs * rhsLong;
    }

    private static Long multiply(final Number lhs, final Long rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }

    private static Integer multiply(final Integer lhs, final Number rhs) {

        final Integer rhsInteger = rhs.intValue();

        return lhs * rhsInteger;
    }

    private static Integer multiply(final Number lhs, final Integer rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }

    private static Short multiply(final Short lhs, final Number rhs) {

        final Short rhsShort = rhs.shortValue();

        final Integer result = lhs * rhsShort;

        return result.shortValue();
    }

    private static Short multiply(final Number lhs, final Short rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }

    private static Byte multiply(final Byte lhs, final Number rhs) {

        final Byte rhsByte = rhs.byteValue();

        final Integer result = lhs * rhsByte;

        return result.byteValue();
    }

    private static Byte multiply(final Number lhs, final Byte rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }


    /**
     * Divides two numbers.
     *
     * @param <T>
     *            the {@link Number} type of the operands.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the quotient of the numbers.
     */
    @SuppressWarnings("unchecked")
    public static final <T extends Number> T divide(final T lhs, final T rhs) {

        final Number result = ArithmeticOperations.divide(lhs, rhs,
                lhs.getClass());

        return (T) result;
    }


    /**
     * Divides two numbers and casts the result to the desired return type.
     *
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the return type.
     * @return the quotient of the numbers.
     */
    public static final <R extends Number> R divide(final Number lhs,
            final Number rhs, final Class<R> returnType) {

        Number result = null;


        if (lhs instanceof Double) {

            result = ArithmeticOperations.divide((Double) lhs, rhs);
        }
        else if (rhs instanceof Double) {

            result = ArithmeticOperations.divide(lhs, (Double) rhs);
        }
        else if (lhs instanceof Float) {

            result = ArithmeticOperations.divide((Float) lhs, rhs);
        }
        else if (rhs instanceof Float) {
            result = ArithmeticOperations.divide(lhs, (Float) rhs);
        }
        else if (lhs instanceof Long) {

            result = ArithmeticOperations.divide((Long) lhs, rhs);
        }
        else if (rhs instanceof Long) {

            result = ArithmeticOperations.divide(lhs, (Long) rhs);
        }
        else if (lhs instanceof Integer) {

            result = ArithmeticOperations.divide((Integer) lhs, rhs);
        }
        else if (rhs instanceof Integer) {

            result = ArithmeticOperations.divide(lhs, (Integer) rhs);
        }
        else if (lhs instanceof Short) {

            result = ArithmeticOperations.divide((Short) lhs, rhs);
        }
        else if (rhs instanceof Short) {

            result = ArithmeticOperations.divide(lhs, (Short) rhs);
        }
        else if (lhs instanceof Byte) {

            result = ArithmeticOperations.divide((Byte) lhs, rhs);
        }
        else if (rhs instanceof Byte) {

            result = ArithmeticOperations.divide(lhs, (Byte) rhs);
        }


        if (result == null) {

            throw new UnsupportedOperationException(
                    "Cannot divide a value of type " + rhs.getClass()
                            + " by a value of type " + lhs.getClass() + "!");
        }

        return NumberUtils.cast(result, returnType);
    }


    private static Double divide(final Double lhs, final Number rhs) {

        final Double rhsDouble = rhs.doubleValue();

        return lhs / rhsDouble;
    }

    private static Double divide(final Number lhs, final Double rhs) {

        final Double lhsDouble = lhs.doubleValue();

        return lhsDouble / rhs;
    }

    private static Float divide(final Float lhs, final Number rhs) {

        final Float rhsFloat = rhs.floatValue();

        return lhs / rhsFloat;
    }

    private static Float divide(final Number lhs, final Float rhs) {

        final Float lhsFloat = lhs.floatValue();

        return lhsFloat / rhs;
    }

    private static Long divide(final Long lhs, final Number rhs) {

        final Long rhsLong = rhs.longValue();

        return lhs / rhsLong;
    }

    private static Long divide(final Number lhs, final Long rhs) {

        final Long lhsLong = lhs.longValue();

        return lhsLong / rhs;
    }

    private static Integer divide(final Integer lhs, final Number rhs) {

        final Integer rhsInteger = rhs.intValue();

        return lhs / rhsInteger;
    }

    private static Integer divide(final Number lhs, final Integer rhs) {

        final Integer lhsInteger = lhs.intValue();

        return lhsInteger / rhs;
    }

    private static Short divide(final Short lhs, final Number rhs) {

        final Short rhsShort = rhs.shortValue();

        final Integer result = lhs / rhsShort;

        return result.shortValue();
    }

    private static Short divide(final Number lhs, final Short rhs) {

        final Short lhsShort = lhs.shortValue();

        final Integer result = lhsShort / rhs;

        return result.shortValue();
    }

    private static Byte divide(final Byte lhs, final Number rhs) {

        final Byte rhsByte = rhs.byteValue();

        final Integer result = lhs / rhsByte;

        return result.byteValue();
    }

    private static Byte divide(final Number lhs, final Byte rhs) {

        final Byte lhsByte = lhs.byteValue();

        final Integer result = lhsByte / rhs;

        return result.byteValue();
    }
}
