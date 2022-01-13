package unsw.enrolment;

import java.util.ArrayList;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade = null;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.student = student;

        // add enrollment to CourseOffering and student
        this.offering.addEnrolment(this);
        this.student.addEnrolment(this);
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
    
    /**
     * prereq<et
     * 
     * checks if prereqs for a course have been met by student
     * 
     * @param course - course that student wishes to enrol in
     * @param s - student who wishes to enrol
     * @return True if prereqs met - student allowed to enrol in course. False otherwise
     */
    public boolean prereqMet(Course course, Student s) {
    	ArrayList<Enrolment> enrol = s.getEnrolments();
    	//ArrayList<Course> cp = course.prereqs;
    	if (course.prereqs == null) {
    		return true;
    	}
    	// for each prereq
    	for (Course c : course.prereqs) {
    		boolean completed = false;
    		// check each course student has done
    		for (Enrolment en : enrol) {
    			// enrolment matches prereq
    			if (en.offering.course.equals(c)) {
    				// student has no mark or mark < 50 - return false
    				if (en.grade == null || en.grade.getMark() < 50) {
    					return false;
    				} else {
    					completed = true;
    					break;
    				}
    			}
    			// student has not done this prereq - return false
    			if (!completed) return false;
    		}   		
    	}
    	return true;
    }

	@Override
	public String toString() {
		return "Enrolment [offering=" + offering + ", grade=" + grade + ", student=" + student + "]";
	}
}
