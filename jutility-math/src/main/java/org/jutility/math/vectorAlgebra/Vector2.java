package org.jutility.math.vectorAlgebra;

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


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The generic {@link Vector2} class provides a reference implementation of the
 * {@link IPoint2} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the point.
 */
@XmlRootElement(name = "Point2")
@XmlType(name = "Point2")
public class Vector2<T extends Number>
        extends Tuple2<T>
        implements IPoint2<T> {


    /**
     * The origin.
     * 
     * @param type
     *            the type of the desired Origin.
     * @return the Origin.
     */
    public static <T extends Number> Vector2<T> ORIGIN(Class<? extends T> type) {

        return new Vector2<T>(0, 0, type);
    }

    /**
     * Creates a new instance of the {@link Vector2} class. (Serialization
     * Constructor)
     */
    protected Vector2() {

        super();
    }


    /**
     * Creates a new instance of the {@link Vector2} class with the provided
     * type and values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param type
     *            The type of this tuple.
     */
    public Vector2(final Number x, final Number y, final Class<? extends T> type) {

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
    public Vector2(final ITuple2<T> tuple) {

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
    public Vector2(final ITuple2<? extends Number> tuple,
            Class<? extends T> returnType) {

        super(tuple, returnType);
        if (!tuple.isVector()) {

            throw new IllegalArgumentException(
                    "Provided parameter is not a vector!");
        }
    }

    @Override
    public boolean isPoint() {

        return false;
    }

    @Override
    public boolean isVector() {

        return true;
    }
}
