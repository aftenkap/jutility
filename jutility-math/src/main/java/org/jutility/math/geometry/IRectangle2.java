package org.jutility.math.geometry;


import org.jutility.math.vectorAlgebra.IPoint2;

import com.fasterxml.jackson.annotation.JsonSubTypes;


/**
 * The {@link IRectangle2} interface provides a contract for classes
 * implementing rectangles in two-dimensional space based on two-dimensional
 * {@link IPoint2 Points}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the rectangle.
 * 
 */
@JsonSubTypes({ @JsonSubTypes.Type(value = Rectangle2.class,
        name = "Rectangle2") })
public interface IRectangle2<T extends Number> {

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
    public abstract IPoint2<T> getTopLeftCorner();

    /**
     * Returns the bottom-left corner of this rectangle.
     * 
     * @return the bottom-left corner.
     */
    public abstract IPoint2<T> getBottomLeftCorner();

    /**
     * Returns the top-right corner of this rectangle.
     * 
     * @return the top-right corner.
     */
    public abstract IPoint2<T> getTopRightCorner();

    /**
     * Returns the bottom-right corner of this rectangle.
     * 
     * @return the bottom-right corner.
     */
    public abstract IPoint2<T> getBottomRightCorner();

}