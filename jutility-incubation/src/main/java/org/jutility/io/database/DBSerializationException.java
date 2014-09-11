/**
 * 
 */
package org.jutility.io.database;


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

    /**
     * Creates a new instance of the {@link DBSerializationException} class.
     * 
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public DBSerializationException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }
}
