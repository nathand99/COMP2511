package unsw.enrolment;
import java.util.ArrayList;
import java.util.List;

/**
 * A course in the enrolment system.
 * @author Robert Clifton-Everest
 *
 */
public class Course {

    private String courseCode;
    private String title;
    private static int uoc = 6;
    ArrayList<Course> prereqs;
    private ArrayList<CourseOffering> courseOfferings;


    public Course(String courseCode, String title) {
        this.courseCode = courseCode;
        this.title = title;
        this.prereqs = new ArrayList<Course>();
        this.courseOfferings = new ArrayList<CourseOffering>();
    }


    public void addPrereq(Course course) {
        prereqs.add(course);
    }

    public void addOffering(CourseOffering offering) {
        courseOfferings.add(offering);
    }

    public String getCourseCode() {
        return courseCode;
    }
    
    public String getTitle() {
        return title;
    }

    public int getUOC() {
        return uoc;
    }


	public ArrayList<Course> getPrereqs() {
		return prereqs;
	}


	public ArrayList<CourseOffering> getCourseOfferings() {
		return courseOfferings;
	}
	
	@Override
	public String toString() {
		String message = this.courseCode;
		return message;
	}
	
}
