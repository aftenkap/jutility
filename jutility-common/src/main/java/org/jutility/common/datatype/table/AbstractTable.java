package org.jutility.common.datatype.table;


//@formatter:off
/*
 * #%L
 * jutility-common
 * %%
 * Copyright (C) 2013 - 2015 jutility.org
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
//@formatter:on

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.jutility.common.reflection.ReflectionUtils;



/**
 * The abstract generic {@code Table} class models a two-dimensional table of
 * arbitrary data.
 * <p>
 * The table is modeled as a {@link TreeMap} of {@link ICell Cells} to limit the
 * memory impact of sparse tables.
 * </p>
 *
 * @param <CELL>
 *         the type of the cells contained in the table.
 * @param <T>
 *         the type of the table data.
 *
 * @author Peter J. Radics
 * @version 0.1.4
 * @since 0.1.0
 */
public abstract class AbstractTable<CELL extends ICell<T>, T>
        implements ICellTable<CELL, T>, Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -4547481051474631317L;

    private final TreeMap<Integer, CellContainer<CELL, T>> columns;
    private final TreeMap<Integer, CellContainer<CELL, T>> rows;

    private IterationOrder iterationOrder;


    @Override
    public IterationOrder getIterationOrder() {

        return this.iterationOrder;
    }


    @Override
    public void setIterationOrder(final IterationOrder iterationOrder) {

        this.iterationOrder = iterationOrder;
    }

    /**
     * Creates a new instance of the {@code AbstractTable} class with
     * {@link IterationOrder#ROW_MAJOR row-major IterationOrder}.
     */
    public AbstractTable() {

        this(IterationOrder.ROW_MAJOR);
    }

    /**
     * Creates a new instance of the {@code AbstractTable} class with the
     * provided {@link IterationOrder iteration order}.
     *
     * @param iterationOrder
     *         the {@link IterationOrder IterationOrder} of the class.
     */
    public AbstractTable(final IterationOrder iterationOrder) {

        this.columns = new TreeMap<>();
        this.rows = new TreeMap<>();

        this.iterationOrder = iterationOrder;
    }

    /**
     * Creates a new instance of the {@link AbstractTable} class. (Copy
     * Constructor)
     *
     * @param table
     *         the table to copy.
     */
    public AbstractTable(final ITable<? extends T> table) {

        this();

        for (int column = 0; column < table.columns(); column++) {

            for (int row = 0; row < table.rows(); row++) {

                final T value = table.get(row, column);

                if (value != null) {

                    this.add(row, column, value);
                }
            }
        }
    }


    @Override
    public boolean add(final CELL cell) {

        CellContainer<CELL, T> column = this.columns.get(cell.getColumn());
        CellContainer<CELL, T> row = this.rows.get(cell.getRow());

        if (column == null) {

            column = new CellContainer<>(cell.getColumn());
            this.columns.put(cell.getColumn(), column);
        }
        if (row == null) {

            row = new CellContainer<>(cell.getRow());
            this.rows.put(cell.getRow(), row);
        }

        final boolean columnAdded = column.add(cell);
        final boolean rowAdded = row.add(cell);

        if (rowAdded != columnAdded) {

            throw new IllegalStateException(
                    "Adding cell to rows and columns did not return same "
                    + "status: (rows added: " + rowAdded + ", columns added: "
                    + columnAdded);
        }

        return rowAdded;
    }


    @Override
    public T get(final int row, final int column) {

        final CELL cell = this.getCell(row, column);

        if (cell != null) {

            return cell.getValue();
        }
        return null;
    }


    @Override
    public T get(final CELL location) {

        return this.get(location.getRow(), location.getColumn());
    }


    @Override
    public T get(final CellLocation location) {

        if (location == null) {

            return null;
        }
        return this.get(location.getRow(), location.getColumn());
    }



    @Override
    public CELL getCell(final int row, final int column) {

        if (this.rows.size() < this.columns.size()) {

            final CellContainer<CELL, T> tableRow = this.rows.get(row);

            if (tableRow != null) {

                return tableRow.getCell(column);
            }

            return null;
        }
        else {
            final CellContainer<CELL, T> tableColumn = this.columns.get(column);

            if (tableColumn != null) {

                return tableColumn.getCell(row);
            }

            return null;
        }
    }


    @Override
    public CELL getCell(final CellLocation location) {

        if (location == null) {

            return null;
        }
        return this.getCell(location.getRow(), location.getColumn());
    }



    @Override
    public CellContainer<CELL, T> getRow(final int index) {

        return this.rows.get(index);
    }


    @Override
    public CellContainer<CELL, T> removeRow(final int index) {

        CellContainer<CELL, T> row = this.rows.remove(index);

        Iterator<CELL> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {

            CELL cell = cellIterator.next();

            this.remove(this.getColumn(cell.getColumn()), cell);
        }

        return row;
    }


    @Override
    public CellContainer<CELL, T> removeColumn(final int index) {

        CellContainer<CELL, T> column = this.columns.remove(index);


        Iterator<CELL> cellIterator = column.cellIterator();
        while (cellIterator.hasNext()) {

            CELL cell = cellIterator.next();

            this.remove(this.getRow(cell.getRow()), cell);
        }

        return column;
    }


    @Override
    public Collection<CellContainer<CELL, T>> getRows() {

        return this.rows.values();
    }


    @Override
    public CellContainer<CELL, T> getColumn(final int index) {

        return this.columns.get(index);
    }


    @Override
    public Collection<CellContainer<CELL, T>> getColumns() {

        return this.columns.values();
    }


    @Override
    public Collection<CELL> getCells() {

        final ArrayList<CELL> list = new ArrayList<>(
                this.rows() + this.columns());

        final Iterator<CELL> it = this.cellIterator();

        while (it.hasNext()) {

            list.add(it.next());
        }

        return list;
    }


    @Override
    public List<T> getValues() {

        final ArrayList<T> list = new ArrayList<>(this.rows() + this.columns());

        for (final T element : this) {
            list.add(element);
        }

        return list;
    }


    @Override
    public boolean remove(final int row, final int column) {

        final CellContainer<CELL, T> cellRow = this.rows.get(row);
        final CellContainer<CELL, T> cellColumn = this.columns.get(column);


        if ((cellRow != null) && (cellColumn != null)) {

            final boolean rowRemoved = this.remove(cellRow,
                    cellRow.getCell(column));
            final boolean columnRemoved = this.remove(cellColumn,
                    cellColumn.getCell(row));

            if (rowRemoved != columnRemoved) {

                throw new IllegalStateException(
                        "Removing cell from rows and columns did not return "
                        + "same status: (rows removed: " + rowRemoved
                        + ", columns removed: " + columnRemoved);
            }

            return rowRemoved;


        }
        else if (cellRow != null || cellColumn != null) {

            throw new IllegalStateException(
                    "Cell not contained in both rows and columns: (row "
                    + "contained: " + (cellRow != null) + ", column contained: "
                    + (cellColumn != null) + "!");
        }

        return false;
    }


    @Override
    public boolean remove(CellLocation location) {

        return this.remove(location.getRow(), location.getColumn());
    }

    @Override
    public boolean remove(final CELL cell) {

        return this.remove(cell.getRow(), cell.getColumn());
    }


    /**
     * Removes the cell from the provided container. Removes the container if
     * empty.
     *
     * @param container
     *         the container to remove the cell from.
     * @param cell
     *         the cell to remove.
     *
     * @return {@code true}, if the container was changed; {@code false}
     * otherwise.
     */
    boolean remove(final CellContainer<CELL, T> container, final CELL cell) {

        final boolean removed = container.remove(cell);
        if (container.isEmpty()) {

            if (container.equals(this.rows.get(container.getIndex()))) {

                this.rows.remove(container.getIndex());
            }
            if (container.equals(this.columns.get(container.getIndex()))) {

                this.columns.remove(container.getIndex());
            }
        }

        return removed;
    }

    @Override
    public void clear() {

        this.rows.clear();
        this.columns.clear();
    }

    @Override
    public CellRange cellRange() {

        final int maxColumn = this.columns.lastKey() + 1;
        final int maxRow = this.rows.lastKey() + 1;

        final int minColumn = this.columns.firstKey();
        final int minRow = this.rows.firstKey();

        return new CellRange(minRow, minColumn, maxRow, maxColumn);
    }


    @Override
    public int rows() {

        return this.rows.size();
    }


    @Override
    public int columns() {

        return this.columns.size();
    }


    @Override
    public int size() {

        return this.getCells()
                   .size();
    }

    @Override
    public String toString() {

        return this.getCells()
                   .toString();
    }


    @Override
    public Iterator<T> iterator() {

        switch (this.iterationOrder) {
            case COLUMN_MAJOR:

                return this.columnMajorOrderIterator();

            case ROW_MAJOR:
            default:

                return this.rowMajorOrderIterator();
        }
    }



    @Override
    public Iterator<T> rowMajorOrderIterator() {

        return new ICell.CellValueIterator<>(this.rowMajorOrderCellIterator());
    }


    @Override
    public Iterator<T> columnMajorOrderIterator() {

        return new ICell.CellValueIterator<>(
                this.columnMajorOrderCellIterator());
    }


    @Override
    public Iterator<CELL> cellIterator() {

        switch (this.iterationOrder) {
            case COLUMN_MAJOR:

                return this.columnMajorOrderCellIterator();

            case ROW_MAJOR:
            default:

                return this.rowMajorOrderCellIterator();
        }
    }


    @Override
    public Iterator<CELL> rowMajorOrderCellIterator() {

        return new TableCellIterator<>(this, IterationOrder.ROW_MAJOR);
    }


    @Override
    public Iterator<CELL> columnMajorOrderCellIterator() {

        return new TableCellIterator<>(this, IterationOrder.COLUMN_MAJOR);
    }

    /**
     * The {@code Table.TableCellIterator} class provides a wrapper around the
     * {@link Iterator} of a row or column.
     *
     * @param <T>
     *         the value type of the table cells.
     *
     * @author Peter J. Radics
     * @version 0.1.2
     * @since 0.1.0
     */
    private static class TableCellIterator<CELL extends ICell<T>, T>
            implements Iterator<CELL> {

        private final ICellTable<CELL, T> table;
        private final IterationOrder      iterationOrder;

        private final Iterator<CellContainer<CELL, T>> cellContainerIterator;

        private Iterator<CELL>         cellIterator;
        private CELL                   currentCell;
        private CellContainer<CELL, T> currentContainer;

        /**
         * Creates a new instance of the {@code TableCellIterator} class.
         *
         * @param table
         *         the {@link ICellTable} to iterate over.
         * @param iterationOrder
         *         the {@link IterationOrder iteration order} to use.
         */
        public TableCellIterator(final ICellTable<CELL, T> table,
                final IterationOrder iterationOrder) {

            this.table = table;
            this.iterationOrder = iterationOrder;

            switch (this.iterationOrder) {
                case COLUMN_MAJOR:
                    this.cellContainerIterator = this.table.getColumns()
                                                           .iterator();
                    break;
                case ROW_MAJOR:
                default:
                    this.cellContainerIterator = this.table.getRows()
                                                           .iterator();
                    break;

            }
            this.cellIterator = null;
            this.currentContainer = null;
        }

        @Override
        public boolean hasNext() {

            if (this.cellIterator == null
                && this.cellContainerIterator.hasNext()) {

                this.currentContainer = this.cellContainerIterator.next();
                this.cellIterator = this.currentContainer.cellIterator();
            }

            return this.cellIterator != null && this.cellIterator.hasNext();
        }

        @Override
        public CELL next() {

            this.currentCell = this.cellIterator.next();

            if (!this.cellIterator.hasNext()) {

                this.cellIterator = null;
            }

            return this.currentCell;
        }

        @Override
        public void remove() {

            if (this.currentCell == null) {

                throw new IllegalStateException(
                        "The {@link TableCellIterator#next()} method has not "
                        + "yet been called, or the "
                        + "{@link TableCellIterator#remove()} "
                        + "method has already been called after the last call"
                        + " to the "
                        + "{@link TableCellIterator#next()} method");
            }

            // Removing the element from the row or column respectively
            this.cellIterator.remove();

            if (this.currentContainer.isEmpty()) {

                this.cellContainerIterator.remove();
            }

            // Removing the element from the collection <i>not</i> iterated
            // over.
            switch (this.iterationOrder) {
                case COLUMN_MAJOR:

                    // Removing the element from the row.
                    final CellContainer<CELL, T> row = this.table.getRow(
                            this.currentCell.getRow());

                    row.remove(this.currentCell);
                    if (row.isEmpty()) {
                        this.table.removeRow(row.getIndex());
                    }

                    break;
                case ROW_MAJOR:
                default:

                    // Removing the element from the column.
                    final CellContainer<CELL, T> column = this.table.getColumn(
                            this.currentCell.getColumn());

                    column.remove(this.currentCell);
                    if (column.isEmpty()) {
                        this.table.removeColumn(column.getIndex());
                    }
                    break;
            }

            this.currentCell = null;
        }
    }


    /**
     * Returns the effective type of the provided table.
     *
     * @param table
     *         the table.
     *
     * @return the effective type of the table (the shared ancestor class of all
     * values in the table).
     */
    public static Class<?> getEffectiveType(final ITable<?> table) {


        Class<?> superType = null;

        for (final Object value : table) {

            final Class<?> valueClass = value.getClass();

            System.out.println("Value class: " + valueClass);
            System.out.println("Super Type: " + superType);



            if (superType == null) {

                superType = valueClass;
            }
            else {



                if (valueClass != superType) {

                    if (valueClass.isAssignableFrom(superType)) {

                        System.out.println(
                                "Can cast " + superType + " to " + valueClass);
                        superType = valueClass;
                    }
                    else if (superType.isAssignableFrom(valueClass)) {
                        // nothing to do
                        System.out.println(
                                "Can cast " + valueClass + " to " + superType);
                    }
                    else {
                        System.out.println(
                                "LCD of " + superType + " and " + valueClass
                                + ": " + ReflectionUtils.getSharedAncestorClass(
                                        superType, valueClass));
                        superType = ReflectionUtils.getSharedAncestorClass(
                                superType, valueClass);
                    }

                }

            }
        }

        return superType;
    }
}
