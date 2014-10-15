package org.jutility.math.vectorAlgebra;

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
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class Converter {


    /**
     * Converts a {@link ITuple4 tuple} to a {@link ITuple4 tuple} of the
     * requested return type.
     * 
     * @param value
     *            the {@link ITuple4 tuple} to convert.
     * @param returnType
     *            the desired return type.
     * @return the converted {@link ITuple4 tuple}.
     */
    public static <T extends Number> ITuple4<T> convert(
            final ITuple4<? extends Number> value,
            final Class<? extends T> returnType) {

        return new Tuple4<T>(value, returnType);
    }

    /**
     * Converts a {@link IVector4 vector} to a {@link IVector4 vector} of the
     * requested return type.
     * 
     * @param value
     *            the {@link IVector4 vector} to convert.
     * @param returnType
     *            the desired return type.
     * @return the converted {@link IVector4 vector}.
     */
    public static <T extends Number> IVector4<T> convert(
            final IVector4<? extends Number> value,
            final Class<? extends T> returnType) {

        return new Vector4<T>(value, returnType);
    }

    /**
     * Converts a {@link IPoint4 point} to a {@link IPoint4 point} of the
     * requested return type.
     * 
     * @param value
     *            the {@link IPoint4 point} to convert.
     * @param returnType
     *            the desired return type.
     * @return the converted {@link IPoint4 point}.
     */
    public static <T extends Number> IPoint4<T> convert(
            final IPoint4<? extends Number> value,
            final Class<? extends T> returnType) {

        return new Point4<T>(value, returnType);
    }


    /**
     * Converts a {@link IMatrix4 matrix} to a {@link IMatrix4 matrix} of the
     * requested return type.
     * 
     * @param value
     *            the {@link IMatrix4 matrix} to convert.
     * @param returnType
     *            the desired return type.
     * @return the converted {@link IMatrix4 matrix}.
     */
    public static <T extends Number> IMatrix4<T> convert(
            final IMatrix4<? extends Number> value,
            final Class<? extends T> returnType) {

        return new Matrix4<T>(value, returnType);
    }

}
