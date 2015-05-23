package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.CPRnummer;
import domain.CPRnummerImpl;
import exceptions.CPRAllreadyExistsException;
import exceptions.CPRDoesNotExistsException;

public class CPRDataAccessImpl implements CPRDataAccess {
	private static final String INSERT_ONE = "INSERT INTO CPRnummer (CPRnummer) VALUES(?)";
	private static final String SELECT_ONE = "SELECT CPRnummer, CPR_id FROM CPRnummer WHERE CPR_id = ?";
	private static final String SELECT_NUMMER = "SELECT CPRnummer, CPR_id FROM CPRnummer WHERE CPRnummer = ?";
	private static final String FIND_UNIQUE = "SELECT COUNT(*) FROM CPRnummer WHERE CPRnummer = ?";
	private static final String DELETE_ONE = "DELETE FROM CPRnummer WHERE CPR_id = ?";

	/**
	 * Create nyt CPR nummer ud fra CPRnummer
	 * 
	 * @param dataaccess
	 * @param CPRnummer
	 * @throws SQLException
	 * @throws CPRAllreadyExistsException
	 */
	public void createCPR(DataAccess dataaccess, CPRnummer CPRnummer)
			throws SQLException, CPRAllreadyExistsException {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(INSERT_ONE);
			statement.setString(1, CPRnummer.getCPRnummer());
			statement.executeUpdate();
		} catch (SQLException e) {
			if (e.getSQLState().equalsIgnoreCase("23505")) {
				throw new CPRAllreadyExistsException();
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
	public CPRnummer listCPR(DataAccess dataaccess, int CPR_id)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setInt(1, CPR_id);
			resultset = statement.executeQuery();
			CPRnummer cpr = new CPRnummerImpl();
			while (resultset.next()) {
				cpr.setCPR_id(resultset.getInt("CPR_id"));
				cpr.setCPRnummer(resultset.getString("CPRnummer"));
			}
			return cpr;
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
	}

	public CPRnummer listCPR(DataAccess dataaccess, String CPRnummer)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(
					SELECT_NUMMER);
			statement.setString(1, CPRnummer);
			resultset = statement.executeQuery();
			CPRnummerImpl cpr = new CPRnummerImpl();
			while (resultset.next()) {
				cpr.setCPR_id(resultset.getInt("CPR_id"));
				cpr.setCPRnummer(resultset.getString("CPRnummer"));
			}
			return cpr;
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * T�ller antallet af CPRnumre (returnerer 0 eller 1). Bruges til at finde
	 * ud af om det allerede findes
	 * 
	 * @param dataaccess
	 * @param CPR
	 * @return int
	 * @throws SQLException
	 */
	public int findUnique(DataAccess dataaccess, String CPR)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(FIND_UNIQUE);
			statement.setString(1, CPR);
			resultset = statement.executeQuery();
			int count = 0;
			while (resultset.next()) {
				count = resultset.getInt("c1");
			}
			return count;
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
	public void deleteCPR(DataAccess dataaccess, int CPR_id)
			throws SQLException, CPRDoesNotExistsException {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(DELETE_ONE);
			statement.setInt(1, CPR_id);
			int antal = statement.executeUpdate();
			if (antal != 1) {
				throw new CPRDoesNotExistsException();
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}
}
