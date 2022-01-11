package unsw.enrolment;

public class Leaf implements Component {
	
	private String name; 
	private int mark;
	private int marksPossible;
	
	public Leaf(String name, int mark, int marksPossible) {
		this.name = name;
		this.mark = mark;
		this.marksPossible = marksPossible;
	}
	
	@Override
	public int calculateMark(String method) {
		return this.getMark();
	}
	
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

	@Override
	public void addComponent(Component c) {
		System.out.println("Error!");	
	}

	@Override
	public void removeComponent(Component c) {
		System.out.println("Error!");	
	}

	

}
