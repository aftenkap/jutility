package org.jutility.math.geometry;


import org.jutility.math.vectorAlgebra.IPoint4;


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