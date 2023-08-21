import java.util.Iterator;
import java.util.Stack;

// -------------------------------------------------------------------------
/**
 * An iterator implementation for traversing a binary search tree (BST) in an
 * in-order manner.
 * This iterator uses a stack to keep track of the nodes while performing the
 * in-order traversal.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
@SuppressWarnings("rawtypes")
public class IterateBST implements Iterator<Node> {

    private Node curr;
    private Stack<Node> st = new Stack<>();

    /**
     * Constructs an iterator starting from the specified node in the binary
     * search tree.
     *
     * @param node
     *            The starting node for the iterator.
     */
    public IterateBST(Node node) {
        curr = node;
    }


    /**
     * Constructs an iterator with no starting node.
     *
     */
    public IterateBST() {
        curr = null;
    }


    /**
     * Returns an iterator starting from the specified node in the binary search
     * tree.
     *
     * @param node
     *            The starting node for the iterator.
     * @return An iterator instance for traversing the binary search tree.
     */
    public Iterator iterator(Node node) {
        return new IterateBST(node);
    }


    /**
     * Checks if there are more nodes to be traversed.
     *
     * @return true if there are more nodes, false otherwise.
     */
    public boolean hasNext() {
        return (!st.isEmpty() || curr != null);
    }


    /**
     * Returns the next node in the in-order traversal of the binary search
     * tree.
     *
     * @return The next node in the traversal.
     */
    public Node next() {
        while (curr != null) {
            st.push(curr);
            curr = curr.left();
        }

        curr = st.pop();
        Node node = curr;
        curr = curr.right();

        return node;
    }

}
