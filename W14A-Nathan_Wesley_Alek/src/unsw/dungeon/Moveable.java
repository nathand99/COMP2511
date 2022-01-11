package unsw.dungeon;

/**
 * Moveable - always can move through, always returns true.
 *
 */

public class Moveable implements Movement{

	@Override
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		// TODO Auto-generated method stub
		return true;
	}

}
