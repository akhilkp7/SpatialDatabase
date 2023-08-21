import java.awt.Rectangle;

// -------------------------------------------------------------------------
/**
 * Utility class for generating formatted print messages.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
public class PrintUtils {

    /**
     * Generates a message indicating the intersecting region of a rectangle.
     *
     * @param rect
     *            The rectangle whose intersecting region is to be printed.
     * @return A formatted string indicating the intersecting region of the
     *         rectangle.
     */
    public String printInterRegion(Rectangle rect) {
        return "Rectangles intersecting region (" + (int)rect.getX() + ", "
            + (int)rect.getY() + ", " + (int)rect.getWidth() + ", " + (int)rect
                .getHeight() + "):";
    }


    /**
     * Generates a formatted message for a rectangle and associated dictionary
     * key.
     *
     * @param du
     *            The dictionary utility object.
     * @param rect
     *            The rectangle to be printed.
     * @return A formatted string indicating the rectangle and its associated
     *         dictionary key.
     */
    @SuppressWarnings("rawtypes")
    public String printRects(DictUtils du, Rectangle rect) {
        return "(" + du.getKey() + ", " + (int)rect.getX() + ", " + (int)rect
            .getY() + ", " + (int)rect.getWidth() + ", " + (int)rect.getHeight()
            + ")";
    }


    /**
     * Generates a formatted message for the intersection of two rectangles
     * along with associated dictionary keys.
     *
     * @param du1
     *            The dictionary utility object for the first rectangle.
     * @param rect1
     *            The first rectangle to be printed.
     * @param rect2
     *            The second rectangle to be printed.
     * @param du2
     *            The dictionary utility object for the second rectangle.
     * @return A formatted string indicating the intersection of two rectangles
     *         and their associated dictionary keys.
     */
    @SuppressWarnings("rawtypes")
    public String printIntersectionUtil(
        DictUtils du1,
        Rectangle rect1,
        Rectangle rect2,
        DictUtils du2) {
        return "(" + du1.getKey() + ", " + (int)rect1.getX() + ", " + (int)rect1
            .getY() + ", " + (int)rect1.getWidth() + ", " + (int)rect1
                .getHeight() + " : " + du2.getKey() + ", " + (int)rect2.getX()
            + ", " + (int)rect2.getY() + ", " + (int)rect2.getWidth() + ", "
            + (int)rect2.getHeight() + ")";
    }


    /**
     * Generates a response message for a rejected rectangle command.
     *
     * @param commandArray
     *            The array containing command details.
     * @return A formatted string indicating a rejected rectangle command.
     */
    public String messageUtil(String[] commandArray) {
        return "Rectangle rejected: (" + +Integer.valueOf(commandArray[1])
            + ", " + Integer.valueOf(commandArray[2]) + ", " + Integer.valueOf(
                commandArray[3]) + ", " + Integer.valueOf(commandArray[4])
            + ")";
    }


    /**
     * Generates an insertion response message for a rectangle.
     *
     * @param message
     *            The message indicating the type of insertion.
     * @param name
     *            The name associated with the rectangle.
     * @param rectangle
     *            The rectangle to be printed.
     * @return A formatted string indicating an insertion response for a
     *         rectangle.
     */
    public String insertMessageUtil(
        String message,
        String name,
        Rectangle rectangle) {
        return "Rectangle " + message + ": (" + name + ", " + (int)rectangle
            .getX() + ", " + (int)rectangle.getY() + ", " + (int)rectangle
                .getWidth() + ", " + (int)rectangle.getHeight() + ")";
    }


    /**
     * Generates a search response message for a found rectangle.
     *
     * @param name
     *            The name associated with the rectangle.
     * @param rectangle
     *            The rectangle to be printed.
     * @return A formatted string indicating a search response for a rectangle.
     */
    public String searchMessageUtil(String name, Rectangle rectangle) {
        return "Rectangles found: (" + name + ", " + (int)rectangle.getX()
            + ", " + (int)rectangle.getY() + ", " + (int)rectangle.getWidth()
            + ", " + (int)rectangle.getHeight() + ")";
    }

}
