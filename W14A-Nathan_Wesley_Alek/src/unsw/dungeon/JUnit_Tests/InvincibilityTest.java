package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/*
 * Tests PS6
 */
public class InvincibilityTest {
	/*
	 * Test to determine potion pickup, invincibility
	 */
	@Test
	void testInvincibility() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Invincibility i = new Invincibility(dungeon, 0, 1, new Collectable()); // the potion
		dungeon.addEntity(i);
		assertEquals(player.isNormalState(), true); // normal state
		player.moveDown();
		assertEquals(player.isNormalState(), false); // potion act
		try {Thread.sleep(11000);}					// potion times out
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		assertEquals(player.isNormalState(), true); // timed out potion.
	}
	
	/*
	 * Test to determine double potion pickup
	 */
	@Test
	void testDoubleInvincibility() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		Invincibility i = new Invincibility(dungeon, 0, 1, new Collectable()); // the potion
		dungeon.addEntity(i);
		Invincibility i2 = new Invincibility(dungeon, 0, 2, new Collectable()); // another potion
		dungeon.addEntity(i2);
		assertEquals(player.isNormalState(), true); // normal state
		player.moveDown();
		assertEquals(player.isNormalState(), false); // potion act
		try {Thread.sleep(400);}					// timer to move down
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		player.moveDown();							// pick up another potion, timer resets
		assertEquals(player.isNormalState(), false); // potion act
		try {Thread.sleep(11000);}					// potion times out
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		assertEquals(player.isNormalState(), true); // timed out potion.
	}
	
	// test for enemy dying is in enemy.
}
