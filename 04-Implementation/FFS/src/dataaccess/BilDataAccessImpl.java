package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Bil;
import domain.BilImpl;

public class BilDataAccessImpl implements BilDataAccess {
	private static final String SELECT_ONE = "SELECT bil_id, pris FROM bil WHERE bil_id = ?";

	/*
	 * Read
	 */
	@Override
	public Bil listBil(DataAccess dataaccess, String bil_id)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setString(1, bil_id);
			resultset = statement.executeQuery();
			Bil bil = new BilImpl();
			while (resultset.next()) {
				bil.setBil_id(resultset.getInt("bil_id"));
				bil.setPris(resultset.getInt("pris"));
			}
			return bil;
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
