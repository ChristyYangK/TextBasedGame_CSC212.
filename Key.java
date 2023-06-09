/**
 * The Key class represents a key item.
 * Extends the Item class.
 * Has a unique identifier, the keyId field.
 *
 * @author Christy Yang
 */
public class Key extends Item {
    /**
     * The unique identifier for this key.
     */
    public String keyId;

    /**
     * Constructs a new Key with the given name and id.
     *
     * @param name the name of the key
     * @param id   the unique identifier for the key
     */
    public Key(String name, String id) {
        super(name);
        this.keyId = id;
    }

}



