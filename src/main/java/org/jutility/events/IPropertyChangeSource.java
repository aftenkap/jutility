/**
 * 
 */
package org.jutility.events;

/*
 * #%L
 * jutility-incubation
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


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
