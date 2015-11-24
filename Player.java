/***
 * A player in the game.
 * 
 * @author David
 */
public class Player extends Animate{
    private int sight;
    
    public Player(Room room) {
    	super(room, "*");
    }
    
    public Player(Location loc, Room room) {
    	super(loc, room, "*");
    }

    // returns true if player was able to move in that direction.
    public void move(int direction) {
        setLoc(Location.locationInDirection(getLoc(), direction));
    }
    
    public void escape() {
    	Location a = room.getRandomEmptyLocation();
    	setLoc(a);
    }
    
    public String interact(String action, Wumpus enemy) {
		if (Room.areAdjacent(getLoc(), enemy.getLoc())) return "You are " + action + "ing the wumpus!";
		return "You are too far to " + action + "!";
    }

	public int getSight() {
		return sight;
	}

}