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


/**
 * The {@link Scalef} class provides a convenience implementation of the
 * {@link IScale} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Scalef")
public class Scalef
        extends Scale<Float>
        implements IScale<Float> {


    /**
     * Constructs a new instance of the {@link Scalef} class that represents no
     * scaling.
     */
    public Scalef() {

        this(1, 1, 1);
    }

    /**
     * Constructs a new instance of the {@link Scalef} class with the provided
     * type and parameters.
     * 
     * @param scaleFactorX
     *            the x scale factor.
     * @param scaleFactorY
     *            the y scale factor.
     * @param scaleFactorZ
     *            the z scale factor.
     */
    public Scalef(final Number scaleFactorX, final Number scaleFactorY,
            final Number scaleFactorZ) {

        super(scaleFactorX, scaleFactorY, scaleFactorZ, Float.class);
    }

    /**
     * Copy constructor.
     * 
     * @param scaleToCopy
     *            the {@link IScale scale} to copy.
     */
    public Scalef(final IScale<Float> scaleToCopy) {

        super(scaleToCopy);
    }
}
