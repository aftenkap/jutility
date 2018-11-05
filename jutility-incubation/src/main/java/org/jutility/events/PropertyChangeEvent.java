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


import java.util.EventObject;




/**
 * The <CODE>PropertyChangeEvent</CODE> class provides a reference implementation of
 * the <CODE>IPropertyChangeEvent</CODE> interface.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 * @see IPropertyChangeEvent
 * @see EventObject
 */
public class PropertyChangeEvent
        extends EventObject
        implements IPropertyChangeEvent {

    private static final long serialVersionUID = 1L;

    private final String propertyName;
    private final Object oldValue;
    private final Object newValue;


    @Override
    public Object getNewValue() {

        return this.newValue;
    }


    @Override
    public Object getOldValue() {

        return this.oldValue;
    }


    @Override
    public String getPropertyName() {

        return this.propertyName;
    }


    /**
     * Constructs a new <CODE>IPropertyChangeEvent</CODE>.
     *
     * @param source
     *            The object that fired the event.
     * @param propertyName
     *            The programmatic name of the property that was changed.
     * @param oldValue
     *            The old value of the property.
     * @param newValue
     *            The new value of the property.
     */
    public PropertyChangeEvent(Object source, String propertyName, Object oldValue,
            Object newValue) {

        super(source);

        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;

    }


    /**
     * Provides a String representation of the event.
     *
     * @return a string representation of the event.
     */
    @Override
    public String toString() {

        String returnValue = super.toString();

        if (this.source != null) {

            returnValue = "From: <" + this.source.toString() + ">";

            returnValue += " requested a change: \n";

            if (this.propertyName != null) {

                returnValue += "\tTo: " + this.propertyName + "\n";
            }

            if (this.oldValue != null) {
                returnValue += "\told value: " + this.oldValue.toString()
                        + "\n";
            }

            if (this.newValue != null) {
                returnValue += "\tnew value: " + this.newValue.toString()
                        + "\n";
            }
        }
        return returnValue;
    }
}
