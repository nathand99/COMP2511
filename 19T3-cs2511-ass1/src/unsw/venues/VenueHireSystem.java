/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author z5204935
 *
 */

public class VenueHireSystem {
	List<Venue> venues;
	List<Booking> bookings;
    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
    public VenueHireSystem() {
    	this.venues = new ArrayList<Venue>();
    	this.bookings = new ArrayList<Booking>();
    }

    private void processCommand(JSONObject json) {
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            String id = json.getString("id");
            LocalDate start = LocalDate.parse(json.getString("start"));
            LocalDate end = LocalDate.parse(json.getString("end"));
            int small = json.getInt("small");
            int medium = json.getInt("medium");
            int large = json.getInt("large");

            JSONObject result = request(id, start, end, small, medium, large);
            System.out.println(result.toString(2));
            break;

        case "change":
        	String change_id = json.getString("id");
            LocalDate change_start = LocalDate.parse(json.getString("start"));
            LocalDate change_end = LocalDate.parse(json.getString("end"));
            int change_small = json.getInt("small");
            int change_medium = json.getInt("medium");
            int change_large = json.getInt("large");
        	// delete old booking
            cancel(change_id);
        	// make new booking with same id
            JSONObject change_result = request(change_id, change_start, change_end, change_small, change_medium, change_large);
            System.out.println(change_result.toString(2));
        	break;
        	
        case "cancel":
        	String cancel_id = json.getString("id");
        	cancel(cancel_id);
        	break;
        	
        case "list":
        	String venue_list = json.getString("venue");
        	JSONArray list = list(venue_list);
        	System.out.println(list.toString(2));
        	break;    	
        }
    }
    
    /**
     * Adds a room to a venue. If venue doesn't exist, it is created
     * @param venue - venue for room to be added to
     * @param room - name of room to be added
     * @param size - room size (small, medium, or large)
     */
    private void addRoom(String venue, String room, String size) {
    	// if this venue exists, then use use this venue
    	Venue new_venue = null;
    	int length = 0;
    	if (!venues.isEmpty()) {
    		length = venues.size();
    		for ( int i = 0; i < length ; i++ ) {
        		if (venues.get(i).name.equals(venue)) {
        			new_venue = venues.get(i);
        			break;
        		}
        	}
    	}	   	
    	// if venue doesn't exist, create a new venue
    	if (new_venue == null) {
    		new_venue = new Venue(venue);
    		addVenue(new_venue);
    	}   		
    	// make the room and add it to venue
    	Room new_room = new Room(room, size);
    	new_venue.addRoom(new_room, size);
    }
    
    /**
     * process booking request
     * @param id - id of booking
     * @param start - start date of booking
     * @param end - end date of booking
     * @param small - number of small rooms
     * @param medium - number of medium rooms
     * @param large - number of large rooms
     * @return JSON object
     */
    public JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
        JSONObject result = new JSONObject();    	
        // look through venues
        for (Venue v : venues) {        	
        	if (canBook(v, start, end, small, medium, large)) {
        		Booking new_booking = new Booking(id, v, start, end);
        		// small, medium, large rooms required - decremented as they are booked
        		int sr = small;
            	int mr = medium;
            	int lr = large;
            	JSONArray rooms = new JSONArray();
            	for (Room r : v.rooms) {            		           		
            		if (r.size.equals("small") && sr > 0) {
            			if (isAvailable(r, start, end)) {
            				new_booking.addRoom(r);
            				rooms.put(r);
                			sr--;
                		}
            		}
            		if (r.size.equals("medium") && mr > 0) {
            			if (isAvailable(r, start, end)) {
            				new_booking.addRoom(r);
            				rooms.put(r);
                			mr--;
                		}
            		}   
            		if (r.size.equals("large") && lr > 0) {
            			if (isAvailable(r, start, end)) {
            				new_booking.addRoom(r);
            				rooms.put(r);
                			lr--;
                		}
            		}
            		// all booking requests fulfilled 
            		if (sr == 0 && mr == 0 && lr == 0) {
            			result.put("rooms", rooms);           			
            	        result.put("venue", v.name);
            	        result.put("status", "success");
            	        addBooking(new_booking);
            	        return result;
            		}
            	}
        	}
        }
        // no venue can fulfill the request - it is rejected
        result.put("status", "rejected");    
	    return result;    
    }
    
    /**
     * Checks if booking can be made for given venue at given start and end dates
     * @param venue - venue to be checked
     * @param start - start date of booking to be made
     * @param end - end date of booking to be made
     * @param small - number of small rooms requested
     * @param medium - number of medium rooms requested
     * @param large - number of large rooms requested
     * @return true if can book, false if cannot book
     */
    private Boolean canBook(Venue venue, LocalDate start, LocalDate end, int small, int medium, int large) {
    	// if venue doesnt have enough of requested rooms - cannot book
    	if (venue.small < small || venue.medium < medium || venue.large < large) {
    		return false;
    	}
    	// number of small, medium, large rooms required. Decremented as room can be booked
    	int sr = small;
    	int mr = medium;
    	int lr = large;
    	// for each room in the venue - if size needed, checks if room is available
    	for (Room r : venue.rooms) {
    		// all rooms are booked - so can book
    		if (sr == 0 && mr == 0 && lr == 0) {
    			return true;
    		}
    		if (r.size.equals("small") && sr > 0) {
    			if (isAvailable(r, start, end)) {
        			sr--;
        		}
    		} else if (r.size.equals("medium") && mr > 0) {
    			if (isAvailable(r, start, end)) {
        			mr--;
        		}
    		} else if (r.size.equals("large") && lr > 0) {
    			if (isAvailable(r, start, end)) {
        			lr--;
        		}
    		}   
    	}
    	// all rooms booked - so can book
    	if (sr == 0 && mr == 0 && lr == 0) {
			return true;
		// not all bookings satisfied - cannot book
		} else {
			return false;
		}    	   	
    }
    
    /**
     * check if a room is available between start and end dates by going through all bookings for that room
     * @param r - room to be checked
     * @param start - start date of proposed booking
     * @param end - end date of proposed booking
     * @return true if room is available, false if room is not available
     */
    private Boolean isAvailable(Room r, LocalDate start, LocalDate end) {
    	for (Booking b : bookings) {   		
    		if (b.rooms.contains(r)) {
    			// if start equals the booking start or end date return false
    			if (start.equals(b.start_date) || start.equals(b.end_date)) {
    				return false;
				// if end equals the booking start or end date return false
    			} else if (end.equals(b.start_date) || end.equals(b.end_date)) {
    				return false;
    			}
    			// if start and end are inside the start_date and end_date of a booking return false
    			else if (start.isAfter(b.start_date) && end.isBefore(b.end_date)) { 	
    				return false; 			
    			}
    			// if start is before booking start_date and end is after booking end_date return false
    			else if (start.isBefore(b.start_date) && end.isAfter(b.end_date)) { 	
    				return false; 			
    			}
    			// if start is before booking start_date and end is after booking start_date return false
    			else if (start.isBefore(b.start_date) && end.isAfter(b.start_date)) { 	
    				return false; 			
    			}
    			// if start is before booking end_date and end is after booking end_date return false
    			else if (start.isBefore(b.end_date) && end.isAfter(b.end_date)) { 	
    				return false; 			
    			}
    		}
    	}
    	return true;
    }
    
    /**
     *  cancel a booking in VenueHireSystem
     * @param id - id of booking to be canceled
     */
    public void cancel(String id) {
    	Booking to_remove = null;
    	for (Booking b : bookings) {
			if (b.id.equals(id)) {
				to_remove = b;
			}
    	}
    	bookings.remove(to_remove);
    }
    
    /**
     * Output a list of the occupancy for all rooms in given venue, in order of room declarations, then date
     * @param venue_string - name of venue to have occupancy listed
     * @return JSONObject 
     */
    public JSONArray list(String venue_string) {
    	// find the venue given its name
    	Venue venue = null;
    	for (Venue v : venues) {
    		if (v.name.equals(venue_string)) {
    			venue = v;
    			break;
    		}
    	}
    	// result is an array of objects - one for each room
    	JSONArray result = new JSONArray();
    	// for each room in the venue
    	for (Room r : venue.rooms) {    
    		// JSONObject for this room
    		JSONObject object = new JSONObject();
    		object.put("room", r.name);
    		// get all starting dates for bookings for room r
    		List<LocalDate> dates = new ArrayList<LocalDate>();
	    	for (Booking b : bookings) {    		
				if (b.rooms.contains(r)) {	
					dates.add(b.start_date);
				}
	    	}
	    	// sort dates
	    	Collections.sort(dates);	  
	    	JSONArray bookingsArray = new JSONArray();
			object.put("reservations", bookingsArray);
			result.put(object);
			for (LocalDate l : dates) {							
				for (Booking b : bookings) {
					if (b.start_date.equals(l) && b.rooms.contains(r)) {						
				    	String id = b.id;
						LocalDate start = b.start_date;
						LocalDate end = b.end_date;
						// make JSONObject about booking and put details in
						JSONObject booking = new JSONObject();
						booking.put("id", id);
						booking.put("start", start);
						booking.put("end", end);
						bookingsArray.put(booking);
					}
				}
			}
			
    	}   			
    	return result;
    }
    
    /**
     * adds a venue to venues in VenueHireSystem
     * @param venue - venue to be added to system
     */
    private void addVenue(Venue venue) {
    	venues.add(venue);
    }
     
    /**
     * Add a booking to bookings in VenueHireSystem
     * @param b - booking to be added
     */
    public void addBooking(Booking b) {
    	bookings.add(b);
    }
    
    // methods for debugging
    /**
     * prints all bookings in VenueHireSystem
     */
    public void printBookings() {
    	for (Booking b : bookings) {
    		System.out.println("##################");
    		b.toString();
    		System.out.println("##################");
    	}
    }
    
    /**
     *  prints all venues in VenueHireSystem
     */
    public void printVenues() {
		for (Venue v : venues) {
			System.out.println("^^^^^^^^^^^^");
			v.toString();
		}
    }
    
    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}
