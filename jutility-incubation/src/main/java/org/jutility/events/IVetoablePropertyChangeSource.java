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
    void addVetoableDataChangeListener(
            IVetoablePropertyChangeListener listener);


    /**
     * Removes the provided listener from the list of listeners.
     *
     * @param listener
     *            The listener to be removed.
     */
    void removeVetoableDataChangeListener(
            IVetoablePropertyChangeListener listener);

    /**
     * Clears all listeners.
     */
    void clearVetoablePropertyChangeListeners();

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
    void fireVetoableDataChangeEvent(String propertyName,
            Object oldValue, Object newValue)
            throws PropertyChangeVetoException;

}
