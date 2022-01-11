package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/*
 * Tests ENS1
 */
public class EnemyTest {
	/*
	 * Tests that the player will be approached by the enemy.
	 */
	@Test
	void TestEnemyApproach() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Henchman e = new Henchman(dungeon, 0, 3, new Interactable());
		dungeon.addEntity(e);
		assertEquals(e.getX(), 0);
		assertEquals(e.getY(), 3);
		dungeon.registerNoMove();
		e.singleMove();
		assertEquals(e.getX(), 0);
		assertEquals(e.getY(), 2);
	}
	
	/*
	 * Test that enemy will approach every 800 millisecs (approx)
	 */
	@Test
	void TestTimedEnemyApproach() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Henchman e = new Henchman(dungeon, 0, 3, new Interactable());
		dungeon.addEntity(e);
		assertEquals(e.getX(), 0);
		assertEquals(e.getY(), 3);
		dungeon.registerAll();
		try {Thread.sleep(850);}									// gives some leeway
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		assertEquals(e.getX(), 0);
		assertEquals(e.getY(), 2);
		
	}
	
	/*
	 * Tests that the player will be approached by the enemy, but not diagonally
	 */
	@Test
	void TestEnemyNoDiagonal() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Henchman e = new Henchman(dungeon, 1, 1, new Interactable());
		dungeon.addEntity(e);
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 1);
		dungeon.registerNoMove();
		e.singleMove();
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 0);									// if diagonal, will just move onto player
	}
	
	/*
	 * Tests that enemy can move through unlocked doors
	 */
	@Test
	void TestEnemyUnlockedDoor() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Henchman e = new Henchman(dungeon, 1, 1, new Interactable());
		dungeon.addEntity(e);
		Door d = new Door(dungeon, 1, 0, 1, new Moveable()); 		// unlocked door
		dungeon.addEntity(d);
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 1);
		dungeon.registerNoMove();
		e.singleMove();
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 0);									// will move into door.
	}
	
	/*
	 * Tests that enemy can move through floorSwitches
	 */
	@Test
	void TestEnemySwitch() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Henchman e = new Henchman(dungeon, 1, 1, new Interactable());
		dungeon.addEntity(e);
		FloorSwitch s = new FloorSwitch(dungeon, 1, 0, new Moveable()); 		// unlocked door
		dungeon.addEntity(s);
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 1);
		dungeon.registerNoMove();
		e.singleMove();
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 0);									// will move into door.
	}
	
	/*
	 * Tests that enemy can move through exits
	 */
	@Test
	void TestEnemyExit() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Henchman e = new Henchman(dungeon, 1, 1, new Interactable());
		dungeon.addEntity(e);
		Exit ex = new Exit(dungeon, 1, 0, new Interactable()); 		// exit
		dungeon.addEntity(ex);
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 1);
		dungeon.registerNoMove();
		e.singleMove();
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 0);									// will move into door.
	}
	
	/*
	 * Tests that enemy can move through portals, but don't use
	 */
	@Test
	void TestEnemyPortal() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Henchman e = new Henchman(dungeon, 1, 1, new Interactable());
		dungeon.addEntity(e);
		Portal ex = new Portal(dungeon, 1, 0, 0, new Interactable()); 		// portal
		dungeon.addEntity(ex);
		Portal ex2 = new Portal(dungeon, 5, 0, 0, new Interactable()); 		// portal
		dungeon.addEntity(ex2);
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 1);
		dungeon.registerNoMove();
		e.singleMove();
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 0);									// will move into portal
	}
	
	/*
	 * Tests that the player will be approached by the enemy in the shortest path
	 */
	@Test
	void TestEnemyShortestPath() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 1, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Henchman e = new Henchman(dungeon, 3, 2, new Interactable());
		dungeon.addEntity(e);
		Wall w1 = new Wall(1, 1, new Immovable()); // wall at 1,1
		dungeon.addEntity(w1);
		Wall w2 = new Wall (2, 1, new Immovable()); // wall at 0,2
		dungeon.addEntity(w2);
		
		assertEquals(e.getX(), 3);
		assertEquals(e.getY(), 2);
		dungeon.registerNoMove();
		e.singleMove();
		assertEquals(e.getX(), 3);
		assertEquals(e.getY(), 1);				
		//[ ,  ,  ]
		//[P, W,  ]
		//[ , W,  ]
		//[ ,  , E]
		// enemy will go to the left
	}
	
	/*
	 * Tests that the enemy kills player if they move onto them
	 */
	@Test
	void TestEnemyKill() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Henchman e = new Henchman(dungeon, 1, 0, new Interactable());
		dungeon.addEntity(e);

		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 0);
		dungeon.registerNoMove();
		e.singleMove();
		assertEquals(e.getX(), 0);
		assertEquals(e.getY(), 0);				
		assertEquals(dungeon.getPlayer(), null);					// no player
	}
	
	/*
	 * Tests enemy will run away while player is invincible
	 * Also tests the enemy runs down instead of right.
	 */
	@Test
	void TestEnemyRun() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 	
		player.setNormalState(false);								// set the player to invincible
		Henchman e = new Henchman(dungeon, 1, 0, new Interactable());
		dungeon.addEntity(e);

		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 0);
		dungeon.registerNoMove();
		e.singleMove();
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 1);				
	}
	
	/*
	 * Tests enemy will run away while player is invincible, but not move if no move gets further away
	 * also tests locked doors and boulders are unmoveable
	 */
	@Test
	void TestEnemyBlockedRun() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 	
		player.setNormalState(false);								// set the player to invincible
		Henchman e = new Henchman(dungeon, 1, 0, new Interactable());
		dungeon.addEntity(e);
		Boulder b1 = new Boulder(dungeon, 1, 1, new Interactable()); // wall at 1,1
		dungeon.addEntity(b1);
		Door d = new Door (dungeon, 2, 0, 1, new Interactable()); // wall at 0,2
		dungeon.addEntity(d);

		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 0);
		dungeon.registerNoMove();
		e.singleMove();
		assertEquals(e.getX(), 1);
		assertEquals(e.getY(), 0);				
	}
	
	/*
	 * Asserts enemy die if player with invincibility walks in
	 */
	@Test
	void TestEnemyDie() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.addEntity(player);
		dungeon.setPlayer(player); 	
		player.setNormalState(false);								// set the player to invincible
		Henchman e = new Henchman(dungeon, 0, 1, new Interactable());
		dungeon.addEntity(e);
		

		assertEquals(e.getX(), 0);
		assertEquals(e.getY(), 1);
		dungeon.registerNoMove();
		player.moveDown();		
		assertEquals(dungeon.getEntities().size(), 1);				// assert only a player entity left.
	}
	
}
