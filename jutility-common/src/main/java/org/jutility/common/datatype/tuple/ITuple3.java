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


/**
 * The generic {@link ITuple3} interface provides a contract for all classes
 * implementing three-dimensional tuples.
 *
 * @param <T>
 *            the type of the tuple.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonSubTypes({ @JsonSubTypes.Type(value = Tuple3.class, name = "Tuple3") })
public interface ITuple3<T>
        extends ITuple<T> {


    /**
     * Returns the X coordinate.
     *
     * @return The X coordinate.
     */
    T getX();


    /**
     * Returns the Y coordinate.
     *
     * @return The Y coordinate.
     */
    T getY();


    /**
     * Returns the Z coordinate.
     *
     * @return The Z coordinate.
     */
    T getZ();
}
