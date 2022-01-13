package unsw.enrolment;
import java.util.ArrayList;
import java.util.List;

public class CourseOffering {

    Course course;
    private String term;
    private ArrayList<Session> sessions;
    private ArrayList<Enrolment> enrolments;

    public CourseOffering(Course course, String term) {
        this.course = course;
        this.term = term;
        this.sessions = new ArrayList<Session>();
        this.enrolments = new ArrayList<Enrolment>();
        // add this course offering to the list of course offerings in the Course
        this.course.addOffering(this);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }
    
    public void addEnrolment(Enrolment enrolment) {
        enrolments.add(enrolment);
    }

    public Course getCourse() {
        return course;
    }

    public String getTerm() {
        return term;
    }
    
    @Override
	public String toString() {
		String message = this.course + " " + this.term;
		return message;
	}

}
