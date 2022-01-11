package unsw.dungeon.Entities;

import javafx.scene.image.ImageView;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;

public class Sword extends Entity implements PickupItem, Weapons {
	

	private int swordID;
	private Dungeon dungeon;
	private int hitsLeft = 5;

	
    /**
     * Create a sword positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     * @param swordID - ID of sword
     */
    public Sword(Dungeon dungeon, int x, int y, Movement movement) {
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
     * > for when player swings sword 
     */
	public void swordSound() {
		SoundEffects swordSound = new SoundEffects();
		swordSound.playSound("./sound/sword.wav");
	}
	
	/**
     * Sound function calls play method in sound effect class
     * on sound file 
     * > for when player picks sword up
     */
	public void pickupSound() {
		SoundEffects pickupSound = new SoundEffects();
		pickupSound.playSound("./sound/key.wav");
	}
	
	@Override
	public void attackLeft(Player player) {
		long now =  System.nanoTime();
    	if (now - player.getLastWeaponSwing() < player.getMinClickDelay()) return;
		if (player.getWeapon() != null) {
			swordSound();
			player.notifyEnemyWeapon(player.getX()-1, player.getY());
			player.setLastWeaponSwing(now);
			useHit();
		}
	}
	@Override
	public void attackRight(Player player) {
		long now =  System.nanoTime();
    	if (now - player.getLastWeaponSwing() < player.getMinClickDelay()) return;
		if (player.getWeapon() != null) {
			swordSound();
			player.notifyEnemyWeapon(player.getX()+1, player.getY());
			player.setLastWeaponSwing(now);
			useHit();
		}
	}
	@Override
	public void attackAbove(Player player) {
		long now =  System.nanoTime();
    	if (now - player.getLastWeaponSwing() < player.getMinClickDelay()) return;
		if (player.getWeapon() != null) {
			swordSound();
			player.notifyEnemyWeapon(player.getX(), player.getY()-1);
			player.setLastWeaponSwing(now);
			useHit();
		}
	}
	@Override
	public void attackBelow(Player player) {
		long now =  System.nanoTime();
    	if (now - player.getLastWeaponSwing() < player.getMinClickDelay()) return;
		if (player.getWeapon() != null) {
			swordSound();
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

	 public int getWeaponID() {
        return this.swordID;
    }
    @Override
	public int getHitsLeft() {
		return hitsLeft;
	}

	public void setHitsLeft(int hitsLeft) {
		this.hitsLeft = hitsLeft;
	}
	
	@Override
	public ImageView getWeaponView() {
		return this.getEntityView();
	}
}
