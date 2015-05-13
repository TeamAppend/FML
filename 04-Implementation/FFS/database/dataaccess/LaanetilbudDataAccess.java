package dataaccess;

import java.sql.SQLException;
import java.util.List;

import domain.Laanetilbud;
import domain.LaanetilbudImpl;
import exceptions.LaanetilbudAllreadyExists;
import exceptions.LaanetilbudDoesNotExists;

public interface LaanetilbudDataAccess {

	/*
	 *  Create
	 */
	public abstract void createLaanetilbud(DataAccess dataaccess,
			Laanetilbud laanetilbud) throws SQLException,
			LaanetilbudAllreadyExists;

	/*
	 * Read
	 */
	public abstract List<LaanetilbudImpl> listLaanetilbud(
			DataAccess dataaccess, int kunde_id) throws SQLException;

	/*
	 * Delete
	 */
	public abstract void deleteLaanetilbud(DataAccess dataaccess,
			int lånetilbud_id) throws SQLException, LaanetilbudDoesNotExists;

}