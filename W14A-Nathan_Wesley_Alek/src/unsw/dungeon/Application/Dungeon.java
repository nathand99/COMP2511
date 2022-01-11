/**
 *
 */
package unsw.dungeon.Application;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.*;
import unsw.dungeon.Entities.*;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 * 
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntities () {
		return this.entities;
    }
    
    public void printEntities() {
    	for (Entity entity : entities) {
    		System.out.println(entity.getX() + ", " + entity.getY());
    	}
    }
    
    /**
     * registerAll - registers all observables to observers.
     */
    public void registerAll() {
    	for (Entity entity : entities) {
    		if (entity.getEntityName().equals("treasure")) {
    			((GoalObserver) entity).register();
    		}
    		else if (entity.getEntityName().equals("exit")) {
    			((GoalObserver) entity).register();
    		}
    		else if (entity.getEntityName().equals("floorSwitch")) {
    			((GoalObserver) entity).register();
    		}
    		else if (entity.getEntityName().equals("enemy")) {
    			((EnemyObserver) entity).register();
    		} 
    		else if (entity.getEntityName().equals("fireTrap")) {
    			((FireTrap) entity).start();
    		}
    	}
    }
    
    /**
     * Deregisters all moving entities.
     */
    public void deregisterAll() {
    	if (player != null) {
    		player.removeAllEnemies();
    	}
    	for (Entity entity : entities) {
    		if (entity.getEntityName().equals("fireTrap")) {
    			((FireTrap) entity).stop();
    		}
    	}
    }
    
    /**
     * registerNoMove - registers as above, but registers enemies to not move.
     * For testing purposes.
     */
    public void registerNoMove() {
    	for (Entity entity : entities) {
    		if (entity.getEntityName().equals("treasure")) {
    			((GoalObserver) entity).register();
    		}
    		else if (entity.getEntityName().equals("exit")) {
    			((GoalObserver) entity).register();
    		}
    		else if (entity.getEntityName().equals("floorSwitch")) {
    			((GoalObserver) entity).register();
    		}
    		else if (entity.getEntityName().equals("enemy")) {
    			((EnemyObserver) entity).registerNoMove();
    		}
    	}
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return list of entities on X,Y co-ordinate
     */
    public List<Entity> getCurrentEntity (int x, int y) {
    	List<Entity> particular_entity = new ArrayList<Entity>();
    	
    	for (Entity curr_e: this.entities) {
    		if (curr_e == null) continue;
    		if ((curr_e.getX() == x) && (curr_e.getY() == y)) {
    			particular_entity.add(curr_e);
    		}
    	}
		return particular_entity;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return true if enemy is on X,Y co-ordinate
     */
    public boolean isEnemy (int x, int y) {
    	for (Entity curr_e: this.entities) {
    		if (curr_e == null) continue;
    		if ((curr_e.getX() == x) && (curr_e.getY() == y) && curr_e.getEntityName().equals("enemy")) {
    			return true;
    		}
    	}
		return false;
    }


}
