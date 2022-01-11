package unsw.dungeon;

import java.awt.Point;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;



public class GenerateMaps {
	
	
	public static void createMaze(int x, int y) {
		
	}
	
	
	/**
	 *  generates a horizontal line of walls.
	 * @param x
	 * @param y1
	 * @param y2
	 * @param name
	 */
	public static void addWallV(int x, int y1, int y2, String name) {
		JSONObject result = new JSONObject();
		JSONArray final_entites_array = new JSONArray();
		
		for (int i = y1  ; i <= y2 ; i++) {
			JSONObject final_enities_wall = new JSONObject();
			final_enities_wall.put("x", x);
			final_enities_wall.put("y", i);
			final_enities_wall.put("type", "wall");
			final_entites_array.put(final_enities_wall);
		}
		
		result.put("entities", final_entites_array);
		
		try (FileWriter file = new FileWriter("./dungeons/" + name +".json")){
			file.write(result.toString(2));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 *  generates a vertical line of walls.
	 * @param x
	 * @param y1
	 * @param y2
	 * @param name
	 */
	public static void addWallH(int x1, int x2, int y, String name) {
		JSONObject result = new JSONObject();
		JSONArray final_entites_array = new JSONArray();
		
		for (int i = x1  ; i <= x2 ; i++) {
			JSONObject final_enities_wall = new JSONObject();
			final_enities_wall.put("x", i);
			final_enities_wall.put("y", y);
			final_enities_wall.put("type", "wall");
			final_entites_array.put(final_enities_wall);
		}
		
		result.put("entities", final_entites_array);
		
		try (FileWriter file = new FileWriter("./dungeons/" + name +".json")){
			file.write(result.toString(2));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Very basic, creates a map of size with wall around it.
	 * @param x
	 * @param y
	 * @param name
	 */
	public static void simpleCreate(int x, int y, String name) {
		ArrayList<Point> usedCoordinates = new ArrayList<>(); 
		ArrayList<Point> goal_list = new ArrayList<>(); 
		
		JSONObject result = new JSONObject();
		result.put("width", x);
		result.put("height", y);
		
		JSONArray final_entites_array = new JSONArray();
		
		// player done
		JSONObject final_enities_player = new JSONObject();
		final_enities_player.put("x", 1);
		final_enities_player.put("y", 1);
		final_enities_player.put("type", "player");
		final_entites_array.put(final_enities_player);
		usedCoordinates.add(new Point(1,1));
		
		// surrounding wall
		// left side 0,0 0,1 ... 0,19
		for (int i = 0  ; i < y ; i++) {
			if (!usedCoordinates.contains(new Point(0,i))) {
				JSONObject final_enities_wall = new JSONObject();
				final_enities_wall.put("x", 0);
				final_enities_wall.put("y", i);
				final_enities_wall.put("type", "wall");
				final_entites_array.put(final_enities_wall);
				usedCoordinates.add(new Point(0,i));
			}
		}
				
					// right side 19,0 ... 19,19
					for (int i = 0  ; i < y ; i++) {
						if (!usedCoordinates.contains(new Point(x-1,i))) {
							JSONObject final_enities_wall = new JSONObject();
							final_enities_wall.put("x", x-1);
							final_enities_wall.put("y", i);
							final_enities_wall.put("type", "wall");
							final_entites_array.put(final_enities_wall);
							usedCoordinates.add(new Point(x-1,i));
						}
					}
					// up 0,0 1,0 ... 19,0
					for (int i = 0  ; i < x ; i++) {
						if (!usedCoordinates.contains(new Point(i,0))) {
							JSONObject final_enities_wall = new JSONObject();
							final_enities_wall.put("x", i);
							final_enities_wall.put("y", 0);
							final_enities_wall.put("type", "wall");
							final_entites_array.put(final_enities_wall);
							usedCoordinates.add(new Point(i,0));
						}
					}
					
					// down 0,19 .. 19,19
					for (int i = 0  ; i < x ; i++) {
						if (!usedCoordinates.contains(new Point(i,y-1))) {
							JSONObject final_enities_wall = new JSONObject();
							final_enities_wall.put("x", i);
							final_enities_wall.put("y", y-1);
							final_enities_wall.put("type", "wall");
							final_entites_array.put(final_enities_wall);
							usedCoordinates.add(new Point(i,y-1));
						}
					}

					result.put("entities", final_entites_array);
					
					try (FileWriter file = new FileWriter("./dungeons/" + name +".json")){
						file.write(result.toString(2));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//*/
	}
	
	public static void createMap(String location, int num_maps,int x, int y, String... entities) {
		
		for(int count = 1; count<= num_maps ; count++ ) {
			//array of used Points 
			ArrayList<Point> usedCoordinates = new ArrayList<>(); 
			ArrayList<Point> goal_list = new ArrayList<>(); 
			
			JSONObject result = new JSONObject();
			result.put("width", x);
			result.put("height", y);
			
			JSONArray final_entites_array = new JSONArray();
			
			// player done
			JSONObject final_enities_player = new JSONObject();
			final_enities_player.put("x", 1);
			final_enities_player.put("y", 1);
			final_enities_player.put("type", "player");
			final_entites_array.put(final_enities_player);
			usedCoordinates.add(new Point(1,1));
			
			// surrounding wall
			// left side 0,0 0,1 ... 0,19
			for (int i = 0  ; i < y ; i++) {
				if (!usedCoordinates.contains(new Point(0,i))) {
					JSONObject final_enities_wall = new JSONObject();
					final_enities_wall.put("x", 0);
					final_enities_wall.put("y", i);
					final_enities_wall.put("type", "wall");
					final_entites_array.put(final_enities_wall);
					usedCoordinates.add(new Point(0,i));
				}
			}
		
			// right side 19,0 ... 19,19
			for (int i = 0  ; i < y ; i++) {
				if (!usedCoordinates.contains(new Point(x-1,i))) {
					JSONObject final_enities_wall = new JSONObject();
					final_enities_wall.put("x", x-1);
					final_enities_wall.put("y", i);
					final_enities_wall.put("type", "wall");
					final_entites_array.put(final_enities_wall);
					usedCoordinates.add(new Point(x-1,i));
				}
			}
			// up 0,0 1,0 ... 19,0
			for (int i = 0  ; i < x ; i++) {
				if (!usedCoordinates.contains(new Point(i,0))) {
					JSONObject final_enities_wall = new JSONObject();
					final_enities_wall.put("x", i);
					final_enities_wall.put("y", 0);
					final_enities_wall.put("type", "wall");
					final_entites_array.put(final_enities_wall);
					usedCoordinates.add(new Point(i,0));
				}
			}
			
			// down 0,19 .. 19,19
			for (int i = 0  ; i < x ; i++) {
				if (!usedCoordinates.contains(new Point(i,y-1))) {
					JSONObject final_enities_wall = new JSONObject();
					final_enities_wall.put("x", i);
					final_enities_wall.put("y", y-1);
					final_enities_wall.put("type", "wall");
					final_entites_array.put(final_enities_wall);
					usedCoordinates.add(new Point(i,y-1));
				}
			}
			
			// exit
			// exit on bottom above bottom wall
			int rnd_exit = (int) (Math.round(Math.random()*100)%(x-2))+1;
			if (!usedCoordinates.contains(new Point(rnd_exit,y-2))) {
				JSONObject final_enities_wall = new JSONObject();
				final_enities_wall.put("x", rnd_exit);
				final_enities_wall.put("y", y-2);
				final_enities_wall.put("type", "exit");
				final_entites_array.put(final_enities_wall);
				usedCoordinates.add(new Point(rnd_exit,y-2));
			}
			
			// enemy
			// random on map
			//System.out.println(Math.round(Math.random()*100)%((x+y)/10)+1);
			for (int i = 0; i < (Math.round(Math.random()*100)%((x+y)/10))+1 ;i++) {
				int rnd_enemy_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
				int rnd_enemy_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
				if (!usedCoordinates.contains(new Point(rnd_enemy_x,rnd_enemy_y))) {
					JSONObject final_enities_wall = new JSONObject();
					final_enities_wall.put("x", rnd_enemy_x);
					final_enities_wall.put("y", rnd_enemy_y);
					final_enities_wall.put("type", "enemy");
					final_entites_array.put(final_enities_wall);
					usedCoordinates.add(new Point(rnd_enemy_x,rnd_enemy_y));
				}
			}
			
			// sword
			// random on map
			for (int i = 0; i < (Math.round(Math.random()*100)%(4))+1 ;i++) {
				int rnd_sword_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
				int rnd_sword_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
				if (!usedCoordinates.contains(new Point(rnd_sword_x,rnd_sword_y))) {
					JSONObject final_enities_wall = new JSONObject();
					final_enities_wall.put("x", rnd_sword_x);
					final_enities_wall.put("y", rnd_sword_y);
					final_enities_wall.put("type", "sword");
					final_entites_array.put(final_enities_wall);
					usedCoordinates.add(new Point(rnd_sword_x,rnd_sword_y));
				}
			}
			
			
			// random walls 
			for (int i = 0 ; i < Math.round(x/2)-1; i++ ) {
				for (int j = 0  ; j < Math.floorDiv(y, 2) ; j++) {
					int x_rnd = (int) (Math.round(Math.random()*100)%(x-3))+1;
					int y_rnd = (int) (Math.round(Math.random()*100)%(y-3))+1;
					if (!usedCoordinates.contains(new Point(x_rnd,y_rnd)) && x_rnd !=1 && y_rnd != 1) {
						JSONObject final_enities_wall = new JSONObject();
						final_enities_wall.put("x", x_rnd);
						final_enities_wall.put("y", y_rnd);
						final_enities_wall.put("type", "wall");
						final_entites_array.put(final_enities_wall);
						usedCoordinates.add(new Point(x_rnd,y_rnd));
					}
				}
			}
	
			//result.put("entities", final_entites_array);
			
			// for other entities 
			for(String e : entities) {
				switch(e) {
				
				case "door_key":
					JSONObject final_enities_door_exit = new JSONObject();
					final_enities_door_exit.put("x", rnd_exit);
					final_enities_door_exit.put("y", y-2);
					final_enities_door_exit.put("type", "door");
					final_enities_door_exit.put("id", 0);
					final_entites_array.put(final_enities_door_exit);
					
					int rnd_key_exit_key_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
					int rnd_key_exit_key_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
					if (usedCoordinates.contains(new Point(rnd_key_exit_key_x,rnd_key_exit_key_y))) {
						while (true) {
							rnd_key_exit_key_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
							rnd_key_exit_key_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
							if (!usedCoordinates.contains(new Point(rnd_key_exit_key_x,rnd_key_exit_key_y))) {
								usedCoordinates.add(new Point(rnd_key_exit_key_x,rnd_key_exit_key_y));
								break;
							}
						}	
					}
				
					
					JSONObject final_enities_key_exit = new JSONObject();
					final_enities_key_exit.put("x", rnd_key_exit_key_x);
					final_enities_key_exit.put("y", rnd_key_exit_key_y);
					final_enities_key_exit.put("type", "key");
					final_enities_key_exit.put("id", 0);
					final_entites_array.put(final_enities_key_exit);
					
					
					for (int i = 1; i < (Math.round(Math.random()*100)%(4))+1 ;i++) {
						int rnd_door_x = (int) (Math.round(Math.random()*100)%(x-3))+1;
						int rnd_door_y = (int) (Math.round(Math.random()*100)%(y-3))+1;
						
						if (usedCoordinates.contains(new Point(rnd_door_x,rnd_door_y))) {
							while (true) {
								rnd_door_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
								rnd_door_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
								if (!usedCoordinates.contains(new Point(rnd_door_x,rnd_door_y))) {
									usedCoordinates.add(new Point(rnd_door_x,rnd_door_y));
									break;
								}
							}	
						}
						JSONObject final_enities_door = new JSONObject();
						final_enities_door.put("x", rnd_door_x);
						final_enities_door.put("y", rnd_door_y);
						final_enities_door.put("type", "door");
						final_enities_door.put("id", i);
						final_entites_array.put(final_enities_door);
						usedCoordinates.add(new Point(rnd_door_x,rnd_door_y));
						
						int rnd_key_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
						int rnd_key_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
						if (usedCoordinates.contains(new Point(rnd_key_x,rnd_key_y))) {
							while (true) {
								rnd_key_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
								rnd_key_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
								if (!usedCoordinates.contains(new Point(rnd_key_x,rnd_key_y))) {
									usedCoordinates.add(new Point(rnd_key_x,rnd_key_y));
									break;
								}
							}	
						}
						JSONObject final_enities_key = new JSONObject();
						final_enities_key.put("x", rnd_key_x);
						final_enities_key.put("y", rnd_key_y);
						final_enities_key.put("type", "key");
						final_enities_key.put("id", i);
						final_entites_array.put(final_enities_key);
						usedCoordinates.add(new Point(rnd_key_x,rnd_key_y));
						
					}
					break;
				case "boulder_switch":
					for (int i = 0; i < (Math.round(Math.random()*100)%(4))+1 ;i++) {
						int rnd_switch_x = (int) (Math.round(Math.random()*100)%(x-3))+1;
						int rnd_switch_y = (int) (Math.round(Math.random()*100)%(y-3))+1;
						if (usedCoordinates.contains(new Point(rnd_switch_x,rnd_switch_y))) {
							while (true) {
								rnd_switch_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
								rnd_switch_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
								if (!usedCoordinates.contains(new Point(rnd_switch_x,rnd_switch_y))) {
									usedCoordinates.add(new Point(rnd_switch_x,rnd_switch_y));
									break;
								}
							}	
						}
						
						JSONObject final_enities_switch = new JSONObject();
						final_enities_switch.put("x", rnd_switch_x);
						final_enities_switch.put("y", rnd_switch_y);
						final_enities_switch.put("type", "switch");
						final_entites_array.put(final_enities_switch);
						usedCoordinates.add(new Point(rnd_switch_x,rnd_switch_y));
						
						int rnd_boulder_x = (int) (Math.round(Math.random()*100)%(x-3))+1;
						int rnd_boulder_y = (int) (Math.round(Math.random()*100)%(y-3))+1;
						if (usedCoordinates.contains(new Point(rnd_boulder_x,rnd_boulder_y))) {
							while (true) {
								rnd_boulder_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
								rnd_boulder_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
								if (!usedCoordinates.contains(new Point(rnd_boulder_x,rnd_boulder_y))) {
									usedCoordinates.add(new Point(rnd_boulder_x,rnd_boulder_y));
									break;
								}
							}	
						}
						
						JSONObject final_enities_boulder = new JSONObject();
						final_enities_boulder.put("x", rnd_boulder_x);
						final_enities_boulder.put("y", rnd_boulder_y);
						final_enities_boulder.put("type", "boulder");
						final_entites_array.put(final_enities_boulder);
						usedCoordinates.add(new Point(rnd_boulder_x,rnd_boulder_y));
						
					}
					break;
				case "treasure":
					for (int i = 0; i < (Math.round(Math.random()*100)%(4))+1 ;i++) {
						int rnd_treasure_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
						int rnd_treasure_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
						if (!usedCoordinates.contains(new Point(rnd_treasure_x,rnd_treasure_y))) {
							JSONObject final_enities_treasure = new JSONObject();
							final_enities_treasure.put("x", rnd_treasure_x);
							final_enities_treasure.put("y", rnd_treasure_y);
							final_enities_treasure.put("type", "treasure");
							final_entites_array.put(final_enities_treasure);
							usedCoordinates.add(new Point(rnd_treasure_x,rnd_treasure_y));
						}
					}
					break;
				case "portal":
					for (int i = 0; i < (Math.round(Math.random()*100)%(4))+1 ;i++) {
						int rnd_portal_x_enter = (int) (Math.round(Math.random()*100)%(x-2))+1;
						int rnd_portal_y_enter = (int) (Math.round(Math.random()*100)%(y-2))+1;
						int rnd_portal_x_exit = (int) (Math.round(Math.random()*100)%(x-2))+1;
						int rnd_portal_y_exit = (int) (Math.round(Math.random()*100)%(y-2))+1;
						if (!usedCoordinates.contains(new Point(rnd_portal_x_enter,rnd_portal_y_enter)) && !usedCoordinates.contains(new Point(rnd_portal_x_exit,rnd_portal_y_exit))) {
							JSONObject final_enities_portal_enter = new JSONObject();
							final_enities_portal_enter.put("x", rnd_portal_x_enter);
							final_enities_portal_enter.put("y", rnd_portal_y_enter);
							final_enities_portal_enter.put("type", "portal");
							final_enities_portal_enter.put("id", i);
							final_entites_array.put(final_enities_portal_enter);
							usedCoordinates.add(new Point(rnd_portal_x_enter,rnd_portal_y_enter));
							
							JSONObject final_enities_portal_exit = new JSONObject();
							final_enities_portal_exit.put("x", rnd_portal_x_exit);
							final_enities_portal_exit.put("y", rnd_portal_y_exit);
							final_enities_portal_exit.put("type", "portal");
							final_enities_portal_exit.put("id", i);
							final_entites_array.put(final_enities_portal_exit);
							usedCoordinates.add(new Point(rnd_portal_x_exit,rnd_portal_y_exit));
						}
					}
					break;
				case "invincibility":
					for (int i = 0; i < (Math.round(Math.random()*100)%(3))+1 ;i++) {
						int rnd_invincibility_x = (int) (Math.round(Math.random()*100)%(x-2))+1;
						int rnd_invincibility_y = (int) (Math.round(Math.random()*100)%(y-2))+1;
						if (!usedCoordinates.contains(new Point(rnd_invincibility_x,rnd_invincibility_y))) {
							JSONObject final_enities_invincibility = new JSONObject();
							final_enities_invincibility.put("x", rnd_invincibility_x);
							final_enities_invincibility.put("y", rnd_invincibility_y);
							final_enities_invincibility.put("type", "invincibility");
							final_entites_array.put(final_enities_invincibility);
							usedCoordinates.add(new Point(rnd_invincibility_x,rnd_invincibility_y));
						}
					}
					break;
				}
				
			}
			
			result.put("entities", final_entites_array);
			
			//for goals random entities that can be goals 
	
			JSONArray sub_goals = new JSONArray();
			// goal object 
			JSONObject goal_object = new JSONObject();
			
			String and_or = "AND";
			if (new Random().nextBoolean()) {
				and_or = "OR";
			}
			String objective = "enemies";
			
			switch(new Random().nextInt(3)) {
			case(0):
				if (entities[0] == "treasure" || entities[1] == "treasure") {
					objective = "treasure";
				}
				break;
			case(1):
				if (entities[0] == "boulder_switch" || entities[1] == "boulder_switch") {
					objective = "switch";
				}
				break;
			case(2):
				objective = "enemies";
				break;
			}
		
			JSONObject sub1 = new JSONObject();
			JSONObject sub2 = new JSONObject();
			sub1.put("goal", "exit");
			sub2.put("goal", objective);
			sub_goals.put(sub1);
			sub_goals.put(sub2);
			
			
			goal_object.put("goal", and_or);
			goal_object.put("subgoals", sub_goals);
			
			result.put("goal-condition", goal_object);
			//System.out.println(result.toString(2));
			
			// output to file 
			///*
			try (FileWriter file = new FileWriter("./dungeons/"+location+"/"+count+".json")){
				file.write(result.toString(2));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//*/
		}	
	}
	
	
	// args order: num_maps x y entities...
	// type of entities that can be added:
	// door_key, boulder_switch, treasure, portal, invincibility
	public static void main(String[] args) {
		// x, y1, y2
		 //addWallH(10,  18, 6, "wallH");
		// x1, x2, y
		//addWallV(14, 4, 10, "wallV");
		 //simpleCreate(14, 11, "18");
		/*
		//parameters: location, num_maps, x, y, entities...
		
		//SmallMaps
		//AllEntities
		createMap("SmallMaps/AllEntities", 10, 10, 10, "treasure", "boulder_switch", "door_key", "portal", "invincibility");
		
		//NoBoulderSwitch	
		createMap("SmallMaps/NoBoulderSwitch", 10, 10, 10, "treasure", "door_key", "portal", "invincibility");
		
		//NoPortal
		createMap("SmallMaps/NoPortal", 10, 10, 10, "treasure", "boulder_switch", "door_key", "invincibility");
		
		//NoTreasure
		createMap("SmallMaps/NoTreasure", 10, 10, 10, "boulder_switch", "door_key", "portal", "invincibility");
		
		//Random
		createMap("SmallMaps/Random", 10, 10, 10, "treasure", "boulder_switch", "portal");
		
		
		//MediumMaps 
		//AllEntities
		createMap("MediumMaps/AllEntities", 10, 20, 15, "treasure", "boulder_switch", "door_key", "portal", "invincibility");
		
		//NoBoulderSwitch	
		createMap("MediumMaps/NoBoulderSwitch", 10, 20, 15, "treasure", "door_key", "portal", "invincibility");
		
		//NoPortal
		createMap("MediumMaps/NoPortal", 10, 20, 15, "treasure", "boulder_switch", "door_key", "invincibility");
		
		//NoTreasure
		createMap("MediumMaps/NoTreasure", 10, 20, 15, "boulder_switch", "door_key", "portal", "invincibility");
		
		//Random
		createMap("MediumMaps/Random", 10, 20, 15, "treasure", "boulder_switch", "portal");
		
		//LargeMaps
		//AllEntities
		createMap("LargeMaps/AllEntities", 10, 30, 20, "treasure", "boulder_switch", "door_key", "portal", "invincibility");
		
		//NoBoulderSwitch	
		createMap("LargeMaps/NoBoulderSwitch", 10, 30, 20, "treasure", "door_key", "portal", "invincibility");
		
		//NoPortal
		createMap("LargeMaps/NoPortal", 10, 30, 20, "treasure", "boulder_switch", "door_key", "invincibility");
		
		//NoTreasure
		createMap("LargeMaps/NoTreasure", 10, 30, 20, "boulder_switch", "door_key", "portal", "invincibility");
		
		//Random
		createMap("LargeMaps/Random", 10, 30, 20, "treasure", "boulder_switch", "portal");
		*/
	}
	
}
