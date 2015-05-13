package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Laanetilbud;
import domain.LaanetilbudImpl;
import exceptions.LaanetilbudAllreadyExists;
import exceptions.LaanetilbudDoesNotExists;

public class LaanetilbudDataAccessImpl implements LaanetilbudDataAccess {
	private static final String INSERT_ONE = "INSERT INTO laanetilbud (rentesats, tilbagebetalingsperiode, udbetaling, AAOP, kunde_id, bil_id, saelger_id, oprettelsestidspunkt) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SELECT_ONE = "SELECT rentesats, tilbagebetalingsperiode, udbetaling, AAOP, kunde_id, bil_id, saelger_id, oprettelsestidspunkt FROM laanetilbud WHERE kunde_id = ?";
	private static final String DELETE_ONE = "DELETE FROM laanetilbud WHERE laanetilbud_id = ?";

	
	/*
	 *  Create
	 */

	@Override
	public void createLaanetilbud(DataAccess dataaccess, Laanetilbud laanetilbud) throws SQLException, LaanetilbudAllreadyExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(INSERT_ONE);
			statement.setDouble(1, laanetilbud.getRentesats());
			statement.setInt(2, laanetilbud.getTilbagebetalingsperiode());
			statement.setDouble(3, laanetilbud.getUdbetaling());
			statement.setDouble(4, laanetilbud.getAAOP());
			statement.setInt(5, laanetilbud.getKunde_id());
			statement.setInt(6, laanetilbud.getBil_id());
			statement.setInt(7, laanetilbud.getSaelger_id());
			statement.setString(8, laanetilbud.getOprettelsestidspunkt());
			statement.executeUpdate();
		} catch (SQLException e) {
			if (e.getSQLState().equalsIgnoreCase("23505")) {
				throw new LaanetilbudAllreadyExists();
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
	public List<LaanetilbudImpl> listLaanetilbud(DataAccess dataaccess, int kunde_id) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setInt(1, kunde_id);
			resultset = statement.executeQuery();
			List<LaanetilbudImpl> list = new ArrayList<>();
			while (resultset.next()) {
				LaanetilbudImpl lt = new LaanetilbudImpl();
				lt.setRentesats(resultset.getDouble("rentesats"));
				lt.setTilbagebetalingsperiode(resultset.getInt("tilbagebetalingsperiode"));
				lt.setUdbetaling(resultset.getDouble("udbetaling"));
				lt.setAAOP(resultset.getDouble("AAOP"));
				lt.setKunde_id(resultset.getInt("kunde_id"));
				lt.setBil_id(resultset.getInt("bil_id"));
				lt.setSaelger_id(resultset.getInt("saelger_id"));
				lt.setOprettelsestidspunkt(resultset.getString("oprettelsestidspunkt"));
				list.add(lt);
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
	@Override
	public void deleteLaanetilbud(DataAccess dataaccess, int laanetilbud_id) throws SQLException, LaanetilbudDoesNotExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(DELETE_ONE);
			statement.setInt(1, laanetilbud_id);
			int antal = statement.executeUpdate();
			if (antal != 1) {
				throw new LaanetilbudDoesNotExists();
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}
}

