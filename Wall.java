public class Wall extends Entity {
	
	public Wall(Room room) {
		super(room, "X");
	}

	public Wall(Location loc, Room room) {
		super(loc, room, "X");
	}
}
