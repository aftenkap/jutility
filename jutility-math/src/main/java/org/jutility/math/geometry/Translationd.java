package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Translationd} class is a convenience implementation of the
 * {@link ITranslation} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Translationd")
public class Translationd
        extends Translation<Double>
        implements ITranslation<Double> {



    /**
     * Constructs a new instance of the {@link Translationd} class that
     * represents no translation.
     */
    public Translationd() {

        this(0, 0, 0);
    }


    /**
     * Constructs a new instance of the {@link Translationd} class with the
     * provided parameters.
     * 
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     */
    public Translationd(double xTranslation, double yTranslation,
            double zTranslation) {

        super(xTranslation, yTranslation, zTranslation, Double.class);
    }

    /**
     * Copy Constructor.
     * 
     * @param translationToCopy
     *            the tuple to copy.
     */
    public Translationd(final ITranslation<Double> translationToCopy) {

        super(translationToCopy);
    }
}
