package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Tuple4d} class provides a convenience implementation of the
 * {@link ITuple4} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Tuple4d")
@XmlType(name = "Tuple4d")
public class Tuple4d
        extends Tuple4<Double> {

    /**
     * Creates a new instance of the {@link Tuple4d} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Tuple4d() {

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
    public Tuple4d(final Number x, final Number y, final Number z,
            final Number w) {

        super(x, y, z, w, Double.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public Tuple4d(final ITuple4<Double> tupleToCopy) {

        super(tupleToCopy);
    }

}
