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
 * The {@code Polygon2} interface provides a contract for classes implementing
 * polygons in two-dimensional space based on two-dimensional {@link IPoint2
 * Points}.
 * 
 * @param <T>
 *            the {@link Number} type of the {@code Polygon2}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Polygon2.class, name = "Polygon2") })
public interface IPolygon2<T extends Number> {

    /**
     * Returns the type of the {@code IPolygon2}.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();


    /**
     * Returns the {@link IPoint2 Points} of this {@code IPolygon2}.
     * 
     * @return the {@link IPoint2 Points} of this {@code IPolygon2}.
     */
    public abstract List<IPoint2<T>> getPoints();

    /**
     * Adds a {@link IPoint2 Point} to the {@link IPoint2 Points} of this
     * {@code IPolygon2}.
     * 
     * @param point
     *            the {@link IPoint2 Point} to add.
     * @return whether or not the collection has been changed by this operation.
     */
    public abstract boolean addPoint(final IPoint2<? extends Number> point);

    /**
     * Removes a {@link IPoint2 Point} from the {@link IPoint2 Points} of this
     * {@code IPolygon2}.
     * 
     * @param point
     *            the {@link IPoint2 Point} to remove.
     * @return whether or not the collection has been changed by this operation.
     */
    public abstract boolean removePoint(final IPoint2<? extends Number> point);

    /**
     * Clears the {@link IPoint2 Points} of this {@code IPolygon2}.
     */
    public abstract void clearPoints();
}
