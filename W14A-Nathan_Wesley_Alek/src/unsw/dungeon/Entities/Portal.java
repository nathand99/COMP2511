package unsw.dungeon.Entities;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class Portal extends Entity{
	private int id; 
	private Dungeon dungeon;
	
	/**
	 * Portal to teleport player
	 * @param dungeon
	 * @param x
	 * @param y
	 * @param id
	 * @param movement
	 */
	public Portal(Dungeon dungeon,int x, int y, int id, Movement movement) {
		super(x, y, movement);
		// TODO Auto-generated constructor stub
		this.id = id;
		this.dungeon = dungeon;
		this.setEntityName("portal");
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * bestTele - teleports the player onto the portal.
	 * @param player
	 * @return always returns false, just sets the player square or not.
	 */
	public boolean bestTele(Player player) {
		// TODO Auto-generated method stub
		
		for(Entity e : dungeon.getEntities()) {
			if (e == null) continue;
			if(e.getEntityName().equals("portal") && ((Portal) e).getId() == getId() && e != this) {
				// this code can be used to just set the player x-y to the portal.
				teleSound();
				player.x().set(e.getX());
				player.y().set(e.getY());
			}
		}
		return false;
	}
	
	/**
     * Sound function calls play method in sound effect class
     * on sound file 
     * > for when player uses portal
     */
	public void teleSound() {
		SoundEffects teleSound = new SoundEffects();
		teleSound.playSound("./sound/teleport.wav");
	}
	

}
