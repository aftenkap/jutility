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


/**
 * The generic {@code ShearFactor} class provides a reference implementation of
 * the {@link IShearFactor} interface.
 *
 * @param <T>
 *            the {@link Number} type of this {@code ShearFactor}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "ShearFactor")
@XmlType(name = "ShearFactor")
public class ShearFactor<T extends Number>
        implements IShearFactor<T>, Serializable {

    /**
     * Serial Version UID.
     */
    private static final long        serialVersionUID = 867385117210311246L;

    @XmlAttribute
    private final Class<? extends T> type;

    @XmlElement(name = "ShearCoefficient")
    private final T                  shearCoefficient;
    @XmlElement(name = "ShearComponent")
    private final ShearComponent     shearComponent;


    @Override
    public Class<? extends T> getType() {

        return this.type;
    }

    @Override
    public T getShearCoefficient() {

        return this.shearCoefficient;
    }


    @Override
    public ShearComponent getShearComponent() {

        return this.shearComponent;
    }



    /**
     * Constructs a new instance of the {@code ShearFactor} class.
     * (Serialization Constructor)
     */
    protected ShearFactor() {

        this(null, null, null, true);
    }

    /**
     * Constructs a new instance of the {@code ShearFactor} class with the
     * provided parameters and type.
     *
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearComponent
     *            the parameter determining the direction of the shear.
     * @param type
     *            the type of the shear.
     */
    public ShearFactor(final Number shearCoefficient,
            final ShearComponent shearComponent, final Class<? extends T> type) {

        this(shearCoefficient, shearComponent, type, false);
    }

    /**
     * Constructs a new instance of the {@code ShearFactor} class with the
     * provided parameters and type.
     *
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearComponent
     *            the parameter determining the direction of the shear.
     * @param type
     *            the type of the shear.
     */
    private ShearFactor(final Number shearCoefficient,
            final ShearComponent shearComponent, final Class<? extends T> type,
            final boolean serialization) {

        if (shearCoefficient == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a shear without a shear coefficient!");
        }
        if (shearComponent == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a scale without a shear direction");
        }

        if (type == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a shear without a type!");
        }

        this.shearCoefficient = NumberUtils.cast(shearCoefficient, type);

        this.shearComponent = shearComponent;
        this.type = type;
    }


    /**
     * Copy Constructor.
     *
     * @param shearToCopy
     *            the shear to copy.
     */
    public ShearFactor(final IShearFactor<T> shearToCopy) {

        this(shearToCopy, shearToCopy.getType());
    }

    /**
     * Copy Constructor.
     *
     * @param shearToCopy
     *            the shear to copy.
     * @param type
     *            the desired return type of the rotation to copy.
     */
    public ShearFactor(final IShearFactor<? extends Number> shearToCopy,
            final Class<? extends T> type) {

        this(shearToCopy.getShearCoefficient(),
                shearToCopy.getShearComponent(), type);
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof IShearFactor<?>) {
            IShearFactor<?> other = (IShearFactor<?>) obj;

            boolean sameCoefficient = NumberComparator.equals(
                    this.getShearCoefficient(), other.getShearCoefficient());
            boolean sameComponent = this.getShearComponent() == other
                    .getShearComponent();

            return sameCoefficient && sameComponent;
        }
        return false;
    }


    @Override
    public int hashCode() {

        int hash = 7;

        hash += 13 * this.getShearCoefficient().hashCode();
        hash += 17 * this.getShearComponent().hashCode();

        return hash;
    }


    @Override
    public String toString() {

        return "ShearFactor " + this.getShearCoefficient() + " around "
                + this.getShearComponent();
    }
}
