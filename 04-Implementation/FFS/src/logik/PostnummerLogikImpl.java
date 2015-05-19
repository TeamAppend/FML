package logik;

import java.sql.SQLException;

import dataaccess.DataAccess;
import dataaccess.PostnummerDataAccessImpl;
import domain.Postnummer;
import exceptions.PostnummerDoesNotExist;

public class PostnummerLogikImpl implements PostnummerLogik {

	
	@Override
	public Postnummer listPostnummer(String postnummer) throws SQLException, PostnummerDoesNotExist {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			PostnummerDataAccessImpl searchda = new PostnummerDataAccessImpl();
			Postnummer pn =  searchda.listPostnummer(dataaccess, postnummer);
			dataaccess.commit();
			return pn;
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
