package org.jutility.io;


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

    /**
     * Creates a new instance of a conversion exception with the provided
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
    public ConversionException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }

}
