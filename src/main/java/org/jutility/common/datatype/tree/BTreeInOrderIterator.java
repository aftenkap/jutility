package org.jutility.common.datatype.tree;


// @formatter:off
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
// @formatter:on

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;



/**
 * The {@code BTreeInOrderIterator} class provides an in-order iterator for a
 * {@link BTree}.
 *
 * @param <VALUE>
 *            the value type.
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class BTreeInOrderIterator<VALUE extends Comparable<VALUE>>
        implements Iterator<VALUE> {

    private final BTree<VALUE>      tree;

    private VALUE                   currentValue;

    private final LinkedList<VALUE> stack;


    /**
     * Creates a new instance of the {@code BTreeInOrderIterator} class.
     *
     * @param tree
     *            the tree to iterate over.
     */
    public BTreeInOrderIterator(final BTree<VALUE> tree) {

        this.tree = tree;

        this.currentValue = null;

        this.stack = new LinkedList<>();

        this.enqueue(tree.getRoot());
    }



    @Override
    public boolean hasNext() {

        return !this.stack.isEmpty();
    }

    @Override
    public VALUE next()
            throws NoSuchElementException {

        if (!this.stack.isEmpty()) {

            return this.currentValue = this.stack.removeFirst();

        }
        else {
            throw new NoSuchElementException(
                    "Trying to iterate past the last element of the tree.");
        }
    }

    @Override
    public void remove() {

        if (this.currentValue == null) {
            throw new IllegalStateException(
                    "Cannot remove element without calling next() immediately "
                            + "before.");
        }

        this.tree.remove(this.currentValue);
        this.currentValue = null;
    }


    private void enqueue(final BTree<VALUE>.BTreeNode node) {

        if (!node.getChildren().isEmpty()) {

            for (int i = 0; i < node.getChildren().size(); i++) {

                this.enqueue(node.getChild(i));

                if (i < node.getEntries().size()) {

                    this.stack.add(node.getEntry(i));
                }

            }

        }
        else {
            for (final VALUE entry : node.getEntries()) {

                this.stack.add(entry);
            }
        }
    }
}
