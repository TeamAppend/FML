package logik;

import java.sql.SQLException;

import domain.Kunde;
import exceptions.KundeAllreadyExists;
import exceptions.KundeDoesNotExists;

public interface KundeLogik {

	public abstract void createKunde(Kunde kunde) throws SQLException,
			KundeAllreadyExists;

	public abstract Kunde listKunde(String telefon)
			throws SQLException;
	
	public abstract Kunde hentKunde(int kunde_id)
			throws SQLException;

	public abstract void updateKunde(String kundenavn, String adresse,
			String postnummer, String telefon, int kunde_id)
			throws SQLException, KundeDoesNotExists;

	public abstract void deleteKunde(int kunde_id) throws SQLException,
			KundeDoesNotExists;

	public abstract int findUnique(String telefon) throws SQLException;

}