package org.jutility.common.datatype.map;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The generic {@link KeyValuePair} class provides a pairwise mapping between
 * keys and values.
 * 
 * @param <KEY>
 *            The key type.
 * @param <VALUE>
 *            The value type.
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlRootElement(name = "KeyValuePair")
@XmlType(name = "KeyValuePair")
@XmlAccessorType(XmlAccessType.NONE)
public class KeyValuePair<KEY, VALUE> {

    @XmlElement
    private final KEY key;

    @XmlElement
    private VALUE     value;


    /**
     * Returns the key.
     * 
     * @return the key.
     */
    public KEY getKey() {

        return key;
    }

    /**
     * Returns the value.
     * 
     * @return the value.
     */
    public VALUE getValue() {

        return value;
    }

    /**
     * Sets the value to the value provided.
     * 
     * @param value
     *            the new value.
     */
    public void setValue(VALUE value) {

        this.value = value;
    }

    /**
     * Creates a new instance of the {@link KeyValuePair} class. (Serialization
     * Constructor)
     */
    protected KeyValuePair() {

        this(null, null, true);
    }

    /**
     * Creates a new instance of the {@link KeyValuePair} class with the
     * provided key.
     * 
     * @param key
     *            the key.
     */
    public KeyValuePair(KEY key) {

        this(key, null);
    }

    /**
     * Creates a new instance of the {@link KeyValuePair} class with the
     * provided key and value.
     * 
     * @param key
     *            the key.
     * @param value
     *            the value.
     */
    public KeyValuePair(KEY key, VALUE value) {

        this(key, value, false);
    }

    /**
     * Creates a new instance of the {@link KeyValuePair} class with the
     * provided key and value.
     * 
     * @param key
     *            the key.
     * @param value
     *            the value.
     * @param serialization
     *            whether or not the constructor is used during serialization.
     */
    private KeyValuePair(KEY key, VALUE value, boolean serialization) {

        if (key == null && !serialization) {
            throw new IllegalArgumentException("Cannot create key-value pair "
                    + "without key!");
        }

        this.key = key;
        this.value = value;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof KeyValuePair<?, ?>) {

            final KeyValuePair<?, ?> other = (KeyValuePair<?, ?>) obj;

            boolean sameKey = this.getKey().equals(other.getKey());

            boolean sameValue = (this.getValue() == other.getValue())
                    || (this.getValue() != null && this.getValue().equals(
                            other.getValue()));

            return sameKey && sameValue;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 83 * (this.getKey() != null ? this.getKey().hashCode() : 0);
        hash = 87 * (this.getValue() != null ? this.getValue().hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {

        String string = this.getKey().toString() + ": ";
        if (this.getValue() != null) {
            string += this.getValue().toString();
        }
        else {
            string += "<null>";
        }
        return string;
    }
}
