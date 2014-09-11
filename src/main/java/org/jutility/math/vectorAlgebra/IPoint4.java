package org.jutility.math.vectorAlgebra;


/**
 * The generic {@link IPoint4} interface provides a contract for all classes
 * implementing three-dimensional points in homogeneous coordinate
 * representation.
 * <p/>
 * This is a tagging interface.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the point.
 */
public interface IPoint4<T extends Number>
        extends ITuple4<T> {

    // Tagging interface.
}
