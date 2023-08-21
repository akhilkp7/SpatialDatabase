import java.awt.Rectangle;
import java.util.Iterator;

// -------------------------------------------------------------------------
/**
 * A class for finding intersections and performing region search.
 *
 * @param <T>
 *            The type of elements stored.
 *
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
@SuppressWarnings("rawtypes")
public class World<T extends Comparable<T>> {
    /**
     * Util object for printing the formatted output
     */
    private PrintUtils printUtil = new PrintUtils();

    /**
     * Finds and prints intersection pairs among rectangles stored in a Binary
     * Search Tree (BST).
     *
     * @param bst
     *            The Binary Search Tree containing rectangles.
     */
    public void intersections(BST bst) {
        int id = 0;
        Iterator itr = bst.iterator();
        Node[] nodeArray = new Node[bst.size()];
        while (itr.hasNext()) {
            nodeArray[id++] = (Node)itr.next();

        }
        System.out.println("Intersection pairs:");
        for (int i = 0; i < nodeArray.length; i++) {

            Node node1 = nodeArray[i];
            DictUtils du1 = (DictUtils)node1.value();
            Rectangle rect1 = (Rectangle)du1.getValue();

            for (int j = i + 1; j < nodeArray.length; j++) {
                Node node2 = nodeArray[j];

                DictUtils duT = (DictUtils)node2.value();
                Rectangle rectT = (Rectangle)duT.getValue();

                Double insertionX = Math.max(rectT.getX(), rect1.getX());
                Double insertionY = Math.max(rectT.getY(), rect1.getY());

                Double insertionW = Math.min(rectT.getX() + rectT.getWidth(),
                    rect1.getX() + rect1.getWidth()) - insertionX;
                Double insertionH = Math.min(rectT.getY() + rectT.getHeight(),
                    rect1.getY() + rect1.getHeight()) - insertionY;

                boolean flag = (insertionW > 0) && (insertionH > 0);

                if (flag && node2 != node1) {

                    DictUtils du2 = (DictUtils)node2.value();
                    Rectangle rect2 = (Rectangle)du2.getValue();
                    System.out.println(printUtil.printIntersectionUtil(du1,
                        rect1, rect2, du2));
                }

            }
        }
    }


    /**
     * Performs a region search within a given rectangle in the Binary Search
     * Tree (BST).
     *
     * @param rect1
     *            The rectangle to search within.
     * @param bst
     *            The Binary Search Tree containing rectangles.
     * @param rect2
     *            The second rectangle involved in the region search.
     * @return The count of intersecting rectangles found.
     */
    public int regionSearch(Rectangle rect1, BST bst, Rectangle rect2) {
        int count = 0;
        Node node = bst.root();
        count = regionSearchHelper(rect1, node, rect2, count);
        return count;
    }


    /**
     * Helper method for recursively performing a region search within a given
     * rectangle.
     *
     * @param rect1
     *            The rectangle to search within.
     * @param node
     *            The current node in the Binary Search Tree.
     * @param rect2
     *            The second rectangle involved in the region search.
     * @param count
     *            The count of intersecting rectangles found so far.
     * @return The updated count of intersecting rectangles found.
     */
    private int regionSearchHelper(
        Rectangle rect1,
        Node node,
        Rectangle rect2,
        int count) {
        if (node == null) {
            return count;
        }
        if (rect1.intersects((Rectangle)((DictUtils)node.value()).getValue())) {
            if (count == 0) {
                System.out.println(printUtil.printInterRegion(rect2));
            }
            count++;
            DictUtils du = (DictUtils)node.value();
            Rectangle rect = (Rectangle)du.getValue();
            System.out.println(printUtil.printRects(du, rect));
        }
        count = regionSearchHelper(rect1, node.left(), rect2, count);
        count = regionSearchHelper(rect1, node.right(), rect2, count);

        return count;
    }

}
