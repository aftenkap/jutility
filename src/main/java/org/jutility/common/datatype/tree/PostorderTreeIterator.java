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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * @param <E>
 *         the value type.
 *
 * @author Peter J. Radics
 * @version 1.0
 */
public class PostorderTreeIterator<E>
        implements Iterator<E> {



    private final Tree<E> tree;

    private final LinkedList<TreeNode<E>> stack;
    private       TreeNode<E>             currentNode;



    /**
     * Creates a new instance of the {@code PostorderTreeIterator} class.
     *
     * @param tree
     *         the tree to iterate over.
     */
    protected PostorderTreeIterator(final Tree<E> tree) {

        this.tree = tree;

        this.stack = new LinkedList<>();

        if (!this.tree.isEmpty()) {
            this.stack.addFirst(this.tree.getRoot());
            this.enqueueChildren();
        }

        this.currentNode = null;
    }


    @Override
    public boolean hasNext() {

        return !this.stack.isEmpty();
    }


    @Override
    public E next()
            throws NoSuchElementException {

        if (!this.stack.isEmpty()) {

            this.currentNode = this.stack.removeFirst();

            return this.currentNode.getElement();
        }

        throw new NoSuchElementException(
                "Trying to iterate past the last element of the tree.");
    }


    @Override
    public void remove() {

        if (this.currentNode == null) {
            throw new IllegalStateException(
                    "Cannot remove element without calling next() immediately "
                    + "before.");
        }
        else {


            // Not root
            if (this.currentNode.getParent() != null) {

                // Leaf node
                if (this.currentNode.getChildren()
                        .isEmpty()) {

                    this.currentNode.getParent()
                            .removeChild(this.currentNode);
                }
                // Not leaf node
                else {

                    // System.out.println("\n\nIn here");
                    final TreeNode<E> parent = this.currentNode.getParent();
                    final TreeNode<E> lastChild = this.currentNode.getChildren()
                            .get(this.currentNode.getChildren()
                                         .size() - 1);

                    // System.out.println("Stack: " + this.stack);
                    // System.out.println("Current: "
                    // + this.currentNode.getElement());
                    // System.out.println("Parent: " + parent.getElement());
                    // System.out.println("Last Child: " +
                    // lastChild.getElement());


                    this.currentNode.removeChild(lastChild);

                    parent.replaceChild(this.currentNode, lastChild);

                    final List<TreeNode<E>> children = lastChild.getChildren();
                    lastChild.clearChildren();

                    for (final TreeNode<E> child : this.currentNode
                            .getChildren()) {
                        lastChild.addChild(child);
                    }
                    for (final TreeNode<E> child : children) {
                        lastChild.addChild(child);
                    }

                    // System.out.println("\n\n");

                }
            }
            // Root
            else {

                // Leaf node
                if (this.currentNode.getChildren()
                        .isEmpty()) {

                    this.tree.setRoot(null);
                }
                // Not leaf node
                else {

                    final TreeNode<E> lastChild = this.currentNode.getChildren()
                            .get(this.currentNode.getChildren()
                                         .size() - 1);

                    this.tree.setRoot(lastChild);

                    this.currentNode.removeChild(lastChild);

                    final List<TreeNode<E>> children = lastChild.getChildren();
                    lastChild.clearChildren();

                    for (final TreeNode<E> child : this.currentNode
                            .getChildren()) {
                        lastChild.addChild(child);
                    }
                    for (final TreeNode<E> child : children) {
                        lastChild.addChild(child);
                    }
                }
            }


            // this.lastVisited.setParent(null);
            this.currentNode = null;
        }
    }

    // public void insert(E element) {
    //
    // if (this.currentNode == null) {
    //
    // }
    // else {
    //
    // this.currentNode.addChild(element);
    // }
    // }
    //
    // public TreeNode<E> getTreeNode() {
    //
    // return this.currentNode;
    // }

    /**
     * Returns the tree node.
     *
     * @return the tree node.
     */
    public TreeNode<E> getTreeNode() {

        return this.currentNode;
    }

    private void enqueueChildren() {

        final TreeNode<E> current = this.stack.peekFirst();

        final List<TreeNode<E>> children = current.getChildren();

        // We only have to enqueue children if the current top of the stack has
        // children
        if (!children.isEmpty()) {


            final ListIterator<TreeNode<E>> it = children.listIterator(
                    children.size());

            // We enqueue all children in reverse order
            while (it.hasPrevious()) {
                this.stack.addFirst(it.previous());

                this.enqueueChildren();
            }

        }

    }
}
