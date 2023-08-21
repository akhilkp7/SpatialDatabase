import java.awt.Rectangle;

// -------------------------------------------------------------------------
/**
 * A utility class for working with key-value pairs, where the key is comparable
 * and the value can be any type.
 *
 * @param <K>
 *            The type of the key, must extend Comparable.
 * @param <E>
 *            The type of the value.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
public class DictUtils<K extends Comparable<K>, E>
    implements Comparable<DictUtils<K, E>> {
    private K key;
    private E value;

    /**
     * Constructs a new DictUtils instance with the given key and value.
     *
     * @param key
     *            The key for the key-value pair.
     * @param value
     *            The value associated with the key.
     */
    DictUtils(K key, E value) {
        this.key = key;
        this.value = value;
    }


    /**
     * Returns the key of the key-value pair.
     *
     * @return The key.
     */
    public K getKey() {
        return this.key;
    }


    /**
     * Returns the value of the key-value pair.
     *
     * @return The value.
     */
    public E getValue() {
        return this.value;
    }


    /**
     * Compares the key of this instance with the specified key for other.
     *
     * @param key1
     *            The key to compare with.
     * @return A negative integer, zero, or a positive integer as this key is
     *         less than, equal to, or greater than the specified key.
     */
    public int compareByKey(K key1) {
        return (this.key).compareTo(key1);
    }


    /**
     * Compares the value of this instance with the specified value for
     * equality.
     *
     * @param value1
     *            The value to compare with.
     * @return {@code true} if the values are equal, {@code false} otherwise.
     */
    public boolean compareByValue(E value1) {

        Rectangle rect1 = (Rectangle)value;
        Rectangle rect2 = (Rectangle)value1;
        return rect1.equals(rect2);
    }


    /**
     * Compares this instance with the specified DictUtils for order.
     *
     * @param du
     *            The DictUtils to compare with.
     * @return A negative integer, zero, or a positive integer as this instance
     *         is less than, equal to, or greater than the specified instance.
     */
    public int compareTo(DictUtils<K, E> du) {
        if (key.compareTo(du.getKey()) == 0) {
            Rectangle rect1 = (Rectangle)value;
            Rectangle rect2 = (Rectangle)du.getValue();
            if (rect1.equals(rect2)) {
                return 0;
            }
            else {
                return 1;
            }

        }
        return key.compareTo(du.getKey());
    }

}
