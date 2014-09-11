package org.jutility.common.datatype.tree;


import static org.junit.Assert.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.jutility.common.datatype.tree.TreeNode;


/**
 * Test class for TreeNode.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class TreeNodeTest {


    private TreeNode<String> testNode;
    private TreeNode<String> parent;
    private TreeNode<String> child;

    /**
     */
    @Before
    public void setUp(){

        this.testNode = new TreeNode<String>("A");
        this.parent = new TreeNode<String>("B");
        this.child = new TreeNode<String>("C", parent);
    }

    /**
     * Test method for {@link org.jutility.common.datatype.tree.TreeNode#TreeNode(Object)} and
     * {@link org.jutility.common.datatype.tree.TreeNode#TreeNode(Object, TreeNode)}.
     */
    @SuppressWarnings("unused")
    @Test
    public void testConstructor() {

        Exception ex = null;
        try {
            new TreeNode<String>(null);
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof IllegalArgumentException);
        assertEquals("Cannot create a tree node without a valid element!",
                ex.getMessage());
    }

    /**
     * Test method for {@link org.jutility.common.datatype.tree.TreeNode#getElement()}.
     */
    @Test
    public void testGetElement() {

        assertEquals("A", testNode.getElement());
        assertEquals("B", parent.getElement());
        assertEquals("C", child.getElement());
    }


    /**
     * Test method for {@link org.jutility.common.datatype.tree.TreeNode#getParent()}.
     */
    @Test
    public void testGetParent() {

        assertNull(testNode.getParent());
        assertNull(parent.getParent());
        assertEquals(parent, child.getParent());
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#addChild(org.jutility.common.datatype.tree.TreeNode)}z
     * {@link org.jutility.common.datatype.tree.TreeNode#removeChild(org.jutility.common.datatype.tree.TreeNode)}z
     * {@link org.jutility.common.datatype.tree.TreeNode#addChild(java.lang.Object)} and
     * {@link org.jutility.common.datatype.tree.TreeNode#removeChild(java.lang.Object)}.
     */
    @Test
    public void testAddRemoveChildTreeNode() {

        Exception ex = null;
        TreeNode<String> nullNode = null;

        try {
            parent.addChild(nullNode);
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof IllegalArgumentException);
        assertEquals("Cannot contain null elements!", ex.getMessage());


        parent.addChild(testNode);
        List<TreeNode<String>> parentNodeChildren = parent.getChildren();
        assertEquals(2, parentNodeChildren.size());
        assertEquals(child, parentNodeChildren.get(0));
        assertEquals(testNode, parentNodeChildren.get(1));

        assertEquals(parent, child.getParent());
        assertEquals(parent, testNode.getParent());


        ex = null;

        try {
            parent.removeChild(nullNode);
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof IllegalArgumentException);
        assertEquals("Cannot contain null elements!", ex.getMessage());

        parent.removeChild(testNode);

        parentNodeChildren = parent.getChildren();
        assertEquals(1, parentNodeChildren.size());
        assertEquals(child, parentNodeChildren.get(0));


        assertEquals(parent, child.getParent());
        assertNull(testNode.getParent());


        parent.removeChild(testNode);

        parentNodeChildren = parent.getChildren();
        assertEquals(1, parentNodeChildren.size());
        assertEquals(child, parentNodeChildren.get(0));
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#addChild(org.jutility.common.datatype.tree.TreeNode)}z
     * {@link org.jutility.common.datatype.tree.TreeNode#removeChild(org.jutility.common.datatype.tree.TreeNode)}z
     * {@link org.jutility.common.datatype.tree.TreeNode#addChild(java.lang.Object)} and
     * {@link org.jutility.common.datatype.tree.TreeNode#removeChild(java.lang.Object)}.
     */
    @Test
    public void testAddRemoveChildE() {


        Exception ex = null;
        String nullNode = null;

        try {
            parent.addChild(nullNode);
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof IllegalArgumentException);
        assertEquals("Cannot create a tree node without a valid element!",
                ex.getMessage());


        parent.addChild("E");
        List<TreeNode<String>> parentNodeChildren = parent.getChildren();
        assertEquals(2, parentNodeChildren.size());
        assertEquals(child, parentNodeChildren.get(0));

        TreeNode<String> actualNode = parentNodeChildren.get(1);
        assertEquals(new TreeNode<String>("E"), actualNode);

        assertEquals(parent, child.getParent());
        assertEquals(parent, actualNode.getParent());


        ex = null;

        try {
            parent.removeChild(nullNode);
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof IllegalArgumentException);
        assertEquals("Cannot create a tree node without a valid element!",
                ex.getMessage());

        parent.removeChild("E");

        parentNodeChildren = parent.getChildren();
        assertEquals(1, parentNodeChildren.size());
        assertEquals(child, parentNodeChildren.get(0));

        assertEquals(parent, child.getParent());
        assertNull(actualNode.getParent());


        parent.removeChild("E");

        parentNodeChildren = parent.getChildren();
        assertEquals(1, parentNodeChildren.size());
        assertEquals(child, parentNodeChildren.get(0));
    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#replaceChild(org.jutility.common.datatype.tree.TreeNode, org.jutility.common.datatype.tree.TreeNode)}
     * .
     */
    @Test
    public void testReplaceChildTreeNode() {

        Exception ex = null;
        TreeNode<String> nullNode = null;

        try {
            parent.replaceChild(nullNode, nullNode);
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof IllegalArgumentException);
        assertEquals("Cannot contain null elements!", ex.getMessage());

        ex = null;

        try {
            parent.replaceChild(child, nullNode);
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof IllegalArgumentException);
        assertEquals("Cannot contain null elements!", ex.getMessage());

        ex = null;

        try {
            parent.replaceChild(testNode, child);
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof NoSuchElementException);
        assertEquals("The provided child to be replaced could not be found!",
                ex.getMessage());


        parent.replaceChild(child, testNode);

        List<TreeNode<String>> parentNodeChildren = parent.getChildren();
        assertEquals(1, parentNodeChildren.size());
        assertEquals(testNode, parentNodeChildren.get(0));

        assertNull(child.getParent());
        assertEquals(parent, testNode.getParent());

        parent.replaceChild(testNode, testNode);
        parentNodeChildren = parent.getChildren();
        assertEquals(1, parentNodeChildren.size());
        assertEquals(testNode, parentNodeChildren.get(0));

        assertNull(child.getParent());
        assertEquals(parent, testNode.getParent());

        parent.addChild(child);
        parentNodeChildren = parent.getChildren();
        assertEquals(2, parentNodeChildren.size());
        assertEquals(testNode, parentNodeChildren.get(0));
        assertEquals(child, parentNodeChildren.get(1));

        assertEquals(parent, child.getParent());
        assertEquals(parent, testNode.getParent());

        parent.replaceChild(testNode, child);
        parentNodeChildren = parent.getChildren();
        assertEquals(1, parentNodeChildren.size());
        assertEquals(child, parentNodeChildren.get(0));

        assertNull(testNode.getParent());
        assertEquals(parent, child.getParent());

    }

    /**
     * Test method for
     * {@link org.jutility.common.datatype.tree.TreeNode#replaceChild(java.lang.Object, java.lang.Object)}
     * .
     */
    @Test
    public void testReplaceChildE() {

        Exception ex = null;

        try {
            parent.replaceChild("E", "C");
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(ex instanceof NoSuchElementException);
        assertEquals("The provided child to be replaced could not be found!",
                ex.getMessage());


        parent.replaceChild("C", "E");

        List<TreeNode<String>> parentNodeChildren = parent.getChildren();
        assertEquals(1, parentNodeChildren.size());
        TreeNode<String> actualNode = parentNodeChildren.get(0);
        assertEquals(new TreeNode<String>("E"), actualNode);

        assertNull(child.getParent());
        assertEquals(parent, actualNode.getParent());

        parent.replaceChild("E", "E");
        parentNodeChildren = parent.getChildren();
        assertEquals(1, parentNodeChildren.size());
        TreeNode<String> newActualNode = parentNodeChildren.get(0);
        assertEquals(actualNode, newActualNode);
        assertNull(actualNode.getParent());
        assertEquals(parent, newActualNode.getParent());


        parent.addChild("C");
        parentNodeChildren = parent.getChildren();
        assertEquals(2, parentNodeChildren.size());
        actualNode = parentNodeChildren.get(0);
        newActualNode = parentNodeChildren.get(1);

        assertEquals(new TreeNode<String>("E"), actualNode);
        assertEquals(new TreeNode<String>("C"), newActualNode);

        assertEquals(parent, actualNode.getParent());
        assertEquals(parent, newActualNode.getParent());

        parent.replaceChild("E", "C");

        assertNull(actualNode.getParent());
        assertNull(newActualNode.getParent());

        parentNodeChildren = parent.getChildren();
        assertEquals(1, parentNodeChildren.size());
        actualNode = parentNodeChildren.get(0);
        assertEquals(new TreeNode<String>("C"), actualNode);

    }

    /**
     * Test method for {@link org.jutility.common.datatype.tree.TreeNode#clearChildren()}.
     */
    @Test
    public void testClearChildren() {

        parent.addChild(testNode);
        List<TreeNode<String>> parentNodeChildren = parent.getChildren();
        assertEquals(2, parentNodeChildren.size());
        assertEquals(child, parentNodeChildren.get(0));
        assertEquals(testNode, parentNodeChildren.get(1));
        parent.clearChildren();
        parentNodeChildren = parent.getChildren();
        assertEquals(0, parentNodeChildren.size());
        assertNull(child.getParent());
        assertNull(testNode.getParent());
    }

    /**
     * Test method for {@link org.jutility.common.datatype.tree.TreeNode#equals(java.lang.Object)}
     * .
     */
    @Test
    public void testEqualsObject() {

        assertEquals(new TreeNode<String>("A"), testNode);
        assertFalse(testNode.equals(null));
        assertFalse(testNode.equals("A"));
        assertFalse(testNode.equals(parent));
    }

    /**
     * Test method for {@link org.jutility.common.datatype.tree.TreeNode#toString()}.
     */
    @Test
    public void testToString() {

        assertEquals("A", testNode.toString());
        assertEquals("B", parent.toString());
        assertEquals("C", child.toString());
    }

    /**
     * Test method for {@link org.jutility.common.datatype.tree.TreeNode#hashCode()}.
     */
    @Test
    public void testHashCode() {

        assertEquals("A".hashCode(), testNode.hashCode());
        assertEquals("B".hashCode(), parent.hashCode());
        assertEquals("C".hashCode(), child.hashCode());
    }
}
