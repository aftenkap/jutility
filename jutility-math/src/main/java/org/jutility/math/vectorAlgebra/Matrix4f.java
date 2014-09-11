package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Matrix4f} class provides a convenience implementation of the
 * {@link IMatrix4} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
@XmlRootElement(name = "Matrix4f")
@XmlType(name = "Matrix4f")
public class Matrix4f
        extends Matrix4<Float> {

    /**
     * The unit matrix.
     */
    public static final Matrix4f UNIT_MATRIX = new Matrix4f(
                                                     Vector4f.I_UNIT_VECTOR,
                                                     Vector4f.J_UNIT_VECTOR,
                                                     Vector4f.K_UNIT_VECTOR,
                                                     Point4f.ORIGIN);

    /**
     * Creates a new instance of the {@link Matrix4f} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Matrix4f() {

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
    public Matrix4f(ITuple4<Float> i, ITuple4<Float> j, ITuple4<Float> k,
            ITuple4<Float> s) {

        super(i, j, k, s, Float.class);
    }


    /**
     * Copy Constructor.
     * 
     * @param matrixToCopy
     *            the matrix to copy.
     */
    public Matrix4f(IMatrix4<Float> matrixToCopy) {

        super(matrixToCopy);
    }

}
