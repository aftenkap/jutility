package org.jutility.events;


import java.util.EventObject;




/**
 * The <CODE>CollectionChangeEvent</CODE> class provides a reference
 * implementation of the <CODE>ICollectionChangeEvent</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see ICollectionChangeEvent
 * @see EventObject
 */
public class CollectionChangeEvent
        extends EventObject
        implements ICollectionChangeEvent {

    private static final long serialVersionUID = 1L;

    private final String collectionName;
    private final Object causeOfChange;
    private final CollectionChangeOperation operation;


    @Override
    public String getCollectionName() {

        return this.collectionName;
    }


    @Override
    public Object getCauseOfChange() {

        return this.causeOfChange;
    }


    @Override
    public CollectionChangeOperation getOperation() {

        return this.operation;
    }


    /**
     * Constructs a new <CODE>CollectionChangeEvent</CODE> with the provided
     * collection name, source, and cause of change. The operation, however, is
     * unknown.
     * 
     * @param collectionName
     *            The collection name.
     * @param source
     *            The source of the change.
     * @param causeOfChange
     *            The cause of the change.
     */
    public CollectionChangeEvent(Object source, String collectionName,
            Object causeOfChange) {

        this(source, collectionName, causeOfChange,
                CollectionChangeOperation.UNKNOWN);
    }


    /**
     * Constructs a new <CODE>CollectionChangeEvent</CODE> with the provided
     * collection name, source,cause of change, and operation.
     * 
     * @param source
     *            The source of the change.
     * @param collectionName
     *            The collection name.
     * @param causeOfChange
     *            The cause of the change.
     * @param operation
     *            The operation.
     */
    public CollectionChangeEvent(Object source, String collectionName,
            Object causeOfChange, CollectionChangeOperation operation) {

        super(source);

        this.collectionName = collectionName;
        this.causeOfChange = causeOfChange;
        this.operation = operation;
    }


    /**
     * Provides a String representation of the event.
     * 
     * @return a string representation of the event.
     */
    @Override
    public String toString() {

        String returnValue = super.toString();

        if (this.source != null) {

            returnValue = "From: <" + this.source.toString() + ">\n";

            if (this.collectionName != null) {

                returnValue += "\tTo: " + this.collectionName + "\n";
            }

            if (this.operation != null) {
                returnValue += "\tOperation: " + this.operation.toString()
                        + "\n";
            }

            if (this.causeOfChange != null) {
                returnValue += "\tElement: " + this.causeOfChange.toString()
                        + "\n";
            }

        }
        return returnValue;
    }
}
