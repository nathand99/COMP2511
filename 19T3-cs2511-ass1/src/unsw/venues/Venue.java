package unsw.venues;

import java.util.ArrayList;
import java.util.List;

public class Venue {
	String name;
	List<Room> rooms = new ArrayList<Room>();
	int small = 0;
	int medium = 0;
	int large = 0;
		
	
	/**
	 * constructor - creates an empty venue, only setting name
	 * @param name - name of venue
	 */
	public Venue(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * adds a room to venue and increments corresponding room size counter for venue
	 * @param new_room - room to be added
	 * @param size - room size (small, medium, or large)
	 * 
	 * @return void
	 */
	public void addRoom(Room new_room, String size) {
		rooms.add(new_room);
		switch (size) {
        case "small":
        	this.small++;
        	break;
        case "medium":
        	this.medium++;
        	break;
        case "large":
        	this.large++;
        	break;
		}
	}

	@Override
	public String toString() {
		int length = rooms.size();
		for ( int i = 0; i < length ; i++ ) {
    		System.out.println(rooms.get(i));
		}
		System.out.println(small);
		System.out.println(medium);
		System.out.println(large);
		return "";
	}
	
}
