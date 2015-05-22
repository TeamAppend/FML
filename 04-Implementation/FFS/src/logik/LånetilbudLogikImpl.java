package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.LånetilbudDataAccessImpl;
import domain.Lånetilbud;
import exceptions.LånetilbudAllreadyExists;
import exceptions.LånetilbudDoesNotExists;

public class LånetilbudLogikImpl implements LånetilbudLogik {

	@Override
	public void createLånetilbud(Lånetilbud lånetilbud) throws SQLException, LånetilbudAllreadyExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			LånetilbudDataAccessImpl lånetilbudda = new LånetilbudDataAccessImpl();
			lånetilbudda.createLånetilbud(dataacces, lånetilbud);
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
	public List<Lånetilbud> listLånetilbud(int kunde_id) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			LånetilbudDataAccessImpl searchda = new LånetilbudDataAccessImpl();
			List<Lånetilbud> list =  searchda.listLånetilbud(dataaccess, kunde_id);
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
	public Lånetilbud hentLånetilbud(int lånetilbud_id) throws SQLException {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			LånetilbudDataAccessImpl searchda = new LånetilbudDataAccessImpl();
			Lånetilbud lånetilbud = searchda.hentLånetilbud(dataaccess, lånetilbud_id);
			dataaccess.commit();
			return lånetilbud;
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
	public void deleteLånetilbud(int lånetilbud_id) throws SQLException, LånetilbudDoesNotExists {
		DataAccess dataacces = null;
		try {
			dataacces = new DataAccess();
			LånetilbudDataAccessImpl lånetilbudda = new LånetilbudDataAccessImpl();
			lånetilbudda.deleteLånetilbud(dataacces, lånetilbud_id);
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
