package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;
    private List<Session> sessions;
    EnrolmentSubject es;
    EnrolmentObserver eo;

    public Enrolment(CourseOffering offering, Student student, Session... sessions) {
        this.offering = offering;
        this.student = student;
        this.grade = null; // Student has not completed course yet.
        student.addEnrolment(this);
        offering.addEnrolment(this);
        this.sessions = new ArrayList<>();
        for (Session session : sessions) {
            this.sessions.add(session);
        }
        es = new EnrolmentSubject(offering.getCourse().getCourseCode(), offering.getTerm(), student.getZID());
        eo = new EnrolmentObserver();
        es.attach(eo);
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public boolean hasPassed() {
        return grade != null && grade.isPassing();
    }

//    Whole course marks can no longer be assigned this way.
//    public void assignMark(int mark) {
//        grade = new Grade(mark);
//    }
    
    public Component marks = new Composite("marks", 100);
    
    public Component newComposite(String name, int marksPossible) {
    	return new Composite(name, marksPossible);
    }
    
    public Component newLeaf(String name, int mark, int marksPossible) {
    	es.newMarks(name, mark);
    	return new Leaf(name, mark, marksPossible);
    }
    
    public void setMarks(Component c, String method, String name) {
    	c.setMark(c.calculateMark(method));
    	int mark = c.calculateMark(method);
    	es.newMarks(name, mark);
    }
    
    public void giveGrade() {
    	int mark = marks.calculateMark("sum");
    	Grade grade = new Grade(mark);
    	this.grade = grade;
    }
}
