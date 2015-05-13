package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Lånetilbud;
import domain.LånetilbudImpl;
import exceptions.LånetilbudAllreadyExists;
import exceptions.LånetilbudDoesNotExists;

public class LånetilbudDataAccessImpl implements LånetilbudDataAccess {
	private static final String INSERT_ONE = "INSERT INTO lånetilbud (rentesats, tilbagebetalingsperiode, udbetaling, ÅOP, kunde_id, bil_id, saelger_id, oprettelsestidspunkt) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SELECT_ONE = "SELECT rentesats, tilbagebetalingsperiode, udbetaling, ÅOP, kunde_id, bil_id, sælger_id, oprettelsestidspunkt FROM lånetilbud WHERE kunde_id = ?";
	private static final String DELETE_ONE = "DELETE FROM lånetilbud WHERE lånetilbud_id = ?";

	
	/*
	 *  Create
	 */

	@Override
	public void createLånetilbud(DataAccess dataaccess, Lånetilbud lånetilbud) throws SQLException, LånetilbudAllreadyExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(INSERT_ONE);
			statement.setDouble(1, lånetilbud.getRentesats());
			statement.setInt(2, lånetilbud.getTilbagebetalingsperiode());
			statement.setDouble(3, lånetilbud.getUdbetaling());
			statement.setDouble(4, lånetilbud.getÅOP());
			statement.setInt(5, lånetilbud.getKunde_id());
			statement.setInt(6, lånetilbud.getBil_id());
			statement.setInt(7, lånetilbud.getSælger_id());
			statement.setString(8, lånetilbud.getOprettelsestidspunkt());
			statement.executeUpdate();
		} catch (SQLException e) {
			if (e.getSQLState().equalsIgnoreCase("23505")) {
				throw new LånetilbudAllreadyExists();
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
	public List<LånetilbudImpl> listLånetilbud(DataAccess dataaccess, int kunde_id) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setInt(1, kunde_id);
			resultset = statement.executeQuery();
			List<LånetilbudImpl> list = new ArrayList<>();
			while (resultset.next()) {
				LånetilbudImpl lt = new LånetilbudImpl();
				lt.setRentesats(resultset.getDouble("rentesats"));
				lt.setTilbagebetalingsperiode(resultset.getInt("tilbagebetalingsperiode"));
				lt.setUdbetaling(resultset.getDouble("udbetaling"));
				lt.setÅOP(resultset.getDouble("ÅOP"));
				lt.setKunde_id(resultset.getInt("kunde_id"));
				lt.setBil_id(resultset.getInt("bil_id"));
				lt.setSælger_id(resultset.getInt("sælger_id"));
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
	public void deleteLånetilbud(DataAccess dataaccess, int lånetilbud_id) throws SQLException, LånetilbudDoesNotExists {
		PreparedStatement statement = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(DELETE_ONE);
			statement.setInt(1, lånetilbud_id);
			int antal = statement.executeUpdate();
			if (antal != 1) {
				throw new LånetilbudDoesNotExists();
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}
}

