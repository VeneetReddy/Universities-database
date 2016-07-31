package db.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Instantiate this class to load the JDBC driver into the JVM.
 * 
 * @author venee
 *
 */
public final class DriverLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverLoader.class);
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			LOGGER.error("Exception while loading the driver!", e);
		} catch (IllegalAccessException e) {
			LOGGER.error("Exception while loading the driver!", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Exception while loading the driver!", e);
		}
	}
}
