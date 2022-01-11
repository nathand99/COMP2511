package unsw.dungeon.Old_Tests;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.*;

public class TestGoal {
	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10,10);
		Player player = new Player(dungeon, 0, 0, new Moveable());
		PlayerGoal gS = new PlayerGoal(player);
		player.addGoals(gS);
		MultipleGoal m1 = new MultipleGoal("and");
		SingleGoal s1 = new SingleGoal("exit", 1);
		m1.addGoal(s1);
		MultipleGoal m2 = new MultipleGoal("or");
		m1.addGoal(m2);
		SingleGoal s2 = new SingleGoal("kill", 5);
		SingleGoal s3 = new SingleGoal("switch", 2);
		m2.addGoal(s2);
		m2.addGoal(s3);
		gS.addGoal(m1);
		System.out.println(player.getGoals().checkCompletion());
		System.out.println(player.getGoals().onlyExit());
		player.getGoals().printGoals();
		s2.setRemaining(0);
		s3.setRemaining(0);
		System.out.println(player.getGoals().checkCompletion());
		System.out.println(player.getGoals().onlyExit());
		player.getGoals().printGoals();
		s1.setRemaining(0);
		System.out.println(player.getGoals().checkCompletion());
		System.out.println(player.getGoals().onlyExit());
		player.getGoals().printGoals();
		
		PlayerGoal gS2 = new PlayerGoal(player);
		player.addGoals(gS2);
		MultipleGoal m3 = new MultipleGoal("or");
		gS2.addGoal(m3);
		SingleGoal s4 = new SingleGoal("kill", 4);
		SingleGoal s5 = new SingleGoal("exit", 1);
		m3.addGoal(s4);
		m3.addGoal(s5);
		System.out.println(player.getGoals().checkCompletion());
		System.out.println(player.getGoals().onlyExit());
		player.getGoals().printGoals();
		s4.setRemaining(0);
		System.out.println(player.getGoals().checkCompletion());
		System.out.println(player.getGoals().onlyExit());
		player.getGoals().printGoals();
	}
}
