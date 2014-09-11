package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.math.arithmetics.ArithmeticOperations;


/**
 * The generic {link Vector4f} class provides a reference implementation of the
 * {@link IVector4} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of this vector.
 */
@XmlRootElement(name = "Vector4")
@XmlType(name = "Vector4")
public class Vector4<T extends Number>
        extends Tuple4<T>
        implements IVector4<T> {

    /**
     * Creates the i unit vector for the provided type.
     * 
     * @param type
     *            the desired type of the vector.
     * @return the i unit vector.
     */
    public static final <T extends Number> IVector4<T> I_UNIT_VECTOR(
            Class<? extends T> type) {

        return new Vector4<T>(1, 0, 0, type);
    }


    /**
     * Creates the j unit vector for the provided type.
     * 
     * @param type
     *            the desired type of the vector.
     * @return the j unit vector.
     */
    public static final <T extends Number> IVector4<T> J_UNIT_VECTOR(
            Class<? extends T> type) {

        return new Vector4<T>(0f, 1f, 0f, type);
    }


    /**
     * Creates the k unit vector for the provided type.
     * 
     * @param type
     *            the desired type of the vector.
     * @return the k unit vector.
     */
    public static final <T extends Number> IVector4<T> K_UNIT_VECTOR(
            Class<? extends T> type) {

        return new Vector4<T>(0f, 0f, 1f, type);
    }


    /**
     * Creates the null vector for the provided type.
     * 
     * @param type
     *            the desired type of the vector.
     * @return the null vector.
     */
    public static final <T extends Number> IVector4<T> NULL_VECTOR(
            Class<? extends T> type) {

        return new Vector4<T>(0f, 0f, 0f, type);
    }

    /**
     * Creates a new instance of the {@link Vector4} class. (Serialization
     * Constructor)
     */
    protected Vector4() {

        super();
    }


    /**
     * Creates a new instance of the {@link Vector4} class with the provided
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
            Class<? extends T> type) {

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
            Class<? extends T> returnType) {

        super(tuple, returnType);

        if (!tuple.isVector()) {
            throw new IllegalArgumentException(
                    "Provided parameter is not a vector!");
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.vectorAlgebra.IVector4#length()
     */
    @Override
    public T length() {

        Class<? extends T> type = this.getType();

        T xSquared = ArithmeticOperations.multiply(this.getX(), this.getX(),
                type);
        T ySquared = ArithmeticOperations.multiply(this.getY(), this.getY(),
                type);
        T zSquared = ArithmeticOperations.multiply(this.getZ(), this.getZ(),
                type);
        T wSquared = ArithmeticOperations.multiply(this.getW(), this.getW(),
                type);

        T sum = ArithmeticOperations.add(xSquared, ySquared, type);
        sum = ArithmeticOperations.add(sum, zSquared, type);
        sum = ArithmeticOperations.add(sum, wSquared, type);

        Double squareRoot = Math.sqrt(sum.doubleValue());

        return NumberUtils.cast(squareRoot, type);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.vectorAlgebra.IVector4#normalizedVector()
     */
    @Override
    public Vector4<T> normalizedVector() {

        T length = this.length();
        Class<? extends T> type = this.getType();

        if (!NumberComparator.equals(length, 0)) {

            T normalizedX = ArithmeticOperations.divide(this.getX(), length,
                    type);
            T normalizedY = ArithmeticOperations.divide(this.getY(), length,
                    type);
            T normalizedZ = ArithmeticOperations.divide(this.getZ(), length,
                    type);

            return new Vector4<T>(normalizedX, normalizedY, normalizedZ, type);
        }
        else {
            return new Vector4<T>(this);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.math.vectorAlgebra.IVector4#dotProduct(org.jutility.math
     * .vectorAlgebra .IVector4)
     */
    @Override
    public T dotProduct(IVector4<T> rhs) {

        return Vector4.dotProduct(this, rhs, this.getType());
    }

    /**
     * Calculates the dot product of two vectors.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the dot product.
     */
    public static <T extends Number, S extends Number> Number dotProduct(
            IVector4<T> lhs, IVector4<S> rhs) {

        if (lhs == null || rhs == null) {
            throw new IllegalArgumentException(
                    "Cannot calculate dot product without two vectors");
        }


        Number xSquared = ArithmeticOperations.multiply(lhs.getX(), rhs.getX());
        Number ySquared = ArithmeticOperations.multiply(lhs.getY(), rhs.getY());
        Number zSquared = ArithmeticOperations.multiply(lhs.getZ(), rhs.getZ());

        Number sum = ArithmeticOperations.add(xSquared, ySquared);
        sum = ArithmeticOperations.add(sum, zSquared);

        return sum;
    }

    /**
     * Calculates the dot product of two vectors.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the desired return type.
     * @return the dot product.
     */
    public static <T extends Number, S extends Number, R extends Number> R dotProduct(
            IVector4<T> lhs, IVector4<S> rhs, Class<? extends R> returnType) {

        if (lhs == null || rhs == null) {
            throw new IllegalArgumentException(
                    "Cannot calculate dot product without two vectors");
        }


        Number xSquared = ArithmeticOperations.multiply(lhs.getX(), rhs.getX());
        Number ySquared = ArithmeticOperations.multiply(lhs.getY(), rhs.getY());
        Number zSquared = ArithmeticOperations.multiply(lhs.getZ(), rhs.getZ());

        R sum = ArithmeticOperations.add(xSquared, ySquared, returnType);
        sum = ArithmeticOperations.add(sum, zSquared, returnType);

        return sum;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.math.vectorAlgebra.IVector4#crossProduct(org.jutility.math
     * .vectorAlgebra .IVector4)
     */
    @Override
    public IVector4<T> crossProduct(IVector4<T> rhs) {

        return Vector4.crossProduct(this, rhs, this.getType());
    }


    /**
     * Calculates the cross product of the two vectors.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the cross product (a vector orthogonal to both vectors provided).
     */
    public static <T extends Number, S extends Number> IVector4<? extends Number> crossProduct(
            IVector4<T> lhs, IVector4<S> rhs) {

        if (lhs == null || rhs == null) {
            throw new IllegalArgumentException(
                    "Cannot calculate cross product without two vectors");
        }

        Number yz = ArithmeticOperations.multiply(lhs.getY(), rhs.getZ());
        Number zy = ArithmeticOperations.multiply(lhs.getZ(), rhs.getY());

        Number zx = ArithmeticOperations.multiply(lhs.getZ(), rhs.getX());
        Number xz = ArithmeticOperations.multiply(lhs.getX(), rhs.getZ());

        Number xy = ArithmeticOperations.multiply(lhs.getX(), rhs.getY());
        Number yx = ArithmeticOperations.multiply(lhs.getY(), rhs.getX());

        Number xValue = ArithmeticOperations.subtract(yz, zy);
        Number yValue = ArithmeticOperations.subtract(zx, xz);
        Number zValue = ArithmeticOperations.subtract(xy, yx);

        return new Vector4<Number>(xValue, yValue, zValue, xValue.getClass());
    }

    /**
     * Calculates the cross product of the two vectors.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @param returnType
     *            the desired return type.
     * @return the cross product (a vector orthogonal to both vectors provided).
     */
    public static <T extends Number, S extends Number, R extends Number> IVector4<R> crossProduct(
            IVector4<T> lhs, IVector4<S> rhs, Class<? extends R> returnType) {

        if (lhs == null || rhs == null) {
            throw new IllegalArgumentException(
                    "Cannot calculate cross product without two vectors");
        }

        Number yz = ArithmeticOperations.multiply(lhs.getY(), rhs.getZ());
        Number zy = ArithmeticOperations.multiply(lhs.getZ(), rhs.getY());

        Number zx = ArithmeticOperations.multiply(lhs.getZ(), rhs.getX());
        Number xz = ArithmeticOperations.multiply(lhs.getX(), rhs.getZ());

        Number xy = ArithmeticOperations.multiply(lhs.getX(), rhs.getY());
        Number yx = ArithmeticOperations.multiply(lhs.getY(), rhs.getX());

        Number xValue = ArithmeticOperations.subtract(yz, zy);
        Number yValue = ArithmeticOperations.subtract(zx, xz);
        Number zValue = ArithmeticOperations.subtract(xy, yx);

        return new Vector4<R>(xValue, yValue, zValue, returnType);
    }
}
