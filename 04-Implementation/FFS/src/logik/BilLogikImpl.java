package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.BilDataAccess;
import dataaccess.BilDataAccessImpl;
import dataaccess.DataAccess;
import domain.Bil;
import exceptions.BilDoesNotExistException;

public class BilLogikImpl implements BilLogik{

	@Override
	public Bil listBil(int bil_id) throws SQLException, BilDoesNotExistException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			BilDataAccess searchda = new BilDataAccessImpl();
			Bil bil =  searchda.listBil(dataaccess, bil_id);
			dataaccess.commit();
			return bil;
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
	public Bil listBilModel(String modelNavn) throws SQLException, BilDoesNotExistException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			BilDataAccess searchda = new BilDataAccessImpl();
			Bil bil =  searchda.listBilModel(dataaccess, modelNavn);
			dataaccess.commit();
			return bil;
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
	public List<Bil> listAlleBil() throws SQLException, BilDoesNotExistException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			BilDataAccess searchda = new BilDataAccessImpl();
			List<Bil> list =  searchda.listAlleBil(dataaccess);
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
}
