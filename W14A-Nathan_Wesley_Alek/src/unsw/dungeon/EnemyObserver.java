package unsw.dungeon;

/**
 * EnemyObserver - an observer which deals with the enemy class.
 * register - registers the observer.
 * registerNoMove - registers the observer for testing.
 * update - updates if the player has moved onto an enemy, and kills them/ the enemy
 * depending on conditions.
 * die - when the player slashes the enemy with a sword, checks if the square being attacked
 * is its square, and acts accordingly.
 */
public interface EnemyObserver {
	public void register();
	public void registerNoMove();
	public void update(int[] playerXY, PlayerGoal goals); // TODO: add what is passed.
	public void die(int x, int y, PlayerGoal goals);
}
