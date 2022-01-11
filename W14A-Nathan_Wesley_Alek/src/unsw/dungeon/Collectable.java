package unsw.dungeon;

import unsw.dungeon.Entities.*;

public class Collectable implements Movement {
	
	/**
	 * @return false if moving entity is a boulder, else true
	 */
	@Override
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		if (movingEntity.getEntityName().equals("boulder")) {
			return false;
		} 
		return true;
	}

}
