/**
 * 
 */
package org.jutility.math.geometry;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.vectorAlgebra.IPoint2;
import org.jutility.math.vectorAlgebra.Point2;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 * @param <T>
 *            the type of the polygon.
 */
@XmlRootElement(name = "Polygon2")
@XmlType(name = "Polygon4")
public class Polygon2<T extends Number>
        implements IPolygon2<T> {

    @XmlElement(type = Point2.class)
    private final List<IPoint2<T>>   points;

    @XmlAttribute
    private final Class<? extends T> type;

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.geometry.IPolygon4#getType()
     */
    @Override
    public Class<? extends T> getType() {

        return this.type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.geometry.IPolygon4#getPoints()
     */
    @Override
    public List<IPoint2<T>> getPoints() {

        return Collections.unmodifiableList(this.points);
    }

    @Override
    public boolean addPoint(IPoint2<? extends Number> point) {

        Point2<T> pointToAdd = new Point2<T>(point, this.type);

        return this.points.add(pointToAdd);
    }

    @Override
    public boolean removePoint(IPoint2<? extends Number> point) {

        Point2<T> pointToRemove = new Point2<T>(point, this.type);

        return this.points.remove(pointToRemove);
    }

    @Override
    public void clearPoints() {

        this.points.clear();
    }


    /**
     * Creates a new instance of the {@code Polygon2} class. (Serialization
     * Constructor)
     */
    public Polygon2() {


        this(null, null, true);
    }


    /**
     * Creates a new instance of the {@code Polygon2} class.
     * 
     * @param type
     *            the type.
     */
    public Polygon2(final Class<? extends T> type) {


        this(null, type, false);
    }

    /**
     * Creates a new instance of the {@link Polygon2} class with the provided
     * type and parameters.
     * 
     * @param points
     *            the points.
     * @param type
     *            the type.
     */
    public <S extends Number> Polygon2(final List<IPoint2<S>> points,
            final Class<? extends T> type) {

        this(points, type, false);
    }

    /**
     * Creates a new instance of the {@link Polygon2} class with the provided
     * type and parameters.
     * 
     * @param points
     *            the points.
     * @param type
     *            the type.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public <S extends Number> Polygon2(final List<IPoint2<S>> points,
            final Class<? extends T> type, final boolean serialization) {

        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a polygon without a type!");

        }

        this.points = new LinkedList<IPoint2<T>>();
        this.type = type;

        if (points != null && type != null) {


            for (IPoint2<? extends Number> point : points) {

                this.points.add(new Point2<T>(point, type));
            }
        }
    }


    /**
     * Copy Constructor.
     * 
     * @param polygonToCopy
     *            the polygon to copy.
     */
    public Polygon2(final IPolygon2<T> polygonToCopy) {

        this(polygonToCopy, polygonToCopy.getType());
    }

    /**
     * Copy Constructor.
     * 
     * @param polygonToCopy
     *            the polygon to cop
     * @param type
     *            the desired type of the rectangle to copy.
     */
    public Polygon2(final IPolygon2<?> polygonToCopy,
            final Class<? extends T> type) {

        this(polygonToCopy.getPoints(), type);
    }



    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("Polygon4 [ ");

        for (IPoint2<T> point : this.points) {

            builder.append(point.toString());
        }

        builder.append(" ]");


        return builder.toString();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj != null && obj instanceof IPolygon2<?>) {
            IPolygon2<?> other = (IPolygon2<?>) obj;

            boolean same = true;

            for (IPoint2<?> point : other.getPoints()) {

                if (!this.getPoints().contains(point)) {

                    same = false;
                    break;
                }
            }


            return same;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;

        for (IPoint2<T> point : this.getPoints()) {

            hash += 7 * point.hashCode();
        }

        return hash;
    }

}
