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


import java.util.EventListener;


/**
 * The <CODE>IPropertyChangeListener</CODE> interface provides an interface
 * for listeners who are interested in getting notified of a change in a
 * property.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 * @see EventListener
 * @see IPropertyChangeEvent
 */
public interface IPropertyChangeListener
        extends EventListener {

    /**
     * This event is fired if the property is changed.
     *
     * @param event
     *            The event.
     */
    void propertyChanged(IPropertyChangeEvent event);


}
