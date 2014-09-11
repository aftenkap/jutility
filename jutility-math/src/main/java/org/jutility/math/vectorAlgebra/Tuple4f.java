package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Tuple4f} class provides a convenience implementation of the
 * {@link ITuple4} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Tuple4f")
@XmlType(name = "Tuple4f")
public class Tuple4f
        extends Tuple4<Float> {

    /**
     * Creates a new instance of the {@link Tuple4f} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Tuple4f() {

        super();
    }

    /**
     * Creates a new instance of the {@link Tuple4d} class with the provided
     * values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     * @param w
     *            The additional coordinate.
     */
    public Tuple4f(final Number x, final Number y, final Number z,
            final Number w) {

        super(x, y, z, w, Float.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public Tuple4f(final ITuple4<Float> tupleToCopy) {

        super(tupleToCopy);
    }

}
