package unsw.dungeon;

/**
 * Immovable - cannot be moved through
 * automatically returns false.
 */
public class Immovable implements Movement {
	
	@Override
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		// TODO Auto-generated method stub
		return false;
	}

}
