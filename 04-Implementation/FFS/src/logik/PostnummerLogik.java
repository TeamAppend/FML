package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.PostnummerDataAccess;
import domain.Postnummer;
import exceptions.PostnummerDoesNotExist;

public class PostnummerLogik {

	public Postnummer listPostnummer(String postnummer) throws SQLException, PostnummerDoesNotExist {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			PostnummerDataAccess searchda = new PostnummerDataAccess();
			List<Postnummer> list =  searchda.listPostnummer(dataaccess, postnummer);
			dataaccess.commit();
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
