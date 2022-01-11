package unsw.enrolment;

import java.util.List;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.student = student;
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }
    
    public void giveGrade(int mark, String grade) {
        this.grade = new Grade(mark, grade);
    }
    
    public boolean prereqMet(Course course, List<Enrolment> e) {
    	List<Enrolment> enrol = e;
    	List<Course> cp = course.prereqs;
    	if (course.prereqs == null) {
    		return true;
    	}
    	Course toRemove = null;
    	for (Course c : course.prereqs) {
    		for (Enrolment en : enrol) {
    			
    			cp.remove(toRemove);
    			if (en.offering.course.equals(c) && en.grade.mark >= 50) {   				
    				toRemove = c;
    			}
    		}
    		
    	}
    	cp.remove(toRemove);
    	if (cp.isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    }

	@Override
	public String toString() {
		return "Enrolment [offering=" + offering + ", grade=" + grade + ", student=" + student + "]";
	}
}
