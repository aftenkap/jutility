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
 * The <CODE>CollectionChangeEvent</CODE> class provides a reference
 * implementation of the <CODE>ICollectionChangeEvent</CODE> interface.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 * @see ICollectionChangeEvent
 * @see EventObject
 */
public class CollectionChangeEvent
        extends EventObject
        implements ICollectionChangeEvent {

    private static final long serialVersionUID = 1L;

    private final String collectionName;
    private final Object causeOfChange;
    private final CollectionChangeOperation operation;


    @Override
    public String getCollectionName() {

        return this.collectionName;
    }


    @Override
    public Object getCauseOfChange() {

        return this.causeOfChange;
    }


    @Override
    public CollectionChangeOperation getOperation() {

        return this.operation;
    }


    /**
     * Constructs a new <CODE>CollectionChangeEvent</CODE> with the provided
     * collection name, source, and cause of change. The operation, however, is
     * unknown.
     *
     * @param collectionName
     *            The collection name.
     * @param source
     *            The source of the change.
     * @param causeOfChange
     *            The cause of the change.
     */
    public CollectionChangeEvent(Object source, String collectionName,
            Object causeOfChange) {

        this(source, collectionName, causeOfChange,
                CollectionChangeOperation.UNKNOWN);
    }


    /**
     * Constructs a new <CODE>CollectionChangeEvent</CODE> with the provided
     * collection name, source,cause of change, and operation.
     *
     * @param source
     *            The source of the change.
     * @param collectionName
     *            The collection name.
     * @param causeOfChange
     *            The cause of the change.
     * @param operation
     *            The operation.
     */
    public CollectionChangeEvent(Object source, String collectionName,
            Object causeOfChange, CollectionChangeOperation operation) {

        super(source);

        this.collectionName = collectionName;
        this.causeOfChange = causeOfChange;
        this.operation = operation;
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

            returnValue = "From: <" + this.source.toString() + ">\n";

            if (this.collectionName != null) {

                returnValue += "\tTo: " + this.collectionName + "\n";
            }

            if (this.operation != null) {
                returnValue += "\tOperation: " + this.operation.toString()
                        + "\n";
            }

            if (this.causeOfChange != null) {
                returnValue += "\tElement: " + this.causeOfChange.toString()
                        + "\n";
            }

        }
        return returnValue;
    }
}
