package org.jutility.math.vectorAlgebra;

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


import static org.junit.Assert.*;

import org.junit.Test;
import org.jutility.math.vectorAlgebra.IVector4;
import org.jutility.math.vectorAlgebra.Point4f;
import org.jutility.math.vectorAlgebra.Vector4f;


/**
 * Test harness for the Vector4f class.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class Vector4fTest {

    /**
     * Test method for
     * {@link org.jutility.math.vectorAlgebra.Vector4f#Vector4f(org.jutility.math.vectorAlgebra.Tuple4f)}
     * .
     */
    @Test
    public void testVector4fTuple4f() {

        try {
            IVector4<Float> testVector = new Vector4f(Point4f.ORIGIN);

            fail("Shouldn't be able to copy point into vector: " + testVector);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Provided parameter is not a vector!", e.getMessage());
        }
    }

}
