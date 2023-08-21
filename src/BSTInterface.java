/**
 * This interface represents the basic operations that can be performed on a
 * Binary Search Tree (BST).
 *
 * @param <T>
 *            The type of elements that will be stored in the Binary Search
 *            Tree. Must extend Comparable<T>
 *
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
public interface BSTInterface<T extends Comparable<T>>
    extends Iterable<Node<T>> {

    // ----------------------------------------------------------
    /**
     * Removes all elements from the Binary Search Tree, leaving it empty.
     */
    public void clear();


    /**
     * Returns the number of elements currently stored in the Binary Search
     * Tree.
     *
     * @return The number of elements in the tree.
     */
    public int size();


    /**
     * Checks whether the Binary Search Tree is empty.
     *
     * @return true if the tree is empty, false otherwise.
     */
    public boolean isEmpty();


    /**
     * Inserts the given value into the Binary Search Tree while maintaining the
     * BST property.
     *
     * @param value
     *            The value to be inserted into the tree.
     */
    public void insert(T value);


    /**
     * Removes a node containing the specified value from the Binary Search Tree
     *
     * @param value
     *            The value to be removed from the tree.
     * @return The value that was removed, or null if the value was not found.
     */
    public T remove(T value);


    /**
     * Searches for a node containing the specified value in the Binary Search
     * Tree.
     *
     * @param value
     *            The value to search for.
     * @return The node containing the value, or null if the value is not found
     *         in the tree.
     */
    public Node<T> find(T value);


    /**
     * Prints the elements of the Binary Search Tree
     *
     */
    public void print();

}
