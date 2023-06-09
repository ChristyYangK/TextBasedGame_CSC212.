# CSC212_GP Reflection Christy

1. Hashmap to store item in player, room, storage, person class, and hashmap to store keys and its lock
   Using a hashmap to store items and their names can be a good choice because I only need to quickly look up, add and
   remove an item by its name. (I add item by its value because an item must has its name according to its constructor,
   I can getname of the item and use it as key)
   - With a hashmap, I can use the item's name as the key and the item itself as the value, to look up an item in
   constant time (O(1)). This is faster than searching through a list, which would require O(n) time in the general
   case.
   - Hashmap will not cause an unexpected exception except return null when looking up a non-existed value, however,
   program may appear IndexError exception when using a list.
   - One of the drawbacks using a hashmap is that it does not maintain the order of the items.But since I didn’t
   implement any functions that required to access items by their position, a list is unnecessary. Another concern is
   that hashmap is slower when iterating through all, however, compare to the huge demand of quick looking up, the time
   waste on printing can be ignored at this time.

2. Graph to connect all rooms and build map. Why I didn’t use Guava packages：
   There are a few potential drawbacks to using the Guava library for graph data structures in this project:
   - This map is strongly depend on the direction relation of each room, but Guava package doesn’t provide a method to
   deal with the directions. When using Guava to connect rooms, program won’t now if styleRoom is on the north side of
   office, instead, program will just know styleRoom is accessible if player is in the office. We will still need an
   additional class to record the direction relations.
   - Using a library like Guava may add additional layers of abstraction that can make it more difficult to understand
   and debug. Since we don’t need the most important part of it : traverse, there is no need for this program to use
   such a big library just for connection.


4. Things I will implement if I have more time:
   - I would consider adding a bit of spice and some side effects to the game, perhaps to make it more challenging, such
   as adding a frustrated dancer to the dance studio, where the player can initiate a conversation and ask him/her if
   he/she needs help and teach him/her a dance. This might help the player get extra honors, or increase the number of
   moves.
   - I’ll also make the conversations between the player and the NPC more varied, perhaps importing some of the existing
   NPC conversation data so that the NPC can recognize more of the player's messages.
   - I’ll make it possible for the program to handle more user input, such as calling player.wear(item) when the user
   types "wear officeKey"

5. The way I approached this project：
   The way I approached this project was to build a prototype of the game on paper, including maps, character actions,
   objects, and NPCS, and then to implement the game from the creation of the rooms and maps, and finally to the
   construction of the details.

6. new thing(s) I learn / figure out in completing this project：
   This was my first time to create a project completely from scratch, and I felt that I gradually understood OOP
   methods, which allowed me to assign classes and methods for each class properly, and make each class work
   harmoniously. What made me most happy was that I learned some java packages on my own, such as UUID and timer, while
   trying to implement some gameplay processes.

7. Is there anything that you wish you had implemented differently:
   I might write a separate method for reading user input. All user input should be translated into a uniform format and then used as parameters.
   I also wish I could rewrite the method of the put and take actions so that putting something into room and putting something into storage item could be handled by a function
8. If you could go back in time and give your past self some advice about this project, what hints would you give?
   Don't hesitate to try to implement the most basic actions before considering the rest.
   Write the javadoc review as writing the function.....

