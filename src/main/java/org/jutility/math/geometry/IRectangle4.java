package org.jutility.math.geometry;


import org.jutility.math.vectorAlgebra.IPoint4;


/**
 * The {@link IRectangle4} interface provides a contract for classes implementing
 * rectangles in three-dimensional space based on {@link IPoint4 Points} in
 * homogeneous representation.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the rectangle.
 * 
 */
public interface IRectangle4<T extends Number> {

    /**
     * Returns the type of the rectangle.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();


    /**
     * Returns the top-left corner of this rectangle.
     * 
     * @return the top-left corner.
     */
    public abstract IPoint4<T> getTopLeftCorner();

    /**
     * Returns the bottom-left corner of this rectangle.
     * 
     * @return the bottom-left corner.
     */
    public abstract IPoint4<T> getBottomLeftCorner();

    /**
     * Returns the top-right corner of this rectangle.
     * 
     * @return the top-right corner.
     */
    public abstract IPoint4<T> getTopRightCorner();

    /**
     * Returns the bottom-right corner of this rectangle.
     * 
     * @return the bottom-right corner.
     */
    public abstract IPoint4<T> getBottomRightCorner();



}