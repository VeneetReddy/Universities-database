package db.entities;

public class ExamResult extends DatabaseEntity {

	@Override
	public String toString() {
		return "ExamResult [enrollmentId=" + enrollmentId + ", year=" + year + ", grade=" + grade + "]";
	}

	public enum Grade {
		A, B, C, D, E, F
	}

	private int enrollmentId, year;
	private Grade grade;

	public ExamResult(int enrollmentId, int year, db.entities.ExamResult.Grade grade) {
		this.enrollmentId = enrollmentId;
		this.year = year;
		this.grade = grade;
	}

	public ExamResult(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
}
