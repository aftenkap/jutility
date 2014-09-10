/**
 * 
 */
package org.jutility.events;


import java.util.EventListener;


/**
 * The <CODE>IPropertyChangeListener</CODE> interface provides an interface
 * for listeners who are interested in getting notified of a change in a
 * property.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see EventListener
 * @see IPropertyChangeEvent
 */
public interface IPropertyChangeListener
        extends EventListener {

    /**
     * This event is fired if the property is changed.
     * 
     * @param event
     *            The event.
     */
    public void propertyChanged(IPropertyChangeEvent event);


}
