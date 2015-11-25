public class Wall extends Entity {
	
	public Wall(Room room) {
		super(room, "X");
	}

	public Wall(Room room, Location loc) {
		super(room, loc, "X");
	}
}
