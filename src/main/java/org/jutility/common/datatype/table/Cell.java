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



/**
 * The generic {@link Cell} class is a container class for elements that are
 * referenced by two indices.
 * 
 * @param <T>
 *            the element type.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class Cell<T>
        implements ICell<T>, Comparable<ICell<T>> {

    private CellLocation location;

    private T            value;


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ICell#getRow()
     */
    @Override
    public int getRow() {

        return this.location.getRow();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ICell#setRow(int)
     */
    @Override
    public void setRow(final int row) {

        this.location = new CellLocation(row, this.getColumn());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ICell#getColumn()
     */
    @Override
    public int getColumn() {

        return this.location.getColumn();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ICell#setColumn(int)
     */
    @Override
    public void setColumn(final int column) {

        this.location = new CellLocation(this.getRow(), column);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ICell#getLocation()
     */
    @Override
    public CellLocation getLocation() {

        return this.location;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.datatypes.table.ICell#setLocation(org.jutility.datatypes
     * .table.CellLocation)
     */
    @Override
    public void setLocation(CellLocation location) {

        if (location == null) {

            throw new IllegalArgumentException("Cell location cannot be null!");
        }
        this.location = location;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ICell#getValue()
     */
    @Override
    public T getValue() {

        return value;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ICell#setValue(java.lang.Object)
     */
    @Override
    public void setValue(T value) {

        if (value == null) {

            throw new IllegalArgumentException("Null values are not supported!");
        }
        this.value = value;
    }

    /**
     * Creates a new instance of the {@link Cell} class.
     */
    protected Cell() {

        this(0, 0);
    }

    /**
     * 
     * Creates a new instance of the {@link Cell} class.
     * 
     * @param row
     *            the row.
     * @param column
     *            the column.
     */
    public Cell(int row, int column) {

        this(row, column, null);
    }

    /**
     * Creates a new instance of the {@link Cell} class with the provided value.
     * 
     * @param row
     *            the row.
     * @param column
     *            the column.
     * @param value
     *            the value.
     */
    public Cell(int row, int column, T value) {

        if (row < 0) {

            throw new IllegalArgumentException(
                    "Row index cannot be smaller than 0!");
        }
        if (column < 0) {

            throw new IllegalArgumentException(
                    "Column index cannot be smaller than 0!");
        }


        this.location = new CellLocation(row, column);

        this.value = value;
    }


    /**
     * Creates a new instance of the {@link Cell} class. (Copy Constructor)
     * 
     * @param cell
     *            the cell to copy.
     */
    public Cell(ICell<? extends T> cell) {

        this(cell.getRow(), cell.getColumn(), cell.getValue());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(ICell<T> other) {

        return ICell.rowMajorOrder.compare(this, other);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "(" + this.getRow() + ", " + this.getColumn() + ") = "
                + this.value;
    }

}
