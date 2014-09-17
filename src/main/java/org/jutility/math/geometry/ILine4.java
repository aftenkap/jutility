package org.jutility.math.geometry;


import org.jutility.math.vectorAlgebra.IPoint4;

import com.fasterxml.jackson.annotation.JsonSubTypes;


/**
 * The {@link ILine4} interface provides a contract for classes implementing
 * lines in three-dimensional space based on {@link IPoint4 Points} in
 * homogeneous representation.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the line.
 * 
 */
@JsonSubTypes({ @JsonSubTypes.Type(value = Line4.class, name = "Line4") })
public interface ILine4<T extends Number> {

    /**
     * Returns the type of the line.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();

    /**
     * Returns the source of this line.
     * 
     * @return the source.
     */
    public abstract IPoint4<T> getSource();


    /**
     * Returns the sink of this line.
     * 
     * @return the sink
     */
    public abstract IPoint4<T> getSink();

}