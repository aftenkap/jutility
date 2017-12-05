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

import org.jutility.math.vectoralgebra.IPoint4;
import org.jutility.math.vectoralgebra.Point4;


/**
 * The generic {@code Rectangle4} class provides a reference implementation of
 * the {@link IRectangle4} interface.
 *
 * @param <T>
 *            the {@link Number} type of the {@code Rectangle4}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Rectangle4")
@XmlType(name = "Rectangle4")
public class Rectangle4<T extends Number>
        implements IRectangle4<T>, Serializable {

    /**
     * Serial Version UID.
     */
    private static final long        serialVersionUID = -5650871897919038389L;


    @XmlAttribute
    private final Class<? extends T> type;

    @XmlElement(name = "TopLeftCorner", type = Point4.class)
    private final IPoint4<T>         topLeftCorner;
    @XmlElement(name = "BottomLeftCorner", type = Point4.class)
    private final IPoint4<T>         bottomLeftCorner;
    @XmlElement(name = "TopRightCorner", type = Point4.class)
    private final IPoint4<T>         topRightCorner;
    @XmlElement(name = "BottomRightCorner", type = Point4.class)
    private final IPoint4<T>         bottomRightCorner;


    @Override
    public Class<? extends T> getType() {

        return this.type;
    }


    @Override
    public IPoint4<T> getBottomLeftCorner() {

        return this.bottomLeftCorner;
    }

    @Override
    public IPoint4<T> getBottomRightCorner() {

        return this.bottomRightCorner;
    }

    @Override
    public IPoint4<T> getTopLeftCorner() {

        return this.topLeftCorner;
    }

    @Override
    public IPoint4<T> getTopRightCorner() {

        return this.topRightCorner;
    }

    /**
     * Creates a new instance of the {@code Rectangle4} class. (Serialization
     * Constructor)
     */
    protected Rectangle4() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the {@code Rectangle4} class with the provided
     * type and parameters.
     * 
     * @param bottomLeftCorner
     *            the bottom-left corner.
     * @param topRightCorner
     *            the top-right corner.
     * @param type
     *            the type.
     */
    public Rectangle4(final IPoint4<? extends Number> bottomLeftCorner,
            final IPoint4<? extends Number> topRightCorner,
            final Class<? extends T> type) {

        this(bottomLeftCorner, topRightCorner, type, false);
    }

    /**
     * Creates a new instance of the {@code Rectangle4} class with the provided
     * type and parameters.
     * 
     * @param bottomLeftCorner
     *            the bottom-left corner.
     * @param topRightCorner
     *            the top-right corner.
     * @param type
     *            the type.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public Rectangle4(final IPoint4<? extends Number> bottomLeftCorner,
            final IPoint4<? extends Number> topRightCorner,
            final Class<? extends T> type, final boolean serialization) {

        if (bottomLeftCorner == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a line without a bottom-left corner!");
        }
        if (topRightCorner == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a line without a top-right corner!");
        }

        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a line without a type!");

        }
        if (bottomLeftCorner != null && topRightCorner != null && type != null) {

            this.bottomLeftCorner = new Point4<>(bottomLeftCorner, type);
            this.topRightCorner = new Point4<>(topRightCorner, type);

            this.topLeftCorner = new Point4<>(bottomLeftCorner.getX(),
                    topRightCorner.getY(), bottomLeftCorner.getZ(), type);
            this.bottomRightCorner = new Point4<>(topRightCorner.getX(),
                    bottomLeftCorner.getY(), topRightCorner.getZ(), type);

        }
        else {
            this.bottomLeftCorner = null;
            this.bottomRightCorner = null;
            this.topLeftCorner = null;
            this.topRightCorner = null;
        }


        this.type = type;
    }


    /**
     * Copy Constructor.
     * 
     * @param rectangleToCopy
     *            the rectangle to copy.
     */
    public Rectangle4(final IRectangle4<T> rectangleToCopy) {

        this(rectangleToCopy, rectangleToCopy.getType());
    }

    /**
     * Copy Constructor.
     * 
     * @param rectangleToCopy
     *            the rectangle to cop
     * @param type
     *            the desired type of the rectangle to copy.
     */
    public Rectangle4(final IRectangle4<? extends Number> rectangleToCopy,
            final Class<? extends T> type) {

        this(rectangleToCopy.getBottomLeftCorner(), rectangleToCopy
                .getTopRightCorner(), type);
    }


    @Override
    public String toString() {

        return "Rectangle [ Top Left: " + this.getTopLeftCorner()
                + ", Top Right: " + this.getTopRightCorner()
                + ", Bottom Left: " + this.getBottomLeftCorner()
                + ", Bottom Right: " + this.getBottomRightCorner() + " ]";
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj != null && obj instanceof IRectangle4<?>) {
            IRectangle4<?> other = (IRectangle4<?>) obj;

            boolean sameTopLeftCorner = this.getTopLeftCorner().equals(
                    other.getTopLeftCorner());
            boolean sameBottomLeftCorner = this.getBottomLeftCorner().equals(
                    other.getBottomLeftCorner());
            boolean sameTopRightCorner = this.getTopRightCorner().equals(
                    other.getTopRightCorner());
            boolean sameBottomRightCorner = this.getBottomRightCorner().equals(
                    other.getBottomRightCorner());

            return sameTopLeftCorner && sameBottomLeftCorner
                    && sameTopRightCorner && sameBottomRightCorner;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;

        hash += 7 * this.getTopLeftCorner().hashCode();
        hash += 11 * this.getBottomLeftCorner().hashCode();
        hash += 13 * this.getTopRightCorner().hashCode();
        hash += 17 * this.getBottomRightCorner().hashCode();

        return hash;
    }
}
