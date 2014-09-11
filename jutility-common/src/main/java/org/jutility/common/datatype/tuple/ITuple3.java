package org.jutility.common.datatype.tuple;


import org.jutility.common.datatype.tuple.ITuple;


/**
 * The generic {@link ITuple3} interface provides a contract for all classes
 * implementing three-dimensional tuples.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the tuple.
 */
public interface ITuple3<T>
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


    /**
     * Getter for the Z coordinate.
     * 
     * @return The Z coordinate.
     */
    public abstract T getZ();


}
