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


import javax.xml.bind.annotation.XmlType;


/**
 * The {@link Translationf} class is a convenience implementation of the
 * {@link ITranslation} interface for {@link Float Floats}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Translationf")
public class Translationf
        extends Translation<Float>
        implements ITranslation<Float> {



    /**
     * Constructs a new instance of the {@link Translationf} class that
     * represents no translation.
     */
    public Translationf() {

        this(0, 0, 0);
    }


    /**
     * Constructs a new instance of the {@link Translationd} class with the
     * provided parameters.
     * 
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     */
    public Translationf(float xTranslation, float yTranslation,
            float zTranslation) {

        super(xTranslation, yTranslation, zTranslation, Float.class);
    }

    /**
     * Copy Constructor.
     * 
     * @param translationToCopy
     *            the tuple to copy.
     */
    public Translationf(final ITranslation<Float> translationToCopy) {

        super(translationToCopy);
    }
}
