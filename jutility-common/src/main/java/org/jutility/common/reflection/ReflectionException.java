package org.jutility.common.reflection;

/*
 * #%L
 * jutility-common
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
 * The {@link ReflectionException} class provides details about exceptions
 * during reflection-related operations.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class ReflectionException
        extends Exception {


    private static final long serialVersionUID = -1775265252416064767L;


    /**
     * Creates a new instance of the {@link ReflectionException}.
     */
    public ReflectionException() {

        super();
    }


    /**
     * Creates a new instance of the {@link ReflectionException} class with the
     * provided parameters.
     * 
     * @param message
     *            the detail message.
     */
    public ReflectionException(String message) {

        super(message);
    }


    /**
     * Creates a new instance of the {@link ReflectionException} class with the
     * provided parameters.
     * 
     * @param cause
     *            the {@link Throwable cause} of the exception.
     */
    public ReflectionException(Throwable cause) {

        super(cause);
    }

    /**
     * Creates a new instance of the {@link ReflectionException} class with the
     * provided parameters.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the {@link Throwable cause} of the exception.
     */
    public ReflectionException(String message, Throwable cause) {

        super(message, cause);
    }


    /**
     * Creates a new instance of the {@link ReflectionException} class with the
     * provided parameters.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the {@link Throwable cause} of the exception.
     * @param enableSuppression
     *            whether or not suppression is enabled or disabled.
     * @param writableStackTrace
     *            whether or not the stack trace should be writable.
     */
    public ReflectionException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }
}
