public class Animate extends Entity {
	boolean alive;
	
	public Animate(Room room) {
		super(room);
		alive = true;
	}

	public Animate(Location loc, Room room) {
		super(loc, room);
		alive = true;
	}

	public Animate(Room room, String symbol) {
		super(room, symbol);
		alive = true;
	}

	public Animate(Location loc, Room room, String symbol) {
		super(loc, room, symbol);
		alive = true;
	}

}