package logik;

import java.sql.SQLException;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.PostnummerDataAccessImpl;
import domain.PostnummerImpl;
import exceptions.PostnummerDoesNotExist;

public class PostnummerLogikImpl implements PostnummerLogik {

	
	@Override
	public PostnummerImpl listPostnummer(String postnummer) throws SQLException, PostnummerDoesNotExist {
		DataAccess dataaccess = null;
		try {
			dataaccess = new DataAccess();
			PostnummerDataAccessImpl searchda = new PostnummerDataAccessImpl();
			List<PostnummerImpl> list =  searchda.listPostnummer(dataaccess, postnummer);
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
