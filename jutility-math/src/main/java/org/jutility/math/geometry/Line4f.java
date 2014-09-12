package org.jutility.math.geometry;


import javax.xml.bind.annotation.XmlType;

import org.jutility.math.vectorAlgebra.IPoint4;


/**
 * The {@link Line4f} class provides a convenience implementation of the
 * {@link ILine4} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlType(name = "Line4f")
public class Line4f
        extends Line4<Float>
        implements ILine4<Float> {


    /**
     * Creates a new instance of the {@link Line4f} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Line4f() {

        super();
    }

    /**
     * Creates a new instance of the {@link Line4f} class with the provided type
     * and parameters.
     * 
     * @param source
     *            the source of the line.
     * @param sink
     *            the sink of the line.
     */
    public Line4f(final IPoint4<? extends Number> source,
            final IPoint4<? extends Number> sink) {

        super(source, sink, Float.class);
    }

    /**
     * Copy Constructor.
     * 
     * @param lineToCopy
     *            the line to copy.
     */
    public Line4f(final ILine4<Float> lineToCopy) {

        super(lineToCopy);
    }
}
