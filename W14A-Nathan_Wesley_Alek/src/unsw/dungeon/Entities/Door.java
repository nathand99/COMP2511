package unsw.dungeon.Entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class Door extends Entity {
	
	Dungeon dungeon;
	private int doorID;
	
	/**
	 * 
	 * @param dungeon
	 * @param x
	 * @param y
	 * @param doorID
	 * @param movement
	 */
	public Door(Dungeon dungeon, int x, int y, int doorID, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.doorID = doorID;
		this.setEntityName("door");
	}
	
	/**
	 * if player's key's id = door id, then door is opened
	 * @param player
	 * @return true if player key ID = doorID, false if not
	 */
	public boolean openDoor(Player player) {
		if (player.getKey() != null) {
			if (player.getKey().getkeyID() == doorID) {				
				// remove the locked door
				openDoorSound();
				dungeon.removeEntity(this);
				if (this.getEntityView() != null) {
					this.getEntityView().setImage(new Image("/open_door.png"));
				}
				
				// add in an unlocked door where the locked one was
				Door new_door = new Door(this.dungeon, this.getX(), this.getY(), this.getdoorID(), new Moveable());
				dungeon.addEntity(new_door);
				// remove key from players inventory
				player.setKey(null);
				return true;
			}
		}
		return false;
	}
	
	/**
     * Sound function calls play method in sound effect class
     * on sound file 
     * > when door is unlocked by key 
     */
	public void openDoorSound() {
		SoundEffects openDoorSound = new SoundEffects();
		openDoorSound.playSound("./sound/opendoor.wav");
	}

	public int getdoorID() {
        return doorID;
    }
}
