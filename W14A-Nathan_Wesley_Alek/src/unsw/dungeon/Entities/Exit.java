package unsw.dungeon.Entities;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class Exit extends Entity implements GoalObserver {
	
	private Dungeon dungeon;
	private Player player;
	
	/**
	 * 
	 * @param dungeon
	 * @param x
	 * @param y
	 * @param movement
	 */
	public Exit(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.player = null;
		this.setEntityName("exit");
	}
	
	/**
	 * update - updates goals if possible.
	 */
	@Override
	public void update(PlayerGoal goals, int[] playerXY) {
		if (this.getX() == playerXY[0] && this.getY() == playerXY[1]) {
			if (player.canExit()) {
				goals.addComplete("exit");
			}
		}
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		this.player = dungeon.getPlayer();
		player.registerObserver(this);
		
	}
	
}
