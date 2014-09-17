package org.jutility.math.geometry;


import org.jutility.math.vectorAlgebra.IPoint2;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@link ILine2} interface provides a contract for classes implementing
 * lines in two-dimensional space based on two-dimensional {@link IPoint2
 * Points}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the line.
 * 
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Line2.class, name = "Line2") })
public interface ILine2<T extends Number> {

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
    public abstract IPoint2<T> getSource();


    /**
     * Returns the sink of this line.
     * 
     * @return the sink
     */
    public abstract IPoint2<T> getSink();

}