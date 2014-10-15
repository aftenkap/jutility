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


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;


/**
 * The generic {@link Shear} class provides a reference implementation of the
 * {@link IShear} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of this {@link Shear}.
 */
@XmlRootElement(name = "Shear")
@XmlType(name = "Shear")
public class Shear<T extends Number>
        extends ShearBase<T>
        implements IShear<T> {



    /**
     * Constructs a new instance of the {@link Rotation} class. (Serialization
     * Constructor)
     */
    protected Shear() {

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
    public Shear(final Number shearCoefficient, final ShearBy shearBy,
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
     */
    private Shear(final Number shearCoefficient, final ShearBy shearBy,
            final Class<? extends T> type, final boolean serialization) {

        super(NumberUtils.cast(shearCoefficient, type), shearBy, type, serialization);

    }


    /**
     * Copy Constructor.
     * 
     * @param shearToCopy
     *            the shear to copy.
     */
    public Shear(final IShear<T> shearToCopy) {

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
    public Shear(final IShear<? extends Number> shearToCopy,
            final Class<? extends T> type) {

        this(shearToCopy.getShearCoefficient(), shearToCopy.getShearBy(), type);
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof IShear<?>) {
            IShear<?> other = (IShear<?>) obj;

            boolean sameCoefficient = NumberComparator.equals(
                    this.getShearCoefficient(), other.getShearCoefficient());
            boolean sameDirection = this.getShearBy() == other.getShearBy();

            return sameCoefficient && sameDirection;
        }
        return false;
    }
}
