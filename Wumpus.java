/***
 * An enemy which can occupy a location in a room and is not to be touched.
 * 
 * @author David
 */
public class Wumpus extends Enemy{
    
    public Wumpus(Room room) {
    	super(room, "W");
    }
    
    public Wumpus(Location loc, Room room) {
    	super(loc, room, "W");
    }

    public void move() {
    	randomMove();
    }
    
    // returns true if enemy was able to move in that direction.
    public void move(int direction) {
        setLoc(Location.locationInDirection(getLoc(), direction));
    }
    
    public void moveAwayFromPlayer() {
    	double mostDist = Double.MIN_VALUE;
    	int index = 0;
    	
    	for(int i = 0; i < 4; i++) {
    		Location loc = Location.locationInDirection(getLoc(), i);
    		if(room.isEmpty(loc)) {
        		double distance = Room.getDistance(player.getLoc(), loc);
        		if(distance > mostDist) {
        			mostDist = distance;
        			index = i;
        		}
    		}
    	}
    	move(index);
    }
    
    public void moveTowardPlayer() {
    	double leastDist = Double.MAX_VALUE;
    	int index = 0;
    	
    	for(int i = 0; i < 4; i++) {
    		Location loc = Location.locationInDirection(getLoc(), i);
    		if(room.isEmpty(loc)) {
        		double distance = Room.getDistance(player.getLoc(), loc);
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