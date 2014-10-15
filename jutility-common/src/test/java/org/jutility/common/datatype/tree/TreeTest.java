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


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.jutility.common.datatype.tree.PostorderTreeIterator;
import org.jutility.common.datatype.tree.PreorderTreeIterator;
import org.jutility.common.datatype.tree.Tree;
import org.jutility.common.datatype.tree.TreeNode;


/**
 * Test class for the Tree implementation.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class TreeTest {

    private Tree<String>      stringTree;
    private ArrayList<String> stringList;

    /**
     */
    @Before
    public void setUp() {

        stringTree = new Tree<String>();
        stringList = new ArrayList<String>();

        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        stringList.add("D");
        stringList.add("E");
        stringList.add("F");
        stringList.add("G");
        stringList.add("H");
        stringList.add("I");
        stringList.add("O");

    }


    /**
     * Test method for {@link org.jutility.common.datatype.tree.Tree#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {

        assertTrue(stringTree.isEmpty());
        stringTree.add("F");
        assertFalse(stringTree.isEmpty());


        System.out.println("IsEmpty: passed!");
    }

    /**
     * Test method for {@link org.jutility.common.datatype.tree.Tree#clear()}.
     */
    @Test
    public void testClear() {

        stringTree.add("F");
        stringTree.addChild("F", "B");
        stringTree.addChild("F", "O");
        stringTree.addChild("F", "G");
        stringTree.addChild("B", "A");
        stringTree.addChild("B", "D");
        stringTree.addChild("D", "C");
        stringTree.addChild("D", "E");
        stringTree.addChild("G", "I");
        stringTree.addChild("I", "H");

        assertEquals(10, stringTree.size());
        stringTree.clear();
        assertEquals(0, stringTree.size());
        assertTrue(stringTree.isEmpty());


        System.out.println("Clear: passed!");
    }


    /**
     * Test method for {@link org.jutility.common.datatype.tree.Tree#iterator()}.
     */
    @Test
    public void testIterator() {

        PreorderTreeIterator<String> it = stringTree.preorderIterator();

        Exception e = null;
        try {
            it.next();
        }
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        assertTrue(e instanceof NoSuchElementException);
        assertEquals("Trying to iterate past the last element of the tree.",
                e.getMessage());

        e = null;
        try {
            it.remove();
        }
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        assertTrue(e instanceof IllegalStateException);
        assertEquals(
                "Cannot remove element without calling next() immediately before.",
                e.getMessage());

        System.out.println("Iterator: passed!");
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.Tree#add(java.lang.Object)},
     * {@link org.jutility.common.datatype.tree.Tree#addChild(java.lang.Object, java.lang.Object)}
     * ,{@link java.util.AbstractCollection#contains(java.lang.Object)},
     * {@link org.jutility.common.datatype.tree.Tree#remove(java.lang.Object)}, and
     * {@link org.jutility.common.datatype.tree.Tree#size()}.
     */
    @Test
    public void testAddRemoveContainsSizeE() {

        assertEquals(0, stringTree.size());

        assertTrue(stringTree.add("F"));
        assertFalse(stringTree.add("F"));
        assertEquals(1, stringTree.size());
        assertTrue(stringTree.contains("F"));


        assertTrue(stringTree.remove("F"));
        assertEquals(0, stringTree.size());
        assertFalse(stringTree.remove("F"));
        assertFalse(stringTree.contains("F"));


        assertTrue(stringTree.add("F"));
        assertEquals(1, stringTree.size());
        assertTrue(stringTree.contains("F"));


        assertTrue(stringTree.add("B"));
        assertEquals(2, stringTree.size());
        assertTrue(stringTree.contains("B"));

        assertTrue(stringTree.addChild("F", "O"));
        assertEquals(3, stringTree.size());
        assertTrue(stringTree.contains("O"));

        assertTrue(stringTree.addChild("F", "G"));
        assertEquals(4, stringTree.size());
        assertTrue(stringTree.contains("G"));

        assertTrue(stringTree.addChild("B", "A"));
        assertEquals(5, stringTree.size());
        assertTrue(stringTree.contains("A"));

        assertTrue(stringTree.addChild("B", "D"));
        assertEquals(6, stringTree.size());
        assertTrue(stringTree.contains("D"));

        assertTrue(stringTree.addChild("D", "C"));
        assertEquals(7, stringTree.size());
        assertTrue(stringTree.contains("C"));

        assertTrue(stringTree.addChild("D", "E"));
        assertEquals(8, stringTree.size());
        assertTrue(stringTree.contains("E"));

        assertTrue(stringTree.addChild("G", "I"));
        assertEquals(9, stringTree.size());
        assertTrue(stringTree.contains("I"));

        assertTrue(stringTree.addChild("A", "M"));
        assertEquals(10, stringTree.size());
        assertTrue(stringTree.contains("M"));

        assertTrue(stringTree.addChild("A", "N"));
        assertEquals(11, stringTree.size());
        assertTrue(stringTree.contains("N"));

        assertTrue(stringTree.addChild("M", "Q"));
        assertEquals(12, stringTree.size());
        assertTrue(stringTree.contains("Q"));

        assertTrue(stringTree.addChild("I", "H"));
        assertEquals(13, stringTree.size());
        assertTrue(stringTree.contains("H"));

        assertFalse(stringTree.addChild("Z", "Z"));
        assertEquals(13, stringTree.size());
        assertFalse(stringTree.contains("Z"));

        assertTrue(stringTree.remove("O"));
        assertEquals(12, stringTree.size());
        assertFalse(stringTree.contains("O"));

        assertTrue(stringTree.remove("A"));
        assertEquals(11, stringTree.size());
        assertFalse(stringTree.contains("A"));

        assertTrue(stringTree.remove("G"));
        assertEquals(10, stringTree.size());
        assertFalse(stringTree.contains("G"));

        assertTrue(stringTree.remove("F"));
        assertEquals(9, stringTree.size());
        assertFalse(stringTree.contains("F"));

        System.out.println("AddRemoveContainsSize: passed!");
    }


    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.Tree#postorderIterator()}.
     */
    @Test
    public void testPreorderIterator() {

        PreorderTreeIterator<String> it = stringTree.preorderIterator();

        Exception e = null;
        try {
            it.next();
        }
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        assertTrue(e instanceof NoSuchElementException);
        assertEquals("Trying to iterate past the last element of the tree.",
                e.getMessage());

        e = null;
        try {
            it.remove();
        }
        catch (Exception ex) {
            e = ex;
        }

        assertNotNull(e);
        assertTrue(e instanceof IllegalStateException);
        assertEquals(
                "Cannot remove element without calling next() immediately before.",
                e.getMessage());


        assertEquals("[]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.add("F"));
        assertEquals("[F]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.add("B"));
        assertEquals("[F, B]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("F", "O"));
        assertEquals("[F, B, O]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("F", "G"));
        assertEquals("[F, B, O, G]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("B", "A"));
        assertEquals("[F, B, A, O, G]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("B", "D"));
        assertEquals("[F, B, A, D, O, G]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("D", "C"));
        assertEquals("[F, B, A, D, C, O, G]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("D", "E"));
        assertEquals("[F, B, A, D, C, E, O, G]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("G", "I"));
        assertEquals("[F, B, A, D, C, E, O, G, I]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("A", "M"));
        assertEquals("[F, B, A, M, D, C, E, O, G, I]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("A", "N"));
        assertEquals("[F, B, A, M, N, D, C, E, O, G, I]", stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("M", "Q"));
        assertEquals("[F, B, A, M, Q, N, D, C, E, O, G, I]",
                stringTree.toString());
        System.out.println(stringTree);

        assertTrue(stringTree.addChild("I", "H"));
        assertEquals("[F, B, A, M, Q, N, D, C, E, O, G, I, H]",
                stringTree.toString());
        System.out.println(stringTree);


        // System.out.println("Removing O:");
        this.preorderRemove(stringTree, "O");
        assertEquals(12, stringTree.size());
        assertFalse(stringTree.contains("O"));
        assertEquals("[F, B, A, M, Q, N, D, C, E, G, I, H]",
                stringTree.toString());

        // System.out.println("\nRemoving A:");
        this.preorderRemove(stringTree, "A");
        assertEquals(11, stringTree.size());
        assertFalse(stringTree.contains("A"));
        assertEquals("[F, B, M, Q, N, D, C, E, G, I, H]", stringTree.toString());

        // System.out.println("\nRemoving G:");
        this.preorderRemove(stringTree, "G");
        assertEquals(10, stringTree.size());
        assertFalse(stringTree.contains("G"));
        assertEquals("[F, B, M, Q, N, D, C, E, I, H]", stringTree.toString());

        // System.out.println("Removing F:");
        this.preorderRemove(stringTree, "F");
        assertEquals(9, stringTree.size());
        assertFalse(stringTree.contains("F"));
        assertEquals("[B, M, Q, N, D, C, E, I, H]", stringTree.toString());


        System.out.println("PreorderIterator: passed!");
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.Tree#postorderIterator()}.
     */
    @Test
    public void testPostorderIterator() {

        PostorderTreeIterator<String> it = stringTree.postorderIterator();

        Exception e = null;
        try {
            it.next();
        }
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        assertTrue(e instanceof NoSuchElementException);
        assertEquals("Trying to iterate past the last element of the tree.",
                e.getMessage());

        e = null;
        try {
            it.remove();
        }
        catch (Exception ex) {
            e = ex;
        }
        assertNotNull(e);
        assertTrue(e instanceof IllegalStateException);
        assertEquals(
                "Cannot remove element without calling next() immediately before.",
                e.getMessage());

        assertEquals("[]", this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));
        assertNull(it.getTreeNode());

        assertTrue(stringTree.add("F"));
        assertEquals("[F]", this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));
        it = stringTree.postorderIterator();
        assertEquals("F", it.next());
        assertEquals(new TreeNode<String>("F"), it.getTreeNode());

        this.postorderRemove(stringTree, "F");
        assertTrue(stringTree.isEmpty());
        assertFalse(stringTree.contains("F"));
        assertEquals("[]", this.toPostorderString(stringTree));


        assertTrue(stringTree.add("F"));
        assertEquals("[F]", this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.add("B"));
        assertEquals("[B, F]", this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("F", "O"));
        assertEquals("[B, O, F]", this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("F", "G"));
        assertEquals("[B, O, G, F]", this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("B", "A"));
        assertEquals("[A, B, O, G, F]", this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("B", "D"));
        assertEquals("[A, D, B, O, G, F]", this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("D", "C"));
        assertEquals("[A, C, D, B, O, G, F]",
                this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("D", "E"));
        assertEquals("[A, C, E, D, B, O, G, F]",
                this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("G", "I"));
        assertEquals("[A, C, E, D, B, O, I, G, F]",
                this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("A", "M"));
        assertEquals("[M, A, C, E, D, B, O, I, G, F]",
                this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("A", "N"));
        assertEquals("[M, N, A, C, E, D, B, O, I, G, F]",
                this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("M", "Q"));
        assertEquals("[Q, M, N, A, C, E, D, B, O, I, G, F]",
                this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));

        assertTrue(stringTree.addChild("I", "H"));
        assertEquals("[Q, M, N, A, C, E, D, B, O, H, I, G, F]",
                this.toPostorderString(stringTree));
        System.out.println(this.toPostorderString(stringTree));


        // System.out.println("Removing O:");
        this.postorderRemove(stringTree, "O");
        assertEquals(12, stringTree.size());
        assertFalse(stringTree.contains("O"));
        assertEquals("[Q, M, N, A, C, E, D, B, H, I, G, F]",
                this.toPostorderString(stringTree));

        // System.out.println("\n\nRemoving A:");
        this.postorderRemove(stringTree, "A");
        assertEquals(11, stringTree.size());
        assertFalse(stringTree.contains("A"));
        assertEquals("[Q, M, N, C, E, D, B, H, I, G, F]",
                this.toPostorderString(stringTree));

        // System.out.println("\n\nRemoving G:");
        this.postorderRemove(stringTree, "G");
        assertEquals(10, stringTree.size());
        assertFalse(stringTree.contains("G"));
        assertEquals("[Q, M, N, C, E, D, B, H, I, F]",
                this.toPostorderString(stringTree));

        // System.out.println("Removing F:");
        this.postorderRemove(stringTree, "F");
        assertEquals(9, stringTree.size());
        assertFalse(stringTree.contains("F"));
        assertEquals("[Q, M, N, C, E, D, B, H, I]",
                this.toPostorderString(stringTree));


        System.out.println("PostorderIterator: passed!");
    }

    /**
     * Test method for {@link java.util.AbstractCollection#toString()}.
     */
    @Test
    public void testToString() {

        assertEquals("[]", stringTree.toString());

        stringTree.add("F");
        stringTree.addChild("F", "B");
        stringTree.addChild("F", "O");
        stringTree.addChild("F", "G");
        stringTree.addChild("B", "A");
        stringTree.addChild("B", "D");
        stringTree.addChild("D", "C");
        stringTree.addChild("D", "E");
        stringTree.addChild("G", "I");
        stringTree.addChild("I", "H");

        assertEquals("[F, B, A, D, C, E, O, G, I, H]", stringTree.toString());


        System.out.println("ToString: passed!");
    }

    /**
     * Test method for
     * {@link java.util.AbstractCollection#containsAll(java.util.Collection)}.
     */
    @Test
    public void testInheritedMethods() {

        stringTree = new Tree<String>(stringList);
        assertTrue(stringList.containsAll(stringList));


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

        assertEquals(0, stringTree.size());

        assertTrue(stringTree.addAll(stringList));
        assertFalse(stringTree.addAll(stringList));

        for (String string : stringList) {
            assertTrue(stringTree.contains(string));
        }
        assertTrue(stringList.containsAll(stringList));
        assertTrue(stringTree.add("Z"));
        assertTrue(stringTree.containsAll(stringList));

        assertTrue(stringTree.retainAll(stringList));
        assertFalse(stringTree.retainAll(stringList));
        assertFalse(stringTree.contains("Z"));

        assertTrue(stringTree.removeAll(stringList));
        assertFalse(stringTree.removeAll(stringList));
        assertTrue(stringTree.isEmpty());


        System.out.println("AddContainsRetainRemoveAll: passed!");
    }

    private String toPostorderString(Tree<?> tree) {

        StringBuilder returnValue = new StringBuilder();

        returnValue.append("[");

        PostorderTreeIterator<?> it = tree.postorderIterator();

        int i = 0;
        while (it.hasNext()) {
            if (i > 0) {
                returnValue.append(", ");
            }
            returnValue.append(it.next().toString());
            i++;
        }

        returnValue.append("]");

        return returnValue.toString();
    }

    private void postorderRemove(Tree<?> tree, Object toRemove) {

        PostorderTreeIterator<?> it = tree.postorderIterator();
        System.out.print("[");
        boolean start = true;
        while (it.hasNext()) {
            if (start) {
                start = false;
            }
            else {
                System.out.print(", ");
            }
            Object current = it.next();
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

    private void preorderRemove(Tree<?> tree, Object toRemove) {

        PreorderTreeIterator<?> it = tree.preorderIterator();
        boolean start = true;
        while (it.hasNext()) {
            if (start) {
                start = false;
            }
            else {
                System.out.print(", ");
            }
            Object current = it.next();
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
