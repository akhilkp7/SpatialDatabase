// -------------------------------------------------------------------------
/**
 * This interface represents a generic node that can be used in various tree
 * structures.
 *
 * @param <T>
 *            The type of data that the node holds.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
public interface NodeInterface<T extends Comparable<T>> {

    /**
     * Sets the value of the node.
     *
     * @param value
     *            The value to be set for the node.
     */
    public void setValue(T value);


    /**
     * Sets the right child node of this node.
     *
     * @param right
     *            The right child node to be set.
     */
    public void setRight(Node<T> right);


    /**
     * Sets the left child node of this node.
     *
     * @param left
     *            The left child node to be set.
     */
    public void setLeft(Node<T> left);


    /**
     * Retrieves the right child node of this node.
     *
     * @return The right child node.
     */
    public Node<T> right();


    /**
     * Retrieves the left child node of this node.
     *
     * @return The left child node.
     */
    public Node<T> left();


    /**
     * Retrieves the value stored in this node.
     *
     * @return The value stored in the node.
     */
    public T value();


    /**
     * Checks whether the node is a leaf node.
     *
     * @return true if the node is a leaf, false otherwise.
     */
    public boolean isLeaf();
}
