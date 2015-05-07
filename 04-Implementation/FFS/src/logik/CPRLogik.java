package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.CPRDataAccess;
import dataaccess.DataAccess;
import domain.CPRnummer;
import exceptions.CPRAllreadyExists;
import exceptions.CPRDoesNotExists;

public class CPRLogik {


	public void createCPR(CPRnummer CPRnummer) throws SQLException, CPRAllreadyExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			CPRDataAccess CPRda = new CPRDataAccess();
			CPRda.createCPR(dataacces, CPRnummer);
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
	
	public List<CPRnummer> listCPR(int CPR_id) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			CPRDataAccess searchda = new CPRDataAccess();
			List<CPRnummer> list =  searchda.listCPR(dataaccess, CPR_id);
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
	

	public void deleteCPR(int CPR_id) throws SQLException, CPRDoesNotExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			CPRDataAccess CPRda = new CPRDataAccess();
			CPRda.deleteCPR(dataacces, CPR_id);
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
