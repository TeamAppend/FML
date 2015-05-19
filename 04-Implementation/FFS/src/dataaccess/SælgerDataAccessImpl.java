package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.PostnummerImpl;
import domain.SælgerImpl;

public class SælgerDataAccessImpl implements SælgerDataAccess {
	private static final String SELECT_MANY = "SELECT sælger_id, sælgernavn, rang, beløbsgrænse FROM sælger";
	
	/*
	 * Read
	 */	
	public List<SælgerImpl> listSælger(DataAccess dataaccess, String sælger) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_MANY);
			resultset = statement.executeQuery();
			List<SælgerImpl> list = new ArrayList<>();
			while (resultset.next()) {
				SælgerImpl sr = new SælgerImpl();
				sr.setSælgerNavn(resultset.getString("sælgernavn"));
				sr.setRang(resultset.getString("rang"));
				sr.setBeløbsGrænse( resultset.getInt("beløbsgrænse"));
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
