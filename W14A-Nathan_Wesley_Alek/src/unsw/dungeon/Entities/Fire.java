package unsw.dungeon.Entities;

import java.util.List;

import unsw.dungeon.Entity;
import unsw.dungeon.Interactable;
import unsw.dungeon.Moveable;
import unsw.dungeon.Movement;
import unsw.dungeon.SoundEffects;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Entities.Player;

/**
 * Fire, for the fire on the trap.
 * @author z5207990
 *
 */
public class Fire extends Entity {
	
	private Dungeon dungeon;
	private Player player;
	
	public Fire(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.setEntityName("fire");
		//this.player = dungeon.getPlayer();
		
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Deactivates the fire.
	 */
	public void deactivate() {
		setMovement(new Moveable());
		this.getEntityView().setVisible(false);
	}
	
	/**
     * Sound function calls play method in sound effect class
     * on sound file 
     * > every time fire animation appears
     */
	public void fireSound() {
		SoundEffects fireSound = new SoundEffects();
		fireSound.playSound("./sound/fire1.wav");
	}
	
     /**
 	 * Activates the fire, killing players.
 	 */
	public void activate() {
		checkKill();
		setMovement(new Interactable());
		this.getEntityView().setVisible(true);
		fireSound();
		try {Thread.sleep(300);}					// lasts 0.3s
		catch(InterruptedException ex) { Thread.currentThread().interrupt();}
		deactivate();
	}
	
	/**
	 * Checks if the player is killed.
	 */
	public void checkKill() {
		if (this.getX() == player.getX() && this.getY() == player.getY()) {
			if (player.isNormalState() == true) {
				player.die();
			}
		}
		//List<Entity> onSquare = dungeon.getCurrentEntity(getX(), getY());
		// System.out.println(onSquare.size());
		/*for (Entity entity : onSquare) {
			if (entity instanceof Enemy) {
				((Enemy) entity).stopTimer();
				((Enemy) entity).death(player.getGoals());
			} else if (entity instanceof Player) {
				if (player.isNormalState() == true) {
					this.player.die();
				}
			}
		}*/
		/*System.out.println("Player X " + player.getX());
		if (player.getX() == this.getX() && player.getY() == this.getY()) {
			player.die();
		}*/
	}
}
