package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/**
 * 
 * testing ES10: Sword Interaction: pickup and swap
 *
 */
class SwordTest {

	@Test
	void testSwordPickup() {
		Dungeon dungeon = new Dungeon(10,10);							// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());		// initialise player in the dungeon		
		Sword sword = new Sword(dungeon, 0, 1, new Collectable());	// create a new sword (1 square below player)
		dungeon.addEntity(sword);										// add sword to dungeon
		
		assertEquals(player.getWeapon(), null);	// player initally has no sword in inventory
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getWeapon(), sword);		// player picks up sword from ground and puts it in inventory
		
		// now check the sword is not on the ground
		List<Entity> entities = dungeon.getCurrentEntity(player.getX(), player.getY());	// get all entities on the square of the player
		Sword i = null;
    	for (Entity e : entities) {
    		if (e instanceof Sword) {
    			i = (Sword) e;
    			break;
    		}
    	}
    	assertEquals(i,null);	// there is no ksword on the square of the player 
	}
	
	@Test
	void testSwordSwap() {
		Dungeon dungeon = new Dungeon(10,10);							// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());		// initialise player in the dungeon		
		Sword sword1 = new Sword(dungeon, 0, 1, new Collectable());	// create a new sword (1 square below player)
		Sword sword2 = new Sword(dungeon, 0, 2, new Collectable());	// create another new sword (2 squares below player)
		dungeon.addEntity(sword1);
		dungeon.addEntity(sword2);										// add swords to dungeon
		
		assertEquals(player.getWeapon(), null);	// player initally has no sword in inventory
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getWeapon(), sword1);	// player picks up sword 1 from ground and puts it in inventory
		
		// now check sword 1 is not on the ground
		List<Entity> entities = dungeon.getCurrentEntity(player.getX(), player.getY());	// get all entities on the square of the player
		Sword i = null;
    	for (Entity e : entities) {
    		if (e instanceof Sword) {
    			i = (Sword) e;
    			break;
    		}
    	}
    	assertEquals(i,null);	// there is no sword on the square of the player 
    	
    	player.moveDown();		// player moves down 1 more space onto sword 2
    	try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
    	
    	assertEquals(player.getWeapon(), sword2); // player picks up sword 2 from ground and puts it in inventory
    	// now check sword 1 is on the ground
		List<Entity> entities2 = dungeon.getCurrentEntity(player.getX(), player.getY());	// get all entities on the square of the player
    	Sword s = null;
    	for (Entity e : entities2) {
    		if (e instanceof Sword) {
    			s = (Sword) e;
    			break;
    		}
    	}
    	assertEquals(s.getWeaponID(),sword1.getWeaponID());	// the player drops sword 1 on the ground
	}

}
