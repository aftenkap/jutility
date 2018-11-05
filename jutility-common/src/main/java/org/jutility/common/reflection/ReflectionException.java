package org.jutility.common.reflection;


//@formatter:off
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
//@formatter:on


/**
 * The {@code ReflectionException} class provides details about exceptions
 * during reflection-related operations.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class ReflectionException
        extends Exception {


    private static final long serialVersionUID = -1775265252416064767L;


    /**
     * Creates a new instance of the {@code ReflectionException}.
     */
    public ReflectionException() {

        super();
    }


    /**
     * Creates a new instance of the {@code ReflectionException} class with the
     * provided parameters.
     *
     * @param message
     *            the detail message.
     */
    public ReflectionException(final String message) {

        super(message);
    }


    /**
     * Creates a new instance of the {@code ReflectionException} class with the
     * provided parameters.
     *
     * @param cause
     *            the {@link Throwable cause} of the exception.
     */
    public ReflectionException(final Throwable cause) {

        super(cause);
    }

    /**
     * Creates a new instance of the {@code ReflectionException} class with the
     * provided parameters.
     *
     * @param message
     *            the detail message.
     * @param cause
     *            the {@link Throwable cause} of the exception.
     */
    public ReflectionException(final String message, final Throwable cause) {

        super(message, cause);
    }
}
