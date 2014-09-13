package org.jutility.common.datatype.tuple;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The generic {@link Tuple2} class provides a reference base implementation of
 * the {@link ITuple2} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @param <T>
 *            the type of the tuple.
 */
@XmlRootElement(name = "Tuple2")
@XmlType(name = "Tuple2", propOrder = { "x", "y" })
public class Tuple2<T>
        extends TupleBase<T>
        implements ITuple2<T> {



    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple2#getX()
     */
    @Override
    @XmlElement(name = "X")
    public T getX() {

        return super.get(0);
    }

    @SuppressWarnings("unused")
    private void setX(T value) {

        if (super.getComponents() != null) {
            if (super.getComponents().isEmpty()) {
                super.getComponents().add(value);
            }
            else {
                super.getComponents().set(0, value);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple2#getY()
     */
    @Override
    @XmlElement(name = "Y")
    public T getY() {

        return super.get(1);
    }

    @SuppressWarnings("unused")
    private void setY(T value) {

        if (super.getComponents() != null) {
            if (super.getComponents().size() == 1) {
                super.getComponents().add(value);
            }
            else if (super.getComponents().size() > 1) {
                super.getComponents().set(1, value);
            }
        }
    }


    /**
     * Creates a new instance of the {@link Tuple2} class. (Serialization
     * Constructor)
     */
    protected Tuple2() {

        super();
    }

    /**
     * Creates a new instance of the {@link Tuple2} class with the provided type
     * and values.
     * 
     * @param x
     *            The X component.
     * @param y
     *            The Y component.
     * @param type
     *            The type of this tuple.
     */
    @SuppressWarnings("unchecked")
    public Tuple2(final T x, final T y, final Class<? extends T> type) {

        super(type, x, y);
    }

    /**
     * Creates a new instance of the {@link Tuple2} class with the provided type
     * and values.
     * 
     * @param x
     *            The X component.
     * @param y
     *            The Y component.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    @SuppressWarnings("unchecked")
    protected Tuple2(final T x, final T y, final Class<? extends T> type,
            final boolean serialization) {

        super(type, serialization, x, y);
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
    public Tuple2(final ITuple2<T> tupleToCopy, Class<? extends T> type) {

        this(tupleToCopy.getX(), tupleToCopy.getY(), type, false);
    }
}
