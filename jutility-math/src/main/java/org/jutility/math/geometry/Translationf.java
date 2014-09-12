package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Translationf} class is a convenience implementation of the
 * {@link ITranslation} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Translationf")
public class Translationf
        extends Translation<Float>
        implements ITranslation<Float> {



    /**
     * Constructs a new instance of the {@link Translationf} class that
     * represents no translation.
     */
    public Translationf() {

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
    public Translationf(float xTranslation, float yTranslation,
            float zTranslation) {

        super(xTranslation, yTranslation, zTranslation, Float.class);
    }

    /**
     * Copy Constructor.
     * 
     * @param translationToCopy
     *            the tuple to copy.
     */
    public Translationf(final ITranslation<Float> translationToCopy) {

        super(translationToCopy);
    }
}
