package org.jutility.common.datatype.tuple;


import org.jutility.common.datatype.tuple.ITuple;

import com.fasterxml.jackson.annotation.JsonSubTypes;


/**
 * The generic {@link ITuple2} interface provides a contract for all classes
 * implementing two-dimensional tuples.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the tuple.
 */
@JsonSubTypes({
    @JsonSubTypes.Type(value = Tuple2.class, name = "Tuple2")
})
public interface ITuple2<T>
        extends ITuple<T> {


    /**
     * Getter for the X coordinate.
     * 
     * @return The X coordinate.
     */
    public abstract T getX();


    /**
     * Getter for the Y coordinate.
     * 
     * @return The Y coordinate.
     */
    public abstract T getY();
}
