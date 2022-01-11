package unsw.dungeon;
import unsw.dungeon.Entities.*;

/**
 * Pickup_item - an item that can be picked up by player (Key, Treasure, Sword, Invincibility Potion)
 */
public interface PickupItem {
	/**
	 * pickup - each class (item) that implements this method must implement how it is picked up
	 * @param p - player 
	 * 
	 * @return Entity: This method returns a Pickup_item (as an entity) that is left on the ground 
	 * from the players inventory if they swap an item in inventory with the item on the ground
	 * 
	 * returns null if no Pickup_item is swapped and put on the ground
	 */
	public Entity pickup(Player p);
}
