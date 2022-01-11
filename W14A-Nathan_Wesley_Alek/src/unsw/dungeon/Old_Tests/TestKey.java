package unsw.dungeon.Old_Tests;

import java.util.List;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

public class TestKey {
	
	
	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 0, 0, new Moveable());
		
		Key key = new Key(dungeon, 0, 1, 1, new Collectable());
		dungeon.addEntity(key);
		Key key2 = new Key(dungeon, 0, 2, 2, new Collectable());
		dungeon.addEntity(key2);
		player.moveDown();
		if (player.getKey() == null) {
			System.out.println("i have no key");
		}
		if (player.getKey() != null) {
			System.out.println("got key with ID:" + player.getKey().getkeyID());
		}
		
		try
		{
		    Thread.sleep(800);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveDown();
		System.out.println("move down");
		if (player.getKey() != null) {
			System.out.println("got key with ID:" + player.getKey().getkeyID());
		}
		
		List<Entity> entities = dungeon.getCurrentEntity(player.getX(), player.getY());
    	Key i = null;
    	// check if there is a Pickup_item on players location
    	for (Entity e : entities) {
    		if (e instanceof Key) {
    			i = (Key) e;
    			break;
    		}
    	}
		if (i == null) {
			System.out.println("why");
		}
		System.out.println("dropped key with ID:" + i.getkeyID());
		
		//test drop key1 pick key2 then pick key1 again 
		try
		{
		    Thread.sleep(800);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveUp();
		try
		{
		    Thread.sleep(800);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveDown();
		System.out.println("move up and back down");
		if (player.getKey() != null) {
			System.out.println("got key with ID:" + player.getKey().getkeyID());
		}
		
	}
	
}
