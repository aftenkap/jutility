package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The generic {@link Point2} class provides a reference implementation of the
 * {@link IPoint2} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the point.
 */
@XmlRootElement(name = "Point2")
@XmlType(name = "Point2")
public class Point2<T extends Number>
        extends Tuple2<T>
        implements IPoint2<T> {


    /**
     * The origin.
     * 
     * @param type
     *            the type of the desired Origin.
     * @return the Origin.
     */
    public static <T extends Number> Point2<T> ORIGIN(Class<? extends T> type) {

        return new Point2<T>(0, 0, type);
    }

    /**
     * Creates a new instance of the {@link Point2} class. (Serialization
     * Constructor)
     */
    protected Point2() {

        super();
    }


    /**
     * Creates a new instance of the {@link Point2} class with the provided type
     * and values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param type
     *            The type of this tuple.
     */
    public Point2(final Number x, final Number y, final Class<? extends T> type) {

        super(x, y, type);
    }

    /**
     * Copy constructor.
     * 
     * Creates a new point from the tuple provided.
     * 
     * @param tuple
     *            The tuple to be copied.
     * @throws IllegalArgumentException
     *             if the tuple provided is not a point
     */
    public Point2(final ITuple2<T> tuple) {

        this(tuple, tuple.getType());
    }

    /**
     * Copy constructor.
     * 
     * Creates a new point from the tuple provided.
     * 
     * @param tuple
     *            The tuple to be copied.
     * @param returnType
     *            the desired return type.
     * @throws IllegalArgumentException
     *             if the tuple provided is not a point
     */
    public Point2(final ITuple2<? extends Number> tuple,
            Class<? extends T> returnType) {

        super(tuple, returnType);
        if (!tuple.isPoint()) {

            throw new IllegalArgumentException(
                    "Provided parameter is not a point!");
        }
    }

    @Override
    public boolean isPoint() {

        return true;
    }

    @Override
    public boolean isVector() {

        return false;
    }
}