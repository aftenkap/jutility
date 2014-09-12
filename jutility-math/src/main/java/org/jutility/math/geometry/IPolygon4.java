/**
 * 
 */
package org.jutility.math.geometry;


import java.util.List;

import org.jutility.math.vectorAlgebra.IPoint4;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 * @param <T>
 */
public interface IPolygon4<T extends Number> {

    /**
     * Returns the type of the rectangle.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();


    /**
     * Returns the {@link IPoint4 Points} of this {@link IPolygon4 Polygon}.
     * 
     * @return the {@link IPoint4 Points} of this {@link IPolygon4 Polygon}.
     */
    public abstract List<IPoint4<T>> getPoints();

    /**
     * Adds a {@link IPoint4 Point} to the {@link IPoint4 Points} of this
     * {@link IPolygon4 Polygon}.
     * 
     * @param point
     *            the {@link IPoint4 Point} to add.
     * @return whether or not the collection has been changed by this operation.
     */
    public abstract boolean addPoint(IPoint4<? extends Number> point);

    /**
     * Removes a {@link IPoint4 Point} from the {@link IPoint4 Points} of this
     * {@link IPolygon4 Polygon}.
     * 
     * @param point
     *            the {@link IPoint4 Point} to remove.
     * @return whether or not the collection has been changed by this operation.
     */
    public abstract boolean removePoint(IPoint4<? extends Number> point);

    /**
     * Clears the {@link IPoint4 Points} of this {@link IPolygon4 Polygon}.
     */
    public abstract void clearPoints();

}
