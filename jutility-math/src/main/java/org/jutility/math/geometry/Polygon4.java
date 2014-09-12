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

import org.jutility.math.vectorAlgebra.IPoint4;
import org.jutility.math.vectorAlgebra.Point4;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 * @param <T>
 *            the type of the polygon.
 */
@XmlRootElement(name = "Polygon4")
@XmlType(name = "Polygon4")
public class Polygon4<T extends Number>
        implements IPolygon4<T> {

    @XmlElement(type = Point4.class)
    private final List<IPoint4<T>>   points;

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
    public List<IPoint4<T>> getPoints() {

        return Collections.unmodifiableList(this.points);
    }

    @Override
    public boolean addPoint(IPoint4<? extends Number> point) {

        Point4<T> pointToAdd = new Point4<T>(point, this.type);

        return this.points.add(pointToAdd);
    }

    @Override
    public boolean removePoint(IPoint4<? extends Number> point) {

        Point4<T> pointToRemove = new Point4<T>(point, this.type);

        return this.points.remove(pointToRemove);
    }

    @Override
    public void clearPoints() {

        this.points.clear();
    }


    /**
     * Creates a new instance of the {@code Polygon4} class. (Serialization
     * Constructor)
     */
    public Polygon4() {


        this(null, null, true);
    }


    /**
     * Creates a new instance of the {@code Polygon4} class.
     * @param type
     *            the type.
     */
    public Polygon4(final Class<? extends T> type) {


        this(null, type, false);
    }
    /**
     * Creates a new instance of the {@link Polygon4} class with the provided
     * type and parameters.
     * 
     * @param points
     *            the points.
     * @param type
     *            the type.
     */
    public <S extends Number>Polygon4(final List<IPoint4<S>> points,
            final Class<? extends T> type) {

        this(points, type, false);
    }

    /**
     * Creates a new instance of the {@link Polygon4} class with the provided
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
    public <S extends Number>Polygon4(final List<IPoint4<S>> points,
            final Class<? extends T> type, final boolean serialization) {

        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a polygon without a type!");

        }

        this.points = new LinkedList<IPoint4<T>>();
        this.type = type;

        if (points != null && type != null) {


            for (IPoint4<? extends Number> point : points) {

                this.points.add(new Point4<T>(point, type));
            }
        }
    }


    /**
     * Copy Constructor.
     * 
     * @param polygonToCopy
     *            the polygon to copy.
     */
    public Polygon4(final IPolygon4<T> polygonToCopy) {

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
    public Polygon4(final IPolygon4<?> polygonToCopy,
            final Class<? extends T> type) {

        this(polygonToCopy.getPoints(), type);
    }



    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("Polygon4 [ ");

        for (IPoint4<T> point : this.points) {

            builder.append(point.toString());
        }

        builder.append(" ]");


        return builder.toString();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj != null && obj instanceof IRectangle4<?>) {
            IPolygon4<?> other = (IPolygon4<?>) obj;

            boolean same = true;

            for (IPoint4<?> point : other.getPoints()) {

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

        for (IPoint4<T> point : this.getPoints()) {

            hash += 7 * point.hashCode();
        }

        return hash;
    }

}
