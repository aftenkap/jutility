package org.jutility.math.vectorAlgebra;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberUtils;


/**
 * The generic {@link Matrix4} class provides a reference implementation for the
 * {@link IMatrix4} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the matrix.
 * 
 */
@XmlRootElement(name = "Matrix4")
@XmlType(name = "Matrix4")
public class Matrix4<T extends Number>
        implements IMatrix4<T> {

    @XmlAttribute
    private final Class<? extends T> type;

    @XmlElement(name = "IComponent", type = Tuple4.class)
    private final ITuple4<T>         i;
    @XmlElement(name = "JComponent", type = Tuple4.class)
    private final ITuple4<T>         j;
    @XmlElement(name = "KComponent", type = Tuple4.class)
    private final ITuple4<T>         k;
    @XmlElement(name = "SComponent", type = Tuple4.class)
    private final ITuple4<T>         s;


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.vectorAlgebra.IMatrix4#getType()
     */
    @Override
    public Class<? extends T> getType() {

        return this.type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.vectorAlgebra.IMatrix4#getI()
     */
    @Override
    public ITuple4<T> getI() {

        return this.i;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.vectorAlgebra.IMatrix4#getJ()
     */
    @Override
    public ITuple4<T> getJ() {

        return this.j;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.vectorAlgebra.IMatrix4#getK()
     */
    @Override
    public ITuple4<T> getK() {

        return this.k;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.vectorAlgebra.IMatrix4#getS()
     */
    @Override
    public ITuple4<T> getS() {

        return this.s;
    }

    /**
     * Creates a new instance of the {@link Matrix4} class. (Serialization
     * Constructor)
     */
    protected Matrix4() {

        this(null, null, null, null, null, true);
    }

    /**
     * Creates a new instance of the {@link Matrix4} class with the provided
     * type and provided columns.
     * 
     * @param i
     *            The first column of the matrix.
     * @param j
     *            The second column of the matrix.
     * @param k
     *            The third column of the matrix.
     * @param s
     *            The fourth column of the matrix.
     * @param type
     *            the type of the matrix.
     */
    public Matrix4(final ITuple4<? extends Number> i,
            final ITuple4<? extends Number> j,
            final ITuple4<? extends Number> k,
            final ITuple4<? extends Number> s, Class<? extends T> type) {

        this(i, j, k, s, type, false);
    }


    /**
     * Creates a new instance of the {@link Matrix4} class with the provided
     * type and provided columns.
     * 
     * @param i
     *            The first column of the matrix.
     * @param j
     *            The second column of the matrix.
     * @param k
     *            The third column of the matrix.
     * @param s
     *            The fourth column of the matrix.
     * @param type
     *            the type of the matrix.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected Matrix4(final ITuple4<? extends Number> i,
            final ITuple4<? extends Number> j,
            final ITuple4<? extends Number> k,
            final ITuple4<? extends Number> s, Class<? extends T> type,
            boolean serialization) {

        if (i == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a matrix with missing I component!");
        }
        if (j == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a matrix with missing J component!");
        }
        if (k == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a matrix with missing K component!");
        }
        if (s == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a matrix with missing S component!");
        }

        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a matrix without a type!");
        }

        if (i != null && type != null) {
            this.i = new Tuple4<T>(i, type);
        }
        else {
            this.i = null;
        }

        if (j != null && type != null) {
            this.j = new Tuple4<T>(j, type);
        }
        else {
            this.j = null;
        }

        if (k != null && type != null) {
            this.k = new Tuple4<T>(k, type);
        }
        else {
            this.k = null;
        }

        if (s != null && type != null) {
            this.s = new Tuple4<T>(s, type);
        }
        else {
            this.s = null;
        }

        this.type = type;
    }


    /**
     * Copy Constructor.
     * 
     * @param matrixToCopy
     *            the matrix to copy.
     */
    public Matrix4(IMatrix4<T> matrixToCopy) {

        this(matrixToCopy, matrixToCopy.getType());
    }

    /**
     * Copy Constructor.
     * 
     * @param matrixToCopy
     *            the matrix to copy.
     * @param returnType
     *            the desired return type.
     */
    public Matrix4(IMatrix4<? extends Number> matrixToCopy,
            Class<? extends T> returnType) {

        this(new Tuple4<T>(matrixToCopy.getI(), returnType), new Tuple4<T>(
                matrixToCopy.getJ(), returnType), new Tuple4<T>(
                matrixToCopy.getK(), returnType), new Tuple4<T>(
                matrixToCopy.getS(), returnType), returnType);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.vectorAlgebra.IMatrix4#toColumnMajorArray()
     */
    @Override
    public T[] toColumnMajorArray() {

        @SuppressWarnings("unchecked")
        T array[] = NumberUtils.createArray(

        this.getI().getX(), this.getI().getY(), this.getI().getZ(), this.getI()
                .getW(),

        this.getJ().getX(), this.getJ().getY(), this.getJ().getZ(), this.getJ()
                .getW(),

        this.getK().getX(), this.getK().getY(), this.getK().getZ(), this.getK()
                .getW(),

        this.getS().getX(), this.getS().getY(), this.getS().getZ(), this.getS()
                .getW()

        );

        return array;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.vectorAlgebra.IMatrix4#toRowMajorArray()
     */
    @Override
    public T[] toRowMajorArray() {

        @SuppressWarnings("unchecked")
        T array[] = NumberUtils.createArray(

        this.getI().getX(), this.getJ().getX(), this.getK().getX(), this.getS()
                .getX(),

        this.getI().getY(), this.getJ().getY(), this.getK().getY(), this.getS()
                .getY(),

        this.getI().getZ(), this.getJ().getZ(), this.getK().getZ(), this.getS()
                .getZ(),

        this.getI().getW(), this.getJ().getW(), this.getK().getW(), this.getS()
                .getW()

        );

        return array;
    }



    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.vectorAlgebra.IMatrix4#transpose()
     */
    @Override
    public Matrix4<T> transpose() {

        Tuple4<T> it = new Tuple4<T>(this.getI().getX(), this.getJ().getX(),
                this.getK().getX(), this.getS().getX(), this.getType());
        Tuple4<T> jt = new Tuple4<T>(this.getI().getY(), this.getJ().getY(),
                this.getK().getY(), this.getS().getY(), this.getType());
        Tuple4<T> kt = new Tuple4<T>(this.getI().getZ(), this.getJ().getZ(),
                this.getK().getZ(), this.getS().getZ(), this.getType());
        Tuple4<T> st = new Tuple4<T>(this.getI().getW(), this.getJ().getW(),
                this.getK().getW(), this.getS().getW(), this.getType());

        return new Matrix4<T>(it, jt, kt, st, this.getType());
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        String returnValue = "\n|";
        Object array[] = new Object[16];

        array[0] = this.getI().getX();
        array[1] = this.getJ().getX();
        array[2] = this.getK().getX();
        array[3] = this.getS().getX();

        array[4] = this.getI().getY();
        array[5] = this.getJ().getY();
        array[6] = this.getK().getY();
        array[7] = this.getS().getY();

        array[8] = this.getI().getZ();
        array[9] = this.getJ().getZ();
        array[10] = this.getK().getZ();
        array[11] = this.getS().getZ();

        array[12] = this.getI().getW();
        array[13] = this.getJ().getW();
        array[14] = this.getK().getW();
        array[15] = this.getS().getW();


        for (int v = 0; v < 16; v++) {
            if (v > 0 && v % 4 == 0) {
                returnValue += "\t\t|\n|";
            }
            returnValue += "\t\t" + String.format("%.3f", array[v]);
        }

        returnValue += "\t\t|\n";

        return returnValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof IMatrix4<?>) {

            IMatrix4<?> other = (IMatrix4<?>) obj;

            boolean sameIComponent = this.getI().equals(other.getI());
            boolean sameJComponent = this.getJ().equals(other.getJ());
            boolean sameKComponent = this.getK().equals(other.getK());
            boolean sameSComponent = this.getS().equals(other.getS());

            return sameIComponent && sameJComponent && sameKComponent
                    && sameSComponent;
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hash = 23;

        hash += 29 * this.getI().hashCode();
        hash += 31 * this.getJ().hashCode();
        hash += 37 * this.getK().hashCode();
        hash += 39 * this.getS().hashCode();

        return hash;
    }
}
