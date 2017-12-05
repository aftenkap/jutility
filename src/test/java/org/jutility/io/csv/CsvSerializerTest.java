package org.jutility.io.csv;

/*
 * #%L
 * jutility-io
 * %%
 * Copyright (C) 2013 - 2015 jutility.org
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


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jutility.common.datatype.table.Table;
import org.jutility.io.SerializationException;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * The {@code CsvSerializerTest} class provides unit tests for the
 * {@link CsvSerializer class}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.2
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class CsvSerializerTest {

    private Table<String> referenceTable;
    private File          testFile;

    private final String filename = "test.csv";
    private File tempFile;


    /**
     * Set up.
     *
     * @throws IOException
     *         if a temporary file for serialization could not be created.
     */
    @Before
    public void setUp()
            throws IOException {

        this.referenceTable = new Table<>();

        this.referenceTable.add(0, 0, "foo");
        this.referenceTable.add(0, 1, "bar");
        this.referenceTable.add(0, 2, "baz");
        this.referenceTable.add(1, 0, "row1-column0");
        this.referenceTable.add(1, 1, "row1-column1");
        this.referenceTable.add(1, 2, "row1-column2");
        this.referenceTable.add(2, 0, "row2-column0");
        this.referenceTable.add(2, 1, "row2-column1");
        this.referenceTable.add(2, 2, "row2-column2");

        ClassLoader classLoader = this.getClass()
                                      .getClassLoader();

        String name = classLoader.getResource(this.filename)
                                 .getFile();
        this.testFile = new File(name);

        this.tempFile = File.createTempFile("test", "csv");
    }

    /**
     * Tear Down.
     *
     * @throws IOException
     *         if the temporary file created during setup could not be removed.
     */
    @After
    public void tearDown()
            throws IOException {

        if (this.tempFile != null && this.tempFile.exists()) {

            this.tempFile.delete();
        }
    }

    /**
     * Test for
     * {@link org.jutility.io.csv.CsvSerializer#deserialize(File, Class)}.
     */
    @Test
    public void testDeserialize() {

        Table<?> table = null;
        try {

            table = CsvSerializer.Instance()
                                 .deserialize(this.testFile, Table.class);

        }
        catch (SerializationException e) {

            fail("Deserialization failed with exception " + e.toString());
        }

        assertEquals(referenceTable.rows(), table.rows());
        assertEquals(referenceTable.columns(), table.columns());

        for (int row = 0; row < referenceTable.rows(); row++) {

            for (int column = 0; column < referenceTable.columns(); column++) {

                assertEquals(this.referenceTable.get(row, column),
                        table.get(row, column));
            }
        }

        System.out.println("Deserialization passed.");
    }


    /**
     * Test for
     * {@link org.jutility.io.csv.CsvSerializer#serialize(Object, String)} .
     */
    @Test
    public void testSerialize() {

        try {

            CsvSerializer.Instance()
                         .serialize(this.referenceTable,
                                 this.tempFile.getPath());
        }
        catch (SerializationException e) {

            fail("Serialization failed with exception " + e.toString());
        }


        Table<?> table = null;
        try {

            table = CsvSerializer.Instance()
                                 .deserialize(this.tempFile, Table.class);

        }
        catch (SerializationException e) {

            fail("Deserialization failed with exception " + e.toString());
        }

        assertEquals(referenceTable.rows(), table.rows());
        assertEquals(referenceTable.columns(), table.columns());

        for (int row = 0; row < referenceTable.rows(); row++) {

            for (int column = 0; column < referenceTable.columns(); column++) {

                assertEquals(this.referenceTable.get(row, column),
                        table.get(row, column));
            }
        }

        System.out.println("Serialization passed.");
    }
}
