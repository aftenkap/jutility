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
 * The <CODE>ICollectionChangeSource</CODE> provides an interface for classes
 * that want to notify other classes of a change in one or multiple of their
 * collections.
 *
 * Use the implementation of this interface to simplify synchronization of the
 * listener list!
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 * @see ICollectionChangeListener
 * @see ICollectionChangeEvent
 */
public interface ICollectionChangeSource {

    /**
     * Adds the provided listener to the list of listeners.
     *
     * @param listener
     *            The listener to be added.
     */
    void addCollectionChangeListener(
            ICollectionChangeListener listener);


    /**
     * Removes the provided listener from the list of listeners.
     *
     * @param listener
     *            The listener to be removed.
     */
    void removeCollectionChangeListener(
            ICollectionChangeListener listener);


    /**
     * Removes all listeners from the list of listeners.
     */
    void clearCollectionChangeListeners();


    /**
     * Notifies all listeners of the addition of an element to a collection.
     *
     * @param collectionName
     *            the name of the collection.
     * @param elementAdded
     *            the element added.
     */
    void fireElementAddedEvent(String collectionName,
            Object elementAdded);


    /**
     * Notifies all listeners of the removal of an element from a collection.
     *
     * @param collectionName
     *            the name of the collection.
     * @param removedObject
     *            the element removed.
     */
    void fireElementRemovedEvent(String collectionName,
            Object removedObject);


    /**
     * Notifies all listeners of the clearing of a collection.
     *
     * @param collectionName
     *            the name of the collection.
     */
    void fireCollectionClearedEvent(String collectionName);

}
