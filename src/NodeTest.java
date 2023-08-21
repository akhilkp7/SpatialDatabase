import org.junit.Test;
import static org.junit.Assert.*;

// -------------------------------------------------------------------------
/**
 * A class to test the functionality of the Node class.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
public class NodeTest {

    /**
     * Tests the setValue method.
     */
    @Test
    public void testSetValue() {
        Node<Integer> node = new Node<>(5);
        node.setValue(10);
        assertEquals(Integer.valueOf(10), node.value());
    }


    /**
     * Tests the setRight method.
     */
    @Test
    public void testSetRight() {
        Node<Integer> node = new Node<>(5);
        Node<Integer> rightNode = new Node<>(8);
        node.setRight(rightNode);
        assertEquals(rightNode, node.right());
    }


    /**
     * Tests the setLeft method.
     */
    @Test
    public void testSetLeft() {
        Node<Integer> node = new Node<>(5);
        Node<Integer> leftNode = new Node<>(3);
        node.setLeft(leftNode);
        assertEquals(leftNode, node.left());
    }


    /**
     * Tests the right() method when no right node is set.
     */
    @Test
    public void testRight() {
        Node<Integer> node = new Node<>(5);
        assertNull(node.right());
    }


    /**
     * Tests the left() method when no left node is set.
     */
    @Test
    public void testLeft() {
        Node<Integer> node = new Node<>(5);
        assertNull(node.left());
    }


    /**
     * Tests the value() method.
     */
    @Test
    public void testValue() {
        Node<Integer> node = new Node<>(5);
        assertEquals(Integer.valueOf(5), node.value());
    }


    /**
     * Tests the isLeaf() method.
     */
    @Test
    public void testIsLeaf() {
        Node<Integer> leafNode = new Node<>(5);
        assertTrue(leafNode.isLeaf());

        Node<Integer> parentNode = new Node<>(10);
        Node<Integer> leftChildNode = new Node<>(5);
        parentNode.setLeft(leftChildNode);
        assertFalse(parentNode.isLeaf());

        Node<Integer> rightChildNode = new Node<>(15);
        parentNode.setRight(rightChildNode);
        assertFalse(parentNode.isLeaf());
    }
}
