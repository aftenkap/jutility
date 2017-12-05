package org.jutility.math.vectoralgebra;


//@formatter:off
/*
 * #%L
 * jutility-math
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
//@formatter:on


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberUtils;


/**
 * The generic {@code Matrix4} class provides a reference implementation for the
 * {@link IMatrix4} interface.
 *
 * @param <T>
 *            the {@link Number} type of the {@code Matrix4}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Matrix4")
@XmlType(name = "Matrix4")
public class Matrix4<T extends Number>
        implements IMatrix4<T>, Serializable {


    /**
     * Serial Version UID.
     */
    private static final long        serialVersionUID = 3197138832018316229L;


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


    @Override
    public Class<? extends T> getType() {

        return this.type;
    }


    @Override
    public ITuple4<T> getI() {

        return this.i;
    }


    @Override
    public ITuple4<T> getJ() {

        return this.j;
    }


    @Override
    public ITuple4<T> getK() {

        return this.k;
    }


    @Override
    public ITuple4<T> getS() {

        return this.s;
    }

    /**
     * Creates a new instance of the {@code Matrix4} class. (Serialization
     * Constructor)
     */
    protected Matrix4() {

        this(null, null, null, null, null, true);
    }

    /**
     * Creates a new instance of the {@code Matrix4} class with the provided
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
            final ITuple4<? extends Number> s, final Class<? extends T> type) {

        this(i, j, k, s, type, false);
    }


    /**
     * Creates a new instance of the {@code Matrix4} class with the provided
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
            final ITuple4<? extends Number> s, final Class<? extends T> type,
            final boolean serialization) {

        if ((i == null) && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a matrix with missing I component!");
        }
        if ((j == null) && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a matrix with missing J component!");
        }
        if ((k == null) && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a matrix with missing K component!");
        }
        if ((s == null) && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a matrix with missing S component!");
        }

        if ((type == null) && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a matrix without a type!");
        }

        if (!serialization) {

            this.i = new Tuple4<>(i, type);
            this.j = new Tuple4<>(j, type);
            this.k = new Tuple4<>(k, type);
            this.s = new Tuple4<>(s, type);
        }
        else {

            this.i = null;
            this.j = null;
            this.k = null;
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
    public Matrix4(final IMatrix4<T> matrixToCopy) {

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
    public Matrix4(final IMatrix4<? extends Number> matrixToCopy,
            final Class<? extends T> returnType) {

        this(new Tuple4<T>(matrixToCopy.getI(), returnType), new Tuple4<T>(
                matrixToCopy.getJ(), returnType), new Tuple4<T>(
                matrixToCopy.getK(), returnType), new Tuple4<T>(
                matrixToCopy.getS(), returnType), returnType);
    }



    @Override
    public T[] toColumnMajorArray() {

        @SuppressWarnings("unchecked")
        final T array[] = NumberUtils.createArray(this.type,

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



    @Override
    public T[] toRowMajorArray() {

        @SuppressWarnings("unchecked")
        final T array[] = NumberUtils.createArray(this.type,

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



    @Override
    public Matrix4<T> transpose() {

        final Tuple4<T> it = new Tuple4<>(this.getI()
                .getX(), this.getJ()
                .getX(), this.getK()
                .getX(), this.getS()
                .getX(), this.getType());
        final Tuple4<T> jt = new Tuple4<>(this.getI()
                .getY(), this.getJ()
                .getY(), this.getK()
                .getY(), this.getS()
                .getY(), this.getType());
        final Tuple4<T> kt = new Tuple4<>(this.getI()
                .getZ(), this.getJ()
                .getZ(), this.getK()
                .getZ(), this.getS()
                .getZ(), this.getType());
        final Tuple4<T> st = new Tuple4<>(this.getI()
                .getW(), this.getJ()
                .getW(), this.getK()
                .getW(), this.getS()
                .getW(), this.getType());

        return new Matrix4<>(it, jt, kt, st, this.getType());
    }



    @Override
    public boolean equals(final Object obj) {

        if ((obj != null) && (obj instanceof IMatrix4<?>)) {

            final IMatrix4<?> other = (IMatrix4<?>) obj;

            final boolean sameIComponent = this.getI().equals(other.getI());
            final boolean sameJComponent = this.getJ().equals(other.getJ());
            final boolean sameKComponent = this.getK().equals(other.getK());
            final boolean sameSComponent = this.getS().equals(other.getS());

            return sameIComponent && sameJComponent && sameKComponent
                    && sameSComponent;
        }

        return false;
    }


    @Override
    public int hashCode() {

        int hash = 23;

        hash += 29 * this.getI().hashCode();
        hash += 31 * this.getJ().hashCode();
        hash += 37 * this.getK().hashCode();
        hash += 39 * this.getS().hashCode();

        return hash;
    }


    @Override
    public String toString() {

        final StringBuilder returnValue = new StringBuilder("\n|");
        final Number array[] = new Number[16];

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
            if ((v > 0) && ((v % 4) == 0)) {

                returnValue.append("\t\t|\n|");
            }

            returnValue.append("\t\t")
                    .append(String.format("%.3f", array[v]));
        }

        returnValue.append("\t\t|\n");

        return returnValue.toString();
    }
}
