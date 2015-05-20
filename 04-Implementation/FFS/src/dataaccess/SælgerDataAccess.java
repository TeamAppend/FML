package dataaccess;

import java.sql.SQLException;
import java.util.List;

import domain.Sælger;

public interface SælgerDataAccess {

	/*
	 * Read
	 */
	public abstract List<Sælger> listAlleSælger(DataAccess dataaccess)
			throws SQLException;

	public abstract Sælger listSælger(DataAccess dataaccess, int sælger_id)
			throws SQLException;
	
	public abstract Sælger listSælgerBeløb(DataAccess dataaccess, String sælgernavn)
			throws SQLException;

}