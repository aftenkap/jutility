package org.jutility.math.geometry;


import com.fasterxml.jackson.annotation.JsonSubTypes;


/**
 * The {@link IScale} interface provides a contract for classes implementing
 * scaling in three dimensions.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the scale.
 * 
 */
@JsonSubTypes({ @JsonSubTypes.Type(value = Scale.class, name = "Scale") })
public interface IScale<T extends Number> {

    /**
     * Returns the type of this scale.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();

    /**
     * Returns the scale factor for the x-dimension.
     * 
     * @return the scale factor.
     */
    public abstract T getScaleFactorX();


    /**
     * Returns the scale factor for the y-dimension.
     * 
     * @return the scale factor.
     */
    public abstract T getScaleFactorY();


    /**
     * Returns the scale factor for the z-dimension.
     * 
     * @return the scale factor.
     */
    public abstract T getScaleFactorZ();
}
