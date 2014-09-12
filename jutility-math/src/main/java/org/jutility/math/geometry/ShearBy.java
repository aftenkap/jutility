package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * Enumerates the options for shearing.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlEnum
public enum ShearBy {
    /**
     * Shear x by y
     */
    @XmlEnumValue(value = "ShearXbyY")
    X_BY_Y,
    /**
     * Shear x by z
     */
    @XmlEnumValue(value = "ShearXbyZ")
    X_BY_Z,
    /**
     * Shear y by x
     */
    @XmlEnumValue(value = "ShearYbyX")
    Y_BY_X,
    /**
     * Shear y by z
     */
    @XmlEnumValue(value = "ShearYbyZ")
    Y_BY_Z,
    /**
     * Shear z by x
     */
    @XmlEnumValue(value = "ShearZbyX")
    Z_BY_X,
    /**
     * Shear z by y
     */
    @XmlEnumValue(value = "ShearZbyY")
    Z_BY_Y;
}
