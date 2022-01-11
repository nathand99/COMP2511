package unsw.dungeon.Entities;

import javafx.scene.image.ImageView;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class Mace extends Entity implements PickupItem, Weapons {
	

	private int maceID;
	private Dungeon dungeon;
	private int hitsLeft = 1;

	
    /**
     * Create a mace positioned in square (x,y)
     * Mace hits all 4 squares around player, regardless of swing direction. 
     * Only 1 hit total.
     * @param dungeon
     * @param x
     * @param y
     * @param swordID - ID of sword
     */
    public Mace(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.setEntityName("weapon");
    }
    
	@Override
	public Entity pickup(Player p) {
		// if player has no sword, put this sword in inventory - return null
		if (p.getWeapon() == null) {
			p.setWeapon(this);
			if (this.getEntityView() != null) {
				this.getEntityView().setVisible(false);
			}
			pickupSound();
			dungeon.removeEntity(this);
			return null;
		// if player has sword, swap sword - return players sword - to be placed on ground
		} else {
			Weapons prevWeapon = p.getWeapon();
			((Entity) prevWeapon).x().set(this.getX());
			((Entity) prevWeapon).y().set(this.getY());
			
			if (p.getController() != null) {
				this.getEntityView().setVisible(false);
				prevWeapon.getWeaponView().setVisible(true);
			}
			pickupSound();
			dungeon.removeEntity(this);
			p.setWeapon(this);
			// drop sword where the player is with the ID of the sword the player had
			return (Entity) prevWeapon;			
		}
	}
    
	/**
     * Sound function calls play method in sound effect class
     * on sound file 
     * > for when player uses mace
     */
	public void maceSound() {
		SoundEffects maceSound = new SoundEffects();
		maceSound.playSound("./sound/mace.wav");
	}
	/**
     * Sound function calls play method in sound effect class
     * on sound file 
     * >for when player picks mace up
     */
	public void pickupSound() {
		SoundEffects pickupSound = new SoundEffects();
		pickupSound.playSound("./sound/key.wav");
	}
	
	@Override
	public void attackLeft(Player player) {
		MaceSwing(player);
	}
	@Override
	public void attackRight(Player player) {
		MaceSwing(player);
	}
	@Override
	public void attackAbove(Player player) {
		MaceSwing(player);
	}
	@Override
	public void attackBelow(Player player) {
		MaceSwing(player);
	}
	
	/**
	 * Mace swings one square about the players position
	 * @param player
	 */
	public void MaceSwing(Player player) {
		long now =  System.nanoTime();
    	if (now - player.getLastWeaponSwing() < player.getMinClickDelay()) return;
		if (player.getWeapon() != null) {
			maceSound();
			player.notifyEnemyWeapon(player.getX()-1, player.getY());
			player.notifyEnemyWeapon(player.getX()+1, player.getY());
			player.notifyEnemyWeapon(player.getX(), player.getY()-1);
			player.notifyEnemyWeapon(player.getX(), player.getY()+1);
			player.setLastWeaponSwing(now);
			useHit();
		}
	}
	/**
	 * -1 durability to sword.
	 */
	public void useHit() {
		this.setHitsLeft(hitsLeft - 1);
		if (hitsLeft == 0) {
			dungeon.getPlayer().setWeapon(null);
		}
	}
	
	@Override
    public int getWeaponID() {
        return this.maceID;
    }
	
    @Override
	public int getHitsLeft() {
		return hitsLeft;
	}
    
    @Override
	public void setHitsLeft(int hitsLeft) {
		this.hitsLeft = hitsLeft;
	}
    
    @Override
	public ImageView getWeaponView() {
		return this.getEntityView();
	}
}
