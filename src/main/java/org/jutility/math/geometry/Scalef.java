package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Scalef} class provides a convenience implementation of the
 * {@link IScale} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Scalef")
public class Scalef
        extends Scale<Float>
        implements IScale<Float> {


    /**
     * Constructs a new instance of the {@link Scalef} class that represents no
     * scaling.
     */
    public Scalef() {

        this(1, 1, 1);
    }

    /**
     * Constructs a new instance of the {@link Scalef} class with the provided
     * type and parameters.
     * 
     * @param scaleFactorX
     *            the x scale factor.
     * @param scaleFactorY
     *            the y scale factor.
     * @param scaleFactorZ
     *            the z scale factor.
     */
    public Scalef(final Number scaleFactorX, final Number scaleFactorY,
            final Number scaleFactorZ) {

        super(scaleFactorX, scaleFactorY, scaleFactorZ, Float.class);
    }

    /**
     * Copy constructor.
     * 
     * @param scaleToCopy
     *            the {@link IScale scale} to copy.
     */
    public Scalef(final IScale<Float> scaleToCopy) {

        super(scaleToCopy);
    }
}
