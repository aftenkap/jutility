package org.jutility.math.geometry;


import org.jutility.common.datatype.tuple.ITuple4;


/**
 * The {@link IRotationBase} interface provides a contract for classes implementing
 * axis-angle rotations.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            The type of the rotation
 */
public interface IRotationBase<T> {

    /**
     * Returns the type of this rotation.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();

    /**
     * Returns the rotation axis.
     * 
     * @return the rotation axis.
     */
    public ITuple4<T> getAxis();


    /**
     * Returns the rotation angle.
     * 
     * @return the rotation angle.
     */
    public T getAngle();
}
