package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlType;

import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Vector4;


/**
 * The {@link Rotationf} class provides a convenience implementation of the
 * {@link IRotation} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Rotationf")
public class Rotationf
        extends Rotation<Float>
        implements IRotation<Float> {


    /**
     * Constructs a new instance of the {@link Rotationf} class that does not
     * perform any rotation.
     */
    public Rotationf() {

        this(Vector4.NULL_VECTOR(Float.class), 0);
    }


    /**
     * Constructs a new instance of the {@link Rotationf} class with the
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
    public Rotationf(final float x, final float y, final float z,
            final float rotationAngle) {

        this(new Vector4<Float>(x, y, z, Float.class), rotationAngle);
    }


    /**
     * Constructs a new instance of the {@link Rotationf} class with the
     * provided parameters. The rotation axis provided is normalized to avoid
     * distorting of objects during rotation.
     * 
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     */
    public Rotationf(final IVector4<Float> rotationAxis,
            final float rotationAngle) {

        super(rotationAxis, rotationAngle, Float.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param rotationToCopy
     *            the rotation to copy.
     */
    public Rotationf(final IRotation<Float> rotationToCopy) {

        super(rotationToCopy);
    }
}
