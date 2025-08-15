package implementations;

public class BSTreeNode<E>
{
    private E element;
    private BSTreeNode<E> left;
    private BSTreeNode<E> right;

    public BSTreeNode(E element) throws NullPointerException
    {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public BSTreeNode<E>  getLeft()
    {
        return this.left;
    }

    public BSTreeNode<E>  getRight()
    {
        return this.right;
    }

    public E getElement() {
        return this.element;
    }
}
