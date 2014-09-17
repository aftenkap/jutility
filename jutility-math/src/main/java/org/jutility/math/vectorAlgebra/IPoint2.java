package org.jutility.math.vectorAlgebra;


import com.fasterxml.jackson.annotation.JsonSubTypes;


/**
 * The generic {@link IPoint2} interface provides a contract for all classes
 * implementing two-dimensional points.
 * <p/>
 * This is a tagging interface.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the point.
 */
@JsonSubTypes({ @JsonSubTypes.Type(value = Point2.class, name = "Point2") })
public interface IPoint2<T extends Number>
        extends ITuple2<T> {

    // Tagging interface.
}
