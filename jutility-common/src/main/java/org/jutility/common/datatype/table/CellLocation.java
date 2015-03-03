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
import java.util.Comparator;

import org.jutility.common.datatype.tuple.ITuple2;
import org.jutility.common.datatype.tuple.Tuple2;


/**
 * The {@code CellLocation} class provides the row and column location of a
 * cell.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class CellLocation
        extends Tuple2<Integer>
        implements Comparable<CellLocation> {

    /**
     * Returns the row index of a cell.
     *
     * @return the row index of a cell.
     */
    public Integer getRow() {

        return this.getX();
    }


    /**
     * Returns the column index of a cell.
     *
     * @return the column index of a cell.
     */
    public Integer getColumn() {

        return this.getY();
    }

    /**
     * Creates a new instance of the {@code CellLocation} class.
     * (SerializationConstructor)
     */
    @SuppressWarnings("unused")
    private CellLocation() {

        super();
    }


    /**
     * Creates a new instance of the {@code CellLocation} class with the
     * provided values.
     *
     * @param row
     *            the row index.
     * @param column
     *            the column index.
     */
    public CellLocation(final int row, final int column) {

        super(row, column, Integer.class);
    }

    /**
     * Creates a new instance of the {@code CellLocation} class as a copy of the
     * provided {@link ITuple2} {@code <}{@link Integer} {@code >}.
     *
     * @param tupleToCopy
     *            the {@link ITuple2} {@code <}{@link Integer} {@code >} to
     *            copy.
     */
    public CellLocation(final ITuple2<Integer> tupleToCopy) {

        super(tupleToCopy);
    }


    /**
     * A {@link Comparator} for {@link CellLocation CellLocations} using
     * row-major order.
     *
     */
    public static Comparator<CellLocation> rowMajorOrder    =

                                                            new Comparator<CellLocation>() {

                                                                @Override
                                                                public int compare(
                                                                        final CellLocation lhs,
                                                                        final CellLocation rhs) {

                                                                    final int compareRows = lhs
                                                                            .getRow()
                                                                            - rhs.getRow();

                                                                    // If rows
                                                                    // are
                                                                    // equal,
                                                                    // determine
                                                                    // order by
                                                                    // column
                                                                    // index
                                                                    // order.
                                                                    if (compareRows == 0) {

                                                                        return lhs
                                                                                .getColumn()
                                                                                - rhs.getColumn();
                                                                    }

                                                                    // If rows
                                                                    // are
                                                                    // not
                                                                    // equal,
                                                                    // determine
                                                                    // order by
                                                                    // row
                                                                    // index
                                                                    // order.
                                                                    return compareRows;
                                                                }
                                                            };



    /**
     * A {@link Comparator} for {@link CellLocation CellLocations} using
     * column-major order.
     */
    public static Comparator<CellLocation> columnMajorOrder = new Comparator<CellLocation>() {

                                                                @Override
                                                                public int compare(
                                                                        final CellLocation lhs,
                                                                        final CellLocation rhs) {

                                                                    final int compareColumns = lhs
                                                                            .getColumn()
                                                                            - rhs.getColumn();


                                                                    // If
                                                                    // columns
                                                                    // are
                                                                    // equal,
                                                                    // determine
                                                                    // order by
                                                                    // row
                                                                    // index
                                                                    // order.
                                                                    if (compareColumns == 0) {

                                                                        return lhs
                                                                                .getRow()
                                                                                - rhs.getRow();
                                                                    }

                                                                    // If
                                                                    // columns
                                                                    // are
                                                                    // not
                                                                    // equal,
                                                                    // determine
                                                                    // order by
                                                                    // column
                                                                    // index
                                                                    // order.
                                                                    return compareColumns;
                                                                }
                                                            };


    @Override
    public int compareTo(final CellLocation other) {

        return CellLocation.rowMajorOrder.compare(this, other);
    }
}
