import java.util.HashMap;
import java.util.Scanner;

/**
 * The Person class represents a NPC in the game.
 * Each person has a name, a description, and a list of items they possess.
 * Extends Item class.
 *
 * @author Christy
 */
public class Person extends Item {
    /**
     * A HashMap containing the items that the player can gain from this NPC
     * with the item names as the keys and the Item objects as the values.
     */
    public HashMap<String, Item> itemList;
    /**
     * A description of this person.
     */
    public String description;

    /**
     * Constructs a new Person object with the specified name and description.
     *
     * @param name        the name of this person
     * @param description a description of this person
     */
    public Person(String name, String description) {
        super(name);
        this.itemList = new HashMap<>();
        this.description = description;
        this.takable = false;
    }

    /**
     * Adds one or more Item objects to this person's list of items.
     *
     * @param items one or more Item objects to add to this person's list of items
     */
    public void addItem(Item... items) {
        for (Item item : items)
            this.itemList.put(item.getName(), item);
    }

    /**
     * Interacts with this person by printing their description and possibly exchanging items with the player.
     * If this person is the "manager," the player's progress in completing tasks will be checked and the player may be given the "garageKey" item if all tasks are completed.
     * If this person is the "receptionist," the player may be given the "storageKey" item if they show the "officeKey" item in their inventory.
     *
     * @param player the Player object representing the player in the game
     */
    public void talk(Player player) {
        System.out.println(">>>> " + this.description);
        if (this.name.equals("manager")) {
            if (player.taskList.get("take photo") && player.taskList.get("dance") && player.taskList.get("interview") && player.taskList.get("wear mask") && player.taskList.get("wear outfit")) {
                if (!player.taskList.get("took a photo of yourself in the pose of hands over the face")) {
                    System.out.println(">>>> You should hands over the face in the photo. Go back and try again.");
                    return;
                }
                System.out.println(">>>> Well, you finished all the tasks. You deserve a drive with your friends！");
                if (this.itemList.get("garageKey") == null) {
                    System.out.println("But I think you've already taken the garage key. Do you think you left it somewhere?");
                    return;
                }
                System.out.println("Here is the key to the garage.");
                player.addItem(this.itemList.get("garageKey"));
                this.itemList.remove("garageKey");
            } else {
                System.out.println(">>>> Finish all your tasks before you want to hang out with your friends. I'm sure you know what they are.");
            }
        } else if (this.name.equals("receptionist")) {
            System.out.println(">>>> What do you have?");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            //if the player has the officeKey

            if (input.equals("officeKey") && player.inventory.get("officeKey") != null) {
                if (this.itemList.get("storageKey") == null) {
                    System.out.println("I'm sorry, but I think you've already taken the storage key. Do you think you left it somewhere?");
                    return;
                }
                System.out.println(">>>> Thank you. Here is the storage key.");
                player.addItem(this.itemList.get("storageKey"));
                this.itemList.remove("storageKey");
                return;
            } else if (input.equals("officeKey") && player.inventory.get("officeKey") == null) {
                System.out.println(">>>> You don't have that!");
                return;
            } else if (!input.equals("officeKey")) {
                System.out.println(">>>> I don't know what that is.");
                return;
            }
        }
    }

}


