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


/**
 * The {@code NumberConstants} class provides constants for the math package.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public final class NumberConstants {

    /**
     * Precision for float comparisons.
     */
    private static final float DELTA_F = 0.0001f;

    /**
     * Precision for double comparisons.
     */
    private static final double DELTA_D = 0.0001;

    private static Float deltaF;

    private static Double deltaD;

    /**
     * Sets the runtime precision for {@link Float} comparisons.
     *
     * @param deltaF
     *         the desired precision (delta).
     */
    public static synchronized void setDeltaF(float deltaF) {

        NumberConstants.deltaF = Math.abs(deltaF);
    }

    /**
     * Sets the runtime precision for {@link Double} comparisons.
     *
     * @param deltaD
     *         the desired precision (delta).
     */
    public static synchronized void setDeltaD(double deltaD) {

        NumberConstants.deltaD = Math.abs(deltaD);
    }

    /**
     * Returns the runtime precision for {@link Float} comparisons.
     *
     * @return  the precision (delta).
     */
    public static synchronized float deltaF() {

        return NumberConstants.deltaF != null ? NumberConstants.deltaF : DELTA_F;
    }

    /**
     * Returns the runtime precision for {@link Double} comparisons.
     *
     * @return  the precision (delta).
     */
    public static synchronized double deltaD() {

        return NumberConstants.deltaD != null ? NumberConstants.deltaD : DELTA_D;
    }
}
