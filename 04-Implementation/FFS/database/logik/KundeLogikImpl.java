package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.KundeDataAccessImpl;
import domain.Kunde;
import domain.KundeImpl;
import exceptions.KundeAllreadyExists;
import exceptions.KundeDoesNotExists;

public class KundeLogikImpl implements KundeLogik {


	
	@Override
	public void createKunde(Kunde kunde) throws SQLException, KundeAllreadyExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			KundeDataAccessImpl kundeda = new KundeDataAccessImpl();
			kundeda.createKunde(dataacces, kunde);
			dataacces.commit();
		} catch (Exception e) {
			if (dataacces != null) {
				dataacces.rollback();
			}
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			if (dataacces != null) {
				dataacces.close();
			}
		}
	}
	

	@Override
	public List<KundeImpl> listKunde(String telefon) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			KundeDataAccessImpl searchda = new KundeDataAccessImpl();
			List<KundeImpl> list =  searchda.listKunde(dataaccess, telefon);
			dataaccess.commit();
			return list;
		} catch (Exception e) {
			if (dataaccess != null) {
				dataaccess.rollback();
			}
			throw e;
		} finally {
			if (dataaccess != null) {
				dataaccess.close();
			}
			
		}
	}
	
	

	
	@Override
	public void updateKunde(String kundenavn, String adresse, String postnummer, String telefon, String kommentar, int kunde_id) throws SQLException, KundeDoesNotExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			KundeDataAccessImpl kundeda = new KundeDataAccessImpl();
			kundeda.updateKunde(dataacces, kundenavn, adresse, postnummer, telefon, kommentar, kunde_id);
			dataacces.commit();
		} catch (Exception e) {
			if (dataacces != null) {
				dataacces.rollback();
			}
			throw e;
		} finally {
			if (dataacces != null) {
				dataacces.close();
			}
		}
	}

	
	@Override
	public void deleteKunde(int kunde_id) throws SQLException, KundeDoesNotExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			KundeDataAccessImpl kundeda = new KundeDataAccessImpl();
			kundeda.deleteKunde(dataacces, kunde_id);
			dataacces.commit();
		} catch (Exception e) {
			if (dataacces != null) {
				dataacces.rollback();
			}
			throw e;
		} finally {
			if (dataacces != null) {
				dataacces.close();
			}
		}
	}

}
