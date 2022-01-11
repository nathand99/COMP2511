package unsw.dungeon;

/**
 * Movement - a Strategy Pattern for movement
 *
 */

public interface Movement {
	/**
	 * canMove - returns true/false depending on if an entity can move through it or not.
	 * @param movingEntity - the entity that is moving.
	 * @param stationaryEntity - the entity that is not.
	 * @param direction - the direction the movingEntity is moving in.
	 * @return
	 */
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction);
}
