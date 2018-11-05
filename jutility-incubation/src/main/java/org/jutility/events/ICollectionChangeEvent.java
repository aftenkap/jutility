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
 * The <CODE>ICollectionChangeEvent</CODE> provides an interface for events that
 * notify listeners of changes in a collection.
 *
 * To that end, they provide the event source, the name of the collection, the
 * cause of the change, as well as the operation attempted.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 * @see java.util.EventObject
 */
public interface ICollectionChangeEvent {


    /**
     * Getter for the source of the event.
     *
     * <b>Note:</b> Since the actual event is a subclass of
     * <CODE>EventObject</CODE>, this method does not need to be implemented!
     *
     * @return The source of the event.
     */
    Object getSource();


    /**
     * Getter for the name of the collection.
     *
     * @return the name of the changed collection.
     */
    String getCollectionName();


    /**
     * Getter for the cause of the change.
     *
     * @return The cause of the change. May be null if the collection was
     *         cleared.
     */
    Object getCauseOfChange();


    /**
     * Getter for the operation.
     *
     * @return The operation on the collection.
     */
    CollectionChangeOperation getOperation();

}
