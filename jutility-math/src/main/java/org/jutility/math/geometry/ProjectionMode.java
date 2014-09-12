package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * The {@link ProjectionMode} enum enumerates over the different possible
 * projection modes.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlEnum
public enum ProjectionMode {
    /**
     * 2D orthographic projection.
     */
    @XmlEnumValue(value = "Orthographic2D")
    ORTHOGRAPHIC_2D,
    /**
     * 3D orthographic projection.
     */
    @XmlEnumValue(value = "Orthographic3D")
    ORTHOGRAPHIC_3D,
    /**
     * 3D perspective projection.
     */
    @XmlEnumValue(value = "Perspective")
    PERSPECTIVE;
}
