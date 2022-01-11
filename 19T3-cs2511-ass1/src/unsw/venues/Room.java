package unsw.venues;

public class Room {
	String name;
	String size;
	
	/**
	 * Constructor
	 * @param name - room name
	 * @param size - room size (small, medium, or large)
	 */
	public Room(String name, String size) {
		super();
		this.name = name;
		this.size = size;
	}

	@Override
	public String toString() {
		//String message = "name: " + name + " size: " + size;
		String message = name;
		return message;
	}
	
}
