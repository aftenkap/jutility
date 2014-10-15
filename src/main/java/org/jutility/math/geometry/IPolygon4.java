/**
 * 
 */
package org.jutility.math.geometry;

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


import java.util.List;

import org.jutility.math.vectorAlgebra.IPoint4;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 * @param <T>
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Polygon4.class, name = "Polygon4") })
public interface IPolygon4<T extends Number> {

    /**
     * Returns the type of the rectangle.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();


    /**
     * Returns the {@link IPoint4 Points} of this {@link IPolygon4 Polygon}.
     * 
     * @return the {@link IPoint4 Points} of this {@link IPolygon4 Polygon}.
     */
    public abstract List<IPoint4<T>> getPoints();

    /**
     * Adds a {@link IPoint4 Point} to the {@link IPoint4 Points} of this
     * {@link IPolygon4 Polygon}.
     * 
     * @param point
     *            the {@link IPoint4 Point} to add.
     * @return whether or not the collection has been changed by this operation.
     */
    public abstract boolean addPoint(IPoint4<? extends Number> point);

    /**
     * Removes a {@link IPoint4 Point} from the {@link IPoint4 Points} of this
     * {@link IPolygon4 Polygon}.
     * 
     * @param point
     *            the {@link IPoint4 Point} to remove.
     * @return whether or not the collection has been changed by this operation.
     */
    public abstract boolean removePoint(IPoint4<? extends Number> point);

    /**
     * Clears the {@link IPoint4 Points} of this {@link IPolygon4 Polygon}.
     */
    public abstract void clearPoints();

}
