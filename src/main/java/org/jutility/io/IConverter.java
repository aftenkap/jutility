package org.jutility.io;



/**
 * The @ link IConverter} interface provides a contract for converters.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public interface IConverter {



    /**
     * Determines whether or not the {@link IConverter converter} supports
     * conversion from the {@link Class source type} to a given {@link Class
     * target type}.
     * 
     * @param sourceType
     *            the {@link Class source type}.
     * @param targetType
     *            the {@link Class target type}.
     * @return <code>true</code> if the converter supports the conversion;
     *         <code>false</code> otherwise.
     */
    public abstract boolean supportsConversion(Class<?> sourceType,
            Class<?> targetType);


    /**
     * Converts the provided object into an instance of the desired return type.
     * 
     * @param documentToConvert
     *            the document to be converted.
     * @param returnType
     *            the desired return type.
     * @return a document of the desired format.
     * @throws ConversionException
     *             if the return type is not supported or conversion fails.
     */
    public abstract <T, S> S convert(T documentToConvert,
            Class<? extends S> returnType)
            throws ConversionException;
}
