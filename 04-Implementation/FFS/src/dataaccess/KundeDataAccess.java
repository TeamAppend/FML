package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Kunde;
import exceptions.KundeAllreadyExists;
import exceptions.KundeDoesNotExists;

public class KundeDataAccess {
	private static final String INSERT_ONE = "INSERT INTO KUNDE (CPRnummer, navn, adresse, postnummer, telefon, kommentar) VALUES(?,?,?,?,?,?)";
	private static final String SELECT_ONE = "SELECT CPRnummer, navn, adresse, postnummer, telefon, kommentar FROM kunde WHERE CPRnummer = ?";
	private static final String UPDATE_ONE = "UPDATE kunde SET CPRnummer = ?, navn = ?, adresse = ?, postnummer = ?, telefon = ?, kommentar = ? WHERE kunde_id = ?";
	private static final String DELETE_ONE = "DELETE FROM kunde WHERE kunde_id = ?";

	//Hvis man skal kunne søge på CPRnummer, navn og/eller telefon
	private static final String SELECT_MANY = "SELECT CPRnummer, navn, adresse, postnummer, telefon, kommentar FROM kunde WHERE CPRnummer = ? OR navn = ? OR telefon = ?";
	
	
	
	
	/*
	 *  Create
	 */
	public void createKunde(DataAccess dataaccess, Kunde kunde) throws SQLException, KundeAllreadyExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(INSERT_ONE);
			statement.setString(1, kunde.getCPRnummer());
			statement.setString(2, kunde.getNavn());
			statement.setString(3, kunde.getAdresse());
			statement.setString(4, kunde.getPostnummer());
			statement.setString(5, kunde.getTelefon());
			statement.setString(6, kunde.getKommentar());
			statement.executeUpdate();
		} catch (SQLException e) {
			if (e.getSQLState().equalsIgnoreCase("23505")) {
				throw new KundeAllreadyExists();
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
	public List<Kunde> listKunde(DataAccess dataaccess, String CPRnummer) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setString(1, CPRnummer);
			resultset = statement.executeQuery();
			List<Kunde> list = new ArrayList<>();
			while (resultset.next()) {
				Kunde ku = new Kunde();
				ku.setCPRnummer(resultset.getString("CPRnummer"));
				list.add(ku);
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
	
	
	// Hvis man skal kunne søge på CPRnummer, navn og/eller telefon
	public List<Kunde> listKunder(DataAccess dataaccess, String CPRnummer, String navn, String telefon) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_MANY);
			statement.setString(1, CPRnummer);
			statement.setString(2, navn);
			statement.setString(3, telefon);
			resultset = statement.executeQuery();
			List<Kunde> list = new ArrayList<>();
			while (resultset.next()) {
				Kunde ku = new Kunde();
				ku.setCPRnummer(resultset.getString("CPRnummer"));
				ku.setNavn(resultset.getString("navn"));
				ku.setTelefon(resultset.getString("telefon"));
				list.add(ku);
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
	 * Update
	 */
	public void updateRet(DataAccess dataaccess, String CPRnummer, String navn, String adresse, String postnummer, String telefon, String kommentar, int kunde_id) throws SQLException, KundeDoesNotExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(UPDATE_ONE);
			statement.setString(1, CPRnummer);
			statement.setString(2, navn);
			statement.setString(3, adresse);
			statement.setString(4, postnummer);
			statement.setString(5, telefon);
			statement.setString(6, kommentar);
			statement.setInt(7, kunde_id);
			int antal = statement.executeUpdate();
			if (antal != 1) {
				throw new KundeDoesNotExists();
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}
	

	/*
	 * Delete
	 */
	public void deleteRet(DataAccess dataaccess, int kunde_id) throws SQLException, KundeDoesNotExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(DELETE_ONE);
			statement.setInt(1, kunde_id);
			int antal = statement.executeUpdate();
			if (antal != 1) {
				throw new KundeDoesNotExists();
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}
}

