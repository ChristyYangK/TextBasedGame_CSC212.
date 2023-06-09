/**
 * The `Item` class represents an object that can be picked up and carried by the player.
 * It has a name and a boolean value indicating whether it is takable or not.
 *
 * @author [Christy Yang]
 */
public class Item {
    /**
     * The name of the item.
     */
    public String name;
    /**
     * Whether the item can be taken.
     */
    public boolean takable = true;

    /**
     * Constructs a new `Item` with the given name.
     *
     * @param name the name of the item
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the takable property of the item to the given value.
     *
     * @param input the new value for the takable property
     */
    public void setTakable(boolean input) {
        this.takable = input;
    }
}
