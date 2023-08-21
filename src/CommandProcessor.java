import java.awt.Rectangle;
import java.util.List;

// -------------------------------------------------------------------------
/**
 * This class represents a Command Processor that handles various commands
 * such as insertion, removal, searching, and intersection detection.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
@SuppressWarnings("rawtypes")
public class CommandProcessor {

    /**
     * Util object used for printing the output
     */
    private PrintUtils printUtil = new PrintUtils();

    /**
     * Processes an array of commands by dispatching them to specific helper
     * methods based on the operation.
     *
     * @param commands
     *            An array of commands to be processed.
     * @param bst
     *            The Binary Search Tree to perform operations on.
     * @param world
     *            The World object for region-based operations.
     */
    public void processCommands(String[] commands, BST bst, World world) {

        String op = commands[0];
        if (op.equals("insert")) {
            insertHelper(bst, commands);
        }
        else if (op.equals("dump")) {
            dumpHelper(bst);

        }
        else if (op.equals("intersections")) {
            intersectionsHelper(world, bst);

        }
        else if (op.equals("regionsearch")) {
            Rectangle rectangle = new Rectangle(Integer.valueOf(commands[1]),
                Integer.valueOf(commands[2]), Integer.valueOf(commands[3]),
                Integer.valueOf(commands[4]));
            regionSearchHelper(rectangle, world, bst);

        }
        else if (op.equals("search")) {
            searchHelper(bst, commands[1]);
        }
        else if (op.equals("remove")) {
            if (commands.length == 2) {
                if (!(commands[1].matches("^[a-zA-Z].*$")) && (commands[1]
                    .matches("[a-zA-Z0-9_]*"))) {
                    System.out.println("Rectangle rejected: (" + commands[1]
                        + ")");
                }
                else {
                    removeByNameHelper(bst, commands[1]);
                }
            }
            else {
                if (!checkCoordinates(commands)) {
                    System.out.println(printUtil.messageUtil(commands));

                }
                else {
                    Rectangle rectangle = new Rectangle(Integer.valueOf(
                        commands[1]), Integer.valueOf(commands[2]), Integer
                            .valueOf(commands[3]), Integer.valueOf(
                                commands[4]));
                    removeByCoOrdinatessHelper(bst, rectangle);
                }
            }

        }

    }


    // ----------------------------------------------------------
    /**
     * Helper funciton for performing dump operation
     *
     * @param bst
     *            The Binary Search Tree to perform operations on.
     *
     */
    public void dumpHelper(BST bst) {
        System.out.println("BST dump:");
        Node curr = bst.root();
        if (curr == null) {
            System.out.println("Node has depth " + bst.size() + ", Value ("
                + curr + ")");
        }
        else {
            bst.print();
        }
        System.out.println("BST size is: " + bst.size());
    }


    // ----------------------------------------------------------
    /**
     * Helper funciton for performing insert operation
     *
     * @param bst
     *            The Binary Search Tree to perform operations on.
     * @param commands
     *            An array of commands to be processed.
     * @return True or false depending on the operation is success or fail
     */
    @SuppressWarnings("unchecked")
    public boolean insertHelper(BST bst, String[] commands) {

        int x = Integer.valueOf(commands[2]);
        int y = Integer.valueOf(commands[3]);
        int w = Integer.valueOf(commands[4]);
        int h = Integer.valueOf(commands[5]);

        Rectangle rect = new Rectangle(x, y, w, h);

        if (!checkRectangle(rect, commands[1])) {

            System.out.println(printUtil.insertMessageUtil("rejected",
                commands[1], rect));
            return false;

        }
        DictUtils<String, Rectangle> du = new DictUtils<String, Rectangle>(
            commands[1], rect);
        int beforeSize = bst.size();

        bst.insert(du);

        if (bst.size() != beforeSize + 1) {

            System.out.println(printUtil.insertMessageUtil("rejected",
                commands[1], rect));

            return false;
        }

        System.out.println(printUtil.insertMessageUtil("accepted", commands[1],
            rect));
        return true;

    }


    // ----------------------------------------------------------
    /**
     * Helper funciton for performing intersection operation
     *
     * @param world
     *            The World object for region-based operations.
     * @param bst
     *            The Binary Search Tree to perform operations on.
     */
    public void intersectionsHelper(World world, BST bst) {
        world.intersections(bst);
    }


    /**
     * Searches for rectangles in the Binary Search Tree (BST) with a given key.
     *
     * @param bst
     *            The Binary Search Tree (BST) to search in.
     * @param key
     *            The key to search for.
     * @return The count of rectangles found.
     */
    @SuppressWarnings("unchecked")
    public int searchHelper(BST bst, String key) {
        int count = 0;
        List<Node> nodes = bst.search(key);
        if (nodes.isEmpty()) {
            System.out.println("Rectangle not found: " + key);
            return count;
        }
        for (Node node : nodes) {

            System.out.println(printUtil.searchMessageUtil(key,
                (Rectangle)((DictUtils)node.value()).getValue()));

            count++;
        }
        return count;
    }


    /**
     * Performs a region search that intersect with a given rectangle.
     *
     * @param rect
     *            The rectangle for which to perform the region search.
     * @param world
     *            The World object to operate on.
     * @param bst
     *            The Binary Search Tree (BST) to use for the region search.
     * @return The count of intersecting rectangles found.
     */
    public int regionSearchHelper(Rectangle rect, World world, BST bst) {
        int h = (int)rect.getHeight();
        int x = (int)rect.getX();
        int y = (int)rect.getY();
        int w = (int)rect.getWidth();

        if (h <= 0 || w <= 0 || x + w <= 0 || y + h <= 0 || x >= 1024
            || y >= 1024) {
            return -1;
        }
        if (x < 0) {
            w = w + x;
            x = 0;
        }
        if (y < 0) {
            h = h + y;
            y = 0;
        }

        return world.regionSearch(new Rectangle(x, y, w, h), bst, rect);

    }


    /**
     * Removes a rectangle from the Binary Search Tree (BST) by its associated
     * name.
     *
     * @param bst
     *            The Binary Search Tree (BST) to remove from.
     * @param key
     *            The name associated with the rectangle to be removed.
     */
    @SuppressWarnings("unchecked")
    public void removeByNameHelper(BST bst, String key) {
        String name = (String)bst.remove(key);

        if (name == null) {
            System.out.println("Rectangle rejected: (" + key + ")");
        }
    }


    /**
     * Removes a rectangle from the Binary Search Tree (BST) by its coordinates.
     *
     * @param bst
     *            The Binary Search Tree (BST) to remove from.
     * @param rectangle
     *            The rectangle to be removed.
     */
    @SuppressWarnings("unchecked")
    public void removeByCoOrdinatessHelper(BST bst, Rectangle rectangle) {
        DictUtils du = new DictUtils<>(null, rectangle);
        Boolean removedFlag = bst.removeByCoOrdinates(du);
        if (!removedFlag) {
            System.out.println("Rectangle rejected: (" + (int)rectangle.getX()
                + ", " + (int)rectangle.getY() + ", " + (int)rectangle
                    .getWidth() + ", " + (int)rectangle.getHeight() + ")");
        }

    }


    /**
     * Checks if a given rectangle is valid
     *
     * @param rect
     *            The rectangle to be checked.
     * @param name
     *            The name associated with the rectangle.
     * @return True if the rectangle is valid, false otherwise.
     */
    public Boolean checkRectangle(Rectangle rect, String name) {

        if (rect.getHeight() <= 0 || rect.getWidth() <= 0 || rect.getX() < 0
            || rect.getY() < 0) {
            return false;
        }
        if (!(name.matches("^[a-zA-Z].*$") && name.matches("[a-zA-Z0-9_]*"))) {
            return false;
        }

        return (rect.getX() + rect.getWidth() <= 1024) && (rect.getY() + rect
            .getHeight() <= 1024);

    }


    /**
     * Checks if the coordinates of a rectangle are valid.
     *
     * @param commands
     *            An array of command parameters including coordinates.
     * @return True if the coordinates are valid, false otherwise.
     */
    public Boolean checkCoordinates(String[] commands) {
        int x = Integer.valueOf(commands[1]);
        int y = Integer.valueOf(commands[2]);
        int w = Integer.valueOf(commands[3]);
        int h = Integer.valueOf(commands[4]);
        if (h <= 0 || w <= 0 || x < 0 || y < 0) {
            return false;
        }

        return (x + w <= 1024) && (y + h <= 1024);

    }

}
