import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A binary search tree (BST) implementation that stores nodes containing
 * generic values and allows various operations.
 *
 * @param <T>
 *            The type of elements to be stored in the tree, must extend
 *            Comparable.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
@SuppressWarnings("rawtypes")
public class BST<T extends Comparable<T>> implements BSTInterface<T> {

    private Node<T> root;

    private int size;

    private boolean nodeFoundFlag = false;

    // ----------------------------------------------------------
    /**
     * Create a new BST object.
     */
    public BST() {
        root = null;
        size = 0;
    }


    /**
     * Clears the entire binary search tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }


    /**
     * Retrieves the number of nodes in the binary search tree.
     *
     * @return The size of the binary search tree.
     */
    public int size() {
        return this.size;
    }


    /**
     * Checks if the binary search tree is empty.
     *
     * @return True if the binary search tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Retrieves the root node of the binary search tree.
     *
     * @return The root node of the binary search tree.
     */
    public Node<T> root() {
        return this.root;
    }


    /**
     * Inserts a new value into the binary search tree.
     *
     * @param value
     *            The value to be inserted.
     */
    public void insert(T value) {
        try {
            root = insertHelper(root, value);
        }
        catch (Exception e) {
            return;
        }
        size++;

    }


    // Helper method for inserting a value into the tree
    @SuppressWarnings("unchecked")
    private Node<T> insertHelper(Node<T> node, T value) throws Exception {
        if (node == null) {
            return new Node<T>(value);
        }

        if (((DictUtils)node.value()).compareTo((DictUtils)value) == 0) {
            throw new Exception();
        }
        else if (((DictUtils)node.value()).compareTo((DictUtils)value) > 0) {
            node.setLeft(insertHelper(node.left(), value));

        }
        else {
            node.setRight(insertHelper(node.right(), value));

        }
        return node;
    }


    /**
     * Find a node with a specific value in the tree
     *
     * @param value
     *            The value to search.
     *
     * @return the node with the given value
     */
    public Node<T> find(T value) {
        return findHelper(root, value);

    }


    // Helper method to find a node with a specific value
    @SuppressWarnings("unchecked")
    private Node<T> findHelper(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (((DictUtils)node.value()).compareByKey(value) == 0) {
            return node;
        }
        else if (((DictUtils)node.value()).compareByKey(value) > 0) {
            return findHelper(node.left(), value);
        }
        return findHelper(node.right(), value);
    }


    /**
     * Delete a node with a specific value in the tree
     *
     * @param value
     *            The value to delete.
     *
     * @return returns the deleted node null otherwise
     */
    @SuppressWarnings("unchecked")
    public T remove(T value) {
        nodeFoundFlag = false;
        Node node = removeHelper(root, value);
        if (nodeFoundFlag) {
            root = node;
            size--;
            return value;
        }
        return null;
    }


    // Helper method to remove a node with a specific value
    @SuppressWarnings("unchecked")
    private Node<T> removeHelper(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (((DictUtils)node.value()).compareByKey(value) > 0) {
            node.setLeft(removeHelper(node.left(), value));
        }
        else if (((DictUtils)node.value()).compareByKey(value) < 0) {
            node.setRight(removeHelper(node.right(), value));
        }
        else {
            nodeFoundFlag = true;
            if (node.left() == null) {
                return node.right();
            }
            else if (node.right() == null) {
                return node.left();
            }
            else {
                Node t = getMax(node.left());
                node.setValue((T)t.value());
                node.setLeft(deleteMax(node.left()));
            }
        }
        return node;
    }


    // ----------------------------------------------------------
    /**
     * Get the maximum node in a subtree
     *
     * @param node
     *            root of the subtree
     * @return node with max value
     */

    private Node<T> getMax(Node<T> node) {
        if (node.right() == null) {
            return node;
        }

        return getMax(node.right());
    }


    // ----------------------------------------------------------
    /**
     * Delete the maximum node in a subtree
     *
     * @param node
     *            root of the subtree
     * @return deleted node with max value
     */

    private Node<T> deleteMax(Node<T> node) {
        if (node.right() == null) {
            return node.left();
        }
        node.setRight(deleteMax(node.right()));
        return node;

    }


    // ----------------------------------------------------------
    /**
     * Remove a node by coordinates.
     *
     * @param value
     *            value to remove
     * @return Boolean true or flase indicating the success or fail
     */
    @SuppressWarnings("unchecked")
    public Boolean removeByCoOrdinates(T value) {
        nodeFoundFlag = false;
        Node node = removeByCoOrdinatesHelper(root, value);
        if (nodeFoundFlag) {
            root = node;
            size--;
        }
        return nodeFoundFlag;

    }


    // Helper method to remove a node by coordinates
    @SuppressWarnings("unchecked")
    private Node<T> removeByCoOrdinatesHelper(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (((DictUtils)node.value()).compareByValue(((DictUtils)value)
            .getValue())) {

            nodeFoundFlag = true;
            if (node.left() == null) {
                return node.right();
            }
            else if (node.right() == null) {
                return node.left();
            }
            else {
                Node temp = getMax(node.left());
                node.setValue((T)temp.value());
                node.setLeft(deleteMax(node.left()));
            }
        }
        else {
            node.setLeft(removeByCoOrdinatesHelper(node.left(), value));
            if (!nodeFoundFlag) {
                node.setRight(removeByCoOrdinatesHelper(node.right(), value));
            }
        }
        return node;

    }


    /**
     * Get an iterator for the tree
     *
     * @return returns the iterator object from the given node
     */
    @SuppressWarnings("unchecked")
    public Iterator<Node<T>> iterator() {
        IterateBST iterateBST = new IterateBST();
        return iterateBST.iterator(root);
    }


    // ----------------------------------------------------------
    /**
     * Search nodes with a specific key in the tree
     *
     * @param key
     *            key of the node
     * @return node with the given key
     */
    public LinkedList<Node> search(String key) {
        LinkedList<Node> nodeList = new LinkedList<>();
        searchNodesHelper(root, key, nodeList);
        return nodeList;
    }


    // Helper method to search nodes with a specific key
    @SuppressWarnings("unchecked")
    private void searchNodesHelper(
        Node<T> node,
        String key,
        LinkedList<Node> nodeList) {
        if (node == null) {
            return;
        }

        if (((DictUtils)node.value()).compareByKey(key) == 0) {
            nodeList.add(node);
            searchNodesHelper(node.left(), key, nodeList);
        }
        else if (((DictUtils)node.value()).compareByKey(key) > 0) {
            searchNodesHelper(node.left(), key, nodeList);
        }
        else {
            searchNodesHelper(node.right(), key, nodeList);
        }
    }


    /**
     * Print the tree structure
     */
    public void print() {

        printTree(root, 0);
    }


    // Helper method to print the tree structure
    private void printTree(Node currentNode, int depth) {
        if (currentNode == null) {
            return;
        }
        printTree(currentNode.left(), depth + 1);
        DictUtils du = (DictUtils)currentNode.value();
        Rectangle rectangle = (Rectangle)du.getValue();

        System.out.println("Node has depth " + depth + ", Value (" + du.getKey()
            + ", " + (int)rectangle.getX() + ", " + (int)rectangle.getY() + ", "
            + (int)rectangle.getWidth() + ", " + (int)rectangle.getHeight()
            + ")");

        printTree(currentNode.right(), depth + 1);

    }

}
