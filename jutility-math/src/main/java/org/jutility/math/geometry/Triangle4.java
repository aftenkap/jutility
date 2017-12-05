package org.jutility.math.geometry;


// @formatter:off
/*
 * #%L
 * jutility-math
 * %%
 * Copyright (C) 4013 - 4014 jutility.org
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
 * The generic {@code Triangle4} class provides a reference implementation of
 * the {@link ITriangle4} interface.
 *
 * @param <T>
 *            the {@link Number} type of the {@code Triangle4}.
 * 
 * @author Peter J. Radics
 * @version 0.1.4
 * @since 0.1.0
 */
@XmlRootElement(name = "Triangle4")
@XmlType(name = "Triangle4")
public class Triangle4<T extends Number>
        implements ITriangle4<T>, Serializable {


    /**
     * Serial Version UID.
     */
    private static final long        serialVersionUID = 532513735685662883L;


    @XmlAttribute
    private final Class<? extends T> type;

    @XmlElement(name = "FirstPoint", type = Point4.class)
    private final IPoint4<T>         firstPoint;
    @XmlElement(name = "SecondPoint", type = Point4.class)
    private final IPoint4<T>         secondPoint;
    @XmlElement(name = "ThirdPoint", type = Point4.class)
    private final IPoint4<T>         thirdPoint;


    @Override
    public Class<? extends T> getType() {

        return this.type;
    }

    @Override
    public IPoint4<T> getFirstPoint() {

        return this.firstPoint;
    }

    @Override
    public IPoint4<T> getSecondPoint() {

        return this.secondPoint;
    }

    @Override
    public IPoint4<T> getThirdPoint() {

        return this.thirdPoint;
    }



    /**
     * Creates a new instance of the {@code Triangle4} class. (Serialization
     * Constructor)
     */
    protected Triangle4() {

        this(null, null, null, null, true);
    }


    /**
     * Creates a new instance of the {@code Triangle4} class with the provided
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
    public Triangle4(final IPoint4<? extends Number> firstPoint,
            final IPoint4<? extends Number> secondPoint,
            final IPoint4<? extends Number> thirdPoint,
            final Class<? extends T> type) {

        this(firstPoint, secondPoint, thirdPoint, type, false);
    }


    /**
     * Creates a new instance of the {@code Triangle4} class with the provided
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
    public Triangle4(final IPoint4<? extends Number> firstPoint,
            final IPoint4<? extends Number> secondPoint,
            final IPoint4<? extends Number> thirdPoint,
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

            this.firstPoint = new Point4<>(firstPoint, type);
            this.secondPoint = new Point4<>(secondPoint, type);
            this.thirdPoint = new Point4<>(thirdPoint, type);
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
    public Triangle4(final ITriangle4<T> triangleToCopy) {

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
    public Triangle4(final ITriangle4<? extends Number> triangleToCopy,
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

        if (obj != null && obj instanceof ITriangle4<?>) {

            ITriangle4<?> other = (ITriangle4<?>) obj;

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
