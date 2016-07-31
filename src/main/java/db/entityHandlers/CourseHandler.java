package db.entityHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.entities.Course;
import exceptions.NonFatalException;

public class CourseHandler<E extends Course> implements DatabaseHandler<Course> {

	private String url, user, password;

	public CourseHandler(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	@Override
	public boolean add(Course entity) throws NonFatalException {
		Course course = (Course) entity;
		String query = "INSERT INTO course(course_id,name) VALUES(?,?)";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, course.getCourseId());
			preparedStatement.setString(2, course.getName());
			return preparedStatement.execute();

		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}

	@Override
	public boolean remove(Course entity) throws NonFatalException {
		Course course = (Course) entity;
		StringBuilder query = new StringBuilder("DELETE FROM course where course_id = " + course.getCourseId());

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
			return preparedStatement.execute();
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}

	@Override
	public List<Course> getAll() throws NonFatalException {
		List<Course> courses = new ArrayList<>();
		String query = "SELECT * from course";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet result = preparedStatement.executeQuery(query)) {

			// Add each tuple in the resultSet to the list
			while (result.next()) {
				int courseId = result.getInt("course_id");
				String name = result.getString("name");
				courses.add(new Course(courseId, name));
			}
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
		return courses;
	}

}
