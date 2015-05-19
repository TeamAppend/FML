package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Sælger;
import domain.SælgerImpl;

public class SælgerDataAccessImpl implements SælgerDataAccess {
	private static final String SELECT_MANY = "SELECT sælger_id, sælgernavn, rang, beløbsgrænse FROM sælger";
	private static final String SELECT_ONE = "SELECT sælger_id, sælgernavn, rang, beløbsgrænse FROM sælger WHERE sælger_id = ?";

	/*
	 * Read
	 */

	@Override
	public Sælger listSælger(DataAccess dataaccess, String sælger_id)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setString(1, sælger_id);
			resultset = statement.executeQuery();
			Sælger sælger = new SælgerImpl();
			SælgerImpl sr = new SælgerImpl();
			sr.setSælgerNavn(resultset.getString("sælgernavn"));
			sr.setRang(resultset.getString("rang"));
			sr.setBeløbsGrænse(resultset.getInt("beløbsgrænse"));
			sr.setSælger_id(resultset.getInt("sælger_id"));
			return sælger;
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
	public List<Sælger> listAlleSælger(DataAccess dataaccess) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_MANY);
			resultset = statement.executeQuery();
			List<Sælger> list = new ArrayList<>();
			while (resultset.next()) {
				SælgerImpl sr = new SælgerImpl();
				sr.setSælgerNavn(resultset.getString("sælgernavn"));
				sr.setRang(resultset.getString("rang"));
				sr.setBeløbsGrænse(resultset.getInt("beløbsgrænse"));
				sr.setSælger_id(resultset.getInt("sælger_id"));
				list.add(sr);
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
