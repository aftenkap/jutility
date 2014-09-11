package org.jutility.math.vectorAlgebra;



/**
 * The generic {@link ITuple4} interface provides a contract for all classes
 * implementing four-dimensional numeric tuples.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the tuple.
 */
public interface ITuple4<T extends Number>
        extends org.jutility.common.datatype.tuple.ITuple4<T> {


    /**
     * Determines whether the homogeneous (w) coordinate is 1, signifying a
     * point.
     * 
     * @return <code>true</code>, if the homogeneous coordinate is 1;
     *         <code>false</code> otherwise.
     */
    public abstract boolean isPoint();

    /**
     * Determines whether the homogeneous (w) coordinate is not equal to 1,
     * signifying a vector.
     * 
     * @return <code>true</code>, if the homogeneous coordinate is not 1;
     *         <code>false</code> otherwise.
     */
    public abstract boolean isVector();
}
