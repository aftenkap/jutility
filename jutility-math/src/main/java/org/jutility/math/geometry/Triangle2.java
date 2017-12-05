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

import org.jutility.math.vectoralgebra.IPoint2;
import org.jutility.math.vectoralgebra.Point2;


/**
 * The generic {@code Triangle2} class provides a reference implementation of
 * the {@link ITriangle2} interface.
 *
 * @param <T>
 *            the {@link Number} type of the {@code Triangle2}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Triangle2")
@XmlType(name = "Triangle2")
public class Triangle2<T extends Number>
        implements ITriangle2<T>, Serializable {


    /**
     * Serial Version UID.
     */
    private static final long        serialVersionUID = 1480691131362723023L;


    @XmlAttribute
    private final Class<? extends T> type;

    @XmlElement(name = "FirstPoint", type = Point2.class)
    private final IPoint2<T>         firstPoint;
    @XmlElement(name = "SecondPoint", type = Point2.class)
    private final IPoint2<T>         secondPoint;
    @XmlElement(name = "ThirdPoint", type = Point2.class)
    private final IPoint2<T>         thirdPoint;


    @Override
    public Class<? extends T> getType() {

        return this.type;
    }

    @Override
    public IPoint2<T> getFirstPoint() {

        return this.firstPoint;
    }

    @Override
    public IPoint2<T> getSecondPoint() {

        return this.secondPoint;
    }

    @Override
    public IPoint2<T> getThirdPoint() {

        return this.thirdPoint;
    }



    /**
     * Creates a new instance of the {@code Triangle2} class. (Serialization
     * Constructor)
     */
    protected Triangle2() {

        this(null, null, null, null, true);
    }


    /**
     * Creates a new instance of the {@code Triangle2} class with the provided
     * type and parameters.
     *
     * @param firstPoint
     *            the first point.
     * @param secondPoint
     *            the second point.
     * @param thirdPoint
     *            the third point.
     * @param type
     *            the type.
     */
    public Triangle2(final IPoint2<? extends Number> firstPoint,
            final IPoint2<? extends Number> secondPoint,
            final IPoint2<? extends Number> thirdPoint,
            final Class<? extends T> type) {

        this(firstPoint, secondPoint, thirdPoint, type, false);
    }


    /**
     * Creates a new instance of the {@code Triangle2} class with the provided
     * type and parameters.
     *
     * @param firstPoint
     *            the first point.
     * @param secondPoint
     *            the second point.
     * @param thirdPoint
     *            the third point.
     * @param type
     *            the type.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public Triangle2(final IPoint2<? extends Number> firstPoint,
            final IPoint2<? extends Number> secondPoint,
            final IPoint2<? extends Number> thirdPoint,
            final Class<? extends T> type, final boolean serialization) {

        if (firstPoint == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a triangle without a first point!");
        }
        if (secondPoint == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a triangle without a second point!");
        }
        if (thirdPoint == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a triangle without a second point!");
        }

        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a line without a type!");

        }

        if (!serialization) {

            this.firstPoint = new Point2<>(firstPoint, type);
            this.secondPoint = new Point2<>(secondPoint, type);
            this.thirdPoint = new Point2<>(thirdPoint, type);
        }
        else {

            this.firstPoint = null;
            this.secondPoint = null;
            this.thirdPoint = null;
        }

        this.type = type;
    }


    /**
     * Copy Constructor.
     *
     * @param triangleToCopy
     *            the triangle to copy.
     */
    public Triangle2(final ITriangle2<T> triangleToCopy) {

        this(triangleToCopy, triangleToCopy.getType());
    }

    /**
     * Copy Constructor.
     *
     * @param triangleToCopy
     *            the triangle to cop
     * @param type
     *            the desired type of the triangle to copy.
     */
    public Triangle2(final ITriangle2<? extends Number> triangleToCopy,
            final Class<? extends T> type) {

        this(triangleToCopy.getFirstPoint(), triangleToCopy.getSecondPoint(),
                triangleToCopy.getThirdPoint(), type);
    }

    @Override
    public String toString() {

        return "Triangle [ " + this.getFirstPoint() + ", "
                + this.getSecondPoint() + ", " + this.getThirdPoint() + " ]";
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj != null && obj instanceof ITriangle2<?>) {

            ITriangle2<?> other = (ITriangle2<?>) obj;

            boolean sameFirstPoint = this.getFirstPoint().equals(
                    other.getFirstPoint());
            boolean sameSecondPoint = this.getSecondPoint().equals(
                    other.getSecondPoint());
            boolean sameThirdPoint = this.getThirdPoint().equals(
                    other.getThirdPoint());

            return sameFirstPoint && sameSecondPoint && sameThirdPoint;
        }

        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;

        hash += 11 * this.getFirstPoint().hashCode();
        hash += 13 * this.getSecondPoint().hashCode();
        hash += 17 * this.getThirdPoint().hashCode();

        return hash;
    }
}
