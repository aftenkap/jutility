package org.jutility.math.vectorAlgebra;



import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Vector4f} class provides a convenience implementation of the
 * {@link IVector4} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Vector4f")
@XmlType(name = "Vector4f")
public class Vector4f
        extends Vector4<Float>
        implements IVector4<Float> {

    /**
     * The i unit vector.
     */
    public static final Vector4f I_UNIT_VECTOR = new Vector4f(1, 0, 0);
    /**
     * The j unit vector.
     */
    public static final Vector4f J_UNIT_VECTOR = new Vector4f(0, 1, 0);
    /**
     * The k unit vector.
     */
    public static final Vector4f K_UNIT_VECTOR = new Vector4f(0, 0, 1);
    /**
     * The null vector.
     */
    public static final Vector4f NULL_VECTOR   = new Vector4f(0, 0, 0);


    /**
     * Creates a new instance of the {@link Vector4f} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Vector4f() {

        super();
    }

    /**
     * Creates a new instance of the {@link Vector4f} class with the provided
     * values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     */
    public Vector4f(final Number x, final Number y, final Number z) {

        super(x, y, z, Float.class);
    }


    /**
     * Copy constructor.
     * 
     * Creates a new vector from the tuple provided.
     * 
     * @param tuple
     *            The tuple to be copied.
     * @throws IllegalArgumentException
     *             if the tuple provided is not a vector
     */
    public Vector4f(final ITuple4<Float> tuple) {

        super(tuple);
    }
}
