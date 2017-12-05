package org.jutility.common.datatype.range;


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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberComparator;



/**
 * The generic {@code Interval} class provides a reference implementation of the
 * {@link IInterval} interface for {@link Number Numbers}.
 *
 * @param <T>
 *            the type of the interval
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@XmlRootElement(name = "Interval")
@XmlType(name = "Interval")
public class Interval<T extends Number>
        extends IntervalBase<T> {


    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 614470392681595042L;


    /**
     * Constructs a new instance of the {@code Interval} class. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private Interval() {

        this(null, null, true, true, true);
    }

    /**
     * Constructs a new instance of the {@code Interval} class with the
     * specified bounds. By default, the interval contains its bounds.
     *
     * @param lowerBound
     *            the lower bound.
     * @param upperBound
     *            the upper bound.
     */
    public Interval(final T lowerBound, final T upperBound) {

        this(lowerBound, upperBound, true, true);
    }

    /**
     * Constructs a new instance of the {@code Interval} class with the
     * specified bounds. This constructor allows to specify whether the
     * lower/upper bound are part of the interval or not.
     *
     * @param lowerBound
     *            the lower bound.
     * @param upperBound
     *            the upper bound.
     * @param includeLowerBound
     *            whether the lower bound is part of the interval.
     * @param includeUpperBound
     *            whether the upper bound is part of the interval.
     */
    public Interval(final T lowerBound, final T upperBound,
            final boolean includeLowerBound, final boolean includeUpperBound) {

        this(lowerBound, upperBound, includeLowerBound, includeUpperBound,
                false);
    }

    /**
     * Constructs a new instance of the {@code Interval} class with the
     * specified bounds. This constructor allows to specify whether the
     * lower/upper bound are part of the interval or not.
     *
     * @param lowerBound
     *            the lower bound.
     * @param upperBound
     *            the upper bound.
     * @param includeLowerBound
     *            whether the lower bound is part of the interval.
     * @param includeUpperBound
     *            whether the upper bound is part of the interval.
     * @param serialization
     *            whether or not the constructor is used during serialization.
     */
    private Interval(final T lowerBound, final T upperBound,
            final boolean includeLowerBound, final boolean includeUpperBound,
            final boolean serialization) {

        super(lowerBound, upperBound, includeLowerBound, includeUpperBound,
                serialization);
    }


    @Override
    public int compareTo(final Number lhs, final Number rhs) {

        return NumberComparator.compareTo(lhs, rhs);
    }

    /**
     * Factory method for the creation of arbitrary intervals.
     *
     * @param <S>
     *            the desired return type.
     *
     * @param lowerBound
     *            the lower bound.
     * @param upperBound
     *            the upper bound.
     * @param includeLowerBound
     *            whether or not to include the lower bound.
     * @param includeUpperBound
     *            whether or not to include the upper bound.
     * @return the new interval.
     */
    public static <S extends Number> Interval<S> createInterval(
            final S lowerBound, final S upperBound,
            final boolean includeLowerBound, final boolean includeUpperBound) {

        return new Interval<>(lowerBound, upperBound, includeLowerBound,
                includeUpperBound);
    }

    /**
     * Factory method for the creation of arbitrary intervals.
     *
     * @param <S>
     *            the desired return type.
     *
     * @param lowerBound
     *            the lower bound.
     * @param upperBound
     *            the upper bound.
     * @return the new interval.
     */
    public static <S extends Number> Interval<S> createInterval(
            final S lowerBound, final S upperBound) {

        return Interval.createInterval(lowerBound, upperBound, true, true);
    }
}
