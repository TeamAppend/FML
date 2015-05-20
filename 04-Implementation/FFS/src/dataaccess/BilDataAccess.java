package dataaccess;

import java.sql.SQLException;
import java.util.List;

import domain.Bil;

public interface BilDataAccess {

	/*
	 * Read
	 */
	public abstract Bil listBil(DataAccess dataaccess, int bil_id)
			throws SQLException;
	
	public abstract Bil listBilModel(DataAccess dataaccess, String modelNavn)
			throws SQLException;

	public abstract List<Bil> listAlleBil(DataAccess dataaccess) throws SQLException;

}