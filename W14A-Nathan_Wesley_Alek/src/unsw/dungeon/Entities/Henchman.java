package unsw.dungeon.Entities;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import unsw.dungeon.Movement;
import unsw.dungeon.AStar_Algorithm.AStarSearch;
import unsw.dungeon.Application.Dungeon;

public class Henchman extends Enemy {
	
	public Henchman(Dungeon dungeon, int x, int y, Movement movement) {
		super(dungeon, x, y, movement);
	}
	
	@Override
	public void startMove() {
		// TODO Auto-generated method stub
		t = new Timer();
		t.schedule(new TimerTask() {

            @Override
            public void run() {
            	if (player == null) {
            		t.cancel();
            	} else if (player.isNormalState()) {
        			approach();
        		} else {
        			escape();
        		}

            }
        }, 800, 800);
	}
	
	/**
	 * singleMove - for testing, allows enemy to move a single square.
	 */
	@Override
	public void singleMove() {
		// for testing, uses same logic as above.
		if (player == null) {
    		// do nothing.
    	} else if (player.isNormalState()) {
			approach();
		} else {
			escape();
		}
	}
	
	/**
	 * approach - code to run A* to approach the player by the shortest path.
	 */
	public void approach() {
		// should loop every once in a while.
		int[] playerXY = player.getXY();
		int[] currentXY = this.getXY();
		
		AStarSearch aStar = new AStarSearch(dungeon, playerXY, currentXY);
		List<String> bestPath = aStar.search();
		if (bestPath == null) {
			return; // can't reach player, do nothing.
		}
		String firstMove = bestPath.get(0);
		enemyMove(firstMove);
		kill();
	}

	/**
	 * escape - code to move away from player in a very simplistic movement.
	 */
	public void escape() {
		int[] playerXY = player.getXY();
		int distance = calcDistance();
		// can probs be its own func, currently just testing functionality.
		int[] enemyUp = this.getXY();
		enemyUp[1] = enemyUp[1] - 1;
		if (enemyUp[1] >= 0) {
			if(moveValid(distance, enemyUp, playerXY, "UP")) {
				return;
			}
		}
		
		int[] enemyDown = this.getXY();
		enemyDown[1] = enemyDown[1] + 1;
		if (enemyDown[1] <= dungeon.getHeight() - 1) {
			if(moveValid(distance, enemyDown, playerXY, "DOWN")) {
				return;
			}
		}
		int[] enemyLeft = this.getXY();
		enemyLeft[0] = enemyLeft[0] - 1;
		if (enemyLeft[0] >= 0) {
			if(moveValid(distance, enemyLeft, playerXY, "LEFT")) {
				return;
			}
		}
		int[] enemyRight = this.getXY();
		enemyRight[0] = enemyRight[0] + 1;
		if (enemyRight[0] <= dungeon.getHeight() - 1) {
			if(moveValid(distance, enemyRight, playerXY, "RIGHT")) {
				return;
			}
		}
	}
	
}
