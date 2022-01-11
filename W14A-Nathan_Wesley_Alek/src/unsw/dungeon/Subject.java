package unsw.dungeon;

/**
 * The subject interface.
 * To be used on player.
 * notifies the observers necessary.
 */
public interface Subject {
	public void registerObserver(EnemyObserver o);
	public void registerObserver(GoalObserver o);
	public void removeObserver(EnemyObserver o);
	public void removeObserver(GoalObserver o);
	public void notifyEnemyObservers();
	public void notifyEnemyWeapon(int x, int y);
	public void notifyGoalObservers();
}
