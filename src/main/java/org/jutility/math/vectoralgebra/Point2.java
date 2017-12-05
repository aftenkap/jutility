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



/**
 * The generic {@code Point2} class provides a reference implementation of the
 * {@link IPoint2} interface.
 *
 * @param <T>
 *            the {@link Number} type of the {@code Point2}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Point2")
@XmlType(name = "Point2")
public class Point2<T extends Number>
        extends Tuple2<T>
        implements IPoint2<T> {


    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 7933336246493355528L;


    /**
     * The origin.
     *
     * @param <T>
     *            the {@link Number} type of the {@link IPoint2 Point}.
     *
     * @param type
     *            the type of the desired Origin.
     * @return the Origin.
     */
    public static final <T extends Number> IPoint2<T> ORIGIN(
            final Class<? extends T> type) {

        return new Point2<>(0, 0, type);
    }


    /**
     * Creates a new instance of the {@code Point2} class. (Serialization
     * Constructor)
     */
    protected Point2() {

        super();
    }


    /**
     * Creates a new instance of the {@code Point2} class with the provided type
     * and values.
     *
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param type
     *            The type of this tuple.
     */
    public Point2(final Number x, final Number y, final Class<? extends T> type) {

        super(x, y, type);
    }

    /**
     * Copy constructor.
     *
     * Creates a new point from the tuple provided.
     *
     * @param tuple
     *            The tuple to be copied.
     * @throws IllegalArgumentException
     *             if the tuple provided is not a point
     */
    public Point2(final ITuple2<T> tuple) {

        this(tuple, tuple.getType());
    }

    /**
     * Copy constructor.
     *
     * Creates a new point from the tuple provided.
     *
     * @param tuple
     *            The tuple to be copied.
     * @param returnType
     *            the desired return type.
     * @throws IllegalArgumentException
     *             if the tuple provided is not a point
     */
    public Point2(final ITuple2<? extends Number> tuple,
            final Class<? extends T> returnType) {

        super(tuple, returnType);
        if (!tuple.isPoint()) {

            throw new IllegalArgumentException("Tuple to copy is not a point!");
        }
    }

    @Override
    public boolean isPoint() {

        return true;
    }

    @Override
    public boolean isVector() {

        return false;
    }
}
