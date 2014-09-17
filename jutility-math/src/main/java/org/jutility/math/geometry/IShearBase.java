package org.jutility.math.geometry;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@link IShearBase} interface provides a contract for classes implementing
 * a shear around a certain component.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the shear.
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Shear.class, name = "Shear") })
public interface IShearBase<T> {

    /**
     * Returns the type of the line.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();

    /**
     * Returns the shear coefficient.
     * 
     * @return the shear coefficient.
     */
    public abstract T getShearCoefficient();


    /**
     * Returns the identifier of the shear
     * 
     * @return the identifier.
     */
    public abstract ShearBy getShearBy();
}
