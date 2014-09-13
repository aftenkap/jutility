package org.jutility.common.datatype.tuple;


import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;



/**
 * The generic {@link TupleBase} class provides a reference base implementation
 * of the {@link ITuple} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @param <T>
 *            the type of the tuple.
 */
@JsonSubTypes({
    @JsonSubTypes.Type(value = Tuple.class, name = "Tuple"),
    @JsonSubTypes.Type(value = Tuple2.class, name = "Tuple2"),
    @JsonSubTypes.Type(value = Tuple3.class, name = "Tuple3"),
    @JsonSubTypes.Type(value = Tuple4.class, name = "Tuple4")
})
@XmlRootElement(name = "TupleBase")
@XmlType(name = "TupleBase")
public abstract class TupleBase<T>
        implements ITuple<T> {

    @XmlAttribute
    private final Class<? extends T> type;

    private final List<T>            components;


    /**
     * Returns the components of this tuple.
     * 
     * @return the components.
     */
    protected List<T> getComponents() {

        return this.components;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple#getType()
     */
    @Override
    public Class<? extends T> getType() {

        return this.type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple#getDimension()
     */
    @Override
    @JsonIgnore
    public int getDimension() {

        if (this.components != null) {
            return this.components.size();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple#get(int)
     */
    @Override
    public T get(int index) {

        return this.components.get(index);
    }



    /**
     * Creates a new instance of the {@link TupleBase} class. (Serialization
     * Constructor)
     */
    protected TupleBase() {

        this(null, null, true);
    }

    /**
     * Creates a new instance of the {@link TupleBase} class with the provided
     * type and values.
     * 
     * @param components
     *            The components of this tuple.
     * @param type
     *            The type of this tuple.
     */
    @SafeVarargs
    protected TupleBase(final Class<? extends T> type, T... components) {

        this(components, type, false);
    }

    /**
     * Creates a new instance of the {@link TupleBase} class with the provided
     * type and values.
     * 
     * @param components
     *            The components of this tuple.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    @SafeVarargs
    protected TupleBase(final Class<? extends T> type,
            final boolean serialization, T... components) {

        this(components, type, serialization);
    }

    /**
     * Creates a new instance of the {@link TupleBase} class with the provided
     * type and values.
     * 
     * @param components
     *            The components of this tuple.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected TupleBase(final T[] components, Class<? extends T> type,
            boolean serialization) {

        if (components == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a tuple without components!");
        }
        if (type == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a tuple without a type!");
        }

        this.components = new LinkedList<T>();

        if (components != null && type != null) {
            for (Object component : components) {
                if (type.isAssignableFrom(component.getClass())) {
                    this.components.add(type.cast(component));
                }
                else {
                    throw new IllegalArgumentException(
                            "Cannot assign a value of type "
                                    + component.getClass()
                                    + " to a Tuple of type " + type + "!");
                }
            }
        }

        this.type = type;
    }

    /**
     * Copy Constructor.
     * 
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public TupleBase(final ITuple<T> tupleToCopy) {

        this(tupleToCopy.toArray(), tupleToCopy.getType(), false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        StringBuilder returnValue = new StringBuilder();

        returnValue.append("(");

        int i = 0;
        for (T component : this.components) {
            if (i > 0) {
                returnValue.append(", ");
            }
            returnValue.append(component);
            i++;
        }

        returnValue.append(")");

        return returnValue.toString();
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.tuple.ITuple#toArray()
     */
    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {

        if (!this.components.isEmpty()) {
            
            this.components.toArray(TupleBase.createArray(this.get(0)));
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof ITuple<?>) {
            ITuple<?> otherTuple = (ITuple<?>) obj;

            boolean sameDimension = this.getDimension() == otherTuple
                    .getDimension();

            if (sameDimension) {

                for (int i = 0; i < this.getDimension(); i++) {

                    boolean componentEquals = this.get(i).equals(
                            otherTuple.get(i));

                    if (!componentEquals) {
                        return false;
                    }

                }
                return true;
            }
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hash = 7;

        for (T component : this.components) {
            hash += 13 * component.hashCode();
        }

        return hash;
    }

    /**
     * Creates an array of a generic type from the provided elements.
     * 
     * @param elements
     *            the elements comprising the desired array.
     * @return an array containing all elements.
     */
    @SafeVarargs
    private static <T> T[] createArray(final T... elements) {

        return elements;
    }
}
