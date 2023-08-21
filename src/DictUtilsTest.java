import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Rectangle;

// -------------------------------------------------------------------------
/**
 * Unit tests for the DictUtils class.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
public class DictUtilsTest {

    /**
     * Test case for the getKey() and getValue() methods.
     */
    @Test
    public void testKeyAndValue() {
        Integer intKey = 42;
        String strValue = "Hello, World!";
        DictUtils<Integer, String> dict = new DictUtils<>(intKey, strValue);

        assertEquals(intKey, dict.getKey());
        assertEquals(strValue, dict.getValue());
    }


    /**
     * Tests the compareByKey method.
     */
    @Test
    public void testCompareToKey() {
        Integer intKey1 = 42;
        Integer intKey2 = 73;
        DictUtils<Integer, String> dict1 = new DictUtils<>(intKey1, "Value1");
        DictUtils<Integer, String> dict2 = new DictUtils<>(intKey2, "Value2");

        assertTrue(dict1.compareByKey(intKey2) < 0);
        assertTrue(dict2.compareByKey(intKey1) > 0);
        assertEquals(0, dict1.compareByKey(intKey1));
    }


    /**
     * Tests the compareByValue method.
     */
    @Test
    public void testCompareToValue() {
        Rectangle rect1 = new Rectangle(10, 20, 30, 40);
        Rectangle rect2 = new Rectangle(10, 20, 30, 40);
        Rectangle rect3 = new Rectangle(50, 60, 70, 80);
        DictUtils<Integer, Rectangle> dict1 = new DictUtils<>(42, rect1);
        DictUtils<Integer, Rectangle> dict2 = new DictUtils<>(73, rect2);
        assertTrue(dict1.compareByValue(rect2));
        assertFalse(dict1.compareByValue(rect3));
        assertFalse(dict2.compareByValue(rect3));
    }


    /**
     * Tests the compareTo method.
     */
    @Test
    public void testCompareToDictUtils() {
        DictUtils<Integer, String> dict1 = new DictUtils<>(42, "Value1");
        DictUtils<Integer, String> dict2 = new DictUtils<>(73, "Value2");

        assertTrue(dict1.compareTo(dict2) < 0);
        assertTrue(dict2.compareTo(dict1) > 0);

    }
}
