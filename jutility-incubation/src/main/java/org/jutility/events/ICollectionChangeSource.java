/**
 * 
 */
package org.jutility.events;


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
    public abstract void addCollectionChangeListener(
            ICollectionChangeListener listener);


    /**
     * Removes the provided listener from the list of listeners.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public abstract void removeCollectionChangeListener(
            ICollectionChangeListener listener);


    /**
     * Removes all listeners from the list of listeners.
     */
    public abstract void clearCollectionChangeListeners();


    /**
     * Notifies all listeners of the addition of an element to a collection.
     * 
     * @param collectionName
     *            the name of the collection.
     * @param elementAdded
     *            the element added.
     */
    public abstract void fireElementAddedEvent(String collectionName,
            Object elementAdded);


    /**
     * Notifies all listeners of the removal of an element from a collection.
     * 
     * @param collectionName
     *            the name of the collection.
     * @param removedObject
     *            the element removed.
     */
    public abstract void fireElementRemovedEvent(String collectionName,
            Object removedObject);


    /**
     * Notifies all listeners of the clearing of a collection.
     * 
     * @param collectionName
     *            the name of the collection.
     */
    public abstract void fireCollectionClearedEvent(String collectionName);

}
