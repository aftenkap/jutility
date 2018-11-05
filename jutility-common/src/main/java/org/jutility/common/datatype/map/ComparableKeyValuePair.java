package org.jutility.common.datatype.map;


// @formatter:off
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
// @formatter:on

import javax.xml.bind.annotation.XmlType;


/**
 * The generic {@code ComparableKeyValuePair} class provides a pairwise mapping
 * between keys and values where the keys are {@link Comparable comparable}.
 *
 * @param <KEY>
 *            The key type.
 * @param <VALUE>
 *            The value type.
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlType(name = "ComparableKeyValuePair")
public class ComparableKeyValuePair<KEY extends Comparable<KEY>, VALUE>
        extends KeyValuePair<KEY, VALUE>
        implements Comparable<ComparableKeyValuePair<KEY, VALUE>> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -3898170300715575747L;


    /**
     * Creates a new instance of the {@code ComparableKeyValuePair} class.
     * (Serialization Constructor)
     */
    @SuppressWarnings("unused")
    private ComparableKeyValuePair() {

        super();
    }

    /**
     * Creates a new instance of the {@code ComparableKeyValuePair} class with
     * the provided key.
     *
     * @param key
     *            the key.
     */
    public ComparableKeyValuePair(final KEY key) {

        super(key);
    }

    /**
     * Creates a new instance of the {@code ComparableKeyValuePair} class with
     * the provided key and value.
     *
     * @param key
     *            the key.
     * @param value
     *            the value.
     */
    public ComparableKeyValuePair(final KEY key, final VALUE value) {

        super(key, value);
    }

    @Override
    public int compareTo(final ComparableKeyValuePair<KEY, VALUE> other) {

        return this.getKey().compareTo(other.getKey());
    }
}
