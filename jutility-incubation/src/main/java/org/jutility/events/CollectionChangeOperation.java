/**
 * 
 */
package org.jutility.events;


/**
 * The <CODE>CollectionChangeOperation</CODE> Enum provides all the operation
 * that can be performed on a collection.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public enum CollectionChangeOperation {
    /**
     * The add operation.
     */
    ADD("Adding an element"),
    /**
     * The remove operation.
     */
    REMOVE("Removing an element"),
    /**
     * The clear operation.
     */
    CLEAR("Clearing collection"),
    /**
     * An unknown operation.
     */
    UNKNOWN("Unknown");

    private final String name;


    private CollectionChangeOperation(String name) {

        this.name = name;
    }


    /**
     * Provides a String representation of the Enum.
     * 
     * @return A String representation of the Enum.
     */
    @Override
    public String toString() {

        return this.name;
    }
}
