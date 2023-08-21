
// -------------------------------------------------------------------------
/**
 * Represents a node in a binary tree structure.
 *
 * @param <T>
 *            The type of value held by the node.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
public class Node<T extends Comparable<T>> implements NodeInterface<T> {

    /**
     * Reference to the right child node.
     */
    private Node<T> right;

    /**
     * Reference to the left child node.
     */
    private Node<T> left;

    /**
     * Value held by the node.
     */
    private T value;

    /**
     * Constructs a new node with the given value and initializes left and right
     * children as null.
     *
     * @param value
     *            The value to be stored in the node.
     */
    public Node(T value) {
        this.value = value;
        this.right = null;
        this.left = null;
    }


    /**
     * Sets the value of the node.
     *
     * @param value
     *            The new value for the node.
     */
    public void setValue(T value) {
        this.value = value;
    }


    /**
     * Sets the right child node of this node.
     *
     * @param right
     *            The new right child node.
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }


    /**
     * Sets the left child node of this node.
     *
     * @param left
     *            The new left child node.
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }


    /**
     * Retrieves the right child node of this node.
     *
     * @return The right child node or null if no right child exists.
     */
    public Node<T> right() {
        return this.right;
    }


    /**
     * Retrieves the left child node of this node.
     *
     * @return The left child node or null if no left child exists.
     */
    public Node<T> left() {
        return this.left;
    }


    /**
     * Retrieves the value stored in this node.
     *
     * @return The value held by the node.
     */
    public T value() {
        return this.value;
    }


    /**
     * Checks if the node is a leaf node.
     *
     * @return True if the node is a leaf node, false otherwise.
     */
    public boolean isLeaf() {
        return this.right == null && this.left == null;
    }
}
