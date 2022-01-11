package unsw.dungeon.Entities;

import java.util.List;

import javafx.scene.image.ImageView;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class Treasure extends Entity implements PickupItem, GoalObserver {
	
	Dungeon dungeon;
	Player player;

    /**
     * Create a treasure positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     */
    public Treasure(Dungeon dungeon, int x, int y, Movement movement) {
    	super (x, y, movement);
    	this.dungeon = dungeon;
    	this.player = null;
    	this.setEntityName("treasure");
    }
    
    @Override
    public void register() {
    	this.player = dungeon.getPlayer();
    	player.registerObserver(this);
    }
    
    /**
     * Sound function calls play method in sound effect class
     * on sound file 
     */
    public void treasureSound() {
		SoundEffects treasureSound = new SoundEffects();
		treasureSound.playSound("./sound/treasure.wav");
	}
    
	@Override
	public Entity pickup(Player p) {	
		// increment players treasure counter
		addTreasure(p);
		if (this.getEntityView() != null) {
			this.getEntityView().setVisible(false);
		}
		treasureSound();
		// remove picked up treasure from the dungeon
		dungeon.removeEntity(this);
		
		// you can only pick up treasure - cannot drop it, so return null
		return null;
	}

	@Override
	public void update(PlayerGoal goals, int[] playerXY) {
		// TODO Auto-generated method stub
		if (this.getY() == playerXY[1] && this.getX() == playerXY[0]) {
			goals.addComplete("treasure");
			player.removeObserver(this);
		}
	}
	
	/**
	 * adds 1 to the players treasure count.
	 */
	public void addTreasure(Player p) {
    	p.setTreasure(p.getTreasure() + 1);
    }

}
