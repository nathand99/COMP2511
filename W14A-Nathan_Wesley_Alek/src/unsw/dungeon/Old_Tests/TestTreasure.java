package unsw.dungeon.Old_Tests;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

public class TestTreasure {
	
	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 0, 0, new Moveable());
		dungeon.setPlayer(player);
		PlayerGoal gS = new PlayerGoal(player);
		SingleGoal s1 = new SingleGoal("treasure", 2);
		gS.addGoal(s1);
		player.addGoals(gS);
		Treasure treasure = new Treasure(dungeon, 0, 1, new Collectable());
		dungeon.addEntity(treasure);
		Treasure treasure2 = new Treasure(dungeon, 0, 2, new Collectable());
		dungeon.addEntity(treasure2);
		dungeon.registerAll();
		System.out.println(player);
		player.moveDown();
		if (player.getTreasure() == 0) {
			System.out.println("i have no treasure");
		}
		if (player.getTreasure() == 1) {
			System.out.println("got 1 treasure");
		}
		try
		{
		    Thread.sleep(400);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		player.moveDown();
		System.out.println("move down");
		if (player.getTreasure() == 2) {
			System.out.println("got 2 treasure");
		}
		gS.printGoals();
	}
}
