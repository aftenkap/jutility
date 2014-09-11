/**
 * 
 */
package org.jutility.events;


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

        this.listeners = new LinkedList<ICollectionChangeListener>();
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
