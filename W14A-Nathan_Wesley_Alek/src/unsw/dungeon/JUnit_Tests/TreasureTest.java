package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/**
 * 
 * testing: PS8: Pick up Treasure, ES3: Treasure Interaction
 *
 */
class TreasureTest {

	@Test
	void testPickupTreasure() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());			// initialise player in the dungeon (at 0,0)
		Treasure treasure = new Treasure(dungeon, 0, 1, new Collectable());
		dungeon.addEntity(treasure);										// add treasure to dungeon (1 space down from player)
		
		assertEquals(player.getTreasure(), 0);	// player initially has no treasure in inventory
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getTreasure(), 1);	// player picks up treasure (they now have 1 treasure in inventory)
		
		// check treasure is not on the ground now
		List<Entity> entities = dungeon.getCurrentEntity(player.getX(), player.getY());	// get all entities on the square of the player
    	Treasure t = null;
    	for (Entity e : entities) {
    		if (e instanceof Key) {
    			t = (Treasure) e;
    			break;
    		}
    	}
    	assertEquals(t,null);					// there is now no treasure on the ground in the square the player is on
    	
    	player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		player.moveUp();						// player moves up 1 space back to where the treasure was
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getTreasure(), 1);	// player still only has 1 treasure in inventory
	}

}
