package org.jutility.common.datatype.map;


import javax.xml.bind.annotation.XmlType;


/**
 * The generic {@link KeyValuePair} class provides a pairwise mapping between
 * keys and values where the keys are {@link Comparable comparable}.
 * 
 * @param <KEY>
 *            The key type.
 * @param <VALUE>
 *            The value type.
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "Comparable eyValuePair")
public class ComparableKeyValuePair<KEY extends Comparable<KEY>, VALUE>
        extends KeyValuePair<KEY, VALUE>
        implements Comparable<ComparableKeyValuePair<KEY, VALUE>> {

    /**
     * Creates a new instance of the {@link ComparableKeyValuePair} class.
     * (Serialization Constructor)
     */
    @SuppressWarnings("unused")
    private ComparableKeyValuePair() {

        super();
    }

    /**
     * Creates a new instance of the {@link KeyValuePair} class with the
     * provided key.
     * 
     * @param key
     *            the key.
     */
    public ComparableKeyValuePair(KEY key) {

        super(key);
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
    public ComparableKeyValuePair(KEY key, VALUE value) {

        super(key, value);
    }

    @Override
    public int compareTo(ComparableKeyValuePair<KEY, VALUE> other) {

        return this.getKey().compareTo(other.getKey());
    }
}
