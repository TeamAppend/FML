package logik;

import java.sql.SQLException;

import dataaccess.DataAccess;
import dataaccess.KundeDataAccess;
import dataaccess.KundeDataAccessImpl;
import domain.Kunde;
import exceptions.KundeAllreadyExists;
import exceptions.KundeDoesNotExists;

public class KundeLogikImpl implements KundeLogik {


	
	@Override
	public void createKunde(Kunde kunde) throws SQLException, KundeAllreadyExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			KundeDataAccess kundeda = new KundeDataAccessImpl();
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
	public Kunde listKunde(String telefon) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			KundeDataAccess searchda = new KundeDataAccessImpl();
			Kunde kunde =  searchda.listKunde(dataaccess, telefon);
			dataaccess.commit();
			return kunde;
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
	public int findUnique(String telefon) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			KundeDataAccess searchda = new KundeDataAccessImpl();
			int count =  searchda.findUnique(dataaccess, telefon);
			dataaccess.commit();
			return count;
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
	public void updateKunde(String kundenavn, String adresse, String postnummer, String telefon, int kunde_id) throws SQLException, KundeDoesNotExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			KundeDataAccess kundeda = new KundeDataAccessImpl();
			kundeda.updateKunde(dataacces, kundenavn, adresse, postnummer, telefon, kunde_id);
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
			KundeDataAccess kundeda = new KundeDataAccessImpl();
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
