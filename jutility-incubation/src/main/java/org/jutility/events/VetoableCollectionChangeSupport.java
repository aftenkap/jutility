/**
 * 
 */
package org.jutility.events;


import java.util.LinkedList;
import java.util.List;


/**
 * The <CODE>VetoableCollectionChangeSupport</CODE> class provides a reference
 * implementation of the <CODE>IVetoableCollectionChangeSource</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IVetoableCollectionChangeSource
 */
public class VetoableCollectionChangeSupport
        implements IVetoableCollectionChangeSource {


    private final Object                                  source;
    private final List<IVetoableCollectionChangeListener> listeners;


    /**
     * Creates a new <CODE>VetoableCollectionChangeSupport</CODE>.
     * 
     * @param source
     *            the source of the events.
     */
    public VetoableCollectionChangeSupport(Object source) {

        this.listeners = new LinkedList<IVetoableCollectionChangeListener>();
        this.source = source;
    }


    @Override
    public synchronized void addVetoableCollectionChangeListener(
            IVetoableCollectionChangeListener listener) {

        this.listeners.add(listener);
    }


    @Override
    public synchronized void removeVetoableCollectionChangeListener(
            IVetoableCollectionChangeListener listener) {

        this.listeners.remove(listener);
    }

    @Override
    public void clearVetoableCollectionChangeListeners() {

        this.listeners.clear();
    }


    @Override
    public synchronized void fireVetoableElementAddedEvent(
            String collectionName, Object addedElement)
            throws CollectionChangeVetoException {

        CollectionChangeEvent event = new CollectionChangeEvent(source,
                collectionName, addedElement, CollectionChangeOperation.ADD);

        for (IVetoableCollectionChangeListener listener : this.listeners) {
            listener.vetoableElementAdded(event);
        }
    }


    @Override
    public synchronized void fireVetoableElementRemovedEvent(
            String collectionName, Object removedElement)
            throws CollectionChangeVetoException {

        CollectionChangeEvent event = new CollectionChangeEvent(source,
                collectionName, removedElement,
                CollectionChangeOperation.REMOVE);

        for (IVetoableCollectionChangeListener listener : this.listeners) {
            listener.vetoableElementRemoved(event);
        }
    }


    @Override
    public synchronized void fireVetoableCollectionClearedEvent(
            String collectionName)
            throws CollectionChangeVetoException {

        CollectionChangeEvent event = new CollectionChangeEvent(source,
                collectionName, null, CollectionChangeOperation.CLEAR);

        for (IVetoableCollectionChangeListener listener : this.listeners) {
            listener.vetoableCollectionCleared(event);
        }
    }

}
