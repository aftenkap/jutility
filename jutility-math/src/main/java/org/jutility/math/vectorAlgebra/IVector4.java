package org.jutility.math.vectorAlgebra;


/**
 * The generic {@link IVector4} interface provides a contract for all classes
 * implementing three-dimensional vectors in homogeneous coordinate
 * representation.
 * <p/>
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the point.
 */
public interface IVector4<T extends Number>
        extends ITuple4<T> {

    /**
     * Returns the length of the vector (its Euclidean norm).
     * 
     * @return the length of the vector.
     */
    public abstract T length();

    /**
     * Returns the normalized version of this vector.
     * 
     * @return the normalized vector.
     */
    public abstract IVector4<T> normalizedVector();

    /**
     * Calculates the dot product of the vector with the provided vector.
     * 
     * @param rhs
     *            the provided vector.
     * @return the dot product.
     */
    public abstract T dotProduct(IVector4<T> rhs);


    /**
     * Calculates the cross product of the vectors with the provided vector.
     * 
     * @param rhs
     *            the provided vector.
     * @return the cross product (a vector orthogonal to both vectors).
     */
    public abstract IVector4<T> crossProduct(IVector4<T> rhs);
}