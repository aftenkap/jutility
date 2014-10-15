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


import java.util.SortedSet;

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
 */
public class Table<T>
        extends AbstractTable<Cell<T>, T> {

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.datatypes.table.ITable#add(int, int, java.lang.Object)
     */
    @Override
    public boolean add(int row, int column, T value) {


        return this.add(new Cell<T>(row, column, value));
    }
    
    

    /**
     * Creates a new instance of the {@link Table} class with
     * {@link IterationOrder#ROW_MAJOR row-major IterationOrder}.
     */
    public Table() {

        this(IterationOrder.ROW_MAJOR);
    }

    /**
     * Creates a new instance of the {@link Table} class with the provided
     * {@link IterationOrder iteration order}.
     * 
     * @param iterationOrder
     *            the {@link IterationOrder IterationOrder} of the class.
     */
    public Table(IterationOrder iterationOrder) {

        super(iterationOrder);
    }

    /**
     * Creates a new instance of the {@link Table} class. (Copy Constructor)
     * 
     * @param table
     *            the table to copy.
     */
    public Table(ITable<? extends T> table) {

        super(table);
    }


    /**
     * Converts a table with compatible content type into a table of the desired
     * type.
     * 
     * @param table
     *            the table to convert.
     * @param type
     *            the desired return type.
     * @return the converted table or {@code null}, if the types are
     *         incompatible.
     */
    public static <S> ITable<S> convert(ITable<? extends S> table, Class<S> type) {

        return new Table<S>(table);
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
