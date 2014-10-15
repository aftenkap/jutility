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


/**
 * The {@link Sheard} class provides a convenience implementation of the
 * {@link IShear} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class Sheard
        extends Shear<Double>
        implements IShear<Double> {


    /**
     * Creates a new instance of the {@link Sheard} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Sheard() {

        super();
    }

    /**
     * Creates a new instance of the {@link Sheard} class with the provided
     * parameters.
     * 
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearBy
     *            the shear by instructions.
     */
    public Sheard(final double shearCoefficient, final ShearBy shearBy) {

        super(shearCoefficient, shearBy, Double.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param shearToCopy
     *            the {@link IShear shear} to copy.
     */
    public Sheard(final IShear<Double> shearToCopy) {

        super(shearToCopy);
    }
}
