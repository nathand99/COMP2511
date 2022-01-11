package unsw.dungeon;

import unsw.dungeon.Entities.*;

/**
 * Interactable - has some interactions with other entities.
 * Interaction depends heavily on both entities.
 *
 */
public class Interactable implements Movement{

	@Override
	public boolean canMove(Entity movingEntity, Entity stationaryEntity, String direction) {
		if (movingEntity.getEntityName().equals("player")) {
			// should only be sometimes true.
			if (stationaryEntity.getEntityName().equals("boulder")) {
				return successPush((Boulder) stationaryEntity, direction);
			}
			else if (stationaryEntity.getEntityName().equals("portal")) {
				return teleport((Player) movingEntity,(Portal) stationaryEntity);
			}
			else if (stationaryEntity.getEntityName().equals("door")) {
				return openDoor((Player) movingEntity,(Door) stationaryEntity, direction);
			}
			else if (stationaryEntity.getEntityName().equals("fire")) {
				if (((Player) movingEntity).isNormalState() == true) {
					((Player) movingEntity).die();
				}
				return true;
			}
			return true; // for exit, always true. However, does not always win.
		} else if (movingEntity.getEntityName().equals("enemy")) {
			if (stationaryEntity.getEntityName().equals("exit")) {
				return true;
			} else if (stationaryEntity.getEntityName().equals("portal")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param boulder
	 * @param direction
	 * @return true if boulder can successfully be pushed, false if not
	 */
	public boolean successPush(Boulder boulder, String direction) {
		switch(direction) {
		case "UP":
			return boulder.pushUp();
		case "DOWN":
			return boulder.pushDown();
		case "LEFT":
			return boulder.pushLeft();
		case "RIGHT":
			return boulder.pushRight();
		}
		return false;
	}

	public boolean teleport(Player player, Portal portal) {
		return portal.bestTele(player);
	}
	
	public boolean openDoor(Player player, Door door, String direction) {
		return door.openDoor(player);
	}
}
