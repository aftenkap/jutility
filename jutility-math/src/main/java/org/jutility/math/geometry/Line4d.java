package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlType;

import org.jutility.math.vectorAlgebra.IPoint4;


/**
 * The {@link Line4d} class provides a convenience implementation of the
 * {@link ILine4} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlType(name = "Line4d")
public class Line4d
        extends Line4<Double>
        implements ILine4<Double> {


    /**
     * Creates a new instance of the {@link Line4d} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Line4d() {

        super();
    }

    /**
     * Creates a new instance of the {@link Line4d} class with the provided type
     * and parameters.
     * 
     * @param source
     *            the source of the line.
     * @param sink
     *            the sink of the line.
     */
    public Line4d(final IPoint4<? extends Number> source,
            final IPoint4<? extends Number> sink) {

        super(source, sink, Double.class);
    }

    /**
     * Copy Constructor.
     * 
     * @param lineToCopy
     *            the line to copy.
     */
    public Line4d(final ILine4<Double> lineToCopy) {

        super(lineToCopy);
    }

}
