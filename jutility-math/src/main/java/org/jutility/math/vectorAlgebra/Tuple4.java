package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberComparator;
import org.jutility.common.datatype.util.NumberUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;



/**
 * The generic {@link Tuple4} class provides a reference implementation of the
 * {@link ITuple4} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @param <T>
 *            the type of the tuple.
 */

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "tupleType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Point4.class, name = "Point4"),
    @JsonSubTypes.Type(value = Vector4.class, name = "Vector4")
})
@JsonPropertyOrder(value =  { "type", "x", "y", "z", "w" })
@XmlRootElement(name = "Tuple4")
@XmlType(name = "Tuple4")
public class Tuple4<T extends Number>
        extends org.jutility.common.datatype.tuple.Tuple4<T>
        implements ITuple4<T> {



    /**
     * Creates a new instance of the {@link Tuple4} class. (Serialization
     * Constructor)
     */
    protected Tuple4() {

        this(null, null, null, null, null, true);
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
     *            The Y coordinate.
     * @param w
     *            The homogeneous coordinate component.
     * @param type
     *            The type of this tuple.
     */
    public Tuple4(final Number x, final Number y, final Number z,
            final Number w, final Class<? extends T> type) {

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
     *            The Y coordinate.
     * @param w
     *            The homogeneous coordinate component.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    private Tuple4(final Number x, final Number y, final Number z,
            final Number w, Class<? extends T> type, boolean serialization) {

        super(NumberUtils.cast(x, type), NumberUtils.cast(y, type), NumberUtils.cast(z, type), NumberUtils
                .cast(w, type), type, serialization);
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
    public Tuple4(final ITuple4<? extends Number> tupleToCopy,
            Class<? extends T> type) {

        this(tupleToCopy.getX(), tupleToCopy.getY(), tupleToCopy.getZ(),
                tupleToCopy.getW(), type);
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof ITuple4<?>) {
            ITuple4<?> otherTuple = (ITuple4<?>) obj;


            boolean xEquals = NumberComparator.equals(this.getX(), otherTuple.getX());
            boolean yEquals = NumberComparator.equals(this.getY(), otherTuple.getY());
            boolean zEquals = NumberComparator.equals(this.getZ(), otherTuple.getZ());
            boolean wEquals = NumberComparator.equals(this.getW(), otherTuple.getW());


            return xEquals && yEquals && zEquals && wEquals;
        }

        return false;
    }



    @Override
    @JsonIgnore
    public boolean isPoint() {

        return NumberComparator.equals(1, this.getW());
    }

    @Override
    @JsonIgnore
    public boolean isVector() {

        return !this.isPoint();
    }

}
