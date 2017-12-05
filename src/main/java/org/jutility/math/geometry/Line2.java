package org.jutility.math.geometry;


//@formatter:off
/*
* #%L
 * * jutility-math
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * *
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

import org.jutility.math.vectoralgebra.IPoint2;
import org.jutility.math.vectoralgebra.Point2;


/**
 * The generic {@code Line2} class provides a reference implementation of the
 * {@link ILine2} interface.
 * 
 * @param <T>
 *            the {@link Number} type of the {@code Line2}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Line2")
@XmlType(name = "Line2")
public class Line2<T extends Number>
        implements ILine2<T>, Serializable {


    /**
     * Serial Version UID.
     */
    private static final long        serialVersionUID = -7756312621286658016L;


    @XmlAttribute
    private final Class<? extends T> type;

    @XmlElement(name = "Source", type = Point2.class)
    private final IPoint2<T>         source;
    @XmlElement(name = "Sink", type = Point2.class)
    private final IPoint2<T>         sink;

    @Override
    public IPoint2<T> getSource() {

        return this.source;
    }


    @Override
    public Class<? extends T> getType() {

        return this.type;
    }

    @Override
    public IPoint2<T> getSink() {

        return this.sink;
    }


    /**
     * Creates a new instance of the {@code Line2} class. (Serialization
     * Constructor)
     */
    protected Line2() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the {@code Line2} class with the provided type
     * and parameters.
     * 
     * @param source
     *            the source of the line.
     * @param sink
     *            the sink of the line.
     * @param type
     *            the type.
     */
    public Line2(final IPoint2<? extends Number> source,
            final IPoint2<? extends Number> sink, final Class<? extends T> type) {

        this(source, sink, type, false);
    }

    /**
     * Creates a new instance of the {@code Line2} class with the provided type
     * and parameters.
     * 
     * @param source
     *            the source of the line.
     * @param sink
     *            the sink of the line.
     * @param type
     *            the type.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public Line2(final IPoint2<? extends Number> source,
            final IPoint2<? extends Number> sink,
            final Class<? extends T> type, final boolean serialization) {

        if (source == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a line without a source point!");
        }
        if (sink == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a line without a sink point!");
        }
        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a line without a type!");

        }

        if (source != null && type != null) {
            this.source = new Point2<>(source, type);
        }
        else {
            this.source = null;
        }
        if (sink != null && type != null) {
            this.sink = new Point2<>(sink, type);
        }
        else {
            this.sink = null;
        }

        this.type = type;
    }


    /**
     * Copy Constructor.
     * 
     * @param lineToCopy
     *            the line to copy.
     */
    public Line2(final ILine2<T> lineToCopy) {

        this(lineToCopy, lineToCopy.getType());
    }

    /**
     * Copy Constructor.
     * 
     * @param lineToCopy
     *            the line to copy.
     * @param type
     *            the desired type of the line to copy.
     */
    public Line2(final ILine2<? extends Number> lineToCopy,
            final Class<? extends T> type) {

        this(lineToCopy.getSource(), lineToCopy.getSink(), type);
    }


    @Override
    public String toString() {

        return "Line2 from " + this.getSource() + " to " + this.getSink();
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj != null && obj instanceof ILine2<?>) {
            ILine2<?> other = (ILine2<?>) obj;

            boolean sameSource = this.getSource().equals(other.getSource());
            boolean sameSink = this.getSink().equals(other.getSink());

            return sameSource && sameSink;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;

        hash += 11 * this.getSource().hashCode();
        hash += 13 * this.getSink().hashCode();

        return hash;
    }
}
