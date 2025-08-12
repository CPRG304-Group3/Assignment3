package implementations;

import java.io.Serializable;

/**
 * BSTreeNode represents a node in a Binary Search Tree.
 * Each node contains data and references to left and right child nodes.
 * This class supports the BSTree implementation for Assignment 3.
 *
 * @param <E> the type of elements stored in this node, must extend Comparable
 * @author Your Name
 * @version 1.0
 */
public class BSTreeNode<E extends Comparable<? super E>> implements Serializable {

    /** Serial version UID for serialization compatibility */
    private static final long serialVersionUID = 1L;

    /** The data element stored in this node */
    private E data;

    /** Reference to the left child node */
    private BSTreeNode<E> left;

    /** Reference to the right child node */
    private BSTreeNode<E> right;

    /**
     * Constructs a new BSTreeNode with the specified data element.
     * Left and right children are initialized to null.
     *
     * @param data the data element to store in this node
     * @throws NullPointerException if data is null
     */
    public BSTreeNode(E data) {
        if (data == null) {
            throw new NullPointerException("Data cannot be null");
        }
        this.data = data;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructs a new BSTreeNode with the specified data and child nodes.
     *
     * @param data the data element to store in this node
     * @param left the left child node
     * @param right the right child node
     * @throws NullPointerException if data is null
     */
    public BSTreeNode(E data, BSTreeNode<E> left, BSTreeNode<E> right) {
        if (data == null) {
            throw new NullPointerException("Data cannot be null");
        }
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the data element stored in this node.
     *
     * @return the data element stored in this node
     */
    public E getData() {
        return data;
    }

    /**
     * Sets the data element stored in this node.
     *
     * @param data the new data element to store
     * @throws NullPointerException if data is null
     */
    public void setData(E data) {
        if (data == null) {
            throw new NullPointerException("Data cannot be null");
        }
        this.data = data;
    }

    /**
     * Returns the left child node.
     *
     * @return the left child node, or null if no left child exists
     */
    public BSTreeNode<E> getLeft() {
        return left;
    }

    /**
     * Sets the left child node.
     *
     * @param left the new left child node (can be null)
     */
    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }

    /**
     * Returns the right child node.
     *
     * @return the right child node, or null if no right child exists
     */
    public BSTreeNode<E> getRight() {
        return right;
    }

    /**
     * Sets the right child node.
     *
     * @param right the new right child node (can be null)
     */
    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }

    /**
     * Checks if this node has a left child.
     *
     * @return true if this node has a left child, false otherwise
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * Checks if this node has a right child.
     *
     * @return true if this node has a right child, false otherwise
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * Checks if this node is a leaf node (has no children).
     *
     * @return true if this node is a leaf, false otherwise
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Returns the number of children this node has.
     *
     * @return 0, 1, or 2 depending on the number of non-null children
     */
    public int getChildCount() {
        int count = 0;
        if (left != null) count++;
        if (right != null) count++;
        return count;
    }

    /**
     * Returns a string representation of this node's data.
     *
     * @return string representation of the node's data element
     */
    @Override
    public String toString() {
        return data.toString();
    }

    /**
     * Compares this node with another object for equality.
     * Two nodes are considered equal if their data elements are equal.
     *
     * @param obj the object to compare with this node
     * @return true if the nodes are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BSTreeNode<?> other = (BSTreeNode<?>) obj;
        return data.equals(other.data);
    }

    /**
     * Returns the hash code for this node based on its data element.
     *
     * @return hash code for this node
     */
    @Override
    public int hashCode() {
        return data.hashCode();
    }
}