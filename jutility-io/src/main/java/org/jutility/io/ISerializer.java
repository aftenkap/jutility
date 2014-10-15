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


import java.io.File;
import java.net.URI;
import java.net.URL;


/**
 * The {@link ISerializer} interface provides a common contract for classes
 * implementing serialization for a specific type.
 *
 * @author Peter J. Radics
 * @version 0.1
 *
 */
public interface ISerializer {



    /**
     * Determines whether or not the {@link ISerializer serializer} supports
     * deserialization of a given {@link Class type}.
     *
     * @param type
     *            the {@link Class type}.
     * @return <code>true</code> if the converter supports the format;
     *         <code>false</code> otherwise.
     */
    public abstract boolean supportsSerializationOf(Class<?> type);

    /**
     * Determines whether or not the {@link ISerializer serializer} supports
     * deserialization of a given {@link Class type}.
     *
     * @param type
     *            the {@link Class type}.
     * @return <code>true</code> if the converter supports the format;
     *         <code>false</code> otherwise.
     */
    public abstract boolean supportsDeserializationOf(Class<?> type);

    /**
     * Serializes a document into a file
     *
     * @param document
     *            the document to be serialized.
     * @param filename
     *            the file name.
     * @throws SerializationException
     *             if serialization fails.
     */
    public abstract <T> void serialize(T document, String filename)
            throws SerializationException;


//    /**
//     * Deserializes a class of the provided type from a provided location.
//     *
//     * @param filenameOrURI
//     *            the file name or URI.
//     * @param type
//     *            the desired return type.
//     * @return the deserialized document.
//     * @throws SerializationException
//     *             if deserialization fails.
//     */
//    public abstract <T> T deserialize(String filenameOrURI,
//            Class<? extends T> type)
//            throws SerializationException;


    /**
     * Deserializes a class of the provided type from a file.
     *
     * @param file
     *            the file.
     * @param type
     *            the desired return type.
     * @return the deserialized document.
     * @throws SerializationException
     *             if deserialization fails.
     */
    public abstract <T> T deserialize(File file, Class<? extends T> type)
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
    public abstract <T> T deserialize(URI uri, Class<? extends T> type)
            throws SerializationException;


    /**
     * Deserializes a class of the provided type from a URL.
     *
     * @param url
     *            the URL.
     * @param type
     *            the desired return type.
     * @return the deserialized document.
     * @throws SerializationException
     *             if deserialization fails.
     */
    public abstract <T> T deserialize(URL url, Class<? extends T> type)
            throws SerializationException;


}
