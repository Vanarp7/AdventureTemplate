public class Enemy extends Animate{
	protected Player player;
	
	public Enemy(Room room) {
		super(room);
	}
	
	public Enemy(Location loc, Room room) {
		super(loc, room);
	}
	
	public Enemy(Room room, String symbol) {
		super(room, symbol);
	}
	
	public Enemy(Location loc, Room room, String symbol) {
		super(loc, room, symbol);
	}


}