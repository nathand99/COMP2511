package unsw.dungeon.Application;

import unsw.dungeon.*;
import unsw.dungeon.Entities.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

	private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader(filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        // old location for registering.
        
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        
        
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y, new Moveable());
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            // doesn't load a goal here.
            break;
        case "wall":
            Wall wall = new Wall(x, y, new Immovable());
            onLoad(wall);
            entity = wall;
            break;
        // TODO Handle other possible entities
        case "enemy":
        	Henchman henchman = new Henchman(dungeon, x, y, new Interactable());  // moveable or interactable?
        	onLoad(henchman);
        	entity = henchman;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon, x, y, new Interactable());
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	FloorSwitch fSwitch = new FloorSwitch(dungeon, x, y, new Moveable());
        	onLoad(fSwitch);
        	entity = fSwitch;
        	break;
        case "portal":
        	int portalID = json.getInt("id");
            Portal portal = new Portal(dungeon, x, y, portalID, new Interactable());
            onLoad(portal);
            entity = portal;
            break;
        case "invincibility":
        	Invincibility invincibility = new Invincibility(dungeon, x, y, new Collectable());
            onLoad(invincibility);
            entity = invincibility;
            break;
        case "exit":
        	Exit exit = new Exit(dungeon, x, y, new Interactable());
        	onLoad(exit);
        	entity = exit;
        	break;        
	    case "sword":
	    	Sword sword = new Sword(dungeon, x, y, new Collectable());
	    	onLoad(sword);
	    	entity = sword;
	    	break;
	    case "long_sword":
	    	LongSword longSword = new LongSword(dungeon, x, y, new Collectable());
	    	onLoad(longSword);
	    	entity = longSword;
	    	break;
	    case "mace":
	    	Mace mace = new Mace(dungeon, x, y, new Collectable());
	    	onLoad(mace);
	    	entity = mace;
	    	break;
	    	
	    case "treasure":
	    	Treasure treasure = new Treasure(dungeon, x, y, new Collectable());
	    	onLoad(treasure);
	    	entity = treasure;
	    	break;
	    	
	    case "door":
        	int doorID = json.getInt("id");
            Door door = new Door(dungeon, x, y, doorID, new Interactable());
            onLoad(door);
            entity = door;
            break;
            
	    case "key":
        	int keyID = json.getInt("id");
            Key key = new Key(dungeon, x, y, keyID, new Collectable());
            onLoad(key);
            entity = key;
            break;
            
	    case "hound":
	    	String direction = json.getString("direction");
	    	Hound hound = new Hound(dungeon, x, y, direction, new Interactable());
	    	onLoad(hound);
	    	entity = hound;
	    	break;
            
	    case "fire_trap":
	    	int timeGap = json.getInt("time_gap");
        	FireTrap fireTrap = new FireTrap(dungeon, x, y, timeGap, new Interactable());
        	onLoad(fireTrap);
        	entity = fireTrap;
        	
        	// each fire_trap comes with its own fire.
        	Fire fire = new Fire(dungeon, x, y, new Interactable());
        	onLoad(fire);
        	fireTrap.setFire(fire);
        	Entity entity2 = fire;
        	dungeon.addEntity(entity2);
        	
        	break;
        	
    	}
        
        dungeon.addEntity(entity); // where the entity is added.
    }
    
    public JSONObject getJson() {
		return json;
	}

    public abstract void onLoad(Entity player);
    
    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities
    public abstract void onLoad(Henchman henchman);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(FloorSwitch fSwitch);
    
    public abstract void onLoad(Portal portal);
    
    public abstract void onLoad(Invincibility invincibility);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(LongSword longSword);
    public abstract void onLoad(Mace mace);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(FireTrap fireTrap);
    
    public abstract void onLoad(Fire fire);
    
    public abstract void onLoad(Hound hound);
}
