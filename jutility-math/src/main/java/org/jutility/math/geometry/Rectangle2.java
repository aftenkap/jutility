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

import org.jutility.math.vectorAlgebra.IPoint2;
import org.jutility.math.vectorAlgebra.Point2;


/**
 * The generic {@link Rectangle2} class provides a reference implementation of
 * the {@link IRectangle2} interface.
 *
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the rectangle.
 *
 */
@XmlRootElement(name = "Rectangle2")
@XmlType(name = "Rectangle2")
public class Rectangle2<T extends Number>
        implements IRectangle2<T> {

    @XmlAttribute
    private final Class<? extends T> type;

    @XmlElement(name = "TopLeftCorner", type = Point2.class)
    private final IPoint2<T>         topLeftCorner;
    @XmlElement(name = "BottomRightCorner", type = Point2.class)
    private final IPoint2<T>         bottomRightCorner;


    @Override
    public Class<? extends T> getType() {

        return this.type;
    }


    @Override
    public IPoint2<T> getBottomLeftCorner() {

        return new Point2<T>(this.getTopLeftCorner().getX(), this
                .getBottomRightCorner().getY(), this.type);
    }

    @Override
    public IPoint2<T> getBottomRightCorner() {

        return this.bottomRightCorner;
    }

    @Override
    public IPoint2<T> getTopLeftCorner() {

        return this.topLeftCorner;
    }

    @Override
    public IPoint2<T> getTopRightCorner() {

        return new Point2<T>(this.getBottomRightCorner().getX(), this
                .getTopLeftCorner().getY(), this.type);
    }

    /**
     * Creates a new instance of the {@link Rectangle2} class. (Serialization
     * Constructor)
     */
    protected Rectangle2() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the {@link Rectangle2} class with the provided
     * type and parameters.
     *
     * @param topLeftCorner
     *            the top-left corner.
     * @param bottomRightCorner
     *            the bottom-right corner.
     * @param type
     *            the type.
     */
    public Rectangle2(final IPoint2<? extends Number> topLeftCorner,
            final IPoint2<? extends Number> bottomRightCorner,
            final Class<? extends T> type) {

        this(topLeftCorner, bottomRightCorner, type, false);
    }

    /**
     * Creates a new instance of the {@link Rectangle2} class with the provided
     * type and parameters.
     *
     * @param topLeftCorner
     *            the top-left corner.
     * @param bottomRightCorner
     *            the bottom-right corner.
     * @param type
     *            the type.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public Rectangle2(final IPoint2<? extends Number> topLeftCorner,
            final IPoint2<? extends Number> bottomRightCorner,
            final Class<? extends T> type, final boolean serialization) {

        if (topLeftCorner == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a rectangle without a top-left corner!");
        }
        if (bottomRightCorner == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a rectangle without a bottom-right corner!");
        }

        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a line without a type!");

        }

        if (topLeftCorner != null && type != null) {
            this.topLeftCorner = new Point2<T>(topLeftCorner, type);
        }
        else {
            this.topLeftCorner = null;
        }
        if (bottomRightCorner != null && type != null) {
            this.bottomRightCorner = new Point2<T>(bottomRightCorner, type);
        }
        else {
            this.bottomRightCorner = null;
        }

        this.type = type;
    }


    /**
     * Copy Constructor.
     *
     * @param rectangleToCopy
     *            the rectangle to copy.
     */
    public Rectangle2(final IRectangle2<T> rectangleToCopy) {

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
    public Rectangle2(final IRectangle2<? extends Number> rectangleToCopy,
            final Class<? extends T> type) {

        this(rectangleToCopy.getTopLeftCorner(), rectangleToCopy
                .getBottomRightCorner(), type);
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
            boolean sameBottomRightCorner = this.getBottomRightCorner().equals(
                    other.getBottomRightCorner());

            return sameTopLeftCorner && sameBottomRightCorner;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;

        hash += 7 * this.getTopLeftCorner().hashCode();
        hash += 17 * this.getBottomRightCorner().hashCode();

        return hash;
    }
}
