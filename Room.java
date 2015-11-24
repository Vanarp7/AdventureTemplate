import java.util.ArrayList;

/***
 * A room in the game. A room contains a 2d int array to represent the things in
 * the room. See the Game class for int constants that represent different game
 * things. For example: Game.WUMPUS = 3; These are the values in the room int
 * array.
 * 
 * @author David
 */
public class Room {
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	Player player = new Player(this);
	private Entity[][] room; 					// 2d grid for the room

	public Room(int w, int h) {
		room = new Entity[h][w];
		addBorder();
	}

	// Adds a border of wall objects around the edges of the room
	private void addBorder() {
		for (int i = 0; i < room.length; i++) {
			addWall(i, 0);
			addWall(i, room[0].length - 1);
		}
		for (int j = 0; j < room[0].length; j++) {
			addWall(0, j);
			addWall(room.length - 1, j);
		}
	}
	
	public void addWall(int row, int col) {
		addWall(new Location(row, col));
	}
	
	public void addWall(Location loc) {
		put(loc, new Wall(this));
	}

	/**
	 * Returns the value of (row, col) in the room.  Possible values include
	 * Game.WUMPUS, Game.WALL, Game.PLAYER, Game.EMPTY, Game.INVALID
	 * 
	 * @param row the row in the room grid
	 * @param col the column in the room grid
	 * @return what is at that location in the room.
	 */
	public Entity get(Location loc) {
		if(isInRoom(loc)) return room[loc.row][loc.col];
		else return null;
	}

	/**
	 * Put a new value into the room grid at (row, col).  Possible values include
	 * Game.WUMPUS, Game.WALL, Game.PLAYER, Game.EMPTY
	 * 
	 * @param row the row to place the new value 
	 * @param col the column to place the new value
	 * @param value the value to be placed at (row, col)
	 */
	public void put(Location loc, Entity object) {
		if (isInRoom(loc) && isEmpty(loc)) {
			Entity newObject = new Entity(object.getLoc(), this);
			room[loc.row][loc.col] = newObject;
			if(object instanceof Enemy) enemies.add((Enemy) newObject);
			else if(object instanceof Wall) walls.add((Wall) newObject);
			else if(object instanceof Player) player = (Player) newObject;
		}
	}

	/**
	 * Return a string representation for the room.  This is what actually
	 * gets displayed by the GUI class and what the user sees.
	 */
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int r = 0; r < room.length; r++) {
			for (int c = 0; c < room[0].length; c++) {
				Location loc = new Location(r, c);
				if(getDistance(loc, player.getLoc()) > player.getSight() && !(room[r][c] instanceof Wall))
					b.append("");
				else b.append(room[r][c].getSymbol());
			}
			b.append("\n");
		}
		return b.toString();
	}

	int getWidth() {
		return room[0].length;
	}

	int getHeight() {
		return room.length;
	}

	// return true if (row, col) is Game.EMPTY
	boolean isEmpty(int row, int col) {
		return get(new Location(row, col)) == null;
	}

	// return true if Location loc is Game.EMPTY
	boolean isEmpty(Location loc) {
		return isEmpty(loc.row, loc.col);
	}

	// Move the element at Location loc in the direction
	// This places the element at the new location and sets
	// the previous location to Game.EMPTY
	void moveElementAt(Location loc, int direction) {
		if (!isInRoom(loc)) return;
		Location moveTo = Location.locationInDirection(loc, direction);
		if (!isInRoom(moveTo)) return;

		room[moveTo.row][moveTo.col] = room[loc.row][loc.col]; // move thing
		room[loc.row][loc.col] = null; // old square empty
	}
	
	public static boolean areAdjacent(Location a, Location b){
		int rowDiff = Math.abs(a.row - b.row);
		int colDiff = Math.abs(a.col - b.col);
		return rowDiff <= 1 && colDiff <= 1;
	}

	// return true if (row, col) is a valid location in the room
	public boolean isInRoom(int row, int col) {
		if (row < 0 || col < 0) return false;
		else if (row >= room.length) return false;
		else if (col >= room[0].length) return false;
		else return true;
	}

	public boolean isInRoom(Location loc) {
		return isInRoom(loc.row, loc.col);
	}

	// return a random location in the room
	public Location getRandomEmptyLocation() {
		int row, col;
		do {
			row = (int) (Math.random() * getHeight());
			col = (int) (Math.random() * getWidth());
		} while(!isEmpty(row, col));
		return new Location(row, col);
	}
	
	public static double getDistance(Location a, Location b) {
		double rowDiff = a.row - b.row;
		double colDiff = a.col - b.col;
		return Math.sqrt(rowDiff * rowDiff + colDiff * colDiff);
	}	
}