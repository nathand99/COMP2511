package staff;

public class StaffTest {
	public static void printStaffDetails(StaffMember staff) {
		System.out.println(staff.toString());
	}
	
	public static void main(String[] args) {
		StaffMember staff = new StaffMember("Steve", 1000);
		Lecturer lec = new Lecturer("Andrew", 10000, "CSE", "Lecturer");
		printStaffDetails(staff);
		printStaffDetails(lec);
		
		StaffMember staff1 = new StaffMember("Steve", 1000);
		StaffMember staff2 = new StaffMember("Steve", 1000);
		StaffMember staff3 = new StaffMember("Steve", 1000);
		StaffMember staff4 = new StaffMember("Tom", 1000);
		StaffMember staff5 = new StaffMember("Steve", 5);
		Lecturer lec1 = new Lecturer("Ash", 1000, "CSE", "Lecturer");
		Lecturer lec2 = new Lecturer("Ash", 1000, "CSE", "Lecturer");
		Lecturer lec3 = new Lecturer("Ash", 1000, "CSE", "Lecturer");
		Lecturer lec4 = new Lecturer("John", 1000, "CSE", "Lecturer");
		Lecturer lec5 = new Lecturer("Ash", 1000, "ESC", "Non-Lecturer");
		Lecturer lec6 = new Lecturer("John", 1000, "CSE", "Non-Lecturer");
		
		// testing equals for StaffMember
		System.out.println("testing staff member");
		// true		
		System.out.println("1. " + staff1.equals(staff1)); // reflexive
		System.out.println(staff1 == staff2); // false since they do not point to the same object
		System.out.println("2. " + staff1.equals(staff2)); // symmetric
		System.out.println("3. " + staff2.equals(staff1));
		System.out.println("4. " + staff2.equals(staff3)); // transitive
		// staff1 = staff2 = staff3...so staff1 == staff3
		System.out.println("5. " + staff1.equals(staff3));
		System.out.println("6. " + staff1.equals(staff2)); // consistent
		System.out.println("7. " + staff1.equals(staff2));
		System.out.println("8. " + staff1.equals(staff2));
		// false
		System.out.println("9. " + staff1.equals(staff4));
		System.out.println("10. " + staff1.equals(staff5));
		System.out.println("11. " + staff4.equals(staff5));
		System.out.println("12. " + staff1.equals(lec1));
		
		// testing equals for Lecturer
		System.out.println("testing lecturer");
		// true
		System.out.println("1. " + lec1.equals(lec1)); // reflexive
		System.out.println("2. " + lec1.equals(lec2)); // symmetric
		System.out.println("3. " + lec2.equals(lec1));
		System.out.println("4. " + lec2.equals(lec3)); // transitive
		System.out.println("5. " + lec1.equals(lec3)); //lec1 == lec3
		System.out.println("6. " + lec1.equals(lec2)); // consistent
		System.out.println("7. " + lec1.equals(lec2));
		System.out.println("8. " + lec1.equals(lec2));
		// false
		System.out.println("9. " + lec1.equals(lec4));
		System.out.println("10. " + lec1.equals(lec5));
		System.out.println("11. " + lec1.equals(lec6));
		System.out.println("12. " + lec4.equals(lec6));
		System.out.println("12. " + lec1.equals(staff1));
	}
}
