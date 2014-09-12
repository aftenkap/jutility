/**
 * 
 */
package org.jutility.events;


import java.util.LinkedList;
import java.util.List;


/**
 * The <CODE>VetoablePropertyChangeSupport</CODE> class provides a reference
 * implementation of the <CODE>IVetoablePropertyChangeSource</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IVetoablePropertyChangeSource
 */
public class VetoablePropertyChangeSupport
        implements IVetoablePropertyChangeSource {

    private final Object                                source;
    private final List<IVetoablePropertyChangeListener> listeners;


    /**
     * Creates a new <CODE>DataChangeSource</CODE>.
     * 
     * @param source
     *            the source of the events.
     */
    public VetoablePropertyChangeSupport(Object source) {

        this.listeners = new LinkedList<IVetoablePropertyChangeListener>();
        this.source = source;
    }


    @Override
    public synchronized void addVetoableDataChangeListener(
            IVetoablePropertyChangeListener listener) {

        this.listeners.add(listener);
    }


    @Override
    public synchronized void removeVetoableDataChangeListener(
            IVetoablePropertyChangeListener listener) {

        this.listeners.remove(listener);
    }

    @Override
    public synchronized void clearVetoablePropertyChangeListeners() {

        this.listeners.clear();
    }

    @Override
    public synchronized void fireVetoableDataChangeEvent(String propertyName,
            Object oldValue, Object newValue)
            throws PropertyChangeVetoException {

        PropertyChangeEvent event = new PropertyChangeEvent(source,
                propertyName, oldValue, newValue);

        for (IVetoablePropertyChangeListener listener : this.listeners) {
            listener.vetoableDataChange(event);
        }
    }

}
