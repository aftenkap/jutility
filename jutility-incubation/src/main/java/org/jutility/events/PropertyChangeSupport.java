/**
 * 
 */
package org.jutility.events;


import java.util.LinkedList;
import java.util.List;


/**
 * The <CODE>DataChangeSource</CODE> class provides a reference implementation
 * of the <CODE>IPropertyChangeSource</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IPropertyChangeSource
 */
public class PropertyChangeSupport
        implements IPropertyChangeSource {


    private final List<IPropertyChangeListener> listeners;
    private final Object                        source;


    /**
     * Creates a new <CODE>DataChangeSource</CODE>.
     * 
     * @param source
     *            the source of the events.
     */
    public PropertyChangeSupport(Object source) {

        this.listeners = new LinkedList<IPropertyChangeListener>();
        this.source = source;
    }


    @Override
    public synchronized void addPropertyChangeListener(
            IPropertyChangeListener listener) {

        this.listeners.add(listener);
    }


    @Override
    public synchronized void removePropertyChangeListener(
            IPropertyChangeListener listener) {

        this.listeners.remove(listener);
    }

    @Override
    public synchronized void clearPropertyChangeListeners() {

        this.listeners.clear();

    }

    @Override
    public synchronized void firePropertyChangeEvent(String propertyName,
            Object oldValue, Object newValue) {

        PropertyChangeEvent event = new PropertyChangeEvent(source,
                propertyName, oldValue, newValue);

        for (IPropertyChangeListener listener : this.listeners) {
            listener.propertyChanged(event);
        }
    }

}
