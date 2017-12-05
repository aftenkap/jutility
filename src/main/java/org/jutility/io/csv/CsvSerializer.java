package org.jutility.io.csv;


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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
 * The {@code CsvSerializer} class provides an implementation of the
 * {@link ISerializer} interface for CSV files.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class CsvSerializer
        implements ISerializer {


    private static CsvSerializer s_Instance;

    /**
     * Returns the Singleton instance of the {@code CsvSerializer}.
     *
     * @return the Singleton instance.
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


    @Override
    public boolean supportsSerializationOf(Class<?> type) {

        return Table.class.isAssignableFrom(type);
    }


    @Override
    public boolean supportsDeserializationOf(Class<?> type) {

        return type.isAssignableFrom(Table.class);
    }

    @Override
    public <T> void serialize(T document, String filename)
            throws SerializationException {

        Class<?> documentType = document.getClass();

        if (!this.supportsSerializationOf(documentType)) {

            throw new SerializationException(
                    "Serialization of type " + documentType
                    + " is not supported!");
        }

        Table<?> table = Table.class.cast(document);
        ICsvMapWriter mapWriter = null;
        try {

            mapWriter = new CsvMapWriter(new FileWriter(filename),
                    CsvPreference.STANDARD_PREFERENCE);

            String[] header = new String[table.columns()];

            for (int i = 0; i < table.columns(); i++) {

                Object value = table.get(0, i);

                if (value != null) {

                    header[i] = value.toString();
                }
                else {

                    header[i] = "";
                }
            }

            mapWriter.writeHeader(header);

            for (int row = 1; row < table.rows(); row++) {

                Map<String, String> values = new LinkedHashMap<>();

                for (int column = 0; column < table.columns(); column++) {

                    String key = header[column];
                    Object value = table.get(row, column);

                    if (value != null) {

                        values.put(key, value.toString());
                    }
                    else {

                        values.put(key, "");
                    }
                }

                mapWriter.write(values, header);
            }
        }

        catch (IOException e) {

            throw new SerializationException("Could not serialize resource.",
                    e);
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

        Map<String, List<String>> contents = new LinkedHashMap<>();

        ITable<String> table = new Table<>();
        try (ICsvMapReader mapReader = new CsvMapReader(
                new BufferedReader(new InputStreamReader(url.openStream())),
                CsvPreference.STANDARD_PREFERENCE)) {



            // the header columns are used as the keys to the Map
            final String[] headerArray = mapReader.getHeader(true);
            final List<String> header = Arrays.asList(headerArray);

            AtomicInteger headerIndex = new AtomicInteger(0);
            header.forEach(columnHeader -> {

                contents.put(columnHeader, new LinkedList<>());

                table.add(0, headerIndex.get(),
                        columnHeader != null ? columnHeader.trim() : null);
                headerIndex.incrementAndGet();
            });

            Map<String, String> rowValueMap;
            while ((rowValueMap = mapReader.read(headerArray)) != null) {

                rowValueMap.keySet()
                        .forEach(key -> contents.get(key)
                                            .add(rowValueMap.get(key)));
            }


            AtomicInteger column = new AtomicInteger(0);
            contents.keySet()
                    .forEach(key -> {

                        AtomicInteger row = new AtomicInteger(1);

                        contents.get(key)
                                .forEach(value -> {

                                    if (value != null) {

                                        table.add(row.get(), column.get(),
                                                value.trim());
                                    }
                                    row.incrementAndGet();
                                });

                        column.incrementAndGet();
                    });

        }
        catch (IOException e) {

            throw new SerializationException(
                    "Could not deserialize CSV file " + url.toString() + "!",
                    e);
        }

        return type.cast(table);
    }
}
