package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/**
 * 
 * testing: PS2: Picking up Items
 * 
 * This class is testing picking up and placing into inventory a key, sword and treasure in the same game
 * 
 * Individual pickup and swapping for keys and swords is tested in KeyTest and SwordTest, and treasure is tested in TreasureTest
 *
 */
class PickupItemTest {
	
	@Test
	void testPickup() {
		Dungeon dungeon = new Dungeon(10,10);							// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());		// initialise player in the dungeon		
		Key key = new Key(dungeon, 0, 1, 1, new Collectable());			// create a new key (at 0,1) with ID 1
		Sword sword = new Sword(dungeon, 0, 2, new Collectable());	// create a new sword (at 0,2) with ID 2	
		Treasure treasure1 = new Treasure(dungeon, 0, 3, new Collectable());
		Treasure treasure2 = new Treasure(dungeon, 0, 4, new Collectable());
		Treasure treasure3 = new Treasure(dungeon, 0, 5, new Collectable());
		Treasure treasure4 = new Treasure(dungeon, 0, 6, new Collectable());
		Treasure treasure5 = new Treasure(dungeon, 0, 7, new Collectable());
		Treasure treasure6 = new Treasure(dungeon, 0, 8, new Collectable());
		dungeon.addEntity(key);											// add key to dungeon
		dungeon.addEntity(sword);										// add sword to dungeon
		dungeon.addEntity(treasure1);
		dungeon.addEntity(treasure2);
		dungeon.addEntity(treasure3);
		dungeon.addEntity(treasure4);
		dungeon.addEntity(treasure5);
		dungeon.addEntity(treasure6);									// add the 6 treasure
		
		assertEquals(player.getKey(), null);	// player initally has no key in inventory
		assertEquals(player.getWeapon(), null);	// player initally has no sword in inventory
		assertEquals(player.getTreasure(), 0);	// player initally has 0 treasure
		
		player.moveDown();						// player moves down 1 space onto 0,1
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key);		// player picks up key from ground and puts it in inventory
		assertEquals(player.getWeapon(), null);	// player has no sword in inventory
		assertEquals(player.getTreasure(), 0);	// player has 0 treasure
    	
    	player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key);		// player has key in inventory
		assertEquals(player.getWeapon(), sword);	// player picks up sword from ground and puts it in inventory
		assertEquals(player.getTreasure(), 0);	// player has 0 treasure
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key);		// player has key in inventory
		assertEquals(player.getWeapon(), sword);	// player has sword in inventory
		assertEquals(player.getTreasure(), 1);	// player picks up treasure from ground and puts it in inventory
		
		// player now has 1 treasure
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key);		// player has key in inventory
		assertEquals(player.getWeapon(), sword);	// player has sword in inventory
		assertEquals(player.getTreasure(), 2);	// player has 2 treasure
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key);		// player has key in inventory
		assertEquals(player.getWeapon(), sword);	// player has sword in inventory
		assertEquals(player.getTreasure(), 3);	// player has 3 treasure
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key);		// player has key in inventory
		assertEquals(player.getWeapon(), sword);	// player has sword in inventory
		assertEquals(player.getTreasure(), 4);	// player has 4 treasure
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key);		// player has key in inventory
		assertEquals(player.getWeapon(), sword);	// player has sword in inventory
		assertEquals(player.getTreasure(), 5);	// player has 5 treasure
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(player.getKey(), key);		// player has key in inventory
		assertEquals(player.getWeapon(), sword);	// player has sword in inventory
		assertEquals(player.getTreasure(), 6);	// player has 6 treasure
	}

}
