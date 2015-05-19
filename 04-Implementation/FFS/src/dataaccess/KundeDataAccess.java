package dataaccess;

import java.sql.SQLException;

import domain.Kunde;
import exceptions.KundeAllreadyExists;
import exceptions.KundeDoesNotExists;

public interface KundeDataAccess {
	public void createKunde(DataAccess dataaccess, Kunde kunde) throws SQLException, KundeAllreadyExists;
	
	public Kunde listKunde(DataAccess dataaccess, String telefon) throws SQLException;
	
	public void updateKunde(DataAccess dataaccess, String kundenavn, String adresse, String postnummer, String telefon, int kunde_id) throws SQLException, KundeDoesNotExists;
	
	public void deleteKunde(DataAccess dataaccess, int kunde_id) throws SQLException, KundeDoesNotExists;
}
