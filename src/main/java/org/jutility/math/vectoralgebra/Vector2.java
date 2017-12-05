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
 * The generic {@code Vector2} class provides a reference implementation of the
 * {@link IVector2} interface.
 * 
 * @param <T>
 *            the {@link Number} type of the {@code Vector2}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Vector2")
@XmlType(name = "Vector2")
public class Vector2<T extends Number>
        extends Tuple2<T>
        implements IVector2<T> {


    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -2247141238001624578L;


    /**
     * Creates the i unit vector for the provided type.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IVector2 Vector}.
     * 
     * @param type
     *            the desired type of the vector.
     * @return the i unit vector.
     */
    public static final <T extends Number> IVector2<T> I_UNIT_VECTOR(
            Class<? extends T> type) {

        return new Vector2<>(1, 0, type);
    }


    /**
     * Creates the j unit vector for the provided type.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IVector2 Vector}.
     * 
     * @param type
     *            the desired type of the vector.
     * @return the j unit vector.
     */
    public static final <T extends Number> IVector2<T> J_UNIT_VECTOR(
            Class<? extends T> type) {

        return new Vector2<>(0, 1, type);
    }

    /**
     * Creates the null vector for the provided type.
     * 
     * @param <T>
     *            the {@link Number} type of the {@link IVector2 Vector}.
     * 
     * @param type
     *            the desired type of the vector.
     * @return the null vector.
     */
    public static final <T extends Number> IVector2<T> NULL_VECTOR(
            Class<? extends T> type) {

        return new Vector2<>(0f, 0f, type);
    }

    /**
     * Creates a new instance of the {@code Vector2} class. (Serialization
     * Constructor)
     */
    protected Vector2() {

        super();
    }


    /**
     * Creates a new instance of the {@code Vector2} class with the provided
     * type and values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param type
     *            The type of this tuple.
     */
    public Vector2(final Number x, final Number y, final Class<? extends T> type) {

        super(x, y, type);
    }

    /**
     * Copy constructor.
     * 
     * Creates a new {@code Vector2} from the {@link ITuple2 Tuple} provided.
     * 
     * @param tuple
     *            The {@link ITuple2 Tuple} to be copied.
     * @throws IllegalArgumentException
     *             if the {@link ITuple2 Tuple} provided is not a vector
     */
    public Vector2(final ITuple2<T> tuple) {

        this(tuple, tuple.getType());
    }

    /**
     * Copy constructor.
     * 
     * Creates a new {@code Vector2} from the {@link ITuple2 Tuple} provided.
     * 
     * @param tuple
     *            The {@link ITuple2 Tuple} to be copied.
     * @param returnType
     *            the desired return type.
     * @throws IllegalArgumentException
     *             if the {@link ITuple2 Tuple} provided is not a vector
     */
    public Vector2(final ITuple2<? extends Number> tuple,
            Class<? extends T> returnType) {

        super(tuple, returnType);
        if (!tuple.isVector()) {

            throw new IllegalArgumentException(
                    "Provided parameter is not a vector!");
        }
    }

    @Override
    public boolean isPoint() {

        return false;
    }

    @Override
    public boolean isVector() {

        return true;
    }


    @Override
    public T length() {

        Class<? extends T> type = this.getType();

        T xSquared = ArithmeticOperations.multiply(this.getX(), this.getX(),
                type);
        T ySquared = ArithmeticOperations.multiply(this.getY(), this.getY(),
                type);


        T sum = ArithmeticOperations.add(xSquared, ySquared, type);

        Double squareRoot = Math.sqrt(sum.doubleValue());

        return NumberUtils.cast(squareRoot, type);
    }


    @Override
    public IVector2<T> normalizedVector() {

        T length = this.length();
        Class<? extends T> type = this.getType();

        if (!NumberComparator.equals(length, 0)) {

            T normalizedX = ArithmeticOperations.divide(this.getX(), length,
                    type);
            T normalizedY = ArithmeticOperations.divide(this.getY(), length,
                    type);

            return new Vector2<>(normalizedX, normalizedY, type);
        }
        else {

            return new Vector2<>(this);
        }
    }



    @Override
    public T dotProduct(IVector2<T> rhs) {

        return Vector2.dotProduct(this, rhs);
    }



    @Override
    public IVector2<T> perpendicularVector() {

        return Vector2.perpendicularVector(this);
    }



    /**
     * Calculates the dot product of two {@link IVector2 Vectors}.
     * 
     * @param <T>
     *            the {@link Number} type of the input {@link IVector2 Vectors}.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the dot product.
     */
    public static final <T extends Number> T dotProduct(IVector2<T> lhs,
            IVector2<T> rhs) {

        if (lhs == null || rhs == null) {

            throw new IllegalArgumentException(
                    "Cannot calculate dot product without two vectors");
        }

        return Vector2.dotProduct(lhs, rhs, lhs.getType());
    }

    /**
     * Calculates the dot product of two {@link IVector2 Vectors}.
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
    public static final <R extends Number> R dotProduct(IVector2<?> lhs,
            IVector2<?> rhs, Class<? extends R> returnType) {

        if (lhs == null || rhs == null) {

            throw new IllegalArgumentException(
                    "Cannot calculate dot product without two vectors");
        }


        Number xProduct = ArithmeticOperations.multiply(lhs.getX(), rhs.getX());
        Number yProduct = ArithmeticOperations.multiply(lhs.getY(), rhs.getY());

        return ArithmeticOperations.add(xProduct, yProduct, returnType);
    }


    /**
     * Returns a {@link IVector2 Vector} counterclockwise perpendicular to the
     * provided {@link IVector2 Vector}.
     * 
     * @param <T>
     *            the {@link Number} type of the input {@link IVector2}.
     * 
     * @param value
     *            the {@link IVector2 Vector}.
     * 
     * @return a {@link IVector2 Vector} counterclockwise perpendicular to the
     *         provided {@link IVector2 Vector}.
     */
    public static final <T extends Number> IVector2<T> perpendicularVector(
            final IVector2<T> value) {

        return Vector2.perpendicularVector(value, value.getType());
    }

    /**
     * Returns a {@link IVector2 Vector} counterclockwise perpendicular to the
     * provided {@link IVector2 Vector}.
     * 
     * @param <R>
     *            the {@link Number} type of the perpendicular {@link IVector2}.
     * 
     * @param value
     *            the {@link IVector2 Vector}.
     * @param returnType
     *            the desired return type.
     * 
     * @return a {@link IVector2 Vector} counterclockwise perpendicular to the
     *         provided {@link IVector2 Vector}.
     */
    public static final <R extends Number> IVector2<R> perpendicularVector(
            final IVector2<?> value, final Class<? extends R> returnType) {

        return new Vector2<>(ArithmeticOperations.multiply(-1, value.getY()),
                value.getX(), returnType);
    }
}
