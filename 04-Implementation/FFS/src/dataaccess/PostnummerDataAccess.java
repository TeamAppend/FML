package dataaccess;

import java.sql.SQLException;
import java.util.List;

import domain.PostnummerImpl;

public interface PostnummerDataAccess {
	public List<PostnummerImpl> listPostnummer(DataAccess dataaccess, String postnummer) throws SQLException;
}
