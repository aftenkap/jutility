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

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;


/**
 * The {@code CellContainer} class represents a row or column in a
 * {@link ITable} .
 *
 * @param <CELL>
 *         the cell type
 * @param <T>
 *         the type of the contents.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class CellContainer<CELL extends ICell<T>, T>
        extends AbstractList<T>
        implements Comparable<CellContainer<CELL, T>>, Serializable {


    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -2922010862735630588L;


    private final int                    index;
    private final TreeMap<Integer, CELL> entries;


    /**
     * Returns the index.
     *
     * @return the index.
     */
    public int getIndex() {

        return this.index;
    }


    /**
     * Creates a new instance of the {@code CellContainer} class.
     *
     * @param index
     *         the index of the container.
     */
    public CellContainer(final int index) {

        this.index = index;
        this.entries = new TreeMap<>();
    }


    /**
     * Returns the {@link ICell cell} value for the provided valueIndex in this
     * {@code CellContainer}.
     *
     * @param valueIndex
     *         the valueIndex.
     *
     * @return the {@link ICell Cell} value.
     */
    @Override
    public T get(final int valueIndex) {

        final CELL valueCell = this.entries.get(valueIndex);
        if (valueCell != null) {

            return this.entries.get(valueIndex)
                    .getValue();
        }
        return null;
    }


    /**
     * Returns the {@link ICell} for the provided valueIndex in this
     * {@code CellContainer}.
     *
     * @param valueIndex
     *         the valueIndex
     *
     * @return the {@link ICell Cell}.
     */
    public CELL getCell(final int valueIndex) {

        return this.entries.get(valueIndex);
    }


    /**
     * Returns the {@link ICell Cells} of this {@code CellContainer}.
     *
     * @return the {@link ICell Cells}.
     */
    public Collection<CELL> getCells() {

        return this.entries.values();
    }


    /**
     * Adds the value to the table in the specified cell.
     *
     * @param cell
     *         the table cell.
     *
     * @return {@code true} if the table did not already contain the value;
     * {@code false} otherwise.
     */
    public boolean add(final CELL cell) {

        final boolean rowIsIndex = cell.getRow() == this.index;
        final boolean columnIsIndex = cell.getColumn() == this.index;

        if (rowIsIndex) {

            return this.entries.put(cell.getColumn(), cell) != null;
        }
        if (columnIsIndex) {

            return this.entries.put(cell.getRow(), cell) != null;
        }


        throw new IllegalArgumentException("Trying to add cell " + cell
                                           + " to container where neither row"
                                           + " nor column match"
                                           + " container index (index = "
                                           + this.getIndex() + ")!");
    }

    /**
     * Removes the cell at the specified valueIndex from the
     * {@code CellContainer} row.
     *
     * @param valueIndex
     *         the value index.
     *
     * @return {@code true}, if this {@code CellContainer} contained the cell;
     * {@code false} otherwise.
     */
    @Override
    public T remove(final int valueIndex) {

        return this.entries.remove(valueIndex)
                .getValue();
    }


    /**
     * Removes the {@link Cell} from the table.
     *
     * @param cell
     *         the cell to remove.
     *
     * @return {@code true}, if the {@code CellContainer} contained the cell;
     * {@code false} otherwise.
     */
    public boolean remove(final CELL cell) {

        final boolean rowIsIndex = cell.getRow() == this.index;
        final boolean columnIsIndex = cell.getColumn() == this.index;

        if (rowIsIndex) {

            return this.entries.remove(cell.getColumn()) != null;
        }

        return columnIsIndex && this.entries.remove(cell.getRow()) != null;

    }


    /**
     * Returns whether or not this {@code CellContainer} is empty.
     *
     * @return {@code true}, if this {@code CellContainer} is empty; {@code
     * false} otherwise.
     */
    @Override
    public boolean isEmpty() {

        return this.entries.isEmpty();
    }


    /**
     * Returns the size of this {@code CellContainer}.
     *
     * @return the size.
     */
    @Override
    public int size() {

        return this.entries.size();
    }


    @Override
    public int compareTo(final CellContainer<CELL, T> other) {

        return this.getIndex() - other.getIndex();
    }


    /**
     * Returns an {@link Iterator} over the cells of this {@code CellContainer}.
     *
     * @return an {@link Iterator} over the cells of this {@code CellContainer}.
     */
    public Iterator<CELL> cellIterator() {

        return this.entries.values()
                .iterator();
    }


    @Override
    public Iterator<T> iterator() {

        return new ICell.CellValueIterator<>(this.cellIterator());
    }


}
