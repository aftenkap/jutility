/**
 * 
 */
package org.jutility.events;


import java.util.EventListener;




/**
 * The <CODE>IVetoableCollectionChangeListener</CODE> interface provides an interface
 * for listeners who are interested in getting notified before a change in a
 * collection is applied for a chance to veto that change.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see EventListener
 * @see ICollectionChangeEvent
 */
public interface IVetoableCollectionChangeListener
        extends EventListener {

    /**
     * This event is fired if an element is added to the collection.
     * 
     * @param event
     *            The event.
     * @throws CollectionChangeVetoException
     *             if the change is to be rejected.
     */
    public void vetoableElementAdded(ICollectionChangeEvent event)
            throws CollectionChangeVetoException;


    /**
     * This event is fired if an element is removed fromthe collection.
     * 
     * @param event
     *            The event.
     * @throws CollectionChangeVetoException
     *             if the change is to be rejected.
     */
    public void vetoableElementRemoved(ICollectionChangeEvent event)
            throws CollectionChangeVetoException;


    /**
     * This event is fired if a collection is cleared.
     * 
     * @param event
     *            The event.
     * @throws CollectionChangeVetoException
     *             if the change is to be rejected.
     */
    public void vetoableCollectionCleared(ICollectionChangeEvent event)
            throws CollectionChangeVetoException;
}
