/***
 * A player in the game.
 * 
 * @author David
 */
public class Player {
    public static final int VISIBLE_RANGE = 5;
    private Room currentRoom;				// room player is in
    private Location loc;					// location of the player in the room
    
    public Player(Room r) {
        currentRoom = r;
        loc = new Location(currentRoom.getHeight()/2, currentRoom.getWidth()/2);
        currentRoom.put(loc.row, loc.col, Game.PLAYER);
    }
    
    // returns true if player was able to move in that direction.
    public boolean move(int direction) {
        Location moveTo = Location.locationInDirection(loc, direction);
        
        if (currentRoom.isEmpty(moveTo.row, moveTo.col)) {
            currentRoom.moveElementAt(loc, direction);

            loc = moveTo;   // update own location
            return true;
        }
        
        return false;
    }
    
    public void escape() {
    	Location a;
		currentRoom.put(getLoc().row, getLoc().col, Game.EMPTY);
		do {
			a = currentRoom.getRandomLocation();
		} while (!currentRoom.isEmpty(a));
		setLoc(a);
		currentRoom.put(a.row, a.col, Game.PLAYER);
    }
    
    public String interact(String action, Wumpus enemy) {
		if (Room.areAdjacent(getLoc(), enemy.getLoc())) return "You are " + action + "ing the wumpus!";
		return "You are too far to " + action + "!";
    }

    public Location getLoc() {
		return loc;
	}
    
	public void setLoc(Location loc) {
		this.loc = loc;
	}

}