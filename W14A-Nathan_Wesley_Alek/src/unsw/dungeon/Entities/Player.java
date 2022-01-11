package unsw.dungeon.Entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import unsw.dungeon.*;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Application.DungeonController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.*;
import unsw.dungeon.Entities.*;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {
	
	static final int minClickDelay = 400000000;
	
    private Dungeon dungeon;
    
    private List<EnemyObserver> enemyObservers;
    private List<GoalObserver> goalObservers;
    private DungeonController controller;
    
    private long lastClickTime =  System.nanoTime() - minClickDelay;
    private long lastWeaponSwing =  System.nanoTime() - minClickDelay;
    
    // goals
    private PlayerGoal goals;
    
    // inventory
    private Weapons weapon = null;
    private Key key = null;
    private int treasure = 0; 
    //game starts on normal state being true 
    //when normal state is true enemy can kill player
    //when normal state is false enemy can't kill player
    private boolean normalState = true;
    private int numThreads = 0;

	/**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, Movement movement) {
        super(x, y, movement);
        this.dungeon = dungeon;
        this.setEntityName("player");
        enemyObservers = new ArrayList<EnemyObserver>();
        goalObservers = new ArrayList<GoalObserver>();
        this.setGoals(new PlayerGoal(this));
    }

    /**
     * moves up
     */
    public void moveUp() {
    	System.out.println("this works");
    	long now =  System.nanoTime();
    	if (now - lastClickTime < minClickDelay) return;
    	System.out.println("Timer works");
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX(), this.getY() - 1);
        if (getY() > 0 && moveTo.size() == 0) {
            y().set(getY() - 1); 
            lastClickTime = now;
            notifyEnemyObservers();
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		//System.out.println(entity.checkMovement(this));
        		if (entity.canMove(this, entity, "UP") == false) {
        			return;
        		}
        	}
        	y().set(getY() - 1); 
        	notifyGoalObservers();
        	pickup();
        	//lastClickTime = now;
        	notifyEnemyObservers();
        	checkKey();
        }
    }
    
    /**
     * moves down
     */
    public void moveDown() {
    	System.out.println("this works");
    	long now =  System.nanoTime();
    	if (now - lastClickTime < minClickDelay) return;
    	System.out.println("Timer works");
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX(), this.getY() + 1);
        if (getY() < dungeon.getHeight() - 1 && moveTo.size() == 0) {
            y().set(getY() + 1);
            lastClickTime = now;
            notifyEnemyObservers();
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "DOWN") == false) {
        			return;
        		}
        	}
        	y().set(getY() + 1);   
        	notifyGoalObservers();
        	pickup();
        	//lastClickTime = now;
        	notifyEnemyObservers();
        	checkKey();
        } 
    }
    
    /**
     * moves left
     */
    public void moveLeft() {
    	System.out.println("this works");
    	long now =  System.nanoTime();
    	if (now - lastClickTime < minClickDelay) return;
    	System.out.println("Timer works");
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX() - 1, this.getY());
        if (getX() > 0 && moveTo.size() == 0) {
            x().set(getX() - 1);
            lastClickTime = now;
            notifyEnemyObservers();
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "LEFT") == false) {
        			return;
        		}
        	}
        	x().set(getX() - 1);
        	notifyGoalObservers();
        	pickup();
        	lastClickTime = now;
        	notifyEnemyObservers();
        	checkKey();
        } 
    }

    /**
     * moves right
     */
    public void moveRight() {
    	System.out.println("this works");
    	long now =  System.nanoTime();
    	if (now - lastClickTime < minClickDelay) return;
    	System.out.println("Timer works");
    	List<Entity> moveTo = dungeon.getCurrentEntity(this.getX() + 1, this.getY());
        if (getX() < dungeon.getWidth() - 1 && moveTo.size() == 0) {
            x().set(getX() + 1);
            lastClickTime = now;
            notifyEnemyObservers();
        } else if (moveTo.size() != 0) {
        	for (Entity entity : moveTo) {
        		if (entity.canMove(this, entity, "RIGHT") == false) {
        			return;
        		}
        	}
        	x().set(getX() + 1); 
        	notifyGoalObservers();
        	pickup();
        	//lastClickTime = now;
        	notifyEnemyObservers();
        	checkKey();
        } 
    }
    
    /**
     * pickups an item.
     */
    public void pickup() {
    	List<Entity> entities = dungeon.getCurrentEntity(getX(), getY());
    	PickupItem item = null;
    	// check if there is a Pickup_item on players location
    	for (Entity e : entities) {
    		if (e.getEntityName().equals("treasure")) {
    			item = (PickupItem) e;
    			
    			break;
    		}
    		else if (e.getEntityName().equals("weapon")) {
    			item = (PickupItem) e;
    			
    			break;
    		}
    		else if (e.getEntityName().equals("invincibility")) {
    			item = (PickupItem) e;
    			
    			break;
    		}
    		else if (e.getEntityName().equals("key")) {
    			item = (PickupItem) e;
    			
    			break;
    		}
    	}
    	// there is a Pickup_item on the players location
    	if (item != null) {
    		Entity dropped = null;
			dropped = item.pickup(this);
			// if player drops a Pickup_item, add it to the dungeon
			if (dropped != null) {
				dungeon.addEntity(dropped);
			}
			// dungeonController bar editing.
			if (controller != null) {
				controller.invAdd(item);
			}
    	}
    }
    
    /**
     * checks if player has won, and updates the goal.
     */
    public void win() {
    	if (controller != null) {
	    	Platform.runLater(new Runnable() {
	            @Override public void run() {
	                updateGoal();
	            }
	        });
    	}
    	if (goals.checkCompletion()) {
    		// TODO: stub, should actually do stuff when front end is done.
    		winGameSound();
    		try {
				controller.nextLevel();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		//
    	}
    }
    
    /**
     * Sound function calls play method in sound effect class
     * on sound file 
     * > for when player wins the game
     */
    public void winGameSound() {
		SoundEffects winGameSound = new SoundEffects();
		winGameSound.playSound("./sound/win.wav");
	}
    
    
    public int getMinClickDelay() {
		return minClickDelay;
	}
    
    /**
     * checks if player can exit the Exit entity.
     * @return true or false.
     */
    public boolean canExit() {
    	return goals.onlyExit();
    }
    
    /**
     * isNormalState - essentially a getter.
     * @return normalState value.
     */
	public boolean isNormalState() {
		return normalState;
	}

	public void setNormalState(boolean normalState) {
		this.normalState = normalState;
	}

	public int getNumThreads() {
		return numThreads;
	}

	public void setNumThreads(int numThreads) {
		this.numThreads = numThreads;
	}
	
	
	
	/**
	 * Returns the keyID.
	 * @return
	 */
	public int getKeyID() {
		return key.getkeyID();
	}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public int getTreasure() {
		return treasure;
	}

	public void setTreasure(int treasure) {
		this.treasure = treasure;
	}
	
	public PlayerGoal getGoals() {
		return goals;
	}

	public void setGoals(PlayerGoal goals) {
		this.goals = goals;
	}
	
	/**
	 * adds a goal to the player's goals
	 * @param goals - a goal.
	 */
	public void addGoals(PlayerGoal goals) {
		this.setGoals(goals);
	}

	@Override
	public void registerObserver(EnemyObserver o) {
		enemyObservers.add(o);
	}

	@Override
	public void removeObserver(EnemyObserver o) {
		int i = enemyObservers.indexOf(o);
		if (i >= 0) {
			enemyObservers.remove(i);
		}
	}
	
	/**
	 * Removes all enemy Observers.
	 */
	public void removeAllEnemies() {
		for (int i = 0; i < enemyObservers.size(); i++) {
			EnemyObserver o = enemyObservers.get(i);
			Enemy enemy = (Enemy) o;
			enemy.setPlayer(null);
			removeObserver(o);
			i--;
		}
	}

	@Override
	public void notifyEnemyObservers() {
		// TODO Auto-generated method stub
		for (int i = 0; i < enemyObservers.size(); i++) {
			int prevSize = enemyObservers.size();
			EnemyObserver obs = enemyObservers.get(i);
			obs.update(this.getXY(), this.goals);
			if (prevSize > enemyObservers.size()) {
				i--;
			}
		}
		//updateGoal();
		win();
	}
	
	@Override
	public void notifyEnemyWeapon(int x, int y) {
		// TODO Auto-generated method stub
		for (int i = 0; i < enemyObservers.size(); i++) {
			int prevSize = enemyObservers.size();
			EnemyObserver obs = enemyObservers.get(i);
			obs.die(x, y, this.goals);
			if (prevSize > enemyObservers.size()) {
				i--;
			}
		}
		//updateGoal();
		win();
	}

	@Override
	public void registerObserver(GoalObserver o) {
		// TODO Auto-generated method stub
		goalObservers.add(o);
	}

	@Override
	public void removeObserver(GoalObserver o) {
		// TODO Auto-generated method stub
		int i = goalObservers.indexOf(o);
		if (i >= 0) {
			goalObservers.remove(i);
		}
	}

	@Override
	public void notifyGoalObservers() {
		for (int i = 0; i < goalObservers.size(); i++) {
			int prevSize = goalObservers.size();
			GoalObserver obs = goalObservers.get(i);
			obs.update(this.getGoals(), this.getXY());
			if (prevSize > goalObservers.size()) {
				i--;
			}
		}
		//updateGoal();
		win();
	}
	
	/**
	 * attack up
	 */
	public void attackW() {
		if(weapon != null) {
			weapon.attackAbove(this);
			updateWeaponDurability();
		}
		
		
	}
	
	/**
	 * attack down
	 */
	public void attackS() {
		if(weapon != null) {
			weapon.attackBelow(this);
			updateWeaponDurability();
		}
		
	}

	/**
	 * attack right
	 */
	public void attackD() {
		if(weapon != null) {
			weapon.attackRight(this);
			updateWeaponDurability();
		}
		
	}
	
	/**
	 * attack left
	 */
	public void attackA() {
		if(weapon != null) {
			weapon.attackLeft(this);
			updateWeaponDurability();
		}
	}
	
	
	/**
	 * The player dies.
	 */
	public void die() {
		loseGameSound();
		System.out.println("Yep, he's dead");
		dungeon.deregisterAll();
		dungeon.removeEntity(this);
		dungeon.setPlayer(null);
		if (controller != null) {
		    Platform.runLater(new Runnable() {
		        @Override public void run() {
		            try {
						controller.restart();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
	    }
    }
	
	public void loseGameSound() {
		SoundEffects loseGameSound = new SoundEffects();
		loseGameSound.playSound("./sound/game_over.wav");
	}
	
	public long getLastWeaponSwing() {
		return lastWeaponSwing;
	}

	public void setLastWeaponSwing(long lastWeaponSwing) {
		this.lastWeaponSwing = lastWeaponSwing;
	}

	
	/**
	 * Returns the durability of Sword.
	 * @return
	 */
	
	public int getWeaponDurability() {
		if (weapon == null) {
			return 0;
		}
		return weapon.getHitsLeft();
	}

	public DungeonController getController() {
		return controller;
	}

	public void setController(DungeonController controller) {
		this.controller = controller;
	}
	
	/**
	 * Checks if the key doesn't exist.
	 */
	public void checkKey() {
		if (key == null && controller != null) {
			controller.removeKey();
		}
	}
	
	/**
	 * Updates the front end weapon durability.
	 */
	public void updateWeaponDurability() {
		if (controller != null) {
			controller.setWeaponDurability();
		}
	}
	
	/**
	 * Updates the goal for frontend.
	 */
	public void updateGoal() {
		if (controller != null) {
			controller.updateGoal();
		}
	}

	public Weapons getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapons weapon) {
		this.weapon = weapon;
	}
	
	/**
	 * Returns the imageView of the weapon to frontend
	 * @return
	 */
	public ImageView getWeaponView() {
		return weapon.getWeaponView();
	}
	
	/**
	 * Sets the player at the front of the entities.
	 */
	public void toFront() {
		getEntityView().toFront();
	}
}
