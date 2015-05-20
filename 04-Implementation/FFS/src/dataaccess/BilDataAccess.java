package dataaccess;

import java.sql.SQLException;
import java.util.List;

import domain.Bil;

public interface BilDataAccess {

	/*
	 * Read
	 */
	public abstract Bil listBil(DataAccess dataaccess, String bil)
			throws SQLException;

	public abstract List<Bil> listAlleBil(DataAccess dataaccess) throws SQLException;

}