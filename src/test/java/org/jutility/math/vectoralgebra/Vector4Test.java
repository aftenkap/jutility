package org.jutility.math.vectoralgebra;


//@formatter:off
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
//@formatter:on


import org.junit.Assert;
import org.junit.Test;


/**
 * The {@code Vector4Test} class provides unit tests for the {@link Vector4}
 * class.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class Vector4Test {

    /**
     * Test method for {@link Vector4#Vector4(ITuple4)}.
     */
    @Test
    public void testVector4fTuple4f() {

        try {
            final IVector4<Float> testVector = new Vector4<>(
                    Point4.ORIGIN(Float.class));

            Assert.fail("Shouldn't be able to copy point into vector: "
                    + testVector);
        }
        catch (final IllegalArgumentException e) {

            Assert.assertEquals("Provided parameter is not a vector!",
                    e.getMessage());
        }
    }
}
