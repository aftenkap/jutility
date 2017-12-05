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
import org.jutility.math.vectoralgebra.IPoint4;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@code ILine4} interface provides a contract for classes implementing
 * lines in three-dimensional space based on {@link IPoint4 Points} in
 * homogeneous representation.
 * 
 * @param <T>
 *            the {@link Number} type of the {@code Line}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Line4.class, name = "Line4") })
public interface ILine4<T extends Number> {

    /**
     * Returns the type of the line.
     * 
     * @return the type.
     */
    Class<? extends T> getType();

    /**
     * Returns the source {@link IPoint4 Point} of this line.
     * 
     * @return the source{@link IPoint4 Point}.
     */
    IPoint4<T> getSource();


    /**
     * Returns the sink {@link IPoint4 Point} of this line.
     * 
     * @return the sink {@link IPoint4 Point}.
     */
    IPoint4<T> getSink();
}
