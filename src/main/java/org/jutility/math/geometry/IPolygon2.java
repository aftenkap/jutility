/**
 * 
 */
package org.jutility.math.geometry;


import java.util.List;

import org.jutility.math.vectorAlgebra.IPoint2;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 * @param <T>
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Polygon2.class, name = "Polygon2") })
public interface IPolygon2<T extends Number> {

    /**
     * Returns the type of the rectangle.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();


    /**
     * Returns the {@link IPoint2 Points} of this {@link IPolygon2 Polygon}.
     * 
     * @return the {@link IPoint2 Points} of this {@link IPolygon2 Polygon}.
     */
    public abstract List<IPoint2<T>> getPoints();

    /**
     * Adds a {@link IPoint2 Point} to the {@link IPoint2 Points} of this
     * {@link IPolygon2 Polygon}.
     * 
     * @param point
     *            the {@link IPoint2 Point} to add.
     * @return whether or not the collection has been changed by this operation.
     */
    public abstract boolean addPoint(IPoint2<? extends Number> point);

    /**
     * Removes a {@link IPoint2 Point} from the {@link IPoint2 Points} of this
     * {@link IPolygon2 Polygon}.
     * 
     * @param point
     *            the {@link IPoint2 Point} to remove.
     * @return whether or not the collection has been changed by this operation.
     */
    public abstract boolean removePoint(IPoint2<? extends Number> point);

    /**
     * Clears the {@link IPoint2 Points} of this {@link IPolygon2 Polygon}.
     */
    public abstract void clearPoints();

}
