package org.jutility.common.datatype.tree;


/*
 * #%L jutility-common %% Copyright (C) 2013 - 2014 jutility.org %% Licensed
 * under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. #L%
 */


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
public class PreorderTreeIterator<E>
        implements Iterator<E> {


    private final Tree<E> tree;

    private final LinkedList<TreeNode<E>> stack;
    private       TreeNode<E>             currentNode;



    /**
     * Creates a new instance of the {@code PreorderTreeIterator} class.
     *
     * @param tree
     *         the tree to iterate over.
     */
    protected PreorderTreeIterator(final Tree<E> tree) {


        this.tree = tree;

        this.stack = new LinkedList<>();

        if (!this.tree.isEmpty()) {
            this.stack.addFirst(this.tree.getRoot());
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

            this.enqueueChildren();

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
                    "Cannot remove element without calling next() immediately"
                    + " before.");
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

                    // System.out.println("\nStack before remove: " + stack);
                    final TreeNode<E> parent = this.currentNode.getParent();

                    final TreeNode<E> firstChild = this.currentNode
                            .getChildren()
                            .get(0);

                    this.currentNode.removeChild(firstChild);


                    this.stack.removeFirst();
                    parent.replaceChild(this.currentNode, firstChild);
                    // System.out.println("Replacing " +
                    // currentNode.getElement()
                    // + " with " + firstChild.getElement());

                    // System.out.println("Stack before removing children: " +
                    // stack);
                    for (final TreeNode<E> child : this.currentNode
                            .getChildren()) {
                        firstChild.addChild(child);
                        this.stack.removeFirst();
                    }
                    // System.out.println("Stack after remove: " + stack);


                    this.currentNode.clearChildren();

                    this.stack.addFirst(firstChild);
                    // this.enqueueChildren();
                    // System.out
                    // .println("Stack after enqueue children: " + stack);
                    this.currentNode = null;

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

                    // System.out
                    // .println("In
                    // haaaaaaare!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    // System.out.println("Stack: " + this.stack);


                    final TreeNode<E> firstChild = this.currentNode
                            .getChildren()
                            .get(0);
                    // if (!this.stack.peekFirst().equals(firstChild)) {
                    //
                    // throw new IllegalStateException(
                    // "In-order iterator state has been corrupted "
                    // + "during removing. File bug report.");
                    // }

                    this.tree.setRoot(firstChild);
                    // System.out.println("First Child: " + firstChild);
                    // System.out.println("First child's children: "
                    // + firstChild.getChildren());

                    this.currentNode.removeChild(firstChild);


                    // System.out.println("First Element on Stack: "
                    // + this.stack.peekFirst());


                    this.stack.removeFirst();


                    // System.out.println("Stack: " + this.stack);

                    for (final TreeNode<E> child : this.currentNode
                            .getChildren()) {
                        firstChild.addChild(child);
                        this.stack.removeFirst();
                    }


                    // System.out.println("First child's children now: "
                    // + firstChild.getChildren());

                    // System.out.println("Stack: " + this.stack);

                    this.currentNode.clearChildren();

                    this.stack.addFirst(firstChild);
                    this.currentNode = null;

                }
            }
        }
    }

    // public void insert(E element) {
    //
    // if (this.currentNode == null) {
    // TreeNode<E> newElement = new TreeNode<E>(element);
    //
    // // Stack is empty, which means we either have an empty tree or are
    // // trying to insert after deleting the last element.
    // if (stack.isEmpty()) {
    //
    // // Empty tree.
    // // Inserting as root.
    // if (tree.isEmpty()) {
    // this.tree.setRoot(newElement);
    // }
    // // After deleting last element.
    // // Inserting after last element.
    // else {
    //
    // TreeNode<E> current = this.tree.getRoot();
    //
    // // Need to find the last element
    // while (!current.getChildren().isEmpty()) {
    //
    // // Last element of current
    // current = current.getChildren().get(
    // current.getChildren().size() - 1);
    // }
    //
    // current.addChild(element);
    // }
    // }
    // // Stack is not empty, which means we deleted an element inside the
    // // tree or haven't started iteration yet.
    // else {
    //
    // TreeNode<E> stackTop = stack.peekFirst();
    //
    // TreeNode<E> parent = stackTop.getParent();
    // // We haven't started iterating yet. The stack contains the root
    // // node.
    // // We are setting the new node as the root.
    // if (parent == null) {
    // tree.setRoot(newElement);
    // newElement.addChild(stackTop);
    // }
    // // During regular iteration.
    // // We are replacing the current stack top with the new node and
    // // adding it back into the tree as the new node's child.
    // else {
    // parent.replaceChild(stackTop, newElement);
    // newElement.addChild(stackTop);
    // }
    // }
    //
    // }
    // // Inserting during regular iteration.
    // else {
    //
    // // This is probably wrong...
    // this.currentNode.addChild(element);
    // }
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

        // System.out.println("Stack before enqueueChildren: " + this.stack);
        final TreeNode<E> current = this.stack.peekFirst();

        final List<TreeNode<E>> children = current.getChildren();

        // We only have to enqueue children if the current top of the stack has
        // children
        if (!children.isEmpty()) {

            this.stack.removeFirst();
            final ListIterator<TreeNode<E>> it = children.listIterator(
                    children.size());


            // We enqueue all children in reverse order
            while (it.hasPrevious()) {

                this.stack.addFirst(it.previous());
            }

            this.stack.addFirst(current);

        }
        // System.out.println("Stack after enqueueChildren: " + this.stack);

    }
}
