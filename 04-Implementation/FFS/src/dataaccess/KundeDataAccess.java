package dataaccess;

import java.sql.SQLException;

import domain.Kunde;
import exceptions.KundeAllreadyExistsException;
import exceptions.KundeDoesNotExistsException;

public interface KundeDataAccess {

	/*
	 * Create
	 */
	public abstract void createKunde(DataAccess dataaccess, Kunde kunde)
			throws SQLException, KundeAllreadyExistsException;

	/*
	 * Read
	 */
	public abstract Kunde listKunde(DataAccess dataaccess, String telefon)
			throws SQLException;
	
	public abstract Kunde hentKunde(DataAccess dataaccess, int kunde_id)
			throws SQLException;

	public abstract int findUnique(DataAccess dataaccess, String telefon)
			throws SQLException;

	/*
	 * Update
	 */
	public abstract void updateKunde(DataAccess dataaccess, String kundenavn,
			String adresse, String postnummer, String telefon, int kunde_id)
			throws SQLException, KundeDoesNotExistsException;

	/*
	 * Delete
	 */
	public abstract void deleteKunde(DataAccess dataaccess, int kunde_id)
			throws SQLException, KundeDoesNotExistsException;

}