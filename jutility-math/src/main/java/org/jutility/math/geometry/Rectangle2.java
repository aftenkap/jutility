package org.jutility.math.geometry;


// @formatter:off
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
//@formatter:on

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.arithmetics.ArithmeticOperations;
import org.jutility.math.vectoralgebra.IPoint2;
import org.jutility.math.vectoralgebra.Point2;


/**
 * The generic {@code Rectangle2} class provides a reference implementation of
 * the {@link IRectangle2} interface.
 *
 * @param <T>
 *            the {@link Number} type of the {@code Rectangle2}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Rectangle2")
@XmlType(name = "Rectangle2")
public class Rectangle2<T extends Number>
        implements IRectangle2<T>, Serializable {


    /**
     * Serial Version UID.
     */
    private static final long        serialVersionUID = -383379881571111028L;


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

        return new Point2<>(this.getTopLeftCorner()
                .getX(), this.getBottomRightCorner()
                .getY(), this.type);
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

        return new Point2<>(this.getBottomRightCorner()
                .getX(), this.getTopLeftCorner()
                .getY(), this.type);
    }

    /**
     * Creates a new instance of the {@code Rectangle2} class. (Serialization
     * Constructor)
     */
    protected Rectangle2() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the {@code Rectangle2} class with the provided
     * type and parameters.
     * 
     * @param x
     *            the x coordinate of the top-left corner.
     * @param y
     *            the y coordinate of the top-left corner
     * @param width
     *            the width of the rectangle.
     * @param height
     *            the height of the rectangle.
     * @param type
     *            the type.
     */
    public Rectangle2(final Number x, final Number y, final Number width,
            final Number height, Class<? extends T> type) {

        this(new Point2<T>(x, y, type), new Point2<T>(ArithmeticOperations.add(
                x, width), ArithmeticOperations.add(y, height), type), type);
    }

    /**
     * Creates a new instance of the {@code Rectangle2} class with the provided
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
     * Creates a new instance of the {@code Rectangle2} class with the provided
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

            this.topLeftCorner = new Point2<>(topLeftCorner, type);
        }
        else {

            this.topLeftCorner = null;
        }
        if (bottomRightCorner != null && type != null) {

            this.bottomRightCorner = new Point2<>(bottomRightCorner, type);
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

        if (obj != null && obj instanceof IRectangle2<?>) {

            IRectangle2<?> other = (IRectangle2<?>) obj;

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
