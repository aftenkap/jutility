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

        this.listeners = new LinkedList<>();
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
