package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.SælgerDataAccess;
import dataaccess.SælgerDataAccessImpl;
import domain.Sælger;
import exceptions.SælgerDoesNotExistException;

public class SælgerLogikImpl implements SælgerLogik  {

	
	@Override
	public Sælger listSælger(int sælger_id) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			SælgerDataAccess searchda = new SælgerDataAccessImpl();
			Sælger sælger =  searchda.listSælger(dataaccess, sælger_id);
			dataaccess.commit();
			return sælger;
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
	public Sælger listSælgerBeløb(String sælgernavn) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			SælgerDataAccess searchda = new SælgerDataAccessImpl();
			Sælger sælger =  searchda.listSælgerBeløb(dataaccess, sælgernavn);
			dataaccess.commit();
			return sælger;
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
	public List<Sælger> listAlleSælger() throws SQLException, SælgerDoesNotExistException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			SælgerDataAccess searchda = new SælgerDataAccessImpl();
			List<Sælger> list =  searchda.listAlleSælger(dataaccess);
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
