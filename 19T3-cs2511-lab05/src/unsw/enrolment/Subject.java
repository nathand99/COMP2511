package unsw.enrolment;

public interface Subject {
	public void attach(Observer o);
	public void detach(Observer o);
	public void notifyObservers();
	public void newMarks(String assessment, int mark);
}
