/**
 * 
 */
package org.jutility.events;

/*
 * #%L
 * jutility-incubation
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


/**
 * The <CODE>CollectionChangeOperation</CODE> Enum provides all the operation
 * that can be performed on a collection.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public enum CollectionChangeOperation {
    /**
     * The add operation.
     */
    ADD("Adding an element"),
    /**
     * The remove operation.
     */
    REMOVE("Removing an element"),
    /**
     * The clear operation.
     */
    CLEAR("Clearing collection"),
    /**
     * An unknown operation.
     */
    UNKNOWN("Unknown");

    private final String name;


    private CollectionChangeOperation(String name) {

        this.name = name;
    }


    /**
     * Provides a String representation of the Enum.
     * 
     * @return A String representation of the Enum.
     */
    @Override
    public String toString() {

        return this.name;
    }
}
