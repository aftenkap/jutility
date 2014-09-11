package org.jutility.common.datatype.table;

/**
 * The {@link IterationOrder} enumeration determines iteration order
 * of a {@link Table}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public enum IterationOrder {

    /**
     * Iteration as a row-major table.
     */
    ROW_MAJOR,
    /**
     * Iteration as a column-major table.
     */
    COLUMN_MAJOR;
}