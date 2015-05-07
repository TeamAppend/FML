package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Postnummer;

public class PostnummerDataAccess {
	private static final String SELECT_ONE = "SELECT postnummer, bynavn FROM postnummer WHERE postnummer = ?";
	
	/*
	 * Read
	 */	
	public List<Postnummer> listPostnummer(DataAccess dataaccess, String postnummer) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setString(1, postnummer);
			resultset = statement.executeQuery();
			List<Postnummer> list = new ArrayList<>();
			while (resultset.next()) {
				Postnummer pn = new Postnummer();
				pn.setPostnummer(resultset.getString("postnummer"));
				pn.setBynavn(resultset.getString("bynavn"));
				list.add(pn);
			}
			return list;
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
