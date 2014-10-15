package org.jutility.common.datatype.table;

/*
 * #%L
 * jutility-common
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
     * A {@link Comparator} for {@link ICell Cells} using row-major order.
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
     * A {@link Comparator} for {@link ICell Cells} using column-major order.
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