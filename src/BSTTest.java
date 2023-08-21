import static org.junit.Assert.*;
import java.awt.Rectangle;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Test class for the BST implementation.
 * This class contains test methods to validate the functionality of the BST
 * class.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
@SuppressWarnings("rawtypes")
public class BSTTest {

    private BST bst;

    /**
     * Sets up the test environment by initializing a new BST instance.
     */
    @Before
    public void setUp() {
        bst = new BST();
    }


    /**
     * Tests the insertion of elements into the BST.
     * Inserts three integers and verifies that the tree is not empty after
     * insertion.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testInsert() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(8);
        assertFalse(bst.isEmpty());
    }


    /**
     * Tests the clearing of the BST.
     * Inserts three integers, clears the tree, and then checks that the tree is
     * empty.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testClear() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(8);

        bst.clear();

        assertEquals(0, bst.size());
        assertTrue(bst.isEmpty());
    }


    /**
     * Tests the find method of the BST.
     * Tries to find two non-existent elements and verifies that they are not
     * found.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testFind() {
        assertNull(bst.find("rc1"));
        assertNull(bst.find("rc2"));

    }


    /**
     * Tests the isEmpty method of the BST.
     * Verifies that the tree is empty initially.
     */
    @Test
    public void testIsEmpty() {

        assertTrue(bst.isEmpty());
    }


    /**
     * Tests the remove method of the BST with an empty tree.
     * Tries to remove an element from an empty tree and verifies it returns
     * null.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testEmptyRemove() {
        assertNull(bst.remove("rc"));
    }


    /**
     * Tests the isLeaf method of the BST.
     * Inserts rectangles into the tree and verifies leaf status of tree nodes.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testIsLeaf() {
        Rectangle rc1 = new Rectangle(4, 0, 10, 11);
        Rectangle rc2 = new Rectangle(41, 10, 110, 30);
        DictUtils<String, Rectangle> du1 = new DictUtils<String, Rectangle>(
            "rect1", rc1);
        DictUtils<String, Rectangle> du2 = new DictUtils<String, Rectangle>(
            "rect2", rc2);

        bst.insert(du1);
        bst.insert(du2);
        assertFalse(bst.root().isLeaf());
        assertTrue(bst.root().right().isLeaf());
        DictUtils<String, Rectangle> du3 = new DictUtils<String, Rectangle>(
            "rect0", rc2);
        bst.insert(du3);
        assertFalse(bst.root().isLeaf());
        assertTrue(bst.root().left().isLeaf());

    }

}
