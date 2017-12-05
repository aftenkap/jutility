package org.jutility.io;


// @formatter:off
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
//@formatter:on


import java.io.File;
import java.net.URI;
import java.net.URL;


/**
 * The {@code ISerializer} interface provides a common contract for classes
 * implementing serialization for a specific type.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public interface ISerializer {



    /**
     * Determines whether or not the {@link ISerializer serializer} supports
     * deserialization of a given {@link Class type}.
     *
     * @param type
     *            the {@link Class type}.
     * @return {@code true} if the converter supports the format; {@code false}
     *         otherwise.
     */
    boolean supportsSerializationOf(Class<?> type);

    /**
     * Determines whether or not the {@link ISerializer serializer} supports
     * deserialization of a given {@link Class type}.
     *
     * @param type
     *            the {@link Class type}.
     * @return {@code true} if the converter supports the format; {@code false}
     *         otherwise.
     */
    boolean supportsDeserializationOf(Class<?> type);

    /**
     * Serializes a document into a file
     *
     * @param <T>
     *            the document type.
     *
     * @param document
     *            the document to be serialized.
     * @param filename
     *            the file name.
     * @throws SerializationException
     *             if serialization fails.
     */
    <T> void serialize(final T document, final String filename)
            throws SerializationException;



    /**
     * Deserializes a class of the provided type from a file.
     *
     * @param <T>
     *            the document type.
     * 
     * @param file
     *            the file.
     * @param type
     *            the desired return type.
     * @return the deserialized document.
     * @throws SerializationException
     *             if deserialization fails.
     */
    <T> T deserialize(final File file, Class<? extends T> type)
            throws SerializationException;

    /**
     * Deserializes a class of the provided type from a URI.
     *
     * @param uri
     *            the URI.
     * @param type
     *            the desired return type.
     * @return the deserialized document.
     * @throws SerializationException
     *             if deserialization fails.
     */
    <T> T deserialize(final URI uri, final Class<? extends T> type)
            throws SerializationException;


    /**
     * Deserializes a class of the provided type from a URL.
     *
     * @param <T>
     *            the document type.
     * 
     * @param url
     *            the URL.
     * @param type
     *            the desired return type.
     * @return the deserialized document.
     * @throws SerializationException
     *             if deserialization fails.
     */
    <T> T deserialize(final URL url, Class<? extends T> type)
            throws SerializationException;
}
