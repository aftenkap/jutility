package org.jutility.events;





/**
 * The <CODE>PropertyChangeVetoException</CODE> is a reference implementation of an
 * <CODE>IPropertyChangeVetoException</CODE>.
 * 
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see Exception
 * @see IPropertyChangeEvent
 */
public class PropertyChangeVetoException
        extends Exception
        implements IPropertyChangeVetoException {


    private static final long serialVersionUID = 1L;

    private final IPropertyChangeEvent vetoedEvent;


    @Override
    public IPropertyChangeEvent getVetoedEvent() {

        return this.vetoedEvent;
    }


    /**
     * Constructs a new <CODE>PropertyChangeVetoException</CODE> with a detailed
     * message and the vetoed event.
     * 
     * @param message
     *            The detailed message.
     * @param vetoedEvent
     *            The vetoed event.
     */
    public PropertyChangeVetoException(String message, IPropertyChangeEvent vetoedEvent) {

        super(message);

        if (vetoedEvent == null) {
            throw new IllegalArgumentException(
                    "PropertyChangeVetoException needs to specify the vetoed event!");
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
