package org.jutility.common.datatype.tuple;

import com.fasterxml.jackson.annotation.JsonSubTypes;


/**
 * The generic {@link ITuple} interface provides a contract for all classes
 * implementing n-dimensional tuples.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the tuple.
 */
@JsonSubTypes({
    @JsonSubTypes.Type(value = Tuple.class, name = "Tuple")
})
public interface ITuple<T> {

    /**
     * Returns the type of the tuple.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();

    /**
     * Returns the dimension of this tuple.
     * 
     * @return the dimension.
     */
    public abstract int getDimension();

    /**
     * Getter for the component at the provided index.
     * 
     * @param index
     *            the index of the component to get
     * 
     * @return The component with the provided index.
     */
    public abstract T get(int index);

    /**
     * Creates an array representation of the tuple.
     * 
     * @return The array representation of the tuple.
     */
    public abstract T[] toArray();

}
