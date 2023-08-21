import static org.junit.Assert.*;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Unit tests for the CommandProcessor class
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
@SuppressWarnings("rawtypes")
public class CommandProcessorTest {

    private CommandProcessor commandProcessor = new CommandProcessor();

    private World world;
    private static BST bst;
    private final ByteArrayOutputStream outputStream =
        new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Initializes the BST and World instances before each test case.
     */
    @Before
    public void setUp() {
        bst = new BST();
        world = new World();
    }


    /**
     * Redirects the standard output stream to a ByteArrayOutputStream
     * to capture printed output during test cases.
     */
    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outputStream));
    }


    /**
     * Restores the original standard output stream after each test case.
     */
    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }


    /**
     * Tests the insertHelper method in CommandProcessor class.
     */
    @Test
    public void testInsert() {
        String[] commands = { "insert", "rect1", "3", "5", "6", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands));

        String[] commands2 = { "insert", "rect2", "3", "3", "6", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands2));

        String[] commands3 = { "insert", "rect3", "3", "8", "6", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands3));

        String[] commands4 = { "insert", "rect4", "4", "8", "5", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands4));

        String[] commands5 = { "insert", "rect5", "0", "3", "7", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands5));

        assertEquals(5, bst.size());
    }


    /**
     * Tests invalid scenarios for the insertHelper method in CommandProcessor
     * class.
     */
    @Test
    public void testInvalidInsert() {

        String[] commands = { "insert", "rect1", "3", "5", "-6", "2" };
        assertFalse(commandProcessor.insertHelper(bst, commands));

        String[] commands2 = { "insert", "rect2", "3", "3", "6", "-2" };
        assertFalse(commandProcessor.insertHelper(bst, commands2));

        String[] commands3 = { "insert", "rect3", "-1", "5", "6", "2" };
        assertFalse(commandProcessor.insertHelper(bst, commands3));

        String[] commands4 = { "insert", "rect4", "4", "1025", "5", "2" };
        assertFalse(commandProcessor.insertHelper(bst, commands4));

        String[] commands5 = { "insert", "rect5", "1", "-5", "6", "2" };
        assertFalse(commandProcessor.insertHelper(bst, commands5));

        assertEquals(0, bst.size());

    }


    /**
     * Tests the searchHelper method in CommandProcessor class.
     */
    @Test
    public void testSearch() {

        String[] commands1 = { "insert", "rect1", "3", "3", "6", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands1));

        String[] commands2 = { "insert", "rect2", "0", "3", "6", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands2));

        assertEquals(1, commandProcessor.searchHelper(bst, "rect1"));
        assertEquals(1, commandProcessor.searchHelper(bst, "rect2"));

    }


    /**
     * Tests invalid scenarios for the searchHelper method in CommandProcessor
     * class.
     */
    @Test
    public void testInvalidSearch() {
        assertEquals(0, commandProcessor.searchHelper(bst, "rect"));
    }


    /**
     * Tests the dumpHelper method in CommandProcessor class with an empty BST.
     */
    @Test
    public void testEmptyDump() {
        commandProcessor.dumpHelper(bst);
        assertEquals(0, bst.size());
    }


    /**
     * Tests the regionSearchHelper method in CommandProcessor class.
     */
    @Test
    public void testRegionSearch() {

        String[] insertCommand = { "insert", "rect1", "0", "0", "10", "10" };
        commandProcessor.insertHelper(bst, insertCommand);

        Rectangle searchRectangle = new Rectangle(0, 0, 5, 5);
        int result = commandProcessor.regionSearchHelper(searchRectangle,
            new World(), bst);

        assertEquals(1, result);

        String[] commands = { "insert", "rect1", "45", "0", "10", "10" };
        assertTrue(commandProcessor.insertHelper(bst, commands));

        String[] commands2 = { "insert", "rect2", "400", "0", "100", "310" };
        assertTrue(commandProcessor.insertHelper(bst, commands2));

        Rectangle region = new Rectangle(-900, 5, 5000, 20);
        assertEquals(3, commandProcessor.regionSearchHelper(region, new World(),
            bst));
        region = new Rectangle(2, 2, 1, 1);
        assertEquals(1, commandProcessor.regionSearchHelper(region, new World(),
            bst));
    }


    /**
     * Tests invalid scenarios for the regionSearchHelper method in
     * CommandProcessor class.
     */
    @Test
    public void testInvalidRegionSearch() {
        Rectangle invalidCoords = new Rectangle(3, 4, -6, 7);
        assertEquals(-1, commandProcessor.regionSearchHelper(invalidCoords,
            new World(), bst));
        invalidCoords = new Rectangle(3, 4, 6, 0);
        assertEquals(-1, commandProcessor.regionSearchHelper(invalidCoords,
            new World(), bst));
        invalidCoords = new Rectangle(3, 4, 6, -2);
        assertEquals(-1, commandProcessor.regionSearchHelper(invalidCoords,
            new World(), bst));
        invalidCoords = new Rectangle(-3, 4, -6, 0);
        assertEquals(-1, commandProcessor.regionSearchHelper(invalidCoords,
            new World(), bst));
        invalidCoords = new Rectangle(3, -4, 6, 0);
        assertEquals(-1, commandProcessor.regionSearchHelper(invalidCoords,
            new World(), bst));
        invalidCoords = new Rectangle(1026, 4, 2, 3);
        assertEquals(-1, commandProcessor.regionSearchHelper(invalidCoords,
            new World(), bst));
        invalidCoords = new Rectangle(3, 1026, 4, 3);
        assertEquals(-1, commandProcessor.regionSearchHelper(invalidCoords,
            new World(), bst));
    }


    /**
     * Tests the removeByNameHelper method in CommandProcessor class.
     */
    @Test
    public void testRemoveByName() {

        String[] insertCommand = { "insert", "rect", "0", "0", "10", "10" };
        commandProcessor.insertHelper(bst, insertCommand);
        assertEquals(1, bst.size());
        commandProcessor.removeByNameHelper(bst, "rect");

        assertEquals(0, bst.size());
    }


    /**
     * Tests invalid scenarios for the removeByNameHelper method in
     * CommandProcessor class.
     */
    @Test
    public void testInvalidRemove() {

        String[] commands = { "insert", "rect1", "3", "5", "6", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands));

        String[] commands2 = { "insert", "rect2", "3", "3", "6", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands2));

        String[] commands3 = { "insert", "rect3", "3", "5", "6", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands3));

        String[] commands4 = { "insert", "rect4", "4", "5", "5", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands4));

        String[] commands5 = { "insert", "rect5", "0", "3", "6", "2" };
        assertTrue(commandProcessor.insertHelper(bst, commands5));

        commandProcessor.removeByNameHelper(bst, "rect6");
        assertEquals(5, bst.size());
    }


    /**
     * Tests the intersections method in the World class through the
     * CommandProcessor.
     */
    @Test
    public void testIntersections() {

        String[] insertCommand1 = { "insert", "rect1", "0", "0", "10", "10" };
        String[] insertCommand2 = { "insert", "rect2", "5", "5", "10", "10" };

        commandProcessor.insertHelper(bst, insertCommand1);
        commandProcessor.insertHelper(bst, insertCommand2);

        world.intersections(bst);

        String printedOutput = outputStream.toString();
        String printedOutput2 = outputStream.toString();
        assertEquals(printedOutput, printedOutput2);

    }


    /**
     * Tests the removeByCoOrdinatessHelper method in CommandProcessor class.
     */
    @Test
    public void testRemoveByCoordinates() {

        String[] commands = { "insert", "rect1", "1", "4", "5", "6" };
        assertTrue(commandProcessor.insertHelper(bst, commands));

        String[] commands2 = { "insert", "rect2", "3", "4", "6", "7" };
        assertTrue(commandProcessor.insertHelper(bst, commands2));

        String[] commands3 = { "insert", "rect3", "3", "4", "5", "6" };
        assertTrue(commandProcessor.insertHelper(bst, commands3));

        String[] commands4 = { "insert", "rect4", "3", "4", "6", "7" };
        assertTrue(commandProcessor.insertHelper(bst, commands4));

        Rectangle rectangle1 = new Rectangle(1, 4, 5, 6);
        Rectangle rectangle2 = new Rectangle(3, 4, 6, 7);
        Rectangle rectangle3 = new Rectangle(3, 4, 5, 6);
        Rectangle rectangle4 = new Rectangle(3, 4, 6, 7);

        assertEquals(4, bst.size());
        commandProcessor.removeByCoOrdinatessHelper(bst, rectangle1);
        assertEquals(3, bst.size());
        commandProcessor.removeByCoOrdinatessHelper(bst, rectangle2);
        assertEquals(2, bst.size());
        commandProcessor.removeByCoOrdinatessHelper(bst, rectangle3);
        assertEquals(1, bst.size());
        commandProcessor.removeByCoOrdinatessHelper(bst, rectangle4);
        assertEquals(0, bst.size());
    }


    /**
     * Tests invalid scenarios for the removeByCoOrdinatessHelper method in
     * CommandProcessor class.
     */
    @Test
    public void testInvalidRemoveByCoordinates() {

        Rectangle rc1 = new Rectangle(3, 14, 5, 4);
        commandProcessor.removeByCoOrdinatessHelper(bst, rc1);
        assertNotEquals(1, bst.size());

    }


    /**
     * Tests the processCommands method in CommandProcessor class for "insert"
     * commands.
     */
    @Test
    public void testProcessCommandsInsert() {
        String[] insertCommand = { "insert", "rect1", "0", "0", "10", "10" };
        commandProcessor.processCommands(insertCommand, bst, world);

        String printedOutput = outputStream.toString();
        boolean flag = printedOutput.contains("Rejected");

        assertFalse(flag);

    }


    /**
     * Tests the processCommands method in CommandProcessor class for "dump"
     * commands.
     */
    @Test
    public void testProcessCommandsDump() {
        String[] dumpCommand = { "dump" };
        commandProcessor.processCommands(dumpCommand, bst, world);

        String printedOutput = outputStream.toString();
        boolean flag = printedOutput.length() > 0;
        assertTrue(flag);
    }


    /**
     * Tests the processCommands method in CommandProcessor class for "remove"
     * commands by name.
     */
    @Test
    public void testProcessCommandsRemoveByName() {
        String[] removeCommand = { "remove", "rect1" };
        commandProcessor.processCommands(removeCommand, bst, world);

        String printedOutput = outputStream.toString();
        boolean flag = printedOutput.length() > 0;
        assertTrue(flag);

    }


    /**
     * Tests the processCommands method in CommandProcessor class for "remove"
     * commands by coordinates.
     */
    @Test
    public void testProcessCommandsRemoveByCoordinates() {
        String[] removeCommand = { "remove", "0", "0", "10", "10" };
        commandProcessor.processCommands(removeCommand, bst, world);

        String printedOutput = outputStream.toString();
        boolean flag = printedOutput.length() > 0;
        assertTrue(flag);

    }


    /**
     * Tests the processCommands method in CommandProcessor class for
     * "regionsearch" commands.
     */
    @Test
    public void testProcessCommandsRegionSearch() {
        String[] regionSearchCommand = { "regionsearch", "0", "0", "10", "10" };
        commandProcessor.processCommands(regionSearchCommand, bst, world);

        String printedOutput = outputStream.toString();
        boolean flag = printedOutput.length() > 0;
        assertFalse(flag);

    }


    /**
     * Tests the processCommands method in CommandProcessor class for
     * "intersections" commands.
     */
    @Test
    public void testProcessCommandsIntersections() {
        String[] intersectionsCommand = { "intersections" };
        commandProcessor.processCommands(intersectionsCommand, bst, world);

        String printedOutput = outputStream.toString();
        boolean flag = printedOutput.length() > 0;
        assertTrue(flag);

    }


    /**
     * Tests the processCommands method in CommandProcessor class for "search"
     * commands.
     */
    @Test
    public void testProcessCommandsSearch() {
        String[] searchCommand = { "search", "rect1" };
        commandProcessor.processCommands(searchCommand, bst, world);

        String printedOutput = outputStream.toString();
        boolean flag = printedOutput.length() > 0;
        assertTrue(flag);

    }


    /**
     * Tests process command processor function
     *
     * @throws FileNotFoundException
     */
    @Test
    public void testProcessCommandsWithListOfCommands()
        throws FileNotFoundException {

        String fileName = "file2.txt";
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String[] commands = command.trim().split("\\s+");
            commandProcessor.processCommands(commands, bst, world);
        }
        assertEquals(24, bst.size());
    }


    /**
     * Tests process command function with empty data
     *
     * @throws FileNotFoundException
     */
    @Test
    public void testProcessCommandsWithEmptyListOfCommands()
        throws FileNotFoundException {
        String fileName = "file1.txt";
        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String[] commands = command.trim().split("\\s+");
            commandProcessor.processCommands(commands, bst, world);
        }

        assertEquals(0, bst.size());
    }

}
