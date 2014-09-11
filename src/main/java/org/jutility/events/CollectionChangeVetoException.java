/**
 * 
 */
package org.jutility.events;





/**
 * The <CODE>CollectionChangeVetoException</CODE> is a reference implementation
 * of an <CODE>ICollectionChangeVetoException</CODE>.
 * 
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see Exception
 * @see ICollectionChangeEvent
 */
public class CollectionChangeVetoException
        extends Exception
        implements ICollectionChangeVetoException {

    private static final long serialVersionUID = 1L;

    private final ICollectionChangeEvent vetoedEvent;


    @Override
    public ICollectionChangeEvent getVetoedEvent() {

        return this.vetoedEvent;
    }


    /**
     * Constructs a new <CODE>CollectionChangeVetoException</CODE> with the
     * provided detailed message and the vetoed event.
     * 
     * @param message
     *            The detailed reason for this exception.
     * @param vetoedEvent
     *            The vetoed event.
     * @throws IllegalArgumentException
     *             if the collectionName is null.
     */
    public CollectionChangeVetoException(String message,
            ICollectionChangeEvent vetoedEvent) {

        super(message);

        if (vetoedEvent == null) {
            throw new IllegalArgumentException(
                    "CollectionChangeVetoException needs to specify the vetoed event!");
        }

        this.vetoedEvent = vetoedEvent;
    }


    /**
     * Provides a String representation of this exception.
     * 
     * @return a String representation of this exception.
     */
    @Override
    public String toString() {

        String returnValue = "Attempted change is to be rejected due to a veto:\n";
        returnValue += "\tReason: " + this.getMessage() + "\n";

        returnValue += "\tVetoed Change:\n\t" + this.vetoedEvent;

        return returnValue;
    }
}
