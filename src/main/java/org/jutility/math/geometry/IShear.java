package org.jutility.math.geometry;

import com.fasterxml.jackson.annotation.JsonSubTypes;


/**
 * The {@link IShear} interface provides a contract for classes implementing a
 * shear around a certain component.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the shear.
 */
@JsonSubTypes({ @JsonSubTypes.Type(value = Shear.class, name = "Shear") })
public interface IShear<T extends Number>
        extends IShearBase<T> {

    // Tagging interface
}
