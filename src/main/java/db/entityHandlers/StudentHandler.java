package db.entityHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.entities.Student;
import exceptions.NonFatalException;

public class StudentHandler<E extends Student> implements DatabaseHandler<Student> {

	private String url, user, password;

	public StudentHandler(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	@Override
	public boolean add(Student entity) throws NonFatalException {

		Student student = (Student) entity;
		String query = "INSERT INTO student(student_id,name,gender,age,mobile,email) VALUES(?,?,?,?,?,?)";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, student.getStudentId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3, student.getGender().name());
			preparedStatement.setInt(4, student.getAge());
			preparedStatement.setString(5, student.getMobile());
			preparedStatement.setString(6, student.getEmail());

			return preparedStatement.execute();
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}

	@Override
	public boolean remove(Student entity) throws NonFatalException {
		Student student = (Student) entity;
		StringBuilder query = new StringBuilder("DELETE FROM student where student_id = " + student.getStudentId());

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
			return preparedStatement.execute();
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}

	@Override
	public List<Student> getAll() throws NonFatalException {
		List<Student> students = new ArrayList<>();
		String query = "SELECT * from student";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet result = preparedStatement.executeQuery(query)) {

			// Add each tuple in the resultSet to the list
			while (result.next()) {
				int studentId = result.getInt("student_id");
				String name = result.getString("name");
				Student.Gender gender = Student.Gender.valueOf(result.getString("gender"));
				int age = result.getInt("age");
				String mobile = result.getString("mobile");
				String email = result.getString("email");
				students.add(new Student(studentId, age, name, mobile, email, gender));
			}
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
		return students;
	}
}
