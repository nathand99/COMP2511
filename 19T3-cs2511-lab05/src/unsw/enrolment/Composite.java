package unsw.enrolment;

import java.util.ArrayList;

public class Composite implements Component {
	
	private String name; 
	private int mark;
	private int marksPossible;
	
	ArrayList<Component> components = new ArrayList<Component>();
	
	public Composite (String name, int marksPossible) {
		this.name = name;
		this.marksPossible = marksPossible;
	}
	
	public int calculateMark(String method) {
		int totalMark;
		switch(method) {
			case "sum":
				 totalMark = this.getMark();
				for (Component c : components) {
					totalMark = totalMark + c.calculateMark(method);
				}
				return totalMark;
			case "average":
				totalMark = this.getMark();
				for (Component c : components) {
					totalMark = totalMark + (c.calculateMark(method) / components.size());
				}
				return totalMark;
			default:
				return 0;
		}
		
	}
	
	

	// add a component to components
	public void addComponent(Component c) {
		components.add(c);
	}
	
	// remove a component to components
	public void removeComponent(Component c) {
		components.remove(c);
	}
	
	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public int getMarksPossible() {
		return marksPossible;
	}

	public void setMarksPossible(int marksPossible) {
		this.marksPossible = marksPossible;
	}
	
}
