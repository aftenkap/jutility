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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@code ITranslation} interface provides a contract for classes that
 * implement the translation of an object.
 * 
 * @param <T>
 *            the {@link Number} type of this {@code ITranslation}.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Translation.class,
        name = "Translation") })
public interface ITranslation<T extends Number> {

    /**
     * Returns the type of this translation.
     * 
     * @return the type.
     */
    Class<? extends T> getType();

    /**
     * The translation in the x-dimension.
     * 
     * @return the translation in the x-dimension.
     */
    T getXTranslation();


    /**
     * The translation in the y-dimension.
     * 
     * @return the translation in the y-dimension.
     */
    T getYTranslation();


    /**
     * The translation in the x-dimension.
     * 
     * @return the translation in the y-dimension.
     */
    T getZTranslation();
}
