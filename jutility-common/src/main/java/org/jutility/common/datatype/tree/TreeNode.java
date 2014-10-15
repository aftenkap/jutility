package org.jutility.common.datatype.tree;

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


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * The {@link TreeNode} class provides a tree node for a tree with an arbitrary
 * amount of children.
 * <p/>
 * Thread safety: fully thread safe through locking around critical sections and
 * immutable element.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <E>
 *            the type of the tree node's elements.
 */
public class TreeNode<E> {


    private final E                 element;

    private TreeNode<E>             parent;
    private final Lock              parentLock;

    private final List<TreeNode<E>> children;
    private final Lock              childLock;


    /**
     * Returns the tree node's element.
     * 
     * @return the element.
     */
    public E getElement() {

        return this.element;
    }

    /**
     * Returns whether or not the tree node has a parent.
     * 
     * @return <code>true</code>, if the tree node has a parent;
     *         <code>false</code> otherwise.
     */
    public boolean hasParent() {

        return this.getParent() != null;
    }

    /**
     * Returns the tree node's parent.
     * 
     * @return the parent.
     */
    public TreeNode<E> getParent() {

        TreeNode<E> parentCopy = null;
        this.parentLock.lock();
        parentCopy = this.parent;
        return parentCopy;
    }

    /**
     * Sets the tree node's parent.
     * 
     * @param parent
     *            the parent.
     */
    private void setParent(TreeNode<E> parent) {

        this.parentLock.lock();
        this.parent = parent;
        this.parentLock.unlock();

    }

    /**
     * Returns whether or not the tree node has children.
     * 
     * @return <code>true</code>, if the tree node has children;
     *         <code>false</code> otherwise.
     */
    public boolean hasChildren() {

        return !this.getChildren().isEmpty();
    }

    /**
     * Returns the tree node's children.
     * 
     * @return the children.
     */
    public List<TreeNode<E>> getChildren() {

        List<TreeNode<E>> returnValue = null;
        this.childLock.lock();

        returnValue = Collections.unmodifiableList(new ArrayList<TreeNode<E>>(
                this.children));
        return returnValue;
    }


    /**
     * Creates a new instance of the {@link TreeNode} class.
     * 
     * @param element
     *            the element of the newly created {@link TreeNode}.
     */
    protected TreeNode(E element) {

        this(element, null);
    }

    /**
     * Creates a new instance of the {@link TreeNode} class.
     * 
     * @param element
     *            the element of the newly created {@link TreeNode}.
     * @param parent
     *            the parent of the newly created {@link TreeNode}.
     */
    protected TreeNode(E element, TreeNode<E> parent) {

        if (element == null) {
            throw new IllegalArgumentException(
                    "Cannot create a tree node without a valid element!");
        }

        this.element = element;

        this.parent = parent;
        this.parentLock = new ReentrantLock();

        this.children = new LinkedList<TreeNode<E>>();
        this.childLock = new ReentrantLock();

        if (this.parent != null) {
            this.parent.addChild(this);
        }
    }


    /**
     * Adds the provided {@link TreeNode} as a child of this instance.
     * 
     * @param child
     *            the {@link TreeNode} to add as a child.
     */
    protected void addChild(TreeNode<E> child) {

        if (child == null) {
            throw new IllegalArgumentException("Cannot contain null elements!");
        }

        this.childLock.lock();
        if (!this.children.contains(child)) {
            this.children.add(child);
            child.setParent(this);
        }
        this.childLock.unlock();

    }

    /**
     * Adds the provided element in a new {@link TreeNode} as a child of this
     * instance.
     * 
     * @param childElement
     *            the element to add as a child.
     */
    protected void addChild(E childElement) {

        this.addChild(new TreeNode<E>(childElement, this));
    }

    /**
     * Removes the provided {@link TreeNode} from the children of this instance.
     * 
     * @param child
     *            the {@link TreeNode} to add as a child.
     */
    protected void removeChild(TreeNode<E> child) {

        if (child == null) {
            throw new IllegalArgumentException("Cannot contain null elements!");
        }

        this.childLock.lock();

        int index = this.children.indexOf(child);
        if (index != -1) {

            TreeNode<E> realChild = this.children.get(index);

            realChild.setParent(null);
            this.children.remove(index);
        }
        this.childLock.unlock();
    }

    /**
     * Removes the {@link TreeNode} containing the provided element from the
     * children of this instance.
     * 
     * @param childElement
     *            the element to add as a child.
     */
    protected void removeChild(E childElement) {

        this.removeChild(new TreeNode<E>(childElement, this));
    }


    /**
     * Replaces a {@link TreeNode} with a different {@link TreeNode} instance.
     * 
     * @param childToReplace
     *            the {@link TreeNode} to replace.
     * @param childToReplaceWith
     *            the {@link TreeNode} to act as replacement.
     */
    protected void replaceChild(TreeNode<E> childToReplace,
            TreeNode<E> childToReplaceWith) {

        if (childToReplace == null || childToReplaceWith == null) {
            throw new IllegalArgumentException("Cannot contain null elements!");
        }

        this.childLock.lock();
        try {


            int index = this.children.indexOf(childToReplace);

            if (index == -1) {
                throw new NoSuchElementException(
                        "The provided child to be replaced could not be found!");
            }
            else {

                int otherIndex = this.children.indexOf(childToReplaceWith);

                TreeNode<E> currentChild = this.children.get(index);
                currentChild.setParent(null);

                this.children.set(index, childToReplaceWith);

                if (otherIndex != -1 && index != otherIndex) {

                    TreeNode<E> otherChild = this.children.get(otherIndex);
                    otherChild.setParent(null);
                    this.children.remove(otherIndex);
                }
                childToReplaceWith.setParent(this);
            }
        }
        finally {
            this.childLock.unlock();
        }
    }

    /**
     * Replaces the {@link TreeNode} of an element with a {@link TreeNode} of a
     * different element.
     * 
     * @param childElementToReplace
     *            the element to replace.
     * @param childElementToReplaceWith
     *            the element to act as replacement.
     */
    protected void replaceChild(E childElementToReplace,
            E childElementToReplaceWith) {

        this.replaceChild(new TreeNode<E>(childElementToReplace),
                new TreeNode<E>(childElementToReplaceWith));
    }

    /**
     * Clears the children of this instance.
     */
    protected void clearChildren() {

        this.childLock.lock();

        for (TreeNode<E> child : this.children) {
            child.setParent(null);
        }

        this.children.clear();
        this.childLock.unlock();
    }


    /**
     * Returns the depth (distance from the root {@link TreeNode}) of this
     * instance.
     * 
     * @return the depth.
     */
    public int depth() {

        int depth = 0;
        TreeNode<?> walker = this;

        while (walker.getParent() != null) {
            depth++;
            walker = walker.getParent();
        }

        return depth;
    }

    /**
     * Returns the number of siblings of this instance.
     * 
     * @return the number of siblings.
     */
    public int numberOfSiblings() {

        int numberOfSiblings = 0;

        if (this.hasParent()) {

            return this.getParent().getChildren().size() - 1;
        }

        return numberOfSiblings;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {

        if (o != null && o instanceof TreeNode) {
            TreeNode<?> treeNode = (TreeNode<?>) o;

            return (this.getElement().equals(treeNode.getElement()));
        }
        return false;

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        return this.element.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return this.element.toString();
    }
}
