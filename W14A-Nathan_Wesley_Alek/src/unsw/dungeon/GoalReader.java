package unsw.dungeon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.Entities.Enemy;
import unsw.dungeon.Entities.FloorSwitch;
import unsw.dungeon.Entities.Player;
import unsw.dungeon.Entities.Treasure;

/**
 * public class GoalReader
 * 
 * Grabs goals in the .json file.
 * 
 */
public class GoalReader {
	private PlayerGoal goals;
	private Player player;
	private JSONObject json;
	private List<Entity> entities;
	
	public GoalReader(PlayerGoal goals, Player player, String filename, List<Entity> entities) 
			throws FileNotFoundException { 
		// can attach list of entities later to read how many of each goal is needed.
		this.goals = goals;
		this.player = player;
		this.entities = entities;
		// System.out.println(filename);
		json = new JSONObject(new JSONTokener(new FileReader(filename)));
	}
	
	/**
	 * loadGoal: loads the goals from the JSON file into the PlayerGoal.
	 */
	public void loadGoal() {
		JSONObject JSONGoals = json.getJSONObject("goal-condition"); //goal-condition is an object
		String initialGoal = (String) JSONGoals.get("goal");
		//System.out.println(initialGoal);
		//JSONObject JSONGoals = json.getJSONObject("goal-condition");
		Goal playerGoals = createGoal(initialGoal, JSONGoals);
		goals.addGoal(playerGoals);
		player.addGoals(goals);
		goals.setPlayer(player);
	}
	
	/**
	 * createMultiGoal: creates a multipleGoal by reading in the subgoals from the json.
	 * @param conjunction - "and" or "or"
	 * @return
	 */
	public MultipleGoal createMultiGoal(String conjunction, JSONObject JSONGoals) {
		MultipleGoal newGoal = new MultipleGoal(conjunction);
		//JSONGoals = json.getJSONObject("goal-condition"); //goal-condition is an object
		JSONArray subgoals = JSONGoals.getJSONArray("subgoals");
		/*for (int j = 0; j < depth; j++) {
			subgoals = ((JSONArray) subgoals).getJSONArray("subgoals");
		}*/
		// System.out.println(subgoals.length());
		for (int i = 0; i < subgoals.length(); i++) {
			JSONObject subgoal = subgoals.getJSONObject(i);
			String subgoalString = (String) subgoal.get("goal");
			// System.out.println(subgoalString);
			Goal nextGoal = createGoal(subgoalString, subgoal);
			newGoal.addGoal(nextGoal);
			
		}
		return newGoal;
	}
	
	/**
	 * createSingleGoal - as it suggests.
	 * @param goal - the string of the "goal" that is in the json.
	 * @return
	 */
	public SingleGoal createSingleGoal(String goal) {
		SingleGoal newGoal = new SingleGoal();
		int i = 0;
		System.out.println(goal);
		switch(goal) {
		case "boulders":
			for (Entity entity : entities) {
				if (entity.getEntityName().equals("floorSwitch")) {
					i++;
				}
			}
			newGoal.setGoal(goal);
			break;
		case "enemies":
			for (Entity entity : entities) {
				if (entity.getEntityName().equals("enemy")) {
					i++;
				}
			}
			newGoal.setGoal("enemy");
			break;
		case "treasure":
			for (Entity entity : entities) {
				if (entity.getEntityName().equals("treasure")) {
					i++;
				}
			}
			newGoal.setGoal(goal);
			break;
		case "exit":
			newGoal.setGoal(goal);
			newGoal.setRemaining(1); // doesn't make sense to have to exit more than once.
			return newGoal;
		}
		System.out.println(i);
		newGoal.setRemaining(i);
		return newGoal;
	}
	
	/**
	 * createGoal - general if-else-if-else decision of MultipleGoal or SingleGoal.
	 * @param goal - "AND", "OR" or a goal (ie. "boulders").
	 * @return
	 */
	public Goal createGoal(String goal, JSONObject JSONGoals) {
		// and goal
		if (goal.equals("AND")) {
			MultipleGoal andGoal = createMultiGoal("and", JSONGoals);
			return andGoal;
		// or goal
		} else if (goal.equals("OR")) {
			MultipleGoal orGoal = createMultiGoal("or", JSONGoals);
			return orGoal;
		// single goal
		} else {
			// currently adds only once, needs to add multiple later.
			SingleGoal singleGoal = createSingleGoal(goal);
			return singleGoal;
		}
	}

	public PlayerGoal getGoals() {
		return goals;
	}

	public void setGoals(PlayerGoal goals) {
		this.goals = goals;
	}
	
	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
