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
 * The {@code Translation} class provides a reference implementation of the
 * {@link ITranslation} interface.
 *
 * @param <T>
 *            the {@link Number} type of this translation.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Translation")
@XmlType(name = "Translation")
public class Translation<T extends Number>
        extends Tuple3<T>
        implements ITranslation<T> {


    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -8787334514178129836L;


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
     * Constructs a new instance of the {@code Translation} class.
     * (Serialization Constructor)
     */
    @SuppressWarnings("unused")
    private Translation() {

        this(null, null, null, null, true);
    }

    /**
     * Constructs a new instance of the {@code Translation} class with the
     * provided type that represents no translation.
     *
     * @param type
     *            the type of this translation.
     */
    public Translation(final Class<? extends T> type) {

        this(0, 0, 0, type);
    }


    /**
     * Constructs a new instance of the {@code Translation} class with the
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
    public Translation(final Number xTranslation, final Number yTranslation,
            final Number zTranslation, final Class<? extends T> type) {

        this(xTranslation, yTranslation, zTranslation, type, false);
    }

    /**
     * Constructs a new instance of the {@code Translation} class with the
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
    public boolean equals(final Object obj) {

        if ((obj != null) && (obj instanceof ITranslation<?>)) {
            final ITranslation<?> other = (ITranslation<?>) obj;

            final boolean sameXTranslation = NumberComparator.equals(
                    this.getXTranslation(), other.getXTranslation());
            final boolean sameYTranslation = NumberComparator.equals(
                    this.getYTranslation(), other.getYTranslation());
            final boolean sameZTranslation = NumberComparator.equals(
                    this.getZTranslation(), other.getZTranslation());

            return sameXTranslation && sameYTranslation && sameZTranslation;
        }

        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;

        hash += ArithmeticOperations.multiply(this.getXTranslation(), 11,
                Integer.class);
        hash += ArithmeticOperations.multiply(this.getYTranslation(), 13,
                Integer.class);
        hash += ArithmeticOperations.multiply(this.getZTranslation(), 17,
                Integer.class);

        return hash;
    }


    private static <S extends Number> S cast(final Number number,
            final Class<? extends S> type, final boolean serialization) {

        if ((number == null) && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a translation with a null component!");
        }
        if ((type == null) && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a translation without a type!");
        }

        if ((number != null) && (type != null)) {
            return NumberUtils.cast(number, type);
        }
        else {
            return null;
        }
    }
}
