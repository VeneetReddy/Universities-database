package db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import exceptions.NonFatalException;

public class QueryProcessorImpl implements QueryProcessor {

	private String url, user, password;

	public QueryProcessorImpl(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	private String getFormattedString(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int numberOfColumns = metaData.getColumnCount();
		StringBuilder resultString = new StringBuilder();

		while (result.next()) {
			for (int col = 1; col <= numberOfColumns; col++) {
				resultString.append("\n" + metaData.getColumnLabel(col) + " : " + result.getObject(col));
			}

			resultString.append("\n\n");
		}
		return resultString.toString();
	}

	public String processQuery(String query) throws NonFatalException {

		if (query == null || query.isEmpty()) {
			throw new NonFatalException("Empty query string given!", new IllegalArgumentException());
		}

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet result = preparedStatement.executeQuery()) {

			return getFormattedString(result);
		} catch (SQLException e) {
			throw new NonFatalException("Exception while executing query : " + query, e);
		}
	}
}
