package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Postnummer;
import domain.PostnummerImpl;

public class PostnummerDataAccessImpl implements PostnummerDataAccess {
	private static final String SELECT_ONE = "SELECT postnummer, bynavn FROM postnummer WHERE postnummer = ?";

	/*
	 * Read
	 */
	public Postnummer listPostnummer(DataAccess dataaccess, String postnummer)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setString(1, postnummer);
			resultset = statement.executeQuery();
			PostnummerImpl pn = new PostnummerImpl();
			while (resultset.next()) {
				pn.setPostnummer(resultset.getString("postnummer"));
				pn.setBynavn(resultset.getString("bynavn"));
			}
			return pn;
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
	}
}
