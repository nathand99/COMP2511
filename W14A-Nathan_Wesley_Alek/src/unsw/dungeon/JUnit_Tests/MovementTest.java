package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/*
 * Testing PS1: Moving the Player
 */
public class MovementTest {
	/*
	 * Test to that a player must wait at least 0.4s to move.
	 */
	@Test
	void testMoveTimer() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);								// assert initial position
		
		player.moveDown();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);								// assert moved down
		
		player.moveDown();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);								// assert did not move down, as too fast.
		
		try {Thread.sleep(400);}									// wait for 0.4s
		catch(InterruptedException ex) { Thread.currentThread().interrupt();} 
		player.moveDown();											// then move
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 2);								// assert moved down
	}
	
	/*
	 * Test to that a player can move in all 4 cardinal directions
	 */
	@Test
	void testMoveDirections() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);								// assert initial position
		
		player.moveDown();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);								// assert moved down
		
		try {Thread.sleep(400);}									// wait for 0.4s
		catch(InterruptedException ex) { Thread.currentThread().interrupt();} 
		player.moveUp();											// then move
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);								// assert moved up
		
		try {Thread.sleep(400);}									// wait for 0.4s
		catch(InterruptedException ex) { Thread.currentThread().interrupt();} 
		player.moveRight();											// then move
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);								// assert moved right
		
		try {Thread.sleep(400);}									// wait for 0.4s
		catch(InterruptedException ex) { Thread.currentThread().interrupt();} 
		player.moveLeft();											// then move
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);								// assert moved left
	}
}
