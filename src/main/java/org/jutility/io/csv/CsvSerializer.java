package org.jutility.io.csv;

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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jutility.common.datatype.table.ITable;
import org.jutility.common.datatype.table.Table;
import org.jutility.io.ISerializer;
import org.jutility.io.SerializationException;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;



/**
 * 
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class CsvSerializer
        implements ISerializer {


    private static CsvSerializer s_Instance;

    /**
     * Returns the Singleton instance of the OWLSerializer.
     * 
     * @return the Singleton instnace.
     */
    public static CsvSerializer Instance() {

        if (s_Instance == null) {

            s_Instance = new CsvSerializer();
        }

        return s_Instance;
    }


    private CsvSerializer() {

        // Nothing to be done.
    }



    //
    // /**
    // * Sets up the processors used for the examples. There are 10 CSV columns,
    // * so 10 processors are defined. Empty columns are read as null (hence the
    // * NotNull() for mandatory columns).
    // *
    // * @return the cell processors
    // */
    // private static CellProcessor[] getProcessors() {
    //
    // final String emailRegex = "[a-z0-9\\._]+@[a-z0-9\\.]+"; // just an
    // // example, not
    // // very robust!
    // StrRegEx.registerMessage(emailRegex, "must be a valid email address");
    //
    // final CellProcessor[] processors = new CellProcessor[] {
    // new UniqueHashCode(), // customerNo (must be unique)
    // new NotNull(), // firstName
    // new NotNull(), // lastName
    // new ParseDate("dd/MM/yyyy"), // birthDate
    // new NotNull(), // mailingAddress
    // new Optional(new ParseBool()), // married
    // new Optional(new ParseInt()), // numberOfKids
    // new NotNull(), // favouriteQuote
    // new StrRegEx(emailRegex), // email
    // new LMinMax(0L, LMinMax.MAX_LONG) // loyaltyPoints
    // };
    //
    // return processors;
    // }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.conversion.ISerializer#supportsSerializationOf(java.lang
     * .Class)
     */
    @Override
    public boolean supportsSerializationOf(Class<?> type) {

        if (Table.class.isAssignableFrom(type)) {

            return true;
        }
        return false;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.conversion.ISerializer#supportsDeserializationOf(java
     * .lang.Class)
     */
    @Override
    public boolean supportsDeserializationOf(Class<?> type) {

        if (type.isAssignableFrom(Table.class)) {

            return true;
        }
        return false;
    }

    @Override
    public <T> void serialize(T document, String filename)
            throws SerializationException {

        Class<?> documentType = document.getClass();

        if (!this.supportsSerializationOf(documentType)) {

            throw new SerializationException("Serialization of type "
                    + documentType + " is not supported!");
        }

        // Table<?> table = Table.class.cast(document);
        ICsvMapWriter mapWriter = null;
        try {
            mapWriter = new CsvMapWriter(new FileWriter(filename),
                    CsvPreference.STANDARD_PREFERENCE);
            // for (;)
            //
            // mapWriter.writeHeader(header);
            //
            // mapWriter.w
        }

        catch (IOException e) {

            throw new SerializationException("Could not serialize resource.", e);
        }
        finally {

            if (mapWriter != null) {
                try {
                    mapWriter.close();
                }
                catch (IOException e) {

                    e.printStackTrace();
                }
            }

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

            throw new SerializationException("URI " + uri + " is malformed.", e);
        }
    }

    @Override
    public <T> T deserialize(URL url, Class<? extends T> type)
            throws SerializationException {

        if (!this.supportsDeserializationOf(type)) {

            throw new SerializationException("Deserialization of type "
                    + type.toString() + " is not supported!");
        }

        Map<String, List<String>> contents = new LinkedHashMap<String, List<String>>();

        ITable<String> table = new Table<String>();
        ICsvMapReader mapReader = null;
        try {
            mapReader = new CsvMapReader(new BufferedReader(
                    new InputStreamReader(url.openStream())),
                    CsvPreference.STANDARD_PREFERENCE);
            // the header columns are used as the keys to the Map
            final String[] header = mapReader.getHeader(true);

            int headerIndex = 0;
            for (String columnHeader : header) {


                contents.put(columnHeader, new LinkedList<String>());
                if (columnHeader != null) {

                    table.add(0, headerIndex, columnHeader.trim());
                }
                else {

                    table.add(0, headerIndex, null);
                }
                headerIndex++;
            }

            Map<String, String> rowValueMap;
            while ((rowValueMap = mapReader.read(header)) != null) {

                for (String key : rowValueMap.keySet()) {

                    contents.get(key).add(rowValueMap.get(key));
                }
            }


            int column = 0;
            for (String key : contents.keySet()) {

                int row = 1;

                List<String> values = contents.get(key);

                for (String value : values) {

                    if (value != null) {

                        table.add(row, column, value.trim());
                    }
                    row++;
                }
                column++;
            }

        }
        catch (IOException e) {

            throw new SerializationException("Could not deserialize CSV file "
                    + url.toString() + "!", e);
        }
        finally {

            try {

                mapReader.close();
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }

        return type.cast(table);
    }
}
