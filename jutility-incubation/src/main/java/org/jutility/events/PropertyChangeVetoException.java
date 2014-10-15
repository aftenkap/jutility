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
 * The <CODE>PropertyChangeVetoException</CODE> is a reference implementation of an
 * <CODE>IPropertyChangeVetoException</CODE>.
 * 
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see Exception
 * @see IPropertyChangeEvent
 */
public class PropertyChangeVetoException
        extends Exception
        implements IPropertyChangeVetoException {


    private static final long serialVersionUID = 1L;

    private final IPropertyChangeEvent vetoedEvent;


    @Override
    public IPropertyChangeEvent getVetoedEvent() {

        return this.vetoedEvent;
    }


    /**
     * Constructs a new <CODE>PropertyChangeVetoException</CODE> with a detailed
     * message and the vetoed event.
     * 
     * @param message
     *            The detailed message.
     * @param vetoedEvent
     *            The vetoed event.
     */
    public PropertyChangeVetoException(String message, IPropertyChangeEvent vetoedEvent) {

        super(message);

        if (vetoedEvent == null) {
            throw new IllegalArgumentException(
                    "PropertyChangeVetoException needs to specify the vetoed event!");
        }

        this.vetoedEvent = vetoedEvent;

    }


    /**
     * Provides a String representation of this exception.
     * 
     * @return a String representation of this exception.
     */
    @Override
    public String toString() {

        String returnValue = "Attempted change is to be rejected due to a veto:\n";
        returnValue += "\tReason: " + this.getMessage() + "\n";

        returnValue += "\tVetoed Change:\n\t" + this.vetoedEvent;

        return returnValue;
    }

}
