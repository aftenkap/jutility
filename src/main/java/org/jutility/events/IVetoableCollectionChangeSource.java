/**
 * 
 */
package org.jutility.events;





/**
 * The <CODE>IVetoableCollectionChangeSource</CODE> provides an interface for
 * classes that want to provide other classes with the ability to veto a change
 * in one or multiple of their collections.
 * 
 * Use the implementation of this interface to simplify synchronization of the
 * listener list!
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IVetoableCollectionChangeListener
 * @see ICollectionChangeEvent
 */
public interface IVetoableCollectionChangeSource {

    /**
     * Adds the provided listener to the list of listeners.
     * 
     * @param listener
     *            The listener to be added.
     */
    public abstract void addVetoableCollectionChangeListener(
            IVetoableCollectionChangeListener listener);


    /**
     * Removes the provided listener from the list of listeners.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public abstract void removeVetoableCollectionChangeListener(
            IVetoableCollectionChangeListener listener);

    /**
     * Removes all listeners from the list of listeners.
     */
    public abstract void clearVetoableCollectionChangeListeners();

    /**
     * Notifies all listeners of the event.
     * 
     * @param collectionName
     *            the name of the collection.
     * @param elementAdded
     *            the element added.
     * @throws CollectionChangeVetoException
     *             if the change is rejected.
     */
    public abstract void fireVetoableElementAddedEvent(String collectionName,
            Object elementAdded)
            throws CollectionChangeVetoException;


    /**
     * Notifies all listeners of the event.
     * 
     * @param collectionName
     *            the name of the collection.
     * @param removedObject
     *            the element removed.
     * @throws CollectionChangeVetoException
     *             if the change is rejected.
     */
    public void fireVetoableElementRemovedEvent(String collectionName,
            Object removedObject)
            throws CollectionChangeVetoException;


    /**
     * Notifies all listeners of the event.
     * 
     * @param collectionName
     *            the name of the collection.
     * @throws CollectionChangeVetoException
     *             if the change is rejected.
     */
    public void fireVetoableCollectionClearedEvent(String collectionName)
            throws CollectionChangeVetoException;

}
