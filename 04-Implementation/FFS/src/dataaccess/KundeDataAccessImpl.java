package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Kunde;
import domain.KundeImpl;
import exceptions.KundeAllreadyExists;
import exceptions.KundeDoesNotExists;

public class KundeDataAccessImpl implements KundeDataAccess{
	private static final String INSERT_ONE = "INSERT INTO KUNDE (CPR_id, kundenavn, adresse, postnummer, telefon) VALUES(?,?,?,?,?)";
	private static final String SELECT_ONE = "SELECT kundenavn, adresse, postnummer, telefon, CPR_id, kunde_id FROM kunde WHERE telefon = ?";
	private static final String UPDATE_ONE = "UPDATE kunde SET kundenavn = ?, adresse = ?, postnummer = ?, telefon = ? WHERE kunde_id = ?";
	private static final String DELETE_ONE = "DELETE FROM kunde WHERE kunde_id = ?";
	private static final String FIND_UNIQUE = "SELECT COUNT(*) FROM kunde WHERE telefon = ?";

	/*
	 * Create
	 */
	@Override
	public void createKunde(DataAccess dataaccess, Kunde kunde)
			throws SQLException, KundeAllreadyExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(INSERT_ONE);
			statement.setInt(1, kunde.getCPR_id());
			statement.setString(2, kunde.getKundenavn());
			statement.setString(3, kunde.getAdresse());
			statement.setString(4, kunde.getPostnummer());
			statement.setString(5, kunde.getTelefon());
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
	@Override
	public Kunde listKunde(DataAccess dataaccess, String telefon)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setString(1, telefon);
			resultset = statement.executeQuery();
			Kunde kunde = new KundeImpl();
			while(resultset.next()){
				kunde.setTelefon(resultset.getString("telefon"));
				kunde.setCPR_id(resultset.getInt("CPR_id"));
				kunde.setKunde_id(resultset.getInt("kunde_id"));
				kunde.setKundenavn(resultset.getString("kundenavn"));
				kunde.setPostnummer(resultset.getString("postnummer"));
				kunde.setAdresse(resultset.getString("adresse"));
			}
			return kunde;
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
	}
	
	@Override
	public int findUnique(DataAccess dataaccess, String telefon)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(FIND_UNIQUE);
			statement.setString(1, telefon);
			resultset = statement.executeQuery();
			int count = 0;
			while(resultset.next()){
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
	 * Update
	 */
	@Override
	public void updateKunde(DataAccess dataaccess, String kundenavn,
			String adresse, String postnummer, String telefon, int kunde_id)
			throws SQLException, KundeDoesNotExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(UPDATE_ONE);
			statement.setString(1, kundenavn);
			statement.setString(2, adresse);
			statement.setString(3, postnummer);
			statement.setString(4, telefon);
			statement.setInt(5, kunde_id);
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
	@Override
	public void deleteKunde(DataAccess dataaccess, int kunde_id)
			throws SQLException, KundeDoesNotExists {
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
