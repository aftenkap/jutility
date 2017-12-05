package org.jutility.math.geometry;


//@formatter:off
/*
* #%L
 * * jutility-math
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * *
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



import java.util.List;

import org.jutility.math.vectoralgebra.IPoint2;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@code PolyLine2} interface provides a contract for classes implementing
 * polygons in two-dimensional space based on two-dimensional {@link IPoint2
 * Points}.
 * 
 * @param <T>
 *            the {@link Number} type of the {@code PolyLine2}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = PolyLine2.class, name = "PolyLine2") })
public interface IPolyLine2<T extends Number> {

    /**
     * Returns the type of the {@code IPolyLine2}.
     * 
     * @return the type.
     */
    Class<? extends T> getType();


    /**
     * Returns the {@link IPoint2 Points} of this {@code IPolyLine2}.
     * 
     * @return the {@link IPoint2 Points} of this {@code IPolyLine2}.
     */
    List<IPoint2<T>> getPoints();

    /**
     * Adds a {@link IPoint2 Point} to the {@link IPoint2 Points} of this
     * {@code IPolyLine2}.
     * 
     * @param point
     *            the {@link IPoint2 Point} to add.
     * @return whether or not the collection has been changed by this operation.
     */
    boolean addPoint(final IPoint2<? extends Number> point);

    /**
     * Removes a {@link IPoint2 Point} from the {@link IPoint2 Points} of this
     * {@code IPolyLine2}.
     * 
     * @param point
     *            the {@link IPoint2 Point} to remove.
     * @return whether or not the collection has been changed by this operation.
     */
    boolean removePoint(final IPoint2<? extends Number> point);

    /**
     * Clears the {@link IPoint2 Points} of this {@code IPolyLine2}.
     */
    void clearPoints();
}
