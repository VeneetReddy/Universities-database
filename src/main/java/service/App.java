package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.entities.Student;
import db.entityHandlers.DatabaseHandler;
import db.entityHandlers.StudentHandler;
import db.utils.QueryProcessor;
import db.utils.QueryProcessorImpl;
import exceptions.NonFatalException;

public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		DatabaseHandler<Student> handler = new StudentHandler<>("jdbc:mysql://localhost/Universities", "root",
				"xtinctdinos");
		QueryProcessor processor = new QueryProcessorImpl("jdbc:mysql://localhost/Universities", "root", "xtinctdinos");
		try {

			// handler.add(new
			// Student(1,22,"Veneet","8123545215","veneet@raremile.com",Student.Gender.M));
			LOGGER.info("\n" + processor.processQuery("select * from student"));
		} catch (NonFatalException e) {
			LOGGER.error("A Non fatal exception occured!", e);
		}

	}

}
