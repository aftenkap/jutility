package org.jutility.common.datatype.util;

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
     * Specified by: {@link Comparator#compare} in {@link Comparator}
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


        return  rhsTokens.length - lhsTokens.length;
    }

}
