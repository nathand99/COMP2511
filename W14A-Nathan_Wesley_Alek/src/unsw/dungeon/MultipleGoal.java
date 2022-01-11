package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * MultipleGoal - stem and leaf of the Goal.
 * Composite pattern.
 *
 */
public class MultipleGoal implements Goal {
	private List<Goal> goals;
	private String conjunction;
	
	public MultipleGoal(String conjunction) {
		this.goals = new ArrayList<Goal>();
		this.conjunction = conjunction;
	}
	
	@Override
	public boolean checkComplete() {
		// TODO Auto-generated method stub
		if (this.conjunction.equals("or")) {
			for (Goal goal : goals) {
				if (goal.checkComplete() == true) {
					return true;
				}
			}
			return false;
		} else {
			for (Goal goal : goals) {
				if (goal.checkComplete() == false) {
					return false;
				}
			}
			return true;
		}
		
	}
	
	@Override
	public boolean onlyExit() {
		if (this.conjunction.equals("or")) {
			for (Goal goal : goals) {
				if (goal.onlyExit() == true) {
					return true;
				}
			}
			return false;
		} else {
			for (Goal goal : goals) {
				if (goal.onlyExit() == false) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public void addComplete(String obj) {
		// TODO Auto-generated method stub
		for (Goal goal : goals) {
			goal.addComplete(obj);
		}
	}

	@Override
	public void removeComplete(String obj) {
		// TODO Auto-generated method stub
		for (Goal goal : goals) {
			goal.removeComplete(obj);
		}
	}
	
	@Override
	public String toString() {
		String string = "(";
		for (int i = 0; i < goals.size(); i++) {
			Goal goal = goals.get(i);
			if (i == goals.size() - 1) {
				string += goal.toString();
				continue;
			}
			string += goal.toString();
			string += " ";
			string += this.conjunction;
			string += " ";
		}
		string += ")";
		return string;
	}

	public List<Goal> getGoals() {
		return goals;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	
	public void addGoal(Goal goal) {
		this.goals.add(goal);
	}

	public String getConjunction() {
		return conjunction;
	}

	public void setConjunction(String conjunction) {
		this.conjunction = conjunction;
	}
	
}
