package staff;

public class Lecturer extends StaffMember {
	String school;
	String status;
	
	/**
	 * Constructor
	 * @param name		name of lecturer
	 * @param salary	salary of lecturer
	 * @param hire_date	date the lecturer was hired
	 * @param end_date	date the lecturer ended
	 * @param school	lecturer's school
	 * @param status	academic status of lecturer:
	 * 
	 * @return 			returns a lecturer
	 */
	public Lecturer(String name, int salary, String school, String status) {
		super(name, salary);
		this.school = school;
		this.status = status;
	}
	
	// getters
	public String getSchool() {
		return this.school;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	// setters
	public void setSchool(String school) {
		this.school = school;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		Lecturer A = (Lecturer) obj;
		if (this.school.equals(A.school) && this.status == A.status) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		String message = super.toString() + "School: " + this.school + "\n" + "Status: " + this.status;
		return message;
	}
	
}

