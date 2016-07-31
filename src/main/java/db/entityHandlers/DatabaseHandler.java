/**
 * Provides interface for basic operations that
 * a table must implement
 */
package db.entityHandlers;

import java.util.List;

import db.entities.DatabaseEntity;
import exceptions.NonFatalException;

/**
 * @author venee
 * @param <P>
 *
 */
public interface DatabaseHandler<E extends DatabaseEntity> {
	boolean add(E entity) throws NonFatalException;

	/**
	 * Needs to have the primary key set
	 * 
	 * @param entity
	 * @return
	 */
	boolean remove(E entity) throws NonFatalException;

	/**
	 * Returns a List of all entities
	 * 
	 * @return
	 */
	List<E> getAll() throws NonFatalException;
}
