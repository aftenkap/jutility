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
 * The <CODE>ICollectionChangeListener</CODE> interface provides an interface
 * for listeners who are interested in getting notified of a change in a
 * collection.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see EventListener
 * @see ICollectionChangeEvent
 */
public interface ICollectionChangeListener
        extends EventListener {

    /**
     * This event is fired if an element is added to the collection.
     * 
     * @param event
     *            The event.
     */
    public void elementAdded(ICollectionChangeEvent event);


    /**
     * This event is fired if an element is removed fromthe collection.
     * 
     * @param event
     *            The event.
     */
    public void elementRemoved(ICollectionChangeEvent event);


    /**
     * This event is fired if a collection is cleared.
     * 
     * @param event
     *            The event.
     */
    public void collectionCleared(ICollectionChangeEvent event);
}
