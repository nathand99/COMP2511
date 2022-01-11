package unsw.dungeon.Entities;

import unsw.dungeon.*;

public class Wall extends Entity {
	
	/**
	 * Contructor for wall
	 * @param x
	 * @param y
	 * @param movement
	 */
    public Wall(int x, int y, Movement movement) {
        super(x, y, movement);
        this.setEntityName("wall");
    }

}
