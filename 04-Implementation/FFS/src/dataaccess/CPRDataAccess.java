package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.CPRnummer;
import exceptions.CPRAllreadyExists;
import exceptions.CPRDoesNotExists;

public class CPRDataAccess {
	private static final String INSERT_ONE = "INSERT INTO CPRnummer (CPRnummer) VALUES(?)";
	private static final String SELECT_ONE = "SELECT CPRnummer FROM CPRnummer WHERE CPR_id = ?";
	private static final String DELETE_ONE = "DELETE FROM CPRnummer WHERE CPR_id = ?";

	
	
	
	
	/*
	 *  Create
	 */
	public void createCPR(DataAccess dataaccess, CPRnummer CPRnummer) throws SQLException, CPRAllreadyExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(INSERT_ONE);
			statement.setString(1, CPRnummer.getCPRnummer());
			statement.executeUpdate();
		} catch (SQLException e) {
			if (e.getSQLState().equalsIgnoreCase("23505")) {
				throw new CPRAllreadyExists();
			} else {
				throw e;
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}
	
	
	/*
	 * Read
	 */	
	public List<CPRnummer> listCPR(DataAccess dataaccess, int CPR_id) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setInt(1, CPR_id);
			resultset = statement.executeQuery();
			List<CPRnummer> list = new ArrayList<>();
			while (resultset.next()) {
				CPRnummer cpr = new CPRnummer();
				cpr.setCPR_id(resultset.getInt("CPR_id"));
				list.add(cpr);
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

	

	/*
	 * Delete
	 */
	public void deleteCPR(DataAccess dataaccess, int CPR_id) throws SQLException, CPRDoesNotExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(DELETE_ONE);
			statement.setInt(1, CPR_id);
			int antal = statement.executeUpdate();
			if (antal != 1) {
				throw new CPRDoesNotExists();
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}
}

