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
 * The <code>ConversionException</code> class provides information about an
 * exception during the conversion of formats.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class ConversionException
        extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 8916031583865810571L;

    /**
     * Creates a new instance of a conversion exception.
     */
    public ConversionException() {

        super();
    }

    /**
     * Creates a new instance of a conversion exception with the provided
     * message.
     * 
     * @param message
     *            the exception message.
     */
    public ConversionException(String message) {

        super(message);
    }

    /**
     * Creates a new instance of a conversion exception with the provided cause.
     * 
     * @param cause
     *            the cause of the exception.
     */
    public ConversionException(Throwable cause) {

        super(cause);
    }

    /**
     * Creates a new instance of a conversion exception with the provided
     * message and cause.
     * 
     * @param message
     *            the exception message.
     * @param cause
     *            the cause of the exception.
     */
    public ConversionException(String message, Throwable cause) {

        super(message, cause);
    }
}
