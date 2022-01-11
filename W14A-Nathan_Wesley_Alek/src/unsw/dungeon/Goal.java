package unsw.dungeon;

/**
 * Goal - the actual goals of each level.
 * checkComplete - checks if all goals for victory are complete.
 * onlyExit - checks if the only goal left is to exit.
 * addComplete - adds the completion of a goal.
 * removeComplete - removes the completion of a goal due to a new state.
 */
public interface Goal {
	public boolean checkComplete();
	public boolean onlyExit();
	public void addComplete(String obj);
	public void removeComplete(String obj);
}
