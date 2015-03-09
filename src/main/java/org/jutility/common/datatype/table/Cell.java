package org.jutility.common.datatype.table;


import java.io.Serializable;


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


/**
 * The generic {@code Cell} class is a container class for elements that are
 * referenced by two indices.
 *
 * @param <T>
 *            the element type.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class Cell<T>
        implements ICell<T>, Comparable<ICell<T>>, Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 7327202361564453945L;


    private CellLocation      location;

    private T                 value;



    @Override
    public int getRow() {

        return this.location.getRow();
    }


    @Override
    public void setRow(final int row) {

        this.location = new CellLocation(row, this.getColumn());
    }


    @Override
    public int getColumn() {

        return this.location.getColumn();
    }


    @Override
    public void setColumn(final int column) {

        this.location = new CellLocation(this.getRow(), column);
    }


    @Override
    public CellLocation getLocation() {

        return this.location;
    }



    @Override
    public void setLocation(final CellLocation location) {

        if (location == null) {

            throw new IllegalArgumentException("Cell location cannot be null!");
        }
        this.location = location;
    }


    @Override
    public T getValue() {

        return this.value;
    }



    @Override
    public void setValue(final T value) {

        if (value == null) {

            throw new IllegalArgumentException("Null values are not supported!");
        }
        this.value = value;
    }

    /**
     * Creates a new instance of the {@code Cell} class.
     */
    protected Cell() {

        this(0, 0);
    }

    /**
     *
     * Creates a new instance of the {@code Cell} class.
     *
     * @param row
     *            the row.
     * @param column
     *            the column.
     */
    public Cell(final int row, final int column) {

        this(row, column, null);
    }

    /**
     * Creates a new instance of the {@code Cell} class with the provided value.
     *
     * @param row
     *            the row.
     * @param column
     *            the column.
     * @param value
     *            the value.
     */
    public Cell(final int row, final int column, final T value) {

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
     * Creates a new instance of the {@code Cell} class. (Copy Constructor)
     *
     * @param cell
     *            the cell to copy.
     */
    public Cell(final ICell<? extends T> cell) {

        this(cell.getRow(), cell.getColumn(), cell.getValue());
    }


    @Override
    public int compareTo(final ICell<T> other) {

        return ICell.rowMajorOrder.compare(this, other);
    }


    @Override
    public String toString() {

        return "(" + this.getRow() + ", " + this.getColumn() + ") = "
                + this.value;
    }
}
