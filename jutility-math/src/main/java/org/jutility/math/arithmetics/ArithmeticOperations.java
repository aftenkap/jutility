package org.jutility.math.arithmetics;

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


import org.jutility.common.datatype.util.NumberUtils;


/**
 * Provides arithmetic operations.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class ArithmeticOperations {

    /**
     * Calculates the sum of the provided numbers.
     * 
     * @param numbers
     *            the numbers.
     * @return the sum of the numbers.
     */
    @SafeVarargs
    public static <T extends Number> Number sum(T... numbers) {

        if (numbers.length == 0) {
            throw new IllegalArgumentException(
                    "Need to have at least one operand to calculate sum.");
        }
        else if (numbers.length == 1) {
            return numbers[0];
        }
        else {
            Number sum = ArithmeticOperations.add(numbers[0], numbers[1]);

            for (int i = 2; i < numbers.length; i++) {
                sum = ArithmeticOperations.add(sum, numbers[i]);
            }
            return sum;
        }
    }

    /**
     * Calculates the sum of the provided numbers.
     * 
     * @param returnType
     *            the desired return type.
     * 
     * @param numbers
     *            the numbers.
     * @return the sum of the numbers.
     */
    @SafeVarargs
    public static <T extends Number, S extends Number> S sum(
            Class<? extends S> returnType, T... numbers) {

        if (numbers.length == 0) {
            throw new IllegalArgumentException(
                    "Need to have at least one operand to calculate sum.");
        }
        else if (numbers.length == 1) {
            return NumberUtils.cast(numbers[0], returnType);
        }
        else {
            S sum = ArithmeticOperations
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
     * @param numbers
     *            the numbers.
     * @return the product of the numbers.
     */
    @SafeVarargs
    public static <T extends Number> Number product(T... numbers) {

        if (numbers.length == 0) {
            throw new IllegalArgumentException(
                    "Need to have at least one operand to calculate product.");
        }
        else if (numbers.length == 1) {
            return numbers[0];
        }
        else {
            Number product = ArithmeticOperations.multiply(numbers[0],
                    numbers[1]);

            for (int i = 2; i < numbers.length; i++) {
                product = ArithmeticOperations.multiply(product, numbers[i]);
            }
            return product;
        }
    }

    /**
     * Calculates the product of the provided numbers.
     * 
     * @param returnType
     *            the desired return type.
     * 
     * @param numbers
     *            the numbers.
     * @return the product of the numbers.
     */
    @SafeVarargs
    public static <T extends Number, S extends Number> S product(
            Class<? extends S> returnType, T... numbers) {

        if (numbers.length == 0) {
            throw new IllegalArgumentException(
                    "Need to have at least one operand to calculate product.");
        }
        else if (numbers.length == 1) {
            return NumberUtils.cast(numbers[0], returnType);
        }
        else {
            S product = ArithmeticOperations.multiply(numbers[0], numbers[1],
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
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the sum of the numbers.
     */
    public static <T extends Number, S extends Number> Number add(final T lhs,
            final S rhs) {

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

        return result;
    }


    /**
     * Adds two numbers and casts the result to the desired return type.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the return type.
     * @return the sum of the numbers.
     */
    public static <T extends Number, S extends Number, R extends Number> R add(
            T lhs, S rhs, Class<R> returnType) {

        Number result = ArithmeticOperations.add(lhs, rhs);

        return NumberUtils.cast(result, returnType);
    }

    private static <T extends Number> Number add(Double lhs, T rhs) {

        Double rhsDouble = rhs.doubleValue();

        return lhs + rhsDouble;
    }

    private static <T extends Number> Number add(T lhs, Double rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }

    private static <T extends Number> Number add(Float lhs, T rhs) {

        Float rhsFloat = rhs.floatValue();

        return lhs + rhsFloat;
    }

    private static <T extends Number> Number add(T lhs, Float rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }

    private static <T extends Number> Number add(Long lhs, T rhs) {

        Long rhsLong = rhs.longValue();

        return lhs + rhsLong;
    }

    private static <T extends Number> Number add(T lhs, Long rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }

    private static <T extends Number> Number add(Integer lhs, T rhs) {

        Integer rhsInteger = rhs.intValue();

        return lhs + rhsInteger;
    }

    private static <T extends Number> Number add(T lhs, Integer rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }

    private static <T extends Number> Number add(Short lhs, T rhs) {

        Short rhsShort = rhs.shortValue();

        return lhs + rhsShort;
    }

    private static <T extends Number> Number add(T lhs, Short rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }

    private static <T extends Number> Number add(Byte lhs, T rhs) {

        Byte rhsByte = rhs.byteValue();

        return lhs + rhsByte;
    }

    private static <T extends Number> Number add(T lhs, Byte rhs) {

        return ArithmeticOperations.add(rhs, lhs);
    }



    /**
     * Subtracts two numbers.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the difference of the numbers.
     */
    public static <T extends Number, S extends Number> Number subtract(T lhs,
            S rhs) {

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

        return result;
    }

    /**
     * Subtracts two numbers and casts the result to the desired return type.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the return type.
     * @return the difference of the numbers.
     */
    public static <T extends Number, S extends Number, R extends Number> R subtract(
            T lhs, S rhs, Class<R> returnType) {

        Number result = ArithmeticOperations.subtract(lhs, rhs);

        return NumberUtils.cast(result, returnType);
    }


    private static <T extends Number> Number subtract(Double lhs, T rhs) {

        Double rhsDouble = rhs.doubleValue();

        return lhs - rhsDouble;
    }

    private static <T extends Number> Number subtract(T lhs, Double rhs) {

        Double lhsDouble = lhs.doubleValue();

        return lhsDouble - rhs;
    }

    private static <T extends Number> Number subtract(Float lhs, T rhs) {

        Float rhsFloat = rhs.floatValue();

        return lhs - rhsFloat;
    }

    private static <T extends Number> Number subtract(T lhs, Float rhs) {

        Float lhsFloat = lhs.floatValue();

        return lhsFloat - rhs;
    }

    private static <T extends Number> Number subtract(Long lhs, T rhs) {

        Long rhsLong = rhs.longValue();

        return lhs - rhsLong;
    }

    private static <T extends Number> Number subtract(T lhs, Long rhs) {

        Long lhsLong = lhs.longValue();

        return lhsLong - rhs;
    }

    private static <T extends Number> Number subtract(Integer lhs, T rhs) {

        Integer rhsInteger = rhs.intValue();

        return lhs - rhsInteger;
    }

    private static <T extends Number> Number subtract(T lhs, Integer rhs) {

        Integer lhsInteger = lhs.intValue();

        return lhsInteger - rhs;
    }

    private static <T extends Number> Number subtract(Short lhs, T rhs) {

        Short rhsShort = rhs.shortValue();

        return lhs - rhsShort;
    }

    private static <T extends Number> Number subtract(T lhs, Short rhs) {

        Short lhsShort = lhs.shortValue();

        return lhsShort - rhs;
    }

    private static <T extends Number> Number subtract(Byte lhs, T rhs) {

        Byte rhsByte = rhs.byteValue();

        return lhs - rhsByte;
    }

    private static <T extends Number> Number subtract(T lhs, Byte rhs) {

        Byte lhsByte = lhs.byteValue();

        return lhsByte - rhs;
    }


    /**
     * Multiplies two numbers.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the product of the numbers.
     */
    public static <T extends Number, S extends Number> Number multiply(T lhs,
            S rhs) {

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

        return result;
    }


    /**
     * Multiplies two numbers and casts the result to the desired return type.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the return type.
     * @return the product of the numbers.
     */
    public static <T extends Number, S extends Number, R extends Number> R multiply(
            T lhs, S rhs, Class<R> returnType) {

        Number result = ArithmeticOperations.multiply(lhs, rhs);

        return NumberUtils.cast(result, returnType);
    }

    private static <T extends Number> Number multiply(Double lhs, T rhs) {

        Double rhsDouble = rhs.doubleValue();

        return lhs * rhsDouble;
    }

    private static <T extends Number> Number multiply(T lhs, Double rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }

    private static <T extends Number> Number multiply(Float lhs, T rhs) {

        Float rhsFloat = rhs.floatValue();

        return lhs * rhsFloat;
    }

    private static <T extends Number> Number multiply(T lhs, Float rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }

    private static <T extends Number> Number multiply(Long lhs, T rhs) {

        Long rhsLong = rhs.longValue();

        return lhs * rhsLong;
    }

    private static <T extends Number> Number multiply(T lhs, Long rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }

    private static <T extends Number> Number multiply(Integer lhs, T rhs) {

        Integer rhsInteger = rhs.intValue();

        return lhs * rhsInteger;
    }

    private static <T extends Number> Number multiply(T lhs, Integer rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }

    private static <T extends Number> Number multiply(Short lhs, T rhs) {

        Short rhsShort = rhs.shortValue();

        return lhs * rhsShort;
    }

    private static <T extends Number> Number multiply(T lhs, Short rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }

    private static <T extends Number> Number multiply(Byte lhs, T rhs) {

        Byte rhsByte = rhs.byteValue();

        return lhs * rhsByte;
    }

    private static <T extends Number> Number multiply(T lhs, Byte rhs) {

        return ArithmeticOperations.multiply(rhs, lhs);
    }


    /**
     * Divides two numbers.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the quotient of the numbers.
     */
    public static <T extends Number, S extends Number> Number divide(T lhs,
            S rhs) {

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

        return result;
    }


    /**
     * Divides two numbers and casts the result to the desired return type.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the return type.
     * @return the quotient of the numbers.
     */
    public static <T extends Number, S extends Number, R extends Number> R divide(
            T lhs, S rhs, Class<R> returnType) {

        Number result = ArithmeticOperations.divide(lhs, rhs);

        return NumberUtils.cast(result, returnType);
    }


    private static <T extends Number> Number divide(Double lhs, T rhs) {

        Double rhsDouble = rhs.doubleValue();

        return lhs / rhsDouble;
    }

    private static <T extends Number> Number divide(T lhs, Double rhs) {

        Double lhsDouble = lhs.doubleValue();

        return lhsDouble / rhs;
    }

    private static <T extends Number> Number divide(Float lhs, T rhs) {

        Float rhsFloat = rhs.floatValue();

        return lhs / rhsFloat;
    }

    private static <T extends Number> Number divide(T lhs, Float rhs) {

        Float lhsFloat = lhs.floatValue();

        return lhsFloat / rhs;
    }

    private static <T extends Number> Number divide(Long lhs, T rhs) {

        Long rhsLong = rhs.longValue();

        return lhs / rhsLong;
    }

    private static <T extends Number> Number divide(T lhs, Long rhs) {

        Long lhsLong = lhs.longValue();

        return lhsLong / rhs;
    }

    private static <T extends Number> Number divide(Integer lhs, T rhs) {

        Integer rhsInteger = rhs.intValue();

        return lhs / rhsInteger;
    }

    private static <T extends Number> Number divide(T lhs, Integer rhs) {

        Integer lhsInteger = lhs.intValue();

        return lhsInteger / rhs;
    }

    private static <T extends Number> Number divide(Short lhs, T rhs) {

        Short rhsShort = rhs.shortValue();

        return lhs / rhsShort;
    }

    private static <T extends Number> Number divide(T lhs, Short rhs) {

        Short lhsShort = lhs.shortValue();

        return lhsShort / rhs;
    }

    private static <T extends Number> Number divide(Byte lhs, T rhs) {

        Byte rhsByte = rhs.byteValue();

        return lhs / rhsByte;
    }

    private static <T extends Number> Number divide(T lhs, Byte rhs) {

        Byte lhsByte = lhs.byteValue();

        return lhsByte / rhs;
    }
}
