package org.jutility.common.datatype.tuple;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



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
@JsonPropertyOrder(value =  { "type", "x", "y", "z", "w" })
@XmlRootElement(name = "Tuple4")
@XmlType(name = "Tuple4", propOrder = { "x", "y", "z", "w" })
public class Tuple4<T>
        extends TupleBase<T>
        implements ITuple4<T> {


//    private static Logger LOG = LoggerFactory.getLogger(Tuple4.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple4#getX()
     */
    @Override
    @JsonProperty(required = true, value = "x", index = 0)
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

//        LOG.debug("Setting X value of Tuple4 to " + value);
        if (super.getComponents() != null) {

            if (super.getComponents().isEmpty()) {

                super.getComponents().add(value);
            }
            else {

                super.getComponents().set(0, value);
            }
        }
        else {

//            LOG.error("Attempting to set X value of Tuple4 to null!");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple4#getY()
     */
    @Override
    @JsonProperty(required = true, value = "y", index = 1)
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

//        LOG.debug("Setting Y value of Tuple4 to " + value);
        if (super.getComponents() != null) {

            if (super.getComponents().size() == 1) {

                super.getComponents().add(value);
            }
            else if (super.getComponents().size() > 1) {

                super.getComponents().set(1, value);
            }
            else {

//                LOG.error("Setting Y value of Tuple4 out of order. Size should be >= 1 but is "
//                        + super.getComponents().size());
            }
        }
        else {

//            LOG.error("Attempting to set Y value of Tuple4 to null!");
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple4#getZ()
     */
    @Override
    @JsonProperty(required = true, value = "z", index = 2)
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

//        LOG.debug("Setting Z value of Tuple4 to " + value);
        if (super.getComponents() != null) {
            
            if (super.getComponents().size() == 2) {
                
                super.getComponents().add(value);
            }
            else if (super.getComponents().size() > 2) {
                
                super.getComponents().set(2, value);
            }
            else {

//                LOG.error("Setting Z value of Tuple4 out of order. Size should be >= 2 but is "
//                        + super.getComponents().size());
            }
        }
        else {

//            LOG.error("Attempting to set Z value of Tuple4 to null!");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple4#getW()
     */
    @Override
    @JsonProperty(required = true, value = "w", index = 3)
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

//        LOG.debug("Setting W value of Tuple4 to " + value);
        if (super.getComponents() != null) {
            
            if (super.getComponents().size() == 3) {
                
                super.getComponents().add(value);
            }
            else if (super.getComponents().size() > 3) {
                
                super.getComponents().set(3, value);
            }
            else {

//                LOG.error("Setting W value of Tuple4 out of order. Size should be >= 3 but is "
//                        + super.getComponents().size());
            }
        }
        else {

//            LOG.error("Attempting to set W value of Tuple4 to null!");
        }
    }

    /**
     * Creates a new instance of the {@link Tuple4} class. (Serialization
     * Constructor)
     */
    protected Tuple4() {

        super();
        super.getComponents().add(null);
        super.getComponents().add(null);
        super.getComponents().add(null);
        super.getComponents().add(null);
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
    @SuppressWarnings("unchecked")
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
