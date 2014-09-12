package org.jutility.common.datatype.tuple;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The generic {@link Tuple4} class provides a reference base implementation of
 * the {@link ITuple4} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @param <T>
 *            the type of the tuple.
 */
@XmlRootElement(name = "Tuple4")
@XmlType(name = "Tuple4", propOrder = { "x", "y", "z", "w" })
public class Tuple4<T>
        extends TupleBase<T>
        implements ITuple4<T> {



    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple4#getX()
     */
    @Override
    @XmlElement(name = "X")
    public T getX() {

        return super.get(0);
    }

    /**
     * Setter for the x component (Serialization).
     * 
     * @param value
     *            the new value.
     */
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
     * @see org.jutility.datatypes.tuple.ITuple4#getY()
     */
    @Override
    @XmlElement(name = "Y")
    public T getY() {

        return super.get(1);
    }

    /**
     * Setter for the y component (Serialization).
     * 
     * @param value
     *            the new value.
     */
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
     * @see org.jutility.datatypes.tuple.ITuple4#getZ()
     */
    @Override
    @XmlElement(name = "Z")
    public T getZ() {

        return super.get(2);
    }

    /**
     * Setter for the z component (Serialization).
     * 
     * @param value
     *            the new value.
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple4#getW()
     */
    @Override
    @XmlElement(name = "W")
    public T getW() {

        return super.get(3);
    }

    /**
     * Setter for the w component (Serialization).
     * 
     * @param value
     *            the new value.
     */
    @SuppressWarnings("unused")
    private void setW(T value) {

        if (super.getComponents() != null) {
            if (super.getComponents().size() == 3) {
                super.getComponents().add(value);
            }
            else if (super.getComponents().size() > 3) {
                super.getComponents().set(3, value);
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
     * Creates a new instance of the {@link Tuple4} class. (Serialization
     * Constructor)
     */
    protected Tuple4() {

        super();
    }

    /**
     * Creates a new instance of the {@link Tuple4} class with the provided type
     * and values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Z coordinate.
     * @param w
     *            The homogeneous coordinate component.
     * @param type
     *            The type of this tuple.
     */
    public Tuple4(final T x, final T y, final T z, final T w,
            final Class<? extends T> type) {

        this(x, y, z, w, type, false);
    }

    /**
     * Creates a new instance of the {@link Tuple4} class with the provided type
     * and values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Z coordinate.
     * @param w
     *            The homogeneous coordinate component.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected Tuple4(final T x, final T y, final T z, final T w,
            final Class<? extends T> type, final boolean serialization) {

        super(type, serialization, x, y, z, w);

        if (x == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a four-dimensional tuple without an x "
                            + "component!");
        }
        if (y == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a four-dimensional tuple without a y "
                            + "component!");
        }
        if (z == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a four-dimensional tuple without a z "
                            + "component!");
        }
        if (w == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a four-dimensional tuple without a "
                            + "homogeneous component!");
        }
    }


    /**
     * Copy Constructor.
     * 
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public Tuple4(final ITuple4<T> tupleToCopy) {

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
    public Tuple4(final ITuple4<T> tupleToCopy, Class<? extends T> type) {

        this(tupleToCopy.getX(), tupleToCopy.getY(), tupleToCopy.getZ(),
                tupleToCopy.getW(), type, false);
    }
}
