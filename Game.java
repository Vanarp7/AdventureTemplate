import javax.swing.JTextArea;

/***
 * Represents the entire game.
 * 
 * @author David
 */
public class Game {
	public enum KeyAction {
		RIGHT, LEFT, UP, DOWN
	};

	// possible values for locations in a Room object
	static final int INVALID = -1;
	static final int EMPTY = 0;
	static final int INVISIBLE = 1;
	static final int PLAYER = 2;
	static final int WALL = 3;
	static final int WUMPUS = 4;

	private Room currentRoom;
	private Player player;
	private Wumpus enemy;

	private JTextArea display;

	public Game(JTextArea display) {
		this.display = display;
		currentRoom = new Room(60, 20);
		player = new Player(currentRoom);
		enemy = new Wumpus(currentRoom);

		displayWelcome();
	}

	/**
	 * Performs the appropriate action in response to a keyboard press action.
	 * 
	 * @param the
	 *            key that was pressed which we want to perform an action for.
	 */
	public void handleEvent(KeyAction e) {		
		if (e == KeyAction.RIGHT) {
			player.move(Location.EAST);
			enemy.move();
		} else if (e == KeyAction.LEFT) {
			player.move(Location.WEST);
			enemy.move();
		} else if (e == KeyAction.UP) {
			player.move(Location.NORTH);
			enemy.move();
		} else if (e == KeyAction.DOWN) {
			player.move(Location.SOUTH);
			enemy.move();
		}
	}

	/**
	 * Performs an action for a command typed at the game console.
	 * 
	 * @param cmd
	 *            the string the user typed at the game console.
	 */
	public void handleCommand(String cmd) {
		if (cmd.contains("go right")) handleEvent(KeyAction.RIGHT);
		else if (cmd.contains("go left")) handleEvent(KeyAction.LEFT);
		else if (cmd.contains("go up")) handleEvent(KeyAction.UP);
		else if (cmd.contains("go down")) handleEvent(KeyAction.DOWN);
		else if (cmd.contains("escape")) player.escape();
		else if (cmd.contains("look")) display("DON'T TOUCH THE WUMPUS!!");
		else if (cmd.contains("attack")) display(player.interact("attack", enemy));
		else if (cmd.contains("love")) display(player.interact("lov", enemy));
		else display("I don't know what you mean...");
	}

	private void displayWelcome() {
		display.append("Welcome to our AMAZING TEXT ADVENTURE!\n");
		display.append("Use the arrow keys to navigate\n\n");
		display.append("Don't touch the wumpus");
	}

	public void display(String msg) {
		display.append(msg + "\n");
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}
	
}