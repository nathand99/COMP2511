package unsw.dungeon.Entities;

import unsw.dungeon.*;
import unsw.dungeon.AStar_Algorithm.AStarSearch;
import unsw.dungeon.Application.Dungeon;
import unsw.dungeon.Application.DungeonApplication;
import unsw.dungeon.Application.DungeonController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Enemy extends Entity implements EnemyObserver {
	
	protected Dungeon dungeon;
	protected Player player;
	protected Timer t;
	
	/**
     * Create an enemy positioned in square (x,y)
     * @param x
     * @param y
	 * @throws InterruptedException 
     */
	public Enemy(Dungeon dungeon, int x, int y, Movement movement) {
		super(x, y, movement);
		this.dungeon = dungeon;
		this.player = null;
		this.setEntityName("enemy");
		
	}
	
	@Override
	public void register() {
		this.player = dungeon.getPlayer();
		player.registerObserver((EnemyObserver) this); // when enemy dies, remove
		startMove();
	}
	
	// for testing purposes only.
	@Override
	public void registerNoMove() {
		this.player = dungeon.getPlayer();
		player.registerObserver((EnemyObserver) this); // when enemy dies, remove
	}
	
	/**
	 * startMove - starts the time for enemies to move.
	 */
	public abstract void startMove();
	
	/**
	 * singleMove - for testing, allows enemy to move a single square.
	 */
	public abstract void singleMove();

	// while the game goes on, check for player state, and if not invincible run aggressive code.
	// if invincible, run passive code.
	
	/**
	 * Stops the timer.
	 */
	public void stopTimer() {
		t.cancel();
	}
	
	/**
	 * kill - code to check if the player is on the enemy, and kill them.
	 * @param playerXY - the x y co-ordinates of the player.
	 * @return true if the player is killed, and false otherwise.
	 */
	public boolean kill() {
		int[] playerXY = player.getXY();
		if (playerXY[0] == this.getX() && playerXY[1] == this.getY()) {
			if (player.isNormalState() == false) {
				death(player.getGoals());
				return false;
			}
			System.out.println("You were killed");
			player.die();
			
			return true;	
			
		}
		return false;
	}
	
	public int calcDistance() {
		int[] playerXY = player.getXY();
		return Math.abs(playerXY[0] - this.getX()) + Math.abs(playerXY[1] - this.getY());
	}
	
	/**
	 * isFurther - code to check if the new square is further from the player than old square
	 * @param distance - previous distance from player
	 * @param enemyXY - enemy new x y co-ordinate
	 * @param playerXY - player x y co-ordinate
	 * @return true if further, false otherwise.
	 */
	public boolean isFurther(int distance, int[] enemyXY, int playerXY[]) {
		int newDist = Math.abs(playerXY[0] - enemyXY[0]) + Math.abs(playerXY[1] - enemyXY[1]);
		if (newDist > distance) {
			return true;
		}
		return false;
	}
	
	/**
	 * collision - tests the collision of the enemy entity.
	 * @param newXY - the x y co-ordinate the enemy is moving to
	 * @return true if there is no collision (can move), false otherwise.
	 */
	public boolean collision(int[] newXY) {
		List <Entity> entities = dungeon.getCurrentEntity(newXY[0], newXY[1]);
		for (Entity entity : entities) {
			if (!entity.canMove(this, entity, null)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * moveValid -tests if a move is valid for escape, and actually escapes the player.
	 * @param distance - the distance from enemy to player.
	 * @param newXY - the new square enemy is moving to.
	 * @param playerXY
	 * @param direction - direction the enemy moves.
	 * @return true if the new square satisfies requirements, false otherwise.
	 */
	public boolean moveValid(int distance, int[] newXY, int[] playerXY, String direction) {
		if (collision(newXY) && isFurther(distance, newXY, playerXY)) {
			enemyMove(direction);
			return true;
		}
		return false;
	}
	
	/**
	 * enemyMove - switch statement to determine where the enemy moves.
	 * @param move - the direction the enemy moves in (up, down, left, right).
	 */
	public void enemyMove(String move) {
		
		switch (move) {
        case "UP":
        	y().set(getY() - 1);
            break;
        case "DOWN":
        	y().set(getY() + 1);
            break;
        case "LEFT":
        	x().set(getX() - 1);
            break;
        case "RIGHT":
        	x().set(getX() + 1);
            break;
        default:
            break;
        }
	}
	
	// from EnemyObserver
	@Override
	public void update(int[] playerXY, PlayerGoal goals) {
		// TODO Auto-generated method stub
		if (player.isNormalState()) {
			if (kill()) {
				return;
			}
			//approach();
		} else {
			// System.out.println("this works");
			die(playerXY[0], playerXY[1], goals);
		}
		
	}

	@Override
	public void die(int x, int y, PlayerGoal goals) {
		// TODO Auto-generated method stub
		if (this.getX() == x && this.getY() == y) {
			death(goals);
			enemyDeathSound();
		}

	}
	
	/**
     * Sound function calls play method in sound effect class
     * on sound file 
     * >for when player kills enemy
     */
	public void enemyDeathSound() {
		SoundEffects enemyDeathSound = new SoundEffects();
		enemyDeathSound.playSound("./sound/yell12.wav");
	}
	
	/**
	 * Removes enemy from map, and dungeon.
	 */
	public void death(PlayerGoal goals) {
		goals.addComplete("enemy");
		// can't update goal here?
		// player.updateGoal();
		player.removeObserver((EnemyObserver) this);
		if (this.getEntityView() != null) {
			this.getEntityView().setVisible(false);
		}
		dungeon.removeEntity(this);
		
		player.win(); // checks to see if player wins.
		this.player = null;
	}
	
	/**
	 * @setter
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}



}
