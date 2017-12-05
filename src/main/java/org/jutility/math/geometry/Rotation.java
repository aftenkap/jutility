package org.jutility.math.geometry;


//@formatter:off
/*
* #%L
 * * jutility-math
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * *
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
//@formatter:on


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.math.vectoralgebra.IVector4;
import org.jutility.math.vectoralgebra.Vector4;


/**
 * The generic {@link Rotation} class provides a reference implementation of the
 * {@link IRotation} interface.
 * 
 * @param <T>
 *            the type of the rotation.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Rotation")
@XmlType(name = "Rotation")
public class Rotation<T extends Number>
        implements IRotation<T>, Serializable {


    /**
     * Serial Version UID.
     */
    private static final long        serialVersionUID = -635755269692285133L;


    @XmlAttribute
    private final Class<? extends T> type;

    @XmlElement(name = "RotationAxis", type = Vector4.class)
    private final IVector4<T>        rotationAxis;
    @XmlElement(name = "RotationAngle")
    private final T                  rotationAngle;



    @Override
    public Class<? extends T> getType() {

        return this.type;
    }


    @Override
    public T getRotationAngle() {

        return this.rotationAngle;
    }

    @Override
    public IVector4<T> getRotationAxis() {

        return this.rotationAxis;
    }



    /**
     * Constructs a new instance of the {@link Rotation} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Rotation() {

        this(null, null, null, true);
    }

    /**
     * Constructs a new instance of the {@link Rotation} class of the provided
     * type that does not perform any rotation.
     * 
     * @param type
     *            the type of the rotation.
     */
    public Rotation(final Class<? extends T> type) {

        this(Vector4.NULL_VECTOR(type), 0, type);
    }


    /**
     * Constructs a new instance of the {@link Rotation} class with the provided
     * type and parameters.
     * 
     * @param x
     *            the x component of the rotation axis
     * @param y
     *            the y component of the rotation axis
     * @param z
     *            the z component of the rotation axis
     * @param type
     *            the type or the rotation.
     * 
     * @param rotationAngle
     *            the rotation angle.
     */
    public Rotation(final Number x, final Number y, final Number z,
            final Number rotationAngle, final Class<? extends T> type) {

        this(new Vector4<T>(x, y, z, type), rotationAngle, type);
    }


    /**
     * Constructs a new instance of the {@link Rotation} class with the provided
     * type and parameters.
     * 
     * @param rotationAxis
     *            the rotation axis.
     * @param rotationAngle
     *            the rotation angle.
     * @param type
     *            the type of the rotation.
     */
    public Rotation(final IVector4<? extends Number> rotationAxis,
            final Number rotationAngle, final Class<? extends T> type) {

        this(rotationAxis, rotationAngle, type, false);
    }

    /**
     * Constructs a new instance of the {@link Rotation} class with the provided
     * type and parameters. The rotation axis provided is normalized to avoid
     * distortion of objects during rotation.
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
    protected Rotation(final IVector4<? extends Number> rotationAxis,
            final Number rotationAngle, final Class<? extends T> type,
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

        if (!serialization) {

            this.rotationAxis = new Vector4<>(rotationAxis, type);
            this.rotationAngle = NumberUtils.cast(rotationAngle, type);
        }
        else {

            this.rotationAxis = null;
            this.rotationAngle = null;
        }

        this.type = type;
    }

    /**
     * Copy Constructor.
     * 
     * @param rotationToCopy
     *            the rotation to copy.
     */
    public Rotation(final IRotation<T> rotationToCopy) {

        this(rotationToCopy, rotationToCopy.getType());
    }

    /**
     * Copy Constructor.
     * 
     * @param rotationToCopy
     *            the rotation to copy.
     * @param type
     *            the desired return type of the rotation to copy.
     */
    public Rotation(final IRotation<? extends Number> rotationToCopy,
            final Class<? extends T> type) {

        this(rotationToCopy.getRotationAxis(), rotationToCopy
                .getRotationAngle(), type);
    }

    /**
     * Provides a rotation by the provided rotation angle around the x axis.
     * 
     * @param <T>
     *            the {@link Number} type of the resulting {@code Rotation}.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param type
     *            the type of the rotation.
     * @return the rotation.
     */
    public static <T extends Number> Rotation<T> rotationX(
            final Number rotationAngle, final Class<? extends T> type) {

        return new Rotation<>(Vector4.I_UNIT_VECTOR(type), rotationAngle, type);
    }


    /**
     * Provides a rotation by the provided rotation angle around the y axis.
     * 
     * @param <T>
     *            the {@link Number} type of the resulting {@code Rotation}.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param type
     *            the type of the rotation.
     * @return the rotation.
     */
    public static <T extends Number> Rotation<T> rotationY(
            final Number rotationAngle, final Class<? extends T> type) {

        return new Rotation<>(Vector4.J_UNIT_VECTOR(type), rotationAngle, type);
    }


    /**
     * Provides a rotation by the provided rotation angle around the z axis.
     * 
     * @param <T>
     *            the {@link Number} type of the resulting {@code Rotation}.
     * 
     * @param rotationAngle
     *            the rotation angle.
     * @param type
     *            the type of the rotation.
     * @return the rotation.
     */
    public static <T extends Number> Rotation<T> rotationZ(
            final Number rotationAngle, final Class<? extends T> type) {

        return new Rotation<>(Vector4.K_UNIT_VECTOR(type), rotationAngle, type);
    }



    @Override
    public boolean equals(final Object obj) {

        if (obj != null && obj instanceof Rotation<?>) {
            Rotation<?> other = (Rotation<?>) obj;

            if (this.getRotationAxis().equals(other.getRotationAxis())
                    && NumberComparator.equals(this.getRotationAngle(),
                            other.getRotationAngle())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {

        return 7 + 17 * this.getRotationAxis().hashCode() + 23
                * this.getRotationAngle().hashCode();
    }


    @Override
    public String toString() {

        return "Rotation Axis: " + this.rotationAxis + ", Angle: "
                + this.rotationAngle;
    }
}
