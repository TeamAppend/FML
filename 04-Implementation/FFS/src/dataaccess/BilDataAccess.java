package dataaccess;

import java.sql.SQLException;

import domain.Bil;

public interface BilDataAccess {

	/*
	 * Read
	 */
	public abstract Bil listBil(DataAccess dataaccess, String bil)
			throws SQLException;

}