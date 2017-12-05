package org.jutility.common.datatype.range;


//@formatter:off
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
//@formatter:on



/**
 * The {@code IInterval} interface provides a contract for all classes
 * implementing intervals. Intervals are sets with a natural order of elements
 * that are defined by their lower and upper bounds.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 *
 * @param <T>
 *            the type of the interval.
 */
public interface IInterval<T> {

    /**
     * Returns whether or not the interval contains its lower bound.
     *
     * @return {@code true} if the interval contains its lower bound;
     *         {@code false} otherwise.
     */
    Boolean containsLowerBound();

    /**
     * Returns whether or not the interval contains its upper bound.
     *
     * @return {@code true} if the interval contains its upper bound;
     *         {@code false} otherwise.
     */
    Boolean containsUpperBound();

    /**
     * Returns the lower bound of the interval.
     *
     * @return the lower bound.
     */
    T getLowerBound();

    /**
     * Returns the upper bound of the interval.
     *
     * @return the upper bound.
     */
    T getUpperBound();

    /**
     * Returns whether or not an element is contained within the interval (i.e.,
     * whether it is smaller than the upper bound and greater than the lower
     * bound).
     *
     * @param element
     *            the element to check.
     * @return {@code true} if the element is contained in the interval;
     *         {@code false} otherwise.
     */
    boolean contains(T element);

    /**
     * Returns whether or not another interval is contained within the interval
     * (i.e., whether its upper bound is smaller than the upper bound and its
     * lower bound greater than the lower bound).
     *
     * @param otherInterval
     *            the other interval to check.
     * @return {@code true} if the other interval is contained in the interval;
     *         {@code false} otherwise.
     */
    boolean contains(IInterval<T> otherInterval);

    /**
     * Determines whether this interval intersects with the other interval. <br>
     *
     * Note that it is easier to determine whether two intervals do not
     * intersect. <br>
     * <br>
     * Let {@code X = x,y; A = a,b} be two intervals with {@code x} &le; y and
     * {@code a} &le; {@code b}. <br>
     * The intervals trivially do not intersect, if
     * <ul>
     * <li>b &lt; x, or</li>
     * <li>a &gt; y</li>
     * </ul>
     * <br>
     * If either x &notin; X or b &notin; A, the intervals do not intersect if
     * <ul>
     * <li>b &le; x, or</li>
     * <li>a &gt; y</li>
     * </ul>
     * <br>
     * Similarly, if either y &notin; X or a &notin; A, the intervals do not
     * intersect if
     * <ul>
     * <li>b &lt; x, or</li>
     * <li>a &ge; y</li>
     * </ul>
     * <br>
     * Finally, if either x &notin; X or b &notin; A and y &notin; X or a
     * &notin; A, the intervals do not intersect if
     * <ul>
     * <li>b &le; x, or</li>
     * <li>a &ge; y</li>
     * </ul>
     *
     *
     * @param otherInterval
     *            The other interval.
     * @return {@code true}, if the intervals intersect. Otherwise {@code false}
     *         .
     */
    Boolean intersects(IInterval<T> otherInterval);

    /**
     * Returns whether or not another interval contains this interval (i.e.,
     * whether this interval's upper bound is smaller than the upper bound and
     * this interval's lower bound is greater than the lower bound).
     *
     * @param otherInterval
     *            the other interval to check.
     * @return {@code true} if the other interval is contained in the interval;
     *         {@code false} otherwise.
     */
    Boolean isContainedIn(IInterval<T> otherInterval);

}
