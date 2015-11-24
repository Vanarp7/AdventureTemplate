/***
 * An enemy which can occupy a location in a room and is not to be touched.
 * 
 * @author David
 */
public class Wumpus {
    private Room currentRoom;		// room the Wumpus is in
    private int row, col;			// the location of the wumpus in the room
    
    public Wumpus(Room r) {
        currentRoom = r;      
        row = 6;
        col = 8;
        currentRoom.put(row, col, Game.WUMPUS);	
    }
    
    public void move() {
    	randomMove();
    }
    
    // returns true if enemy was able to move in that direction.
    public boolean move(int direction) {
        int newrow = row;
        int newcol = col;
        
        if (direction == Location.NORTH) newrow--;
        if (direction == Location.SOUTH) newrow++;
        if (direction == Location.EAST) newcol++;
        if (direction == Location.WEST) newcol--;
        
        if (currentRoom.isEmpty(newrow, newcol)) {
            currentRoom.put(row, col, Game.EMPTY);
            currentRoom.put(newrow, newcol, Game.WUMPUS);
            row = newrow;
            col = newcol;
            return true;
        }
        
        return false;
    }
    
    public Location getLoc() {
    	return new Location(row,col);
    }
    
    public void moveAwayFromPlayer() {
    	Location playerLoc = currentRoom.getPlayerLoc();
    	double mostDist = Double.MIN_VALUE;
    	int index = 0;
    	
    	for(int i = 0; i < 4; i++) {
    		Location loc = Location.locationInDirection(getLoc(), i);
    		if(currentRoom.isEmpty(loc)) {
        		double distance = Room.getDistance(playerLoc, loc);
        		if(distance > mostDist) {
        			mostDist = distance;
        			index = i;
        		}
    		}
    	}
    	move(index);
    }
    
    public void moveTowardPlayer() {
    	Location playerLoc = currentRoom.getPlayerLoc();
    	double leastDist = Double.MAX_VALUE;
    	int index = 0;
    	
    	for(int i = 0; i < 4; i++) {
    		Location loc = Location.locationInDirection(getLoc(), i);
    		if(currentRoom.isEmpty(loc)) {
        		double distance = Room.getDistance(playerLoc, loc);
        		if(distance < leastDist) {
        			leastDist = distance;
        			index = i;
        		}
    		}
    	}
    	move(index);
    }
    
    public void randomMove() {
        move((int) (Math.random()*4));
    }
    
}