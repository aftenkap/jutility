package org.jutility.math.geometry;


/**
 * The {@link ITranslation} interface provides a contract for classes that
 * implement the translation of an object.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of this translation.
 * 
 */
public interface ITranslation<T extends Number> {

    /**
     * Returns the type of this translation.
     * 
     * @return the type.
     */
    public abstract Class<? extends T> getType();

    /**
     * The translation in the x-dimension.
     * 
     * @return the translation in the x-dimension.
     */
    public T getXTranslation();


    /**
     * The translation in the y-dimension.
     * 
     * @return the translation in the y-dimension.
     */
    public T getYTranslation();


    /**
     * The translation in the x-dimension.
     * 
     * @return the translation in the y-dimension.
     */
    public T getZTranslation();
}
