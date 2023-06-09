/**
 * The `Main` class represents the entry point of the game.
 * It has a single `main` method that constructs a new `Game` and starts it.
 *
 * @author [Christy Yang]
 */
public class Main {
    /**
     * The entry point of the game.
     * Constructs a new `Game` and starts it.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}

