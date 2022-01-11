package unsw.enrolment.test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Lecture;
import unsw.enrolment.Session;
import unsw.enrolment.Student;

public class EnrolmentTest {

    public static void main(String[] args) {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // TODO Create some sessions for the courses
        Session comp1511Session = new Session("CLB7", DayOfWeek.of(1), LocalTime.of(14,00,00), LocalTime.of(16,00,00));
        comp1511Offering.addSession(comp1511Session);
        Session comp1531Session = new Session("CLB8", DayOfWeek.of(2), LocalTime.of(9,00,00), LocalTime.of(11,00,00));
        comp1531Offering.addSession(comp1531Session);
        Session comp2521Session = new Session("CLB9", DayOfWeek.of(3), LocalTime.of(12,00,00), LocalTime.of(13,00,00));
        comp2521Offering.addSession(comp2521Session);
        // TODO Create a student
        Student new_student = new Student("123456");
        List<Enrolment> subjects = new ArrayList<Enrolment>();
        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
        
        Enrolment new_student_comp1511 = new Enrolment(comp1511Offering, new_student);
        // if prereqs not met, enrolment is null
        if (!new_student_comp1511.prereqMet(comp1511, subjects)) {
        	new_student_comp1511 = null;
        }
        System.out.println(new_student_comp1511);
        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        Enrolment new_student_comp1531 = new Enrolment(comp1531Offering, new_student);
     // prereq not met, enrolment will be null
        if (!new_student_comp1531.prereqMet(comp1531, subjects)) {
        	new_student_comp1531 = null;
        }
        if (new_student_comp1531 == null) {
        	System.out.println("new_student_comp1531 is null - enrolment rejected because prereqs not met");
        } else {
        	System.out.println(new_student_comp1531);
        }
        // TODO Give the student a passing grade for COMP1511
        new_student_comp1511.giveGrade(55, "PS");
        subjects.add(new_student_comp1511);
        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        Enrolment new_student_comp2521 = new Enrolment(comp2521Offering, new_student);
        // prereqs are met
        if (!new_student_comp2521.prereqMet(comp2521, subjects)) {
        	new_student_comp2521 = null;
        }
        if (new_student_comp2521 == null) {
        	System.out.println("new_student_comp2521 is null - enrolment rejected because prereqs not met");
        } else {
        	System.out.println(new_student_comp2521);
        }
    }
}
