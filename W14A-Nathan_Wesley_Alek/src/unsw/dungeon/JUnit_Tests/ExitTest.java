package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/*
 * Tests if the player can use the exit. (PS9).
 */
public class ExitTest {
	// testing successful exit has already been done in goalTests.
	
	/*
	 * Assert that the 4 elements: enemy, treasure, exit and floor switch are all checked.
	 */
	@Test
	void testFailExit() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		PlayerGoal masterGoal = new PlayerGoal(player);				// initialise new goal.
		player.addGoals(masterGoal);
		MultipleGoal m1 = new MultipleGoal("and");
		masterGoal.addGoal(m1);
		SingleGoal s1 = new SingleGoal("exit", 1);					// need to exit
		SingleGoal s2 = new SingleGoal("enemy", 1);					// and kill 1.
		SingleGoal s3 = new SingleGoal("treasure", 1);				// and 1 treasure
		SingleGoal s4 = new SingleGoal("switch", 1);				// and 1 switch.
		m1.addGoal(s1);
		m1.addGoal(s2);
		m1.addGoal(s3);
		m1.addGoal(s4);
		
		Exit exit = new Exit(dungeon, 1, 4, new Interactable());
		dungeon.addEntity(exit);
		dungeon.registerNoMove();
		
		assertEquals(masterGoal.checkCompletion(), false);
		assertEquals(masterGoal.onlyExit(), false);
		
		player.moveDown(); 
		try {Thread.sleep(400);}
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		
		player.moveDown();  
		
		try {Thread.sleep(400);}
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		
		player.moveDown(); // moves down
		
		try {Thread.sleep(400);}
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		
		player.moveDown(); 
		
		try {Thread.sleep(400);}
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		
		assertEquals(masterGoal.onlyExit(), false);
		
		player.moveRight(); 
		
		assertEquals(masterGoal.checkCompletion(), false); // fails to exit
	}
}
