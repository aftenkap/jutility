package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Point4d} class provides a convenience implementation of the
 * {@link IPoint4} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Point4d")
@XmlType(name = "Point4d")
public class Point4d
        extends Point4<Double>
        implements IPoint4<Double> {

    /**
     * The origin.
     */
    public static final Point4d ORIGIN = new Point4d(0, 0, 0);


    /**
     * Creates a new instance of the {@link Point4d} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Point4d() {

        super();
    }

    /**
     * Creates a new instance of the {@link Point4d} class with the provided
     * values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     */
    public Point4d(final Number x, final Number y, final Number z) {

        super(x, y, z, Double.class);
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
    public Point4d(final ITuple4<Double> tuple) {

        super(tuple);
    }
}
