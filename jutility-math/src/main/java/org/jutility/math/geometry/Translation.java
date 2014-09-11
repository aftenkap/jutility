package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.tuple.Tuple3;
import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;


/**
 * The {@link Translation} class provides a reference implementation of the
 * {@link ITranslation} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of this translation.
 * 
 */
@XmlRootElement(name = "Translation")
@XmlType(name = "Translation")
public class Translation<T extends Number>
        extends Tuple3<T>
        implements ITranslation<T> {


    @Override
    public T getXTranslation() {

        return super.getX();
    }


    @Override
    public T getYTranslation() {

        return super.getY();
    }


    @Override
    public T getZTranslation() {

        return super.getZ();
    }


    /**
     * Constructs a new instance of the {@link Translation} class.
     * (Serialization Constructor)
     */
    @SuppressWarnings("unused")
    private Translation() {

        this(null, null, null, null, true);
    }

    /**
     * Constructs a new instance of the {@link Translation} class with the
     * provided type that represents no translation.
     * 
     * @param type
     *            the type of this translation.
     */
    public Translation(Class<? extends T> type) {

        this(0, 0, 0, type);
    }


    /**
     * Constructs a new instance of the {@link Translation} class with the
     * provided type and parameters.
     * 
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @param type
     *            the type of this translation.
     */
    public Translation(Number xTranslation, Number yTranslation,
            Number zTranslation, Class<? extends T> type) {

        this(xTranslation, yTranslation, zTranslation, type, false);
    }

    /**
     * Constructs a new instance of the {@link Translation} class with the
     * provided type and parameters.
     * 
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     * @param type
     *            the type of this translation.
     * @param serialization
     *            whether or not this constructor is used during serialization.
     */
    public Translation(final Number xTranslation, final Number yTranslation,
            final Number zTranslation, final Class<? extends T> type,
            final boolean serialization) {

        super(Translation.cast(xTranslation, type, serialization), Translation
                .cast(yTranslation, type, serialization), Translation.cast(
                zTranslation, type, serialization), type, serialization);
    }


    /**
     * Copy Constructor.
     * 
     * @param translationToCopy
     *            the translation to copy.
     */
    public Translation(final ITranslation<T> translationToCopy) {

        this(translationToCopy, translationToCopy.getType());
    }

    /**
     * Copy Constructor.
     * 
     * @param translationToCopy
     *            the translation to copy.
     * @param type
     *            the desired return type.
     */
    public Translation(final ITranslation<? extends Number> translationToCopy,
            final Class<? extends T> type) {

        this(translationToCopy.getXTranslation(), translationToCopy
                .getYTranslation(), translationToCopy.getZTranslation(), type);
    }


    @Override
    public String toString() {

        return "Tx: " + this.getXTranslation() + ", Ty: "
                + this.getYTranslation() + ", Tz: " + this.getZTranslation();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof ITranslation<?>) {
            ITranslation<?> other = (ITranslation<?>) obj;

            boolean sameXTranslation = NumberComparator.equals(
                    this.getXTranslation(), other.getXTranslation());
            boolean sameYTranslation = NumberComparator.equals(
                    this.getYTranslation(), other.getYTranslation());
            boolean sameZTranslation = NumberComparator.equals(
                    this.getZTranslation(), other.getZTranslation());

            return sameXTranslation && sameYTranslation && sameZTranslation;
        }

        return false;
    }

    private static <S extends Number> S cast(Number number,
            Class<? extends S> type, boolean serialization) {

        if (number == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a translation with a null component!");
        }
        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a translation without a type!");
        }

        if (number != null && type != null) {
            return NumberUtils.cast(number, type);
        }
        else {
            return null;
        }
    }
}
