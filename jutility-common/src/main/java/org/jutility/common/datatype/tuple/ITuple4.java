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
 * The generic {@code ITuple4} interface provides a contract for all classes
 * implementing four-dimensional tuples.
 *
 * @param <T>
 *            the type of the tuple.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "tupleType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Tuple4.class, name = "Tuple4") })
public interface ITuple4<T>
        extends ITuple<T> {


    /**
     * Returns the X component.
     *
     * @return The X component.
     */
    T getX();


    /**
     * Returns the Y component.
     *
     * @return The Y component.
     */
    T getY();


    /**
     * Returns the Z component.
     *
     * @return The Z component.
     */
    T getZ();


    /**
     * Returns the W component.
     *
     * @return The W component.
     */
    T getW();
}
