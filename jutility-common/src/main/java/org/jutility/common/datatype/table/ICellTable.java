package org.jutility.common.datatype.table;


import java.util.Collection;
import java.util.Iterator;



/**
 * The generic {@link ICellTable} interface provides a contract for classes
 * modeling a two-dimensional table of arbitrary data.
 * 
 * @param <T>
 *            the type of the table data.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @param <CELL>
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
    public abstract boolean add(CELL cell);

    /**
     * Returns the value in the cell with the specified row and column.
     * 
     * @param location
     *            the location.
     * @return the value in the cell or {@code null} if no value exists.
     */
    public abstract T get(CELL location);

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
    public abstract CELL getCell(int row, int column);

    /**
     * Returns the cell with the specified location.
     * 
     * @param location
     *            the location.
     * @return the cell or {@code null} if no cell exists for the provided row
     *         and column.
     */
    public abstract CELL getCell(CellLocation location);

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
    public abstract CellContainer<CELL, T> getRow(int index);

    /**
     * Returns the {@link CellContainer Rows} of this {@link Table}.
     * 
     * @return the {@link CellContainer Rows} of this {@link Table}.
     */
    @Override
    public abstract Collection<CellContainer<CELL, T>> getRows();

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
    public abstract CellContainer<CELL, T> getColumn(int index);

    /**
     * Returns the {@link CellContainer Columns} of this {@link Table}.
     * 
     * @return the {@link CellContainer Columns} of this {@link Table}.
     */
    @Override
    public abstract Collection<CellContainer<CELL, T>> getColumns();

    /**
     * Returns the {@link ICell cells} of this {@link Table}.
     * 
     * @return the {@link ICell cell cells} .
     */
    public abstract Collection<CELL> getCells();

    /**
     * Removes the {@link Cell} from the table.
     * 
     * @param cell
     *            the cell to remove.
     * @return {@code true}, if the table contained the cell; {@code false}
     *         otherwise.
     */
    public abstract boolean remove(CELL cell);

    /**
     * Returns an {@link Iterator} over the cells of the table.
     * 
     * @return an {@link Iterator} over the cells of the table.
     */
    public abstract Iterator<CELL> cellIterator();

    /**
     * Returns an {@link Iterator} over the cells of the table with row-major
     * order.
     * 
     * @return an {@link Iterator} over the cells of the table with row-major
     *         order.
     */
    public abstract Iterator<CELL> rowMajorOrderCellIterator();

    /**
     * Returns an {@link Iterator} over the cells of the table with column-major
     * order.
     * 
     * @return an {@link Iterator} over the cells of the table with column-major
     *         order.
     */
    public Iterator<CELL> columnMajorOrderCellIterator();
}