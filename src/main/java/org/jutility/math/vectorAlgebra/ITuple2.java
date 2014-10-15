package org.jutility.math.vectorAlgebra;

/*
 * #%L
 * jutility-math
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


import java.util.Comparator;

import org.jutility.common.datatype.util.NumberComparator;

import com.fasterxml.jackson.annotation.JsonSubTypes;



/**
 * The generic {@link ITuple2} interface provides a contract for all classes
 * implementing two-dimensional numeric tuples.
 * <p/>
 * This is a tagging interface.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @param <T>
 *            the type of the tuple.
 */

@JsonSubTypes({ @JsonSubTypes.Type(value = Tuple2.class, name = "Tuple2") })
public interface ITuple2<T extends Number>
        extends org.jutility.common.datatype.tuple.ITuple2<T> {

    /**
     * Returns whether the tuple was intended to be a point.
     * 
     * @return <code>true</code>, if the tuple was intended to be a point;
     *         <code>false</code> otherwise.
     */
    public abstract boolean isPoint();

    /**
     * Returns whether the tuple was intended to be a vector.
     * 
     * @return <code>true</code>, if the tuple was intended to be a vector;
     *         <code>false</code> otherwise.
     */
    public abstract boolean isVector();



    /**
     * Compares points by x, then y.
     */
    public static final Comparator<ITuple2<?>> byX =

                                                   new Comparator<ITuple2<?>>() {

                                                       @Override
                                                       public int compare(
                                                               ITuple2<?> lhs,
                                                               ITuple2<?> rhs) {

                                                           int comparison = NumberComparator
                                                                   .compareTo(
                                                                           lhs.getX(),
                                                                           rhs.getX());

                                                           if (comparison == 0) {

                                                               return NumberComparator
                                                                       .compareTo(
                                                                               lhs.getY(),
                                                                               rhs.getY());
                                                           }

                                                           return comparison;
                                                       };
                                                   };
    /**
     * Compares points by x, then y.
     */
    public static final Comparator<ITuple2<?>> byY =

                                                   new Comparator<ITuple2<?>>() {

                                                       @Override
                                                       public int compare(
                                                               ITuple2<?> lhs,
                                                               ITuple2<?> rhs) {

                                                           int comparison = NumberComparator
                                                                   .compareTo(
                                                                           lhs.getY(),
                                                                           rhs.getY());

                                                           if (comparison == 0) {

                                                               return NumberComparator
                                                                       .compareTo(
                                                                               lhs.getX(),
                                                                               rhs.getX());
                                                           }

                                                           return comparison;
                                                       };
                                                   };
}
