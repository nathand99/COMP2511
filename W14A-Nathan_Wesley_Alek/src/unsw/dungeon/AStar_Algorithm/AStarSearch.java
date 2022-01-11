package unsw.dungeon.AStar_Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import unsw.dungeon.*;
import unsw.dungeon.Application.*;
import unsw.dungeon.Entities.*;

/**
 * A* Search for enemy
 * @parameter open - the nodes still open for searching
 * @parameter closed - nodes closed for searching
 * @parameter goal - the x y co-ordinates of the goal.
 * @parameter start - the x y co-ordinates of the starting square.
 * @parameter dungeon - the dungeon.
 */
public class AStarSearch {
	private List<AStarNode> open;
	private List<AStarNode> closed;
	private int[] goal;
	private int[] start;
	private Dungeon dungeon;
	
	
	public AStarSearch(Dungeon dungeon, int[] goal, int[] start) {
		this.open = new ArrayList<AStarNode>();
		this.closed = new ArrayList<AStarNode>();
		this.goal = goal;
		this.start = start;
		this.dungeon = dungeon;
	}
	
	/**
	 * search - the logic for the A* search.
	 * @return the path (in Strings) to the goal that is most efficient.
	 */
	public List<String> search() {  // not supposed to be void, must return path.
		initialise();
		
		while(!open.isEmpty()) {
			AStarNode next = open.get(0);
			
			if (checkGoal(next)) {
				return next.getPath();
			}
			open.remove(next);
			closed.add(next);
			createChildren(next);
		}
		
		// return failure
		return null;
	}
	
	/**
	 * initialise - initialises for the A* search.
	 */
	public void initialise() {
		AStarNode start = new AStarNode(this.start, 0);
		start.calcHeuristic(goal);
		open.add(start);
	}
	
	/**
	 * checkGoal - checks if the node is the goal node.
	 * @param node = the current node the search is on.
	 * @return true/false depending on if the node has been reached.
	 */
	public boolean checkGoal(AStarNode node) {
		if (node.getX() == this.goal[0] && node.getY() == this.goal[1]) {
			return true;
		}
		return false;
	}
	
	/**
	 * createChildren - the logic to create the child nodes from a parent node
	 * @param parent - the parent node.
	 */
	public void createChildren(AStarNode parent) {
		int prevX = parent.getX();
		int prevY = parent.getY();
		int newDistance = parent.getDistance() + 1;
		List<String> parentPath = parent.getPath();
		
		List<String> upPath = createPath(parentPath, "UP");
		List<String> downPath = createPath(parentPath, "DOWN");
		List<String> leftPath = createPath(parentPath, "LEFT");
		List<String> rightPath = createPath(parentPath, "RIGHT");

		
		// dont forget enemies have to check collision!
		AStarNode up = new AStarNode(prevX, prevY - 1, newDistance, upPath);
		up.calcHeuristic(goal);
		AStarNode down = new AStarNode(prevX, prevY + 1, newDistance, downPath);
		down.calcHeuristic(goal);
		AStarNode left = new AStarNode(prevX - 1, prevY, newDistance, leftPath);
		left.calcHeuristic(goal);
		AStarNode right = new AStarNode(prevX + 1, prevY, newDistance, rightPath);
		right.calcHeuristic(goal);
		
		addChild(up);
		addChild(down);
		addChild(left);
		addChild(right);
	}
	
	/**
	 * createPath - adds the next direction to a path.
	 * @param parentPath - the String path of the parent, to get to the current node from start
	 * @param direction - the next direction.
	 * @return childPath - the parentPath with the next direction added to it.
	 */
	public List<String> createPath(List<String> parentPath, String direction) {
		List<String> childPath = new ArrayList<String>(parentPath);
		childPath.add(direction);
		return childPath;
	}
	
	/**
	 * addChild - logic of whether to add the child to the string or not.
	 * @param child
	 */
	public void addChild(AStarNode child) { // best if collision is checked here.
		// collision check
		List <Entity> entities = dungeon.getCurrentEntity(child.getX(), child.getY());
		for (Entity entity : entities) {
			if (!entity.canMove(new Henchman(dungeon, child.getX(), child.getY(), new Interactable()), entity, null)) {
				return;
			}
		}
		// collision check end.
		if (!alreadyChecked(child, this.open) && !alreadyChecked(child, this.closed)) {
			addAndSort(child);
		}
		
	}
	
	/**
	 * alreadyChecked - if the child node is not already in the lists
	 * @param child - the node in question
	 * @param list - either open or closed.
	 * @return true/false - depending on if we want to add it to the list or not.
	 */
	public boolean alreadyChecked(AStarNode child, List<AStarNode> list) {
		for (AStarNode node : list) {
			if (node.getX() == child.getX() && node.getY() == child.getY()) {
				// when we have to rerun because there is a new better path
				if (child.getDistance() < node.getDistance()) {
					list.remove(node);
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * addAndSort - simple code to add the code to open, and sort it based on heuristic
	 * @param node - the node to be added.
	 */
	public void addAndSort(AStarNode node) {
		open.add(node);
		Collections.sort(open);
	}
	
}
