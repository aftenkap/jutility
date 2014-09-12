package org.jutility.common.datatype.tuple;


import org.jutility.common.datatype.tuple.ITuple;


/**
 * The generic {@link ITuple4} interface provides a contract for all classes
 * implementing four-dimensional tuples.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the tuple.
 */
public interface ITuple4<T>
        extends ITuple<T> {


    /**
     * Getter for the X component.
     * 
     * @return The X component.
     */
    public abstract T getX();


    /**
     * Getter for the Y component.
     * 
     * @return The Y component.
     */
    public abstract T getY();


    /**
     * Getter for the Z component.
     * 
     * @return The Z component.
     */
    public abstract T getZ();


    /**
     * Getter for the W component.
     * 
     * @return The W component.
     */
    public abstract T getW();
}
