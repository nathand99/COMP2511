package unsw.dungeon.Entities;

import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class Key extends Entity implements PickupItem {
	
	private int keyID;	
	private Dungeon dungeon;
	
    /**
     * Create a keypositioned in square (x,y) with keyID
     * @param dungeon
     * @param x
     * @param y
     * @param keyID - ID of key
     */
    public Key(Dungeon dungeon, int x, int y, int keyID, Movement movement) {
    	super(x, y, movement);
    	this.dungeon = dungeon;
        this.keyID = keyID;
        this.setEntityName("key");
    }
    
    /**
     * pickup - picks the key up.
     * 
     * @return null if no key is swapped to the ground
     * 
     * @return key if player is swapping keys
     * 
     * also removes key being picked up from the dungeon
     */
	@Override
	public Entity pickup(Player p) {		
		// if player has no key, put this key in inventory - return null
		if (p.getKey() == null) {
			p.setKey(this);
			if (this.getEntityView() != null) {
				this.getEntityView().setVisible(false);
			}
			keySound();
			dungeon.removeEntity(this);
			return null;
		// if player has key, swap keys - return players key - to be placed on ground
		} else {			
			Key temp = p.getKey();
			temp.x().setValue(this.getX());
			temp.y().setValue(this.getY());
			if (this.getEntityView() != null) {
				temp.getEntityView().setVisible(true);
				this.getEntityView().setVisible(false);
			}
			keySound();
			dungeon.removeEntity(this);
			p.setKey(this);
			// drop key where the player is, with the ID of the key the player had
			return temp;			
		}
	}	
    
	/**
     * Sound function calls play method in sound effect class
     * on sound file 
     * >for when player uses key
     */
	public void keySound() {
		SoundEffects keySound = new SoundEffects();
		keySound.playSound("./sound/key.wav");
	}
	
    public int getkeyID() {
        return keyID;
    }
}
