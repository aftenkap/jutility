package org.jutility.common.datatype.range;



/**
 * The {@link IInterval} interface provides a contract for all classes
 * implementing intervals. Intervals are sets with a natural order of elements
 * that are defined by their lower and upper bounds.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @param <T>
 *            the type of the interval.
 */
public interface IInterval<T> {

    /**
     * Returns whether or not the interval contains its lower bound.
     * 
     * @return <code>true</code> if the interval contains its lower bound;
     *         <code>false</code> otherwise.
     */
    public abstract Boolean containsLowerBound();

    /**
     * Returns whether or not the interval contains its upper bound.
     * 
     * @return <code>true</code> if the interval contains its upper bound;
     *         <code>false</code> otherwise.
     */
    public abstract Boolean containsUpperBound();

    /**
     * Returns the lower bound of the interval.
     * 
     * @return the lower bound.
     */
    public abstract T getLowerBound();

    /**
     * Returns the upper bound of the interval.
     * 
     * @return the upper bound.
     */
    public abstract T getUpperBound();

    /**
     * Returns whether or not an element is contained within the interval (i.e.,
     * whether it is smaller than the upper bound and greater than the lower
     * bound).
     * 
     * @param element
     *            the element to check.
     * @return <code>true</code> if the element is contained in the interval;
     *         <code>false</code> otherwise.
     */
    public abstract boolean contains(T element);

    /**
     * Returns whether or not another interval is contained within the interval
     * (i.e., whether its upper bound is smaller than the upper bound and its
     * lower bound greater than the lower bound).
     * 
     * @param otherInterval
     *            the other interval to check.
     * @return <code>true</code> if the other interval is contained in the
     *         interval; <code>false</code> otherwise.
     */
    public abstract boolean contains(IInterval<T> otherInterval);

    /**
     * Determines whether this interval intersects with the other interval.
     * 
     * Let X = x,y; A = a,b be two intervals with x <= y and a <= b. The
     * intervals trivially do not intersect, if it holds that b < x || a > y
     * However, the relation changes to b <= x || a > y if either x nE X or b nE
     * A. It changes to b < x || a >= y if either y nE X or a nE A. It changes
     * to b <= x || a >= y if either x nE X or b nE A and y nE X or a nE A.
     * 
     * 
     * @param otherInterval
     *            The other interval.
     * @return <code>true</code>, if the intervals intersect. Otherwise
     *         <code>false</code>.
     */
    public abstract Boolean intersects(IInterval<T> otherInterval);

    /**
     * Returns whether or not another interval contains this interval (i.e.,
     * whether this interval's upper bound is smaller than the upper bound and
     * this interval's lower bound is greater than the lower bound).
     * 
     * @param otherInterval
     *            the other interval to check.
     * @return <code>true</code> if the other interval is contained in the
     *         interval; <code>false</code> otherwise.
     */
    public abstract Boolean isContainedIn(IInterval<T> otherInterval);

}