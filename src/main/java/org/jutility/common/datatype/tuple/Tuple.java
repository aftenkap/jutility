package org.jutility.common.datatype.tuple;

/*
 * #%L
 * jutility-common
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


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The generic {@link Tuple} class provides a reference base implementation of
 * the {@link ITuple} interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @param <T>
 *            the type of the tuple.
 */
@XmlRootElement(name = "Tuple")
@XmlType(name = "Tuple")
public class Tuple<T>
        extends TupleBase<T>
        implements ITuple<T> {


    @Override
    @XmlElementWrapper(name = "Components")
    @XmlElement(name = "Component")
    protected List<T> getComponents() {

        return super.getComponents();
    }

    /**
     * Creates a new instance of the {@link Tuple} class. (Serialization
     * Constructor)
     */
    protected Tuple() {

        this(null, null, true);
    }

    /**
     * Creates a new instance of the {@link Tuple} class with the provided type
     * and values.
     * 
     * @param components
     *            The components of this tuple.
     * @param type
     *            The type of this tuple.
     */
    @SafeVarargs
    public Tuple(final Class<? extends T> type, T... components) {

        this(components, type, false);
    }

    /**
     * Creates a new instance of the {@link Tuple} class with the provided type
     * and values.
     * 
     * @param components
     *            The components of this tuple.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    @SafeVarargs
    protected Tuple(final Class<? extends T> type, final boolean serialization,
            T... components) {

        this(components, type, serialization);
    }

    /**
     * Creates a new instance of the {@link Tuple} class with the provided type
     * and values.
     * 
     * @param components
     *            The components of this tuple.
     * @param type
     *            The type of this tuple.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected Tuple(final T[] components, Class<? extends T> type,
            boolean serialization) {

        super(components, type, serialization);
    }

    /**
     * Copy Constructor.
     * 
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public Tuple(final ITuple<T> tupleToCopy) {

        this(tupleToCopy.toArray(), tupleToCopy.getType(), false);
    }
}
