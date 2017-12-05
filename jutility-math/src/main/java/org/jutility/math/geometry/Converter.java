package org.jutility.math.geometry;


//@formatter:off
/*
* #%L
 * * jutility-math
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * *
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
 * The {@code Converter} utility class provides conversion operations for
 * different geometric types.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public final class Converter {

    /**
     * Converts the provided {@link IRotation Rotation} into a {@link IRotation
     * Rotation} of the desired {@link Number} type.
     * 
     * @param <T>
     *            the desired {@link Number} type.
     * 
     * @param value
     *            the {@link IRotation rotation} to convert.
     * @param returnType
     *            the desired type.
     * @return the converted {@link IRotation rotation}.
     */
    public static final <T extends Number> IRotation<T> convert(
            final IRotation<? extends Number> value,
            final Class<? extends T> returnType) {

        return new Rotation<>(value, returnType);
    }



    /**
     * Converts the provided {@link IScaleFactor ScaleFactor} into a
     * {@link IScaleFactor ScaleFactor} of the desired {@link Number Number}
     * type.
     * 
     * @param <T>
     *            the desired {@link Number} type.
     * 
     * @param value
     *            the {@link IScaleFactor ScaleFactor} to convert.
     * @param returnType
     *            the desired return type.
     * @return the converted {@link IScaleFactor ScaleFactor}.
     */
    public static final <T extends Number> IScaleFactor<T> convert(
            final IScaleFactor<? extends Number> value,
            final Class<? extends T> returnType) {

        return new ScaleFactor<>(value, returnType);
    }

    /**
     * Converts the provided {@link ILine4 Line} into a {@link ILine4 Line} of
     * the desired {@link Number} type.
     * 
     * @param <T>
     *            the desired {@link Number} type.
     * 
     * @param value
     *            the {@link ILine4 line} to convert.
     * @param returnType
     *            the desired return type.
     * @return the converted {@link ILine4 line}.
     */
    public static final <T extends Number> ILine4<T> convert(
            final ILine4<? extends Number> value,
            final Class<? extends T> returnType) {

        return new Line4<>(value, returnType);
    }
}
