package db.entityHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.entities.ExamResult;
import exceptions.NonFatalException;

public class ExamResultHandler<E extends ExamResult> implements DatabaseHandler<ExamResult> {

	private String url, user, password;

	public ExamResultHandler(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	@Override
	public boolean add(ExamResult entity) throws NonFatalException {
		ExamResult examResult = (ExamResult) entity;
		String query = "INSERT INTO exam_result(enrollment_id,year,grades) VALUES(?,?,?)";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, examResult.getEnrollmentId());
			preparedStatement.setInt(2, examResult.getYear());
			preparedStatement.setString(3, examResult.getGrade().name());
			return preparedStatement.execute();

		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}

	@Override
	public boolean remove(ExamResult entity) throws NonFatalException {
		ExamResult examResult = (ExamResult) entity;
		StringBuilder query = new StringBuilder(
				"DELETE FROM exam_result where enrollment_id = " + examResult.getEnrollmentId());

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
			return preparedStatement.execute();
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}

	@Override
	public List<ExamResult> getAll() throws NonFatalException {
		List<ExamResult> examResults = new ArrayList<>();
		String query = "SELECT * from exam_result";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet result = preparedStatement.executeQuery(query)) {

			// Add each tuple in the resultSet to the list
			while (result.next()) {
				int enrollmentId = result.getInt("enrollment_id");
				int year = result.getInt("year");
				ExamResult.Grade grade = ExamResult.Grade.valueOf(result.getString("grade"));
				examResults.add(new ExamResult(enrollmentId, year, grade));
			}
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
		return examResults;
	}

}
