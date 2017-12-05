package org.jutility.math.vectoralgebra;


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


import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.math.arithmetics.ArithmeticOperations;



/**
 * The {@code VectorAlgebraicOperations} class is a utility class for Tuple and
 * Matrix operations.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public final class VectorAlgebraicOperations {

    /**
     * Adds the left-hand tuple to the right-hand tuple.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
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
    protected static final <T extends Number> ITuple4<T> add(
            final ITuple4<T> lhs, final ITuple4<T> rhs) {

        return VectorAlgebraicOperations.add(lhs, rhs, lhs.getType());
    }

    /**
     * Adds the left-hand tuple to the right-hand tuple.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
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
    protected static final <R extends Number> ITuple4<R> add(
            final ITuple4<?> lhs, final ITuple4<?> rhs,
            final Class<? extends R> returnType) {

        final Number newX = ArithmeticOperations.add(lhs.getX(), rhs.getX());
        final Number newY = ArithmeticOperations.add(lhs.getY(), rhs.getY());
        final Number newZ = ArithmeticOperations.add(lhs.getZ(), rhs.getZ());
        final Number newW = ArithmeticOperations.add(lhs.getW(), rhs.getW());

        return new Tuple4<>(newX, newY, newZ, newW, returnType);
    }


    /**
     * Adds the left-hand vector to the right-hand vector.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            The left-hand vector.
     * @param rhs
     *            The right-hand vector.
     * @return The vector sum of the vectors.
     */
    public static final <T extends Number> IVector4<T> add(
            final IVector4<T> lhs, final IVector4<T> rhs) {

        return VectorAlgebraicOperations.add(lhs, rhs, lhs.getType());
    }

    /**
     * Adds the left-hand vector to the right-hand vector.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            The left-hand vector.
     * @param rhs
     *            The right-hand vector.
     * @param returnType
     *            the desired return type.
     * @return The vector sum of the vectors.
     */
    public static final <R extends Number> IVector4<R> add(
            final IVector4<?> lhs, final IVector4<?> rhs,
            final Class<? extends R> returnType) {

        return new Vector4<>(VectorAlgebraicOperations.add((ITuple4<?>) lhs,
                (ITuple4<?>) rhs, returnType));
    }


    /**
     * Adds the left-hand point to the right-hand vector.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            The left-hand point.
     * @param rhs
     *            The right-hand vector.
     * @return The vector sum of the point and the vector.
     */
    public static final <T extends Number> IPoint4<T> add(final IPoint4<T> lhs,
            final IVector4<T> rhs) {

        return VectorAlgebraicOperations.add(lhs, rhs, lhs.getType());
    }

    /**
     * Adds the left-hand point to the right-hand vector.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            The left-hand point.
     * @param rhs
     *            The right-hand vector.
     * @param returnType
     *            the desired return type.
     * @return The vector sum of the point and the vector.
     */
    public static final <R extends Number> IPoint4<R> add(final IPoint4<?> lhs,
            final IVector4<?> rhs, final Class<? extends R> returnType) {

        return new Point4<>(VectorAlgebraicOperations.add((ITuple4<?>) lhs,
                (ITuple4<?>) rhs, returnType));
    }


    /**
     * Adds the left-hand vector to the right-hand point.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            The left-hand vector.
     * @param rhs
     *            The right-hand point.
     * @return The vector sum of the vector and the point.
     */
    public static final <T extends Number> IPoint4<T> add(
            final IVector4<T> lhs, final IPoint4<T> rhs) {

        return VectorAlgebraicOperations.add(rhs, lhs);
    }

    /**
     * Adds the left-hand vector to the right-hand point.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            The left-hand vector.
     * @param rhs
     *            The right-hand point.
     * @param returnType
     *            the desired return type.
     * @return The vector sum of the vector and the point.
     */
    public static final <R extends Number> IPoint4<R> add(
            final IVector4<?> lhs, final IPoint4<?> rhs,
            final Class<? extends R> returnType) {

        return VectorAlgebraicOperations.add(rhs, lhs, returnType);
    }

    /**
     * Subtracts the right-hand tuple from the left-hand tuple.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            the left-hand tuple.
     * @param rhs
     *            the right-hand tuple.
     * @return the difference between the tuples.
     * @throws ArithmeticException
     *             if the operation is not a valid arithmetic operation.
     */
    protected static final <T extends Number> ITuple4<T> subtract(
            final ITuple4<T> lhs, final ITuple4<T> rhs) {

        return VectorAlgebraicOperations.subtract(lhs, rhs, lhs.getType());
    }

    /**
     * Subtracts the right-hand tuple from the left-hand tuple.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
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
    protected static final <R extends Number> ITuple4<R> subtract(
            final ITuple4<?> lhs, final ITuple4<?> rhs,
            final Class<? extends R> returnType) {

        if (lhs.isVector() && rhs.isPoint()) {

            throw new IllegalArgumentException(
                    "Cannot subtract a point from a vector!");
        }

        final Number newX = ArithmeticOperations.subtract(lhs.getX(),
                rhs.getX());
        final Number newY = ArithmeticOperations.subtract(lhs.getY(),
                rhs.getY());
        final Number newZ = ArithmeticOperations.subtract(lhs.getZ(),
                rhs.getZ());
        final Number newW = ArithmeticOperations.subtract(lhs.getW(),
                rhs.getW());

        return new Tuple4<>(newX, newY, newZ, newW, returnType);
    }


    /**
     * Subtracts the right-hand point from the left-hand point.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            the left-hand point.
     * @param rhs
     *            the right-hand point.
     * @return the difference between the points.
     */
    public static final <T extends Number> IVector4<T> subtract(
            final IPoint4<T> lhs, final IPoint4<T> rhs) {

        return VectorAlgebraicOperations.subtract(lhs, rhs, lhs.getType());
    }

    /**
     * Subtracts the right-hand point from the left-hand point.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            the left-hand point.
     * @param rhs
     *            the right-hand point.
     * @param returnType
     *            the desired return type.
     * @return the difference between the points.
     */
    public static final <R extends Number> IVector4<R> subtract(
            final IPoint4<?> lhs, final IPoint4<?> rhs,
            final Class<? extends R> returnType) {

        return new Vector4<>(
                VectorAlgebraicOperations.subtract((ITuple4<?>) lhs, (ITuple4
                        <?>) rhs, returnType));
    }


    /**
     * Subtracts the right-hand vector from the left-hand point.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            the left-hand point.
     * @param rhs
     *            the right-hand vector.
     * @return the difference between the point and the vector.
     */
    public static final <T extends Number> IPoint4<T> subtract(
            final IPoint4<T> lhs, final IVector4<T> rhs) {

        return VectorAlgebraicOperations.subtract(lhs, rhs, lhs.getType());
    }


    /**
     * Subtracts the right-hand vector from the left-hand point.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            the left-hand point.
     * @param rhs
     *            the right-hand vector.
     * @param returnType
     *            the desired return type.
     * @return the difference between the point and the vector.
     */
    public static final <R extends Number> IPoint4<R> subtract(
            final IPoint4<?> lhs, final IVector4<?> rhs,
            final Class<? extends R> returnType) {

        return new Point4<>(VectorAlgebraicOperations.subtract((ITuple4<?>) lhs,
                (ITuple4<?>) rhs, returnType));
    }


    /**
     * Multiplies the tuple by the provided scalar.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            The tuple.
     * @param rhs
     *            The scalar.
     * @return The scaled tuple.
     * @throws ArithmeticException
     *             if the operation is not a valid arithmetic operation.
     */
    protected static final <T extends Number> ITuple4<T> multiply(
            final ITuple4<T> lhs, final T rhs) {

        return VectorAlgebraicOperations.multiply(lhs, rhs, lhs.getType());
    }

    /**
     * Multiplies the tuple by the provided scalar.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
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
    protected static final <R extends Number> ITuple4<R> multiply(
            final ITuple4<?> lhs, final Number rhs,
            final Class<? extends R> returnType) {

        if (lhs.isPoint()) {

            throw new IllegalArgumentException("Cannot scale a point!");
        }

        final Number newX = ArithmeticOperations.multiply(lhs.getX(), rhs);
        final Number newY = ArithmeticOperations.multiply(lhs.getY(), rhs);
        final Number newZ = ArithmeticOperations.multiply(lhs.getZ(), rhs);
        final Number newW = ArithmeticOperations.multiply(lhs.getW(), rhs);

        return new Tuple4<>(newX, newY, newZ, newW, returnType);
    }


    /**
     * Multiplies the vector by the provided scalar.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            The tuple.
     * @param rhs
     *            The scalar.
     * @return The scaled tuple.
     */
    public static final <T extends Number> IVector4<T> multiply(
            final IVector4<T> lhs, final T rhs) {

        return VectorAlgebraicOperations.multiply(lhs, rhs, lhs.getType());
    }

    /**
     * Multiplies the vector by the provided scalar.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            The tuple.
     * @param rhs
     *            The scalar.
     * @param returnType
     *            the desired return type.
     * @return The scaled tuple.
     */
    public static final <R extends Number> IVector4<R> multiply(
            final IVector4<?> lhs, final Number rhs,
            final Class<? extends R> returnType) {

        return new Vector4<>(
                VectorAlgebraicOperations.multiply((ITuple4<?>) lhs, rhs,
                        returnType));
    }


    /**
     * Multiplies the vector by the provided scalar.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            The scalar.
     * @param rhs
     *            The vector.
     * @return The scaled vector.
     */
    public static final <T extends Number> IVector4<T> multiply(final T lhs,
            final IVector4<T> rhs) {

        return VectorAlgebraicOperations.multiply(rhs, lhs);
    }

    /**
     * Multiplies the vector by the provided scalar.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            The scalar.
     * @param rhs
     *            The vector.
     * @param returnType
     *            the desired return type.
     * @return The scaled vector.
     */
    public static final <R extends Number> IVector4<R> multiply(
            final Number lhs, final IVector4<?> rhs,
            final Class<? extends R> returnType) {

        return VectorAlgebraicOperations.multiply(rhs, lhs, returnType);
    }



    /**
     * Multiplies the matrix by the provided tuple.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the tuple.
     * @return The resulting tuple.
     */
    public static final <T extends Number> ITuple4<T> multiply(
            final IMatrix4<T> lhs, final ITuple4<T> rhs) {

        return VectorAlgebraicOperations.multiply(lhs, rhs, lhs.getType());
    }

    /**
     * Multiplies the matrix by the provided tuple.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the tuple.
     * @param returnType
     *            the desired return type.
     * @return The resulting tuple.
     */
    public static final <R extends Number> ITuple4<R> multiply(
            final IMatrix4<?> lhs, final ITuple4<?> rhs,
            final Class<? extends R> returnType) {

        final Number ix = lhs.getI().getX();
        final Number iy = lhs.getI().getY();
        final Number iz = lhs.getI().getZ();
        final Number iw = lhs.getI().getW();

        final Number jx = lhs.getJ().getX();
        final Number jy = lhs.getJ().getY();
        final Number jz = lhs.getJ().getZ();
        final Number jw = lhs.getJ().getW();

        final Number kx = lhs.getK().getX();
        final Number ky = lhs.getK().getY();
        final Number kz = lhs.getK().getZ();
        final Number kw = lhs.getK().getW();

        final Number sx = lhs.getS().getX();
        final Number sy = lhs.getS().getY();
        final Number sz = lhs.getS().getZ();
        final Number sw = lhs.getS().getW();

        final Number vx = rhs.getX();
        final Number vy = rhs.getY();
        final Number vz = rhs.getZ();
        final Number vw = rhs.getW();

        final Number ix_vx = ArithmeticOperations.multiply(ix, vx);
        final Number jx_vy = ArithmeticOperations.multiply(jx, vy);
        final Number kx_vz = ArithmeticOperations.multiply(kx, vz);
        final Number sx_vw = ArithmeticOperations.multiply(sx, vw);

        final Number x = ArithmeticOperations.sum(ix_vx, jx_vy, kx_vz, sx_vw);

        final Number iy_vx = ArithmeticOperations.multiply(iy, vx);
        final Number jy_vy = ArithmeticOperations.multiply(jy, vy);
        final Number ky_vz = ArithmeticOperations.multiply(ky, vz);
        final Number sy_vw = ArithmeticOperations.multiply(sy, vw);

        final Number y = ArithmeticOperations.sum(iy_vx, jy_vy, ky_vz, sy_vw);


        final Number iz_vx = ArithmeticOperations.multiply(iz, vx);
        final Number jz_vy = ArithmeticOperations.multiply(jz, vy);
        final Number kz_vz = ArithmeticOperations.multiply(kz, vz);
        final Number sz_vw = ArithmeticOperations.multiply(sz, vw);

        final Number z = ArithmeticOperations.sum(iz_vx, jz_vy, kz_vz, sz_vw);

        final Number iw_vx = ArithmeticOperations.multiply(iw, vx);
        final Number jw_vy = ArithmeticOperations.multiply(jw, vy);
        final Number kw_vz = ArithmeticOperations.multiply(kw, vz);
        final Number sw_vw = ArithmeticOperations.multiply(sw, vw);

        final Number w = ArithmeticOperations.sum(iw_vx, jw_vy, kw_vz, sw_vw);

        return new Tuple4<>(x, y, z, w, returnType);
    }


    /**
     * Multiplies the matrix by the provided point.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the point.
     * @return The resulting point.
     */
    public static final <T extends Number> IPoint4<T> multiply(
            final IMatrix4<T> lhs, final IPoint4<T> rhs) {

        return VectorAlgebraicOperations.multiply(lhs, rhs, lhs.getType());
    }

    /**
     * Multiplies the matrix by the provided point.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the point.
     * @param returnType
     *            the desired return type.
     * @return The resulting point.
     */
    public static final <R extends Number> IPoint4<R> multiply(
            final IMatrix4<?> lhs, final IPoint4<?> rhs,
            final Class<? extends R> returnType) {

        return new Point4<>(VectorAlgebraicOperations.multiply(lhs, (ITuple4<?>) rhs,
                returnType));
    }


    /**
     * Multiplies the matrix by the provided vector.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the vector.
     * @return The resulting vector.
     */
    public static final <T extends Number> IVector4<T> multiply(
            final IMatrix4<T> lhs, final IVector4<T> rhs) {

        return VectorAlgebraicOperations.multiply(lhs, rhs, lhs.getType());
    }

    /**
     * Multiplies the matrix by the provided vector.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            the matrix.
     * @param rhs
     *            the vector.
     * @param returnType
     *            the desired return type.
     * @return The resulting vector.
     */
    public static final <R extends Number> IVector4<R> multiply(
            final IMatrix4<?> lhs, final IVector4<?> rhs,
            final Class<? extends R> returnType) {

        return new Vector4<>(VectorAlgebraicOperations.multiply(lhs, (ITuple4<?>) rhs,
                returnType));
    }


    /**
     * Multiplies the two 4x4 matrices provided.
     *
     * @param <T>
     *            the {@link Number} type of the operands and result.
     *
     * @param lhs
     *            the left-hand side matrix.
     * @param rhs
     *            the right-hand side matrix.
     * @return the resulting matrix.
     */
    public static final <T extends Number> IMatrix4<T> multiply(
            final IMatrix4<T> lhs, final IMatrix4<T> rhs) {

        return VectorAlgebraicOperations.multiply(lhs, rhs, lhs.getType());
    }

    /**
     * Multiplies the two 4x4 matrices provided.
     * 
     * @param <R>
     *            the {@link Number} type of the result.
     *
     * @param lhs
     *            the left-hand side matrix.
     * @param rhs
     *            the right-hand side matrix.
     * @param returnType
     *            the desired return type.
     * @return the resulting matrix.
     */
    public static final <R extends Number> IMatrix4<R> multiply(
            final IMatrix4<?> lhs, final IMatrix4<?> rhs,
            final Class<? extends R> returnType) {

        final Number a_ix = lhs.getI().getX();
        final Number a_iy = lhs.getI().getY();
        final Number a_iz = lhs.getI().getZ();
        final Number a_iw = lhs.getI().getW();

        final Number a_jx = lhs.getJ().getX();
        final Number a_jy = lhs.getJ().getY();
        final Number a_jz = lhs.getJ().getZ();
        final Number a_jw = lhs.getJ().getW();

        final Number a_kx = lhs.getK().getX();
        final Number a_ky = lhs.getK().getY();
        final Number a_kz = lhs.getK().getZ();
        final Number a_kw = lhs.getK().getW();

        final Number a_sx = lhs.getS().getX();
        final Number a_sy = lhs.getS().getY();
        final Number a_sz = lhs.getS().getZ();
        final Number a_sw = lhs.getS().getW();



        final Number b_ix = rhs.getI().getX();
        final Number b_iy = rhs.getI().getY();
        final Number b_iz = rhs.getI().getZ();
        final Number b_iw = rhs.getI().getW();

        final Number b_jx = rhs.getJ().getX();
        final Number b_jy = rhs.getJ().getY();
        final Number b_jz = rhs.getJ().getZ();
        final Number b_jw = rhs.getJ().getW();

        final Number b_kx = rhs.getK().getX();
        final Number b_ky = rhs.getK().getY();
        final Number b_kz = rhs.getK().getZ();
        final Number b_kw = rhs.getK().getW();

        final Number b_sx = rhs.getS().getX();
        final Number b_sy = rhs.getS().getY();
        final Number b_sz = rhs.getS().getZ();
        final Number b_sw = rhs.getS().getW();



        final Number a_ix__b_ix = ArithmeticOperations.multiply(a_ix, b_ix);
        final Number a_jx__b_iy = ArithmeticOperations.multiply(a_jx, b_iy);
        final Number a_kx__b_iz = ArithmeticOperations.multiply(a_kx, b_iz);
        final Number a_sx__b_iw = ArithmeticOperations.multiply(a_sx, b_iw);

        final Number r_ix = ArithmeticOperations.sum(a_ix__b_ix, a_jx__b_iy,
                a_kx__b_iz, a_sx__b_iw);


        final Number a_ix__b_jx = ArithmeticOperations.multiply(a_ix, b_jx);
        final Number a_jx__b_jy = ArithmeticOperations.multiply(a_jx, b_jy);
        final Number a_kx__b_jz = ArithmeticOperations.multiply(a_kx, b_jz);
        final Number a_sx__b_jw = ArithmeticOperations.multiply(a_sx, b_jw);

        final Number r_jx = ArithmeticOperations.sum(a_ix__b_jx, a_jx__b_jy,
                a_kx__b_jz, a_sx__b_jw);


        final Number a_ix__b_kx = ArithmeticOperations.multiply(a_ix, b_kx);
        final Number a_jx__b_ky = ArithmeticOperations.multiply(a_jx, b_ky);
        final Number a_kx__b_kz = ArithmeticOperations.multiply(a_kx, b_kz);
        final Number a_sx__b_kw = ArithmeticOperations.multiply(a_sx, b_kw);

        final Number r_kx = ArithmeticOperations.sum(a_ix__b_kx, a_jx__b_ky,
                a_kx__b_kz, a_sx__b_kw);

        final Number a_ix__b_sx = ArithmeticOperations.multiply(a_ix, b_sx);
        final Number a_jx__b_sy = ArithmeticOperations.multiply(a_jx, b_sy);
        final Number a_kx__b_sz = ArithmeticOperations.multiply(a_kx, b_sz);
        final Number a_sx__b_sw = ArithmeticOperations.multiply(a_sx, b_sw);

        final Number r_sx = ArithmeticOperations.sum(a_ix__b_sx, a_jx__b_sy,
                a_kx__b_sz, a_sx__b_sw);



        final Number a_iy__b_ix = ArithmeticOperations.multiply(a_iy, b_ix);
        final Number a_jy__b_iy = ArithmeticOperations.multiply(a_jy, b_iy);
        final Number a_ky__b_iz = ArithmeticOperations.multiply(a_ky, b_iz);
        final Number a_sy__b_iw = ArithmeticOperations.multiply(a_sy, b_iw);

        final Number r_iy = ArithmeticOperations.sum(a_iy__b_ix, a_jy__b_iy,
                a_ky__b_iz, a_sy__b_iw);


        final Number a_iy__b_jx = ArithmeticOperations.multiply(a_iy, b_jx);
        final Number a_jy__b_jy = ArithmeticOperations.multiply(a_jy, b_jy);
        final Number a_ky__b_jz = ArithmeticOperations.multiply(a_ky, b_jz);
        final Number a_sy__b_jw = ArithmeticOperations.multiply(a_sy, b_jw);

        final Number r_jy = ArithmeticOperations.sum(a_iy__b_jx, a_jy__b_jy,
                a_ky__b_jz, a_sy__b_jw);


        final Number a_iy__b_kx = ArithmeticOperations.multiply(a_iy, b_kx);
        final Number a_jy__b_ky = ArithmeticOperations.multiply(a_jy, b_ky);
        final Number a_ky__b_kz = ArithmeticOperations.multiply(a_ky, b_kz);
        final Number a_sy__b_kw = ArithmeticOperations.multiply(a_sy, b_kw);

        final Number r_ky = ArithmeticOperations.sum(a_iy__b_kx, a_jy__b_ky,
                a_ky__b_kz, a_sy__b_kw);

        final Number a_iy__b_sx = ArithmeticOperations.multiply(a_iy, b_sx);
        final Number a_jy__b_sy = ArithmeticOperations.multiply(a_jy, b_sy);
        final Number a_ky__b_sz = ArithmeticOperations.multiply(a_ky, b_sz);
        final Number a_sy__b_sw = ArithmeticOperations.multiply(a_sy, b_sw);

        final Number r_sy = ArithmeticOperations.sum(a_iy__b_sx, a_jy__b_sy,
                a_ky__b_sz, a_sy__b_sw);



        final Number a_iz__b_ix = ArithmeticOperations.multiply(a_iz, b_ix);
        final Number a_jz__b_iy = ArithmeticOperations.multiply(a_jz, b_iy);
        final Number a_kz__b_iz = ArithmeticOperations.multiply(a_kz, b_iz);
        final Number a_sz__b_iw = ArithmeticOperations.multiply(a_sz, b_iw);

        final Number r_iz = ArithmeticOperations.sum(a_iz__b_ix, a_jz__b_iy,
                a_kz__b_iz, a_sz__b_iw);


        final Number a_iz__b_jx = ArithmeticOperations.multiply(a_iz, b_jx);
        final Number a_jz__b_jy = ArithmeticOperations.multiply(a_jz, b_jy);
        final Number a_kz__b_jz = ArithmeticOperations.multiply(a_kz, b_jz);
        final Number a_sz__b_jw = ArithmeticOperations.multiply(a_sz, b_jw);

        final Number r_jz = ArithmeticOperations.sum(a_iz__b_jx, a_jz__b_jy,
                a_kz__b_jz, a_sz__b_jw);


        final Number a_iz__b_kx = ArithmeticOperations.multiply(a_iz, b_kx);
        final Number a_jz__b_ky = ArithmeticOperations.multiply(a_jz, b_ky);
        final Number a_kz__b_kz = ArithmeticOperations.multiply(a_kz, b_kz);
        final Number a_sz__b_kw = ArithmeticOperations.multiply(a_sz, b_kw);

        final Number r_kz = ArithmeticOperations.sum(a_iz__b_kx, a_jz__b_ky,
                a_kz__b_kz, a_sz__b_kw);

        final Number a_iz__b_sx = ArithmeticOperations.multiply(a_iz, b_sx);
        final Number a_jz__b_sy = ArithmeticOperations.multiply(a_jz, b_sy);
        final Number a_kz__b_sz = ArithmeticOperations.multiply(a_kz, b_sz);
        final Number a_sz__b_sw = ArithmeticOperations.multiply(a_sz, b_sw);

        final Number r_sz = ArithmeticOperations.sum(a_iz__b_sx, a_jz__b_sy,
                a_kz__b_sz, a_sz__b_sw);



        final Number a_iw__b_ix = ArithmeticOperations.multiply(a_iw, b_ix);
        final Number a_jw__b_iy = ArithmeticOperations.multiply(a_jw, b_iy);
        final Number a_kw__b_iz = ArithmeticOperations.multiply(a_kw, b_iz);
        final Number a_sw__b_iw = ArithmeticOperations.multiply(a_sw, b_iw);

        final Number r_iw = ArithmeticOperations.sum(a_iw__b_ix, a_jw__b_iy,
                a_kw__b_iz, a_sw__b_iw);


        final Number a_iw__b_jx = ArithmeticOperations.multiply(a_iw, b_jx);
        final Number a_jw__b_jy = ArithmeticOperations.multiply(a_jw, b_jy);
        final Number a_kw__b_jz = ArithmeticOperations.multiply(a_kw, b_jz);
        final Number a_sw__b_jw = ArithmeticOperations.multiply(a_sw, b_jw);

        final Number r_jw = ArithmeticOperations.sum(a_iw__b_jx, a_jw__b_jy,
                a_kw__b_jz, a_sw__b_jw);


        final Number a_iw__b_kx = ArithmeticOperations.multiply(a_iw, b_kx);
        final Number a_jw__b_ky = ArithmeticOperations.multiply(a_jw, b_ky);
        final Number a_kw__b_kz = ArithmeticOperations.multiply(a_kw, b_kz);
        final Number a_sw__b_kw = ArithmeticOperations.multiply(a_sw, b_kw);

        final Number r_kw = ArithmeticOperations.sum(a_iw__b_kx, a_jw__b_ky,
                a_kw__b_kz, a_sw__b_kw);

        final Number a_iw__b_sx = ArithmeticOperations.multiply(a_iw, b_sx);
        final Number a_jw__b_sy = ArithmeticOperations.multiply(a_jw, b_sy);
        final Number a_kw__b_sz = ArithmeticOperations.multiply(a_kw, b_sz);
        final Number a_sw__b_sw = ArithmeticOperations.multiply(a_sw, b_sw);

        final Number r_sw = ArithmeticOperations.sum(a_iw__b_sx, a_jw__b_sy,
                a_kw__b_sz, a_sw__b_sw);



        final Class<? extends Number> highestPrecisionI = NumberComparator
                .greaterPrecisionType(r_ix, r_iy, r_iz, r_iw);
        final Tuple4<Number> i = new Tuple4<>(r_ix, r_iy, r_iz, r_iw,
                highestPrecisionI);


        final Class<? extends Number> highestPrecisionJ = NumberComparator
                .greaterPrecisionType(r_jx, r_jy, r_jz, r_jw);
        final Tuple4<Number> j = new Tuple4<>(r_jx, r_jy, r_jz, r_jw,
                highestPrecisionJ);


        final Class<? extends Number> highestPrecisionK = NumberComparator
                .greaterPrecisionType(r_kx, r_ky, r_kz, r_kw);
        final Tuple4<Number> k = new Tuple4<>(r_kx, r_ky, r_kz, r_kw,
                highestPrecisionK);


        final Class<? extends Number> highestPrecisionS = NumberComparator
                .greaterPrecisionType(r_sx, r_sy, r_sz, r_sw);
        final Tuple4<Number> s = new Tuple4<>(r_sx, r_sy, r_sz, r_sw,
                highestPrecisionS);

        return new Matrix4<>(i, j, k, s, returnType);
    }
}
