package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.CPRDataAccessImpl;
import dataaccess.DataAccess;
import domain.CPRnummer;
import domain.CPRnummerImpl;
import exceptions.CPRAllreadyExists;
import exceptions.CPRDoesNotExists;

public class CPRLogikImpl implements CPRLogik {

	@Override
	public void createCPR(CPRnummer CPRnummer) throws SQLException, CPRAllreadyExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			CPRDataAccessImpl CPRda = new CPRDataAccessImpl();
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
	
	
	@Override
	public List<CPRnummerImpl> listCPR(int CPR_id) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			CPRDataAccessImpl searchda = new CPRDataAccessImpl();
			List<CPRnummerImpl> list =  searchda.listCPR(dataaccess, CPR_id);
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
	public int findUniqueCPR(String CPR) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			CPRDataAccessImpl searchda = new CPRDataAccessImpl();
			int size = searchda.listCPR(dataaccess, CPR);
			dataaccess.commit();
			return size;
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
	public void deleteCPR(int CPR_id) throws SQLException, CPRDoesNotExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			CPRDataAccessImpl CPRda = new CPRDataAccessImpl();
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
