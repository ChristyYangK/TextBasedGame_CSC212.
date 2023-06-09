import java.util.HashMap;

/**
 * A class representing a room to be ready to connected into map
 *
 * @author Christy
 */
public class Room {
    /**
     * Room's description
     */
    public String description;
    /**
     * The room to the west of this room
     */
    public Room west;
    /**
     * The room to the east of this room
     */
    public Room east;
    /**
     * The room to the north of this room
     */
    public Room north;
    /**
     * The room to the south of this room
     */
    public Room south;
    /**
     * the name of this room
     */
    public String name;
    /**
     * the room is locked or not
     */
    public boolean isOpen;
    /**
     * the id of the lock of this room
     */
    public String lockId;
    /**
     * the storage item in this room
     */
    public HashMap<String, Item> storageList;
    /**
     * the people in this room
     */
    public HashMap<String, Person> peopleList;

    /**
     * constructor to initialize a room
     *
     * @param name   of this room
     * @param isOpen if this room is open
     */
    public Room(String name, boolean isOpen) {
        this.name = name;
        this.storageList = new HashMap<>();
        this.peopleList = new HashMap<>();
        this.isOpen = isOpen;
    }

    /**
     * Adds the item to this room, using the item's name as the key
     *
     * @param item to be added into this room
     */
    public void addItem(Item item) {
        storageList.put(item.getName(), item);
    }

    /**
     * Adds the person to this room, using the person's name as the key
     *
     * @param person to be added into this room
     */
    public void addPerson(Person person) {
        peopleList.put(person.getName(), person);
    }

    /**
     * Checks if the person in is room;Talks to the person
     *
     * @param personName name
     * @return person's name if the person is in this room; null if not
     */
    public Person getPerson(String personName) {
        if (peopleList.get(personName) != null) {
            System.out.println("You are talking to " + personName + "!");
        }
        return peopleList.get(personName);
    }

    /**
     * set room's description
     * @param description of this room
     */
    public void setDescription(String description){
        this.description=description;
    }

    /**
     * set id of the lock of this room
     *
     * @param id lock id
     */
    public void setId(String id) {
        this.lockId = id;
    }

    /**
     * Sets the room to the west of this room
     *
     * @param west the room to the west of this room
     */
    public void setwest(Room west) {
        this.west = west;
    }

    /**
     * Sets the room to the east of this room
     *
     * @param east the room to the east of this room
     */
    public void seteast(Room east) {
        this.east = east;
    }

    /**
     * Sets the room to the north of this room
     *
     * @param north the room to the north of this room
     */
    public void setnorth(Room north) {
        this.north = north;
    }

    /**
     * Sets the room to the south of this room
     *
     * @param south the room to the south of this room
     */
    public void setsouth(Room south) {
        this.south = south;
    }

    /**
     * @return the room to the west of this room
     */
    public Room getwest() {
        return west;
    }

    /**
     * @return the room to the east of this room
     */
    public Room geteast() {
        return east;
    }

    /**
     * @return the room to the north of this room
     */
    public Room getnorth() {
        return north;
    }

    /**
     * @return the room to the south of this room
     */
    public Room getsouth() {
        return south;
    }

    /**
     * read user input to get the room to the certain direction of this room
     *
     * @param direction user input direction
     * @return get the room to the certain direction of this room
     */
    public Room getRoom(String direction) {
        return switch (direction) {
            case "west" -> west;
            case "east" -> east;
            case "north" -> north;
            case "south" -> south;
            default -> null;
        };
    }

    /**
     * Opens the locked room with key
     *
     * @param keyId  of this room's lock
     * @param player the player
     * @return if the room is open
     */
    public boolean open(String keyId, Player player) {
        if (keyId.equals(lockId)) {
            this.isOpen = true;
            System.out.println("*** You opened the door! ***");
            player.currentRoom = this;
            return true;
        } else {
            System.out.println("*** Wrong key! ***");
            return false;
        }
    }

    /**
     * Print itemList in this room
     */
    public void showItems() {
        if (storageList.isEmpty()) {
            System.out.println("There is no item in this room.");
        } else {
            System.out.print("Items in this room: ");
            System.out.println(storageList.keySet());
            System.out.println();
        }
    }

    /**
     * Print peopleList in this room
     */
    public void showPeople() {
        if (peopleList.isEmpty()) {
            System.out.println("There is no people in this room.");
        } else {
            System.out.print("People in this room: ");
            System.out.println(peopleList.keySet());
            System.out.println();
        }
    }


}
