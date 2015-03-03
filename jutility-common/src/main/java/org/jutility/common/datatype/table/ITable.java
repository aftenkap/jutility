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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;



/**
 * The generic {@code ITable} interface provides a contract for classes modeling
 * a two-dimensional table of arbitrary data.
 *
 * @param <T>
 *            the type of the table data.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public interface ITable<T>
        extends Iterable<T> {

    /**
     * Returns the {@link IterationOrder} of the {@link Table}.
     *
     * @return the {@link IterationOrder} of the {@link Table}.
     */
    public abstract IterationOrder getIterationOrder();

    /**
     * Sets the {@link IterationOrder} of the {@link Table}.
     *
     * @param iterationOrder
     *            the new {@link IterationOrder}.
     */
    public abstract void setIterationOrder(IterationOrder iterationOrder);

    /**
     * Adds the value to the table in the specified row and column.
     *
     * @param row
     *            the table row.
     * @param column
     *            the table column.
     * @param value
     *            the value to set.
     * @return {@code true} if the table did not already contain the value;
     *         {@code false} otherwise.
     */
    public abstract boolean add(int row, int column, T value);

    /**
     * Returns the value in the cell with the specified row and column.
     *
     * @param row
     *            the row.
     * @param column
     *            the column.
     * @return the value in the cell or {@code null} if no value exists.
     */
    public abstract T get(int row, int column);

    /**
     * Returns the value in the cell with the specified location.
     *
     * @param location
     *            the location.
     * @return the value in the cell or {@code null} if no value exists.
     */
    public abstract T get(CellLocation location);


    /**
     * Returns the rows of this {@link Table}.
     *
     * @return the rows of this {@link Table}.
     */
    public abstract Collection<? extends List<T>> getRows();

    /**
     * Returns the row with the provided index.
     *
     * @param index
     *            the row index.
     * @return the row with the provided index or {@code null}, if the table
     *         does not contain a row with the provided index.
     */
    public abstract List<T> getRow(int index);

    /**
     * Removes the row with the provided index.
     *
     * @param index
     *            the row index.
     * @return the removed row or {@code null}, if the table did not contain a
     *         row with the provided index.
     */
    public abstract List<T> removeRow(int index);

    /**
     * Returns the columns of this {@code ITable}.
     *
     * @return the columns of this {@code ITable}.
     */
    public abstract Collection<? extends List<T>> getColumns();

    /**
     * Returns the {@link CellContainer Column} with the provided index.
     *
     * @param index
     *            the column index.
     * @return the column with the provided index or {@code null}, if the table
     *         does not contain a column with the provided index.
     */
    public abstract List<T> getColumn(int index);

    /**
     * Removes the column with the provided index.
     *
     * @param index
     *            the column index.
     * @return the removed column or {@code null}, if the table did not contain
     *         a column with the provided index.
     */
    public abstract List<T> removeColumn(int index);

    /**
     * Returns the {@link ICell cell} values of this {@code ITable}.
     *
     * @return the {@link ICell cell} values.
     */
    public abstract List<T> getValues();

    /**
     * Removes the cell at the specified row and column from the table.
     *
     * @param row
     *            the table row.
     * @param column
     *            the table column.
     * @return {@code true}, if the table contained the cell; {@code false}
     *         otherwise.
     */
    public abstract boolean remove(int row, int column);


    /**
     * Returns the {@link CellRange} of this {@link ITable Table}.
     *
     * @return the {@link CellRange} of this {@link ITable Table}.
     */
    public abstract CellRange cellRange();

    /**
     * Returns the number of rows of the table.
     *
     * @return the number of rows of the table.
     */
    public abstract int rows();

    /**
     * Returns the number of columns of the table.
     *
     * @return the number of columns of the table.
     */
    public abstract int columns();

    /**
     * Returns the size of the table (the number of cells).
     *
     * @return the size of the table.
     */
    public abstract int size();

    /**
     * Returns an {@link Iterator} over the values of the table with row-major
     * order.
     *
     * @return an {@link Iterator} over the values of the table with row-major
     *         order.
     */
    public abstract Iterator<T> rowMajorOrderIterator();

    /**
     * Returns an {@link Iterator} over the values of the table with
     * column-major order.
     *
     * @return an {@link Iterator} over the values of the table with
     *         column-major order.
     */
    public abstract Iterator<T> columnMajorOrderIterator();
}