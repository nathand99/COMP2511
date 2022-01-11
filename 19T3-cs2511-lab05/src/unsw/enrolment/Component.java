package unsw.enrolment;

public interface Component {
	public int calculateMark(String method);
	public void addComponent(Component c);
	public void removeComponent(Component c);
	public void setMark(int calculateMark);
	public int getMark();
}
