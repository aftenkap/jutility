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


import org.jutility.math.vectoralgebra.IPoint4;
import org.jutility.math.vectoralgebra.Point4;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * The {@code PolyLine4} class provides a reference implementation of the
 * {@link IPolyLine4} interface.
 *
 * @param <T>
 *         the {@link Number} type of the {@code PolyLine4}.
 *
 * @author Peter J. Radics
 * @version 0.1.3
 * @since 0.1.3
 */
@XmlRootElement(name = "PolyLine4")
@XmlType(name = "PolyLine4")
public class PolyLine4<T extends Number>
        implements IPolyLine4<T>, Serializable {


    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 6800878576383614443L;



    @XmlElement(type = Point4.class)
    private final List<IPoint4<T>> points;

    @XmlAttribute
    private final Class<? extends T> type;



    @Override
    public Class<? extends T> getType() {

        return this.type;
    }



    @Override
    public List<IPoint4<T>> getPoints() {

        return Collections.unmodifiableList(this.points);
    }


    @Override
    public boolean addPoint(final IPoint4<? extends Number> point) {

        final Point4<T> pointToAdd = new Point4<>(point, this.type);

        return this.points.add(pointToAdd);
    }


    @Override
    public boolean removePoint(final IPoint4<? extends Number> point) {

        final Point4<T> pointToRemove = new Point4<>(point, this.type);

        return this.points.remove(pointToRemove);
    }


    @Override
    public void clearPoints() {

        this.points.clear();
    }



    /**
     * Creates a new instance of the {@code PolyLine4} class. (Serialization
     * Constructor)
     */
    public PolyLine4() {


        this(null, null, true);
    }


    /**
     * Creates a new instance of the {@code PolyLine4} class.
     *
     * @param type
     *         the type.
     */
    public PolyLine4(final Class<? extends T> type) {


        this(null, type, false);
    }

    /**
     * Creates a new instance of the {@link PolyLine4} class with the provided
     * type and parameters.
     *
     * @param <S>
     *         the {@link Number} type of the {@link IPoint4 Points}.
     * @param points
     *         the {@link IPoint4 Points}.
     * @param type
     *         the type.
     */
    public <S extends Number> PolyLine4(final List<IPoint4<S>> points,
            final Class<? extends T> type) {

        this(points, type, false);
    }

    /**
     * Creates a new instance of the {@link PolyLine4} class with the provided
     * type and parameters.
     *
     * @param <S>
     *         the {@link Number} type of the {@link IPoint4 Points}.
     * @param points
     *         the {@link IPoint4 Points}.
     * @param type
     *         the type.
     * @param serialization
     *         whether or not the constructor is invoked during serialization.
     */
    public <S extends Number> PolyLine4(final List<IPoint4<S>> points,
            final Class<? extends T> type, final boolean serialization) {

        if ((type == null) && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a polyline without a type!");

        }

        this.points = new LinkedList<>();
        this.type = type;

        if ((points != null) && (type != null)) {

            points.stream()
                  .map(point -> new Point4<T>(point, type))
                  .forEach(this.getPoints()::add);
        }
    }


    /**
     * Copy Constructor.
     *
     * @param polygonToCopy
     *         the polygon to copy.
     */
    public PolyLine4(final IPolyLine4<T> polygonToCopy) {

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
    public PolyLine4(final IPolyLine4<?> polygonToCopy,
            final Class<? extends T> type) {

        this(polygonToCopy.getPoints(), type);
    }



    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();

        builder.append("PolyLine4 [ ");

        for (final IPoint4<T> point : this.points) {

            builder.append(point.toString());
        }

        builder.append(" ]");


        return builder.toString();
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {

            return true;
        }

        if (obj == null || !(obj instanceof IPolyLine4<?>)) {

            return false;
        }

        final IPolyLine4<?> other = (IPolyLine4<?>) obj;

        return this.getPoints().size() == other.getPoints().size()
                && this.getPoints().containsAll(other.getPoints());

    }


    @Override
    public int hashCode() {

        int hash = 7;

        for (final IPoint4<T> point : this.getPoints()) {

            hash += 7 * point.hashCode();
        }

        return hash;
    }

}
