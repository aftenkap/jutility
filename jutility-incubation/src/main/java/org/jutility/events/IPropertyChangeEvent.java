/**
 * 
 */
package org.jutility.events;


/**
 * The <CODE>IPropertyChangeEvent</CODE> provides an interface for events that
 * notify listeners of changes in a single piece of data.
 * 
 * To that end, they provide the event source, the name of the data, the
 * old and new value of the data.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IPropertyChangeEvent {


    /**
     * Getter for the source of the event.
     * 
     * <b>Note:</b> Since the actual event is a subclass of
     * <CODE>EventObject</CODE>, this method does not need to be implemented!
     * 
     * @return The source of the event.
     */
    public Object getSource();


    /**
     * Gets the new value for the property, expressed as an Object.
     * 
     * @return The new value for the property, expressed as an Object. May be
     *         null if multiple properties have changed.
     */
    public Object getNewValue();


    /**
     * Gets the old value for the property, expressed as an Object.
     * 
     * @return The old value for the property, expressed as an Object. May be
     *         null if multiple properties have changed.
     */
    public Object getOldValue();


    /**
     * Gets the programmatic name of the property that was changed.
     * 
     * @return The programmatic name of the property that was changed. May be
     *         null if multiple properties have changed.
     */
    public String getPropertyName();


}
