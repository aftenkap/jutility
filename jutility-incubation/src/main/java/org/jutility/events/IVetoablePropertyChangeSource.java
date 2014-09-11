/**
 * 
 */
package org.jutility.events;




/**
 * The <CODE>IVetoablePropertyChangeSource</CODE> provides an interface for
 * classes that want to provide other classes with the ability to veto a change
 * in one or multiple of their elements.
 * 
 * Use the implementation of this interface to simplify synchronization of the
 * listener list!
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IVetoablePropertyChangeListener
 * @see IPropertyChangeEvent
 */
public interface IVetoablePropertyChangeSource {

    /**
     * Adds the provided listener to the list of listeners.
     * 
     * @param listener
     *            The listener to be added.
     */
    public abstract void addVetoableDataChangeListener(
            IVetoablePropertyChangeListener listener);


    /**
     * Removes the provided listener from the list of listeners.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public abstract void removeVetoableDataChangeListener(
            IVetoablePropertyChangeListener listener);

    /**
     * Clears all listeners.
     */
    public abstract void clearVetoablePropertyChangeListeners();

    /**
     * Notifies all listeners of an intended change in a property.
     * 
     * @param propertyName
     *            the property name.
     * @param oldValue
     *            the previous value.
     * @param newValue
     *            the new value.
     * 
     * @throws PropertyChangeVetoException
     *             if one of the listeners rejects the change.
     */
    public void fireVetoableDataChangeEvent(String propertyName,
            Object oldValue, Object newValue)
            throws PropertyChangeVetoException;

}
