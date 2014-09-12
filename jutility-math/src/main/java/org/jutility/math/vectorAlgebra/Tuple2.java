package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;



/**
 * The generic {@link Tuple2} class provides a reference implementation of the
 * {@link ITuple2} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @param <T>
 *            the type of the tuple.
 */
@XmlRootElement(name = "Tuple2")
@XmlType(name = "Tuple2")
public class Tuple2<T extends Number>
        extends org.jutility.common.datatype.tuple.Tuple2<T>
        implements ITuple2<T> {



    /**
     * Creates a new instance of the {@link Tuple2} class. (Serialization
     * Constructor)
     */
    protected Tuple2() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the {@link Tuple2} class with the provided type
     * and values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param type
     *            The type of this tuple.
     */
    public Tuple2(final Number x, final Number y, final Class<? extends T> type) {

        this(x, y, type, false);
    }

    /**
     * Creates a new instance of the {@link Tuple2} class with the provided type
     * and values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    private Tuple2(final Number x, final Number y, Class<? extends T> type,
            boolean serialization) {

        super(NumberUtils.cast(x, type), NumberUtils.cast(y, type), type, serialization);
    }

    /**
     * Copy Constructor.
     * 
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public Tuple2(final ITuple2<T> tupleToCopy) {

        this(tupleToCopy, tupleToCopy.getType());
    }

    /**
     * Copy Constructor.
     * 
     * @param tupleToCopy
     *            the tuple to copy.
     * @param type
     *            the desired return type of the tuple to copy.
     */
    public Tuple2(final ITuple2<? extends Number> tupleToCopy,
            Class<? extends T> type) {

        this(tupleToCopy.getX(), tupleToCopy.getY(), type);
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof ITuple2<?>) {
            ITuple2<?> otherTuple = (ITuple2<?>) obj;


            boolean xEquals = NumberComparator.equals(this.getX(), otherTuple.getX());
            boolean yEquals = NumberComparator.equals(this.getY(), otherTuple.getY());

            return xEquals && yEquals;
        }

        return false;
    }

    @Override
    public boolean isPoint() {

        return false;
    }

    @Override
    public boolean isVector() {

        return false;
    }
}
