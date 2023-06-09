import java.util.HashMap;
import java.util.Scanner;

/**
 * The `Storage` class represents a storage container that can be opened, checked, and it's not takable.
 * Extends the `Item` class and has a name, a list of items that it contains, and a boolean value indicating whether it is open or closed.
 * It also has a boolean value indicating whether it is openable or not.
 *
 * @author [Christy Yang]
 */
public class Storage extends Item {
    /**
     * Indicates whether the storage can be opened or not.
     */
    public boolean openable = true;
    /*
     * Indicates whether the storage is currently open or closed.
     */
    public boolean open = false;
    /*
     * A list of items contained in the storage.
     */
    public HashMap<String, Item> itemList;

    /**
     * Constructs a new Storage with the given name and openability，
     * .and it is not takable
     *
     * @param name     the name of the storage
     * @param openable whether the storage can be opened or not
     */
    public Storage(String name, boolean openable) {
        super(name);
        this.itemList = new HashMap<>();
        this.takable = false;
        this.openable = openable;
    }

    /**
     * Adds the given items to the storage.
     *
     * @param items the items to add to the storage
     */
    public void addItem(Item... items) {
        for (Item item : items)
            this.itemList.put(item.getName(), item);
    }

    /**
     * Allows the player to view the contents of the storage and add or remove items from it.
     *
     * @param player the player interacting with the storage
     */
    public void check(Player player) {
        //openable but not open
        if (this.openable && !this.open) {
            System.out.println("The " + this.name + " is closed.");
            return;
        }

        //not openable & open
        if (this.itemList.isEmpty()) {
            System.out.println("The " + this.name + " is empty.");
        } else {
            System.out.print("The " + this.name + " contains:" + itemList.keySet());
            System.out.println("");
            System.out.println("Do you want to get an item? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("y") || input.equals("yes") || input.equals("get")) {
                System.out.println("Which item do you want to get?");
                String itemName = scanner.nextLine();
                if (itemList.get(itemName) != null) {
                    System.out.println("You get the " + itemName + ".");
                    player.addItem(itemList.get(itemName));
                    this.itemList.remove(itemName);
                } else {
                    System.out.println("There is no " + itemName);
                }
            }
        }
        System.out.println("Do you want to put an item? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        if (input.equals("y") || input.equals("yes") || input.equals("put")) {
            System.out.println("Which item do you want to put?");
            String itemName = scanner.nextLine();
            if (player.inventory.get(itemName) != null) {
                System.out.println("You put the " + itemName + ".");
                this.addItem(player.inventory.get(itemName));
                player.inventory.remove(itemName);
            } else {
                System.out.println("You don't have " + itemName);
            }
        }
    }

    /**
     * Opens the storage if it is openable.
     * If it is already open, a message is printed indicating that it is already open.
     * If the storage is empty, a message is printed indicating that it is empty.
     * If the storage is not empty, the contents of the storage are printed.
     */
    public void open() {
        if (!this.openable) {
            System.out.println("This is a " + this.getName() + ". What do you expect?");
            return;
        }
        if (this.open) {
            System.out.println("The " + this.name + " is already open. You can check it now");
            return;
        }
        System.out.println("You open the " + this.name + ".");
        this.open = true;
        if (this.itemList.isEmpty()) {
            System.out.println("The " + this.name + " is empty.");
            return;
        }
        System.out.print("The " + this.name + " contains: " + itemList.keySet());
        System.out.println("");
    }
}

