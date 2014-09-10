package org.jutility.math.vectorAlgebra;


/**
 * The {@link IMatrix4} interface provides a contract for all classes
 * implementing 4x4 matrices using four-dimensional {@link ITuple4 Tuples}.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the matrix
 */
public interface IMatrix4<T extends Number> {

    /**
     * Returns the type of this matrix.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();

    /**
     * Getter for the first column of the matrix.
     * 
     * @return The first column of the matrix.
     */
    public abstract ITuple4<T> getI();


    /**
     * Getter for the second column of the matrix.
     * 
     * @return The second column of the matrix.
     */
    public abstract ITuple4<T> getJ();


    /**
     * Getter for the third column of the matrix.
     * 
     * @return The third column of the matrix.
     */
    public abstract ITuple4<T> getK();


    /**
     * Getter for the fourth column of the matrix.
     * 
     * @return The fourth column of the matrix.
     */
    public abstract ITuple4<T> getS();


    /**
     * Returns this matrix in a column-major array.
     * 
     * @return the matrix in a column-major array.
     */
    public abstract T[] toColumnMajorArray();


    /**
     * Returns this matrix in a row-major array.
     * 
     * @return the matrix in a row-major array.
     */
    public abstract T[] toRowMajorArray();

    /**
     * Returns the transpose matrix of this instance.
     * 
     * @return the transpose matrix.
     */
    public abstract IMatrix4<T> transpose();

}
