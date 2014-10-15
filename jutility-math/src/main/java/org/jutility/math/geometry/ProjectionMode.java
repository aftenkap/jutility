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


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * The {@link ProjectionMode} enum enumerates over the different possible
 * projection modes.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlEnum
public enum ProjectionMode {
    /**
     * 2D orthographic projection.
     */
    @XmlEnumValue(value = "Orthographic2D")
    ORTHOGRAPHIC_2D,
    /**
     * 3D orthographic projection.
     */
    @XmlEnumValue(value = "Orthographic3D")
    ORTHOGRAPHIC_3D,
    /**
     * 3D perspective projection.
     */
    @XmlEnumValue(value = "Perspective")
    PERSPECTIVE;
}
