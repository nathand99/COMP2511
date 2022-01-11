package unsw.enrolment.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Grade;
import unsw.enrolment.Leaf;
import unsw.enrolment.Component;
import unsw.enrolment.Composite;
import unsw.enrolment.Lecture;
import unsw.enrolment.Student;
import unsw.enrolment.Tutorial;

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

        // Create some sessions for the courses
        Lecture lecture1511 = new Lecture(comp1511Offering, "Rex Vowels", DayOfWeek.TUESDAY, LocalTime.of(12, 0),LocalTime.of(14, 0), "Andrew Taylor");
        Lecture lecture1531 = new Lecture(comp1531Offering, "CLB 5", DayOfWeek.MONDAY, LocalTime.of(9, 0),LocalTime.of(11, 0), "Aarthi Natarajan");
        Lecture lecture2521 = new Lecture(comp2521Offering, "Clancy Auditorium", DayOfWeek.FRIDAY, LocalTime.of(15, 0),LocalTime.of(17, 0), "Ashesh Mahidadia");

        Tutorial tute1531 = new Tutorial(comp1531Offering, "Quad 1041", DayOfWeek.WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(19,0), "Hugh Chan");

        // Create a student
        Student student = new Student("z5555555");

        // Enrol the student in COMP1511 for T1 (this should succeed)
        Enrolment comp1511Enrolment = comp1511Offering.enrol(student, lecture1511);

        if (comp1511Enrolment != null)
            System.out.println("Enrolled in COMP1511");

        // Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        Enrolment comp1531Enrolment = comp1531Offering.enrol(student, lecture1531, tute1531);

        if (comp1531Enrolment == null)
            System.out.println("Can't enrol in COMP1531");

        // Give the student a passing grade for COMP1511
//        comp1511Enrolment.assignMark(65);

        // Assign marks for comp1511
        // TODO Give the student a passing mark for assignment 1
        Component assignment1 = comp1511Enrolment.newLeaf("assignment1", 20, 30);
        
        // TODO Give the student a passing mark for milestone 1 of assignment 2
        Component assignment2 = comp1511Enrolment.newComposite("assignment2", 20);
        Component milestone1 = comp1511Enrolment.newLeaf("milestone1", 10, 20);
        assignment2.addComponent(milestone1);  
        
        // TODO Give the student a passing mark for milestone 2 of assignment 2
        Component milestone2 = comp1511Enrolment.newLeaf("milestone2", 16, 20);
        assignment2.addComponent(milestone2);
        
        // TODO Give the student an assignment 2 mark which is the average of
        // milestone 1 and 2
       // assignment2.setMark(assignment2.calculateMark("average"));
        comp1511Enrolment.setMarks(assignment2, "average", "assignment2");
        milestone1.setMark(0);
        milestone2.setMark(0);
        
        // TODO Give the student a prac mark which is the sum of assignment 1
        // and 2
        Component prac = comp1511Enrolment.newComposite("prac", 50);
        prac.addComponent(assignment1);
        prac.addComponent(assignment2);
        comp1511Enrolment.marks.addComponent(prac);
        //prac.setMark(prac.calculateMark("sum"));
        comp1511Enrolment.setMarks(prac, "sum", "prac");
        
        
        // TODO Give the student a passing exam mark.
        Component exam = comp1511Enrolment.newLeaf("exam", 30, 50);
        comp1511Enrolment.marks.addComponent(exam);
        
        comp1511Enrolment.giveGrade();
        // System.out.println(prac.getMark());
        // System.out.println(exam.getMark());
        // Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        Enrolment comp2521Enrolment = comp2521Offering.enrol(student, lecture2521);

        if (comp2521Enrolment != null)
            System.out.println("Enrolled in COMP2521");
    }
}
