package unsw.dungeon.Entities;

import java.util.Timer;
import java.util.TimerTask;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

/**
 * Invincibility - the invincibility potion.
 * 
 *
 */
public class Invincibility extends Entity implements PickupItem {
	Timer timer = new Timer();
	private int timeLimit = 10000;
	Dungeon dungeon;
	
	/**
	 * 
	 * @param dungeon
	 * @param x
	 * @param y
	 * @param movement
	 */
	public Invincibility(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.setEntityName("invincibility");
	}
	
	/**
	 * invincible - starts a timer of 10 seconds for the player's invincibility.
	 * @param player
	 */
	public boolean invincible(Player player) {
		//removes potion entity 
		//.... need to remove potion picture ....
		//.... need to change sprite of player ....
		player.setNormalState(false);
		player.setNumThreads(player.getNumThreads()+1);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//player back to normal state
				if(player.getNumThreads()==1) {
					//only goes back to normal state if one thread is active 
					//otherwise we would set state back to normal before the last 
					//potion was collected
					player.setNormalState(true);
					player.setNumThreads(player.getNumThreads()-1);
				}
				else {
					//another thread is active 
					player.setNumThreads(player.getNumThreads()-1);
				}	
			}
		}, timeLimit);
		return true;
	}

	@Override
	public Entity pickup(Player p) {
		// once player picks up potion, go invincible
		this.invincible(p);
		// remove potion from the dungeon
		if (this.getEntityView() != null) {
			this.getEntityView().setVisible(false);
		}
		invincibleSound();
		dungeon.removeEntity(this);
		return null;
	}
	
	/**
     * Sound function calls play method in sound effect class
     * on sound file 
     * >for when player picks potion up
     */
	public void invincibleSound() {
		SoundEffects invincibleSound = new SoundEffects();
		invincibleSound.playSound("./sound/potion.wav");
	}
	
}
