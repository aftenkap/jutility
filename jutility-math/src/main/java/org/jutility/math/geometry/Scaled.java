package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Scaled} class provides a convenience implementation of the
 * {@link IScale} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Scaled")
public class Scaled
        extends Scale<Double>
        implements IScale<Double> {


    /**
     * Constructs a new instance of the {@link Scaled} class that represents no
     * scaling.
     */
    public Scaled() {

        this(1, 1, 1);
    }

    /**
     * Constructs a new instance of the {@link Scaled} class with the provided
     * type and parameters.
     * 
     * @param scaleFactorX
     *            the x scale factor.
     * @param scaleFactorY
     *            the y scale factor.
     * @param scaleFactorZ
     *            the z scale factor.
     */
    public Scaled(final Number scaleFactorX, final Number scaleFactorY,
            final Number scaleFactorZ) {

        super(scaleFactorX, scaleFactorY, scaleFactorZ, Double.class);
    }

    /**
     * Copy constructor.
     * 
     * @param scaleToCopy
     *            the {@link IScale scale} to copy.
     */
    public Scaled(final IScale<Double> scaleToCopy) {

        super(scaleToCopy);
    }
}
