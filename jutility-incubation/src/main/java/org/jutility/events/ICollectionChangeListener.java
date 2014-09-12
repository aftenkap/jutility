/**
 * 
 */
package org.jutility.events;


import java.util.EventListener;


/**
 * The <CODE>ICollectionChangeListener</CODE> interface provides an interface
 * for listeners who are interested in getting notified of a change in a
 * collection.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see EventListener
 * @see ICollectionChangeEvent
 */
public interface ICollectionChangeListener
        extends EventListener {

    /**
     * This event is fired if an element is added to the collection.
     * 
     * @param event
     *            The event.
     */
    public void elementAdded(ICollectionChangeEvent event);


    /**
     * This event is fired if an element is removed fromthe collection.
     * 
     * @param event
     *            The event.
     */
    public void elementRemoved(ICollectionChangeEvent event);


    /**
     * This event is fired if a collection is cleared.
     * 
     * @param event
     *            The event.
     */
    public void collectionCleared(ICollectionChangeEvent event);
}
