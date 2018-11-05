package org.jutility.common.datatype.tuple;


// @formatter:off
/*
 * #%L
 * jutility-common
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
// @formatter:on

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * The generic {@code Tuple2} class provides a reference base implementation of
 * the {@link ITuple2} interface.
 *
 * @param <T>
 *            the type of the tuple.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Tuple2")
@XmlType(name = "Tuple2", propOrder = { "x", "y" })
public class Tuple2<T>
        extends TupleBase<T>
        implements ITuple2<T> {


    /**
     * Serial Version UID.
     */
    private static final long   serialVersionUID = 2982101384146007968L;

    private static final Logger LOG              = LoggerFactory
                                                         .getLogger(Tuple2.class);


    @Override
    @XmlElement(name = "X")
    public T getX() {

        return super.get(0);
    }

    /**
     * Setter for the x component (Serialization).
     *
     * @param value
     *            the new value.
     */
    @SuppressWarnings("unused")
    private void setX(final T value) {

        if (super.getComponents() != null) {

            if (super.getComponents().isEmpty()) {

                super.getComponents().add(value);
            }
            else {

                super.getComponents().set(0, value);
            }
        }
        else {

            Tuple2.LOG.error("TupleBase.getComponents() returns null value!");
            throw new IllegalStateException(
                    "TupleBase.getComponents() returns null value!");
        }
    }


    @Override
    @XmlElement(name = "Y")
    public T getY() {

        return super.get(1);
    }

    /**
     * Setter for the y component (Serialization).
     *
     * @param value
     *            the new value.
     */
    @SuppressWarnings("unused")
    private void setY(final T value) {

        if (super.getComponents() != null) {

            if (super.getComponents().size() == 1) {

                super.getComponents().add(value);
            }
            else if (super.getComponents().size() > 1) {

                super.getComponents().set(1, value);
            }
            else {

                Tuple2.LOG.error("TupleBase has "
                        + super.getComponents().size()
                        + " components! Should be >= 1.");
                throw new IllegalStateException("TupleBase has "
                        + super.getComponents().size()
                        + " components! Should be >= 1.");
            }
        }
        else {

            Tuple2.LOG.error("TupleBase.getComponents() returns null value!");
            throw new IllegalStateException(
                    "TupleBase.getComponents() returns null value!");
        }
    }


    /**
     * Creates a new instance of the {@code Tuple2} class. (Serialization
     * Constructor)
     */
    protected Tuple2() {

        super();
    }

    /**
     * Creates a new instance of the {@code Tuple2} class with the provided type
     * and values.
     *
     * @param x
     *            The X component.
     * @param y
     *            The Y component.
     * @param type
     *            The type of this tuple.
     */
    public Tuple2(final T x, final T y, final Class<? extends T> type) {

        this(x, y, type, false);
    }

    /**
     * Creates a new instance of the {@code Tuple2} class with the provided type
     * and values.
     *
     * @param x
     *            The X component.
     * @param y
     *            The Y component.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    @SuppressWarnings("unchecked")
    protected Tuple2(final T x, final T y, final Class<? extends T> type,
            final boolean serialization) {

        super(type, serialization, x, y);

        if ((x == null) && !serialization) {

            Tuple2.LOG
                    .error("Cannot create a two-dimensional tuple without an x "
                            + "component!");
            throw new IllegalArgumentException(
                    "Cannot create a two-dimensional tuple without an x "
                            + "component!");
        }
        if ((y == null) && !serialization) {

            Tuple2.LOG
                    .error("Cannot create a two-dimensional tuple without an y "
                            + "component!");
            throw new IllegalArgumentException(
                    "Cannot create a two-dimensional tuple without a y "
                            + "component!");
        }
    }


    /**
     * Copy Constructor.
     *
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public Tuple2(final ITuple2<T> tupleToCopy) {

        this(tupleToCopy, tupleToCopy.getType());
    }

    /**
     * Copy Constructor.
     *
     * @param tupleToCopy
     *            the tuple to copy.
     * @param type
     *            the desired return type of the tuple to copy.
     */
    public Tuple2(final ITuple2<T> tupleToCopy, final Class<? extends T> type) {

        this(tupleToCopy.getX(), tupleToCopy.getY(), type, false);
    }
}
