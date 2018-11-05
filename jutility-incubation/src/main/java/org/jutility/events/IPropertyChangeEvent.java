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
    Object getSource();


    /**
     * Gets the new value for the property, expressed as an Object.
     *
     * @return The new value for the property, expressed as an Object. May be
     *         null if multiple properties have changed.
     */
    Object getNewValue();


    /**
     * Gets the old value for the property, expressed as an Object.
     *
     * @return The old value for the property, expressed as an Object. May be
     *         null if multiple properties have changed.
     */
    Object getOldValue();


    /**
     * Gets the programmatic name of the property that was changed.
     *
     * @return The programmatic name of the property that was changed. May be
     *         null if multiple properties have changed.
     */
    String getPropertyName();


}
