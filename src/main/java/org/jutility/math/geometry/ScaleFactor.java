package org.jutility.math.geometry;


//@formatter:off
/*
 * #%L
 * jutility-math
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
//@formatter:on


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.tuple.Tuple3;
import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.math.arithmetics.ArithmeticOperations;


/**
 * The {@code ScaleFactor} class provides a reference implementation of the
 * {@link IScaleFactor} interface.
 *
 * @param <T>
 *            the {@link Number} type of the {@code ScaleFactor}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "ScaleFactor")
@XmlType(name = "ScaleFactor")
public class ScaleFactor<T extends Number>
        extends Tuple3<T>
        implements IScaleFactor<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -3047493958881622485L;


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
     * Constructs a new instance of the {@code ScaleFactor} class.
     * (Serialization Constructor)
     */
    @SuppressWarnings("unused")
    private ScaleFactor() {

        this(null, null, null, null, true);
    }

    /**
     * Constructs a new instance of the {@code ScaleFactor} class that
     * represents no scaling.
     *
     * @param type
     *            the type of this scale.
     */
    public ScaleFactor(final Class<? extends T> type) {

        this(1, 1, 1, type);
    }

    /**
     * Constructs a new instance of the {@code ScaleFactor} class with the
     * provided type and parameters.
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
    public ScaleFactor(final Number scaleFactorX, final Number scaleFactorY,
            final Number scaleFactorZ, final Class<? extends T> type) {

        this(scaleFactorX, scaleFactorY, scaleFactorZ, type, false);
    }

    /**
     * Constructs a new instance of the {@code ScaleFactor} class with the
     * provided type and parameters.
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
    private ScaleFactor(final Number scaleFactorX, final Number scaleFactorY,
            final Number scaleFactorZ, final Class<? extends T> type,
            final boolean serialization) {

        super(ScaleFactor.cast(scaleFactorX, type, serialization), ScaleFactor
                .cast(scaleFactorY, type, serialization), ScaleFactor.cast(
                scaleFactorZ, type, serialization), type, serialization);

    }

    /**
     * Copy constructor.
     *
     * @param scaleToCopy
     *            the scale to copy
     */
    public ScaleFactor(final IScaleFactor<? extends T> scaleToCopy) {

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
    public ScaleFactor(final IScaleFactor<? extends Number> scaleToCopy,
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
    public boolean equals(final Object obj) {

        if ((obj != null) && (obj instanceof ScaleFactor<?>)) {

            final ScaleFactor<?> other = (ScaleFactor<?>) obj;

            final boolean sameX = NumberComparator.equals(
                    this.getScaleFactorX(), other.getScaleFactorX());
            final boolean sameY = NumberComparator.equals(
                    this.getScaleFactorY(), other.getScaleFactorY());
            final boolean sameZ = NumberComparator.equals(
                    this.getScaleFactorZ(), other.getScaleFactorZ());

            return sameX && sameY && sameZ;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 13;

        hash += ArithmeticOperations.multiply(this.getScaleFactorX(), 17,
                Integer.class);
        hash += ArithmeticOperations.multiply(this.getScaleFactorY(), 23,
                Integer.class);
        hash += ArithmeticOperations.multiply(this.getScaleFactorZ(), 31,
                Integer.class);

        return hash;
    }


    private static <S extends Number> S cast(final Number number,
            final Class<? extends S> type, final boolean serialization) {

        if ((number == null) && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a scale with a null component!");
        }
        if ((type == null) && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a scale without a type!");
        }

        if ((number != null) && (type != null)) {

            return NumberUtils.cast(number, type);
        }
        else {

            return null;
        }
    }
}
