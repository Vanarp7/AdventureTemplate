public class Entity {
	private String symbol;
	private Location loc;
	protected Room room;
	
	public Entity(Room room) {
		setLoc(room.getRandomEmptyLocation());
		this.room = room;
	}
	
	public Entity(Location loc, Room room) {
		setLoc(loc);
		this.room = room;
	}
	
	public Entity(Room room, String symbol) {
		setLoc(room.getRandomEmptyLocation());
		this.room = room;
		this.symbol = symbol;
	}

	public Entity(Location loc, Room room, String symbol) {
		setLoc(loc);
		this.room = room;
		this.symbol = symbol;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location newLoc) {
		if(this.loc != null) room.put(loc, null);
		loc = new Location(newLoc.row, newLoc.col);
		room.put(newLoc, this);
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