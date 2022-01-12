package staff;

import java.util.Date;
/**
 * A staff member
 * @author Robert Clifton-Everest
 *
 */
public class StaffMember {
	String name;
	int salary;
	Date hire_date;
	String end_date;
	
	/**
	 * constructor
	 * @param name		name of lecturer
	 * @param salary	salary of lecturer
	 * @param hire_date	date the lecturer was hired
	 * @param end_date	date the lecturer ended
	 * 
	 * @return			returns a staff member
	 */
	public StaffMember(String name, int salary) {
		this.name = name;
		this.salary = salary;
		this.hire_date = new Date();
		this.end_date = "-";
	}
	
	// getters
	public String getName() {
		return this.name;
	}
	
	public int getSalary() {
		return this.salary;
	}
	
	public Date getHire_date() {
		return this.hire_date;
	}
	
	public String getEnd_date() {
		return this.end_date;
	}
	
	//setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	// overriding equals method
	// checking whether the current object (this) is the same as obj passed in?
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		// obj must be the same class as (this) - cast it (we give a new name A)
		// check all variables are the same
		StaffMember A = (StaffMember) obj;
		if (this.name.equals(A.name) &&
			this.salary == A.salary &&
			this.hire_date.equals(A.hire_date) &&
			this.end_date.equals(A.end_date)) {
			return true;
		} else {
			return false;
		}
	}
	
	// overriding toString method
	// if not overidden, then it just prints the memory location
	@Override
	public String toString() {
		String message = "Name: " + this.name + "\n" + "Salary: " + this.salary + "\n" + "Hire Date: " + this.hire_date + "\n" + "End Date: " + this.end_date + "\n";
		return message;
	}
	
	
	
}
