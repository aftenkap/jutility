package org.jutility.common.datatype.map;


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

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

import org.jutility.common.reflection.ReflectionException;
import org.jutility.common.reflection.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The {@code ListMapWrapper} class provides a {@link java.util.List List}
 * wrapper around a {@link Map}.
 *
 * @author Peter J. Radics
 * @param <K>
 *            the key type.
 * @param <E>
 *            the element type.
 *
 */
public class ListMapWrapper<K, E>
        extends AbstractSequentialList<E>
        implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -604106384904749465L;

    private static final Logger LOG = LoggerFactory
                                                       .getLogger(ListMapWrapper.class);

    private final Class<K>    keyType;
    private final Map<K, E>   map;
    private final String      keyProperty;



    /**
     * Returns the wrapped {@link Map}.
     *
     * @return the wrapped {@link Map}.
     */
    Map<K, E> getMap() {

        return this.map;
    }

    /**
     * Creates a new instance of the {@code ListMapWrapper} class.
     *
     * @param map
     *            the {@link Map} to wrap.
     * @param keyProperty
     *            the key property.
     * @param keyType
     *            the key type.
     */
    public ListMapWrapper(final Map<K, E> map, final String keyProperty,
            final Class<K> keyType) {


        this.map = map;
        this.keyProperty = keyProperty;
        this.keyType = keyType;
    }


    @Override
    public ListIterator<E> listIterator(final int index) {

        return new MappedListIterator<>(this, index);
    }



    @Override
    public int size() {

        return this.map.size();
    }


    private static class MappedListIterator<K, E>
            implements ListIterator<E> {

        private final ListMapWrapper<K, E> listMapWrapper;
        private final Map<K, E>            map;
        private int                        currentIndex;
        private K                          currentKey;
        private Iterator<K>                mapIterator;

        /**
         * Creates a new instance of the {@link MappedListIterator} class.
         *
         * @param listMapWrapper
         *            the listMapWrapper.
         */
        @SuppressWarnings("unused")
        public MappedListIterator(final ListMapWrapper<K, E> listMapWrapper) {

            this(listMapWrapper, 0);
        }

        public MappedListIterator(final ListMapWrapper<K, E> listMapWrapper,
                final int index) {

            this.listMapWrapper = listMapWrapper;
            this.map = this.listMapWrapper.getMap();

            this.mapIterator = this.map.keySet().iterator();

            this.advanceToIndex(index - 1, true);

        }


        @Override
        public boolean hasNext() {

            return this.mapIterator.hasNext();
        }

        @Override
        public E next() {

            this.currentIndex++;
            this.currentKey = this.mapIterator.next();
            return this.map.get(this.currentKey);
        }

        @Override
        public boolean hasPrevious() {

            return this.currentIndex > 0;
        }

        @Override
        public E previous() {

            if (!this.hasPrevious()) {

                throw new IndexOutOfBoundsException();
            }

            this.advanceToIndex(this.currentIndex - 1);
            return this.map.get(this.currentKey);
        }

        @Override
        public int nextIndex() {

            return this.currentIndex + 1;
        }

        @Override
        public int previousIndex() {

            return this.currentIndex - 1;
        }

        @Override
        public void remove() {

            this.mapIterator.remove();
        }

        @Override
        public void set(final E e) {

            if (this.currentKey == null) {

                throw new IllegalStateException();
            }
            this.map.put(this.currentKey, e);
        }

        @Override
        public void add(final E e) {

            final Map<K, E> backupMap = new LinkedHashMap<>();

            final int current = this.currentIndex;
            while (this.mapIterator.hasNext()) {

                final K key = this.mapIterator.next();
                backupMap.put(key, this.map.get(key));
                this.mapIterator.remove();
            }

            this.map.put(this.listMapWrapper.getKey(e), e);

            for (final K key : backupMap.keySet()) {

                this.map.put(key, backupMap.get(key));
            }

            this.advanceToIndex(current, true);

        }

        private void advanceToIndex(final int index) {

            this.advanceToIndex(index, false);
        }

        private void advanceToIndex(final int index,
                final boolean initialization) {

            if ((index > this.map.size()) || (index < 0)) {

                if (index != -1 || !initialization) {

                    throw new IndexOutOfBoundsException("Index: " + index
                            + ", size: " + this.map.size());
                }
            }

            this.currentKey = null;
            this.mapIterator = this.map.keySet().iterator();
            for (int i = 0; i <= index; i++) {

                this.currentKey = this.mapIterator.next();
            }
            this.currentIndex = index;
        }



    }

    /**
     * Returns the key for the provided value.
     *
     * @param value
     *            the value.
     * @return the key for the provided value.
     */
    K getKey(final E value) {

        try {
            ListMapWrapper.LOG.debug("GetKey: " + this.keyProperty + " "
                    + this.keyType);

            return ReflectionUtils.getValue(value, this.keyProperty,
                    this.keyType);
        }
        catch (final ReflectionException e) {

            throw new IllegalArgumentException(e);
        }
    }


}
