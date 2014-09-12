package org.jutility.common.datatype.tree;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;



/**
 * @author Peter J. Radics
 * @version 0.1
 * @param <VALUE>
 *            the value type.
 * 
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

        this.stack = new LinkedList<VALUE>();

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

        this.tree.remove(currentValue);
        this.currentValue = null;
    }


    private void enqueue(BTree<VALUE>.BTreeNode node) {

        if (!node.getChildren().isEmpty()) {

            for (int i = 0; i < node.getChildren().size(); i++) {

                this.enqueue(node.getChild(i));

                if (i < node.getEntries().size()) {

                    this.stack.add(node.getEntry(i));
                }

            }

        }
        else {
            for (VALUE entry : node.getEntries()) {

                this.stack.add(entry);
            }
        }
    }

}