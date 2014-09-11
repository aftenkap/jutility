package org.jutility.math.geometry;


/**
 * The {@link Sheard} class provides a convenience implementation of the
 * {@link IShear} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class Sheard
        extends Shear<Double>
        implements IShear<Double> {


    /**
     * Creates a new instance of the {@link Sheard} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Sheard() {

        super();
    }

    /**
     * Creates a new instance of the {@link Sheard} class with the provided
     * parameters.
     * 
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearBy
     *            the shear by instructions.
     */
    public Sheard(final double shearCoefficient, final ShearBy shearBy) {

        super(shearCoefficient, shearBy, Double.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param shearToCopy
     *            the {@link IShear shear} to copy.
     */
    public Sheard(final IShear<Double> shearToCopy) {

        super(shearToCopy);
    }
}
