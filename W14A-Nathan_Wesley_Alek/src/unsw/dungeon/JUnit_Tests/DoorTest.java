package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/**
 * 
 * testing ES4: Locked Door Interaction (unlocking door with key is tested in KeyTest)
 *
 */
class DoorTest {
	
	/**
	 * locked door blocks player without a key
	 */
	@Test
	void testLockedDoorBlocksPlayerWithNoKey() {
		Dungeon dungeon = new Dungeon(10,10);							// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());		// initialise player in the dungeon (at 0,0)		
		Door door = new Door(dungeon, 0, 1, 1, new Interactable());		// create a new door (1 square below player)
		dungeon.addEntity(door);										// add door to dungeon
		
		assertEquals(player.getKey(), null);	// player has no key in inventory
		player.moveDown();						// player attemps moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getX(), 0);	// player does not unlock the door (since they have no key)
		assertEquals(player.getY(), 0);	// player does not move since the locked door blocks them (player is still at 0,0)
	}
	
	/**
	 * locked door blocks player without the corresponding key
	 */
	@Test
	void testLockedDoorBlocksPlayerWithWrongKey() {
		Dungeon dungeon = new Dungeon(10,10);							// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());		// initialise player in the dungeon (at 0,0)		
		Door door = new Door(dungeon, 0, 1, 1, new Interactable());		// create a new door (1 square below player)
		dungeon.addEntity(door);										// add door to dungeon
		
		player.setKey(new Key(dungeon,0,0,8, new Collectable())); // player has key with wrong ID for door in front of them

		player.moveDown();						// player attemps moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getX(), 0);	// player does not unlock the door (since they have wrong key)
		assertEquals(player.getY(), 0);	// player does not move since the locked door blocks them (player is still at 0,0)
	}
	
	/**
	 * Locked door blocks boulder
	 */
	@Test
	void testLockedDoorBlocksNonPlayer() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());			// initialise player in the dungeon (at 0,0)		
		Boulder boulder = new Boulder(dungeon, 0, 1, new Interactable());	// create new boulder (1 square below player)
		Door door = new Door(dungeon, 0, 2, 1, new Interactable());			// create a new door (2 squares below player)
		dungeon.addEntity(door);											// add door to dungeon
		dungeon.addEntity(boulder);											// add boulder to dungeon
		
		player.moveDown();						// player attemps moves down 1 space to push the boulder
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(boulder.getX(), 0);	
		assertEquals(boulder.getY(), 1);	// boulder does not move since the locked door blocks it (boulder is still at 0,1)
		assertEquals(player.getX(), 0);	
		assertEquals(player.getY(), 0);		// player does not move since the locked door blocked the boulder (player is still at 0,0)
	}
	
	/**
	 * unlocked door does not block boulder
	 */
	@Test
	void testBoulderOnUnlockedDoor() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());			// initialise player in the dungeon (at 0,0)		
		Boulder boulder = new Boulder(dungeon, 0, 1, new Interactable());	// create new boulder (1 square below player)
		Door door = new Door(dungeon, 0, 2, 1, new Moveable());				// create a new opened door (2 squares below player)
		dungeon.addEntity(door);											// add door to dungeon
		dungeon.addEntity(boulder);											// add boulder to dungeon
		
		player.moveDown();						// player attemps moves down 1 space to push the boulder
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(door.getX(), 0);	
		assertEquals(door.getY(), 2);		// unlocked door at 0,2
		assertEquals(boulder.getX(), 0);	
		assertEquals(boulder.getY(), 2);	// boulder moves onto unlocked door at 0,2	
		assertEquals(player.getX(), 0);	
		assertEquals(player.getY(), 1);		// player moves to 0,1 where the boulder was before it was pushed
	}

}
