package org.jutility.math.geometry;


/*
 * #%L jutility-math %% Copyright (C) 2013 - 2014 jutility.org %% Licensed under
 * the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. #L%
 */


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


/**
 * The {@link IShearFactor} interface provides a contract for classes
 * implementing a shear around a certain component.
 * 
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 * @param <T>
 *            the type of the shear.
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY,
        property = "implementingType")
@JsonSubTypes({ @JsonSubTypes.Type(value = ShearFactor.class,
        name = "ShearFactor") })
public interface IShearFactor<T extends Number> {

    /**
     * Returns the type of the {@code ShearFactor}.
     * 
     * @return the type.
     */
    Class<? extends T> getType();

    /**
     * Returns the shear coefficient.
     * 
     * @return the shear coefficient.
     */
    T getShearCoefficient();


    /**
     * Returns the identifier of the shear
     * 
     * @return the identifier.
     */
    ShearComponent getShearComponent();
}
