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
 * The {@link Point4f} class provides a convenience implementation of the
 * {@link IPoint4} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Point4f")
@XmlType(name = "Point4f")
public class Point4f
        extends Point4<Float>
        implements IPoint4<Float> {

    /**
     * The origin.
     */
    public static final Point4f ORIGIN = new Point4f(0, 0, 0);


    /**
     * Creates a new instance of the {@link Point4f} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Point4f() {

        super();
    }

    /**
     * Creates a new instance of the {@link Point4f} class with the provided
     * values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     */
    public Point4f(final Number x, final Number y, final Number z) {

        super(x, y, z, Float.class);
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
    public Point4f(final ITuple4<Float> tuple) {

        super(tuple);
    }
}