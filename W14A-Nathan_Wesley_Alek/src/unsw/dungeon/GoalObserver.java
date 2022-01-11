package unsw.dungeon;

/**
 * GoalObserver - observes when a goal is met, or could be met.
 * register - registers.
 * update - updates the observer of certain changes in state.
 */
public interface GoalObserver {
	public void register();
	public void update(PlayerGoal goals, int[] playerXY); // TODO: add what is passed.
}
