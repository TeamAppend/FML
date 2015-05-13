package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.BilImpl;

public class BilDataAccessImpl implements BilDataAccess {
	private static final String SELECT_ONE = "SELECT bil_id, pris FROM bil WHERE bil_id = ?";
	
	/*
	 * Read
	 */	
	@Override
	public List<BilImpl> listBil(DataAccess dataaccess, String bil) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setString(1, bil);
			resultset = statement.executeQuery();
			List<BilImpl> list = new ArrayList<>();
			while (resultset.next()) {
				BilImpl bl = new BilImpl();
				bl.setBil_id(resultset.getInt("bil_id"));
				bl.setPris(resultset.getInt("pris"));
				list.add(bl);
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
