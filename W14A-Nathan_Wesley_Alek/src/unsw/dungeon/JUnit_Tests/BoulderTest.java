package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/**
 * Tests boulders and floor switches
 * 
 * testing: PS3: Pushing Boulders and ES7: Boulder Interaction
 * 
 * testing: ES8: Floor Switch Interaction
 *
 */
class BoulderTest {
	
	/**
	 * If a player moves onto a square with a boulder, 
	 * the boulder is pushed 1 square in the corresponding direction of the player's movement onto it. 
	 * The player takes its previous square.
	 */
	@Test
	void testPushBoulder() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());			// initialise player in the dungeon (at 0,0)
		Boulder boulder = new Boulder(dungeon, 0, 1, new Interactable());
		dungeon.addEntity(boulder);											// add boulder to dungeon (at 0,1)
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(boulder.getX(), 0);	
		assertEquals(boulder.getY(), 2);		// boulder moves down 1 spot to 0,2 
		assertEquals(player.getX(), 0);	
		assertEquals(player.getY(), 1);			// player moves down one spot to 0,1 to where the boulder was
		
		// push boulder down another space
		player.moveDown();						// player moves down 1 space again
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(boulder.getX(), 0);	
		assertEquals(boulder.getY(), 3);		// boulder moves down 1 spot to 0,3 
		assertEquals(player.getX(), 0);	
		assertEquals(player.getY(), 2);			// player moves down one spot to 0,2 to where the boulder was
	}
	
	/**
	 * If a boulder is blocked in that direction then the boulder does not move, and the player does not move either.
	 * In this test, the boulder is blocked by a wall
	 */
	@Test
	void testBlockedBoulder() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());			// initialise player in the dungeon (at 0,0)		
		Boulder boulder = new Boulder(dungeon, 0, 1, new Interactable());	// create new boulder (at 0,1)
		Wall wall = new Wall(0, 2, new Immovable());						// create a new wall (at 0,2)
		dungeon.addEntity(wall);											// add wall to dungeon
		dungeon.addEntity(boulder);											// add boulder to dungeon
		
		player.moveDown();						// player attemps moves down 1 space to push the boulder
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(boulder.getX(), 0);	
		assertEquals(boulder.getY(), 1);		// boulder does not move since the wall blocks it (boulder is still at 0,1)
		assertEquals(player.getX(), 0);	
		assertEquals(player.getY(), 0);			// player does not move since the wall blocked the boulder (player is still at 0,0)
	}
	
	/**
	 * If the boulder is pushed onto a floor switch, it triggers the floor switch, and moves into the same square as the floor switch
	 * If a boulder moves onto the square a floor switch is in, then the floor switch is triggered.
	 * If a boulder moves off the square a floor switch is in, then the floor switch is untriggered.
	 */
	@Test
	void testPushBoulderOntoFloorSwitch() {
		Dungeon dungeon = new Dungeon(10,10);								// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());			// initialise player in the dungeon (at 0,0)
		Boulder boulder = new Boulder(dungeon, 0, 1, new Interactable());	// add boulder to dungeon (at 0,1)
		FloorSwitch fs = new FloorSwitch(dungeon, 0, 2, new Moveable());	// add FloorSwitch (at 0,2)
		dungeon.addEntity(boulder);	
		dungeon.addEntity(fs);	
		
		dungeon.setPlayer(player);				// attach observers
		dungeon.registerNoMove();
		
		assertEquals(fs.checkOnOff(),false); 	// floor switch is not triggered
		
		player.moveDown();						// player moves down 1 space
		try {
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(boulder.getX(), 0);	
		assertEquals(boulder.getY(), 2);		// boulder moves down 1 spot to 0,2 on top of the floor switch
		assertEquals(fs.getX(), 0);	
		assertEquals(fs.getY(), 2);				// floor switch is at 0,2
		
		assertEquals(fs.checkOnOff(),true);		// floor switch has been triggered by the boulder
		
		player.moveDown();						// player moves down 1 space again
		try {	
		    Thread.sleep(400);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		assertEquals(boulder.getX(), 0);	
		assertEquals(boulder.getY(), 3);		// boulder moves down 1 spot to 0,3 off the floor switch
		assertEquals(player.getX(), 0);	
		assertEquals(player.getY(), 2);			// player moves down 1 spot to 0,2 on top of the floor switch
		assertEquals(fs.getX(), 0);	
		assertEquals(fs.getY(), 2);				// floor switch is still at 0,2
		
		assertEquals(fs.checkOnOff(),false); 	// floor switch has been untriggered because the boulder has been pushed off it
	}

}
