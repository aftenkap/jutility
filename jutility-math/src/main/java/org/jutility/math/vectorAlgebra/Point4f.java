package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Point4f} class provides a convenience implementation of the
 * {@link IPoint4} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Point4f")
@XmlType(name = "Point4f")
public class Point4f
        extends Point4<Float>
        implements IPoint4<Float> {

    /**
     * The origin.
     */
    public static final Point4f ORIGIN = new Point4f(0, 0, 0);


    /**
     * Creates a new instance of the {@link Point4f} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Point4f() {

        super();
    }

    /**
     * Creates a new instance of the {@link Point4f} class with the provided
     * values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     */
    public Point4f(final Number x, final Number y, final Number z) {

        super(x, y, z, Float.class);
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
    public Point4f(final ITuple4<Float> tuple) {

        super(tuple);
    }
}