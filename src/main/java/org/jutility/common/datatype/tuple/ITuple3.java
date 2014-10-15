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


import org.jutility.common.datatype.tuple.ITuple;

import com.fasterxml.jackson.annotation.JsonSubTypes;


/**
 * The generic {@link ITuple3} interface provides a contract for all classes
 * implementing three-dimensional tuples.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the tuple.
 */
@JsonSubTypes({
    @JsonSubTypes.Type(value = Tuple3.class, name = "Tuple3")
})
public interface ITuple3<T>
        extends ITuple<T> {


    /**
     * Getter for the X coordinate.
     * 
     * @return The X coordinate.
     */
    public abstract T getX();


    /**
     * Getter for the Y coordinate.
     * 
     * @return The Y coordinate.
     */
    public abstract T getY();


    /**
     * Getter for the Z coordinate.
     * 
     * @return The Z coordinate.
     */
    public abstract T getZ();


}
