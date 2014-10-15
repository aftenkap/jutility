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

import org.jutility.common.datatype.util.NumberUtils;


/**
 * The generic {@link ShearBase} class provides a reference implementation of
 * the {@link IShearBase} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of this {@link ShearBase}.
 */
@XmlRootElement(name = "ShearBase")
@XmlType(name = "ShearBase")
public class ShearBase<T>
        implements IShearBase<T> {

    @XmlAttribute
    private final Class<? extends T> type;

    @XmlElement(name = "ShearCoefficient")
    private final T                  shearCoefficient;
    @XmlElement(name = "ShearBy")
    private final ShearBy            shearBy;


    @Override
    public Class<? extends T> getType() {

        return this.type;
    }

    @Override
    public T getShearCoefficient() {

        return this.shearCoefficient;
    }


    @Override
    public ShearBy getShearBy() {

        return this.shearBy;
    }


    /**
     * Constructs a new instance of the {@link Rotation} class. (Serialization
     * Constructor)
     */
    protected ShearBase() {

        this(null, null, null, true);
    }

    /**
     * Constructs a new instance of the {@link Rotation} class with the provided
     * parameters and type.
     * 
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearBy
     *            the parameter determining the direction of the shear.
     * @param type
     *            the type of the shear.
     */
    public ShearBase(final Object shearCoefficient, final ShearBy shearBy,
            final Class<? extends T> type) {

        this(shearCoefficient, shearBy, type, false);
    }

    /**
     * Constructs a new instance of the {@link Rotation} class with the provided
     * parameters and type.
     * 
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearBy
     *            the parameter determining the direction of the shear.
     * @param type
     *            the type of the shear.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected ShearBase(final Object shearCoefficient, final ShearBy shearBy,
            final Class<? extends T> type, final boolean serialization) {

        if (shearCoefficient == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a shear without a shear coefficient!");
        }
        if (shearBy == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a scale without a shear direction");
        }

        if (type == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a shear without a type!");
        }

        this.shearCoefficient = NumberUtils.cast(shearCoefficient, type);

        this.shearBy = shearBy;
        this.type = type;
    }


    /**
     * Copy Constructor.
     * 
     * @param shearToCopy
     *            the shear to copy.
     */
    public ShearBase(final IShearBase<T> shearToCopy) {

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
    public ShearBase(final IShearBase<?> shearToCopy,
            final Class<? extends T> type) {

        this(shearToCopy.getShearCoefficient(), shearToCopy.getShearBy(), type);
    }

    @Override
    public String toString() {

        return "Shear " + this.getShearCoefficient() + " around "
                + this.getShearBy();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof IShearBase<?>) {
            IShearBase<?> other = (IShearBase<?>) obj;

            boolean sameCoefficient = this.getShearCoefficient().equals(
                    other.getShearCoefficient());
            boolean sameDirection = this.getShearBy() == other.getShearBy();

            return sameCoefficient && sameDirection;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 71;

        hash += 91 * this.getShearCoefficient().hashCode();
        hash += 97 * this.getShearBy().hashCode();

        return hash;
    }
}
