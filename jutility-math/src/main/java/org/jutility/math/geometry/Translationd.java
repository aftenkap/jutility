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
 * The {@link Translationd} class is a convenience implementation of the
 * {@link ITranslation} interface for {@link Double Doubles}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
@XmlType(name = "Translationd")
public class Translationd
        extends Translation<Double>
        implements ITranslation<Double> {



    /**
     * Constructs a new instance of the {@link Translationd} class that
     * represents no translation.
     */
    public Translationd() {

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
    public Translationd(double xTranslation, double yTranslation,
            double zTranslation) {

        super(xTranslation, yTranslation, zTranslation, Double.class);
    }

    /**
     * Copy Constructor.
     * 
     * @param translationToCopy
     *            the tuple to copy.
     */
    public Translationd(final ITranslation<Double> translationToCopy) {

        super(translationToCopy);
    }
}
