/**
 * 
 */
package org.jutility.events;


/**
 * The <CODE>IPropertyChangeSource</CODE> provides an interface for classes that
 * want to notify other classes of a change in one or multiple of their
 * elements.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IPropertyChangeListener
 * @see IPropertyChangeEvent
 */
public interface IPropertyChangeSource {

    /**
     * Adds the provided listener to the list of listeners.
     * 
     * @param listener
     *            The listener to be added.
     */
    public abstract void addPropertyChangeListener(
            IPropertyChangeListener listener);


    /**
     * Removes the provided listener from the list of listeners.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public abstract void removePropertyChangeListener(
            IPropertyChangeListener listener);

    /**
     * Clears all listeners.
     */
    public abstract void clearPropertyChangeListeners();

    /**
     * Notifies all listeners of the change in a property.
     * 
     * @param propertyName
     *            the property name.
     * @param oldValue
     *            the previous value.
     * @param newValue
     *            the new value.
     */
    public abstract void firePropertyChangeEvent(String propertyName,
            Object oldValue, Object newValue);

}
