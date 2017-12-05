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



/**
 * The generic {@code ICellTable} interface provides a contract for classes
 * modeling a two-dimensional table of arbitrary data.
 *
 * @param <CELL>
 *            the type of the cells contained in the table.
 * @param <T>
 *            the type of the table data.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public interface ICellTable<CELL extends ICell<T>, T>
        extends ITable<T> {

    /**
     * Adds the value to the table in the specified cell.
     *
     * @param cell
     *            the table cell.
     * @return {@code true} if the table did not already contain the value;
     *         {@code false} otherwise.
     */
    boolean add(CELL cell);

    /**
     * Returns the value in the cell with the specified row and column.
     *
     * @param location
     *            the location.
     * @return the value in the cell or {@code null} if no value exists.
     */
    T get(CELL location);

    /**
     * Returns the cell with the specified row and column.
     *
     * @param row
     *            the row.
     * @param column
     *            the column.
     * @return the cell or {@code null} if no cell exists for the provided row
     *         and column.
     */
    CELL getCell(int row, int column);

    /**
     * Returns the cell with the specified location.
     *
     * @param location
     *            the location.
     * @return the cell or {@code null} if no cell exists for the provided row
     *         and column.
     */
    CELL getCell(CellLocation location);

    /**
     * Returns the {@link CellContainer Row} with the provided index.
     *
     * @param index
     *            the index of the {@link CellContainer Row}.
     * @return the {@link CellContainer Row} with the provided index or
     *         {@code null}, if the table does not contain a
     *         {@link CellContainer Row} with the provided index.
     */
    @Override
    CellContainer<CELL, T> getRow(int index);

    /**
     * Returns the {@link CellContainer Rows} of this {@link Table}.
     *
     * @return the {@link CellContainer Rows} of this {@link Table}.
     */
    @Override
    Collection<CellContainer<CELL, T>> getRows();

    /**
     * Returns the {@link CellContainer Column} with the provided index.
     *
     * @param index
     *            the index of the {@link CellContainer Column}.
     * @return the {@link CellContainer Column} with the provided index or
     *         {@code null}, if the table does not contain a
     *         {@link CellContainer Column} with the provided index.
     */
    @Override
    CellContainer<CELL, T> getColumn(int index);

    /**
     * Returns the {@link CellContainer Columns} of this {@link Table}.
     *
     * @return the {@link CellContainer Columns} of this {@link Table}.
     */
    @Override
    Collection<CellContainer<CELL, T>> getColumns();

    /**
     * Returns the {@link ICell cells} of this {@link Table}.
     *
     * @return the {@link ICell cell cells} .
     */
    Collection<CELL> getCells();

    /**
     * Removes the {@link Cell} from the table.
     *
     * @param cell
     *            the cell to remove.
     * @return {@code true}, if the table contained the cell; {@code false}
     *         otherwise.
     */
    boolean remove(CELL cell);

    /**
     * Returns an {@link Iterator} over the cells of the table.
     *
     * @return an {@link Iterator} over the cells of the table.
     */
    Iterator<CELL> cellIterator();

    /**
     * Returns an {@link Iterator} over the cells of the table with row-major
     * order.
     *
     * @return an {@link Iterator} over the cells of the table with row-major
     *         order.
     */
    Iterator<CELL> rowMajorOrderCellIterator();

    /**
     * Returns an {@link Iterator} over the cells of the table with column-major
     * order.
     *
     * @return an {@link Iterator} over the cells of the table with column-major
     *         order.
     */
    Iterator<CELL> columnMajorOrderCellIterator();
}
