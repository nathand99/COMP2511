package unsw.enrolment;

public class Grade {
    private int mark;
    private String grade;
	
    public Grade(int mark, String grade) {
		this.mark = mark;
		this.grade = grade;
	} 
    
    @Override
	public String toString() {
		String message = this.grade + " (mark: " + this.mark + ")";
		return message;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
    
    
}
