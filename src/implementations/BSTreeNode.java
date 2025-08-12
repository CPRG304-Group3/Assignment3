package implementations;

import utilities.BSTreeADT;
import utilities.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

/**
 * BSTree is a Binary Search Tree implementation that maintains elements
 * in sorted order according to their natural ordering (Comparable interface).
 * This implementation supports the WordTracker application for Assignment 3.
 *
 * The tree does not allow duplicate elements and provides iterators for
 * in-order, pre-order, and post-order traversal using deep copy approach.
 *
 * @param <E> the type of elements stored in this tree, must extend Comparable
 * @author Your Name
 * @version 1.0
 */
public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {

    /** Serial version UID for serialization compatibility */
    private static final long serialVersionUID = 1L;

    /** The root node of the binary search tree */
    private BSTreeNode<E> root;

    /** The number of elements currently stored in the tree */
    private int size;

    /**
     * Constructs an empty binary search tree.
     * The root is initialized to null and size to 0.
     */
    public BSTree() {
        root = null;
        size = 0;
    }

    /**
     * Returns the root node of the binary search tree.
     *
     * @return the root node of the tree
     * @throws NullPointerException if the tree is empty and there is no root node
     */
    @Override
    public BSTreeNode<E> getRoot() throws NullPointerException {
        if (root == null) {
            throw new NullPointerException("Tree is empty - no root node exists");
        }
        return root;
    }

    /**
     * Determines the height of the tree and returns that value.
     * The height is the number of levels in the tree, with a single node
     * having height 1, and an empty tree having height 0.
     *
     * @return the height of the tree
     */
    @Override
    public int getHeight() {
        return calculateHeight(root);
    }

    /**
     * Helper method to recursively calculate the height of a subtree.
     *
     * @param node the root of the subtree
     * @return the height of the subtree rooted at node
     */
    private int calculateHeight(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = calculateHeight(node.getLeft());
        int rightHeight = calculateHeight(node.getRight());

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Returns the number of elements currently stored in the tree.
     *
     * @return the number of elements in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the tree is currently empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Removes all elements from the tree, making it empty.
     * After this operation, the tree will have size 0 and no root.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Checks if the tree contains the specified element.
     *
     * @param entry the element to search for in the tree
     * @return true if the element is found in the tree, false otherwise
     * @throws NullPointerException if the entry being passed in is null
     */
    @Override
    public boolean contains(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException("Entry cannot be null");
        }
        return search(entry) != null;
    }

    /**
     * Searches for and retrieves a node from the tree containing the specified element.
     *
     * @param entry the element to search for
     * @return the node containing the element, or null if not found
     * @throws NullPointerException if the entry being passed in is null
     */
    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException("Entry cannot be null");
        }
        return searchRecursive(root, entry);
    }

    /**
     * Helper method to recursively search for a node containing the specified element.
     *
     * @param node the current node being examined
     * @param entry the element to search for
     * @return the node containing the element, or null if not found
     */
    private BSTreeNode<E> searchRecursive(BSTreeNode<E> node, E entry) {
        if (node == null) {
            return null;
        }

        int comparison = entry.compareTo(node.getData());

        if (comparison == 0) {
            return node;
        } else if (comparison < 0) {
            return searchRecursive(node.getLeft(), entry);
        } else {
            return searchRecursive(node.getRight(), entry);
        }
    }

    /**
     * Adds a new element to the tree according to the natural ordering
     * established by the Comparable implementation. Duplicate elements
     * are not added to maintain the set property.
     *
     * @param newEntry the element to add to the tree
     * @return true if the element was added successfully, false if it already exists
     * @throws NullPointerException if the newEntry being passed in is null
     */
    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null) {
            throw new NullPointerException("New entry cannot be null");
        }

        int originalSize = size;
        root = addRecursive(root, newEntry);
        return size > originalSize;
    }

    /**
     * Helper method to recursively add an element to the tree.
     *
     * @param node the current node being examined
     * @param entry the element to add
     * @return the updated node (may be newly created)
     */
    private BSTreeNode<E> addRecursive(BSTreeNode<E> node, E entry) {
        if (node == null) {
            size++;
            return new BSTreeNode<>(entry);
        }

        int comparison = entry.compareTo(node.getData());

        if (comparison < 0) {
            node.setLeft(addRecursive(node.getLeft(), entry));
        } else if (comparison > 0) {
            node.setRight(addRecursive(node.getRight(), entry));
        }
        // If comparison == 0, element already exists, don't add duplicate

        return node;
    }

    /**
     * Removes the smallest element in the tree according to the natural ordering.
     *
     * @return the node that was removed, or null if the tree is empty
     */
    @Override
    public BSTreeNode<E> removeMin() {
        if (root == null) {
            return null;
        }

        BSTreeNode<E> minNode = findMinimum(root);
        BSTreeNode<E> removedNode = new BSTreeNode<>(minNode.getData());
        root = removeMinimum(root);
        size--;

        return removedNode;
    }

    /**
     * Helper method to find the node with the minimum element.
     *
     * @param node the root of the subtree to search
     * @return the node containing the minimum element
     */
    private BSTreeNode<E> findMinimum(BSTreeNode<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Helper method to remove the minimum node from a subtree.
     *
     * @param node the root of the subtree
     * @return the updated subtree root after removal
     */
    private BSTreeNode<E> removeMinimum(BSTreeNode<E> node) {
        if (node.getLeft() == null) {
            return node.getRight();
        }
        node.setLeft(removeMinimum(node.getLeft()));
        return node;
    }

    /**
     * Removes the largest element in the tree according to the natural ordering.
     *
     * @return the node that was removed, or null if the tree is empty
     */
    @Override
    public BSTreeNode<E> removeMax() {
        if (root == null) {
            return null;
        }

        BSTreeNode<E> maxNode = findMaximum(root);
        BSTreeNode<E> removedNode = new BSTreeNode<>(maxNode.getData());
        root = removeMaximum(root);
        size--;

        return removedNode;
    }

    /**
     * Helper method to find the node with the maximum element.
     *
     * @param node the root of the subtree to search
     * @return the node containing the maximum element
     */
    private BSTreeNode<E> findMaximum(BSTreeNode<E> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    /**
     * Helper method to remove the maximum node from a subtree.
     *
     * @param node the root of the subtree
     * @return the updated subtree root after removal
     */
    private BSTreeNode<E> removeMaximum(BSTreeNode<E> node) {
        if (node.getRight() == null) {
            return node.getLeft();
        }
        node.setRight(removeMaximum(node.getRight()));
        return node;
    }

    /**
     * Creates an in-order iterator over the contents of the tree.
     * Elements are returned in their natural (sorted) order.
     * The iterator makes a deep copy of all elements for safe traversal.
     *
     * @return an iterator with elements in natural order
     */
    @Override
    public Iterator<E> inorderIterator() {
        return new TreeIterator("INORDER");
    }

    /**
     * Creates a pre-order iterator over the contents of the tree.
     * Elements are returned in an order where the root element comes first,
     * followed by left subtree, then right subtree.
     * The iterator makes a deep copy of all elements for safe traversal.
     *
     * @return an iterator with elements in pre-order
     */
    @Override
    public Iterator<E> preorderIterator() {
        return new TreeIterator("PREORDER");
    }

    /**
     * Creates a post-order iterator over the contents of the tree.
     * Elements are returned in an order where the root element comes last,
     * after left and right subtrees have been traversed.
     * The iterator makes a deep copy of all elements for safe traversal.
     *
     * @return an iterator with elements in post-order
     */
    @Override
    public Iterator<E> postorderIterator() {
        return new TreeIterator("POSTORDER");
    }

    /**
     * Helper method to perform in-order traversal and collect elements.
     *
     * @param node the current node
     * @param elements the list to collect elements into
     */
    private void inorderTraversal(BSTreeNode<E> node, List<E> elements) {
        if (node != null) {
            inorderTraversal(node.getLeft(), elements);
            elements.add(node.getData());
            inorderTraversal(node.getRight(), elements);
        }
    }

    /**
     * Helper method to perform pre-order traversal and collect elements.
     *
     * @param node the current node
     * @param elements the list to collect elements into
     */
    private void preorderTraversal(BSTreeNode<E> node, List<E> elements) {
        if (node != null) {
            elements.add(node.getData());
            preorderTraversal(node.getLeft(), elements);
            preorderTraversal(node.getRight(), elements);
        }
    }

    /**
     * Helper method to perform post-order traversal and collect elements.
     *
     * @param node the current node
     * @param elements the list to collect elements into
     */
    private void postorderTraversal(BSTreeNode<E> node, List<E> elements) {
        if (node != null) {
            postorderTraversal(node.getLeft(), elements);
            postorderTraversal(node.getRight(), elements);
            elements.add(node.getData());
        }
    }

    /**
     * Inner class implementing the Iterator interface for tree traversal.
     * This iterator creates a deep copy of tree elements according to the
     * specified traversal order and provides safe iteration.
     */
    private class TreeIterator implements Iterator<E> {

        /** List containing deep copy of elements in traversal order */
        private List<E> elements;

        /** Current position in the elements list */
        private int currentIndex;

        /**
         * Constructs a tree iterator for the specified traversal order.
         *
         * @param traversalOrder the order of traversal ("INORDER", "PREORDER", "POSTORDER")
         */
        public TreeIterator(String traversalOrder) {
            elements = new ArrayList<>();
            currentIndex = 0;

            // Create deep copy of elements in specified order
            switch (traversalOrder.toUpperCase()) {
                case "INORDER":
                    inorderTraversal(root, elements);
                    break;
                case "PREORDER":
                    preorderTraversal(root, elements);
                    break;
                case "POSTORDER":
                    postorderTraversal(root, elements);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid traversal order: " + traversalOrder);
            }
        }

        /**
         * Returns true if the iteration has more elements.
         *
         * @return true if the iterator has more elements
         */
        @Override
        public boolean hasNext() {
            return currentIndex < elements.size();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in iteration");
            }
            return elements.get(currentIndex++);
        }
    }

    /**
     * Returns a string representation of the tree using in-order traversal.
     * Elements are displayed in sorted order within square brackets.
     *
     * @return string representation of the tree
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        result.append("[");

        Iterator<E> iterator = inorderIterator();
        boolean first = true;

        while (iterator.hasNext()) {
            if (!first) {
                result.append(", ");
            }
            result.append(iterator.next().toString());
            first = false;
        }

        result.append("]");
        return result.toString();
    }
}