package unsw.enrolment;
import java.util.ArrayList;

/**
 * 
 * @author z5204935
 * 
 * Student
 * @param zid	
 * @param enrolment	- courseOfferings enrolled in
 *
 */
public class Student {

    private String zid;
    private ArrayList<Enrolment> enrolments;

	public Student(String zid) {
        this.zid = zid;
        enrolments = new ArrayList<Enrolment>();
    }
	
	public void addEnrolment(Enrolment enrolment) {
        enrolments.add(enrolment);
    }
	
	public ArrayList<Enrolment> getEnrolments() {
		return enrolments;
	}

	public String getZID() {
		return zid;
	}
	
	@Override
	public String toString() {
		return this.zid;
	}

}
