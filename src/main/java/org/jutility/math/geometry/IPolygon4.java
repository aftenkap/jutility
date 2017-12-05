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

import org.jutility.math.vectoralgebra.IPoint4;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@code Polygon4} interface provides a contract for classes implementing
 * polygons in three-dimensional space based on {@link IPoint4 Points} in
 * homogeneous representation.
 * 
 * @param <T>
 *            the {@link Number} type of the {@code Polygon4}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Polygon4.class, name = "Polygon4") })
public interface IPolygon4<T extends Number> {

    /**
     * Returns the type of the {@code IPolygon4}.
     * 
     * @return the type.
     */
    Class<? extends T> getType();


    /**
     * Returns the {@link IPoint4 Points} of this {@link IPolygon4 Polygon}.
     * 
     * @return the {@link IPoint4 Points} of this {@link IPolygon4 Polygon}.
     */
    List<IPoint4<T>> getPoints();

    /**
     * Adds a {@link IPoint4 Point} to the {@link IPoint4 Points} of this
     * {@code IPolygon4}.
     * 
     * @param point
     *            the {@link IPoint4 Point} to add.
     * @return whether or not the collection has been changed by this operation.
     */
    boolean addPoint(IPoint4<? extends Number> point);

    /**
     * Removes a {@link IPoint4 Point} from the {@link IPoint4 Points} of this
     * {@code IPolygon4}.
     * 
     * @param point
     *            the {@link IPoint4 Point} to remove.
     * @return whether or not the collection has been changed by this operation.
     */
    boolean removePoint(IPoint4<? extends Number> point);

    /**
     * Clears the {@link IPoint4 Points} of this {@code IPolygon4}.
     */
    void clearPoints();
}
