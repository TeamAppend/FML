package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.BilDataAccessImpl;
import domain.Bil;
import domain.BilImpl;
import exceptions.BilDoesNotExist;

public class BilLogikImpl implements BilLogik{

	@Override
	public Bil listBil(String bil) throws SQLException, BilDoesNotExist {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			BilDataAccessImpl searchda = new BilDataAccessImpl();
			List<BilImpl> list =  searchda.listBil(dataaccess, bil);
			dataaccess.commit();
			if(list.isEmpty())
				return null;
			else
				return list.get(0);
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
