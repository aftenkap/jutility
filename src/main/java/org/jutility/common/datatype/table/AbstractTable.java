/**
 * 
 */
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



import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;

import org.jutility.common.reflection.ReflectionUtils;



/**
 * The generic {@link Table} class models a two-dimensional table of arbitrary
 * data.
 * <p/>
 * The table is modeled as a {@link SortedSet} of {@link ICell Cells} to limit
 * the memory impact of sparse tables.
 * 
 * @param <T>
 *            the type of the table data.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @param <CELL>
 */
public abstract class AbstractTable<CELL extends ICell<T>, T>
        implements ICellTable<CELL, T> {

    private final TreeMap<Integer, CellContainer<CELL, T>> columns;
    private final TreeMap<Integer, CellContainer<CELL, T>> rows;

    private IterationOrder                                 iterationOrder;


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#getIterationOrder()
     */
    @Override
    public IterationOrder getIterationOrder() {

        return this.iterationOrder;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.datatypes.table.ITable#setIterationOrder(org.jutility.datatypes
     * .table.Table.IterationOrder)
     */
    @Override
    public void setIterationOrder(IterationOrder iterationOrder) {

        this.iterationOrder = iterationOrder;
    }

    /**
     * Creates a new instance of the {@link Table} class with
     * {@link IterationOrder#ROW_MAJOR row-major IterationOrder}.
     */
    public AbstractTable() {

        this(IterationOrder.ROW_MAJOR);
    }

    /**
     * Creates a new instance of the {@link Table} class with the provided
     * {@link IterationOrder iteration order}.
     * 
     * @param iterationOrder
     *            the {@link IterationOrder IterationOrder} of the class.
     */
    public AbstractTable(IterationOrder iterationOrder) {

        this.columns = new TreeMap<Integer, CellContainer<CELL, T>>();
        this.rows = new TreeMap<Integer, CellContainer<CELL, T>>();

        this.iterationOrder = iterationOrder;
    }

    /**
     * Creates a new instance of the {@link AbstractTable} class. (Copy
     * Constructor)
     * 
     * @param table
     *            the table to copy.
     */
    public AbstractTable(ITable<? extends T> table) {

        this();

        for (int column = 0; column < table.columns(); column++) {

            for (int row = 0; row < table.rows(); row++) {

                T value = table.get(row, column);

                if (value != null) {

                    this.add(row, column, value);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.datatypes.table.ITable#add(org.jutility.datatypes.table.
     * ICell)
     */
    @Override
    public boolean add(CELL cell) {

        CellContainer<CELL, T> column = this.columns.get(cell.getColumn());
        CellContainer<CELL, T> row = this.rows.get(cell.getRow());

        if (column == null) {

            column = new CellContainer<CELL, T>(cell.getColumn());
            this.columns.put(cell.getColumn(), column);
        }
        if (row == null) {

            row = new CellContainer<CELL, T>(cell.getRow());
            this.rows.put(cell.getRow(), row);
        }

        boolean columnAdded = column.add(cell);
        boolean rowAdded = row.add(cell);

        if (rowAdded != columnAdded) {

            throw new IllegalStateException(
                    "Adding cell to rows and columns did not return same status: (rows added: "
                            + rowAdded + ", columns added: " + columnAdded);
        }

        return rowAdded;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#get(int, int)
     */
    @Override
    public T get(int row, int column) {

        CELL cell = this.getCell(row, column);

        if (cell != null) {

            return cell.getValue();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.datatypes.table.ICellTable#get(org.jutility.datatypes.table
     * .ICell)
     */
    @Override
    public T get(CELL location) {

        return this.get(location.getRow(), location.getColumn());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.datatypes.table.ITable#get(org.jutility.datatypes.table.
     * CellLocation)
     */
    @Override
    public T get(CellLocation location) {

        if (location == null) {

            return null;
        }
        return this.get(location.getRow(), location.getColumn());
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#getCell(int, int)
     */
    @Override
    public CELL getCell(int row, int column) {

        if (this.rows.size() < this.columns.size()) {

            CellContainer<CELL, T> tableRow = this.rows.get(row);

            if (tableRow != null) {

                return tableRow.getCell(column);
            }

            return null;
        }
        else {
            CellContainer<CELL, T> tableColumn = this.columns.get(column);

            if (tableColumn != null) {

                return tableColumn.getCell(row);
            }

            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.datatypes.table.ITable#getCell(org.jutility.datatypes.table
     * .CellLocation)
     */
    @Override
    public CELL getCell(CellLocation location) {

        if (location == null) {

            return null;
        }
        return this.getCell(location.getRow(), location.getColumn());
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#getRow(int)
     */
    @Override
    public CellContainer<CELL, T> getRow(int index) {

        return this.rows.get(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#removeRow(int)
     */
    @Override
    public CellContainer<CELL, T> removeRow(int index) {

        return this.rows.remove(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#removeColumn(int)
     */
    @Override
    public CellContainer<CELL, T> removeColumn(int index) {

        return this.columns.remove(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#getRows()
     */
    @Override
    public Collection<CellContainer<CELL, T>> getRows() {

        return this.rows.values();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#getColumn(int)
     */
    @Override
    public CellContainer<CELL, T> getColumn(int index) {

        return this.columns.get(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#getColumns()
     */
    @Override
    public Collection<CellContainer<CELL, T>> getColumns() {

        return this.columns.values();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#getCells()
     */
    @Override
    public Collection<CELL> getCells() {

        ArrayList<CELL> list = new ArrayList<CELL>(this.rows() + this.columns());

        Iterator<CELL> it = this.cellIterator();

        while (it.hasNext()) {

            list.add(it.next());
        }

        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#getValues()
     */
    @Override
    public List<T> getValues() {

        ArrayList<T> list = new ArrayList<T>(this.rows() + this.columns());

        for (T element : this) {
            list.add(element);
        }

        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#remove(int, int)
     */
    @Override
    public boolean remove(int row, int column) {

        CellContainer<CELL, T> cellRow = this.rows.get(row);
        CellContainer<CELL, T> cellColumn = this.columns.get(column);


        if (cellRow != null && cellColumn != null) {

            boolean rowRemoved = this.remove(cellRow, cellRow.getCell(column));
            boolean columnRemoved = this.remove(cellColumn,
                    cellColumn.getCell(row));

            if (rowRemoved != columnRemoved) {

                throw new IllegalStateException(
                        "Removing cell from rows and columns did not return same status: (rows removed: "
                                + rowRemoved
                                + ", columns removed: "
                                + columnRemoved);
            }

            return rowRemoved;


        }
        else if (cellRow != null && cellColumn == null || cellRow == null
                && cellColumn != null) {

            throw new IllegalStateException(
                    "Cell not contained in both rows and columns: (row contained: "
                            + (cellRow != null) + ", column contained: "
                            + (cellColumn != null) + "!");
        }

        return false;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.datatypes.table.ITable#remove(org.jutility.datatypes.table
     * .ICell)
     */
    @Override
    public boolean remove(final CELL cell) {

        return this.remove(cell.getRow(), cell.getColumn());
    }


    /**
     * Removes the cell from the provided container. Removes the container if
     * empty.
     * 
     * @param container
     *            the container to remove the cell from.
     * @param cell
     *            the cell to remove.
     * @return {@code true}, if the container was changed; {@code false}
     *         otherwise.
     */
    boolean remove(CellContainer<CELL, T> container, CELL cell) {

        boolean removed = container.remove(cell);
        if (container.isEmpty()) {

            if (this.rows.containsValue(container)) {

                this.rows.remove(container.getIndex());
            }
            else if (this.columns.containsValue(container)) {

                this.columns.remove(container);
            }
        }

        return removed;
    }

    @Override
    public CellRange cellRange() {

        int maxColumn = this.columns.lastKey() + 1;
        int maxRow = this.rows.lastKey() + 1;

        int minColumn = this.columns.firstKey();
        int minRow = this.rows.firstKey();

        return new CellRange(minRow, minColumn, maxRow, maxColumn);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#rows()
     */
    @Override
    public int rows() {

        return this.rows.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#columns()
     */
    @Override
    public int columns() {

        return this.columns.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#size()
     */
    @Override
    public int size() {

        return this.getCells().size();
    }

    @Override
    public String toString() {

        return this.getCells().toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#iterator()
     */
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


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#rowMajorIterator()
     */
    @Override
    public Iterator<T> rowMajorOrderIterator() {

        return new ICell.CellValueIterator<T>(this.rowMajorOrderCellIterator());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#columnMajorIterator()
     */
    @Override
    public Iterator<T> columnMajorOrderIterator() {

        return new ICell.CellValueIterator<T>(
                this.columnMajorOrderCellIterator());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#cellIterator()
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#rowMajorCellIterator()
     */
    @Override
    public Iterator<CELL> rowMajorOrderCellIterator() {

        return new TableCellIterator<CELL, T>(this, IterationOrder.ROW_MAJOR);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#columnMajorCellIterator()
     */
    @Override
    public Iterator<CELL> columnMajorOrderCellIterator() {

        return new TableCellIterator<CELL, T>(this, IterationOrder.COLUMN_MAJOR);
    }

    /**
     * The {@link Table.TableCellIterator} class provides a wrapper around the
     * {@link Iterator} of a row or column.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * 
     * @param <V>
     *            the value type of the table cells.
     */
    private static class TableCellIterator<CELL extends ICell<T>, T>
            implements Iterator<CELL> {

        private final ICellTable<CELL, T>              table;
        private final IterationOrder                   iterationOrder;

        private final Iterator<CellContainer<CELL, T>> cellContainerIterator;

        private Iterator<CELL>                         cellIterator;
        private CELL                                   currentCell;
        private CellContainer<CELL, T>                 currentContainer;

        /**
         * Creates a new instance of the {@link TableCellIterator} class.
         * 
         * @param table
         *            the {@link Table} to iterate over.
         * @param iterationOrder
         *            the {@link IterationOrder iteration order} to use.
         * 
         */
        public TableCellIterator(ICellTable<CELL, T> table,
                IterationOrder iterationOrder) {

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

            if (this.cellIterator == null) {

                if (this.cellContainerIterator.hasNext()) {

                    this.currentContainer = this.cellContainerIterator.next();
                    this.cellIterator = this.currentContainer.cellIterator();
                }
            }

            if (this.cellIterator != null) {

                return this.cellIterator.hasNext();
            }
            return false;
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
                                + "yet been called, or the {@link TableCellIterator#remove()} "
                                + "method has already been called after the last call to the "
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
                    CellContainer<CELL, T> row = this.table
                            .getRow(this.currentCell.getRow());

                    row.remove(currentCell);
                    if (row.isEmpty()) {
                        this.table.removeRow(row.getIndex());
                    }

                    break;
                case ROW_MAJOR:
                default:

                    // Removing the element from the column.
                    CellContainer<CELL, T> column = this.table
                            .getColumn(this.currentCell.getColumn());

                    column.remove(currentCell);
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
     *            the table.
     * @return the effective type of the table (the shared ancestor class of all
     *         values in the table).
     */
    public static Class<?> getEffectiveType(ITable<?> table) {


        Class<?> superType = null;

        for (Object value : table) {

            Class<?> valueClass = value.getClass();

            System.out.println("Value class: " + valueClass);
            System.out.println("Super Type: " + superType);



            if (superType == null) {

                superType = valueClass;
            }
            else {



                if (valueClass != superType) {

                    if (valueClass.isAssignableFrom(superType)) {

                        System.out.println("Can cast " + superType + " to "
                                + valueClass);
                        superType = valueClass;
                    }
                    else if (superType.isAssignableFrom(valueClass)) {
                        // nothing to do
                        System.out.println("Can cast " + valueClass + " to "
                                + superType);
                    }
                    else {
                        System.out.println("LCD of "
                                + superType
                                + " and "
                                + valueClass
                                + ": "
                                + ReflectionUtils.getSharedAncestorClass(
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
