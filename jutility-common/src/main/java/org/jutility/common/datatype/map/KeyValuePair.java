package org.jutility.common.datatype.map;


//@formatter:off
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

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The generic {@code KeyValuePair} class provides a pairwise mapping between
 * keys and values.
 *
 * @param <KEY>
 *         The key type.
 * @param <VALUE>
 *         The value type.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "KeyValuePair")
@XmlType(name = "KeyValuePair")
@XmlAccessorType(XmlAccessType.NONE)
public class KeyValuePair<KEY, VALUE>
        implements Serializable {


    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -2177494404526358079L;


    @XmlElement private final KEY key;

    @XmlElement private VALUE value;


    /**
     * Returns the key.
     *
     * @return the key.
     */
    public KEY getKey() {

        return this.key;
    }

    /**
     * Returns the value.
     *
     * @return the value.
     */
    public VALUE getValue() {

        return this.value;
    }

    /**
     * Sets the value to the value provided.
     *
     * @param value
     *         the new value.
     */
    public void setValue(final VALUE value) {

        this.value = value;
    }

    /**
     * Creates a new instance of the {@code KeyValuePair} class. (Serialization
     * Constructor)
     */
    protected KeyValuePair() {

        this(null, null, true);
    }

    /**
     * Creates a new instance of the {@code KeyValuePair} class with the
     * provided key.
     *
     * @param key
     *         the key.
     */
    public KeyValuePair(final KEY key) {

        this(key, null);
    }

    /**
     * Creates a new instance of the {@code KeyValuePair} class with the
     * provided key and value.
     *
     * @param key
     *         the key.
     * @param value
     *         the value.
     */
    public KeyValuePair(final KEY key, final VALUE value) {

        this(key, value, false);
    }

    /**
     * Creates a new instance of the {@code KeyValuePair} class with the
     * provided key and value.
     *
     * @param key
     *         the key.
     * @param value
     *         the value.
     * @param serialization
     *         whether or not the constructor is used during serialization.
     */
    private KeyValuePair(final KEY key, final VALUE value,
                         final boolean serialization) {

        if ((key == null) && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create key-value pair " + "without key!");
        }

        this.key = key;
        this.value = value;
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {

            return true;
        }
        if ((obj != null) && (obj instanceof KeyValuePair<?, ?>)) {

            final KeyValuePair<?, ?> other = (KeyValuePair<?, ?>) obj;

            final boolean sameKey = this.getKey()
                                        .equals(other.getKey());

            final boolean sameValue = (this.getValue() == other.getValue()) || (
                    (this.getValue() != null) && this.getValue()
                                                     .equals(other.getValue()));

            return sameKey && sameValue;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash += 83 * (this.getKey() != null ?  this.getKey().hashCode() : 0);
        hash += 87 * (this.getValue() != null ? this.getValue().hashCode() :  0);
        return hash;
    }

    @Override
    public String toString() {

        String string = this.getKey()
                            .toString() + ": ";
        if (this.getValue() != null) {

            string += this.getValue()
                          .toString();
        }
        else {
            string += "<null>";
        }
        return string;
    }
}
