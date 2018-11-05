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


import java.util.LinkedList;
import java.util.List;


/**
 * The <CODE>CollectionChangeSupport</CODE> class provides a reference
 * implementation of the <CODE>ICollectionChangeSource</CODE> interface.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 * @see ICollectionChangeSource
 */
public class CollectionChangeSupport
        implements ICollectionChangeSource {


    private final Object                          source;
    private final List<ICollectionChangeListener> listeners;


    /**
     * Creates a new <CODE>CollectionChangeSupport</CODE>.
     *
     * @param source
     *            the source of the events.
     */
    public CollectionChangeSupport(Object source) {

        this.source = source;

        this.listeners = new LinkedList<>();
    }


    @Override
    public synchronized void addCollectionChangeListener(
            ICollectionChangeListener listener) {

        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }


    @Override
    public synchronized void removeCollectionChangeListener(
            ICollectionChangeListener listener) {

        this.listeners.remove(listener);
    }

    @Override
    public synchronized void clearCollectionChangeListeners() {

        this.listeners.clear();
    }


    @Override
    public synchronized void fireElementAddedEvent(String collectionName,
            Object addedElement) {

        CollectionChangeEvent event = new CollectionChangeEvent(source,
                collectionName, addedElement, CollectionChangeOperation.ADD);

        for (ICollectionChangeListener listener : this.listeners) {
            listener.elementAdded(event);
        }
    }


    @Override
    public synchronized void fireElementRemovedEvent(String collectionName,
            Object removedElement) {

        CollectionChangeEvent event = new CollectionChangeEvent(source,
                collectionName, removedElement,
                CollectionChangeOperation.REMOVE);

        for (ICollectionChangeListener listener : this.listeners) {
            listener.elementRemoved(event);
        }
    }


    @Override
    public synchronized void fireCollectionClearedEvent(String collectionName) {

        CollectionChangeEvent event = new CollectionChangeEvent(source,
                collectionName, null, CollectionChangeOperation.CLEAR);

        for (ICollectionChangeListener listener : this.listeners) {
            listener.collectionCleared(event);
        }
    }

}
