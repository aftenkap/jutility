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

        this.listeners = new LinkedList<>();
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
