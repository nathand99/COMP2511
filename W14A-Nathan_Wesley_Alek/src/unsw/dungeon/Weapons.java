package unsw.dungeon;

import javafx.scene.image.ImageView;
import unsw.dungeon.Entities.*;

/**
 * 
 * Weapons interface 
 * Tracks particular weapon picked up by player
 * attackLeft, attackRight, attackAbove, attackBelow: 
 * used in player to denote direction of weapon swing
 * 
 */

public interface Weapons extends PickupItem {
	public void attackLeft(Player player);
	public void attackRight(Player player);
	public void attackAbove(Player player);
	public void attackBelow(Player player);
	public int getHitsLeft();
	public void setHitsLeft(int hitsLeft);
	public int getWeaponID();
	
	public ImageView getWeaponView();
}
