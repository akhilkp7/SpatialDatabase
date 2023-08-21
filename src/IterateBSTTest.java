import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.EmptyStackException;

// -------------------------------------------------------------------------
/**
 * Test class for the Iterator implementation.
 * This class contains test methods to validate the functionality of the
 * Iterator
 * class.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
@SuppressWarnings("rawtypes")
public class IterateBSTTest {

    private Node root;

    /**
     * Sets up the test environment by creating a binary search tree (BST) with
     * the following structure:
     * 5
     * / \
     * 3 7
     * / \ \
     * 2 4 8
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {

        root = new Node(5);
        root.setLeft(new Node(3));
        root.left().setLeft(new Node(2));
        root.left().setRight(new Node(4));
        root.setRight(new Node(7));
        root.right().setRight(new Node(8));
    }


    /**
     * Tests the iterator behavior of the BST iterator by comparing the values
     * obtained from the iterator
     * with the expected values.
     */
    @Test
    public void testIterator() {
        IterateBST iterator = new IterateBST(root);
        int[] expectedValues = { 2, 3, 4, 5, 7, 8 };
        int index = 0;

        while (iterator.hasNext()) {
            Node nextNode = iterator.next();
            assertEquals(expectedValues[index], nextNode.value());
            index++;
        }

        assertEquals(expectedValues.length, index);
    }


    /**
     * Tests the behavior of calling the "next" method on an empty iterator,
     * expecting an EmptyStackException to be thrown.
     */
    @Test(expected = EmptyStackException.class)
    public void testNextOnEmptyIterator() {
        IterateBST emptyIterator = new IterateBST();
        assertFalse(emptyIterator.hasNext());
        emptyIterator.next();
    }
}
