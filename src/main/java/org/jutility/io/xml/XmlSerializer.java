package org.jutility.io.xml;

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
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.jutility.io.ISerializer;
import org.jutility.io.SerializationException;



/**
 * The {@link XmlSerializer} singleton class implements the {@link ISerializer}
 * interface, providing capabilities to serialize/deserialize any property JAXB
 * annotated class.
 *
 * @author Peter J. Radics
 * @version 0.1
 */
public class XmlSerializer
        implements ISerializer {


    private final List<Class<?>> contextBounds;



    private static XmlSerializer s_Instance;

    /**
     * Returns the instance of the {@link XmlSerializer} singleton class.
     *
     * @return the singleton instance.
     */
    public static XmlSerializer Instance() {

        if (XmlSerializer.s_Instance == null) {

            XmlSerializer.s_Instance = new XmlSerializer();
        }

        return XmlSerializer.s_Instance;
    }

    /**
     * Registers a {@link Class type} for inclusion in the XmlSerialization
     * context.
     *
     * @param type
     *         the type.
     */
    public void registerClass(Class<?> type) {

        if (!this.contextBounds.contains(type)) {
            this.contextBounds.add(type);
        }
    }

    /**
     * Removes a {@link Class type} from the XmlSerialization context.
     *
     * @param type
     *         the type.
     */
    public void unregisterClass(Class<?> type) {

        if (!this.contextBounds.contains(type)) {
            this.contextBounds.remove(type);
        }
    }


    @Override
    public boolean supportsSerializationOf(Class<?> type) {

        return this.supportsType(type);
    }

    @Override
    public boolean supportsDeserializationOf(Class<?> type) {

        return this.supportsType(type);
    }


    private boolean supportsType(Class<?> type) {

        return type.isAnnotationPresent(XmlRootElement.class);
    }

    /**
     * Creates a new instance of the {@link XmlSerializer} class.
     */
    private XmlSerializer() {

        this.contextBounds = new LinkedList<>();
    }


    private Class<?>[] createContextBounds(Class<?> type) {

        this.contextBounds.add(0, type);


        return this.contextBounds.toArray(new Class<?>[0]);
    }

    /**
     * Serializes an XmlSerializable document into a file.
     *
     * @param document
     *         the document to be serialized.
     * @param filename
     *         the file name.
     *
     * @throws SerializationException
     *         if serialization fails.
     */
    @Override
    public <T> void serialize(T document, String filename)
            throws SerializationException {

        Class<?> documentType = document.getClass();

        if (!this.supportsSerializationOf(documentType)) {

            throw new SerializationException(
                    "Serialization of type " + documentType
                    + " is not supported!");
        }

        try {

            JAXBContext context = JAXBContext.newInstance(
                    this.createContextBounds(documentType));

            Marshaller m = context.createMarshaller();


            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // m.setProperty(
            // "com.sun.xml.internal.bind.xmlHeaders",
            // "com.sun.xml.bind.xmlHeaders",
            // "\n<!DOCTYPE X3D PUBLIC \"ISO//Web3D//DTD X3D 3.2//EN\"
            // \"http://www.web3d.org/specifications/x3d-3.2.dtd\">");
            // m.setProperty("com.sun.xml.bind.namespacePrefixMapper",new
            // NamespacePrefixMapperImpl());
            // m.setProperty("jaxb.noNamespaceSchemaLocation",
            // "http://www.web3d.org/specifications/x3d-3.2.xsd");
            // m.setProperty("com.sun.xml.bind.marshaller
            // .CharacterEscapeHandler",
            // new CDataCharacterEscapeHandler());
            XMLOutputFactory xof = XMLOutputFactory.newInstance();
            XMLStreamWriter streamWriter = xof.createXMLStreamWriter(
                    new FileOutputStream(filename));
            XmlCDataStreamWriter cdataStreamWriter = new XmlCDataStreamWriter(
                    streamWriter);
            m.marshal(document, cdataStreamWriter);

        }
        catch (java.io.FileNotFoundException e) {

            throw new SerializationException("Could not write to resource.", e);
        }
        catch (JAXBException | XMLStreamException e) {

            throw new SerializationException("Could not serialize resource.",
                    e);
        }
    }


    // @Override
    // public <T> T deserialize(String filename, Class<? extends T> type)
    // throws SerializationException {
    //
    // return this.deserialize(new File(filename), type);
    // }



    @Override
    public <T> T deserialize(File file, Class<? extends T> type)
            throws SerializationException {

        return this.deserialize(file.toURI(), type);
    }


    @Override
    public <T> T deserialize(URI uri, Class<? extends T> type)
            throws SerializationException {

        try {

            return this.deserialize(uri.toURL(), type);
        }
        catch (MalformedURLException e) {

            throw new SerializationException("URI " + uri + " is malformed.",
                    e);
        }
    }



    @Override
    public <T> T deserialize(URL url, Class<? extends T> type)
            throws SerializationException {

        if (!this.supportsDeserializationOf(type)) {

            throw new SerializationException(
                    "Deserialization of type " + type.toString()
                    + " is not supported!");
        }

        T doc;
        try {
            JAXBContext jc = JAXBContext.newInstance(
                    this.createContextBounds(type));
            Unmarshaller u = jc.createUnmarshaller();

            Object deserializedObject = u.unmarshal(url.openStream());

            if (deserializedObject != null && type.isAssignableFrom(
                    deserializedObject.getClass())) {

                doc = type.cast(deserializedObject);
            }
            else {

                throw new SerializationException(
                        "Could not deserialize resource -- resource appears "
                        + "to be empty!");
            }
        }
        catch (JAXBException e) {

            throw new SerializationException("Could not deserialize resource!",
                    e);
        }
        catch (IOException e) {

            throw new SerializationException("Could not connect to resource!",
                    e);
        }

        return doc;
    }
}
