package db.entityHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.entities.University;

import exceptions.NonFatalException;

public class UniversityHandler<E extends University> implements DatabaseHandler<University> {

	private String url, user, password;

	public UniversityHandler(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public boolean add(University entity) throws NonFatalException {

		University university = (University) entity;
		String query = "INSERT INTO university(university_id,name) VALUES(?,?)";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, university.getUniversityId());
			preparedStatement.setString(2, university.getName());
			return preparedStatement.execute();

		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}

	public boolean remove(University entity) throws NonFatalException {

		University university = (University) entity;
		StringBuilder query = new StringBuilder(
				"DELETE FROM university where university_id = " + university.getUniversityId());

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
			return preparedStatement.execute();
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}

	public List<University> getAll() throws NonFatalException {

		List<University> universities = new ArrayList<>();
		String query = "SELECT * from university";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet result = preparedStatement.executeQuery(query)) {

			// Add each tuple in the resultSet to the list
			while (result.next()) {
				int universityId = result.getInt("university_id");
				String name = result.getString("name");
				University university = new University(universityId, name);
				universities.add(university);
			}
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
		return universities;
	}

}
