package db.utils;

import exceptions.NonFatalException;

public interface QueryProcessor {

	String processQuery(String query) throws NonFatalException;
}
