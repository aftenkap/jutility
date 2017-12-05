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


import org.jutility.math.vectoralgebra.IPoint2;
import org.jutility.math.vectoralgebra.Point2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * The {@code PolyLine2} class provides a reference implementation of the
 * {@link IPolyLine2} interface.
 *
 * @param <T>
 *         the {@link Number} type of the {@code PolyLine2}.
 *
 * @author Peter J. Radics
 * @version 0.1.3
 * @since 0.1.3
 */
@XmlRootElement(name = "PolyLine2")
@XmlType(name = "PolyLine2")
public class PolyLine2<T extends Number>
        implements IPolyLine2<T>, Serializable {


    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 929794420845305322L;


    @XmlElement(type = Point2.class)
    private final List<IPoint2<T>> points;

    @XmlAttribute
    private final Class<? extends T> type;



    @Override
    public Class<? extends T> getType() {

        return this.type;
    }


    @Override
    public List<IPoint2<T>> getPoints() {

        return Collections.unmodifiableList(this.points);
    }


    @Override
    public boolean addPoint(IPoint2<? extends Number> point) {

        Point2<T> pointToAdd = new Point2<>(point, this.type);

        return this.points.add(pointToAdd);
    }


    @Override
    public boolean removePoint(IPoint2<? extends Number> point) {

        Point2<T> pointToRemove = new Point2<>(point, this.type);

        return this.points.remove(pointToRemove);
    }


    @Override
    public void clearPoints() {

        this.points.clear();
    }


    /**
     * Creates a new instance of the {@code PolyLine2} class. (Serialization
     * Constructor)
     */
    public PolyLine2() {


        this(null, null, true);
    }


    /**
     * Creates a new instance of the {@code PolyLine2} class.
     *
     * @param type
     *         the type.
     */
    public PolyLine2(final Class<? extends T> type) {


        this(null, type, false);
    }

    /**
     * Creates a new instance of the {@link PolyLine2} class with the provided
     * type and parameters.
     *
     * @param <S>
     *         the {@link Number} type of the {@link IPoint2 Points}.
     * @param points
     *         the {@link IPoint2 Points}.
     * @param type
     *         the type.
     */
    public <S extends Number> PolyLine2(final List<IPoint2<S>> points,
            final Class<? extends T> type) {

        this(points, type, false);
    }

    /**
     * Creates a new instance of the {@link PolyLine2} class with the provided
     * type and parameters.
     *
     * @param <S>
     *         the {@link Number} type of the {@link IPoint2 Points}.
     * @param points
     *         the {@link IPoint2 Points}.
     * @param type
     *         the type.
     * @param serialization
     *         whether or not the constructor is invoked during serialization.
     */
    public <S extends Number> PolyLine2(final List<IPoint2<S>> points,
            final Class<? extends T> type, final boolean serialization) {

        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a polyline without a type!");

        }

        this.points = new LinkedList<>();
        this.type = type;

        if (points != null && type != null) {


            for (IPoint2<? extends Number> point : points) {

                this.points.add(new Point2<>(point, type));
            }
        }
    }


    /**
     * Copy Constructor.
     *
     * @param polygonToCopy
     *         the polygon to copy.
     */
    public PolyLine2(final IPolyLine2<T> polygonToCopy) {

        this(polygonToCopy, polygonToCopy.getType());
    }

    /**
     * Copy Constructor.
     *
     * @param polygonToCopy
     *         the polygon to cop
     * @param type
     *         the desired type of the rectangle to copy.
     */
    public PolyLine2(final IPolyLine2<?> polygonToCopy,
            final Class<? extends T> type) {

        this(polygonToCopy.getPoints(), type);
    }



    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("PolyLine2 [ ");

        for (IPoint2<T> point : this.points) {

            builder.append(point.toString());
        }

        builder.append(" ]");


        return builder.toString();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj == this) {

            return true;
        }

        if (obj == null || !(obj instanceof IPolyLine2<?>)) {

            return false;
        }


        IPolyLine2<?> other = (IPolyLine2<?>) obj;

        return this.getPoints().size() == other.getPoints().size()
                && this.getPoints().containsAll(other.getPoints());

    }

    @Override
    public int hashCode() {

        int hash = 7;

        hash += this.getPoints()
                    .stream()
                    .map(Object::hashCode)
                    .map(hashCode -> 7 * hashCode)
                    .reduce(0, Integer::sum);

        return hash;
    }
}
