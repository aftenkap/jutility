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



import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.math.arithmetics.ArithmeticOperations;


/**
 * The generic {@code Vector4} class provides a reference implementation of the
 * {@link IVector4} interface.
 *
 * @param <T>
 *            the {@link Number} type of this {@code Vector4}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Vector4")
@XmlType(name = "Vector4")
public class Vector4<T extends Number>
        extends Tuple4<T>
        implements IVector4<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -6222429456842409243L;


    /**
     * Creates the i unit vector for the provided type.
     *
     * @param <T>
     *            the {@link Number} type of the {@link IVector4 Vector}.
     *
     * @param type
     *            the desired type of the vector.
     * @return the i unit vector.
     */
    public static final <T extends Number> IVector4<T> I_UNIT_VECTOR(
            final Class<? extends T> type) {

        return new Vector4<>(1, 0, 0, type);
    }


    /**
     * Creates the j unit vector for the provided type.
     *
     * @param <T>
     *            the {@link Number} type of the {@link IVector4 Vector}.
     *
     * @param type
     *            the desired type of the vector.
     * @return the j unit vector.
     */
    public static final <T extends Number> IVector4<T> J_UNIT_VECTOR(
            final Class<? extends T> type) {

        return new Vector4<>(0f, 1f, 0f, type);
    }


    /**
     * Creates the k unit vector for the provided type.
     *
     * @param <T>
     *            the {@link Number} type of the {@link IVector4 Vector}.
     *
     * @param type
     *            the desired type of the vector.
     * @return the k unit vector.
     */
    public static final <T extends Number> IVector4<T> K_UNIT_VECTOR(
            final Class<? extends T> type) {

        return new Vector4<>(0f, 0f, 1f, type);
    }


    /**
     * Creates the null vector for the provided type.
     *
     * @param <T>
     *            the {@link Number} type of the {@link IVector4 Vector}.
     *
     * @param type
     *            the desired type of the vector.
     * @return the null vector.
     */
    public static final <T extends Number> IVector4<T> NULL_VECTOR(
            final Class<? extends T> type) {

        return new Vector4<>(0f, 0f, 0f, type);
    }

    /**
     * Creates a new instance of the {@code Vector4} class. (Serialization
     * Constructor)
     */
    protected Vector4() {

        super();
    }


    /**
     * Creates a new instance of the {@code Vector4} class with the provided
     * type and values.
     *
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     * @param type
     *            The type of this tuple.
     */
    public Vector4(final Number x, final Number y, final Number z,
            final Class<? extends T> type) {

        super(x, y, z, 0, type);
    }


    /**
     * Copy constructor.
     *
     * Creates a new vector from the tuple provided.
     *
     * @param tuple
     *            The tuple to be copied.
     */
    public Vector4(final ITuple4<T> tuple) {

        this(tuple, tuple.getType());
    }

    /**
     * Copy constructor.
     *
     * Creates a new vector from the tuple provided.
     *
     * @param tuple
     *            The tuple to be copied.
     * @param returnType
     *            the desired return type.
     */
    public Vector4(final ITuple4<? extends Number> tuple,
            final Class<? extends T> returnType) {

        super(tuple, returnType);

        if (!tuple.isVector()) {

            throw new IllegalArgumentException(
                    "Provided parameter is not a vector!");
        }
    }



    @Override
    public T length() {

        final Class<? extends T> type = this.getType();

        final T xSquared = ArithmeticOperations.multiply(this.getX(),
                this.getX(), type);
        final T ySquared = ArithmeticOperations.multiply(this.getY(),
                this.getY(), type);
        final T zSquared = ArithmeticOperations.multiply(this.getZ(),
                this.getZ(), type);
        final T wSquared = ArithmeticOperations.multiply(this.getW(),
                this.getW(), type);

        T sum = ArithmeticOperations.add(xSquared, ySquared, type);
        sum = ArithmeticOperations.add(sum, zSquared, type);
        sum = ArithmeticOperations.add(sum, wSquared, type);

        final Double squareRoot = Math.sqrt(sum.doubleValue());

        return NumberUtils.cast(squareRoot, type);
    }



    @Override
    public Vector4<T> normalizedVector() {

        final T length = this.length();
        final Class<? extends T> type = this.getType();

        if (!NumberComparator.equals(length, 0)) {

            final T normalizedX = ArithmeticOperations.divide(this.getX(),
                    length, type);
            final T normalizedY = ArithmeticOperations.divide(this.getY(),
                    length, type);
            final T normalizedZ = ArithmeticOperations.divide(this.getZ(),
                    length, type);

            return new Vector4<>(normalizedX, normalizedY, normalizedZ, type);
        }
        else {

            return new Vector4<>(this);
        }
    }

    @Override
    public T dotProduct(final IVector4<T> rhs) {

        return Vector4.dotProduct(this, rhs);
    }

    /**
     * Calculates the dot product of two {@link IVector4 Vectors}.
     *
     * @param <T>
     *            the {@link Number} type of the input {@link IVector4 Vectors}.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the dot product.
     */
    public static <T extends Number> T dotProduct(final IVector4<T> lhs,
            final IVector4<T> rhs) {

        return Vector4.dotProduct(lhs, rhs, lhs.getType());
    }

    /**
     * Calculates the dot product of two {@link IVector4 Vectors}.
     *
     * @param <R>
     *            the {@link Number} type of the dot product.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the desired return type.
     * @return the dot product.
     */
    public static <R extends Number> R dotProduct(final IVector4<?> lhs,
            final IVector4<?> rhs, final Class<? extends R> returnType) {

        if ((lhs == null) || (rhs == null)) {

            throw new IllegalArgumentException(
                    "Cannot calculate dot product without two vectors");
        }

        final Number xProduct = ArithmeticOperations.multiply(lhs.getX(),
                rhs.getX());
        final Number yProduct = ArithmeticOperations.multiply(lhs.getY(),
                rhs.getY());
        final Number zProduct = ArithmeticOperations.multiply(lhs.getZ(),
                rhs.getZ());

        return ArithmeticOperations.sum(returnType, xProduct, yProduct,
                zProduct);
    }


    @Override
    public IVector4<T> crossProduct(final IVector4<T> rhs) {

        return Vector4.crossProduct(this, rhs, this.getType());
    }


    /**
     * Calculates the cross product of the two vectors.
     *
     * @param <T>
     *            the {@link Number} type of the input {@link IVector4 Vectors}.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the cross product (a vector orthogonal to both vectors provided).
     */
    public static <T extends Number> IVector4<T> crossProduct(
            final IVector4<T> lhs, final IVector4<T> rhs) {

        if ((lhs == null) || (rhs == null)) {

            throw new IllegalArgumentException(
                    "Cannot calculate cross product without two vectors");
        }

        return Vector4.crossProduct(lhs, rhs, lhs.getType());
    }


    /**
     * Calculates the cross product of the two vectors.
     *
     * @param <R>
     *            the {@link Number} type of the cross product.
     *
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the desired return type.
     * @return the cross product (a vector orthogonal to both vectors provided).
     */
    public static <R extends Number> IVector4<R> crossProduct(
            final IVector4<?> lhs, final IVector4<?> rhs,
            final Class<? extends R> returnType) {

        if ((lhs == null) || (rhs == null)) {

            throw new IllegalArgumentException(
                    "Cannot calculate cross product without two vectors");
        }

        final Number yz = ArithmeticOperations.multiply(lhs.getY(), rhs.getZ());
        final Number zy = ArithmeticOperations.multiply(lhs.getZ(), rhs.getY());

        final Number zx = ArithmeticOperations.multiply(lhs.getZ(), rhs.getX());
        final Number xz = ArithmeticOperations.multiply(lhs.getX(), rhs.getZ());

        final Number xy = ArithmeticOperations.multiply(lhs.getX(), rhs.getY());
        final Number yx = ArithmeticOperations.multiply(lhs.getY(), rhs.getX());

        final Number xValue = ArithmeticOperations.subtract(yz, zy);
        final Number yValue = ArithmeticOperations.subtract(zx, xz);
        final Number zValue = ArithmeticOperations.subtract(xy, yx);

        return new Vector4<>(xValue, yValue, zValue, returnType);
    }
}
