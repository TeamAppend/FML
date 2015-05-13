package dataaccess;

import java.sql.SQLException;
import java.util.List;

import domain.BilImpl;

public interface BilDataAccess {

	/*
	 * Read
	 */
	public abstract List<BilImpl> listBil(DataAccess dataaccess, String bil)
			throws SQLException;

}