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
 * The {@link Rotationf} class provides a convenience implementation of the
 * {@link IRotation} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Rotationf")
public class Rotationf
        extends Rotation<Float>
        implements IRotation<Float> {


    /**
     * Constructs a new instance of the {@link Rotationf} class that does not
     * perform any rotation.
     */
    public Rotationf() {

        this(Vector4.NULL_VECTOR(Float.class), 0);
    }


    /**
     * Constructs a new instance of the {@link Rotationf} class with the
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
    public Rotationf(final float x, final float y, final float z,
            final float rotationAngle) {

        this(new Vector4<Float>(x, y, z, Float.class), rotationAngle);
    }


    /**
     * Constructs a new instance of the {@link Rotationf} class with the
     * provided parameters. The rotation axis provided is normalized to avoid
     * distorting of objects during rotation.
     * 
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     */
    public Rotationf(final IVector4<Float> rotationAxis,
            final float rotationAngle) {

        super(rotationAxis, rotationAngle, Float.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param rotationToCopy
     *            the rotation to copy.
     */
    public Rotationf(final IRotation<Float> rotationToCopy) {

        super(rotationToCopy);
    }
}
