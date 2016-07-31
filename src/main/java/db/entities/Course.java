package db.entities;

public class Course extends DatabaseEntity {

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", name=" + name + "]";
	}

	private int courseId;
	private String name;

	public Course(int courseId, String name) {
		this.courseId = courseId;
		this.name = name;
	}

	public Course(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
