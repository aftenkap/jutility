/**
 * 
 */
package org.jutility.common.datatype.util;


import java.util.Comparator;


/**
 * @author Peter J. Radics
 * 
 */
public class StringWithNumberComparator
        implements Comparator<String> {

    @Override
    public int compare(String lhs, String rhs) {

        return StringWithNumberComparator.compareTo(lhs, rhs);
    }

    /**
     * 
     * Compares its two arguments for order. Returns a negative integer, zero,
     * or a positive integer as the first argument is less than, equal to, or
     * greater than the second.
     * <p>
     * In the foregoing description, the notation {@code sgn(expression)}
     * designates the mathematical signum function, which is defined to return
     * one of -1, 0, or 1 according to whether the value of expression is
     * negative, zero or positive.
     * <p>
     * The implementor must ensure that
     * {@code sgn(compare(x, y)) == -sgn(compare(y,
     * x))} for all x and y. (This implies that {@code compare(x, y)} must throw
     * an exception if and only if {@code compare(y, x)} throws an exception.)
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * {@code ((compare(x, y)>0) && (compare(y, z)>0))} implies
     * {@code compare(x, z)>0}.
     * <p>
     * Finally, the implementor must ensure that {@code compare(x, y)==0}
     * implies that {@code sgn(compare(x, z))==sgn(compare(y, z))} for all z.
     * <p>
     * It is generally the case, but not strictly required that
     * {@code (compare(x,
     * y)==0) == (x.equals(y))}. Generally speaking, any comparator that
     * violates this condition should clearly indicate this fact. The
     * recommended language is
     * "Note: this comparator imposes orderings that are inconsistent with equals."
     * 
     * Specified by: {@link NumberComparator#compare} in {@link NumberComparator}
     * 
     * @param lhs
     *            the first object to be compared.
     * @param rhs
     *            the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second.
     */
    public static int compareTo(String lhs, String rhs) {

        String[] lhsTokens = lhs.split("\\s");
        String[] rhsTokens = rhs.split("\\s");


        int count = lhsTokens.length;

        if (rhsTokens.length < lhsTokens.length) {

            count = rhsTokens.length;
        }

        for (int i = 0; i < count; i++) {

            String lhsToken = lhsTokens[i];
            String rhsToken = rhsTokens[i];

            int comparison = 0;

            try {

                Long lhsL = Long.parseLong(lhsToken);
                Long rhsL = Long.parseLong(rhsToken);

                comparison = lhsL.compareTo(rhsL);


            }
            catch (NumberFormatException ex) {

                comparison = lhsToken.compareTo(rhsToken);
            }


            if (comparison != 0) {

                return comparison;
            }
        }


        return Integer.compare(lhsTokens.length, rhsTokens.length);
    }

}
