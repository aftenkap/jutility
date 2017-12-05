package org.jutility.common.datatype.tuple;


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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The generic {@code ITuple} interface provides a contract for all classes
 * implementing n-dimensional tuples.
 *
 * @param <T>
 *            the type of the tuple.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "tupleType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Tuple.class, name = "Tuple") })
public interface ITuple<T> {

    /**
     * Returns the type of the tuple.
     *
     * @return the type.
     */
    Class<? extends T> getType();

    /**
     * Returns the dimension of this tuple.
     *
     * @return the dimension.
     */
    int getDimension();

    /**
     * Getter for the component at the provided index.
     *
     * @param index
     *            the index of the component to get
     *
     * @return The component with the provided index.
     */
    T get(final int index);

    /**
     * Creates an array representation of the tuple.
     *
     * @return The array representation of the tuple.
     */
    T[] toArray();
}
