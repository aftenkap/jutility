package org.jutility.common.datatype.table;


import java.util.Comparator;
import java.util.Iterator;



/**
 * 
 * 
 * @param <T>
 *            the type of values to be contained in the cell.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface ICell<T> {

    /**
     * Returns the row.
     * 
     * @return the row.
     */
    public abstract int getRow();

    /**
     * Sets the row.
     * 
     * @param row
     *            the row.
     */
    public abstract void setRow(final int row);

    /**
     * Returns the column.
     * 
     * @return the column.
     */
    public abstract int getColumn();

    /**
     * Sets the column.
     * 
     * @param column
     *            the column.
     */
    public abstract void setColumn(final int column);

    /**
     * Returns the {@link CellLocation location} of this sell.
     * 
     * @return the {@link CellLocation location} of this sell.
     */
    public abstract CellLocation getLocation();


    /**
     * Sets the {@link CellLocation location} of this sell.
     * 
     * @param location
     *            the {@link CellLocation location} of this sell.
     */
    public abstract void setLocation(final CellLocation location);

    /**
     * Returns the value.
     * 
     * @return the value.
     */
    public abstract T getValue();

    /**
     * Sets the value.
     * 
     * @param value
     *            the value.
     */
    public abstract void setValue(final T value);


    /**
     * A {@link NumberComparator} for {@link ICell Cells} using row-major order.
     * 
     */
    public static Comparator<ICell<?>> rowMajorOrder    = new Comparator<ICell<?>>() {

                                                       @Override
                                                       public int compare(
                                                               ICell<?> lhs,
                                                               ICell<?> rhs) {

                                                           return CellLocation.rowMajorOrder
                                                                   .compare(
                                                                           lhs.getLocation(),
                                                                           rhs.getLocation());
                                                       }
                                                   };


    /**
     * A {@link NumberComparator} for {@link ICell Cells} using column-major order.
     */
    public static Comparator<ICell<?>> columnMajorOrder =

                                                   new Comparator<ICell<?>>() {

                                                       @Override
                                                       public int compare(
                                                               ICell<?> lhs,
                                                               ICell<?> rhs) {

                                                           return CellLocation.columnMajorOrder
                                                                   .compare(
                                                                           lhs.getLocation(),
                                                                           rhs.getLocation());
                                                       }
                                                   };


    /**
     * The {@link ICell.CellValueIterator} class provides a wrapper around the
     * {@link Iterator} of a row or column.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * 
     * @param <V>
     *            the value type of the table cells.
     */
    public static class CellValueIterator<V>
            implements Iterator<V> {

        private final Iterator<? extends ICell<V>> iterator;

        /**
         * Creates a new instance of the {@link ICell.CellValueIterator} class.
         * 
         * @param iterator
         *            the cell iterator to use with this iterator.
         */
        public CellValueIterator(Iterator<? extends ICell<V>> iterator) {

            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {

            return this.iterator.hasNext();
        }

        @Override
        public V next() {

            return this.iterator.next().getValue();
        }

        @Override
        public void remove() {

            this.iterator.remove();
        }
    }
}