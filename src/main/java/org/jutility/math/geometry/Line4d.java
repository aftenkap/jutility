package org.jutility.math.geometry;

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


import javax.xml.bind.annotation.XmlType;

import org.jutility.math.vectorAlgebra.IPoint4;


/**
 * The {@link Line4d} class provides a convenience implementation of the
 * {@link ILine4} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlType(name = "Line4d")
public class Line4d
        extends Line4<Double>
        implements ILine4<Double> {


    /**
     * Creates a new instance of the {@link Line4d} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Line4d() {

        super();
    }

    /**
     * Creates a new instance of the {@link Line4d} class with the provided type
     * and parameters.
     * 
     * @param source
     *            the source of the line.
     * @param sink
     *            the sink of the line.
     */
    public Line4d(final IPoint4<? extends Number> source,
            final IPoint4<? extends Number> sink) {

        super(source, sink, Double.class);
    }

    /**
     * Copy Constructor.
     * 
     * @param lineToCopy
     *            the line to copy.
     */
    public Line4d(final ILine4<Double> lineToCopy) {

        super(lineToCopy);
    }

}
