public class Animate extends Entity {
	boolean alive;
	
	public Animate(Room room) {
		super(room);
		alive = true;
	}

	public Animate(Room room, Location loc) {
		super(room, loc);
		alive = true;
	}

	public Animate(Room room, String symbol) {
		super(room, symbol);
		alive = true;
	}

	public Animate(Room room, Location loc, String symbol) {
		super(room, loc, symbol);
		alive = true;
	}

}