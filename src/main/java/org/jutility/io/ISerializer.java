package org.jutility.io;


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
