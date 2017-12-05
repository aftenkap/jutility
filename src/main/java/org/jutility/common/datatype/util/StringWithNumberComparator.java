package org.jutility.common.datatype.util;


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
import java.util.Comparator;


/**
 * The {@code StringWithNumberComparator} class provides comparisons of
 * {@link String Strings} with {@link Number Numbers}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class StringWithNumberComparator
        implements Comparator<String> {

    @Override
    public int compare(final String lhs, final String rhs) {

        return StringWithNumberComparator.compareTo(lhs, rhs);
    }

    /**
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
     * Specified by: {@link Comparator#compare} in {@link Comparator}
     *
     * @param lhs
     *            the first object to be compared.
     * @param rhs
     *            the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second.
     */
    public static final int compareTo(final String lhs, final String rhs) {

        final String[] lhsTokens = lhs.split("\\s");
        final String[] rhsTokens = rhs.split("\\s");


        int count = lhsTokens.length;

        if (rhsTokens.length < lhsTokens.length) {

            count = rhsTokens.length;
        }

        for (int i = 0; i < count; i++) {

            final String lhsToken = lhsTokens[i];
            final String rhsToken = rhsTokens[i];

            int comparison;

            try {

                final Long lhsL = Long.parseLong(lhsToken);
                final Long rhsL = Long.parseLong(rhsToken);

                comparison = lhsL.compareTo(rhsL);


            }
            catch (final NumberFormatException ex) {

                comparison = lhsToken.compareTo(rhsToken);
            }


            if (comparison != 0) {

                return comparison;
            }
        }


        return rhsTokens.length - lhsTokens.length;
    }
}
