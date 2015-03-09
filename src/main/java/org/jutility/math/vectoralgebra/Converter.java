package org.jutility.math.vectoralgebra;


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
 * different vector algebraic types.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public final class Converter {


    /**
     * Converts a {@link ITuple4 Tuple} to a {@link ITuple4 Tuple} of the
     * requested return type.
     * 
     * @param <R>
     *            the {@link Number} type of the returned {@link ITuple4 Tuple}.
     * 
     * @param value
     *            the {@link ITuple4 Tuple} to convert.
     * @param returnType
     *            the desired return type.
     * @return the converted {@link ITuple4 Tuple}.
     */
    public static final <R extends Number> ITuple4<R> convert(
            final ITuple4<?> value, final Class<? extends R> returnType) {

        return new Tuple4<R>(value, returnType);
    }

    /**
     * Converts a {@link IVector4 Vector} to a {@link IVector4 Vector} of the
     * requested return type.
     * 
     * @param <R>
     *            the {@link Number} type of the returned {@link IVector4
     *            Vector}.
     * 
     * @param value
     *            the {@link IVector4 Vector} to convert.
     * @param returnType
     *            the desired return type.
     * @return the converted {@link IVector4 Vector}.
     */
    public static final <R extends Number> IVector4<R> convert(
            final IVector4<?> value, final Class<? extends R> returnType) {

        return new Vector4<R>(value, returnType);
    }

    /**
     * Converts a {@link IPoint4 Point} to a {@link IPoint4 Point} of the
     * requested return type.
     * 
     * @param <R>
     *            the {@link Number} type of the returned {@link IPoint4 Point}.
     * 
     * @param value
     *            the {@link IPoint4 Point} to convert.
     * @param returnType
     *            the desired return type.
     * @return the converted {@link IPoint4 point}.
     */
    public static final <R extends Number> IPoint4<R> convert(
            final IPoint4<?> value, final Class<? extends R> returnType) {

        return new Point4<R>(value, returnType);
    }


    /**
     * Converts a {@link IMatrix4 Matrix} to a {@link IMatrix4 Matrix} of the
     * requested return type.
     * 
     * @param <R>
     *            the {@link Number} type of the returned {@link IMatrix4
     *            Matrix}.
     * 
     * @param value
     *            the {@link IMatrix4 Matrix} to convert.
     * @param returnType
     *            the desired return type.
     * @return the converted {@link IMatrix4 Matrix}.
     */
    public static final <R extends Number> IMatrix4<R> convert(
            final IMatrix4<?> value, final Class<? extends R> returnType) {

        return new Matrix4<R>(value, returnType);
    }
}
