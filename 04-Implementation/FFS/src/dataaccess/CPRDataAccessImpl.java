package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.CPRnummer;
import domain.CPRnummerImpl;
import exceptions.CPRAllreadyExists;
import exceptions.CPRDoesNotExists;

public class CPRDataAccessImpl implements CPRDataAccess {
	private static final String INSERT_ONE = "INSERT INTO CPRnummer (CPRnummer) VALUES(?)";
	private static final String SELECT_ONE = "SELECT CPRnummer, CPR_id FROM CPRnummer WHERE CPR_id = ?";
	private static final String SELECT_NUMMER = "SELECT CPRnummer, CPR_id FROM CPRnummer WHERE CPRnummer = ?";
	private static final String FIND_UNIQUE = "SELECT CPRnummer FROM CPRnummer WHERE CPRnummer = ?";
	private static final String DELETE_ONE = "DELETE FROM CPRnummer WHERE CPR_id = ?";

	
	
	
	
	/**
	 * Create nyt CPR nummer ud fra CPRnummer
	 * @param dataaccess
	 * @param CPRnummer
	 * @throws SQLException
	 * @throws CPRAllreadyExists
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
	public List<CPRnummerImpl> listCPR(DataAccess dataaccess, int CPR_id) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setInt(1, CPR_id);
			resultset = statement.executeQuery();
			List<CPRnummerImpl> list = new ArrayList<>();
			while (resultset.next()) {
				CPRnummerImpl cpr = new CPRnummerImpl();
				cpr.setCPR_id(resultset.getInt("CPR_id"));
				cpr.setCPRnummer(resultset.getString("CPRnummer"));
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
	
	
	
	public List<CPRnummerImpl> listCPR(DataAccess dataaccess, String CPRnummer) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_NUMMER);
			statement.setString(1, CPRnummer);
			resultset = statement.executeQuery();
			List<CPRnummerImpl> list = new ArrayList<>();
			while (resultset.next()) {
				CPRnummerImpl cpr = new CPRnummerImpl();
				cpr.setCPR_id(resultset.getInt("CPR_id"));
				cpr.setCPRnummer(resultset.getString("CPRnummer"));
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

	/**
	 * T�ller antallet af CPRnumre (returnerer 0 eller 1). Bruges til at finde ud af om det allerede findes
	 * @param dataaccess
	 * @param CPR
	 * @return int
	 * @throws SQLException
	 */
	public int findUnique(DataAccess dataaccess, String CPR) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(FIND_UNIQUE);
			statement.setString(1, CPR);
			resultset = statement.executeQuery();
			List<CPRnummerImpl> list = new ArrayList<>();
			while (resultset.next()) {
				CPRnummerImpl cpr = new CPRnummerImpl();
				cpr.setCPRnummer(resultset.getString("CPRnummer"));
				list.add(cpr);
			}
			return list.size();
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

