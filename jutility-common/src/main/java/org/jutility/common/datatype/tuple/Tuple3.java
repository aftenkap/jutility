package org.jutility.common.datatype.tuple;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The generic {@link Tuple3} class provides a reference base implementation of
 * the {@link ITuple3} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @param <T>
 *            the type of the tuple.
 */
@XmlRootElement(name = "Tuple3")
@XmlType(name = "Tuple3", propOrder = { "x", "y", "z" })
public class Tuple3<T>
        extends TupleBase<T>
        implements ITuple3<T> {



    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple3#getX()
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
        else {
            System.err.println("Shouldn't be here!");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple3#getY()
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
            else {

                System.err.println("Shouldn't be here either! "
                        + super.getComponents().size());
            }
        }
        else {
            System.err.println("Shouldn't be here!");
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple3#getZ()
     */
    @Override
    @XmlElement(name = "Z")
    public T getZ() {

        return super.get(2);
    }

    @SuppressWarnings("unused")
    private void setZ(T value) {

        if (super.getComponents() != null) {
            if (super.getComponents().size() == 2) {
                super.getComponents().add(value);
            }
            else if (super.getComponents().size() > 2) {
                super.getComponents().set(2, value);
            }
            else {

                System.err.println("Shouldn't be here either! "
                        + super.getComponents().size());
            }
        }
        else {
            System.err.println("Shouldn't be here!");
        }
    }

    /**
     * Creates a new instance of the {@link Tuple3} class. (Serialization
     * Constructor)
     */
    protected Tuple3() {

        super();
    }

    /**
     * Creates a new instance of the {@link Tuple3} class with the provided type
     * and values.
     * 
     * @param x
     *            The X component.
     * @param y
     *            The Y component.
     * @param z
     *            The Z component.
     * @param type
     *            The type of this tuple.
     */
    @SuppressWarnings("unchecked")
    public Tuple3(final T x, final T y, final T z, final Class<? extends T> type) {

        super(type, x, y, z);
    }

    /**
     * Creates a new instance of the {@link Tuple3} class with the provided type
     * and values.
     * 
     * @param x
     *            The X component.
     * @param y
     *            The Y component.
     * @param z
     *            The Z component.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    @SuppressWarnings("unchecked")
    protected Tuple3(final T x, final T y, final T z,
            final Class<? extends T> type, final boolean serialization) {

        super(type, serialization, x, y, z);
    }


    /**
     * Copy Constructor.
     * 
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public Tuple3(final ITuple3<T> tupleToCopy) {

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
    public Tuple3(final ITuple3<T> tupleToCopy, Class<? extends T> type) {

        this(tupleToCopy.getX(), tupleToCopy.getY(), tupleToCopy.getZ(), type,
                false);
    }
}
