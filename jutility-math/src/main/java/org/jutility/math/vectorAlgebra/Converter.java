package org.jutility.math.vectorAlgebra;


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
