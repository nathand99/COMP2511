package unsw.dungeon;

/**
 * SingleGoal - a single goal, conferring to composite pattern.
 *
 */
public class SingleGoal implements Goal {
	private String goal;
	private int remaining;
	
	public SingleGoal() {
		this.setGoal(null);
		this.remaining = 0;
	}
	
	public SingleGoal(String goal, int total) {
		this.setGoal(goal);
		this.remaining = total;
	}
	
	@Override
	public boolean checkComplete() {
		// TODO Auto-generated method stub
		if (remaining == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean onlyExit() {
		// TODO Auto-generated method stub
		if (this.goal.equals("exit")) {
			return true;
		}
		return checkComplete();
	}

	@Override
	public void addComplete(String obj) {
		// TODO Auto-generated method stub
		if (this.goal.equals(obj)) {
			this.remaining -= 1;		
		}
	}

	@Override
	public void removeComplete(String obj) {
		// TODO Auto-generated method stub
		if (this.goal.equals(obj)) {
			this.remaining += 1;		
		}
	}
	
	@Override
	public String toString() {
		if (goal.equals("enemy")) {
			return("kill " + this.remaining + " enemies");
		} else if (goal.equals("treasure")) {
			return("get " + this.remaining + " treasure");
		} else if (goal.equals("boulders")) {
			return("activate " + this.remaining + " switches");
		} else if (goal.equals("exit")) {
			return("get to the exit");
		}
		return(this.goal + " " + this.remaining + " time");
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public int getRemaining() {
		return remaining;
	}
	
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	
	// TODO: need to add new method of grabbing the number of goal observers, then adding them to goal if
	// it's a goal.
}
