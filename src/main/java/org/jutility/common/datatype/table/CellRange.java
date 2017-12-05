package org.jutility.common.datatype.table;


//@formatter:off
/*
* #%L
 * * jutility-common
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * *
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
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * The immutable {@code CellRange} class defines a range of {@link CellLocation
 * cell locations}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class CellRange
        implements Iterable<CellLocation>, Serializable {

    /**
     * Serial Version UID.
     */
    private static final long  serialVersionUID = -8617565404520860866L;
    private final CellLocation beginning;
    private final CellLocation end;

    private final int          size;


    /**
     * Returns the beginning of the Range.
     *
     * @return the beginning of the Range.
     */
    public CellLocation getBeginning() {

        return this.beginning;
    }


    /**
     * Returns the end of the Range.
     *
     * @return the end of the Range.
     */
    public CellLocation getEnd() {

        return this.end;
    }


    /**
     * Returns the size of the {@code CellRange} (the number of CellLocations).
     *
     * @return the size of the {@code CellRange}.
     */
    public int size() {

        return this.size;
    }

    /**
     * Returns whether or not the {@code CellRange} is empty;
     *
     * @return {@code true}, if the {@code CellRange} is empty; {@code false}
     *         otherwise.
     */
    public boolean isEmpty() {

        return this.size == 0;
    }

    /**
     * Creates a new instance of the {@code CellRange} class with the provided
     * indices.
     *
     * @param beginningRowIndex
     *            the row index of the beginning of the range.
     * @param beginningColumnIndex
     *            the column index of the beginning of the range.
     * @param endRowIndex
     *            the row index of the end of the range.
     * @param endColumnIndex
     *            the column index of the end of the range.
     */
    public CellRange(final int beginningRowIndex,
            final int beginningColumnIndex, final int endRowIndex,
            final int endColumnIndex) {

        final int minRow = Math.min(beginningRowIndex, endRowIndex);
        final int minColumn = Math.min(beginningColumnIndex, endColumnIndex);

        final int maxRow = Math.max(beginningRowIndex, endRowIndex);
        final int maxColumn = Math.max(beginningColumnIndex, endColumnIndex);


        this.beginning = new CellLocation(minRow, minColumn);
        this.end = new CellLocation(maxRow, maxColumn);

        this.size = (maxRow - minRow) * (maxColumn - minColumn);
    }

    /**
     * Creates a new instance of the {@code CellRange} class.
     *
     * @param beginning
     *            the beginning of the Range.
     * @param end
     *            the end of the Range.
     */
    public CellRange(final CellLocation beginning, final CellLocation end) {

        this(beginning.getRow(), beginning.getColumn(), end.getRow(), end
                .getColumn());
    }

    /**
     * Returns the {@link Characteristics} of the {@code CellRange}.
     *
     * @return the {@link Characteristics} of the {@code CellRange}.
     */
    public Characteristics characteristic() {

        if (this.beginning.equals(this.getEnd())) {

            return Characteristics.EMPTY;
        }
        else if (((this.beginning.getRow() + 1) == this.end.getRow())
                && ((this.beginning.getColumn() + 1) == this.end.getColumn())) {

            return Characteristics.CELL;
        }
        else if ((this.beginning.getRow() + 1) == this.end.getRow()) {

            return Characteristics.ROW;
        }
        else if ((this.beginning.getColumn() + 1) == this.end.getColumn()) {

            return Characteristics.COLUMN;
        }
        else {

            return Characteristics.RANGE;
        }
    }

    /**
     * Determines whether the location specified by the provided row and column
     * indices lies within the {@code CellRange}.
     *
     * @param row
     *            the row.
     * @param column
     *            the column.
     * @return {@code true}, if the provided location lies within the
     *         {@code CellRange}; {@code false} otherwise.
     */
    public boolean contains(final int row, final int column) {

        return this.containsRow(row) && this.containsColumn(column);
    }

    /**
     * Determines whether the provided row index lies within the
     * {@code CellRange}.
     *
     * @param row
     *            the row index.
     * @return {@code true}, if the row index lies within the {@code CellRange};
     *         {@code false} otherwise.
     */
    public boolean containsRow(final int row) {

        return (this.getBeginning().getRow() <= row)
                && (row < this.getEnd().getRow());
    }


    /**
     * Determines whether the provided column index lies within the
     * {@code CellRange}.
     *
     * @param column
     *            the column index.
     * @return {@code true}, if the column index lies within the
     *         {@code CellRange}; {@code false} otherwise.
     */
    public boolean containsColumn(final int column) {

        return (this.getBeginning().getColumn() <= column)
                && (column < this.getEnd().getColumn());
    }

    /**
     * Determines whether the provided {@link CellLocation} lies within the
     * {@code CellRange}.
     *
     * @param location
     *            the {@link CellLocation}.
     * @return {@code true}, if the provided {@link CellLocation} lies within
     *         the {@code CellRange}; {@code false} otherwise.
     */
    public boolean contains(final CellLocation location) {

        return this.contains(location.getRow(), location.getColumn());
    }



    @Override
    public String toString() {

        return "[ (" + this.beginning.getRow() + ", "
                + this.beginning.getColumn() + ") - (" + this.end.getRow()
                + ", " + this.end.getColumn() + ") )";
    }


    @Override
    public Iterator<CellLocation> iterator() {

        return this.rowMajorOrderIterator();
    }

    /**
     * Returns an {@link Iterator} over the {@code CellRange} that traverses the
     * range in row-major order.
     *
     * @return a row-major order {@link Iterator} over the {@code CellRange}.
     */
    public Iterator<CellLocation> rowMajorOrderIterator() {

        return new CellRangeIterator(this, IterationOrder.ROW_MAJOR);
    }

    /**
     * Returns an {@link Iterator} over the {@code CellRange} that traverses the
     * range in column-major order.
     *
     * @return a column-major order {@link Iterator} over the {@code CellRange}.
     */
    public Iterator<CellLocation> columnMajorOrderIterator() {

        return new CellRangeIterator(this, IterationOrder.COLUMN_MAJOR);
    }



    /**
     * Creates a row {@code CellRange} for the provided row index.
     *
     * @param rowIndex
     *            the row index.
     * @param table
     *            the table that contains the row.
     * @param includeFirstElement
     *            whether or not to include the first element
     * @return a row {@code CellRange}.
     */
    public static CellRange row(final int rowIndex, final ITable<?> table,
            final boolean includeFirstElement) {

        final CellRange range = table.cellRange();

        int startColumn = range.getBeginning().getColumn();

        if (!includeFirstElement) {

            startColumn++;
        }

        if (range.containsRow(rowIndex)) {

            return new CellRange(rowIndex, startColumn, rowIndex + 1, range
                    .getEnd().getColumn());
        }

        return null;
    }

    /**
     * Creates a row {@code CellRange} for the provided row index.
     *
     * @param rowIndex
     *            the row index.
     * @param table
     *            the table that contains the row.
     * @return a row {@code CellRange}.
     */
    public static CellRange row(final int rowIndex, final ITable<?> table) {

        return CellRange.row(rowIndex, table, true);
    }

    /**
     * Creates a column {@code CellRange} for the provided column index.
     *
     * @param columnIndex
     *            the column index.
     * @param table
     *            the table that contains the column.
     * @return a column {@code CellRange}.
     */
    public static CellRange column(final int columnIndex, final ITable<?> table) {


        return CellRange.column(columnIndex, table, true);
    }

    /**
     * Creates a column {@code CellRange} for the provided column index.
     *
     * @param columnIndex
     *            the column index.
     * @param table
     *            the table that contains the column.
     * @param includeFirstElement
     *            whether or not to include the first element
     * @return a column {@code CellRange}.
     */
    public static CellRange column(final int columnIndex,
            final ITable<?> table, final boolean includeFirstElement) {


        final CellRange range = table.cellRange();

        if (range.containsColumn(columnIndex)) {

            int startRow = range.getBeginning().getRow();

            if (!includeFirstElement) {

                startRow++;
            }
            return new CellRange(startRow, columnIndex,
                    range.getEnd().getRow(), columnIndex + 1);

        }
        return null;
    }

    /**
     * The {@code Characteristics} enum provides an enumeration of the
     * characteristics a {@link CellRange} can have. All characteristics are
     * mutually exclusive.
     *
     * @author Peter J. Radics
     * @version 0.1.2
     * @since 0.1.0
     */
    public enum Characteristics {
        /**
         * An empty range.
         */
        EMPTY("Empty Range"),

        /**
         * An range containing a single cell.
         */
        CELL("Single Cell"),

        /**
         * A range containing a single row of cells.
         */
        ROW("Single Row"),

        /**
         * A range containing a single column of cells.
         */
        COLUMN("Single Column"),

        /**
         * A range containing an arbitrary amount of cells.
         */
        RANGE("Range");


        private final String name;

        Characteristics(final String name) {

            this.name = name;
        }


        @Override
        public String toString() {

            return this.name;
        }
    }



    /**
     * The {@code CellRangeIterator} class provides an iterator over a
     * {@link CellRange} using a provided {@link IterationOrder} to determine
     * order of traversal.
     *
     * @author Peter J. Radics
     * @version 0.1.2
     * @since 0.1.0
     */
    private static class CellRangeIterator
            implements Iterator<CellLocation> {

        private final CellRange      cellRange;
        private final IterationOrder iterationOrder;
        private CellLocation         currentLocation;


        /**
         * Creates a new instance of the {@code CellRangeIterator} class for the
         * provided {@link CellRange}.
         *
         * @param cellRange
         *            the {@link CellRange} to iterate over.
         * @param iterationOrder
         *            the iteration order over the {@link CellRange}.
         */
        public CellRangeIterator(final CellRange cellRange,
                final IterationOrder iterationOrder) {

            this.cellRange = cellRange;
            this.iterationOrder = iterationOrder;
            this.currentLocation = cellRange.getBeginning();
        }

        @Override
        public boolean hasNext() {

            return this.cellRange.contains(this.currentLocation);
        }

        @Override
        public CellLocation next() {

            final CellLocation current = this.currentLocation;

            if (!this.cellRange.contains(current)) {

                throw new NoSuchElementException(
                        "Attempted to iterate past the last element!");
            }

            switch (this.iterationOrder) {
                case COLUMN_MAJOR:
                    this.advanceInColumnMajorOrder();
                    break;
                case ROW_MAJOR:
                default:
                    this.advanceInRowMajorOrder();
                    break;
            }

            return current;
        }

        private void advanceInRowMajorOrder() {

            final CellLocation columnIncrement = new CellLocation(
                    this.currentLocation.getRow(),
                    this.currentLocation.getColumn() + 1);

            if (this.cellRange.contains(columnIncrement)) {

                this.currentLocation = columnIncrement;
            }
            else {

                this.currentLocation = new CellLocation(
                        this.currentLocation.getRow() + 1, this.cellRange
                                .getBeginning().getColumn());
            }
        }


        private void advanceInColumnMajorOrder() {

            final CellLocation rowIncrement = new CellLocation(
                    this.currentLocation.getRow() + 1,
                    this.currentLocation.getColumn());

            if (this.cellRange.contains(rowIncrement)) {

                this.currentLocation = rowIncrement;
            }
            else {

                this.currentLocation = new CellLocation(this.cellRange
                        .getBeginning().getRow(),
                        this.currentLocation.getColumn() + 1);
            }
        }

        @Override
        public void remove() {

            throw new UnsupportedOperationException(
                    "The CellRangeIterator does not support removal!");
        }
    }


    /**
     * Clamps the provided {@code CellRange} to the provided {@code CellRange
     * boundary}.
     *
     * @param rangeToClamp
     *            the {@code CellRange} to clamp.
     * @param boundary
     *            the {@code CellRange boundary}.
     * @return the clamped {@code CellRange}.
     */
    public static CellRange clampToRange(final CellRange rangeToClamp,
            final CellRange boundary) {

        final int minRow = Math.max(rangeToClamp.getBeginning().getRow(),
                boundary.getBeginning().getRow());
        final int minColumn = Math.max(rangeToClamp.getBeginning().getColumn(),
                boundary.getBeginning().getColumn());

        final int maxRow = Math.min(rangeToClamp.getEnd().getRow(), boundary
                .getEnd().getRow());
        final int maxColumn = Math.min(rangeToClamp.getEnd().getColumn(),
                boundary.getEnd().getColumn());

        return new CellRange(minRow, minColumn, maxRow, maxColumn);
    }


}
