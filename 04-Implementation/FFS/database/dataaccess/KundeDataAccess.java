package dataaccess;

import java.sql.SQLException;
import java.util.List;

import domain.Kunde;
import domain.KundeImpl;
import exceptions.KundeAllreadyExists;
import exceptions.KundeDoesNotExists;

public interface KundeDataAccess {
	public void createKunde(DataAccess dataaccess, Kunde kunde) throws SQLException, KundeAllreadyExists;
	
	public List<KundeImpl> listKunde(DataAccess dataaccess, String telefon) throws SQLException;
	
	public void updateKunde(DataAccess dataaccess, String kundenavn, String adresse, String postnummer, String telefon, String kommentar, int kunde_id) throws SQLException, KundeDoesNotExists;
	
	public void deleteKunde(DataAccess dataaccess, int kunde_id) throws SQLException, KundeDoesNotExists;
}
