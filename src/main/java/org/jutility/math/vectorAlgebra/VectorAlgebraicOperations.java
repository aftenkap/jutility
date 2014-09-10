package org.jutility.math.vectorAlgebra;


import java.util.ArrayList;
import java.util.List;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.math.arithmetics.ArithmeticOperations;


/**
 * This class is a utility class for Tuple and Matrix operations.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class VectorAlgebraicOperations {

    /**
     * Adds the left-hand tuple to the right-hand tuple.
     * 
     * @param lhs
     *            The left-hand tuple.
     * @param rhs
     *            The right-hand tuple.
     * @return The vector sum of the tuples.
     * @throws IllegalArgumentException
     *             if two points are provided.
     * @throws ArithmeticException
     *             if the result is not a homogeneous coordinate.
     */
    protected static <T extends Number, S extends Number> ITuple4<? extends Number> add(
            final ITuple4<T> lhs, final ITuple4<S> rhs) {

        Number newX = ArithmeticOperations.add(lhs.getX(), rhs.getX());
        Number newY = ArithmeticOperations.add(lhs.getY(), rhs.getY());
        Number newZ = ArithmeticOperations.add(lhs.getZ(), rhs.getZ());
        Number newW = ArithmeticOperations.add(lhs.getW(), rhs.getW());

        Class<? extends Number> highestPrecision = NumberComparator
                .greaterPrecisionType(newX, newY, newZ, newW);

        return new Tuple4<Number>(newX, newY, newZ, newW, highestPrecision);
    }

    /**
     * Adds the left-hand tuple to the right-hand tuple.
     * 
     * @param lhs
     *            The left-hand tuple.
     * @param rhs
     *            The right-hand tuple.
     * @param returnType
     *            the desired return type.
     * @return The vector sum of the tuples.
     * @throws IllegalArgumentException
     *             if two points are provided.
     * @throws ArithmeticException
     *             if the result is not a homogeneous coordinate.
     */
    protected static <T extends Number, S extends Number, R extends Number> ITuple4<R> add(
            final ITuple4<T> lhs, final ITuple4<S> rhs,
            final Class<? extends R> returnType) {

        Number newX = ArithmeticOperations.add(lhs.getX(), rhs.getX());
        Number newY = ArithmeticOperations.add(lhs.getY(), rhs.getY());
        Number newZ = ArithmeticOperations.add(lhs.getZ(), rhs.getZ());
        Number newW = ArithmeticOperations.add(lhs.getW(), rhs.getW());

        return new Tuple4<R>(newX, newY, newZ, newW, returnType);
    }


    /**
     * Adds the left-hand vector to the right-hand vector.
     * 
     * @param lhs
     *            The left-hand vector.
     * @param rhs
     *            The right-hand vector.
     * @return The vector sum of the vectors.
     */
    public static <T extends Number, S extends Number> IVector4<? extends Number> add(
            final IVector4<T> lhs, final IVector4<S> rhs) {

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(lhs.getType());
        classes.add(rhs.getType());
        Class<? extends Number> greatestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Vector4<Number>(VectorAlgebraicOperations.add(
                (ITuple4<T>) lhs, (ITuple4<S>) rhs, greatestPrecision));
    }

    /**
     * Adds the left-hand vector to the right-hand vector.
     * 
     * @param lhs
     *            The left-hand vector.
     * @param rhs
     *            The right-hand vector.
     * @param returnType
     *            the desired return type.
     * @return The vector sum of the vectors.
     */
    public static <T extends Number, S extends Number, R extends Number> IVector4<R> add(
            final IVector4<T> lhs, final IVector4<S> rhs,
            final Class<? extends R> returnType) {

        return new Vector4<R>(VectorAlgebraicOperations.add((ITuple4<T>) lhs,
                (ITuple4<S>) rhs), returnType);
    }


    /**
     * Adds the left-hand point to the right-hand vector.
     * 
     * @param lhs
     *            The left-hand point.
     * @param rhs
     *            The right-hand vector.
     * @return The vector sum of the point and the vector.
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> add(
            final IPoint4<T> lhs, final IVector4<S> rhs) {

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(lhs.getType());
        classes.add(rhs.getType());
        Class<? extends Number> greatestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Point4<Number>(VectorAlgebraicOperations.add(
                (ITuple4<T>) lhs, (ITuple4<S>) rhs, greatestPrecision));
    }

    /**
     * Adds the left-hand point to the right-hand vector.
     * 
     * @param lhs
     *            The left-hand point.
     * @param rhs
     *            The right-hand vector.
     * @param returnType
     *            the desired return type.
     * @return The vector sum of the point and the vector.
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> add(
            final IPoint4<T> lhs, final IVector4<S> rhs,
            final Class<? extends R> returnType) {

        return new Point4<R>(VectorAlgebraicOperations.add((ITuple4<T>) lhs,
                (ITuple4<S>) rhs, returnType));
    }


    /**
     * Adds the left-hand vector to the right-hand point.
     * 
     * @param lhs
     *            The left-hand vector.
     * @param rhs
     *            The right-hand point.
     * @return The vector sum of the vector and the point.
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> add(
            final IVector4<T> lhs, final IPoint4<S> rhs) {

        return VectorAlgebraicOperations.add(rhs, lhs);
    }

    /**
     * Adds the left-hand vector to the right-hand point.
     * 
     * @param lhs
     *            The left-hand vector.
     * @param rhs
     *            The right-hand point.
     * @param returnType
     *            the desired return type.
     * @return The vector sum of the vector and the point.
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> add(
            final IVector4<T> lhs, final IPoint4<S> rhs,
            final Class<? extends R> returnType) {

        return VectorAlgebraicOperations.add(rhs, lhs, returnType);
    }

    /**
     * Subtracts the right-hand tuple from the left-hand tuple.
     * 
     * @param lhs
     *            the left-hand tuple.
     * @param rhs
     *            the right-hand tuple.
     * @return the difference between the tuples.
     * @throws ArithmeticException
     *             if the operation is not a valid arithmetic operation.
     */
    protected static <T extends Number, S extends Number> ITuple4<? extends Number> subtract(
            final ITuple4<T> lhs, final ITuple4<S> rhs) {

        if (lhs.isVector() && rhs.isPoint()) {

            throw new IllegalArgumentException(
                    "Cannot subtract a point from a vector!");
        }

        Number newX = ArithmeticOperations.subtract(lhs.getX(), rhs.getX());
        Number newY = ArithmeticOperations.subtract(lhs.getY(), rhs.getY());
        Number newZ = ArithmeticOperations.subtract(lhs.getZ(), rhs.getZ());
        Number newW = ArithmeticOperations.subtract(lhs.getW(), rhs.getW());

        Class<? extends Number> highestPrecision = NumberComparator
                .greaterPrecisionType(newX, newY, newZ, newW);

        return new Tuple4<Number>(newX, newY, newZ, newW, highestPrecision);
    }

    /**
     * Subtracts the right-hand tuple from the left-hand tuple.
     * 
     * @param lhs
     *            the left-hand tuple.
     * @param rhs
     *            the right-hand tuple.
     * @param returnType
     *            the desired return type.
     * @return the difference between the tuples.
     * @throws ArithmeticException
     *             if the operation is not a valid arithmetic operation.
     */
    protected static <T extends Number, S extends Number, R extends Number> ITuple4<R> subtract(
            final ITuple4<T> lhs, final ITuple4<S> rhs,
            final Class<? extends R> returnType) {

        if (lhs.isVector() && rhs.isPoint()) {

            throw new IllegalArgumentException(
                    "Cannot subtract a point from a vector!");
        }

        Number newX = ArithmeticOperations.subtract(lhs.getX(), rhs.getX());
        Number newY = ArithmeticOperations.subtract(lhs.getY(), rhs.getY());
        Number newZ = ArithmeticOperations.subtract(lhs.getZ(), rhs.getZ());
        Number newW = ArithmeticOperations.subtract(lhs.getW(), rhs.getW());

        return new Tuple4<R>(newX, newY, newZ, newW, returnType);
    }


    /**
     * Subtracts the right-hand point from the left-hand point.
     * 
     * @param lhs
     *            the left-hand point.
     * @param rhs
     *            the right-hand point.
     * @return the difference between the points.
     */
    public static <T extends Number, S extends Number> IVector4<? extends Number> subtract(
            final IPoint4<T> lhs, final IPoint4<S> rhs) {

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(lhs.getType());
        classes.add(rhs.getType());
        Class<? extends Number> greatestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Vector4<Number>(VectorAlgebraicOperations.subtract(
                (ITuple4<T>) lhs, (ITuple4<S>) rhs, greatestPrecision));
    }

    /**
     * Subtracts the right-hand point from the left-hand point.
     * 
     * @param lhs
     *            the left-hand point.
     * @param rhs
     *            the right-hand point.
     * @param returnType
     *            the desired return type.
     * @return the difference between the points.
     */
    public static <T extends Number, S extends Number, R extends Number> IVector4<R> subtract(
            final IPoint4<T> lhs, final IPoint4<S> rhs,
            final Class<? extends R> returnType) {

        return new Vector4<R>(VectorAlgebraicOperations.subtract(
                (ITuple4<T>) lhs, (ITuple4<S>) rhs), returnType);
    }


    /**
     * Subtracts the right-hand vector from the left-hand point.
     * 
     * @param lhs
     *            the left-hand point.
     * @param rhs
     *            the right-hand vector.
     * @return the difference between the point and the vector.
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> subtract(
            final IPoint4<T> lhs, final IVector4<S> rhs) {

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(lhs.getType());
        classes.add(rhs.getType());
        Class<? extends Number> greatestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Point4<Number>(VectorAlgebraicOperations.subtract(
                (ITuple4<T>) lhs, (ITuple4<S>) rhs, greatestPrecision));
    }

    /**
     * Subtracts the right-hand vector from the left-hand point.
     * 
     * @param lhs
     *            the left-hand point.
     * @param rhs
     *            the right-hand vector.
     * @param returnType
     *            the desired return type.
     * @return the difference between the point and the vector.
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> subtract(
            final IPoint4<T> lhs, final IVector4<S> rhs,
            final Class<? extends R> returnType) {

        return new Point4<R>(VectorAlgebraicOperations.subtract(
                (ITuple4<T>) lhs, (ITuple4<S>) rhs), returnType);
    }


    /**
     * Multiplies the tuple by the provided scalar.
     * 
     * @param lhs
     *            The tuple.
     * @param rhs
     *            The scalar.
     * @return The scaled tuple.
     * @throws ArithmeticException
     *             if the operation is not a valid arithmetic operation.
     */
    protected static <T extends Number> ITuple4<? extends Number> multiply(
            final ITuple4<T> lhs, final Number rhs) {

        if (lhs.isPoint()) {

            throw new IllegalArgumentException("Cannot scale a point!");
        }

        Number newX = ArithmeticOperations.multiply(lhs.getX(), rhs);
        Number newY = ArithmeticOperations.multiply(lhs.getY(), rhs);
        Number newZ = ArithmeticOperations.multiply(lhs.getZ(), rhs);
        Number newW = ArithmeticOperations.multiply(lhs.getW(), rhs);

        Class<? extends Number> highestPrecision = NumberComparator
                .greaterPrecisionType(newX, newY, newZ, newW);

        return new Tuple4<Number>(newX, newY, newZ, newW, highestPrecision);

    }

    /**
     * Multiplies the tuple by the provided scalar.
     * 
     * @param lhs
     *            The tuple.
     * @param rhs
     *            The scalar.
     * @param returnType
     *            the desired return type.
     * @return The scaled tuple.
     * @throws ArithmeticException
     *             if the operation is not a valid arithmetic operation.
     */
    protected static <T extends Number, R extends Number> ITuple4<R> multiply(
            final ITuple4<T> lhs, final Number rhs,
            final Class<? extends R> returnType) {

        if (lhs.isPoint()) {

            throw new IllegalArgumentException("Cannot scale a point!");
        }

        Number newX = ArithmeticOperations.multiply(lhs.getX(), rhs);
        Number newY = ArithmeticOperations.multiply(lhs.getY(), rhs);
        Number newZ = ArithmeticOperations.multiply(lhs.getZ(), rhs);
        Number newW = ArithmeticOperations.multiply(lhs.getW(), rhs);

        return new Tuple4<R>(newX, newY, newZ, newW, returnType);
    }


    /**
     * Multiplies the vector by the provided scalar.
     * 
     * @param lhs
     *            The tuple.
     * @param rhs
     *            The scalar.
     * @return The scaled tuple.
     */
    public static <T extends Number> IVector4<? extends Number> multiply(
            final IVector4<T> lhs, final Number rhs) {

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(lhs.getType());
        classes.add(rhs.getClass());
        Class<? extends Number> greatestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Vector4<Number>(VectorAlgebraicOperations.multiply(
                (ITuple4<T>) lhs, rhs, greatestPrecision));
    }

    /**
     * Multiplies the vector by the provided scalar.
     * 
     * @param lhs
     *            The tuple.
     * @param rhs
     *            The scalar.
     * @param returnType
     *            the desired return type.
     * @return The scaled tuple.
     */
    public static <T extends Number, R extends Number> IVector4<R> multiply(
            final IVector4<T> lhs, final Number rhs,
            final Class<? extends R> returnType) {

        return new Vector4<R>(VectorAlgebraicOperations.multiply(
                (ITuple4<T>) lhs, rhs), returnType);
    }


    /**
     * Multiplies the vector by the provided scalar.
     * 
     * @param lhs
     *            The scalar.
     * @param rhs
     *            The vector.
     * @return The scaled vector.
     */
    public static <T extends Number> IVector4<? extends Number> multiply(
            final Number lhs, final IVector4<T> rhs) {

        return VectorAlgebraicOperations.multiply(rhs, lhs);
    }

    /**
     * Multiplies the vector by the provided scalar.
     * 
     * @param lhs
     *            The scalar.
     * @param rhs
     *            The vector.
     * @param returnType
     *            the desired return type.
     * @return The scaled vector.
     */
    public static <T extends Number, R extends Number> IVector4<R> multiply(
            final Number lhs, final IVector4<T> rhs,
            final Class<? extends R> returnType) {

        return VectorAlgebraicOperations.multiply(rhs, lhs, returnType);
    }


    /**
     * Multiplies the matrix by the provided tuple.
     * 
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the tuple.
     * @return The resulting tuple.
     */
    public static <T extends Number, S extends Number> ITuple4<? extends Number> multiply(
            final IMatrix4<T> lhs, final ITuple4<S> rhs) {

        T ix = lhs.getI().getX();
        T iy = lhs.getI().getY();
        T iz = lhs.getI().getZ();
        T iw = lhs.getI().getW();

        T jx = lhs.getJ().getX();
        T jy = lhs.getJ().getY();
        T jz = lhs.getJ().getZ();
        T jw = lhs.getJ().getW();

        T kx = lhs.getK().getX();
        T ky = lhs.getK().getY();
        T kz = lhs.getK().getZ();
        T kw = lhs.getK().getW();

        T sx = lhs.getS().getX();
        T sy = lhs.getS().getY();
        T sz = lhs.getS().getZ();
        T sw = lhs.getS().getW();

        S vx = rhs.getX();
        S vy = rhs.getY();
        S vz = rhs.getZ();
        S vw = rhs.getW();

        Number ix_vx = ArithmeticOperations.multiply(ix, vx);
        Number jx_vy = ArithmeticOperations.multiply(jx, vy);
        Number kx_vz = ArithmeticOperations.multiply(kx, vz);
        Number sx_vw = ArithmeticOperations.multiply(sx, vw);

        Number x = ArithmeticOperations.sum(ix_vx, jx_vy, kx_vz, sx_vw);

        Number iy_vx = ArithmeticOperations.multiply(iy, vx);
        Number jy_vy = ArithmeticOperations.multiply(jy, vy);
        Number ky_vz = ArithmeticOperations.multiply(ky, vz);
        Number sy_vw = ArithmeticOperations.multiply(sy, vw);

        Number y = ArithmeticOperations.sum(iy_vx, jy_vy, ky_vz, sy_vw);


        Number iz_vx = ArithmeticOperations.multiply(iz, vx);
        Number jz_vy = ArithmeticOperations.multiply(jz, vy);
        Number kz_vz = ArithmeticOperations.multiply(kz, vz);
        Number sz_vw = ArithmeticOperations.multiply(sz, vw);

        Number z = ArithmeticOperations.sum(iz_vx, jz_vy, kz_vz, sz_vw);

        Number iw_vx = ArithmeticOperations.multiply(iw, vx);
        Number jw_vy = ArithmeticOperations.multiply(jw, vy);
        Number kw_vz = ArithmeticOperations.multiply(kw, vz);
        Number sw_vw = ArithmeticOperations.multiply(sw, vw);

        Number w = ArithmeticOperations.sum(iw_vx, jw_vy, kw_vz, sw_vw);


        Class<? extends Number> highestPrecision = NumberComparator
                .greaterPrecisionType(x, y, z, w);

        return new Tuple4<Number>(x, y, z, w, highestPrecision);
    }

    /**
     * Multiplies the matrix by the provided tuple.
     * 
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the tuple.
     * @param returnType
     *            the desired return type.
     * @return The resulting tuple.
     */
    public static <T extends Number, S extends Number, R extends Number> ITuple4<R> multiply(
            final IMatrix4<T> lhs, final ITuple4<S> rhs,
            final Class<? extends R> returnType) {

        T ix = lhs.getI().getX();
        T iy = lhs.getI().getY();
        T iz = lhs.getI().getZ();
        T iw = lhs.getI().getW();

        T jx = lhs.getJ().getX();
        T jy = lhs.getJ().getY();
        T jz = lhs.getJ().getZ();
        T jw = lhs.getJ().getW();

        T kx = lhs.getK().getX();
        T ky = lhs.getK().getY();
        T kz = lhs.getK().getZ();
        T kw = lhs.getK().getW();

        T sx = lhs.getS().getX();
        T sy = lhs.getS().getY();
        T sz = lhs.getS().getZ();
        T sw = lhs.getS().getW();

        S vx = rhs.getX();
        S vy = rhs.getY();
        S vz = rhs.getZ();
        S vw = rhs.getW();

        Number ix_vx = ArithmeticOperations.multiply(ix, vx);
        Number jx_vy = ArithmeticOperations.multiply(jx, vy);
        Number kx_vz = ArithmeticOperations.multiply(kx, vz);
        Number sx_vw = ArithmeticOperations.multiply(sx, vw);

        Number x = ArithmeticOperations.sum(ix_vx, jx_vy, kx_vz, sx_vw);

        Number iy_vx = ArithmeticOperations.multiply(iy, vx);
        Number jy_vy = ArithmeticOperations.multiply(jy, vy);
        Number ky_vz = ArithmeticOperations.multiply(ky, vz);
        Number sy_vw = ArithmeticOperations.multiply(sy, vw);

        Number y = ArithmeticOperations.sum(iy_vx, jy_vy, ky_vz, sy_vw);


        Number iz_vx = ArithmeticOperations.multiply(iz, vx);
        Number jz_vy = ArithmeticOperations.multiply(jz, vy);
        Number kz_vz = ArithmeticOperations.multiply(kz, vz);
        Number sz_vw = ArithmeticOperations.multiply(sz, vw);

        Number z = ArithmeticOperations.sum(iz_vx, jz_vy, kz_vz, sz_vw);

        Number iw_vx = ArithmeticOperations.multiply(iw, vx);
        Number jw_vy = ArithmeticOperations.multiply(jw, vy);
        Number kw_vz = ArithmeticOperations.multiply(kw, vz);
        Number sw_vw = ArithmeticOperations.multiply(sw, vw);

        Number w = ArithmeticOperations.sum(iw_vx, jw_vy, kw_vz, sw_vw);

        return new Tuple4<R>(x, y, z, w, returnType);
    }


    /**
     * Multiplies the matrix by the provided point.
     * 
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the point.
     * @return The resulting point.
     */
    public static <T extends Number, S extends Number> IPoint4<? extends Number> multiply(
            final IMatrix4<T> lhs, final IPoint4<S> rhs) {

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(lhs.getType());
        classes.add(rhs.getType());
        Class<? extends Number> greatestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Point4<Number>(VectorAlgebraicOperations.multiply(lhs,
                (ITuple4<S>) rhs, greatestPrecision));
    }

    /**
     * Multiplies the matrix by the provided point.
     * 
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the point.
     * @param returnType
     *            the desired return type.
     * @return The resulting point.
     */
    public static <T extends Number, S extends Number, R extends Number> IPoint4<R> multiply(
            final IMatrix4<T> lhs, final IPoint4<S> rhs,
            final Class<? extends R> returnType) {

        return new Point4<R>(VectorAlgebraicOperations.multiply(lhs,
                (ITuple4<S>) rhs, returnType));
    }


    /**
     * Multiplies the matrix by the provided vector.
     * 
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the vector.
     * @return The resulting vector.
     */
    public static <T extends Number, S extends Number> IVector4<? extends Number> multiply(
            final IMatrix4<T> lhs, final IVector4<S> rhs) {

        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                2);
        classes.add(lhs.getType());
        classes.add(rhs.getType());
        Class<? extends Number> greatestPrecision = NumberComparator
                .greatestPrecisionType(classes);

        return new Vector4<Number>(VectorAlgebraicOperations.multiply(lhs,
                (ITuple4<S>) rhs, greatestPrecision));
    }

    /**
     * Multiplies the matrix by the provided vector.
     * 
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the vector.
     * @param returnType
     *            the desired return type.
     * @return The resulting vector.
     */
    public static <T extends Number, S extends Number, R extends Number> IVector4<R> multiply(
            final IMatrix4<T> lhs, final IVector4<S> rhs,
            final Class<? extends R> returnType) {

        return new Vector4<R>(VectorAlgebraicOperations.multiply(lhs,
                (ITuple4<S>) rhs), returnType);
    }


    /**
     * Multiplies the two 4x4 matrices provided.
     * 
     * @param lhs
     *            the left-hand side matrix.
     * @param rhs
     *            the right-hand side matrix.
     * @return the resulting matrix.
     */
    public static <T extends Number, S extends Number> IMatrix4<? extends Number> multiply(
            final IMatrix4<T> lhs, final IMatrix4<S> rhs) {

        Number a_ix = lhs.getI().getX();
        Number a_iy = lhs.getI().getY();
        Number a_iz = lhs.getI().getZ();
        Number a_iw = lhs.getI().getW();

        Number a_jx = lhs.getJ().getX();
        Number a_jy = lhs.getJ().getY();
        Number a_jz = lhs.getJ().getZ();
        Number a_jw = lhs.getJ().getW();

        Number a_kx = lhs.getK().getX();
        Number a_ky = lhs.getK().getY();
        Number a_kz = lhs.getK().getZ();
        Number a_kw = lhs.getK().getW();

        Number a_sx = lhs.getS().getX();
        Number a_sy = lhs.getS().getY();
        Number a_sz = lhs.getS().getZ();
        Number a_sw = lhs.getS().getW();



        Number b_ix = rhs.getI().getX();
        Number b_iy = rhs.getI().getY();
        Number b_iz = rhs.getI().getZ();
        Number b_iw = rhs.getI().getW();

        Number b_jx = rhs.getJ().getX();
        Number b_jy = rhs.getJ().getY();
        Number b_jz = rhs.getJ().getZ();
        Number b_jw = rhs.getJ().getW();

        Number b_kx = rhs.getK().getX();
        Number b_ky = rhs.getK().getY();
        Number b_kz = rhs.getK().getZ();
        Number b_kw = rhs.getK().getW();

        Number b_sx = rhs.getS().getX();
        Number b_sy = rhs.getS().getY();
        Number b_sz = rhs.getS().getZ();
        Number b_sw = rhs.getS().getW();



        Number a_ix__b_ix = ArithmeticOperations.multiply(a_ix, b_ix);
        Number a_jx__b_iy = ArithmeticOperations.multiply(a_jx, b_iy);
        Number a_kx__b_iz = ArithmeticOperations.multiply(a_kx, b_iz);
        Number a_sx__b_iw = ArithmeticOperations.multiply(a_sx, b_iw);

        Number r_ix = ArithmeticOperations.sum(a_ix__b_ix, a_jx__b_iy,
                a_kx__b_iz, a_sx__b_iw);


        Number a_ix__b_jx = ArithmeticOperations.multiply(a_ix, b_jx);
        Number a_jx__b_jy = ArithmeticOperations.multiply(a_jx, b_jy);
        Number a_kx__b_jz = ArithmeticOperations.multiply(a_kx, b_jz);
        Number a_sx__b_jw = ArithmeticOperations.multiply(a_sx, b_jw);

        Number r_jx = ArithmeticOperations.sum(a_ix__b_jx, a_jx__b_jy,
                a_kx__b_jz, a_sx__b_jw);


        Number a_ix__b_kx = ArithmeticOperations.multiply(a_ix, b_kx);
        Number a_jx__b_ky = ArithmeticOperations.multiply(a_jx, b_ky);
        Number a_kx__b_kz = ArithmeticOperations.multiply(a_kx, b_kz);
        Number a_sx__b_kw = ArithmeticOperations.multiply(a_sx, b_kw);

        Number r_kx = ArithmeticOperations.sum(a_ix__b_kx, a_jx__b_ky,
                a_kx__b_kz, a_sx__b_kw);

        Number a_ix__b_sx = ArithmeticOperations.multiply(a_ix, b_sx);
        Number a_jx__b_sy = ArithmeticOperations.multiply(a_jx, b_sy);
        Number a_kx__b_sz = ArithmeticOperations.multiply(a_kx, b_sz);
        Number a_sx__b_sw = ArithmeticOperations.multiply(a_sx, b_sw);

        Number r_sx = ArithmeticOperations.sum(a_ix__b_sx, a_jx__b_sy,
                a_kx__b_sz, a_sx__b_sw);



        Number a_iy__b_ix = ArithmeticOperations.multiply(a_iy, b_ix);
        Number a_jy__b_iy = ArithmeticOperations.multiply(a_jy, b_iy);
        Number a_ky__b_iz = ArithmeticOperations.multiply(a_ky, b_iz);
        Number a_sy__b_iw = ArithmeticOperations.multiply(a_sy, b_iw);

        Number r_iy = ArithmeticOperations.sum(a_iy__b_ix, a_jy__b_iy,
                a_ky__b_iz, a_sy__b_iw);


        Number a_iy__b_jx = ArithmeticOperations.multiply(a_iy, b_jx);
        Number a_jy__b_jy = ArithmeticOperations.multiply(a_jy, b_jy);
        Number a_ky__b_jz = ArithmeticOperations.multiply(a_ky, b_jz);
        Number a_sy__b_jw = ArithmeticOperations.multiply(a_sy, b_jw);

        Number r_jy = ArithmeticOperations.sum(a_iy__b_jx, a_jy__b_jy,
                a_ky__b_jz, a_sy__b_jw);


        Number a_iy__b_kx = ArithmeticOperations.multiply(a_iy, b_kx);
        Number a_jy__b_ky = ArithmeticOperations.multiply(a_jy, b_ky);
        Number a_ky__b_kz = ArithmeticOperations.multiply(a_ky, b_kz);
        Number a_sy__b_kw = ArithmeticOperations.multiply(a_sy, b_kw);

        Number r_ky = ArithmeticOperations.sum(a_iy__b_kx, a_jy__b_ky,
                a_ky__b_kz, a_sy__b_kw);

        Number a_iy__b_sx = ArithmeticOperations.multiply(a_iy, b_sx);
        Number a_jy__b_sy = ArithmeticOperations.multiply(a_jy, b_sy);
        Number a_ky__b_sz = ArithmeticOperations.multiply(a_ky, b_sz);
        Number a_sy__b_sw = ArithmeticOperations.multiply(a_sy, b_sw);

        Number r_sy = ArithmeticOperations.sum(a_iy__b_sx, a_jy__b_sy,
                a_ky__b_sz, a_sy__b_sw);



        Number a_iz__b_ix = ArithmeticOperations.multiply(a_iz, b_ix);
        Number a_jz__b_iy = ArithmeticOperations.multiply(a_jz, b_iy);
        Number a_kz__b_iz = ArithmeticOperations.multiply(a_kz, b_iz);
        Number a_sz__b_iw = ArithmeticOperations.multiply(a_sz, b_iw);

        Number r_iz = ArithmeticOperations.sum(a_iz__b_ix, a_jz__b_iy,
                a_kz__b_iz, a_sz__b_iw);


        Number a_iz__b_jx = ArithmeticOperations.multiply(a_iz, b_jx);
        Number a_jz__b_jy = ArithmeticOperations.multiply(a_jz, b_jy);
        Number a_kz__b_jz = ArithmeticOperations.multiply(a_kz, b_jz);
        Number a_sz__b_jw = ArithmeticOperations.multiply(a_sz, b_jw);

        Number r_jz = ArithmeticOperations.sum(a_iz__b_jx, a_jz__b_jy,
                a_kz__b_jz, a_sz__b_jw);


        Number a_iz__b_kx = ArithmeticOperations.multiply(a_iz, b_kx);
        Number a_jz__b_ky = ArithmeticOperations.multiply(a_jz, b_ky);
        Number a_kz__b_kz = ArithmeticOperations.multiply(a_kz, b_kz);
        Number a_sz__b_kw = ArithmeticOperations.multiply(a_sz, b_kw);

        Number r_kz = ArithmeticOperations.sum(a_iz__b_kx, a_jz__b_ky,
                a_kz__b_kz, a_sz__b_kw);

        Number a_iz__b_sx = ArithmeticOperations.multiply(a_iz, b_sx);
        Number a_jz__b_sy = ArithmeticOperations.multiply(a_jz, b_sy);
        Number a_kz__b_sz = ArithmeticOperations.multiply(a_kz, b_sz);
        Number a_sz__b_sw = ArithmeticOperations.multiply(a_sz, b_sw);

        Number r_sz = ArithmeticOperations.sum(a_iz__b_sx, a_jz__b_sy,
                a_kz__b_sz, a_sz__b_sw);



        Number a_iw__b_ix = ArithmeticOperations.multiply(a_iw, b_ix);
        Number a_jw__b_iy = ArithmeticOperations.multiply(a_jw, b_iy);
        Number a_kw__b_iz = ArithmeticOperations.multiply(a_kw, b_iz);
        Number a_sw__b_iw = ArithmeticOperations.multiply(a_sw, b_iw);

        Number r_iw = ArithmeticOperations.sum(a_iw__b_ix, a_jw__b_iy,
                a_kw__b_iz, a_sw__b_iw);


        Number a_iw__b_jx = ArithmeticOperations.multiply(a_iw, b_jx);
        Number a_jw__b_jy = ArithmeticOperations.multiply(a_jw, b_jy);
        Number a_kw__b_jz = ArithmeticOperations.multiply(a_kw, b_jz);
        Number a_sw__b_jw = ArithmeticOperations.multiply(a_sw, b_jw);

        Number r_jw = ArithmeticOperations.sum(a_iw__b_jx, a_jw__b_jy,
                a_kw__b_jz, a_sw__b_jw);


        Number a_iw__b_kx = ArithmeticOperations.multiply(a_iw, b_kx);
        Number a_jw__b_ky = ArithmeticOperations.multiply(a_jw, b_ky);
        Number a_kw__b_kz = ArithmeticOperations.multiply(a_kw, b_kz);
        Number a_sw__b_kw = ArithmeticOperations.multiply(a_sw, b_kw);

        Number r_kw = ArithmeticOperations.sum(a_iw__b_kx, a_jw__b_ky,
                a_kw__b_kz, a_sw__b_kw);

        Number a_iw__b_sx = ArithmeticOperations.multiply(a_iw, b_sx);
        Number a_jw__b_sy = ArithmeticOperations.multiply(a_jw, b_sy);
        Number a_kw__b_sz = ArithmeticOperations.multiply(a_kw, b_sz);
        Number a_sw__b_sw = ArithmeticOperations.multiply(a_sw, b_sw);

        Number r_sw = ArithmeticOperations.sum(a_iw__b_sx, a_jw__b_sy,
                a_kw__b_sz, a_sw__b_sw);



        Class<? extends Number> highestPrecisionI = NumberComparator
                .greaterPrecisionType(r_ix, r_iy, r_iz, r_iw);
        Tuple4<Number> i = new Tuple4<Number>(r_ix, r_iy, r_iz, r_iw,
                highestPrecisionI);


        Class<? extends Number> highestPrecisionJ = NumberComparator
                .greaterPrecisionType(r_jx, r_jy, r_jz, r_jw);
        Tuple4<Number> j = new Tuple4<Number>(r_jx, r_jy, r_jz, r_jw,
                highestPrecisionJ);


        Class<? extends Number> highestPrecisionK = NumberComparator
                .greaterPrecisionType(r_kx, r_ky, r_kz, r_kw);
        Tuple4<Number> k = new Tuple4<Number>(r_kx, r_ky, r_kz, r_kw,
                highestPrecisionK);


        Class<? extends Number> highestPrecisionS = NumberComparator
                .greaterPrecisionType(r_sx, r_sy, r_sz, r_sw);
        Tuple4<Number> s = new Tuple4<Number>(r_sx, r_sy, r_sz, r_sw,
                highestPrecisionS);


        List<Class<? extends Number>> classes = new ArrayList<Class<? extends Number>>(
                3);
        classes.add(highestPrecisionI);
        classes.add(highestPrecisionJ);
        classes.add(highestPrecisionK);
        classes.add(highestPrecisionS);
        Class<? extends Number> highestPrecision = NumberComparator
                .greatestPrecisionType(classes);


        return new Matrix4<Number>(i, j, k, s, highestPrecision);
    }

    /**
     * Multiplies the two 4x4 matrices provided.
     * 
     * @param lhs
     *            the left-hand side matrix.
     * @param rhs
     *            the right-hand side matrix.
     * @param returnType
     *            the desired return type.
     * @return the resulting matrix.
     */
    public static <T extends Number, S extends Number, R extends Number> IMatrix4<R> multiply(
            final IMatrix4<T> lhs, final IMatrix4<S> rhs,
            final Class<? extends R> returnType) {

        Number a_ix = lhs.getI().getX();
        Number a_iy = lhs.getI().getY();
        Number a_iz = lhs.getI().getZ();
        Number a_iw = lhs.getI().getW();

        Number a_jx = lhs.getJ().getX();
        Number a_jy = lhs.getJ().getY();
        Number a_jz = lhs.getJ().getZ();
        Number a_jw = lhs.getJ().getW();

        Number a_kx = lhs.getK().getX();
        Number a_ky = lhs.getK().getY();
        Number a_kz = lhs.getK().getZ();
        Number a_kw = lhs.getK().getW();

        Number a_sx = lhs.getS().getX();
        Number a_sy = lhs.getS().getY();
        Number a_sz = lhs.getS().getZ();
        Number a_sw = lhs.getS().getW();



        Number b_ix = rhs.getI().getX();
        Number b_iy = rhs.getI().getY();
        Number b_iz = rhs.getI().getZ();
        Number b_iw = rhs.getI().getW();

        Number b_jx = rhs.getJ().getX();
        Number b_jy = rhs.getJ().getY();
        Number b_jz = rhs.getJ().getZ();
        Number b_jw = rhs.getJ().getW();

        Number b_kx = rhs.getK().getX();
        Number b_ky = rhs.getK().getY();
        Number b_kz = rhs.getK().getZ();
        Number b_kw = rhs.getK().getW();

        Number b_sx = rhs.getS().getX();
        Number b_sy = rhs.getS().getY();
        Number b_sz = rhs.getS().getZ();
        Number b_sw = rhs.getS().getW();



        Number a_ix__b_ix = ArithmeticOperations.multiply(a_ix, b_ix);
        Number a_jx__b_iy = ArithmeticOperations.multiply(a_jx, b_iy);
        Number a_kx__b_iz = ArithmeticOperations.multiply(a_kx, b_iz);
        Number a_sx__b_iw = ArithmeticOperations.multiply(a_sx, b_iw);

        Number r_ix = ArithmeticOperations.sum(a_ix__b_ix, a_jx__b_iy,
                a_kx__b_iz, a_sx__b_iw);


        Number a_ix__b_jx = ArithmeticOperations.multiply(a_ix, b_jx);
        Number a_jx__b_jy = ArithmeticOperations.multiply(a_jx, b_jy);
        Number a_kx__b_jz = ArithmeticOperations.multiply(a_kx, b_jz);
        Number a_sx__b_jw = ArithmeticOperations.multiply(a_sx, b_jw);

        Number r_jx = ArithmeticOperations.sum(a_ix__b_jx, a_jx__b_jy,
                a_kx__b_jz, a_sx__b_jw);


        Number a_ix__b_kx = ArithmeticOperations.multiply(a_ix, b_kx);
        Number a_jx__b_ky = ArithmeticOperations.multiply(a_jx, b_ky);
        Number a_kx__b_kz = ArithmeticOperations.multiply(a_kx, b_kz);
        Number a_sx__b_kw = ArithmeticOperations.multiply(a_sx, b_kw);

        Number r_kx = ArithmeticOperations.sum(a_ix__b_kx, a_jx__b_ky,
                a_kx__b_kz, a_sx__b_kw);

        Number a_ix__b_sx = ArithmeticOperations.multiply(a_ix, b_sx);
        Number a_jx__b_sy = ArithmeticOperations.multiply(a_jx, b_sy);
        Number a_kx__b_sz = ArithmeticOperations.multiply(a_kx, b_sz);
        Number a_sx__b_sw = ArithmeticOperations.multiply(a_sx, b_sw);

        Number r_sx = ArithmeticOperations.sum(a_ix__b_sx, a_jx__b_sy,
                a_kx__b_sz, a_sx__b_sw);



        Number a_iy__b_ix = ArithmeticOperations.multiply(a_iy, b_ix);
        Number a_jy__b_iy = ArithmeticOperations.multiply(a_jy, b_iy);
        Number a_ky__b_iz = ArithmeticOperations.multiply(a_ky, b_iz);
        Number a_sy__b_iw = ArithmeticOperations.multiply(a_sy, b_iw);

        Number r_iy = ArithmeticOperations.sum(a_iy__b_ix, a_jy__b_iy,
                a_ky__b_iz, a_sy__b_iw);


        Number a_iy__b_jx = ArithmeticOperations.multiply(a_iy, b_jx);
        Number a_jy__b_jy = ArithmeticOperations.multiply(a_jy, b_jy);
        Number a_ky__b_jz = ArithmeticOperations.multiply(a_ky, b_jz);
        Number a_sy__b_jw = ArithmeticOperations.multiply(a_sy, b_jw);

        Number r_jy = ArithmeticOperations.sum(a_iy__b_jx, a_jy__b_jy,
                a_ky__b_jz, a_sy__b_jw);


        Number a_iy__b_kx = ArithmeticOperations.multiply(a_iy, b_kx);
        Number a_jy__b_ky = ArithmeticOperations.multiply(a_jy, b_ky);
        Number a_ky__b_kz = ArithmeticOperations.multiply(a_ky, b_kz);
        Number a_sy__b_kw = ArithmeticOperations.multiply(a_sy, b_kw);

        Number r_ky = ArithmeticOperations.sum(a_iy__b_kx, a_jy__b_ky,
                a_ky__b_kz, a_sy__b_kw);

        Number a_iy__b_sx = ArithmeticOperations.multiply(a_iy, b_sx);
        Number a_jy__b_sy = ArithmeticOperations.multiply(a_jy, b_sy);
        Number a_ky__b_sz = ArithmeticOperations.multiply(a_ky, b_sz);
        Number a_sy__b_sw = ArithmeticOperations.multiply(a_sy, b_sw);

        Number r_sy = ArithmeticOperations.sum(a_iy__b_sx, a_jy__b_sy,
                a_ky__b_sz, a_sy__b_sw);



        Number a_iz__b_ix = ArithmeticOperations.multiply(a_iz, b_ix);
        Number a_jz__b_iy = ArithmeticOperations.multiply(a_jz, b_iy);
        Number a_kz__b_iz = ArithmeticOperations.multiply(a_kz, b_iz);
        Number a_sz__b_iw = ArithmeticOperations.multiply(a_sz, b_iw);

        Number r_iz = ArithmeticOperations.sum(a_iz__b_ix, a_jz__b_iy,
                a_kz__b_iz, a_sz__b_iw);


        Number a_iz__b_jx = ArithmeticOperations.multiply(a_iz, b_jx);
        Number a_jz__b_jy = ArithmeticOperations.multiply(a_jz, b_jy);
        Number a_kz__b_jz = ArithmeticOperations.multiply(a_kz, b_jz);
        Number a_sz__b_jw = ArithmeticOperations.multiply(a_sz, b_jw);

        Number r_jz = ArithmeticOperations.sum(a_iz__b_jx, a_jz__b_jy,
                a_kz__b_jz, a_sz__b_jw);


        Number a_iz__b_kx = ArithmeticOperations.multiply(a_iz, b_kx);
        Number a_jz__b_ky = ArithmeticOperations.multiply(a_jz, b_ky);
        Number a_kz__b_kz = ArithmeticOperations.multiply(a_kz, b_kz);
        Number a_sz__b_kw = ArithmeticOperations.multiply(a_sz, b_kw);

        Number r_kz = ArithmeticOperations.sum(a_iz__b_kx, a_jz__b_ky,
                a_kz__b_kz, a_sz__b_kw);

        Number a_iz__b_sx = ArithmeticOperations.multiply(a_iz, b_sx);
        Number a_jz__b_sy = ArithmeticOperations.multiply(a_jz, b_sy);
        Number a_kz__b_sz = ArithmeticOperations.multiply(a_kz, b_sz);
        Number a_sz__b_sw = ArithmeticOperations.multiply(a_sz, b_sw);

        Number r_sz = ArithmeticOperations.sum(a_iz__b_sx, a_jz__b_sy,
                a_kz__b_sz, a_sz__b_sw);



        Number a_iw__b_ix = ArithmeticOperations.multiply(a_iw, b_ix);
        Number a_jw__b_iy = ArithmeticOperations.multiply(a_jw, b_iy);
        Number a_kw__b_iz = ArithmeticOperations.multiply(a_kw, b_iz);
        Number a_sw__b_iw = ArithmeticOperations.multiply(a_sw, b_iw);

        Number r_iw = ArithmeticOperations.sum(a_iw__b_ix, a_jw__b_iy,
                a_kw__b_iz, a_sw__b_iw);


        Number a_iw__b_jx = ArithmeticOperations.multiply(a_iw, b_jx);
        Number a_jw__b_jy = ArithmeticOperations.multiply(a_jw, b_jy);
        Number a_kw__b_jz = ArithmeticOperations.multiply(a_kw, b_jz);
        Number a_sw__b_jw = ArithmeticOperations.multiply(a_sw, b_jw);

        Number r_jw = ArithmeticOperations.sum(a_iw__b_jx, a_jw__b_jy,
                a_kw__b_jz, a_sw__b_jw);


        Number a_iw__b_kx = ArithmeticOperations.multiply(a_iw, b_kx);
        Number a_jw__b_ky = ArithmeticOperations.multiply(a_jw, b_ky);
        Number a_kw__b_kz = ArithmeticOperations.multiply(a_kw, b_kz);
        Number a_sw__b_kw = ArithmeticOperations.multiply(a_sw, b_kw);

        Number r_kw = ArithmeticOperations.sum(a_iw__b_kx, a_jw__b_ky,
                a_kw__b_kz, a_sw__b_kw);

        Number a_iw__b_sx = ArithmeticOperations.multiply(a_iw, b_sx);
        Number a_jw__b_sy = ArithmeticOperations.multiply(a_jw, b_sy);
        Number a_kw__b_sz = ArithmeticOperations.multiply(a_kw, b_sz);
        Number a_sw__b_sw = ArithmeticOperations.multiply(a_sw, b_sw);

        Number r_sw = ArithmeticOperations.sum(a_iw__b_sx, a_jw__b_sy,
                a_kw__b_sz, a_sw__b_sw);



        Class<? extends Number> highestPrecisionI = NumberComparator
                .greaterPrecisionType(r_ix, r_iy, r_iz, r_iw);
        Tuple4<Number> i = new Tuple4<Number>(r_ix, r_iy, r_iz, r_iw,
                highestPrecisionI);


        Class<? extends Number> highestPrecisionJ = NumberComparator
                .greaterPrecisionType(r_jx, r_jy, r_jz, r_jw);
        Tuple4<Number> j = new Tuple4<Number>(r_jx, r_jy, r_jz, r_jw,
                highestPrecisionJ);


        Class<? extends Number> highestPrecisionK = NumberComparator
                .greaterPrecisionType(r_kx, r_ky, r_kz, r_kw);
        Tuple4<Number> k = new Tuple4<Number>(r_kx, r_ky, r_kz, r_kw,
                highestPrecisionK);


        Class<? extends Number> highestPrecisionS = NumberComparator
                .greaterPrecisionType(r_sx, r_sy, r_sz, r_sw);
        Tuple4<Number> s = new Tuple4<Number>(r_sx, r_sy, r_sz, r_sw,
                highestPrecisionS);

        return new Matrix4<R>(i, j, k, s, returnType);
    }
}
