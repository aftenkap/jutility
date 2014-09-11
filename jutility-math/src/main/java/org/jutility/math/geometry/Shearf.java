package org.jutility.math.geometry;


/**
 * The {@link Shearf} class provides a convenience implementation of the
 * {@link IShear} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class Shearf
        extends Shear<Float>
        implements IShear<Float> {


    /**
     * Creates a new instance of the {@link Shearf} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Shearf() {

        super();
    }

    /**
     * Creates a new instance of the {@link Shearf} class with the provided
     * parameters.
     * 
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearBy
     *            the shear by instructions.
     */
    public Shearf(final float shearCoefficient, final ShearBy shearBy) {

        super(shearCoefficient, shearBy, Float.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param shearToCopy
     *            the {@link IShear shear} to copy.
     */
    public Shearf(final IShear<Float> shearToCopy) {

        super(shearToCopy);
    }
}