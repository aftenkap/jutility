package org.jutility.math.vectorAlgebra;



/**
 * The generic {@link ITuple2} interface provides a contract for all classes
 * implementing two-dimensional numeric tuples.
 * <p/>
 * This is a tagging interface.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the tuple.
 */
public interface ITuple2<T extends Number>
        extends org.jutility.common.datatype.tuple.ITuple2<T> {

    /**
     * Returns whether the tuple was intended to be a point.
     * 
     * @return <code>true</code>, if the tuple was intended to be a point;
     *         <code>false</code> otherwise.
     */
    public abstract boolean isPoint();

    /**
     * Returns whether the tuple was intended to be a vector.
     * 
     * @return <code>true</code>, if the tuple was intended to be a vector;
     *         <code>false</code> otherwise.
     */
    public abstract boolean isVector();
}
