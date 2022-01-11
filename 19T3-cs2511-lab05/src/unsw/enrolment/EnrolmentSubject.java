package unsw.enrolment;

import java.util.ArrayList;

public class EnrolmentSubject implements Subject {
	
	ArrayList<Observer> observers = new ArrayList<Observer>();
	
	String course;
	String term;
	String zid;
	String assessment = null;
	int mark = 0;
	
	public EnrolmentSubject(String course, String term, String zid) {
		super();
		this.course = course;
		this.term = term;
		this.zid = zid;
		notifyObservers();
	}
	
	public void newMarks(String assessment, int mark) {
		this.assessment = assessment;
		this.mark = mark;
		notifyObservers();
	}

	@Override
	public void attach(Observer o) {
		observers.add(o);
		notifyObservers();
	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);	
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(this);
		}		
	}

}
