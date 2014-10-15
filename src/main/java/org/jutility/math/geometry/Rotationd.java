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

import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Vector4;


/**
 * The {@link Rotationd} class provides a convenience implementation of the
 * {@link IRotation} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Rotationd")
public class Rotationd
        extends Rotation<Double>
        implements IRotation<Double> {


    /**
     * Constructs a new instance of the {@link Rotationd} class that does not
     * perform any rotation.
     */
    public Rotationd() {

        this(Vector4.NULL_VECTOR(Double.class), 0);
    }


    /**
     * Constructs a new instance of the {@link Rotationd} class with the
     * provided parameters.
     * 
     * @param x
     *            the x component of the rotation axis
     * @param y
     *            the y component of the rotation axis
     * @param z
     *            the z component of the rotation axis
     * 
     * @param rotationAngle
     *            the rotation angle.
     */
    public Rotationd(final double x, final double y, final double z,
            final double rotationAngle) {

        this(new Vector4<Double>(x, y, z, Double.class), rotationAngle);
    }


    /**
     * Constructs a new instance of the {@link Rotationd} class with the
     * provided parameters. The rotation axis provided is normalized to avoid
     * distorting of objects during rotation.
     * 
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     */
    public Rotationd(final IVector4<Double> rotationAxis,
            final double rotationAngle) {

        super(rotationAxis, rotationAngle, Double.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param rotationToCopy
     *            the rotation to copy.
     */
    public Rotationd(final IRotation<Double> rotationToCopy) {

        super(rotationToCopy);
    }
}
