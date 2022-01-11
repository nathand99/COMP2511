package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

/*
 * Testing OS1: Display the Goal
 */
public class GoalTest {
	
	/*
	 * Test to determine if a single goal can be added to goals.
	 */
	@Test
	void testSingleGoal() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		PlayerGoal masterGoal = new PlayerGoal(player);				// initialise new goal.
		player.setGoals(masterGoal); 								// set it as the goal.
		SingleGoal s1 = new SingleGoal("exit", 1);					// set a new single goal to exit once.
		masterGoal.addGoal(s1); 									// adds the goal.
		
		assertEquals(masterGoal.getGoal().size(), 1);
		assertEquals(masterGoal.getGoal().get(0), s1);
	}
	
	/*
	 * Test to determine if a multiple goal can be added.
	 */
	@Test
	void testMultipleGoal() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		PlayerGoal masterGoal = new PlayerGoal(player);				// initialise new goal.
		player.setGoals(masterGoal); 								// set it as the goal.
		MultipleGoal m1 = new MultipleGoal("and");					// new multiple (and) goal
		masterGoal.addGoal(m1);
		SingleGoal s1 = new SingleGoal("exit", 1);					// new singleGoal.
		SingleGoal s2 = new SingleGoal("enemy", 2);					// new singleGoal.
		m1.addGoal(s1);
		m1.addGoal(s2); 											// adds the 2 goals.
		
		assertEquals(masterGoal.getGoal().size(), 1);
		assertEquals(m1.getGoals().size(), 2);
		assertEquals(masterGoal.getGoal().get(0), m1);
		assertEquals(m1.getGoals().get(0), s1);
		assertEquals(m1.getGoals().get(1), s2);
	}
	
	/*
	 * Tests for a goal (Exit and (Kill 2 or Switch 1))
	 */
	@Test
	void testMultiMultiGoal() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		PlayerGoal masterGoal = new PlayerGoal(player);				// initialise new goal.
		player.setGoals(masterGoal); 								// set it as the goal.
		MultipleGoal m1 = new MultipleGoal("and");					// new multiple (and) goal
		masterGoal.addGoal(m1);
		SingleGoal s1 = new SingleGoal("exit", 1);					// new singleGoal.
		m1.addGoal(s1);
		MultipleGoal m2 = new MultipleGoal("or");
		m1.addGoal(m2);
		SingleGoal s2 = new SingleGoal("enemy", 2);					// new singleGoal.
		SingleGoal s3 = new SingleGoal("switch", 1);
		m2.addGoal(s2); 											// adds the 2 goals.
		m2.addGoal(s3);
		
		assertEquals(masterGoal.getGoal().size(), 1);
		assertEquals(m1.getGoals().size(), 2);
		assertEquals(m2.getGoals().size(), 2);
		assertEquals(masterGoal.getGoal().get(0), m1);
		assertEquals(m1.getGoals().get(0), s1);
		assertEquals(m1.getGoals().get(1), m2);
		assertEquals(m2.getGoals().get(0), s2);
		assertEquals(m2.getGoals().get(1), s3);
	}
	
	/*
	 * Tests if the player wins.
	 */
	@Test
	void testWin() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		PlayerGoal masterGoal = new PlayerGoal(player);				// initialise new goal.
		player.addGoals(masterGoal);
		SingleGoal s1 = new SingleGoal("exit", 0);					// new singleGoal that is completed.
		masterGoal.addGoal(s1);
		
		assertEquals(masterGoal.checkCompletion(), true);
	}
	
	/*
	 * Tests if the player wins, with an or statement.
	 */
	@Test
	void testMultiWinOr() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		PlayerGoal masterGoal = new PlayerGoal(player);				// initialise new goal.
		player.addGoals(masterGoal);
		MultipleGoal m1 = new MultipleGoal("or");
		masterGoal.addGoal(m1);
		SingleGoal s1 = new SingleGoal("exit", 0);					// new singleGoal that is completed.
		SingleGoal s2 = new SingleGoal("enemy", 1);
		m1.addGoal(s1);
		m1.addGoal(s2);
		
		assertEquals(masterGoal.checkCompletion(), true);
	}
	
	/*
	 * Tests if the player wins, with an and statement.
	 */
	@Test
	void testMultiWinAnd() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		PlayerGoal masterGoal = new PlayerGoal(player);				// initialise new goal.
		player.addGoals(masterGoal);
		MultipleGoal m1 = new MultipleGoal("and");
		masterGoal.addGoal(m1);
		SingleGoal s1 = new SingleGoal("exit", 0);					// new singleGoal that is completed.
		SingleGoal s2 = new SingleGoal("enemy", 0);					// both are done.
		m1.addGoal(s1);
		m1.addGoal(s2);
		
		assertEquals(masterGoal.checkCompletion(), true);
	}
	
	/*
	 * Tests if the player only has to exit.
	 */
	@Test
	void testSingleExit() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		PlayerGoal masterGoal = new PlayerGoal(player);				// initialise new goal.
		player.addGoals(masterGoal);
		SingleGoal s1 = new SingleGoal("exit", 1);					// new singleGoal that is completed.
		masterGoal.addGoal(s1);
		
		assertEquals(masterGoal.checkCompletion(), false);			// haven't won
		assertEquals(masterGoal.onlyExit(), true);					// but only needs to exit.
	}
	
	/*
	 * Tests if the player only needs to exit, with an or statement.
	 */
	@Test
	void testMultiExitOr() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		PlayerGoal masterGoal = new PlayerGoal(player);				// initialise new goal.
		player.addGoals(masterGoal);
		MultipleGoal m1 = new MultipleGoal("or");
		masterGoal.addGoal(m1);
		SingleGoal s1 = new SingleGoal("exit", 1);					// need to exit
		SingleGoal s2 = new SingleGoal("enemy", 1);					// or kill 1.
		m1.addGoal(s1);
		m1.addGoal(s2);
		
		assertEquals(masterGoal.checkCompletion(), false);
		assertEquals(masterGoal.onlyExit(), true);
	}
	
	/*
	 * Tests if the player only needs to exit, with an and statement.
	 */
	@Test
	void testMultiExitAnd() {
		Dungeon dungeon = new Dungeon(10,10);						// initialise dungeon
		Player player = new Player(dungeon, 0, 0, new Moveable());	// initialise player in the dungeon		
		dungeon.setPlayer(player); 									// set the player in the dungeon
		PlayerGoal masterGoal = new PlayerGoal(player);				// initialise new goal.
		player.addGoals(masterGoal);
		MultipleGoal m1 = new MultipleGoal("and");
		masterGoal.addGoal(m1);
		SingleGoal s1 = new SingleGoal("exit", 1);					// need to exit
		SingleGoal s2 = new SingleGoal("enemy", 1);					// and kill 1.
		m1.addGoal(s1);
		m1.addGoal(s2);
		
		assertEquals(masterGoal.checkCompletion(), false);
		assertEquals(masterGoal.onlyExit(), false);
		
		s2.setRemaining(0);   										// sets remaining kills to 0.
		assertEquals(masterGoal.checkCompletion(), false);
		assertEquals(masterGoal.onlyExit(), true);
	}
	
	/*
	 * Assert that the 4 elements: enemy, treasure, exit and floor switch are all checked.
	 */
	@Test
	void testElementTrack() {
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
		SingleGoal s4 = new SingleGoal("boulders", 1);				// and 1 switch.
		m1.addGoal(s1);
		m1.addGoal(s2);
		m1.addGoal(s3);
		m1.addGoal(s4);
		
		Treasure treasure = new Treasure(dungeon, 0, 1, new Collectable());
		dungeon.addEntity(treasure);
		Sword sword = new Sword(dungeon, 0, 2, new Collectable());
		dungeon.addEntity(sword);
		Henchman enemy1 = new Henchman(dungeon, 0, 3, new Interactable() );
		dungeon.addEntity(enemy1);
		Boulder boulder = new Boulder(dungeon, 0, 4, new Interactable());
		dungeon.addEntity(boulder);
		FloorSwitch switch1 = new FloorSwitch(dungeon, 0, 5, new Moveable());
		dungeon.addEntity(switch1);
		Exit exit = new Exit(dungeon, 1, 4, new Interactable());
		dungeon.addEntity(exit);
		dungeon.registerNoMove();
		
		assertEquals(masterGoal.checkCompletion(), false);
		assertEquals(masterGoal.onlyExit(), false);
		
		player.moveDown(); // picks up the treasure
		try {Thread.sleep(400);}
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		
		player.moveDown(); // picks up the sword
		player.attackS(); // kills the enemy
		
		try {Thread.sleep(400);}
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		
		player.moveDown(); // moves down
		
		try {Thread.sleep(400);}
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		
		player.moveDown(); // pushes boulder onto switch
		
		try {Thread.sleep(400);}
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		
		assertEquals(masterGoal.onlyExit(), true);
		
		player.moveRight(); // exits.
		
		assertEquals(masterGoal.checkCompletion(), true);
	}
}
