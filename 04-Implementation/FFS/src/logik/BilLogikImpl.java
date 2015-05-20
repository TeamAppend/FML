package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.BilDataAccess;
import dataaccess.BilDataAccessImpl;
import dataaccess.DataAccess;
import domain.Bil;
import exceptions.BilDoesNotExist;

public class BilLogikImpl implements BilLogik{

	@Override
	public Bil listBil(String bil_id) throws SQLException, BilDoesNotExist {
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
	public List<Bil> listAlleBil() throws SQLException, BilDoesNotExist {
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
