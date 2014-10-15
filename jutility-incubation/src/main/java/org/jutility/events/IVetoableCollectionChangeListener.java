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
 * The <CODE>IVetoableCollectionChangeListener</CODE> interface provides an interface
 * for listeners who are interested in getting notified before a change in a
 * collection is applied for a chance to veto that change.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see EventListener
 * @see ICollectionChangeEvent
 */
public interface IVetoableCollectionChangeListener
        extends EventListener {

    /**
     * This event is fired if an element is added to the collection.
     * 
     * @param event
     *            The event.
     * @throws CollectionChangeVetoException
     *             if the change is to be rejected.
     */
    public void vetoableElementAdded(ICollectionChangeEvent event)
            throws CollectionChangeVetoException;


    /**
     * This event is fired if an element is removed fromthe collection.
     * 
     * @param event
     *            The event.
     * @throws CollectionChangeVetoException
     *             if the change is to be rejected.
     */
    public void vetoableElementRemoved(ICollectionChangeEvent event)
            throws CollectionChangeVetoException;


    /**
     * This event is fired if a collection is cleared.
     * 
     * @param event
     *            The event.
     * @throws CollectionChangeVetoException
     *             if the change is to be rejected.
     */
    public void vetoableCollectionCleared(ICollectionChangeEvent event)
            throws CollectionChangeVetoException;
}
