package org.jutility.math.geometry;


// @formatter:off
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
// @formatter:on


import org.jutility.math.vectoralgebra.IPoint2;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@code IRectangle2} interface provides a contract for classes
 * implementing rectangles in two-dimensional space based on two-dimensional
 * {@link IPoint2 Points}.
 * 
 * @param <T>
 *            the {@link Number} type of the {@code IRectangle2}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Rectangle2.class,
        name = "Rectangle2") })
public interface IRectangle2<T extends Number> {

    /**
     * Returns the type of the {@code IRectangle2}.
     * 
     * @return the type.
     */
    Class<? extends T> getType();


    /**
     * Returns the top-left corner of this rectangle.
     * 
     * @return the top-left corner.
     */
    IPoint2<T> getTopLeftCorner();

    /**
     * Returns the bottom-left corner of this rectangle.
     * 
     * @return the bottom-left corner.
     */
    IPoint2<T> getBottomLeftCorner();

    /**
     * Returns the top-right corner of this rectangle.
     * 
     * @return the top-right corner.
     */
    IPoint2<T> getTopRightCorner();

    /**
     * Returns the bottom-right corner of this rectangle.
     * 
     * @return the bottom-right corner.
     */
    IPoint2<T> getBottomRightCorner();
}
