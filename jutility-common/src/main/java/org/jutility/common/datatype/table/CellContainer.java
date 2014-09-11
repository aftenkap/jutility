package org.jutility.common.datatype.table;


import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;


/**
 * The {@link CellContainer} class represents a row or column in a
 * {@link ITable} .
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <CELL>
 *            the cell type
 * @param <T>
 *            the type of the contents.
 */
public class CellContainer<CELL extends ICell<T>, T>
        extends AbstractList<T>
        implements Comparable<CellContainer<CELL, T>>, Iterable<T>,
        List<T> {

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
     * Creates a new instance of the {@link CellContainer} class.
     * 
     * @param index
     *            the index of the container.
     */
    public CellContainer(final int index) {

        this.index = index;
        this.entries = new TreeMap<Integer, CELL>();
    }


    /**
     * Returns the {@link ICell cell} value for the provided valueIndex in this
     * {@link CellContainer}.
     * 
     * @param valueIndex
     *            the valueIndex.
     * @return the {@link ICell Cell} value.
     */
    @Override
    public T get(int valueIndex) {

        CELL valueCell = this.entries.get(valueIndex);
        if (valueCell != null) {

            return this.entries.get(valueIndex).getValue();
        }
        return null;
    }


    /**
     * Returns the {@link ICell} for the provided valueIndex in this
     * {@link CellContainer}.
     * 
     * @param valueIndex
     *            the valueIndex
     * @return the {@link ICell Cell}.
     */
    public CELL getCell(int valueIndex) {

        return this.entries.get(valueIndex);
    }


    /**
     * Returns the {@link ICell Cells} of this {@link CellContainer}.
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
     *            the table cell.
     * @return {@code true} if the table did not already contain the value;
     *         {@code false} otherwise.
     */
    public boolean add(CELL cell) {

        boolean rowIsIndex = cell.getRow() == this.index;
        boolean columnIsIndex = cell.getColumn() == this.index;

        if (rowIsIndex) {

            return this.entries.put(cell.getColumn(), cell) != null;
        }
        if (columnIsIndex) {

            return this.entries.put(cell.getRow(), cell) != null;
        }


        throw new IllegalArgumentException("Trying to add cell " + cell
                + " to container where neither row nor column match"
                + " container index (index = " + this.getIndex() + ")!");
    }

    /**
     * Removes the cell at the specified valueIndex from the
     * {@link CellContainer} row.
     * 
     * @param valueIndex
     *            the value index.
     * @return {@code true}, if this {@link CellContainer} contained the cell;
     *         {@code false} otherwise.
     */
    @Override
    public T remove(int valueIndex) {

        return this.entries.remove(valueIndex).getValue();
    }


    /**
     * Removes the {@link Cell} from the table.
     * 
     * @param cell
     *            the cell to remove.
     * @return {@code true}, if the {@link CellContainer} contained the cell;
     *         {@code false} otherwise.
     */
    public boolean remove(final CELL cell) {

        boolean rowIsIndex = cell.getRow() == this.index;
        boolean columnIsIndex = cell.getColumn() == this.index;

        if (rowIsIndex) {

            return this.entries.remove(cell.getColumn()) != null;
        }

        if (columnIsIndex) {

            return this.entries.remove(cell.getRow()) != null;
        }

        return false;
    }


    /**
     * Returns whether or not this {@link CellContainer} is empty.
     * 
     * @return {@code true}, if this {@link CellContainer} is empty;
     *         {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {

        return this.entries.isEmpty();
    }


    /**
     * Returns the size of this {@link CellContainer}.
     * 
     * @return the size.
     */
    @Override
    public int size() {

        return this.entries.size();
    }


    @Override
    public int compareTo(CellContainer<CELL, T> other) {

        return this.getIndex() - other.getIndex();
    }


    /**
     * Returns an {@link Iterator} over the cells of this {@link CellContainer}.
     * 
     * @return an {@link Iterator} over the cells of this {@link CellContainer}.
     */
    public Iterator<CELL> cellIterator() {

        return this.entries.values().iterator();
    }


    @Override
    public Iterator<T> iterator() {

        return new ICell.CellValueIterator<T>(this.cellIterator());
    }


}
