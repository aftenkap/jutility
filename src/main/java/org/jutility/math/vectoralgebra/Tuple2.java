package org.jutility.math.vectoralgebra;


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

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;



/**
 * The generic {@code Tuple2} class provides a reference implementation of the
 * {@link ITuple2} interface.
 *
 * @param <T>
 *            the type of the tuple.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Tuple2")
@XmlType(name = "Tuple2")
public class Tuple2<T extends Number>
        extends org.jutility.common.datatype.tuple.Tuple2<T>
        implements ITuple2<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 2088319056406205081L;


    /**
     * Creates a new instance of the {@code Tuple2} class. (Serialization
     * Constructor)
     */
    protected Tuple2() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the {@code Tuple2} class with the provided type
     * and values.
     *
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param type
     *            The type of this tuple.
     */
    public Tuple2(final Number x, final Number y, final Class<? extends T> type) {

        this(x, y, type, false);
    }

    /**
     * Creates a new instance of the {@code Tuple2} class with the provided type
     * and values.
     *
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    private Tuple2(final Number x, final Number y,
            final Class<? extends T> type, final boolean serialization) {

        super(NumberUtils.cast(x, type), NumberUtils.cast(y, type), type,
                serialization);
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
    public Tuple2(final ITuple2<? extends Number> tupleToCopy,
            final Class<? extends T> type) {

        this(tupleToCopy.getX(), tupleToCopy.getY(), type);
    }



    @Override
    public boolean equals(final Object obj) {

        if ((obj != null) && (obj instanceof ITuple2<?>)) {

            final ITuple2<?> otherTuple = (ITuple2<?>) obj;

            final boolean xEquals = NumberComparator.equals(this.getX(),
                    otherTuple.getX());
            final boolean yEquals = NumberComparator.equals(this.getY(),
                    otherTuple.getY());

            return xEquals && yEquals;
        }

        return false;
    }

    @Override
    public int hashCode() {

        int hash = 13;

        hash += this.getX() != null ? this.getX().hashCode() * 7 : 0;
        hash += this.getY() != null ? this.getY().hashCode() * 23 : 0;

        return hash;
    }

    @Override
    public boolean isPoint() {

        return false;
    }

    @Override
    public boolean isVector() {

        return false;
    }
}
