package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/*
 * Testing ES1: Wall Interaction
 */
public class WallTest {
	/*
	 * Testing player cannot move into a wall.
	 */
	@Test
	void testWallPlayer() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);								// assert initial position
		Wall wall = new Wall(0, 1, new Immovable());				// new wall 1 square below
		dungeon.addEntity(wall);
		
		player.moveDown();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);								// assert could not move
	}
	
	/*
	 * Testing player cannot push boulder into wall.
	 */
	@Test
	void testWallBoulder() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);								// assert initial position
		
		Boulder boulder = new Boulder(dungeon, 0, 1, new Interactable()); // boulder 1 below player.
		dungeon.addEntity(boulder);
		
		Wall wall = new Wall(0, 2, new Immovable());				// new wall 2 squares below
		dungeon.addEntity(wall);
		
		player.moveDown();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);								// assert could not move
	}
	
	// enemies should never walk into wall due to ai, tested in enemy tests.
}
