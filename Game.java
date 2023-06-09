import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

/**
 * The Game class represents a game where the player has to complete certain tasks within a limited number of moves.
 * The game is played by calling the play() method.
 *
 * @author Christy Yang
 */
public class Game {
    /**
     * Plays the game by calling sampleRound to set up and begin game loop
     * If the player runs out of moves, they lose the game and a message is printed.
     * If the player completes all tasks within the allotted moves, they win the game and a message is printed.
     */
    public void play() {
        int limit = sampleRound();
        if (limit <= 0) {
            System.out.println("===== You lost === Remaining moves: " + limit + " =====");
            System.out.println("You have run out of moves and haven’t completed the tasks. You now have to call your friend, explaining you have to cancel just like you have so many times in the past. You feel nervous calling them, but you have to. \n" +
                    "\n" +
                    "You work up the courage to and they start yelling at you, calling you a bad friend and asking you not to speak to them for a while. This was their last straw.\n");
        } else {
            System.out.println("===== You win === Remaining moves: " + limit + " =====");
            System.out.println("Congratulations! You had fun with your friends tonight. They haven’t seen you in so long and it was so refreshing to see their faces again. Although you couldn’t drink with them like you used to, you could still be in their presence. That was more than enough for you after the years of isolation you went through as a group member in your previous group. Now, you feel ready to really take on your solo career. \n" +
                    "\n" +
                    "Thank you for playing!\n");
        }
    }

    /**
     * Sets up the rooms, player, keys, and storages, and starts the game loop.
     *
     * @return the number of moves the player has remaining when the game ends
     */
    public int sampleRound() {
        //initialize rooms
        HashMap<String, String> keyToLocker = new HashMap<String, String>();
        Room frontDesk = new Room("Front Desk", true);
        Room garage = new Room("Garage", false);
        Room danceStudio = new Room("Dance Studio", true);
        Room PS = new Room("Photography Studio", true);
        Room IntervRoom = new Room("Interview Room", true);
        Room styleRoom = new Room("Style Room", false);
        Room storageRoom = new Room("Storage Room", false);
        Room mOffice = new Room("Manager Office", true);
        Room pOffice = new Room("Your Office", false);
        westeast(frontDesk, garage);
        westeast(garage, danceStudio);
        westeast(PS, IntervRoom);
        westeast(IntervRoom, styleRoom);
        westeast(storageRoom, mOffice);
        westeast(mOffice, pOffice);
        northsouth(frontDesk, PS);
        northsouth(garage, IntervRoom);
        northsouth(danceStudio, styleRoom);
        northsouth(PS, storageRoom);
        northsouth(IntervRoom, mOffice);
        northsouth(styleRoom, pOffice);
        frontDesk.setDescription("This is where the front desk of the building is. There is a receptionist at the counter");
        garage.setDescription("Your car is here. It takes most of the space in the garage.");
        danceStudio.setDescription("This dance studio has big windows illuminating the wooden dance floor with natural light. There are big mirrors on one side of the room. You can dance in here.");
        PS.setDescription("The studio is almost white all over with tall lighting fixtures pointing towards the middle. It seems like a good place to take a photo");
        IntervRoom.setDescription("There's a magazine interview waiting for you. If you are ready, interview.");
        styleRoom.setDescription("There’s racks of clothing along the walls and shoes all over the floor. There are also huge closets.This is a good place to find a new outfit");
        storageRoom.setDescription("It’s pretty dusty here. Many storage boxes are on the floor");
        mOffice.setDescription("Your manager's office is cozy yet large. He is sitting here.");
        pOffice.setDescription("This is your personal room. A small, intimate room with a few personal photos and some snacks. The desk is quite messy.");
        //initialize player
        Player player = new Player(frontDesk);
        player.addTask("wear mask", "dance", "wear outfit", "take photo", "took a photo of yourself in the pose of hands over the face", "interview");

        //initialize keys
        Key myOfficeKey = createKeyToLocker(pOffice, keyToLocker, "officeKey");
        Key storageKey = createKeyToLocker(storageRoom, keyToLocker, "storageKey");
        Key styleRoomKey = createKeyToLocker(styleRoom, keyToLocker, "styleRoomKey");
        Key garageKey = createKeyToLocker(garage, keyToLocker, "garageKey");

        player.addItem(myOfficeKey);

        //initialize Storages in Storage room
        Storage managerStorage = new Storage("managerStorage", true);
        Storage receptionistStorage = new Storage("receptionistStorage", true);
        Storage danceStudioStorage = new Storage("danceStudioStorage", true);
        Storage photographyStudioStorage = new Storage("photographyStudioStorage", true);
        Storage interviewRoomStorage = new Storage("interviewRoomStorage", true);
        Storage styleRoomStorage = new Storage("styleRoomStorage", true);
        Storage yourOfficeStorage = new Storage("yourOfficeStorage", true);
        Storage garageStorage = new Storage("garageStorage", true);
        styleRoomStorage.addItem(styleRoomKey);
        storageRoom.addItem(managerStorage);
        storageRoom.addItem(receptionistStorage);
        storageRoom.addItem(danceStudioStorage);
        storageRoom.addItem(photographyStudioStorage);
        storageRoom.addItem(interviewRoomStorage);
        storageRoom.addItem(styleRoomStorage);
        storageRoom.addItem(yourOfficeStorage);
        storageRoom.addItem(garageStorage);

        //initialize receptionist with requiredItem as player's office key
        Person receptionist = new Person("receptionist", "Welcome, I'm happy to help you, but I need something to confirm your identity before I give you the storage Key");
        receptionist.addItem(storageKey);
        frontDesk.addPerson(receptionist);

        //initialize manager to give the car key
        Person manager = new Person("manager", "Look who it is, our star! Have you completed all your schedule today？");
        mOffice.addPerson(manager);
        manager.addItem(garageKey);

        //create a outfit for styleRoom
        Storage closet = new Storage("closet", true);
        Wearable outfit = new Wearable("outfit");
        closet.addItem(outfit);
        styleRoom.addItem(closet);

        //initialize your office
        String carKey = UUID.randomUUID().toString();
        String carLockerId = UUID.randomUUID().toString();
        keyToLocker.put(carKey, carLockerId);
        Key carKeyItem = new Key("carKey", carKey);
        Storage desk = new Storage("desk", false);
        desk.addItem(carKeyItem);
        Wearable mask = new Wearable("mask");
        desk.addItem(mask);
        pOffice.addItem(desk);
        Item car = new Item("car");
        car.setTakable(false);
        garage.addItem(car);


        int limit = 0;
        //let the user select hard or easy mode
        System.out.println("Welcome to An Idol's Day! You're an idol and you want to hang out with your friends, but unfortunately, you have to come to the agency and finish your work and get permission from the manager before you can hang out with your friends.");
        Scanner modeScanner = new Scanner(System.in);
        System.out.println("Please select a mode: easy, medium, or hard");
        String mode = modeScanner.nextLine();
        switch (mode) {
            case "easy" -> limit = 121;
            case "medium" -> limit = 71;
            case "hard" -> limit = 51;
            default -> {
                System.out.println("Invalid mode, please select again");
                return sampleRound();
            }
        }


        while (limit > 0) {
            limit--;
            System.out.println("===== You are in " + player.currentRoom.name + " === Remaining moves: " + limit + " =====");
            System.out.println(player.currentRoom.description);
            // Get user input
            System.out.println("What would you like to do?");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            // Check user input
            if (input.split(" ").length == 0) {
                input = input.toLowerCase();
            }
            switch (input) {
                case "west":
                case "east":
                case "north":
                case "south":
                case "w":
                case "e":
                case "n":
                case "s":
                    player.move(input, keyToLocker);
                    break;
                case "look":
                    player.currentRoom.showItems();
                    player.currentRoom.showPeople();
                    break;
                case "take":
                case "get":
                    System.out.println("Take what?");
                    break;
                case "put":
                    System.out.println("Put what?");
                    break;
                case "open":
                    System.out.println("Open what?");
                    break;
                case "talk":
                    System.out.println("Talk to who?");
                    break;
                case "check":
                    System.out.println("Check what?");
                    break;
                case "wear":
                    System.out.println("Wear what?");
                    break;
                case "dress":
                    System.out.println("Dress what?");
                    break;
                case "photo":
                    player.photo();
                    break;
                case "list":
                    player.printDoneTasks();
                    break;
                case "cheat12345":
                    player.cheat();
                    break;
                case "help":
                case "h":
                    help(mode);
                    break;
                case "directions":
                    System.out.println("You can go:" + player.getDirection());
                    break;
                case "inventory":
                case "i":
                    player.printAllItems();
                    break;
                case "dance":
                    player.dance();
                    break;
                case "interview":
                    player.interview();
                    break;
                case "drive":
                case "drive car":
                    if (player.drive()) return limit;
                    break;
                case "dress new outfit":
                case "dress outfit":
                case "wear new outfit":
                case "wear outfit":
                    player.wear(outfit, true);
                    break;
                case "dress mask":
                case "wear mask":
                case "put mask on":
                case "put on mask":
                    player.wear(mask, true);
                    break;
                case "take off outfit":
                    player.wear(outfit, false);
                    break;
                case "take off mask":
                    player.wear(mask, false);
                    break;
                default:
                    if (input.matches("put [a-zA-Z]+")) {
                        String[] words = input.split(" ");
                        String itemName = words[1];
                        player.put(itemName);
                        break;
                    } else if (input.matches("take [a-zA-Z]+") || input.matches("get [a-zA-Z]+")) {
                        String[] words = input.split(" ");
                        String itemName = words[1];
                        player.take(itemName);
                        break;
                    } else if (input.matches("talk [a-zA-Z]+")) {
                        String[] words = input.split(" ");
                        String personName = words[1];
                        player.talk(personName);
                        break;
                    } else if (input.matches("check [a-zA-Z]+")) {
                        String[] words = input.split(" ");
                        String itemName = words[1];
                        player.check(itemName);
                        break;
                    } else if (input.matches("open [a-zA-Z]+")) {
                        String[] words = input.split(" ");
                        String itemName = words[1];
                        player.open(itemName);
                        break;
                    } else {
                        System.out.println("Invalid Input!");
                    }
            }
        }
        return limit;
    }

    /**
     * Connect two rooms in the direction of west and east
     *
     * @param room1 east room
     * @param room2 weat room
     */
    public void westeast(Room room1, Room room2) {
        room1.seteast(room2);
        room2.setwest(room1);
    }

    /**
     * Connect two rooms in the direction of south and north
     *
     * @param room1 south room
     * @param room2 north room
     */
    public void northsouth(Room room1, Room room2) {
        room1.setsouth(room2);
        room2.setnorth(room1);
    }

    /**
     * print possible movement for players
     *
     * @param mode the mode that player chosen
     */
    public void help(String mode) {
        if (mode.equals("easy")) {
            System.out.println("You can use the following commands:");
            System.out.println("north, south, east, west, look, take, put, open, talk, check, wear, dress, dance, interview, drive, inventory, directions, help,take off");
        } else {
            System.out.println("You cannot see the commands in medium/hard mode!");
        }
    }

    /**
     * Lock room with paired ids of lock and key of the room
     *
     * @param room        the room to be locked
     * @param keyToLocker the hashmap stored paired ids of lock and key of the room
     * @param descrip     Key's name
     * @return the key to the specific room while add the key-locker pair to the hashmap
     */
    public Key createKeyToLocker(Room room, HashMap<String, String> keyToLocker, String descrip) {
        String keyId = UUID.randomUUID().toString();
        String lockerId = UUID.randomUUID().toString();
        keyToLocker.put(keyId, lockerId);
        Key key = new Key(descrip, keyId);
        room.setId(lockerId);
        return key;
    }

}