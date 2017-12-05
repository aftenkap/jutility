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


import org.jutility.common.datatype.tuple.ITuple2;
import org.jutility.common.datatype.tuple.Tuple2;

import java.util.Comparator;


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
     * Serial Version UID.
     */
    private static final long serialVersionUID = 7102510552022429104L;


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
     *         the row index.
     * @param column
     *         the column index.
     */
    public CellLocation(final int row, final int column) {

        super(row, column, Integer.class);
    }

    /**
     * Creates a new instance of the {@code CellLocation} class as a copy of the
     * provided {@link ITuple2} {@code <}{@link Integer} {@code >}.
     *
     * @param tupleToCopy
     *         the {@link ITuple2} {@code <}{@link Integer} {@code >} to copy.
     */
    public CellLocation(final ITuple2<Integer> tupleToCopy) {

        super(tupleToCopy);
    }


    /**
     * A {@link Comparator} for {@link CellLocation CellLocations} using
     * row-major order.
     */
    public static final Comparator<CellLocation> rowMajorOrder = Comparator
            .comparing(CellLocation::getRow)
            .thenComparing(CellLocation::getColumn);


    /**
     * A {@link Comparator} for {@link CellLocation CellLocations} using
     * column-major order.
     */
    public static final Comparator<CellLocation> columnMajorOrder = Comparator
            .comparing(CellLocation::getColumn)
            .thenComparing(CellLocation::getRow);


    @Override
    public int compareTo(final CellLocation other) {

        return CellLocation.rowMajorOrder.compare(this, other);
    }
}
