package org.jutility.common.datatype.tree;


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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * The {@link Tree} class provides an implementation of a tree with an arbitrary
 * number of children per node. It adheres to the {@link Collection} interface.
 * <p>
 * Thread safety: Fully thread safe through locking around critical sections.
 * </p>
 *
 * @param <E>
 *         the type of the tree.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class Tree<E>
        extends AbstractCollection<E> {

    private static final Logger LOG = LoggerFactory.getLogger(Tree.class);

    private       TreeNode<E> root;
    private final Lock        rootLock;



    /**
     * Returns the root {@link TreeNode node}.
     *
     * @return the root {@link TreeNode node}.
     */
    protected TreeNode<E> getRoot() {

        TreeNode<E> root;
        this.rootLock.lock();
        root = this.root;
        this.rootLock.unlock();
        return root;
    }

    /**
     * Sets the root {@link TreeNode node}.
     *
     * @param value
     *         the root {@link TreeNode node}.
     */
    protected void setRoot(final TreeNode<E> value) {

        this.rootLock.lock();
        this.root = value;
        this.rootLock.unlock();
    }


    /**
     * Creates a new instance of the {@code Tree} class.
     */
    public Tree() {

        super();

        this.root = null;
        this.rootLock = new ReentrantLock();
    }

    /**
     * Creates a new instance of the {@code Tree} class and adds all elements of
     * the provided {@link Collection}.
     *
     * @param collection
     *         the elements to add.
     */
    public Tree(final Collection<? extends E> collection) {

        this();

        this.addAll(collection);
    }



    @Override
    public void clear() {

        this.rootLock.lock();
        this.root = null;
        this.rootLock.unlock();
    }


    @Override
    public boolean isEmpty() {

        boolean empty;
        this.rootLock.lock();
        empty = this.root == null;
        this.rootLock.unlock();
        return empty;
    }


    @Override
    public int size() {

        int i = 0;
        for (final E e : this) {

            if (e != null) {

                Tree.LOG.debug(e.toString());
            }
            i++;
        }
        return i;
    }

    @Override
    public Iterator<E> iterator() {

        return this.preorderIterator();
    }


    @Override
    public boolean add(final E element) {

        if (this.contains(element)) {

            return false;
        }
        else {

            if (this.isEmpty()) {

                this.setRoot(new TreeNode<>(element));
            }
            else {

                this.root.addChild(element);
            }

            return true;
        }
    }



    /**
     * Adds the provided child element as a child of the provided parent
     * element.
     *
     * @param parent
     *         the parent element.
     * @param child
     *         the child element.
     *
     * @return {@code true}, if the child was successfully inserted (i.e., the
     * parent element was found); {@code false} otherwise.
     */
    public boolean addChild(final E parent, final E child) {

        final PreorderTreeIterator<E> it = new PreorderTreeIterator<>(this);

        boolean inserted = false;

        while (it.hasNext()) {

            if (it.next()
                  .equals(parent)) {

                it.getTreeNode()
                  .addChild(child);
                inserted = true;
                break;
            }
        }

        return inserted;
    }

    /**
     * Returns a {@link PreorderTreeIterator pre-order iterator} over the
     * {@link Tree}
     *
     * @return a {@link PreorderTreeIterator pre-order iterator} over the {@link
     * Tree}
     */
    public PreorderTreeIterator<E> preorderIterator() {

        return new PreorderTreeIterator<>(this);
    }

    /**
     * Returns a {@link PostorderTreeIterator post-order iterator} over the
     * {@link Tree}
     *
     * @return a {@link PostorderTreeIterator post-order iterator} over the
     * {@link Tree}
     */
    public PostorderTreeIterator<E> postorderIterator() {

        return new PostorderTreeIterator<>(this);
    }


    /**
     * Determines whether the {@link Tree trees} are permutations of each other
     * (i.e., have the same content but potentially different order of
     * children).
     *
     * @param lhs
     *         the left-hand side tree.
     * @param rhs
     *         the right-hand side tree.
     *
     * @return {@code true}, if the trees are permutations of each other; {@code
     * false} otherwise.
     */
    public static boolean isPermutationOf(final Tree<?> lhs,
            final Tree<?> rhs) {


        boolean result = false;
        final int lhsSize = lhs.size();
        final int rhsSize = rhs.size();
        final boolean sameSize = lhsSize == rhsSize;


        if (sameSize) {

            final PreorderTreeIterator<?> lhsIt = lhs.preorderIterator();

            boolean sameNode = false;
            boolean foundINode = false;

            while (lhsIt.hasNext()) {

                final Object lhsElement = lhsIt.next();
                final TreeNode<?> lhsTreeNode = lhsIt.getTreeNode();

                final PreorderTreeIterator<?> rhsIt = rhs.preorderIterator();
                while (rhsIt.hasNext()) {

                    final Object rhsElement = rhsIt.next();
                    final TreeNode<?> rhsTreeNode = rhsIt.getTreeNode();

                    if (lhsElement.equals(rhsElement)) {

                        foundINode = true;
                        sameNode = Tree.sameNodeContent(lhsTreeNode,
                                rhsTreeNode);
                        break;
                    }

                }
                if (!foundINode) {

                    break;
                }
                else {

                    if (!sameNode) {

                        break;
                    }
                }
            }

            result = foundINode && sameNode;
        }

        return result;
    }



    /**
     * Determines whether or not the two {@link TreeNode nodes} have the same
     * content (potentially in different order).
     *
     * @param lhs
     *         the left-hand side {@link TreeNode node}.
     * @param rhs
     *         the right-hand side {@link TreeNode node}.
     *
     * @return {@code true}, if the {@link TreeNode nodes} have the same
     * content; {@code false} otherwise.
     */
    private static boolean sameNodeContent(final TreeNode<?> lhs,
            final TreeNode<?> rhs) {

        if (lhs.getChildren().size() != rhs.getChildren().size()) {

            return false;
        }

        return lhs.getElement().equals(rhs.getElement())
                && lhs.getChildren().containsAll(rhs.getChildren());
    }
}
