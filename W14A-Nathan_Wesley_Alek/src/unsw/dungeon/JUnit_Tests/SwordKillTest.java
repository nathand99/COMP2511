package unsw.dungeon.JUnit_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

public class SwordKillTest {

	
	/**
	 * tests to see if it will kill an enemy above using (W)
	 * with no sword. Should not kill the enemy. 
	 */
	@Test
	void testKillNoSwordW() { 
		//dungeon
		Dungeon dungeon = new Dungeon(10,10);
		
		//player 
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		
		//goals 
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		//enemies 
		Henchman enemy1 = new Henchman(dungeon, 1, 0, new Interactable() );
		dungeon.addEntity(enemy1);
		Henchman enemy2 = new Henchman(dungeon, 0, 2, new Interactable());
		dungeon.addEntity(enemy2);
		Henchman enemy3 = new Henchman(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		Henchman enemy4 = new Henchman(dungeon, 1, 3, new Interactable());
		dungeon.addEntity(enemy4);
		dungeon.registerNoMove();
		//player starts at 1,1
		
		// do i have sword 
		assertEquals(player.getWeapon(), null);
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
		//attack
		player.attackW();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//check if enemies are still there

		assertEquals(dungeon.isEnemy(1, 0), true);

		assertEquals(dungeon.isEnemy(0, 2), true);

		assertEquals(dungeon.isEnemy(2, 2), true);
	
		assertEquals(dungeon.isEnemy(1, 3), true);
		
	}
	
	/**
	 * tests to see if it will kill an enemy on the left using (A)
	 * with no sword. Should not kill the enemy. 
	 */
	@Test
	void testKillNoSwordA() { 
		//dungeon
		Dungeon dungeon = new Dungeon(10,10);
		
		//player 
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		
		//goals 
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		//enemies 
		Henchman enemy1 = new Henchman(dungeon, 1, 0, new Interactable() );
		dungeon.addEntity(enemy1);
		Henchman enemy2 = new Henchman(dungeon, 0, 2, new Interactable());
		dungeon.addEntity(enemy2);
		Henchman enemy3 = new Henchman(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		Henchman enemy4 = new Henchman(dungeon, 1, 3, new Interactable());
		dungeon.addEntity(enemy4);
		dungeon.registerNoMove();
		//player starts at 1,1

		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
		
		//check sword 
		assertEquals(player.getWeapon(), null);
		
		//move down then attack 
		player.moveDown();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackA();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//check sword 
		assertEquals(player.getWeapon(), null);
		
		//check if enemies are still there
	
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
		
	}
	
	/**
	 * tests to see if it will kill an enemy below using (S)
	 * with no sword. Should not kill the enemy. 
	 */
	@Test
	void testKillNoSwordS() { 
		//dungeon
		Dungeon dungeon = new Dungeon(10,10);
		
		//player 
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		
		//goals 
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);

		//enemies 
		Henchman enemy1 = new Henchman(dungeon, 1, 0, new Interactable() );
		dungeon.addEntity(enemy1);
		Henchman enemy2 = new Henchman(dungeon, 0, 2, new Interactable());
		dungeon.addEntity(enemy2);
		Henchman enemy3 = new Henchman(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		Henchman enemy4 = new Henchman(dungeon, 1, 3, new Interactable());
		dungeon.addEntity(enemy4);
		dungeon.registerNoMove();
		//player starts at 1,1

		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
		
		//check sword 
		assertEquals(player.getWeapon(), null);
		
		//move down and attack
		player.moveDown();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackS();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//check sword
		assertEquals(player.getWeapon(), null);
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
	}
	
	/**
	 * tests to see if it will kill an enemy on the right using (D)
	 * with no sword. Should not kill the enemy. 
	 */
	@Test
	void testKillNoSwordD() { 
		//dungeon
		Dungeon dungeon = new Dungeon(10,10);
		
		//player 
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		
		//goals 
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		//enemies 
		Henchman enemy1 = new Henchman(dungeon, 1, 0, new Interactable() );
		dungeon.addEntity(enemy1);
		Henchman enemy2 = new Henchman(dungeon, 0, 2, new Interactable());
		dungeon.addEntity(enemy2);
		Henchman enemy3 = new Henchman(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		Henchman enemy4 = new Henchman(dungeon, 1, 3, new Interactable());
		dungeon.addEntity(enemy4);
		dungeon.registerNoMove();
		//player starts at 1,1

		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
		
		//check sword
		assertEquals(player.getWeapon(), null);
		
		//mpve down and attack
		player.moveDown();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackD();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//check sword
		assertEquals(player.getWeapon(), null);
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
	}
	
	/**
	 * tests to see if it will kill an enemy above using (W)
	 * with sword. Should kill the enemy. 
	 */
	@Test
	void testKillEnemysWithSwordW() {
		//dungeon
		Dungeon dungeon = new Dungeon(10,10);
		
		//player 
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		
		//goals 
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		//sword
		Sword sword = new Sword(dungeon, 1, 2, new Collectable());
		dungeon.addEntity(sword);
		
		//enemies 
		Henchman enemy1 = new Henchman(dungeon, 1, 0, new Interactable() );
		dungeon.addEntity(enemy1);
		Henchman enemy2 = new Henchman(dungeon, 0, 2, new Interactable());
		dungeon.addEntity(enemy2);
		Henchman enemy3 = new Henchman(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		Henchman enemy4 = new Henchman(dungeon, 1, 3, new Interactable());
		dungeon.addEntity(enemy4);
		dungeon.registerNoMove();
		//player starts at 1,1

		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
		
		//mpve down pick up sword, move back up and attack
		player.moveDown();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}

		player.moveUp();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackW();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), false);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
		
	}
	
	/**
	 * tests to see if it will kill an enemy on the left using (A)
	 * with sword. Should kill the enemy. 
	 */
	@Test
	void testKillEnemysWithSwordA() {
		//dungeon
		Dungeon dungeon = new Dungeon(10,10);
		
		//player 
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		
		//goals 
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		//sword
		Sword sword = new Sword(dungeon, 1, 2, new Collectable());
		dungeon.addEntity(sword);
		
		//enemies 
		Henchman enemy1 = new Henchman(dungeon, 1, 0, new Interactable() );
		dungeon.addEntity(enemy1);
		Henchman enemy2 = new Henchman(dungeon, 0, 2, new Interactable());
		dungeon.addEntity(enemy2);
		Henchman enemy3 = new Henchman(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		Henchman enemy4 = new Henchman(dungeon, 1, 3, new Interactable());
		dungeon.addEntity(enemy4);
		dungeon.registerNoMove();
		//player starts at 1,1
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
		
		//mpve down pick up sword and attack
		player.moveDown();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackA();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), false);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
	}
	
	/**
	 * tests to see if it will kill an enemy below using (S)
	 * with sword. Should kill the enemy. 
	 */
	@Test
	void testKillEnemysWithSwordS() {
		//dungeon
		Dungeon dungeon = new Dungeon(10,10);
		
		//player 
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		
		//goals 
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		//sword
		Sword sword = new Sword(dungeon, 1, 2, new Collectable());
		dungeon.addEntity(sword);
		
		//enemies 
		Henchman enemy1 = new Henchman(dungeon, 1, 0, new Interactable() );
		dungeon.addEntity(enemy1);
		Henchman enemy2 = new Henchman(dungeon, 0, 2, new Interactable());
		dungeon.addEntity(enemy2);
		Henchman enemy3 = new Henchman(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		Henchman enemy4 = new Henchman(dungeon, 1, 3, new Interactable());
		dungeon.addEntity(enemy4);
		dungeon.registerNoMove();
		//player starts at 1,1
	
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
		
		//mpve down pick up sword and attack
		player.moveDown();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackS();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), false);
	}
	
	/**
	 * tests to see if it will kill an enemy on the right using (D)
	 * with sword. Should kill the enemy. 
	 */
	@Test
	void testKillEnemysWithSwordD() {
		//dungeon
		Dungeon dungeon = new Dungeon(10,10);
		
		//player 
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		
		//goals 
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		//sword
		Sword sword = new Sword(dungeon, 1, 2, new Collectable());
		dungeon.addEntity(sword);
		
		//enemies 
		Henchman enemy1 = new Henchman(dungeon, 1, 0, new Interactable() );
		dungeon.addEntity(enemy1);
		Henchman enemy2 = new Henchman(dungeon, 0, 2, new Interactable());
		dungeon.addEntity(enemy2);
		Henchman enemy3 = new Henchman(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		Henchman enemy4 = new Henchman(dungeon, 1, 3, new Interactable());
		dungeon.addEntity(enemy4);
		dungeon.registerNoMove();
		//player starts at 1,1
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
				
		//mpve down pick up sword and attack
		player.moveDown();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackD();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), false);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
	}
	
	/**
	 * sword can only kill max five enemies
	 */
	@Test
	void testKill5Max() {
		//dungeon
		Dungeon dungeon = new Dungeon(10,10);
		
		//player 
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		
		//goals 
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		//sword
		Sword sword = new Sword(dungeon, 1, 2, new Collectable());
		dungeon.addEntity(sword);
		
		//enemies 
		Henchman enemy1 = new Henchman(dungeon, 1, 0, new Interactable() );
		dungeon.addEntity(enemy1);
		Henchman enemy2 = new Henchman(dungeon, 0, 2, new Interactable());
		dungeon.addEntity(enemy2);
		Henchman enemy3 = new Henchman(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		Henchman enemy4 = new Henchman(dungeon, 1, 3, new Interactable());
		dungeon.addEntity(enemy4);
		Henchman enemy5 = new Henchman(dungeon, 2, 1, new Immovable());
		dungeon.addEntity(enemy5);
		Henchman enemy6 = new Henchman(dungeon, 3, 1, new Immovable());
		dungeon.addEntity(enemy6);
		dungeon.registerNoMove();
		//player starts at 1,1
		
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), true);
		
		assertEquals(dungeon.isEnemy(0, 2), true);
		
		assertEquals(dungeon.isEnemy(2, 2), true);
		
		assertEquals(dungeon.isEnemy(1, 3), true);
		
		assertEquals(dungeon.isEnemy(2, 1), true);
		
		assertEquals(dungeon.isEnemy(3, 1), true);
		//mpve down pick up sword and attack down, left and right
		player.moveDown();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackA();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackS();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackD();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		//move back up and attack above 
		player.moveUp();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackW();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//attack enemy 5
		player.attackD();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//move right and attack player 6 
		//should not attack as no swords because killed max enemies 
		player.moveRight();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackD();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(1, 0), false);
		
		assertEquals(dungeon.isEnemy(0, 2), false);
		
		assertEquals(dungeon.isEnemy(2, 2), false);
		
		assertEquals(dungeon.isEnemy(1, 3), false);
		
		assertEquals(dungeon.isEnemy(2, 1), false);
		
		assertEquals(dungeon.isEnemy(3, 1), true);
	}
	
	@Test
	void testSwing() {
		//dungeon
		Dungeon dungeon = new Dungeon(10,10);
		
		//player 
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		
		//goals 
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		//sword
		Sword sword = new Sword(dungeon, 1, 2, new Collectable());
		dungeon.addEntity(sword);
		
		//enemies 
	
		Henchman enemy3 = new Henchman(dungeon, 2, 2, new Interactable());
		dungeon.addEntity(enemy3);
		dungeon.registerNoMove();
		//player starts at 1,1
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(2, 2), true);
				
		//mpve down pick up sword
		player.moveDown();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//swing 5 times 
		player.attackA();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackA();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackA();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackA();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		player.attackA();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		// try and attack on the 6th swing but cant kill as no sword 
		player.attackD();
		try { Thread.sleep(400);}
		catch(InterruptedException ex){ Thread.currentThread().interrupt();}
		
		//check if enemies are still there
		assertEquals(dungeon.isEnemy(2, 2), true);
		
	}
}