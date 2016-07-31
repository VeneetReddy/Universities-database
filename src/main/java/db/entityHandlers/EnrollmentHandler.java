package db.entityHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.entities.Enrollment;
import exceptions.NonFatalException;

public class EnrollmentHandler<E extends Enrollment> implements DatabaseHandler<Enrollment> {

	private String url, user, password;

	public EnrollmentHandler(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	@Override
	public boolean add(Enrollment entity) throws NonFatalException {
		Enrollment enrollment = (Enrollment) entity;
		String query = "INSERT INTO enrollment(enrollment_id,university_id,course_id,student_id) VALUES(?,?,?,?)";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, enrollment.getEnrollmentId());
			preparedStatement.setInt(2, enrollment.getUniversityId());
			preparedStatement.setInt(3, enrollment.getCourseId());
			preparedStatement.setInt(4, enrollment.getStudentId());
			return preparedStatement.execute();

		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}

	@Override
	public boolean remove(Enrollment entity) throws NonFatalException {
		Enrollment enrollment = (Enrollment) entity;
		StringBuilder query = new StringBuilder(
				"DELETE FROM enrollment where enrollment_id = " + enrollment.getEnrollmentId());

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
			return preparedStatement.execute();
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}

	@Override
	public List<Enrollment> getAll() throws NonFatalException {
		List<Enrollment> enrollments = new ArrayList<>();
		String query = "SELECT * from enrollment";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet result = preparedStatement.executeQuery(query)) {

			// Add each tuple in the resultSet to the list
			while (result.next()) {
				int courseId = result.getInt("course_id");
				int universityId = result.getInt("university_id");
				int enrollmentId = result.getInt("enrollment_id");
				int studentId = result.getInt("student_id");
				enrollments.add(new Enrollment(enrollmentId, studentId, universityId, courseId));
			}
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
		return enrollments;
	}

}
