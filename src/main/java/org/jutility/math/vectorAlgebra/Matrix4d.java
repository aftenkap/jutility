package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Matrix4d} class provides a convenience implementation of the
 * {@link IMatrix4} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Matrix4d")
@XmlType(name = "Matrix4d")
public class Matrix4d
        extends Matrix4<Double> {

    /**
     * The unit matrix.
     */
    public static final Matrix4d UNIT_MATRIX = new Matrix4d(
                                                     Vector4d.I_UNIT_VECTOR,
                                                     Vector4d.J_UNIT_VECTOR,
                                                     Vector4d.K_UNIT_VECTOR,
                                                     Point4d.ORIGIN);

    /**
     * Creates a new instance of the {@link Matrix4d} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Matrix4d() {

        super();
    }

    /**
     * Creates a new instance of the {@link Matrix4} class with the provided
     * columns.
     * 
     * @param i
     *            The first column of the matrix.
     * @param j
     *            The second column of the matrix.
     * @param k
     *            The third column of the matrix.
     * @param s
     *            The fourth column of the matrix.
     */
    public Matrix4d(ITuple4<Double> i, ITuple4<Double> j, ITuple4<Double> k,
            ITuple4<Double> s) {

        super(i, j, k, s, Double.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param matrixToCopy
     *            the matrix to copy.
     */
    public Matrix4d(IMatrix4<Double> matrixToCopy) {

        super(matrixToCopy);
    }

}
