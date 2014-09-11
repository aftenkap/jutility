/**
 * 
 */
package org.jutility.events;





/**
 * The <CODE>IPropertyChangeVetoException</CODE> models an exception that is thrown
 * when a change to a piece of data is vetoed.
 * 
 * It provides access to the vetoed event.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IPropertyChangeVetoException {


    /**
     * Getter for the vetoed event.
     * 
     * @return The event that was vetoed.
     */
    public IPropertyChangeEvent getVetoedEvent();
}
