package org.jutility.common.datatype.table;


// @formatter:off
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
// @formatter:on

import java.util.SortedSet;

import org.jutility.common.reflection.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * The generic {@code Table} class models a two-dimensional table of arbitrary
 * data.
 * <p>
 * The table is modeled as a {@link SortedSet} of {@link ICell Cells} to limit
 * the memory impact of sparse tables.
 * </p>
 *
 * @param <T>
 *            the type of the table data.
 *
 * @author Peter J. Radics
 * @version 0.1.4
 * @since 0.1.0
 */
public class Table<T>
        extends AbstractTable<Cell<T>, T> {

    /**
     * Serial Version UID.
     */
    private static final long   serialVersionUID = 929580653346817039L;

    private static final Logger LOG              = LoggerFactory
                                                         .getLogger(Table.class);


    @Override
    public boolean add(final int row, final int column, final T value) {


        return this.add(new Cell<>(row, column, value));
    }

    @Override
    public boolean add(CellLocation location, T value) {

        return this.add(
                new Cell<>(location.getRow(), location.getColumn(), value));
    }



    /**
     * Creates a new instance of the {@code Table} class with
     * {@link IterationOrder#ROW_MAJOR row-major IterationOrder}.
     */
    public Table() {

        this(IterationOrder.ROW_MAJOR);
    }

    /**
     * Creates a new instance of the {@code Table} class with the provided
     * {@link IterationOrder iteration order}.
     *
     * @param iterationOrder
     *            the {@link IterationOrder IterationOrder} of the class.
     */
    public Table(final IterationOrder iterationOrder) {

        super(iterationOrder);
    }

    /**
     * Creates a new instance of the {@code Table} class. (Copy Constructor)
     *
     * @param table
     *            the table to copy.
     */
    public Table(final ITable<? extends T> table) {

        super(table);
    }


    /**
     * Converts a table with compatible content type into a table of the desired
     * type.
     *
     * @param <S>
     *            the desired return type.
     *
     * @param table
     *            the table to convert.
     * @param type
     *            the desired return type.
     * @return the converted table or {@code null}, if the types are
     *         incompatible.
     */
    public static <S> ITable<S> convert(final ITable<? extends S> table,
            final Class<S> type) {

        return new Table<>(table);
    }

    /**
     * Returns the effective type of the provided table.
     *
     * @param table
     *            the table.
     * @return the effective type of the table (the shared ancestor class of all
     *         values in the table).
     */
    public static Class<?> getEffectiveType(final ITable<?> table) {


        Class<?> superType = null;

        for (final Object value : table) {

            final Class<?> valueClass = value.getClass();

            Table.LOG.debug("Value class: " + valueClass);
            Table.LOG.debug("Super Type: " + superType);

            if (superType == null) {

                superType = valueClass;
            }
            else {

                if (valueClass != superType) {

                    if (valueClass.isAssignableFrom(superType)) {

                        Table.LOG.debug("Can cast " + superType + " to "
                                + valueClass);
                        superType = valueClass;
                    }
                    else if (superType.isAssignableFrom(valueClass)) {

                        // nothing to do
                        Table.LOG.debug("Can cast " + valueClass + " to "
                                + superType);
                    }
                    else {

                        Table.LOG.debug("LCD of "
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
