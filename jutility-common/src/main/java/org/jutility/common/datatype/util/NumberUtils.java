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


/**
 * The {@code NumberUtils} class provides utility methods for dealing with
 * generic {@link Number numbers}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public final class NumberUtils {


    /**
     * Parses the provided {@link String} into the desired {@link Number}
     * format.
     *
     * @param <R>
     *            the desired return type.
     *
     * @param string
     *            the {@link String} to parse.
     * @param returnType
     *            the desired {@link Number} format.
     * @return the parsed {@link Number} in the desired format.
     * @throws NumberFormatException
     *             if parsing the {@link String} does not produce a valid
     *             instance of the desired {@link Number} type.
     */
    public static final <R extends Number> R parse(final String string,
            final Class<R> returnType)
            throws NumberFormatException {


        if (returnType == Double.class) {

            return returnType.cast(Double.parseDouble(string));
        }
        else if (returnType == Float.class) {

            return returnType.cast(Float.parseFloat(string));
        }
        else if (returnType == Long.class) {

            return returnType.cast(Long.parseLong(string));
        }
        else if (returnType == Integer.class) {

            return returnType.cast(Integer.parseInt(string));
        }
        else if (returnType == Short.class) {

            return returnType.cast(Short.parseShort(string));
        }
        else if (returnType == Byte.class) {

            return returnType.cast(Byte.parseByte(string));
        }

        throw new UnsupportedOperationException("Cannot parse string " + string
                + " into instance of desired return type " + returnType + "!");
    }


    /**
     * Casts the number to the provided return type.
     *
     * @param <R>
     *            the desired return type.
     *
     * @param number
     *            the number to convert.
     * @param returnType
     *            the return type.
     * @return the number in the return type.
     */
    public static final <R extends Number> R cast(final Number number,
            final Class<R> returnType) {

        if ((number == null) || (returnType == null)) {
            return null;
        }

        if (returnType == Double.class) {

            final Double value = number.doubleValue();

            return returnType.cast(value);
        }
        else if (returnType == Float.class) {

            final Float value = number.floatValue();

            return returnType.cast(value);
        }
        else if (returnType == Long.class) {

            final Long value = number.longValue();

            return returnType.cast(value);
        }
        else if (returnType == Integer.class) {

            final Integer value = number.intValue();

            return returnType.cast(value);
        }
        else if (returnType == Short.class) {

            final Short value = number.shortValue();

            return returnType.cast(value);
        }
        else if (returnType == Byte.class) {

            final Byte value = number.byteValue();

            return returnType.cast(value);
        }

        throw new UnsupportedOperationException(
                "Cannot convert number of type " + number.getClass()
                        + " to desired return type " + returnType + "!");
    }

    /**
     * Casts the value to the provided return type.
     *
     * @param <R>
     *            the desired return type.
     *
     * @param value
     *            the value to convert.
     * @param returnType
     *            the return type.
     * @return the number in the return type.
     */
    public static final <R> R cast(final Object value, final Class<R> returnType) {

        if ((value == null) || (returnType == null)) {
            return null;
        }

        if (returnType.isAssignableFrom(value.getClass())) {

            return returnType.cast(value);
        }

        throw new UnsupportedOperationException(
                "Cannot convert object of type " + value.getClass()
                        + " to desired return type " + returnType + "!");
    }

    /**
     * Creates an array of a generic type from the provided elements.
     *
     * @param <T>
     *            the desired return type.
     *
     * @param type
     *            the type of the array.
     *
     * @param elements
     *            the elements comprising the desired array.
     * @return an array containing all elements.
     */
    @SafeVarargs
    public static final <T> T[] createArray(final Class<? extends T> type,
            final T... elements) {


        @SuppressWarnings("unchecked")
        final T[] array = (T[]) java.lang.reflect.Array.newInstance(type,
                elements.length);

        int i = 0;
        for (final T element : elements) {

            array[i] = element;
            i++;
        }

        return array;
    }
}
