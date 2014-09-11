package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlType;

import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Vector4;


/**
 * The {@link Rotationd} class provides a convenience implementation of the
 * {@link IRotation} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Rotationd")
public class Rotationd
        extends Rotation<Double>
        implements IRotation<Double> {


    /**
     * Constructs a new instance of the {@link Rotationd} class that does not
     * perform any rotation.
     */
    public Rotationd() {

        this(Vector4.NULL_VECTOR(Double.class), 0);
    }


    /**
     * Constructs a new instance of the {@link Rotationd} class with the
     * provided parameters.
     * 
     * @param x
     *            the x component of the rotation axis
     * @param y
     *            the y component of the rotation axis
     * @param z
     *            the z component of the rotation axis
     * 
     * @param rotationAngle
     *            the rotation angle.
     */
    public Rotationd(final double x, final double y, final double z,
            final double rotationAngle) {

        this(new Vector4<Double>(x, y, z, Double.class), rotationAngle);
    }


    /**
     * Constructs a new instance of the {@link Rotationd} class with the
     * provided parameters. The rotation axis provided is normalized to avoid
     * distorting of objects during rotation.
     * 
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     */
    public Rotationd(final IVector4<Double> rotationAxis,
            final double rotationAngle) {

        super(rotationAxis, rotationAngle, Double.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param rotationToCopy
     *            the rotation to copy.
     */
    public Rotationd(final IRotation<Double> rotationToCopy) {

        super(rotationToCopy);
    }
}
