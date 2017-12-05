package org.jutility.math.vectoralgebra;


//@formatter:off
/*
* #%L
 * * jutility-math
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


import java.util.Comparator;

import org.jutility.common.datatype.util.NumberComparator;

import com.fasterxml.jackson.annotation.JsonSubTypes;



/**
 * The generic {@code ITuple2} interface provides a contract for all classes
 * implementing two-dimensional numeric tuples.
 *
 * @param <T>
 *         the {@link Number} type of the {@code ITuple2}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */

@JsonSubTypes({ @JsonSubTypes.Type(value = Tuple2.class, name = "Tuple2") })
public interface ITuple2<T extends Number>
        extends org.jutility.common.datatype.tuple.ITuple2<T> {

    /**
     * Returns whether the tuple is a point.
     *
     * @return {@code true}, if the tuple is a point; {@code false} otherwise.
     */
    boolean isPoint();


    /**
     * Returns whether the tuple is a vector.
     *
     * @return {@code true}, if the tuple is a vector; {@code false} otherwise.
     */
    boolean isVector();



    /**
     * Compares points by x, then y.
     */
    Comparator<ITuple2<?>> byX =

            (lhs, rhs) -> {

                final int comparison = NumberComparator.compareTo(lhs.getX(),
                        rhs.getX());

                if (comparison == 0) {

                    return NumberComparator.compareTo(lhs.getY(), rhs.getY());
                }

                return comparison;
            };
    /**
     * Compares points by x, then y.
     */
    Comparator<ITuple2<?>> byY =

            (lhs, rhs) -> {

                final int comparison = NumberComparator.compareTo(lhs.getY(),
                        rhs.getY());

                if (comparison == 0) {

                    return NumberComparator.compareTo(lhs.getX(), rhs.getX());
                }

                return comparison;
            };
}
