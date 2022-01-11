package unsw.dungeon.AStar_Algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates an A* node to be used in the A* search
 * @param x
 * @param y
 * @param distance - distance from starting node.
 * @param heuristic - heuristic for the search.
 * @param path - list of the directions (up, down, left, right) needed to get to the current 
 * point.
 */
public class AStarNode implements Comparable<AStarNode> {
	
	private static int infinite = 999999;
	
	private int x, y;
	private int distance;
	private int heuristic;
	private List<String> path;
	
	public AStarNode(int[] xy, int distance) {
		this.x = xy[0];
		this.y = xy[1];
		this.distance = distance;
		this.heuristic = infinite;
		this.path = new ArrayList<String>();
	}
	
	public AStarNode(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
		this.heuristic = infinite;
		this.path = new ArrayList<String>();
	}
	
	public AStarNode(int x, int y, int distance, List<String> path) {
		this.x = x;
		this.y = y;
		this.distance = distance;
		this.heuristic = infinite;
		this.path = path;
	}
	
	/**
	 * Calculates the heuristic via straight line distance squared.
	 * @param goal - the x y co-ordinates of a goal square.
	 */
	public void calcHeuristic(int[] goal) {
		int xDiff = this.x - goal[0];
		int yDiff = this.y - goal[1];
		this.heuristic = (xDiff * xDiff) + (yDiff * yDiff) + this.distance;
	}
	
	public int getHeuristic() {
		return this.heuristic;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public List<String> getPath() {
		return this.path;
	}
	
	public void setPath(List<String> path) {
		this.path = path;
	}

	@Override
	public int compareTo(AStarNode a2) {
		// TODO Auto-generated method stub
		return this.getHeuristic() - a2.getHeuristic();
	}
}
