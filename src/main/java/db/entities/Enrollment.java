package db.entities;

public class Enrollment extends DatabaseEntity {
	private int enrollmentId, studentId, universityId, courseId;

	public Enrollment(int enrollmentId, int studentId, int universityId, int courseId) {
		this.enrollmentId = enrollmentId;
		this.studentId = studentId;
		this.universityId = universityId;
		this.courseId = courseId;
	}

	public Enrollment(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", studentId=" + studentId + ", universityId="
				+ universityId + ", courseId=" + courseId + "]";
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getUniversityId() {
		return universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
}
