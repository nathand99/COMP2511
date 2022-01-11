package unsw.dungeon.Entities;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import unsw.dungeon.Movement;
import unsw.dungeon.AStar_Algorithm.AStarSearch;
import unsw.dungeon.Application.Dungeon;

/**
 * Hound - an enemy with a very erratic movement pattern, that does not pay attention
 * to where the player is, until it is within 5 squares of the player.
 *
 */
public class Hound extends Enemy {
	
	private String direction;
	
	public Hound(Dungeon dungeon, int x, int y, String direction, Movement movement) {
		super(dungeon, x, y, movement);
		this.direction = direction;
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
            	} else if (calcDistance() <= 5) {
            		hunt();
            	} else {
            		randomMove(0);
            	}

            }
        }, 500, 500);
	}

	@Override
	public void singleMove() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * A simple A* Search.
	 */
	public void hunt() {
		// should loop every once in a while.
		// System.out.println("hunting");
		int[] playerXY = player.getXY();
		int[] currentXY = this.getXY();
		
		AStarSearch aStar = new AStarSearch(dungeon, playerXY, currentXY);
		List<String> bestPath = aStar.search();
		if (bestPath == null) {
			// System.out.println("can't make it through wall");
			randomMove(0); // can't reach player, move randomly.
			return;
		}
		String firstMove = bestPath.get(0);
		enemyMove(firstMove);
		kill();
	}
	
	/**
	 * randomMove - a very basic movement pattern for the hound.
	 */
	public void randomMove(int nest) {
		if (nest >= 2) {
			return;
		}
		int random = (int) Math.round(Math.random()*100) % 8;
		// System.out.println(random);
		int[] move = getXY();
		if (direction.equals("UP")) { // up
			move[1] = move[1] - 1;
			if (!possibleMove(move)) {
				if (random == 0) {
					direction = "LEFT";
				} else if (random == 1) {
					direction = "DOWN";
				} else {
					direction = "RIGHT";
				}
				randomMove(nest + 1);
			} else if (random == 0) {
				direction = "RIGHT";
			}
			
		} else if (direction.equals("DOWN")) { // down
			move[1] += 1;
			if (!possibleMove(move)) {
				if (random == 0) {
					direction = "UP";
				} else if (random == 1) {
					direction = "RIGHT";
				} else {
					direction = "LEFT";
				}
				randomMove(nest + 1);
			} else if (random == 0) {
				direction = "LEFT";
			}
		} else if (direction.equals("LEFT")) { // left
			move[0] -= 1;
			if (!possibleMove(move)) {
				if (random == 0) {
					direction = "RIGHT";
				} else if (random == 1) {
					direction = "DOWN";
				} else {
					direction = "UP";
				}
				randomMove(nest + 1);
			} else if (random == 0) {
				direction = "UP";
			}
		} else { // right
			move[0] += 1;	
			if (!possibleMove(move)) {
				if (random == 0) {
					direction = "UP";
				} else if (random == 1) {
					direction = "LEFT";
				} else {
					direction = "DOWN";
				}
				randomMove(nest + 1);
			} else if (random == 0) {
				direction = "DOWN";
			}
		}
		kill();
	}
	
	/**
	 * PossibleMove - determines if the move is possible or not.
	 * @param move - the move (XY)
	 * @return
	 */
	public boolean possibleMove(int[] move) {
		if (
			move[0] >= 0 && 
			move[1] >= 0 && 
			move[1] <= dungeon.getHeight() - 1 && 
			move[0] <= dungeon.getWidth() - 1
		) {
			if (collision(move)) {
				enemyMove(direction);
				return true;
			}
		} 
		return false;
	}
}
