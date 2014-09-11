/**
 * 
 */
package org.jutility.events;


import java.util.EventListener;




/**
 * The <CODE>IVetoablePropertyChangeListener</CODE> interface provides an interface
 * for listeners who are interested in getting notified before a change in a
 * piece of data is applied for a chance to veto that change.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see EventListener
 * @see IPropertyChangeEvent
 */
public interface IVetoablePropertyChangeListener
        extends EventListener {

    /**
     * This event is fired if the data is changed.
     * 
     * @param event
     *            The event.
     * @throws PropertyChangeVetoException
     *             if the change is vetoed.
     */
    public void vetoableDataChange(IPropertyChangeEvent event)
            throws PropertyChangeVetoException;


}
