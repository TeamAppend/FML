package logik;

import java.sql.SQLException;
import java.util.List;

import domain.Kunde;
import domain.KundeImpl;
import exceptions.KundeAllreadyExists;
import exceptions.KundeDoesNotExists;

public interface KundeLogik {

	public abstract void createKunde(Kunde kunde) throws SQLException,
			KundeAllreadyExists;

	public abstract List<KundeImpl> listKunde(String telefon)
			throws SQLException;

	public abstract void updateKunde(String kundenavn, String adresse,
			String postnummer, String telefon, int kunde_id)
			throws SQLException, KundeDoesNotExists;

	public abstract void deleteKunde(int kunde_id) throws SQLException,
			KundeDoesNotExists;

}