/**
 * 
 */
package org.jutility.events;




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
    public Object getSource();


    /**
     * Getter for the name of the collection.
     * 
     * @return the name of the changed collection.
     */
    public String getCollectionName();


    /**
     * Getter for the cause of the change.
     * 
     * @return The cause of the change. May be null if the collection was
     *         cleared.
     */
    public Object getCauseOfChange();


    /**
     * Getter for the operation.
     * 
     * @return The operation on the collection.
     */
    public CollectionChangeOperation getOperation();

}
