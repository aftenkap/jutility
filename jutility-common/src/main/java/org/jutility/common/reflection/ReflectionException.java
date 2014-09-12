package org.jutility.common.reflection;


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
