/**
 * 
 */
package org.jutility.io;


/**
 * The <code>SerializationException</code> class provides information about an
 * exception during serialization or deserialization of a document.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class SerializationException
        extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 2109991593148674026L;


    /**
     * Creates a new instance of a serialization exception.
     */
    public SerializationException() {

        super();
    }

    /**
     * Creates a new instance of a serialization exception with the provided
     * message.
     * 
     * @param message
     *            the exception message.
     */
    public SerializationException(String message) {

        super(message);
    }

    /**
     * Creates a new instance of a serialization exception with the provided
     * cause.
     * 
     * @param cause
     *            the cause of the exception.
     */
    public SerializationException(Throwable cause) {

        super(cause);
    }

    /**
     * Creates a new instance of a serialization exception with the provided
     * message and cause.
     * 
     * @param message
     *            the exception message.
     * @param cause
     *            the cause of the exception.
     */
    public SerializationException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Creates a new instance of a serialization exception with the provided
     * message and cause. Provides ability to enable suppression and enable
     * writing on the stack trace.
     * 
     * @param message
     *            the exception message.
     * @param cause
     *            the cause of the exception.
     * @param enableSuppression
     *            whether or not suppression is enabled.
     * @param writableStackTrace
     *            whether or not the stack trace is writable.
     */
    public SerializationException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }

}
