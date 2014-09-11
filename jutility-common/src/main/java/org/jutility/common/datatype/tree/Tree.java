package org.jutility.common.datatype.tree;


import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * The {@link Tree} class provides an implementation of a tree with an arbitrary
 * number of children per node. It adheres to the {@link Collection} interface.
 * <p/>
 * Thread safety: Fully thread safe through locking around critical sections.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * @param <E>
 *            the type of the tree.
 */
public class Tree<E>
        extends AbstractCollection<E>
        implements Collection<E> {


    private TreeNode<E> root;
    private final Lock  rootLock;



    /**
     * Returns the root {@link TreeNode node}.
     * 
     * @return the root {@link TreeNode node}.
     */
    protected TreeNode<E> getRoot() {

        TreeNode<E> root = null;
        this.rootLock.lock();
        root = this.root;
        this.rootLock.unlock();
        return root;
    }

    /**
     * Sets the root {@link TreeNode node}.
     * 
     * @param value
     *            the root {@link TreeNode node}.
     */
    protected void setRoot(TreeNode<E> value) {

        this.rootLock.lock();
        this.root = value;
        this.rootLock.unlock();
    }


    /**
     * Creates a new instance of the {@link Tree} class.
     */
    public Tree() {

        super();

        this.root = null;
        this.rootLock = new ReentrantLock();
    }

    /**
     * Creates a new instance of the {@link Tree} class and adds all elements of
     * the provided {@link Collection}.
     * 
     * @param collection
     *            the elements to add.
     */
    public Tree(Collection<? extends E> collection) {

        this();

        this.addAll(collection);
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.util.AbstractCollection#clear()
     */
    @Override
    public void clear() {

        this.rootLock.lock();
        this.root = null;
        this.rootLock.unlock();
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.util.AbstractCollection#isEmpty()
     */
    @Override
    public boolean isEmpty() {

        boolean empty = false;
        this.rootLock.lock();
        empty = this.root == null;
        this.rootLock.unlock();
        return empty;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.util.AbstractCollection#size()
     */
    @Override
    public int size() {

        int i = 0;
        for (Iterator<E> iterator = this.iterator(); iterator.hasNext();) {
            iterator.next();
            i++;
        }
        return i;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.util.AbstractCollection#iterator()
     */
    @Override
    public Iterator<E> iterator() {

        return this.preorderIterator();
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.util.AbstractCollection#add(java.lang.Object)
     */
    @Override
    public boolean add(E element) {

        if (this.contains(element)) {
            return false;
        }
        else {

            if (this.isEmpty()) {

                this.setRoot(new TreeNode<E>(element));
            }
            else {

                this.root.addChild(element);
            }

            return true;
        }
    }



    /**
     * Adds the provided child element as a child of the provided parent
     * element.
     * 
     * @param parent
     *            the parent element.
     * @param child
     *            the child element.
     * @return <code>true</code>, if the child was successfully inserted (i.e.,
     *         the parent element was found); <code>false</code> otherwise.
     */
    public boolean addChild(E parent, E child) {

        PreorderTreeIterator<E> it = new PreorderTreeIterator<E>(this);

        boolean inserted = false;

        while (it.hasNext()) {
            if (it.next().equals(parent)) {
                it.getTreeNode().addChild(child);
                inserted = true;
                break;
            }
        }

        return inserted;
    }

    /**
     * Returns a {@link PreorderTreeIterator pre-order iterator} over the
     * {@link Tree}
     * 
     * @return a {@link PreorderTreeIterator pre-order iterator} over the
     *         {@link Tree}
     */
    public PreorderTreeIterator<E> preorderIterator() {

        return new PreorderTreeIterator<E>(this);
    }

    /**
     * Returns a {@link PostorderTreeIterator post-order iterator} over the
     * {@link Tree}
     * 
     * @return a {@link PostorderTreeIterator post-order iterator} over the
     *         {@link Tree}
     */
    public PostorderTreeIterator<E> postorderIterator() {

        return new PostorderTreeIterator<E>(this);
    }


    /**
     * Determines whether the {@link Tree trees} are permutations of each other
     * (i.e., have the same content but potentially different order of
     * children).
     * 
     * @param lhs
     *            the left-hand side tree.
     * @param rhs
     *            the right-hand side tree.
     * @return <code>true</code>, if the trees are permutations of each other;
     *         <code>false</code> otherwise.
     */
    public static boolean isPermutationOf(Tree<?> lhs, Tree<?> rhs) {


        boolean result = false;
        int lhsSize = lhs.size();
        int rhsSize = rhs.size();
        boolean sameSize = lhsSize == rhsSize;


        if (sameSize) {

            PreorderTreeIterator<?> lhsIt = lhs.preorderIterator();

            boolean sameNode = false;
            boolean foundINode = false;

            while (lhsIt.hasNext()) {
                Object lhsElement = lhsIt.next();
                TreeNode<?> lhsTreeNode = lhsIt.getTreeNode();

                PreorderTreeIterator<?> rhsIt = rhs.preorderIterator();
                while (rhsIt.hasNext()) {

                    Object rhsElement = rhsIt.next();
                    TreeNode<?> rhsTreeNode = rhsIt.getTreeNode();

                    if (lhsElement.equals(rhsElement)) {
                        foundINode = true;
                        sameNode = Tree.sameNodeContent(lhsTreeNode,
                                rhsTreeNode);
                        break;
                    }

                }
                if (!foundINode) {
                    break;
                }
                else {
                    if (!sameNode) {
                        break;
                    }
                }
            }
            result = foundINode && sameNode;


        }

        return result;
    }



    /**
     * Determines whether or not the two {@link TreeNode nodes} have the same
     * content (potentially in different order).
     * 
     * @param lhs
     *            the left-hand side {@link TreeNode node}.
     * @param rhs
     *            the right-hand side {@link TreeNode node}.
     * @return <code>true</code>, if the {@link TreeNode nodes} have the same
     *         content; <code>false</code> otherwise.
     */
    private static boolean sameNodeContent(TreeNode<?> lhs, TreeNode<?> rhs) {

        int lhsChildSize = lhs.getChildren().size();
        int rhsChildSize = rhs.getChildren().size();

        boolean same = ((lhs.getElement().equals(rhs.getElement())) && (lhsChildSize == rhsChildSize));


        if (same) {

            for (TreeNode<?> lhsChild : lhs.getChildren()) {
                if (!rhs.getChildren().contains(lhsChild)) {
                    same = false;
                    break;
                }
            }
        }

        return same;
    }

}
