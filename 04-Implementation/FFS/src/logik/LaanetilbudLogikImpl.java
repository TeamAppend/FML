package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.LaanetilbudDataAccessImpl;
import domain.Laanetilbud;
import domain.LaanetilbudImpl;
import exceptions.LaanetilbudAllreadyExists;
import exceptions.LaanetilbudDoesNotExists;

public class LaanetilbudLogikImpl {


	
	public void createLaanetilbud(Laanetilbud laanetilbud) throws SQLException, LaanetilbudAllreadyExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			LaanetilbudDataAccessImpl laanetilbudda = new LaanetilbudDataAccessImpl();
			laanetilbudda.createLaanetilbud(dataacces, laanetilbud);
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
	
	public List<LaanetilbudImpl> listLaanetilbud(int kunde_id) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			LaanetilbudDataAccessImpl searchda = new LaanetilbudDataAccessImpl();
			List<LaanetilbudImpl> list =  searchda.listLaanetilbud(dataaccess, kunde_id);
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

	
	public void deleteLaanetilbud(int laanetilbud_id) throws SQLException, LaanetilbudDoesNotExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			LaanetilbudDataAccessImpl laanetilbudda = new LaanetilbudDataAccessImpl();
			laanetilbudda.deleteLaanetilbud(dataacces, laanetilbud_id);
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
