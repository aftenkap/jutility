package org.jutility.common.datatype.range;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The abstract generic {@link IntervalBase} class provides a reference base
 * implementation for the {@link IInterval} interface.
 * 
 * @author Peter J. Radics
 * @param <T>
 *            the type of the interval
 */
@XmlRootElement(name = "IntervalBase")
@XmlType(name = "IntervalBase", propOrder = { "lowerBound", "upperBound" })
public abstract class IntervalBase<T>
        implements IInterval<T> {

    @XmlElement(name = "LowerBound")
    private T       lowerBound;
    @XmlElement(name = "UpperBound")
    private T       upperBound;

    @XmlAttribute
    private Boolean containsLowerBound;
    @XmlAttribute
    private Boolean containsUpperBound;


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.common.IInterval#containsLowerBound()
     */
    @Override
    public Boolean containsLowerBound() {

        return containsLowerBound;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.common.IInterval#containsUpperBound()
     */
    @Override
    public Boolean containsUpperBound() {

        return containsUpperBound;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.common.IInterval#getLowerBound()
     */
    @Override
    public T getLowerBound() {

        return lowerBound;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.common.IInterval#getUpperBound()
     */
    @Override
    public T getUpperBound() {

        return upperBound;
    }

    /**
     * Constructs a new instance of the <code>IntervalBase</code> class.
     * (Serialization Constructor)
     */
    @SuppressWarnings("unused")
    private IntervalBase() {

        this(null, null, true, true);
    }

    /**
     * Constructs a new instance of the <code>IntervalBase</code> class with the
     * specified bounds. By default, the interval contains its bounds.
     * 
     * @param lowerBound
     *            the lower bound.
     * @param upperBound
     *            the upper bound.
     */
    public IntervalBase(final T lowerBound, final T upperBound) {

        this(lowerBound, upperBound, true, true);
    }

    /**
     * Constructs a new instance of the <code>IntervalBase</code> class with the
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
    public IntervalBase(final T lowerBound, final T upperBound,
            final boolean includeLowerBound, final boolean includeUpperBound) {

        this(lowerBound, upperBound, includeLowerBound, includeUpperBound,
                false);
    }

    /**
     * Constructs a new instance of the <code>IntervalBase</code> class with the
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
    protected IntervalBase(final T lowerBound, final T upperBound,
            final boolean includeLowerBound, final boolean includeUpperBound,
            final boolean serialization) {

        boolean lowerBoundNull = (lowerBound == null);
        boolean upperBoundNull = (upperBound == null);

        if (lowerBoundNull && !serialization) {
            throw new IllegalArgumentException("Cannot create an interval "
                    + "without lower bound!");

        }

        if (upperBoundNull && !serialization) {
            throw new IllegalArgumentException("Cannot create an interval "
                    + "without upper bound!");
        }
        if (!lowerBoundNull && !upperBoundNull
                && this.compareTo(lowerBound, upperBound) <= 0) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
        else {
            this.lowerBound = upperBound;
            this.upperBound = lowerBound;
        }

        if (!lowerBoundNull && !upperBoundNull
                && this.lowerBound.equals(this.upperBound)) {
            this.containsLowerBound = true;
            this.containsUpperBound = true;
        }
        else {
            this.containsLowerBound = includeLowerBound;
            this.containsUpperBound = includeUpperBound;
        }
    }

    /**
     * Compares the two values
     * 
     * @param lhs
     *            the left-hand side
     * @param rhs
     *            the right-hand side
     * @return the comparison.
     * @see Comparable
     */
    protected abstract int compareTo(final T lhs, final T rhs);



    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.common.IInterval#contains(T)
     */
    @Override
    public boolean contains(final T element) {

        int compareToLowerBound = this.compareTo(this.getLowerBound(), element);
        int compareToUpperBound = this.compareTo(this.getUpperBound(), element);

        // if our lower bound is greater than the element, the element is not
        // contained in the interval.
        if (compareToLowerBound > 0) {
            return false;
        }
        // if the element is equal to our lower bound and the lower bound is not
        // part of the interval, the element is not contained in the interval.
        else if (compareToLowerBound == 0 && !this.containsLowerBound()) {
            return false;
        }
        // if the element is greater than our upper bound, the element is not
        // contained in the interval.
        else if (compareToUpperBound < 0) {
            return false;
        }
        // if the element is equal to our upper bound and the upper bound is not
        // part of the interval, the element is not contained in the interval.
        else if (compareToUpperBound == 0 && !this.containsUpperBound()) {
            return false;
        }

        return true;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jutility.math.common.IInterval#contains(org.jutility.math.common.IInterval)
     */
    @Override
    public boolean contains(IInterval<T> otherInterval) {

        int compareToLowerBound = this.compareTo(this.getLowerBound(),
                otherInterval.getLowerBound());
        int compareToUpperBound = this.compareTo(this.getUpperBound(),
                otherInterval.getUpperBound());

        System.out.println("Lower bound comparison: " + compareToLowerBound);
        System.out.println("Upper bound comparison: " + compareToUpperBound);

        // if our lower bound is greater than the other interval's lower bound,
        // it cannot be contained in the interval.
        if (compareToLowerBound > 0) {
            return false;
        }
        // if our lower bound equals the other interval's lower bound, and if
        // the bound is included either in both intervals or only in the other
        // interval, it cannot be contained in the interval
        else if (compareToLowerBound == 0
                && (this.containsLowerBound().compareTo(
                        otherInterval.containsLowerBound()) < 0)) {
            return false;
        }
        // if our upper bound is smaller than the other interval's upper bound,
        // it cannot be contained in the interval.
        else if (compareToUpperBound < 0) {
            return false;
        }
        // if our lower bound equals the other interval's lower bound, and if
        // the bound is included either in both intervals or only in the other
        // interval, it cannot be contained in the interval
        else if (compareToUpperBound == 0
                && (this.containsUpperBound().compareTo(
                        otherInterval.containsUpperBound()) < 0)) {
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.math.common.IInterval#intersects(org.jutility.math.common.IInterval)
     */
    @Override
    public Boolean intersects(final IInterval<T> otherInterval) {

        T x = this.getLowerBound();
        T y = this.getUpperBound();

        T a = otherInterval.getLowerBound();
        T b = otherInterval.getUpperBound();


        Boolean greater = (this.containsUpperBound() && otherInterval
                .containsLowerBound());
        Boolean smaller = (this.containsLowerBound() && otherInterval
                .containsUpperBound());

        if (greater && smaller) {
            return !((this.compareTo(b, x) < 0) || (this.compareTo(a, y) > 0));
        }
        else if (greater && !smaller) {
            return !((this.compareTo(b, x) <= 0) || (this.compareTo(a, y) > 0));
        }
        else if (!greater && smaller) {
            return !((this.compareTo(b, x) < 0) || (this.compareTo(a, y) >= 0));
        }
        else {
            return !((this.compareTo(b, x) <= 0) || (this.compareTo(a, y) >= 0));
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.jutility.math.common.IInterval#isContainedIn(org.jutility.math.common.IInterval)
     */
    @Override
    public Boolean isContainedIn(final IInterval<T> otherInterval) {

        if (otherInterval == null) {
            return false;
        }
        return otherInterval.contains(this);
    }



    @Override
    public boolean equals(final Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        else {
            final IInterval<?> other = (IInterval<?>) obj;

            return (this.containsLowerBound.equals(other.containsLowerBound())
                    && this.containsUpperBound.equals(other
                            .containsUpperBound())
                    && this.lowerBound.equals(other.getLowerBound()) && this.upperBound
                        .equals(other.getUpperBound()));
        }
    }

    @Override
    public int hashCode() {

        int hash = 5;
        hash = 29 * hash + this.lowerBound.hashCode();
        hash = 17 * hash + this.upperBound.hashCode();
        hash = 23 * hash + this.containsLowerBound.hashCode();
        hash = 41 * hash + this.containsUpperBound.hashCode();
        return hash;
    }


    @Override
    public String toString() {

        String stringValue = "";
        if (containsLowerBound) {
            stringValue += "[";
        }
        else {
            stringValue += "(";
        }


        if (lowerBound.equals(upperBound)) {
            stringValue += lowerBound;
        }
        else {
            stringValue += lowerBound;

            stringValue += ", ";

            stringValue += upperBound;
        }


        if (containsUpperBound) {
            stringValue += "]";
        }
        else {
            stringValue += ")";
        }

        return stringValue;
    }
}
