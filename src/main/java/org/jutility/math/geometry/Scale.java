package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.tuple.Tuple3;
import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;


/**
 * The {@link Scale} class provides a reference implementation of the
 * {@link IScale} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of this scale.
 */
@XmlRootElement(name = "Scale")
@XmlType(name = "Scale")
public class Scale<T extends Number>
        extends Tuple3<T>
        implements IScale<T> {



    @Override
    public T getScaleFactorX() {

        return super.getX();
    }


    @Override
    public T getScaleFactorY() {

        return super.getY();
    }



    @Override
    public T getScaleFactorZ() {

        return super.getZ();
    }


    /**
     * Constructs a new instance of the {@link Scale} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Scale() {

        this(null, null, null, null, true);
    }

    /**
     * Constructs a new instance of the {@link Scale} class that represents no
     * scaling.
     * 
     * @param type
     *            the type of this scale.
     */
    public Scale(final Class<? extends T> type) {

        this(1, 1, 1, type);
    }

    /**
     * Constructs a new instance of the {@link Scale} class with the provided
     * type and parameters.
     * 
     * @param scaleFactorX
     *            the x scale factor.
     * @param scaleFactorY
     *            the y scale factor.
     * @param scaleFactorZ
     *            the z scale factor.
     * @param type
     *            the type of this scale.
     */
    public Scale(final Number scaleFactorX, final Number scaleFactorY,
            final Number scaleFactorZ, final Class<? extends T> type) {

        this(scaleFactorX, scaleFactorY, scaleFactorZ, type, false);
    }

    /**
     * Constructs a new instance of the {@link Scale} class with the provided
     * type and parameters.
     * 
     * @param scaleFactorX
     *            the x scale factor.
     * @param scaleFactorY
     *            the y scale factor.
     * @param scaleFactorZ
     *            the z scale factor.
     * @param type
     *            the type of this scale.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    private Scale(final Number scaleFactorX, final Number scaleFactorY,
            final Number scaleFactorZ, final Class<? extends T> type,
            final boolean serialization) {

        super(Scale.cast(scaleFactorX, type, serialization), Scale.cast(
                scaleFactorY, type, serialization), Scale.cast(scaleFactorZ,
                type, serialization), type, serialization);

    }

    /**
     * Copy constructor.
     * 
     * @param scaleToCopy
     *            the scale to copy
     */
    public Scale(final IScale<? extends T> scaleToCopy) {

        this(scaleToCopy, scaleToCopy.getType());
    }

    /**
     * Copy constructor.
     * 
     * @param scaleToCopy
     *            the scale to copy
     * @param returnType
     *            the return type.
     */
    public Scale(final IScale<? extends Number> scaleToCopy,
            final Class<? extends T> returnType) {

        this(scaleToCopy.getScaleFactorX(), scaleToCopy.getScaleFactorY(),
                scaleToCopy.getScaleFactorZ(), returnType);
    }

    @Override
    public String toString() {

        return "Sx: " + this.getScaleFactorX() + ", Sy: "
                + this.getScaleFactorY() + ", Sz: " + this.getScaleFactorZ();
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof Scale<?>) {

            Scale<?> other = (Scale<?>) obj;

            boolean sameX = NumberComparator.equals(this.getScaleFactorX(),
                    other.getScaleFactorX());
            boolean sameY = NumberComparator.equals(this.getScaleFactorY(),
                    other.getScaleFactorY());
            boolean sameZ = NumberComparator.equals(this.getScaleFactorZ(),
                    other.getScaleFactorZ());

            return sameX && sameY && sameZ;
        }
        return false;
    }



    private static <S extends Number> S cast(Number number,
            Class<? extends S> type, boolean serialization) {

        if (number == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a scale with a null component!");
        }
        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a scale without a type!");
        }

        if (number != null && type != null) {
            return NumberUtils.cast(number, type);
        }
        else {
            return null;
        }
    }
}
