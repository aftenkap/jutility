package org.jutility.common.datatype.tree;



//@formatter:off
/*
* #%L
 * * jutility-common
 * *
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * *
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


import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * The {@code TreeTest} class provides unit tests for the {@link Tree} class.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class TreeTest {

    private Tree<String>      stringTree;
    private ArrayList<String> stringList;

    /**
     * Set Up.
     */
    @Before
    public void setUp() {

        this.stringTree = new Tree<>();
        this.stringList = new ArrayList<>();

        this.stringList.add("A");
        this.stringList.add("B");
        this.stringList.add("C");
        this.stringList.add("D");
        this.stringList.add("E");
        this.stringList.add("F");
        this.stringList.add("G");
        this.stringList.add("H");
        this.stringList.add("I");
        this.stringList.add("O");

    }


    /**
     * Test method for {@link org.jutility.common.datatype.tree.Tree#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {

        Assert.assertTrue(this.stringTree.isEmpty());
        this.stringTree.add("F");
        Assert.assertFalse(this.stringTree.isEmpty());


        System.out.println("IsEmpty: passed!");
    }

    /**
     * Test method for {@link org.jutility.common.datatype.tree.Tree#clear()}.
     */
    @Test
    public void testClear() {

        this.stringTree.add("F");
        this.stringTree.addChild("F", "B");
        this.stringTree.addChild("F", "O");
        this.stringTree.addChild("F", "G");
        this.stringTree.addChild("B", "A");
        this.stringTree.addChild("B", "D");
        this.stringTree.addChild("D", "C");
        this.stringTree.addChild("D", "E");
        this.stringTree.addChild("G", "I");
        this.stringTree.addChild("I", "H");

        Assert.assertEquals(10, this.stringTree.size());
        this.stringTree.clear();
        Assert.assertEquals(0, this.stringTree.size());
        Assert.assertTrue(this.stringTree.isEmpty());


        System.out.println("Clear: passed!");
    }


    /**
     * Test method for {@link org.jutility.common.datatype.tree.Tree#iterator()}
     * .
     */
    @Test
    public void testIterator() {

        final PreorderTreeIterator<String> it = this.stringTree
                .preorderIterator();

        Exception e = null;
        try {
            it.next();
        }
        catch (final Exception ex) {
            e = ex;
        }
        Assert.assertNotNull(e);
        Assert.assertTrue(e instanceof NoSuchElementException);
        Assert.assertEquals(
                "Trying to iterate past the last element of the tree.",
                e.getMessage());

        e = null;
        try {
            it.remove();
        }
        catch (final Exception ex) {
            e = ex;
        }
        Assert.assertNotNull(e);
        Assert.assertTrue(e instanceof IllegalStateException);
        Assert.assertEquals(
                "Cannot remove element without calling next() immediately "
                + "before.", e.getMessage());

        System.out.println("Iterator: passed!");
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.Tree#add(java.lang.Object)},
     * {@link org.jutility.common.datatype.tree.Tree#addChild(java.lang.Object, java.lang.Object)} ,{@link java.util.AbstractCollection#contains(java.lang.Object)},
     * {@link org.jutility.common.datatype.tree.Tree#remove(java.lang.Object)},
     * and {@link org.jutility.common.datatype.tree.Tree#size()}.
     */
    @Test
    public void testAddRemoveContainsSizeE() {

        Assert.assertEquals(0, this.stringTree.size());

        Assert.assertTrue(this.stringTree.add("F"));
        Assert.assertFalse(this.stringTree.add("F"));
        Assert.assertEquals(1, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("F"));


        Assert.assertTrue(this.stringTree.remove("F"));
        Assert.assertEquals(0, this.stringTree.size());
        Assert.assertFalse(this.stringTree.remove("F"));
        Assert.assertFalse(this.stringTree.contains("F"));


        Assert.assertTrue(this.stringTree.add("F"));
        Assert.assertEquals(1, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("F"));


        Assert.assertTrue(this.stringTree.add("B"));
        Assert.assertEquals(2, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("B"));

        Assert.assertTrue(this.stringTree.addChild("F", "O"));
        Assert.assertEquals(3, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("O"));

        Assert.assertTrue(this.stringTree.addChild("F", "G"));
        Assert.assertEquals(4, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("G"));

        Assert.assertTrue(this.stringTree.addChild("B", "A"));
        Assert.assertEquals(5, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("A"));

        Assert.assertTrue(this.stringTree.addChild("B", "D"));
        Assert.assertEquals(6, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("D"));

        Assert.assertTrue(this.stringTree.addChild("D", "C"));
        Assert.assertEquals(7, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("C"));

        Assert.assertTrue(this.stringTree.addChild("D", "E"));
        Assert.assertEquals(8, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("E"));

        Assert.assertTrue(this.stringTree.addChild("G", "I"));
        Assert.assertEquals(9, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("I"));

        Assert.assertTrue(this.stringTree.addChild("A", "M"));
        Assert.assertEquals(10, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("M"));

        Assert.assertTrue(this.stringTree.addChild("A", "N"));
        Assert.assertEquals(11, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("N"));

        Assert.assertTrue(this.stringTree.addChild("M", "Q"));
        Assert.assertEquals(12, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("Q"));

        Assert.assertTrue(this.stringTree.addChild("I", "H"));
        Assert.assertEquals(13, this.stringTree.size());
        Assert.assertTrue(this.stringTree.contains("H"));

        Assert.assertFalse(this.stringTree.addChild("Z", "Z"));
        Assert.assertEquals(13, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("Z"));

        Assert.assertTrue(this.stringTree.remove("O"));
        Assert.assertEquals(12, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("O"));

        Assert.assertTrue(this.stringTree.remove("A"));
        Assert.assertEquals(11, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("A"));

        Assert.assertTrue(this.stringTree.remove("G"));
        Assert.assertEquals(10, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("G"));

        Assert.assertTrue(this.stringTree.remove("F"));
        Assert.assertEquals(9, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("F"));

        System.out.println("AddRemoveContainsSize: passed!");
    }


    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.Tree#postorderIterator()}.
     */
    @Test
    public void testPreorderIterator() {

        final PreorderTreeIterator<String> it = this.stringTree
                .preorderIterator();

        Exception e = null;
        try {
            it.next();
        }
        catch (final Exception ex) {
            e = ex;
        }
        Assert.assertNotNull(e);
        Assert.assertTrue(e instanceof NoSuchElementException);
        Assert.assertEquals(
                "Trying to iterate past the last element of the tree.",
                e.getMessage());

        e = null;
        try {
            it.remove();
        }
        catch (final Exception ex) {
            e = ex;
        }

        Assert.assertNotNull(e);
        Assert.assertTrue(e instanceof IllegalStateException);
        Assert.assertEquals(
                "Cannot remove element without calling next() immediately "
                + "before.", e.getMessage());


        Assert.assertEquals("[]", this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.add("F"));
        Assert.assertEquals("[F]", this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.add("B"));
        Assert.assertEquals("[F, B]", this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("F", "O"));
        Assert.assertEquals("[F, B, O]", this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("F", "G"));
        Assert.assertEquals("[F, B, O, G]", this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("B", "A"));
        Assert.assertEquals("[F, B, A, O, G]", this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("B", "D"));
        Assert.assertEquals("[F, B, A, D, O, G]", this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("D", "C"));
        Assert.assertEquals("[F, B, A, D, C, O, G]",
                this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("D", "E"));
        Assert.assertEquals("[F, B, A, D, C, E, O, G]",
                this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("G", "I"));
        Assert.assertEquals("[F, B, A, D, C, E, O, G, I]",
                this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("A", "M"));
        Assert.assertEquals("[F, B, A, M, D, C, E, O, G, I]",
                this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("A", "N"));
        Assert.assertEquals("[F, B, A, M, N, D, C, E, O, G, I]",
                this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("M", "Q"));
        Assert.assertEquals("[F, B, A, M, Q, N, D, C, E, O, G, I]",
                this.stringTree.toString());
        System.out.println(this.stringTree);

        Assert.assertTrue(this.stringTree.addChild("I", "H"));
        Assert.assertEquals("[F, B, A, M, Q, N, D, C, E, O, G, I, H]",
                this.stringTree.toString());
        System.out.println(this.stringTree);


        // System.out.println("Removing O:");
        this.preorderRemove(this.stringTree, "O");
        Assert.assertEquals(12, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("O"));
        Assert.assertEquals("[F, B, A, M, Q, N, D, C, E, G, I, H]",
                this.stringTree.toString());

        // System.out.println("\nRemoving A:");
        this.preorderRemove(this.stringTree, "A");
        Assert.assertEquals(11, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("A"));
        Assert.assertEquals("[F, B, M, Q, N, D, C, E, G, I, H]",
                this.stringTree.toString());

        // System.out.println("\nRemoving G:");
        this.preorderRemove(this.stringTree, "G");
        Assert.assertEquals(10, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("G"));
        Assert.assertEquals("[F, B, M, Q, N, D, C, E, I, H]",
                this.stringTree.toString());

        // System.out.println("Removing F:");
        this.preorderRemove(this.stringTree, "F");
        Assert.assertEquals(9, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("F"));
        Assert.assertEquals("[B, M, Q, N, D, C, E, I, H]",
                this.stringTree.toString());


        System.out.println("PreorderIterator: passed!");
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.Tree#postorderIterator()}.
     */
    @Test
    public void testPostorderIterator() {

        PostorderTreeIterator<String> it = this.stringTree.postorderIterator();

        Exception e = null;
        try {
            it.next();
        }
        catch (final Exception ex) {
            e = ex;
        }
        Assert.assertNotNull(e);
        Assert.assertTrue(e instanceof NoSuchElementException);
        Assert.assertEquals(
                "Trying to iterate past the last element of the tree.",
                e.getMessage());

        e = null;
        try {
            it.remove();
        }
        catch (final Exception ex) {
            e = ex;
        }
        Assert.assertNotNull(e);
        Assert.assertTrue(e instanceof IllegalStateException);
        Assert.assertEquals(
                "Cannot remove element without calling next() immediately "
                + "before.", e.getMessage());

        Assert.assertEquals("[]", this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));
        Assert.assertNull(it.getTreeNode());

        Assert.assertTrue(this.stringTree.add("F"));
        Assert.assertEquals("[F]", this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));
        it = this.stringTree.postorderIterator();
        Assert.assertEquals("F", it.next());
        Assert.assertEquals(new TreeNode<>("F"), it.getTreeNode());

        this.postorderRemove(this.stringTree, "F");
        Assert.assertTrue(this.stringTree.isEmpty());
        Assert.assertFalse(this.stringTree.contains("F"));
        Assert.assertEquals("[]", this.toPostorderString(this.stringTree));


        Assert.assertTrue(this.stringTree.add("F"));
        Assert.assertEquals("[F]", this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.add("B"));
        Assert.assertEquals("[B, F]", this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("F", "O"));
        Assert.assertEquals("[B, O, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("F", "G"));
        Assert.assertEquals("[B, O, G, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("B", "A"));
        Assert.assertEquals("[A, B, O, G, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("B", "D"));
        Assert.assertEquals("[A, D, B, O, G, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("D", "C"));
        Assert.assertEquals("[A, C, D, B, O, G, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("D", "E"));
        Assert.assertEquals("[A, C, E, D, B, O, G, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("G", "I"));
        Assert.assertEquals("[A, C, E, D, B, O, I, G, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("A", "M"));
        Assert.assertEquals("[M, A, C, E, D, B, O, I, G, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("A", "N"));
        Assert.assertEquals("[M, N, A, C, E, D, B, O, I, G, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("M", "Q"));
        Assert.assertEquals("[Q, M, N, A, C, E, D, B, O, I, G, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));

        Assert.assertTrue(this.stringTree.addChild("I", "H"));
        Assert.assertEquals("[Q, M, N, A, C, E, D, B, O, H, I, G, F]",
                this.toPostorderString(this.stringTree));
        System.out.println(this.toPostorderString(this.stringTree));


        // System.out.println("Removing O:");
        this.postorderRemove(this.stringTree, "O");
        Assert.assertEquals(12, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("O"));
        Assert.assertEquals("[Q, M, N, A, C, E, D, B, H, I, G, F]",
                this.toPostorderString(this.stringTree));

        // System.out.println("\n\nRemoving A:");
        this.postorderRemove(this.stringTree, "A");
        Assert.assertEquals(11, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("A"));
        Assert.assertEquals("[Q, M, N, C, E, D, B, H, I, G, F]",
                this.toPostorderString(this.stringTree));

        // System.out.println("\n\nRemoving G:");
        this.postorderRemove(this.stringTree, "G");
        Assert.assertEquals(10, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("G"));
        Assert.assertEquals("[Q, M, N, C, E, D, B, H, I, F]",
                this.toPostorderString(this.stringTree));

        // System.out.println("Removing F:");
        this.postorderRemove(this.stringTree, "F");
        Assert.assertEquals(9, this.stringTree.size());
        Assert.assertFalse(this.stringTree.contains("F"));
        Assert.assertEquals("[Q, M, N, C, E, D, B, H, I]",
                this.toPostorderString(this.stringTree));


        System.out.println("PostorderIterator: passed!");
    }

    /**
     * Test method for {@link java.util.AbstractCollection#toString()}.
     */
    @Test
    public void testToString() {

        Assert.assertEquals("[]", this.stringTree.toString());

        this.stringTree.add("F");
        this.stringTree.addChild("F", "B");
        this.stringTree.addChild("F", "O");
        this.stringTree.addChild("F", "G");
        this.stringTree.addChild("B", "A");
        this.stringTree.addChild("B", "D");
        this.stringTree.addChild("D", "C");
        this.stringTree.addChild("D", "E");
        this.stringTree.addChild("G", "I");
        this.stringTree.addChild("I", "H");

        Assert.assertEquals("[F, B, A, D, C, E, O, G, I, H]",
                this.stringTree.toString());


        System.out.println("ToString: passed!");
    }

    /**
     * Test method for
     * {@link java.util.AbstractCollection#containsAll(java.util.Collection)}.
     */
    @Test
    public void testInheritedMethods() {

        this.stringTree = new Tree<>(this.stringList);
        Assert.assertTrue(this.stringTree.containsAll(this.stringList));


        System.out.println("Constructor(Collection): passed!");
    }


    /**
     * Test method for
     * {@link java.util.AbstractCollection#addAll(java.util.Collection)},
     * {@link java.util.AbstractCollection#containsAll(java.util.Collection)},
     * {@link java.util.AbstractCollection#retainAll(java.util.Collection)},
     * {@link java.util.AbstractCollection#removeAll(java.util.Collection)}.
     */
    @Test
    public void testAddContainsRemoveAll() {

        Assert.assertEquals(0, this.stringTree.size());

        Assert.assertTrue(this.stringTree.addAll(this.stringList));
        Assert.assertFalse(this.stringTree.addAll(this.stringList));

        for (final String string : this.stringList) {
            Assert.assertTrue(this.stringTree.contains(string));
        }
        Assert.assertTrue(this.stringTree.containsAll(this.stringList));
        Assert.assertTrue(this.stringTree.add("Z"));
        Assert.assertTrue(this.stringTree.containsAll(this.stringList));

        Assert.assertTrue(this.stringTree.retainAll(this.stringList));
        Assert.assertFalse(this.stringTree.retainAll(this.stringList));
        Assert.assertFalse(this.stringTree.contains("Z"));

        Assert.assertTrue(this.stringTree.removeAll(this.stringList));
        Assert.assertFalse(this.stringTree.removeAll(this.stringList));
        Assert.assertTrue(this.stringTree.isEmpty());


        System.out.println("AddContainsRetainRemoveAll: passed!");
    }

    private String toPostorderString(final Tree<?> tree) {

        final StringBuilder returnValue = new StringBuilder();

        returnValue.append("[");

        final PostorderTreeIterator<?> it = tree.postorderIterator();

        int i = 0;
        while (it.hasNext()) {
            if (i > 0) {
                returnValue.append(", ");
            }
            returnValue.append(it.next()
                                 .toString());
            i++;
        }

        returnValue.append("]");

        return returnValue.toString();
    }

    private void postorderRemove(final Tree<?> tree, final Object toRemove) {

        final PostorderTreeIterator<?> it = tree.postorderIterator();
        System.out.print("[");
        boolean start = true;
        while (it.hasNext()) {
            if (start) {
                start = false;
            }
            else {
                System.out.print(", ");
            }
            final Object current = it.next();
            if (current.equals(toRemove)) {

                System.out.print("*");
                it.remove();
            }
            else {

                System.out.print(current);
            }
        }
        System.out.println("]");
    }

    private void preorderRemove(final Tree<?> tree, final Object toRemove) {

        final PreorderTreeIterator<?> it = tree.preorderIterator();
        boolean start = true;
        while (it.hasNext()) {
            if (start) {
                start = false;
            }
            else {
                System.out.print(", ");
            }
            final Object current = it.next();
            if (current.equals(toRemove)) {

                System.out.print("*");
                it.remove();
            }
            else {

                System.out.print(current);
            }
        }
        System.out.println("");
        // System.out.println("  Continued after remove? " +
        // continuedAfterRemove);
    }


}
