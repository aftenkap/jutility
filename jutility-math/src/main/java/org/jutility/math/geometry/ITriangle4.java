package org.jutility.math.geometry;


import org.jutility.math.vectorAlgebra.IPoint4;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@link ITriangle4} interface provides a contract for classes implementing
 * a triangle.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the triangle.
 * 
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
// @JsonSubTypes({ @JsonSubTypes.Type(value = Triangle4.class,
// name = "Triangle4") })
public interface ITriangle4<T extends Number> {

    /**
     * Returns the type of the triangle.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();

    /**
     * Returns the first point.
     * 
     * @return the first point.
     */
    public abstract IPoint4<T> getFirstPoint();


    /**
     * Returns the second point.
     * 
     * @return the second point.
     */
    public abstract IPoint4<T> getSecondPoint();


    /**
     * Returns the third point.
     * 
     * @return the third point.
     */
    public abstract IPoint4<T> getThirdPoint();

}