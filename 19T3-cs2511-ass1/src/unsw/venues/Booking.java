package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
	String id;
	Venue venue;
	List<Room> rooms = new ArrayList<Room>();
	LocalDate start_date;
	LocalDate end_date;
	
	/**
	 * Constructor (no rooms)
	 * @param id - id of booking
	 * @param venue - booking venue
	 * @param start_date - start date of booking
	 * @param end_date - end_date of booking
	 * 
	 * returns a booking
	 */
	public Booking(String id, Venue venue, LocalDate start_date, LocalDate end_date) {
		super();
		this.id = id;
		this.venue = venue;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	/**
	 * Method - adds a room to Booking
	 * @param r - room to be added to Booking
	 */
	public void addRoom(Room r) {
		this.rooms.add(r);
	}

	@Override
	public String toString() {
		System.out.println("id: " + id + "\n" + "venue: " + venue.name + "\n" + "rooms: " + rooms + "\n" + "start: " + start_date + "\n" + "end: " + end_date);
		return "";
	}
	
}
