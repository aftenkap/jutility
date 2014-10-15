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
 * The {@link Tuple4d} class provides a convenience implementation of the
 * {@link ITuple4} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Tuple4d")
@XmlType(name = "Tuple4d")
public class Tuple4d
        extends Tuple4<Double> {

    /**
     * Creates a new instance of the {@link Tuple4d} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Tuple4d() {

        super();
    }

    /**
     * Creates a new instance of the {@link Tuple4d} class with the provided
     * values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     * @param w
     *            The additional coordinate.
     */
    public Tuple4d(final Number x, final Number y, final Number z,
            final Number w) {

        super(x, y, z, w, Double.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public Tuple4d(final ITuple4<Double> tupleToCopy) {

        super(tupleToCopy);
    }

}
