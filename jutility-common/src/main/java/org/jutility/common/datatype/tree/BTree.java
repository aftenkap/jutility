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

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;


/**
 * The {@code BTree} class provides a B-Tree implementation.
 *
 * @param <VALUE>
 *         the value type.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class BTree<VALUE extends Comparable<VALUE>>
        extends AbstractCollection<VALUE>
        implements SortedSet<VALUE> {

    private final int maximumNumberOfChildren;
    private final int minimumNumberOfChildren;

    private int height;
    private int size;

    private BTreeNode root;


    /**
     * Creates a new instance of the {@code BTree} class.
     *
     * @param maximumNumberOfChildren
     *         the maximum number of children.
     */
    public BTree(final int maximumNumberOfChildren) {

        this.maximumNumberOfChildren = maximumNumberOfChildren;
        this.minimumNumberOfChildren = (int) Math.ceil(
                maximumNumberOfChildren / 2.0);

        this.root = new BTreeNode();
    }


    /**
     * Returns the root node.
     *
     * @return the root node.
     */
    protected BTreeNode getRoot() {

        return this.root;
    }

    /**
     * Sets the root node.
     *
     * @param root
     *         the root node.
     */
    protected void setRoot(final BTreeNode root) {

        this.root = root;
    }

    /**
     * Returns the maximum number of children.
     *
     * @return the maximum number of children.
     */
    public int getMaximumNumberOfChildren() {

        return this.maximumNumberOfChildren;
    }


    /**
     * Returns the minimum number of children.
     *
     * @return the minimum number of children.
     */
    public int getMinimumNumberOfChildren() {

        return this.minimumNumberOfChildren;
    }

    /**
     * Returns the height of the tree.
     *
     * @return the height of the tree.
     */
    public int height() {

        return this.height;
    }

    /**
     * Sets the height.
     *
     * @param height
     *         the height.
     */
    void setHeight(final int height) {

        this.height = height;
    }


    @Override
    public int size() {

        return this.size;
    }

    @Override
    public boolean contains(final Object key) {

        if (key instanceof Comparable<?>) {

            try {
                final VALUE returnValue = this.root.find((Comparable<?>) key);

                return (returnValue != null);
            }
            catch (final ClassCastException ex) {

                return false;
            }
        }
        return false;
    }

    /**
     * Returns the value at key.
     *
     * @param key
     *         the key.
     *
     * @return the value.
     */
    public VALUE get(final VALUE key) {

        return this.root.find(key);
    }

    /**
     * Removes the value at key.
     *
     * @param key
     *         the key.
     *
     * @return the value.
     */
    public VALUE remove(final VALUE key) {

        final VALUE deletedEntry = this.root.remove(key);

        if (deletedEntry == null) {
            return null;
        }
        else {
            this.size--;
            return deletedEntry;
        }
    }


    /**
     * Returns the largest key.
     *
     * @return the largest key.
     */
    public VALUE largestKey() {

        final VALUE returnValue = this.root.findLargestEntry();

        if (returnValue != null) {
            return returnValue;
        }
        return null;
    }


    /**
     * Returns the smallest key.
     *
     * @return the smallest key.
     */
    public VALUE smallestKey() {

        final VALUE returnValue = this.root.findSmallestEntry();

        if (returnValue != null) {
            return returnValue;
        }
        return null;
    }


    @Override
    public boolean add(final VALUE value) {


        final VALUE replacedEntry = this.root.insert(value);

        if (replacedEntry == null) {
            this.size++;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Iterator<VALUE> iterator() {

        return new BTreeInOrderIterator<>(this);
    }



    @Override
    public Comparator<? super VALUE> comparator() {

        return null;
    }



    // Returns a view of the portion of this set whose elements range from
    // fromElement, inclusive, to toElement, exclusive.
    @Override
    public SortedSet<VALUE> subSet(final VALUE fromElement,
            final VALUE toElement) {

        // TODO Auto-generated method stub
        return null;
    }


    // Returns a view of the portion of this set whose elements are strictly
    // less than toElement.
    @Override
    public SortedSet<VALUE> headSet(final VALUE toElement) {

        // TODO Auto-generated method stub
        return null;
    }

    // Returns a view of the portion of this set whose elements are greater than
    // or equal to fromElement.
    @Override
    public SortedSet<VALUE> tailSet(final VALUE fromElement) {

        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public VALUE first() {

        return this.root.findSmallestEntry();
    }


    @Override
    public VALUE last() {

        return this.root.findLargestEntry();
    }



    /**
     * The {@code BTreeNode} class provides a node of a {@link BTree}.
     *
     * @author Peter J. Radics
     * @version 0.1.2
     * @since 0.1.0
     */
    public class BTreeNode {


        private final List<VALUE>     entries;
        private final List<BTreeNode> children;

        private BTreeNode parent;


        /**
         * Returns the entries.
         *
         * @return the entries.
         */
        public List<VALUE> getEntries() {

            return this.entries;
        }

        /**
         * Returns the entry at the given index.
         *
         * @param index
         *         the index.
         *
         * @return the entry at the given index.
         */
        public VALUE getEntry(final int index) {

            return this.entries.get(index);
        }

        /**
         * Adds the entry at the given index.
         *
         * @param index
         *         the index.
         * @param entry
         *         the entry to add.
         */
        public void addEntry(final int index, final VALUE entry) {

            this.entries.add(index, entry);
        }

        /**
         * Adds an entry.
         *
         * @param entry
         *         the entry to add.
         */
        public void addEntry(final VALUE entry) {

            this.entries.add(entry);
        }

        /**
         * Removes the entry at the given index.
         *
         * @param index
         *         the index.
         *
         * @return the removed entry.
         */
        public VALUE removeEntry(final int index) {

            return this.entries.remove(index);
        }

        /**
         * Returns the children of this Tree.
         *
         * @return the children of this Tree.
         */
        public List<BTreeNode> getChildren() {

            return this.children;
        }

        /**
         * Returns the child with the given index.
         *
         * @param index
         *         the index.
         *
         * @return the child with the given index.
         */
        public BTreeNode getChild(final int index) {

            return this.children.get(index);
        }

        /**
         * Adds a child at the given index.
         *
         * @param index
         *         the index.
         * @param child
         *         the child to add.
         */
        public void addChild(final int index, final BTreeNode child) {

            this.children.add(index, child);
            child.setParent(this);
        }

        /**
         * Adds a child.
         *
         * @param child
         *         the child to add.
         */
        public void addChild(final BTreeNode child) {

            this.children.add(child);
            child.setParent(this);
        }

        /**
         * Removes the child at the given index.
         *
         * @param index
         *         the index.
         *
         * @return the removed child.
         */
        public BTreeNode removeChild(final int index) {

            return this.children.remove(index);
        }


        /**
         * Returns the parent of this node.
         *
         * @return the parent of this node.
         */
        public BTreeNode getParent() {

            return this.parent;
        }


        /**
         * Sets the parent of this node.
         *
         * @param parent
         *         the parent.
         */
        public void setParent(final BTreeNode parent) {

            this.parent = parent;
        }


        /**
         * Creates a new instance of the {@code BTreeNode} class.
         */
        public BTreeNode() {

            this(null);
        }

        /**
         * Creates a new instance of the {@code BTreeNode} class with the given
         * parent.
         *
         * @param parent
         *         the parent.
         */
        public BTreeNode(final BTreeNode parent) {

            this.entries = new ArrayList<>(
                    BTree.this.getMaximumNumberOfChildren() - 1);
            this.children = new ArrayList<>(
                    BTree.this.getMaximumNumberOfChildren());
            this.parent = parent;
        }


        /**
         * Finds the entry.
         *
         * @param entry
         *         the entry to find.
         *
         * @return the value of the entry.
         */
        public VALUE find(final Comparable<?> entry) {

            int insertionIndex = Arrays.binarySearch(this.entries.toArray(),
                    entry);

            // entry is not contained in this node
            if (insertionIndex < 0) {

                // calculating real insertion index
                insertionIndex = (insertionIndex + 1) * -1;

                if (insertionIndex < this.children.size()) {
                    return this.getChild(insertionIndex)
                               .find(entry);
                }
                else {
                    return null;
                }
            }
            // entry was found in this node
            else {
                return this.entries.get(insertionIndex);
            }

        }

        /**
         * Returns the largest entry.
         *
         * @return the largest entry.
         */
        VALUE findLargestEntry() {

            if (this.children.size() == 0) {
                if (this.entries.size() > 0) {
                    return this.entries.get(this.entries.size() - 1);
                }
                else {
                    return null;
                }
            }
            else {
                return this.children.get(this.children.size() - 1)
                                    .findLargestEntry();
            }
        }

        /**
         * Returns the smallest entry.
         *
         * @return the smallest entry.
         */
        VALUE findSmallestEntry() {

            if (this.children.size() == 0) {

                if (this.entries.size() > 0) {

                    return this.entries.get(0);
                }
                else {

                    return null;
                }
            }
            else {
                return this.children.get(0)
                                    .findSmallestEntry();
            }
        }



        /**
         * Inserts an entry.
         *
         * @param entry
         *         the entry to insert.
         *
         * @return the replaced entry.
         */
        public VALUE insert(final VALUE entry) {

            int insertionIndex = Arrays.binarySearch(this.entries.toArray(),
                    entry);
            VALUE replacedEntry = null;

            // Key already in the tree
            if (insertionIndex >= 0) {

                replacedEntry = this.removeEntry(insertionIndex);
                this.addEntry(insertionIndex, entry);

                return replacedEntry;
            }
            // Calculating real insertion index
            else {

                insertionIndex = (insertionIndex + 1) * -1;
            }

            // internal node
            if (this.children.size() != 0) {

                replacedEntry = this.getChild(insertionIndex)
                                    .insert(entry);
            }
            // leaf node
            else {

                final BTreeNode nodeToInsert = new BTreeNode(this.getParent());
                nodeToInsert.addEntry(entry);
                this.merge(nodeToInsert, insertionIndex);
            }

            if (this.entries.size()
                >= BTree.this.getMaximumNumberOfChildren()) {

                // System.out.println("Splitting!");
                this.split();
            }

            return replacedEntry;
        }

        private void merge(final BTreeNode nodeToMergeWith,
                final int insertionIndex) {

            // System.out.println("Size (entries) before: " +
            // this.entries.size());
            // System.out.println("Size (children) before: "
            // + this.children.size());
            // System.out.println("\tMerging "
            // + nodeToMergeWith.getEntries().size() + " entries.");
            for (int i = 0; i < nodeToMergeWith.getEntries()
                                               .size(); i++) {

                this.addEntry(insertionIndex, nodeToMergeWith.getEntry(i));
            }

            // System.out.println("\tMerging "
            // + nodeToMergeWith.getChildren().size() + " children.");
            for (int i = 0; i < nodeToMergeWith.getChildren()
                                               .size(); i++) {

                this.addChild(insertionIndex + i, nodeToMergeWith.getChild(i));
            }
            // System.out.println("Size (entries) after: " +
            // this.entries.size());
            // System.out
            // .println("Size (children) after: " + this.children.size());
        }


        private BTreeNode split() {

            final BTreeNode leftNode = new BTreeNode(this);
            final BTreeNode rightNode = new BTreeNode(this);


            final List<VALUE> allEntries = new ArrayList<>(this.getEntries());
            final List<BTreeNode> allChildren = new ArrayList<>(
                    this.getChildren());

            this.entries.clear();
            this.children.clear();

            // A single median is chosen from among the leaf's elements and the
            // new element.
            int median = BTree.this.getMinimumNumberOfChildren();
            if ((median % 2) == 0) {
                median--;
            }

            // Values less than the median are put in the new left node and
            // values greater than the median are put in the new right node,
            // with the median acting as a separation value.
            for (int i = 0; i < allEntries.size(); i++) {

                if (i < median) {

                    leftNode.addEntry(allEntries.get(i));
                }
                else if (i > median) {

                    rightNode.addEntry(allEntries.get(i));
                }
                else {

                    this.addEntry(allEntries.get(i));
                }
            }
            for (int i = 0; i < allChildren.size(); i++) {

                if (i <= median) {

                    leftNode.addChild(allChildren.get(i));
                }
                else if (i > median) {

                    rightNode.addChild(allChildren.get(i));
                }
            }

            this.addChild(leftNode);
            this.addChild(rightNode);

            if (this.parent != null) {

                this.parent.removeChild(this.index());
                int insertionIndex = Arrays.binarySearch(
                        this.parent.getEntries()
                                   .toArray(), this.getEntry(0));

                if (insertionIndex >= 0) {
                    throw new IllegalStateException(
                            "There seems to be a duplicate entry for key "
                            + this.getEntry(0));
                }
                else {
                    insertionIndex = (insertionIndex + 1) * -1;
                }

                // The separation value is inserted in the node's parent,
                this.parent.merge(this, insertionIndex);
            }
            else {
                BTree.this.setHeight(BTree.this.height() + 1);
            }

            return this;
        }


        /**
         * Removes the entry.
         *
         * @param entry
         *         the entry to remove.
         *
         * @return the removed entry.
         */
        public VALUE remove(final VALUE entry) {

            VALUE removedValue = null;
            int insertionIndex = Arrays.binarySearch(this.entries.toArray(),
                    entry);

            boolean found = false;
            // Key already in the tree
            if (insertionIndex >= 0) {

                found = true;
            }
            // Calculating real insertion index
            else {

                insertionIndex = (insertionIndex + 1) * -1;
            }

            // internal node
            if (this.children.size() != 0) {

                if (!found) {

                    removedValue = this.getChild(insertionIndex)
                                       .remove(entry);
                }
                else {

                    final BTreeNode affectedChild = this.getChild(
                            insertionIndex);

                    // Choose a new separator (either the largest element in the
                    // left subtree or the smallest element in the right
                    // subtree),
                    final VALUE separator = affectedChild.findLargestEntry();

                    // replace the element to be deleted with the new separator.
                    removedValue = this.removeEntry(insertionIndex);
                    this.addEntry(insertionIndex, separator);

                    // remove it from the leaf node it is in
                    affectedChild.remove(separator);
                }
            }
            // leaf node
            else {

                if (found) {
                    removedValue = this.removeEntry(insertionIndex);
                }
            }

            this.rebalance();

            return removedValue;

        }

        private void rebalance() {

            final BTreeNode parent = this.parent;
            // are we root?
            if (parent != null) {
                // System.out.println(toStringHelper(parent, "Parent: "));

                if (this.entries.size() < (
                        BTree.this.getMinimumNumberOfChildren() - 1)) {

                    // System.out.println("Rebalancing:");
                    // System.out.println(toStringHelper(this, "Tree: "));

                    final BTreeNode leftSibling = this.leftSibling();
                    final BTreeNode rightSibling = this.rightSibling();

                    // In the second caseT has minimumNumberOfChildren - 2 keys
                    // and it also has a sibling immediately on the left with at
                    // least minimumNumberOfChildren keys.
                    // The tree can be balanced by doing an LL rotation.
                    // Notice that after the rotation, both siblings have at
                    // least minimumNumberOfChildren keys. Furthermore, the
                    // heights of the siblings remain unchanged. Therefore, the
                    // resulting tree is a valid B-tree.
                    if ((leftSibling != null) && (leftSibling.getEntries()
                                                             .size()
                                                  >= BTree.this
                                                          .getMinimumNumberOfChildren())) {

                        // System.out.println("LL Rotation!");
                        final int index = leftSibling.index();

                        final VALUE maxLeft = leftSibling.removeEntry(
                                leftSibling.getEntries()
                                           .size() - 1);

                        final VALUE parentKey = parent.removeEntry(index);

                        parent.addEntry(index, maxLeft);
                        this.addEntry(0, parentKey);

                        if (!leftSibling.getChildren()
                                        .isEmpty()) {
                            final BTreeNode maxChildLeft = leftSibling
                                    .removeChild(
                                    leftSibling.getChildren()
                                               .size() - 1);
                            this.addChild(0, maxChildLeft);
                        }

                    }
                    // The third case is the left-right mirror of the second
                    // case.
                    // That is, T has minimumNumberOfChildren - 2 keys and it
                    // also has a sibling immediately on the right with a least
                    // minimumNumberOfChildren keys. In this case, the tree can
                    // be balanced by doing an RR rotation.
                    else if ((rightSibling != null) && (
                            rightSibling.getEntries()
                                        .size()
                            >= BTree.this.getMinimumNumberOfChildren())) {

                        // System.out.println("RR Rotation!");
                        final int index = rightSibling.index();

                        final VALUE minRight = rightSibling.removeEntry(0);

                        final VALUE parentKey = parent.removeEntry(index - 1);
                        parent.addEntry(index - 1, minRight);

                        this.addEntry(0, parentKey);

                        if (!rightSibling.getChildren()
                                         .isEmpty()) {
                            final BTreeNode minChildRight = rightSibling
                                    .removeChild(
                                    0);
                            this.addChild(minChildRight);
                        }
                    }
                    // In the fourth and final case, T has
                    // minimumNumberOfChildren - 2 keys, and its immediate
                    // sibling(s) have minimumNumberOfChildren - 1 keys.
                    // In this case, the sibling(s) cannot give-up a key in a
                    // rotation because they already have the minimum number of
                    // keys. The solution is to merge T with one of its
                    // siblings.
                    //
                    // The merged node contains minimumNumberOfChildren - 2 keys
                    // from T, minimumNumberOfChildren - 1 keys from the
                    // sibling, and one key from the parent. The resulting node
                    // contains 2 (minimumNumberOfChildren - 2) keys altogether,
                    // which is M-2 if M is even and M-1 if M is odd. Either
                    // way, the resulting node contains no more than M-1 keys
                    // and is a valid B-tree node. Notice that in this case a
                    // key has been removed from the parent of T. Therefore, it
                    // may be necessary to balance the parent. Balancing the
                    // parent may necessitate balancing the grandparent, and so
                    // on, up the tree to the root.
                    else {

                        if (leftSibling != null) {

                            // System.out.println("Merging left into this
                            // node!");
                            final int index = leftSibling.index();

                            // remove key from parent
                            final VALUE parentKey = parent.removeEntry(index);
                            // remove left sibling
                            parent.removeChild(index);
                            this.merge(leftSibling, 0);

                            int insertionIndex = Arrays.binarySearch(
                                    this.entries.toArray(), parentKey);
                            insertionIndex = (insertionIndex + 1) * -1;
                            this.addEntry(insertionIndex, parentKey);
                        }
                        else if (rightSibling != null) {

                            // System.out.println("Merging this into right
                            // node!");
                            final int index = this.index();
                            // remove key from parent
                            final VALUE parentKey = parent.removeEntry(index);
                            // remove ourself
                            parent.removeChild(index);


                            rightSibling.merge(this, 0);
                            int insertionIndex = Arrays.binarySearch(
                                    rightSibling.entries.toArray(), parentKey);
                            insertionIndex = (insertionIndex + 1) * -1;
                            rightSibling.addEntry(insertionIndex, parentKey);
                        }
                        // To be a valid BTree, either left or right sibling of
                        // a node have to be present (A node cannot have only
                        // one child!)
                        else {
                            // parent.removeChild(this.index());
                            // System.out.println("A: Reducing height from " +
                            // height);
                            // height--;
                            throw new IllegalStateException(
                                    "Tree corrupted: Leaf Node with a single "
                                    + "child detected!");
                        }
                    }

                }
            }
            else {
                if (this.entries.size() == 0) {
                    if (this.children.size() > 0) {
                        // System.out.println("New root!");
                        // System.out.println("Children: " +
                        // this.children.size());
                        BTree.this.setRoot(this.getChild(0));
                        BTree.this.getRoot()
                                  .setParent(null);
                        // System.out.println("B: Reducing height from " +
                        // height);
                        BTree.this.setHeight(BTree.this.height() - 1);
                    }
                    else {
                        // System.out.println("Empty tree!");
                    }

                }
                else {
                    // System.out.println("Root. Nothing to be done!");
                }
            }


        }

        private int index() {

            return this.parent.getChildren()
                              .indexOf(this);
        }

        private BTreeNode rightSibling() {

            BTreeNode rightSibling = null;

            if (this.index() < (this.parent.getChildren()
                                           .size() - 1)) {
                rightSibling = this.parent.getChildren()
                                          .get(this.index() + 1);
            }

            return rightSibling;
        }

        private BTreeNode leftSibling() {

            BTreeNode leftSibling = null;


            if (this.index() > 0) {
                leftSibling = this.parent.getChildren()
                                         .get(this.index() - 1);
            }

            return leftSibling;
        }


    }


    @Override
    public String toString() {

        return this.toStringHelper(this.root, "") + "\n";
    }

    private String toStringHelper(final BTreeNode node, final String indent) {

        String returnValue = "";
        final List<VALUE> entries = node.getEntries();
        final List<BTreeNode> children = node.getChildren();

        int j = 0;
        final int max = Math.max(entries.size(), children.size());
        if (entries.size() == 0) {
            returnValue += indent + "[]\n";
        }
        for (; j < max; j++) {
            if (j < children.size()) {
                returnValue += this.toStringHelper(children.get(j),
                        indent + "\t");
            }
            if (j < entries.size()) {
                returnValue += indent + entries.get(j) + "\n";
            }
        }
        if (j < children.size()) {
            returnValue += this.toStringHelper(children.get(j), indent + "\t");
        }
        return returnValue;
    }

    /**
     * @param args
     *         unused
     */
    public static void main(final String[] args) {

        final BTree<String> st = new BTree<>(3);

        System.out.println("Maximum number of children:  "
                           + st.getMaximumNumberOfChildren());
        System.out.println("Minimum number of children:  "
                           + st.getMinimumNumberOfChildren());

        // st.put("D", "128.112.136.12");
        st.add("D");
        st.add("K");
        st.add("P");
        st.add("L");
        st.add("B");
        st.add("A");
        st.add("F");
        st.add("C");
        st.add("H");
        st.add("J");
        st.add("I");
        st.add("E");
        st.add("M");
        st.add("G");
        st.add("N");
        st.add("O");
        st.add("Q");
        st.add("R");
        st.add("S");
        st.add("T");

        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("I");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("E");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        //
        // st.remove("J");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        //
        // System.out.println("==================================\n\n");
        // st.remove("H");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        //
        // System.out.println("==================================\n\n");
        // st.remove("M");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("D");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("O");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("N");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("L");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("K");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("G");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("F");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("Q");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("P");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("T");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("C");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("B");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("R");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("A");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println("==================================\n\n");
        // st.remove("S");
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        //
        //
        // System.out.println("size:    " + st.size());
        // System.out.println("height:  " + st.height());
        // System.out.println(st);
        // System.out.println();

        int i = 0;
        for (final String value : st) {
            System.out.println("Value " + i++ + ": " + value);
        }

        System.out.println("size:    " + st.size());
        System.out.println("height:  " + st.height());
        System.out.println(st);


        i = 0;
        final Iterator<String> it = st.iterator();
        while (it.hasNext()) {

            System.out.println("Value " + i++ + ": " + it.next());
            it.remove();
            System.out.println("Size: " + st.size());
            System.out.println("Height:  " + st.height());
        }
        System.out.println("size:    " + st.size());
        System.out.println("height:  " + st.height());
        System.out.println(st);

    }


}
