package unsw.dungeon.Old_Tests;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

public class TestSwordKill {

	
	public static void main(String[] args) {
		
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 1, 1, new Moveable());
		dungeon.setPlayer(player);
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("enemy", 7);
		gS.addGoal(s1);
		player.addGoals(gS);
		
		Sword sword = new Sword(dungeon, 1, 2, new Collectable());
		dungeon.addEntity(sword);
		
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
		
		// Attempts to kill enemy1 so above player with no sword 
		// Should not be able to kill player one 
		// and other players shouldn't die as well
		System.out.println("--------------Test 1: NO kill if NO Sword----------------");
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		player.attackW();
		//check if enemies are still there
		System.out.print("Test should NOT kill enemy1: ");
		if (dungeon.isEnemy(1, 0)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should NOT kill enemy2: ");
		if (dungeon.isEnemy(0, 2))System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should NOT kill enemy3: ");
		if (dungeon.isEnemy(2, 2)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should NOT kill enemy4: ");
		if (dungeon.isEnemy(1, 3)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		
		
		//move down to 1,2 
		player.moveDown();
		
		//attempt to kill the enemy with sword should pass
		//kill enemy2 swing left 
		System.out.println("\n--------------Test 2: Kill enemy to the left----------------");
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		player.attackA();
	
		System.out.print("Test should NOT kill enemy1: ");
		if (dungeon.isEnemy(1, 0)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should kill enemy2: ");
		if (!dungeon.isEnemy(0, 2)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should NOT kill enemy3: ");
		if (dungeon.isEnemy(2, 2)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should NOT kill enemy4: ");
		if (dungeon.isEnemy(1, 3)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		//kill enemy3 swing right
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		System.out.println("\n--------------Test 3: Kill enemy to the right----------------");
		player.attackD();
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		
		System.out.print("Test should NOT kill enemy1: ");
		if (dungeon.isEnemy(1, 0)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test enemy2 is still dead: ");
		if (!dungeon.isEnemy(0, 2)) System.out.println("PASS");
		else System.out.println("FAIL");

		System.out.print("Test should kill enemy3: ");
		if (!dungeon.isEnemy(2, 2)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should NOT kill enemy4: ");
		if (dungeon.isEnemy(1, 3)) System.out.println("PASS");
		else System.out.println("FAIL");
		//kill enemy4 swing down
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		
		System.out.println("\n--------------Test 4: Kill enemy below----------------");
		player.attackS();
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		
		System.out.print("Test should NOT kill enemy1: ");
		if (dungeon.isEnemy(1, 0)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test enemy2 is still dead: ");
		if (!dungeon.isEnemy(0, 2)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test enemy3 is still dead: ");
		if (!dungeon.isEnemy(2, 2)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should kill enemy4: ");
		if (!dungeon.isEnemy(1, 3)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		//move up to 1,1
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		
		System.out.println("\n--------------Test 5: Kill enemy above----------------");
		player.moveUp();
		//attempt to kill enemy1
		player.attackW();
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		//check if enemies are still there
		System.out.print("Test should kill enemy1: ");
		if (!dungeon.isEnemy(1, 0)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test enemy2 is still dead: ");
		if (!dungeon.isEnemy(0, 2)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test enemy3 is still dead: ");
		if (!dungeon.isEnemy(2, 2)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should kill enemy4: ");
		if (!dungeon.isEnemy(1, 3)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.println("\n--------------Test 6: Kill 5th Henchman and can't kill 6th----------------");
		//then add new enemy 5 and 6
		Henchman enemy5 = new Henchman(dungeon, 2, 1, new Immovable());
		dungeon.addEntity(enemy5);
		Henchman enemy6 = new Henchman(dungeon, 3, 1, new Immovable());
		dungeon.addEntity(enemy6);
		dungeon.registerNoMove();
		// should kill enemy 5 but not enemy 6 as no sword.
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.attackD();
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		
		System.out.print("Test should kill enemy5: ");
		if (!dungeon.isEnemy(2, 1)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should NOT kill enemy6: ");
		if (dungeon.isEnemy(3, 1)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		//move right
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveRight();
		player.attackD();
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		//get current entity in dun should help to see if entity exists
		
		System.out.print("Test enemy5 is still dead: ");
		if (!dungeon.isEnemy(2, 1)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.print("Test should NOT kill enemy6: ");
		if (dungeon.isEnemy(3, 1)) System.out.println("PASS");
		else System.out.println("FAIL");
		
		System.out.println("\n--------------Test 7: MAX 5 swings of sword----------------");
		Sword swordNew = new Sword(dungeon, 2, 2, new Collectable());
		dungeon.addEntity(swordNew);
		
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		System.out.println("Pick up sword");
		
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveDown();
		
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		
		
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.attackD();
		System.out.print("After Swing 1: ");
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.attackD();
		System.out.print("After Swing 2: ");
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.attackD();
		System.out.print("After Swing 3: ");
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.attackD();
		System.out.print("After Swing 4: ");
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.attackD();
		System.out.print("After Swing 5: ");
		if (player.getWeapon() == null) System.out.println("I have NO sword");
		if (player.getWeapon() != null) System.out.println("I have sword ");
		
	}
}
