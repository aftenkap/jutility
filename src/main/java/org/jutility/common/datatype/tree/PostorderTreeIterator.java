package org.jutility.common.datatype.tree;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <E>
 *            the value type.
 */
public class PostorderTreeIterator<E>
        implements Iterator<E> {



    private final Tree<E>                 tree;

    private final LinkedList<TreeNode<E>> stack;
    private TreeNode<E>                   currentNode;



    /**
     * Creates a new instance of the {@code PostorderTreeIterator} class.
     * 
     * @param tree
     *            the tree to iterate over.
     */
    protected PostorderTreeIterator(final Tree<E> tree) {

        this.tree = tree;

        this.stack = new LinkedList<TreeNode<E>>();

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

            E returnValue = this.currentNode.getElement();

            return returnValue;
        }
        else {
            throw new NoSuchElementException(
                    "Trying to iterate past the last element of the tree.");
        }
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
                if (this.currentNode.getChildren().isEmpty()) {

                    this.currentNode.getParent().removeChild(this.currentNode);
                }
                // Not leaf node
                else {

                    // System.out.println("\n\nIn here");
                    TreeNode<E> parent = this.currentNode.getParent();
                    TreeNode<E> lastChild = this.currentNode.getChildren().get(
                            this.currentNode.getChildren().size() - 1);

                    // System.out.println("Stack: " + this.stack);
                    // System.out.println("Current: "
                    // + this.currentNode.getElement());
                    // System.out.println("Parent: " + parent.getElement());
                    // System.out.println("Last Child: " +
                    // lastChild.getElement());


                    this.currentNode.removeChild(lastChild);

                    parent.replaceChild(currentNode, lastChild);

                    List<TreeNode<E>> children = lastChild.getChildren();
                    lastChild.clearChildren();

                    for (TreeNode<E> child : this.currentNode.getChildren()) {
                        lastChild.addChild(child);
                    }
                    for (TreeNode<E> child : children) {
                        lastChild.addChild(child);
                    }

                    // System.out.println("\n\n");

                }
            }
            // Root
            else {

                // Leaf node
                if (this.currentNode.getChildren().isEmpty()) {

                    this.tree.setRoot(null);
                }
                // Not leaf node
                else {

                    TreeNode<E> lastChild = this.currentNode.getChildren().get(
                            this.currentNode.getChildren().size() - 1);

                    this.tree.setRoot(lastChild);

                    this.currentNode.removeChild(lastChild);

                    List<TreeNode<E>> children = lastChild.getChildren();
                    lastChild.clearChildren();

                    for (TreeNode<E> child : this.currentNode.getChildren()) {
                        lastChild.addChild(child);
                    }
                    for (TreeNode<E> child : children) {
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

        TreeNode<E> current = this.stack.peekFirst();

        List<TreeNode<E>> children = current.getChildren();

        // We only have to enqueue children if the current top of the stack has
        // children
        if (!children.isEmpty()) {


            ListIterator<TreeNode<E>> it = children.listIterator(children
                    .size());

            // We enqueue all children in reverse order
            while (it.hasPrevious()) {
                this.stack.addFirst(it.previous());

                this.enqueueChildren();
            }

        }

    }
}
