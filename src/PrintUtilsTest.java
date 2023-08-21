import org.junit.Before;
import org.junit.Test;
import java.awt.Rectangle;
import static org.junit.Assert.assertEquals;

// -------------------------------------------------------------------------
/**
 * Unit tests for the methods in the PrintUtils class.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
public class PrintUtilsTest {

    private PrintUtils printUtils;

    /**
     * Initializes the PrintUtils object before each test.
     */
    @Before
    public void setUp() {
        printUtils = new PrintUtils();
    }


    /**
     * Test case for the printInterRegion method.
     */
    @Test
    public void testPrintInterRegion() {
        Rectangle rect = new Rectangle(10, 20, 30, 40);
        String result = printUtils.printInterRegion(rect);
        assertEquals("Rectangles intersecting region (10, 20, 30, 40):",
            result);
    }


    /**
     * Test case for the printRects method.
     */
    @Test
    public void testPrintRects() {

        DictUtils<String, Rectangle> du = new DictUtils<>("key1", new Rectangle(
            10, 20, 30, 40));
        Rectangle rect = new Rectangle(50, 60, 70, 80);
        String expectedOutput = "(key1, 50, 60, 70, 80)";
        String actualOutput = printUtils.printRects(du, rect);
        assertEquals(expectedOutput, actualOutput);
    }


    /**
     * Test case for the printIntersectionUtil method.
     */
    @Test
    public void testPrintIntersectionUtil() {

        DictUtils<String, Rectangle> du1 = new DictUtils<>("key1",
            new Rectangle(10, 20, 30, 40));
        DictUtils<String, Rectangle> du2 = new DictUtils<>("key2",
            new Rectangle(50, 60, 70, 80));
        Rectangle rect1 = new Rectangle(10, 20, 30, 40);
        Rectangle rect2 = new Rectangle(50, 60, 70, 80);
        String expectedOutput = "(key1, 10, 20, 30, 40 : key2, 50, 60, 70, 80)";
        String actualOutput = printUtils.printIntersectionUtil(du1, rect1,
            rect2, du2);
        assertEquals(expectedOutput, actualOutput);
    }


    /**
     * Test case for the messageUtil method.
     */
    @Test
    public void testMessageUtil() {
        String[] commandArray = { "command", "1", "2", "3", "4" };
        String result = printUtils.messageUtil(commandArray);
        assertEquals("Rectangle rejected: (1, 2, 3, 4)", result);
    }


    /**
     * Test case for the insertMessageUtil method.
     */
    @Test
    public void testInsertMessageUtil() {
        String message = "inserted";
        String name = "rectangleName";
        Rectangle rectangle = new Rectangle(10, 20, 30, 40);
        String result = printUtils.insertMessageUtil(message, name, rectangle);
        assertEquals("Rectangle inserted: (rectangleName, 10, 20, 30, 40)",
            result);
    }


    /**
     * Test case for the searchMessageUtil method.
     */
    @Test
    public void testSearchMessageUtil() {
        String name = "rectangleName";
        Rectangle rectangle = new Rectangle(10, 20, 30, 40);
        String result = printUtils.searchMessageUtil(name, rectangle);
        assertEquals("Rectangles found: (rectangleName, 10, 20, 30, 40)",
            result);
    }
}
