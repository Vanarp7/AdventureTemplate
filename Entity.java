public class Entity {
	protected Room room;
	private Location loc;
	private String symbol;
	
	public Entity(Room room) {
		this.room = room;
		setLoc(room.getRandomEmptyLocation());
	}
	
	public Entity(Room room, Location loc) {
		this.room = room;
		setLoc(loc);
	}
	
	public Entity(Room room, String symbol) {
		this.room = room;
		setLoc(room.getRandomEmptyLocation());
		this.symbol = symbol;
	}

	public Entity(Room room, Location loc, String symbol) {
		this.room = room;
		setLoc(loc);
		this.symbol = symbol;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location newLoc) {
		if(room.isEmpty(newLoc)) {
			if(loc != null) room.put(loc, null);
			loc = new Location(newLoc.row, newLoc.col);
			room.put(newLoc, this);
		}
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getSymbol() {
		return symbol;
	}
}