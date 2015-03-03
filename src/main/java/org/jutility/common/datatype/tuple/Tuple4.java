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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



/**
 * The generic {@link Tuple4} class provides a reference base implementation of
 * the {@link ITuple4} interface.
 *
 * @param <T>
 *            the type of the tuple.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonPropertyOrder(value = { "type", "x", "y", "z", "w" })
@XmlRootElement(name = "Tuple4")
@XmlType(name = "Tuple4", propOrder = { "x", "y", "z", "w" })
public class Tuple4<T>
        extends TupleBase<T>
        implements ITuple4<T> {


    private static final Logger LOG = LoggerFactory.getLogger(Tuple4.class);


    @Override
    @JsonProperty(required = true, value = "x", index = 0)
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

            Tuple4.LOG.error("TupleBase.getComponents() returns null value!");
            throw new IllegalStateException(
                    "TupleBase.getComponents() returns null value!");
        }
    }

    @Override
    @JsonProperty(required = true, value = "y", index = 1)
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

                Tuple4.LOG.error("TupleBase has "
                        + super.getComponents().size()
                        + " components! Should be >= 1.");
                throw new IllegalStateException("TupleBase has "
                        + super.getComponents().size()
                        + " components! Should be >= 1.");
            }
        }
        else {

            Tuple4.LOG.error("TupleBase.getComponents() returns null value!");
            throw new IllegalStateException(
                    "TupleBase.getComponents() returns null value!");
        }
    }



    @Override
    @JsonProperty(required = true, value = "z", index = 2)
    @XmlElement(name = "Z")
    public T getZ() {

        return super.get(2);
    }

    /**
     * Setter for the z component (Serialization).
     *
     * @param value
     *            the new value.
     */
    @SuppressWarnings("unused")
    private void setZ(final T value) {

        if (super.getComponents() != null) {

            if (super.getComponents().size() == 2) {

                super.getComponents().add(value);
            }
            else if (super.getComponents().size() > 2) {

                super.getComponents().set(2, value);
            }
            else {

                Tuple4.LOG.error("TupleBase has "
                        + super.getComponents().size()
                        + " components! Should be >= 2.");
                throw new IllegalStateException("TupleBase has "
                        + super.getComponents().size()
                        + " components! Should be >= 2.");
            }
        }
        else {

            Tuple4.LOG.error("TupleBase.getComponents() returns null value!");
            throw new IllegalStateException(
                    "TupleBase.getComponents() returns null value!");
        }
    }

    @Override
    @JsonProperty(required = true, value = "w", index = 3)
    @XmlElement(name = "W")
    public T getW() {

        return super.get(3);
    }

    /**
     * Setter for the w component (Serialization).
     *
     * @param value
     *            the new value.
     */
    @SuppressWarnings("unused")
    private void setW(final T value) {

        if (super.getComponents() != null) {

            if (super.getComponents().size() == 3) {

                super.getComponents().add(value);
            }
            else if (super.getComponents().size() > 3) {

                super.getComponents().set(3, value);
            }
            else {

                Tuple4.LOG.error("TupleBase has "
                        + super.getComponents().size()
                        + " components! Should be >= 3.");
                throw new IllegalStateException("TupleBase has "
                        + super.getComponents().size()
                        + " components! Should be >= 3.");
            }
        }
        else {

            Tuple4.LOG.error("TupleBase.getComponents() returns null value!");
            throw new IllegalStateException(
                    "TupleBase.getComponents() returns null value!");
        }
    }

    /**
     * Creates a new instance of the {@link Tuple4} class. (Serialization
     * Constructor)
     */
    protected Tuple4() {

        super();
        super.getComponents().add(null);
        super.getComponents().add(null);
        super.getComponents().add(null);
        super.getComponents().add(null);
    }

    /**
     * Creates a new instance of the {@link Tuple4} class with the provided type
     * and values.
     *
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Z coordinate.
     * @param w
     *            The homogeneous coordinate component.
     * @param type
     *            The type of this tuple.
     */
    public Tuple4(final T x, final T y, final T z, final T w,
            final Class<? extends T> type) {

        this(x, y, z, w, type, false);
    }

    /**
     * Creates a new instance of the {@link Tuple4} class with the provided type
     * and values.
     *
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Z coordinate.
     * @param w
     *            The homogeneous coordinate component.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    @SuppressWarnings("unchecked")
    protected Tuple4(final T x, final T y, final T z, final T w,
            final Class<? extends T> type, final boolean serialization) {

        super(type, serialization, x, y, z, w);

        if ((x == null) && !serialization) {

            Tuple4.LOG
                    .error("Cannot create a four-dimensional tuple without an x "
                            + "component!");
            throw new IllegalArgumentException(
                    "Cannot create a four-dimensional tuple without an x "
                            + "component!");
        }
        if ((y == null) && !serialization) {

            Tuple4.LOG
                    .error("Cannot create a four-dimensional tuple without an y "
                            + "component!");
            throw new IllegalArgumentException(
                    "Cannot create a four-dimensional tuple without a y "
                            + "component!");
        }
        if ((z == null) && !serialization) {

            Tuple4.LOG
                    .error("Cannot create a four-dimensional tuple without an z "
                            + "component!");
            throw new IllegalArgumentException(
                    "Cannot create a four-dimensional tuple without a z "
                            + "component!");
        }
        if ((w == null) && !serialization) {

            Tuple4.LOG
                    .error("Cannot create a four-dimensional tuple without a "
                            + "homogeneous component!");
            throw new IllegalArgumentException(
                    "Cannot create a four-dimensional tuple without a "
                            + "homogeneous component!");
        }
    }


    /**
     * Copy Constructor.
     *
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public Tuple4(final ITuple4<T> tupleToCopy) {

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
    public Tuple4(final ITuple4<T> tupleToCopy, final Class<? extends T> type) {

        this(tupleToCopy.getX(), tupleToCopy.getY(), tupleToCopy.getZ(),
                tupleToCopy.getW(), type, false);
    }
}
