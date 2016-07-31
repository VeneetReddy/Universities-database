package db.entities;

public class Student extends DatabaseEntity {

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", age=" + age + ", name=" + name + ", mobile=" + mobile + ", email="
				+ email + ", gender=" + gender + "]";
	}

	public enum Gender {
		M, F, O
	}

	private int studentId, age;
	private String name, mobile, email;
	private Gender gender;

	public Student(int studentId, int age, String name, String mobile, String email, Gender gender) {
		this.studentId = studentId;
		this.age = age;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
	}

	public Student(int studentId) {
		this.studentId = studentId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;

	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
