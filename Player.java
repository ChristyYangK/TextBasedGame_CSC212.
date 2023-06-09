import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a player in a text-based adventure game.
 * The player can move between rooms, interact with items, and complete tasks.
 *
 * @author Christy Yang
 */
public class Player {
    /**
     * a Room object representing the room the player is currently in
     */
    public Room currentRoom;
    /**
     * a HashMap object that stores the items in the player's inventory, with the item names as keys
     */
    public HashMap<String, Item> inventory;
    /**
     * a HashMap object that stores the tasks that need to be completed in the game, with the task names as keys
     */
    public HashMap<String, Boolean> taskList;

    /**
     * Constructor to create a new player object.
     *
     * @param startRoom the starting room for the player
     */
    public Player(Room startRoom) {
        this.currentRoom = startRoom;
        this.inventory = new HashMap<String, Item>();
        this.taskList = new HashMap<String, Boolean>();
    }

    /**
     * Adds the specified item to the player's inventory.
     *
     * @param item the item to be added to the inventory
     */
    public void addItem(Item item) {
        inventory.put(item.getName(), item);
    }

    /**
     * Prints all the items in the player's inventory.
     */
    public void printAllItems() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty, go to find something!");
            return;
        }
        System.out.println(inventory.keySet());
    }

    /**
     * Adds tasks that need to be completed to the player's task list.
     *
     * @param tasks the tasks to be added to the task list
     */
    public void addTask(String... tasks) {
        for (String name : tasks) {
            taskList.put(name, false);
        }
    }

    /**
     * Marks the specified task as done in the player's task list.
     *
     * @param task the task to be marked as done
     */
    public void markTaskDone(String task) {
        taskList.put(task, true);
    }

    /**
     * Prints all the tasks that have been completed in the player's task list.
     */
    public void printDoneTasks() {
        System.out.println("Tasks that you have done:");
        boolean allTasksAreFalse = true;
        for (String taskName : taskList.keySet()) {
            boolean isDone = taskList.get(taskName);
            if (isDone) {
                System.out.println(taskName + " ");
                allTasksAreFalse = false;
            }
        }
        if (allTasksAreFalse) {
            System.out.println("You haven't done anything.");
        }
    }

    /**
     * Marks all tasks as done in the player's task list.
     */
    public void cheat() {
        taskList.replaceAll((n, v) -> true);
        System.out.println("You have done everything!");
    }

    /**
     * Shows the directions the player can go in the current room.
     *
     * @return a string containing the directions the player can go in the current room
     */
    public String getDirection() {
        String directions = "";
        if (currentRoom.getwest() != null) {
            directions += " west";
        }
        if (currentRoom.geteast() != null) {
            directions += " east";
        }
        if (currentRoom.getnorth() != null) {
            directions += " north";
        }
        if (currentRoom.getsouth() != null) {
            directions += " south";
        }
        return directions;
    }

    /**
     * Checks the contents of the specified storage item in the current room.
     *
     * @param itemName the name of the storage item to check
     */
    public void check(String itemName) {
        if (this.currentRoom.storageList.get(itemName) == null) {
            System.out.println("There is no such item in this room!");
            return;
        }
        //if the item object is not a storage object
        if (!(this.currentRoom.storageList.get(itemName) instanceof Storage)) {
            System.out.println("Lol it's not a storage!");
        } else {
            ((Storage) this.currentRoom.storageList.get(itemName)).check(this);
        }
    }

    /**
     * The open method allows the player to open a storage object in the game.
     * The method will check if there is a storage object with that name in the current room.
     * If there is, the player will be able to open the storage object by calling the open method on the storage object.
     * If there is no storage object with that name in the current room,
     * or if the object is not a storage object, the player will be informed.
     *
     * @param itemName the name of the storage object the player wants to open
     */
    public void open(String itemName) {
        if (this.currentRoom.storageList.get(itemName) == null) {
            System.out.println("There is no such item in this room!");
            return;
        }
        //if the item object is not a storage object
        if (!(this.currentRoom.storageList.get(itemName) instanceof Storage)) {
            System.out.println("Lol it's not a storage!");
            return;
        }
        ((Storage) this.currentRoom.storageList.get(itemName)).open();
    }

    /**
     * The put method allows the player to put an item from their inventory into the room they are currently in.
     *
     * @param itemName the name of the item to put down
     */
    public void put(String itemName) {
        if (this.inventory.get(itemName) == null) {
            System.out.println("You don't have such item!");
            return;
        }
        this.currentRoom.addItem(this.inventory.get(itemName));
        this.inventory.remove(itemName);
    }

    /**
     * The take method allows the player to take an item from the room they are currently in and add it to their inventory.
     *
     * @param itemName the name of the item to take
     */
    public void take(String itemName) {
        if (this.currentRoom.storageList.get(itemName) == null) {
            System.out.println("There is no such item in this room.");
            return;
        }
        if (!this.currentRoom.storageList.get(itemName).takable) {
            System.out.println("You can't take this item!");
            return;
        }
        System.out.println("You take " + itemName + ".");
        this.addItem(this.currentRoom.storageList.get(itemName));
        this.currentRoom.storageList.remove(itemName);
    }

    /**
     * The wear method allows the player to wear or take off a wearable item from their inventory.
     *
     * @param item  the wearable item to wear or take off
     * @param input a boolean indicating whether the player wants to wear (true) or take off (false) the item
     */

    public void wear(Item item, boolean input) {
        if (!(item instanceof Wearable)) {
            System.out.println("Do you really mean to put a " + item.getName() + " on yourself?!");
            return;
        }
        if (input) {
            if (this.inventory.get(item.getName()) == null) {
                System.out.println("You don't have the " + item.getName() + " in your inventory");
                if (this.taskList.get("wear " + item.getName())) {
                    System.out.println("Because you have already wear the " + item.getName());
                }
                return;
            }
            System.out.println("You wear a " + item.getName() + " now.");
            this.markTaskDone("wear " + item.getName());
            this.inventory.remove(item.getName());
            return;
        }
        if (this.taskList.get("wear " + item.getName())) {
            System.out.println("You take off your " + item.getName() + ".");
            this.addItem(item);
            this.taskList.put("wear " + item.getName(), false);
        } else {
            System.out.println("You are not wearing the " + item.getName());
        }
    }

    /**
     * The dance method allows the player to dance in the dance studio room.
     * If the player is not in the dance studio room, a message is printed indicating this.
     * Otherwise, the player is presented with a series of prompts to perform certain actions within a time limit.
     * If the player successfully completes all the prompts on time, the task of dancing is marked as completed.
     * If the player fails to complete a prompt on time, the task is not completed.
     */
    public void dance() {
        if (this.currentRoom.name.equals("Dance Studio")) {
            System.out.println("You are in the dance training mode! To complete this dance training, you need to make corresponding movements within five seconds according to the prompts. There are eight movements in total.");
            String[] actions = {"left foot forward", "right foot forward", "left foot backward", "right foot backward", "left foot to the side", "right foot to the side", "jump", "spin", "left arm up", "right arm up", "left arm down", "right arm down", "left arm to the side", "right arm to the side", "arm wave", "arm cross"};
            int round = 1;
            int timeLimit = 10; // time limit in seconds
            int totalRounds = 8;
            Scanner scanner = new Scanner(System.in);
            while (round <= totalRounds) {
                Random rand = new Random();
                int actionIndex = rand.nextInt(actions.length);
                String action = actions[actionIndex];
                System.out.println("Perform the following action: " + action);
                long startTime = System.currentTimeMillis();
                String input = scanner.nextLine();
                long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                if (elapsedTime > timeLimit) {
                    System.out.println("Time's up! Dance mission failed.");
                    return;
                }
                if (input.equals(action)) {
                    System.out.println("Correct! Moving on to the next round.");
                    round++;
                } else {
                    System.out.println("Wrong action. Please try again.");
                }
            }
            System.out.println("Dance mission complete!");
            this.markTaskDone("dance");
        } else {
            System.out.println("You can't dance here!");
        }
    }

    /**
     * player does interview task in interview room
     */
    public void interview() {
        if (this.currentRoom.name.equals("Interview Room")) {
            System.out.println("Interview finished!");
            this.markTaskDone("interview");
        } else System.out.println("You can't interview here!");
    }

    /**
     * player does photo task in photography studio
     */
    public void photo() {
        if (this.currentRoom.name.equals("Photography Studio")) {
            System.out.println("Would you like to take a photo? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            input = input.toLowerCase();
            if (input.equals("yes") || input.equals("y") || input.equals("yeah") || input.equals("yep") || input.equals("sure") || input.equals("ok") || input.equals("okay")) {
                this.markTaskDone("take photo");
                //let user type the pose to take the photo
                System.out.println("What pose would you like to take?");
                input = scanner.nextLine();
                System.out.println("You took a photo of yourself in the pose of " + input);
                this.markTaskDone("took a photo of yourself in the pose of " + input);
            } else {
                System.out.println("You don't want to take a photo? You look great!");
            }
        } else {
            System.out.println("You can't take a photo here!");
        }
    }

    /**
     * player drive car in garage and return true to let game over
     */
    public boolean drive() {
        if (this.currentRoom.name.equals("Garage")) {
            if (inventory.get("carKey") != null) {
                return true;
            }
            System.out.println("You don't have a car key!");
            return false;
        } else System.out.println("You can't drive here!");
        return false;
    }

    /**
     * The move method allows the player to move to a different room in the game.* The player can specify the direction they want to go by entering directions.
     * The method will check if the player can go in that direction by checking if there is a room in that direction and if the door to that room is open.
     * If the door is locked, the player will be prompted to try and unlock it with a key.
     * If the player successfully unlocks the door or the door was already open, the player will move to the new room.
     * If the player cannot go in the specified direction or chooses not to unlock a locked door, the player will stay in their current room.
     *
     * @param input       the direction the player wants to go
     * @param keyToLocker a map of keys to the lockers they can unlock
     */
    public void move(String input, HashMap<String, String> keyToLocker) {
        switch (input) {
            case "west", "w" -> input = "west";
            case "east", "e" -> input = "east";
            case "north", "n" -> input = "north";
            case "south", "s" -> input = "south";
        }
        if (this.currentRoom.getRoom(input) == null) {
            System.out.println("You can't go that way!");
            return;
        }
        if (!this.currentRoom.getRoom(input).isOpen) {
            Room room = this.currentRoom.getRoom(input);
            System.out.println("The door is locked! Would you like to open it? (y/n)");
            boolean trying = true;

            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            input = input.toLowerCase();
            if (input.equals("yes") || input.equals("y") || input.equals("yeah") || input.equals("yep") || input.equals("sure") || input.equals("ok") || input.equals("okay")) {
                while (trying) {
                    System.out.println("What key would you like to use?");
                    input = scanner.nextLine();
                    if (this.inventory.get(input) == null) {
                        System.out.println("You don't have that key!");
                        System.out.println("Would you like to try again? (y/n)");
                        input = scanner.nextLine();
                        input = input.toLowerCase();
                        if (input.equals("yes") || input.equals("y") || input.equals("yeah") || input.equals("yep") || input.equals("sure") || input.equals("ok") || input.equals("okay")) {
                            trying = true;
                            continue;
                        } else {
                            trying = false;
                        }
                    } else {
                        if (room.open(keyToLocker.get(((Key) this.inventory.get(input)).keyId), this))
                            return;
                        else {
                            System.out.println("Would you like to try again? (y/n)");
                            input = scanner.nextLine();
                            input = input.toLowerCase();
                            if (input.equals("yes") || input.equals("y") || input.equals("yeah") || input.equals("yep") || input.equals("sure") || input.equals("ok") || input.equals("okay")) {
                                trying = true;
                                continue;
                            } else {
                                trying = false;
                            }
                        }
                    }
                }
            } else {
                System.out.println("Without a key, you can't open the door!");
                return;
            }
            return;
        }
        this.currentRoom = this.currentRoom.getRoom(input);
    }

    /**
     * The talk method allows the player to interact with a person in the game.
     * The method will check if there is a person with that name in the current room.
     * If there is, the player will be able to interact with the person by calling the talk method on the person object.
     *
     * @param personName the name of the person the player wants to talk to
     */
    public void talk(String personName) {
        Person person = this.currentRoom.getPerson(personName);
        if (person == null) {
            System.out.println("There is no such person in this room!");
            return;
        }
        person.talk(this);
        return;
    }

}


