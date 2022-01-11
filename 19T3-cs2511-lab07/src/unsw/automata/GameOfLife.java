/**
 *
 */
package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {
	
	BooleanProperty[][] game = new BooleanProperty[10][10];
	
	public GameOfLife() {
        // At the start all cells are dead
    	for (int i = 0; i < 10; i++) {
    	     for (int j = 0; j < 10; j++) {
    	    	 game[i][j] = new SimpleBooleanProperty(false);
    	     }
    	}
    }

    public void ensureAlive(int x, int y) {
        // Set the cell at (x,y) as alive
    	game[x][y] = new SimpleBooleanProperty(true);
    }

    public void ensureDead(int x, int y) {
        // Set the cell at (x,y) as dead
    	game[x][y] = new SimpleBooleanProperty(false);
    }

    public Boolean isAlive(int x, int y) {
        // Return true if the cell is alive
    	if (cellProperty(x, y).getValue() == true) {
    		return true;
    	} else {
    		return false;
    	}
    }

    public void tick() {
        // Transition the game to the next generation.
    	BooleanProperty[][] temp = new BooleanProperty[10][10];
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			temp[i][j] = game[i][j];
    		}
    	}
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			// number of alive neighbours
    			int count = 0;
				count = neighbours(i,j);
    			System.out.println(count + " i  = " + i + " j  = " + j);
    			if (isAlive(i, j) && (count < 2)) {
    				temp[i][j] = new SimpleBooleanProperty(false);
    			} else if (isAlive(i, j) && (count == 2 || count == 3)) {
    				temp[i][j] = new SimpleBooleanProperty(true);
    			} else if (isAlive(i, j) && (count > 3)) {
    				temp[i][j] = new SimpleBooleanProperty(false);
    			} else if (!isAlive(i, j) && (count == 3)) {
    				temp[i][j] = new SimpleBooleanProperty(true);
    			}
    		}
   	    }
    	for (int i = 0; i < 10; i++) {
   	     for (int j = 0; j < 10; j++) {
   	    	 game[i][j].setValue(temp[i][j].getValue());
   	     }
   	}
   	}
    
    public int neighbours(int i, int j) {
    	int count = 0;
    	
    	// neighbours to the left of x value (i)
    	int new_x = i - 1;
		if (new_x < 0) new_x = 9;
		
		int new_y = j - 1;
		if (new_y < 0) new_y = 9;
		if (isAlive(new_x,new_y)) count++;
		
		if (isAlive(new_x,j)) count++;
		new_y = j + 1;
		if (new_y > 9) new_y = 0;
		if (isAlive(new_x,new_y)) count++;
		
		// neighburs in line with x value
		new_y = j - 1;
		if (new_y < 0) new_y = 9;
		if (isAlive(i, new_y)) count++;
		new_y = j + 1;
		if (new_y > 9) new_y = 0;
		if (isAlive(i, new_y)) count++;
		
		//neighbours to the right of x value
		new_x = i + 1;
		if (new_x > 9) new_x = 0;
		new_y = j - 1;
		if (new_y < 0) new_y = 9;
		if (isAlive(new_x, new_y)) count++;
		if (isAlive(new_x, j)) count++;
		new_y = j + 1;
		if (new_y > 9) new_y = 0;
		if (isAlive(new_x, new_y)) count++;
		
		return count;
    }
    
    public BooleanProperty cellProperty(int x, int y) {
		return game[x][y];
	}
}
