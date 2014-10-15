package org.jutility.io.database;

/*
 * #%L
 * jutility-incubation
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
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class DBSerializationException
        extends Exception {


    private static final long serialVersionUID = 3922986020862947459L;

    /**
     * Creates a new instance of the {@link DBSerializationException} class.
     */
    public DBSerializationException() {

        super();
    }

    /**
     * Creates a new instance of the {@link DBSerializationException} class.
     * 
     * @param message
     */
    public DBSerializationException(String message) {

        super(message);
    }

    /**
     * Creates a new instance of the {@link DBSerializationException} class.
     * 
     * @param cause
     */
    public DBSerializationException(Throwable cause) {

        super(cause);
    }

    /**
     * Creates a new instance of the {@link DBSerializationException} class.
     * 
     * @param message
     * @param cause
     */
    public DBSerializationException(String message, Throwable cause) {

        super(message, cause);
    }
}
