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


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.tuple.ITuple4;
import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.math.vectorAlgebra.Vector4;


/**
 * The abstract generic {@link RotationBase} class provides a reference
 * implementation of the {@link IRotationBase} interface. Besides providing the
 * base implementation for rotations, this class works around a limitation of
 * the JAXB XmlSerialization.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the rotation.
 */
@XmlRootElement(name = "RotationBase")
@XmlType(name = "RotationBase")
public abstract class RotationBase<T>
        implements IRotationBase<T> {

    @XmlAttribute
    private final Class<? extends T> type;

    @XmlElement(name = "RotationAxis", type = Vector4.class)
    private final ITuple4<T>         rotationAxis;
    @XmlElement(name = "RotationAngle")
    private final T                  rotationAngle;


    @Override
    public Class<? extends T> getType() {

        return this.type;
    }

    @Override
    public ITuple4<T> getAxis() {

        return this.rotationAxis;
    }


    @Override
    public T getAngle() {

        return this.rotationAngle;
    }


    /**
     * Constructs a new instance of the {@link RotationBase} class.
     * (Serialization Constructor)
     */
    @SuppressWarnings("unused")
    private RotationBase() {

        this(null, null, null, true);
    }



    /**
     * Constructs a new instance of the {@link RotationBase} class with the
     * provided type and parameters.
     * 
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     * @param type
     *            the type of the rotation.
     */
    public RotationBase(final ITuple4<T> rotationAxis, final T rotationAngle,
            final Class<? extends T> type) {

        this(rotationAxis, rotationAngle, type, false);
    }

    /**
     * Constructs a new instance of the {@link RotationBase} class with the
     * provided type and parameters. The rotation axis provided is normalized to
     * avoid distortion of objects during rotation.
     * 
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     * @param type
     *            the type of the rotation.
     * @param serialization
     *            whether or not the constructor is used during serialization.
     */
    protected RotationBase(final ITuple4<T> rotationAxis,
            final T rotationAngle, final Class<? extends T> type,
            final boolean serialization) {

        if (rotationAxis == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a rotation without a rotation axis!");
        }
        if (rotationAngle == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a rotation without a rotation angle!");
        }

        if (type == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a rotation without a type!");
        }

        this.rotationAxis = rotationAxis;
        this.rotationAngle = NumberUtils.cast(rotationAngle, type);

        this.type = type;
    }

    /**
     * Copy Constructor.
     * 
     * @param rotationToCopy
     *            the rotation to copy.
     */
    public RotationBase(final IRotationBase<T> rotationToCopy) {

        this(rotationToCopy.getAxis(), rotationToCopy.getAngle(),
                rotationToCopy.getType());
    }



    @Override
    public String toString() {

        return "Rotation Axis: " + this.rotationAxis + ", Angle: "
                + this.rotationAngle;
    }


    @Override
    public boolean equals(final Object obj) {

        if (obj != null && obj instanceof RotationBase<?>) {
            RotationBase<?> other = (RotationBase<?>) obj;

            if (this.getAxis().equals(other.getAxis())
                    && this.getAngle().equals(other.getAngle())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {

        return 7 + this.getAxis().hashCode() + this.getAngle().hashCode();
    }
}
