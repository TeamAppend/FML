package dataaccess;

import java.sql.SQLException;
import java.util.List;

import domain.Lånetilbud;
import exceptions.LånetilbudAllreadyExists;
import exceptions.LånetilbudDoesNotExists;

public interface LånetilbudDataAccess {

	/*
	 *  Create
	 */
	public abstract void createLånetilbud(DataAccess dataaccess,
			Lånetilbud lånetilbud) throws SQLException,
			LånetilbudAllreadyExists;

	/*
	 * Read
	 */
	public abstract List<Lånetilbud> listLånetilbud(
			DataAccess dataaccess, int kunde_id) throws SQLException;

	/*
	 * Delete
	 */
	public abstract void deleteLånetilbud(DataAccess dataaccess,
			int lånetilbud_id) throws SQLException, LånetilbudDoesNotExists;

}