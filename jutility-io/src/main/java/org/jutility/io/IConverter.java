package org.jutility.io;

/*
 * #%L
 * jutility-io
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



/**
 * The @ link IConverter} interface provides a contract for converters.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public interface IConverter {



    /**
     * Determines whether or not the {@link IConverter converter} supports
     * conversion from the {@link Class source type} to a given {@link Class
     * target type}.
     * 
     * @param sourceType
     *            the {@link Class source type}.
     * @param targetType
     *            the {@link Class target type}.
     * @return <code>true</code> if the converter supports the conversion;
     *         <code>false</code> otherwise.
     */
    public abstract boolean supportsConversion(Class<?> sourceType,
            Class<?> targetType);


    /**
     * Converts the provided object into an instance of the desired return type.
     * 
     * @param documentToConvert
     *            the document to be converted.
     * @param returnType
     *            the desired return type.
     * @return a document of the desired format.
     * @throws ConversionException
     *             if the return type is not supported or conversion fails.
     */
    public abstract <T, S> S convert(T documentToConvert,
            Class<? extends S> returnType)
            throws ConversionException;
}
