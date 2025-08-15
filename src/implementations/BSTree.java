package implementations;

import utilities.BSTreeADT;
import utilities.Iterator;

import java.util.NoSuchElementException;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E>
{
    private BSTreeNode<E> root;
    private int size;

    public BSTree()
    {
        this.root = null;
        this.size = 0;
    }

    public BSTree(E element)
    {
        this.root = new BSTreeNode<E>(element);
        this.size = 1;
    }

    public BSTreeNode<E> getRoot() throws NullPointerException
    {
        if (this.root == null) {
            throw new NullPointerException();
        }
        return this.root;
    }

    public int getHeight() {
        int lHeight = 0;
        int rHeight = 0;
        int height = 0;
        BSTreeNode<E> currentNode = this.root;

        return getHeight(currentNode);
    }

    public int getHeight(BSTreeNode<E> node)
    {
        if  (node == null) {
            return 1;
        }

        int left = getHeight(node.getLeft());
        int right = getHeight(node.getRight());

        return Math.max(left, right) + 1;
    }
//
//        if  (currentNode != null)
//        {
//            height++;
//            while (currentNode.getLeft() != null)
//            {
//                currentNode = currentNode.getLeft();
//                // when you reach the bottom, travel back up then go down
//            }
//
//            while (currentNode.getRight() != null)
//            {
//                currentNode = currentNode.getRight();
//            }
//        }
//        private BinaryTree left, right;
//        int getHeight() {

        // if level (starting from child of root), has a left and a right, count as 1
        // if level, has a sibling and left and right, check left first then right sibling
        //

        // if root has left
        // count first left
        // if first left has left, count that left
        // if first left has right, count that right
        // if they both exist,continue counting left and right
//        if  (currentNode != null) {
//            leftOf = currentNode.getLeft();
//            rightOf = currentNode.getRight();
//            if (leftOf != null) {
//                leftOfThis = leftOf.getLeft();
//                rightOfThis = rightOf.getRight();
//                if
//            }
//        }

//        return Math.max(lHeight, rHeight);


    public int size() {
        return this.size;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }

    public void clear()
    {

    }

    public boolean contains( E entry ) throws NullPointerException
    {
        return false;
    }

    public BSTreeNode<E> search( E entry ) throws NullPointerException
    {
        return null;
    }

    public boolean add( E newEntry ) throws NullPointerException
    {
        return false;
    }

    public BSTreeNode<E> removeMin()
    {
        return null;
    }

    public BSTreeNode<E> removeMax()
    {
        return null;
    }

    public Iterator<E> inorderIterator()
    {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() throws NoSuchElementException {
                return null;
            }
        };
    }

    public Iterator<E> preorderIterator()
    {
        return null;
    }

    public Iterator<E> postorderIterator()
    {
        return null;
    }
}
