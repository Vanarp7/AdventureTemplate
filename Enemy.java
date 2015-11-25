public class Enemy extends Animate{
	protected Player player;
	
	public Enemy(Room room) {
		super(room);
	}
	
	public Enemy(Room room, Location loc) {
		super(room, loc);
	}
	
	public Enemy(Room room, String symbol) {
		super(room, symbol);
	}
	
	public Enemy(Room room, Location loc, String symbol) {
		super(room, loc, symbol);
	}


}