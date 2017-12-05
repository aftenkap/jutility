package org.jutility.common.datatype.table;


// @formatter:off
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
// @formatter:on

import java.util.Comparator;
import java.util.Iterator;



/**
 * The generic {@code ICell} interface provides a contract for cells of a given
 * value type.
 *
 * @param <T>
 *         the type of values to be contained in the cell.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public interface ICell<T> {

    /**
     * Returns the row.
     *
     * @return the row.
     */
    int getRow();

    /**
     * Sets the row.
     *
     * @param row
     *         the row.
     */
    void setRow(final int row);

    /**
     * Returns the column.
     *
     * @return the column.
     */
    int getColumn();

    /**
     * Sets the column.
     *
     * @param column
     *         the column.
     */
    void setColumn(final int column);

    /**
     * Returns the {@link CellLocation location} of this sell.
     *
     * @return the {@link CellLocation location} of this sell.
     */
    CellLocation getLocation();


    /**
     * Sets the {@link CellLocation location} of this sell.
     *
     * @param location
     *         the {@link CellLocation location} of this sell.
     */
    void setLocation(final CellLocation location);

    /**
     * Returns the value.
     *
     * @return the value.
     */
    T getValue();

    /**
     * Sets the value.
     *
     * @param value
     *         the value.
     */
    void setValue(final T value);


    /**
     * A {@link Comparator} for {@link ICell Cells} using row-major order.
     */
    Comparator<ICell<?>> rowMajorOrder = (lhs, rhs) ->
            CellLocation.rowMajorOrder.compare(
            lhs.getLocation(), rhs.getLocation());


    /**
     * A {@link Comparator} for {@link ICell Cells} using column-major order.
     */
    Comparator<ICell<?>> columnMajorOrder =

            (lhs, rhs) -> CellLocation.columnMajorOrder.compare(
                    lhs.getLocation(), rhs.getLocation());



    /**
     * The {@code ICell.CellValueIterator} class provides a wrapper around the
     * {@link Iterator} of a row or column.
     *
     * @param <V>
     *         the value type of the table cells.
     *
     * @author Peter J. Radics
     * @version 0.1.2
     * @since 0.1.0
     */
    class CellValueIterator<V>
            implements Iterator<V> {

        private final Iterator<? extends ICell<V>> iterator;

        /**
         * Creates a new instance of the {@code ICell.CellValueIterator} class.
         *
         * @param iterator
         *         the cell iterator to use with this iterator.
         */
        public CellValueIterator(final Iterator<? extends ICell<V>> iterator) {

            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {

            return this.iterator.hasNext();
        }

        @Override
        public V next() {

            return this.iterator.next()
                    .getValue();
        }

        @Override
        public void remove() {

            this.iterator.remove();
        }
    }
}
