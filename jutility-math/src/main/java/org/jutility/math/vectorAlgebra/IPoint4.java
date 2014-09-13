package org.jutility.math.vectorAlgebra;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


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
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "tupleType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Point4.class, name = "Point4") })
public interface IPoint4<T extends Number>
        extends ITuple4<T> {

    // Tagging interface.
}
