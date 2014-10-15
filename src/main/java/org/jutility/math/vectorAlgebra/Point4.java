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


import com.fasterxml.jackson.annotation.JsonPropertyOrder;



/**
 * The generic {@link Point4} class provides a reference implementation of the
 * {@link IPoint4} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the point.
 */
@JsonPropertyOrder(value =  { "type", "x", "y", "z", "w" })
@XmlRootElement(name = "Point4")
@XmlType(name = "Point4")
public class Point4<T extends Number>
        extends Tuple4<T>
        implements IPoint4<T> {

//    private static Logger LOG = LoggerFactory.getLogger(Point4.class);

    /**
     * The origin.
     * 
     * @param type
     *            the type of the desired Origin.
     * @return the Origin.
     */
    public static <T extends Number> Point4<T> ORIGIN(Class<? extends T> type) {

        return new Point4<T>(0, 0, 0, type);
    }

    /**
     * Creates a new instance of the {@link Point4} class. (Serialization
     * Constructor)
     */
    protected Point4() {

        super();
    }


    /**
     * Creates a new instance of the {@link Point4} class with the provided type
     * and values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     * @param type
     *            The type of this tuple.
     */
    public Point4(final Number x, final Number y, final Number z,
            final Class<? extends T> type) {

        super(x, y, z, 1, type);
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
    public Point4(final ITuple4<T> tuple) {

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
    public Point4(final ITuple4<? extends Number> tuple,
            Class<? extends T> returnType) {

        super(tuple, returnType);

        if (!tuple.isPoint()) {

//            LOG.error("Error copying Tuple " + tuple
//                    + ": Provided parameter is not a point!");
            throw new IllegalArgumentException(
                    "Error copying Tuple " + tuple
                    + ": Provided parameter is not a point!");
        }
    }
}
