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


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * Test class for TreeNode.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
@SuppressWarnings({ "ConstantConditions", "EqualsBetweenInconvertibleTypes",
                    "ObjectEqualsNull" })
public class TreeNodeTest {


    private TreeNode<String> testNode;
    private TreeNode<String> parent;
    private TreeNode<String> child;

    /**
     * Set Up.
     */
    @Before
    public void setUp() {

        this.testNode = new TreeNode<>("A");
        this.parent = new TreeNode<>("B");
        this.child = new TreeNode<>("C", this.parent);
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#TreeNode(Object)}
     * and {@link org.jutility.common.datatype.tree.TreeNode#TreeNode(Object,
     * TreeNode)} .
     */
    @SuppressWarnings("unused")
    @Test
    public void testConstructor() {

        Exception ex = null;
        try {
            //noinspection ConstantConditions
            new TreeNode<String>(null);
        }
        catch (final Exception e) {
            ex = e;
        }

        Assert.assertNotNull(ex);
        Assert.assertTrue(ex instanceof IllegalArgumentException);
        Assert.assertEquals(
                "Cannot create a tree node without a valid element!",
                ex.getMessage());
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#getElement()}.
     */
    @Test
    public void testGetElement() {

        Assert.assertEquals("A", this.testNode.getElement());
        Assert.assertEquals("B", this.parent.getElement());
        Assert.assertEquals("C", this.child.getElement());
    }


    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#getParent()}.
     */
    @Test
    public void testGetParent() {

        Assert.assertNull(this.testNode.getParent());
        Assert.assertNull(this.parent.getParent());
        Assert.assertEquals(this.parent, this.child.getParent());
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#addChild(org.jutility.common.datatype.tree.TreeNode)}
     * z
     * {@link org.jutility.common.datatype.tree.TreeNode#removeChild(org.jutility.common.datatype.tree.TreeNode)}
     * z
     * {@link org.jutility.common.datatype.tree.TreeNode#addChild(java.lang.Object)}
     * and
     * {@link org.jutility.common.datatype.tree.TreeNode#removeChild(java.lang.Object)}
     * .
     */
    @Test
    public void testAddRemoveChildTreeNode() {

        Exception ex = null;
        final TreeNode<String> nullNode = null;

        try {
            this.parent.addChild(nullNode);
        }
        catch (final Exception e) {
            ex = e;
        }

        Assert.assertNotNull(ex);
        Assert.assertTrue(ex instanceof IllegalArgumentException);
        Assert.assertEquals("Cannot contain null elements!", ex.getMessage());


        this.parent.addChild(this.testNode);
        List<TreeNode<String>> parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(2, parentNodeChildren.size());
        Assert.assertEquals(this.child, parentNodeChildren.get(0));
        Assert.assertEquals(this.testNode, parentNodeChildren.get(1));

        Assert.assertEquals(this.parent, this.child.getParent());
        Assert.assertEquals(this.parent, this.testNode.getParent());


        ex = null;

        try {
            this.parent.removeChild(nullNode);
        }
        catch (final Exception e) {
            ex = e;
        }

        Assert.assertNotNull(ex);
        Assert.assertTrue(ex instanceof IllegalArgumentException);
        Assert.assertEquals("Cannot contain null elements!", ex.getMessage());

        this.parent.removeChild(this.testNode);

        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(1, parentNodeChildren.size());
        Assert.assertEquals(this.child, parentNodeChildren.get(0));


        Assert.assertEquals(this.parent, this.child.getParent());
        Assert.assertNull(this.testNode.getParent());


        this.parent.removeChild(this.testNode);

        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(1, parentNodeChildren.size());
        Assert.assertEquals(this.child, parentNodeChildren.get(0));
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#addChild(org.jutility.common.datatype.tree.TreeNode)}
     * z
     * {@link org.jutility.common.datatype.tree.TreeNode#removeChild(org.jutility.common.datatype.tree.TreeNode)}
     * z
     * {@link org.jutility.common.datatype.tree.TreeNode#addChild(java.lang.Object)}
     * and
     * {@link org.jutility.common.datatype.tree.TreeNode#removeChild(java.lang.Object)}
     * .
     */
    @Test
    public void testAddRemoveChildE() {


        Exception ex = null;
        final String nullNode = null;

        try {
            this.parent.addChild(nullNode);
        }
        catch (final Exception e) {
            ex = e;
        }

        Assert.assertNotNull(ex);
        Assert.assertTrue(ex instanceof IllegalArgumentException);
        Assert.assertEquals(
                "Cannot create a tree node without a valid element!",
                ex.getMessage());


        this.parent.addChild("E");
        List<TreeNode<String>> parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(2, parentNodeChildren.size());
        Assert.assertEquals(this.child, parentNodeChildren.get(0));

        final TreeNode<String> actualNode = parentNodeChildren.get(1);
        Assert.assertEquals(new TreeNode<>("E"), actualNode);

        Assert.assertEquals(this.parent, this.child.getParent());
        Assert.assertEquals(this.parent, actualNode.getParent());


        ex = null;

        try {
            this.parent.removeChild(nullNode);
        }
        catch (final Exception e) {
            ex = e;
        }

        Assert.assertNotNull(ex);
        Assert.assertTrue(ex instanceof IllegalArgumentException);
        Assert.assertEquals(
                "Cannot create a tree node without a valid element!",
                ex.getMessage());

        this.parent.removeChild("E");

        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(1, parentNodeChildren.size());
        Assert.assertEquals(this.child, parentNodeChildren.get(0));

        Assert.assertEquals(this.parent, this.child.getParent());
        Assert.assertNull(actualNode.getParent());


        this.parent.removeChild("E");

        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(1, parentNodeChildren.size());
        Assert.assertEquals(this.child, parentNodeChildren.get(0));
    }

    /**
     * Test method for {@link org.jutility.common.datatype.tree
     * .TreeNode#replaceChild(org.jutility.common.datatype.tree.TreeNode,
     * org.jutility.common.datatype.tree.TreeNode)} .
     */
    @Test
    public void testReplaceChildTreeNode() {

        Exception ex = null;
        final TreeNode<String> nullNode = null;

        try {
            this.parent.replaceChild(nullNode, nullNode);
        }
        catch (final Exception e) {
            ex = e;
        }

        Assert.assertNotNull(ex);
        Assert.assertTrue(ex instanceof IllegalArgumentException);
        Assert.assertEquals("Cannot contain null elements!", ex.getMessage());

        ex = null;

        try {
            this.parent.replaceChild(this.child, nullNode);
        }
        catch (final Exception e) {
            ex = e;
        }

        Assert.assertNotNull(ex);
        Assert.assertTrue(ex instanceof IllegalArgumentException);
        Assert.assertEquals("Cannot contain null elements!", ex.getMessage());

        ex = null;

        try {
            this.parent.replaceChild(this.testNode, this.child);
        }
        catch (final Exception e) {
            ex = e;
        }

        Assert.assertNotNull(ex);
        Assert.assertTrue(ex instanceof NoSuchElementException);
        Assert.assertEquals(
                "The provided child to be replaced could not be found!",
                ex.getMessage());


        this.parent.replaceChild(this.child, this.testNode);

        List<TreeNode<String>> parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(1, parentNodeChildren.size());
        Assert.assertEquals(this.testNode, parentNodeChildren.get(0));

        Assert.assertNull(this.child.getParent());
        Assert.assertEquals(this.parent, this.testNode.getParent());

        this.parent.replaceChild(this.testNode, this.testNode);
        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(1, parentNodeChildren.size());
        Assert.assertEquals(this.testNode, parentNodeChildren.get(0));

        Assert.assertNull(this.child.getParent());
        Assert.assertEquals(this.parent, this.testNode.getParent());

        this.parent.addChild(this.child);
        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(2, parentNodeChildren.size());
        Assert.assertEquals(this.testNode, parentNodeChildren.get(0));
        Assert.assertEquals(this.child, parentNodeChildren.get(1));

        Assert.assertEquals(this.parent, this.child.getParent());
        Assert.assertEquals(this.parent, this.testNode.getParent());

        this.parent.replaceChild(this.testNode, this.child);
        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(1, parentNodeChildren.size());
        Assert.assertEquals(this.child, parentNodeChildren.get(0));

        Assert.assertNull(this.testNode.getParent());
        Assert.assertEquals(this.parent, this.child.getParent());

    }

    /**
     * Test method for {@link org.jutility.common.datatype.tree
     * .TreeNode#replaceChild(java.lang.Object,
     * java.lang.Object)} .
     */
    @Test
    public void testReplaceChildE() {

        Exception ex = null;

        try {
            this.parent.replaceChild("E", "C");
        }
        catch (final Exception e) {
            ex = e;
        }

        Assert.assertNotNull(ex);
        Assert.assertTrue(ex instanceof NoSuchElementException);
        Assert.assertEquals(
                "The provided child to be replaced could not be found!",
                ex.getMessage());


        this.parent.replaceChild("C", "E");

        List<TreeNode<String>> parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(1, parentNodeChildren.size());
        TreeNode<String> actualNode = parentNodeChildren.get(0);
        Assert.assertEquals(new TreeNode<>("E"), actualNode);

        Assert.assertNull(this.child.getParent());
        Assert.assertEquals(this.parent, actualNode.getParent());

        this.parent.replaceChild("E", "E");
        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(1, parentNodeChildren.size());
        TreeNode<String> newActualNode = parentNodeChildren.get(0);
        Assert.assertEquals(actualNode, newActualNode);
        Assert.assertNull(actualNode.getParent());
        Assert.assertEquals(this.parent, newActualNode.getParent());


        this.parent.addChild("C");
        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(2, parentNodeChildren.size());
        actualNode = parentNodeChildren.get(0);
        newActualNode = parentNodeChildren.get(1);

        Assert.assertEquals(new TreeNode<>("E"), actualNode);
        Assert.assertEquals(new TreeNode<>("C"), newActualNode);

        Assert.assertEquals(this.parent, actualNode.getParent());
        Assert.assertEquals(this.parent, newActualNode.getParent());

        this.parent.replaceChild("E", "C");

        Assert.assertNull(actualNode.getParent());
        Assert.assertNull(newActualNode.getParent());

        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(1, parentNodeChildren.size());
        actualNode = parentNodeChildren.get(0);
        Assert.assertEquals(new TreeNode<>("C"), actualNode);

    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#clearChildren()}.
     */
    @Test
    public void testClearChildren() {

        this.parent.addChild(this.testNode);
        List<TreeNode<String>> parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(2, parentNodeChildren.size());
        Assert.assertEquals(this.child, parentNodeChildren.get(0));
        Assert.assertEquals(this.testNode, parentNodeChildren.get(1));
        this.parent.clearChildren();
        parentNodeChildren = this.parent.getChildren();
        Assert.assertEquals(0, parentNodeChildren.size());
        Assert.assertNull(this.child.getParent());
        Assert.assertNull(this.testNode.getParent());
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#equals(java.lang.Object)}
     * .
     */
    @Test
    public void testEqualsObject() {

        Assert.assertEquals(new TreeNode<>("A"), this.testNode);
        Assert.assertFalse(this.testNode.equals(null));
        Assert.assertFalse(this.testNode.equals("A"));
        Assert.assertFalse(this.testNode.equals(this.parent));
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#toString()}.
     */
    @Test
    public void testToString() {

        Assert.assertEquals("A", this.testNode.toString());
        Assert.assertEquals("B", this.parent.toString());
        Assert.assertEquals("C", this.child.toString());
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#hashCode()}.
     */
    @Test
    public void testHashCode() {

        Assert.assertEquals("A".hashCode(), this.testNode.hashCode());
        Assert.assertEquals("B".hashCode(), this.parent.hashCode());
        Assert.assertEquals("C".hashCode(), this.child.hashCode());
    }
}
