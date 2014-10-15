package org.jutility.math.geometry;

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


/**
 * The {@link Converter} utility class provides conversion operations for
 * different geometric types.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class Converter {

    /**
     * Converts the provided {@link IRotation rotation} into a {@link IRotation
     * rotation} of the desired {@link Number number} type.
     * 
     * @param value
     *            the {@link IRotation rotation} to convert.
     * @param returnType
     *            the desired type.
     * @return the converted {@link IRotation rotation}.
     */
    public static <T extends Number> IRotation<T> convert(
            final IRotation<? extends Number> value,
            final Class<? extends T> returnType) {

        return new Rotation<T>(value, returnType);
    }



    /**
     * Converts the provided {@link IScale scale} into a {@link IScale scale} of
     * the desired {@link Number number} type.
     * 
     * @param value
     *            the {@link IScale scale} to convert.
     * @param returnType
     *            the desired {@link Number number} type.
     * @return the converted {@link IScale scale}.
     */
    public static <T extends Number> IScale<T> convert(
            final IScale<? extends Number> value,
            final Class<? extends T> returnType) {

        return new Scale<T>(value, returnType);
    }

    /**
     * Converts the provided {@link ILine4 line} into a {@link ILine4 line} of the
     * desired {@link Number number} type.
     * 
     * @param value
     *            the {@link ILine4 line} to convert.
     * @param returnType
     *            the desired {@link Number number} type.
     * @return the converted {@link ILine4 line}.
     */
    public static <T extends Number> ILine4<T> convert(
            final ILine4<? extends Number> value,
            final Class<? extends T> returnType) {

        return new Line4<T>(value, returnType);
    }



}
