package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Bil;
import domain.BilImpl;

public class BilDataAccessImpl implements BilDataAccess {
	private static final String SELECT_ONE = "SELECT bil_id, pris, modelnavn FROM bil WHERE bil_id = ?";
	private static final String SELECT_MODEL = "SELECT bil_id, pris, modelnavn FROM bil WHERE modelnavn = ?";
	private static final String SELECT_MANY = "SELECT bil_id, pris, modelnavn FROM bil";

	/*
	 * Read
	 */
	@Override
	public Bil listBil(DataAccess dataaccess, int bil_id)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);
			statement.setInt(1, bil_id);
			resultset = statement.executeQuery();
			Bil bil = new BilImpl();
			while (resultset.next()) {
				bil.setBil_id(resultset.getInt("bil_id"));
				bil.setPris(resultset.getInt("pris"));
				bil.setModelnavn(resultset.getString("modelnavn"));
			}
			return bil;
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
	public Bil listBilModel(DataAccess dataaccess, String modelNavn)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_MODEL);
			statement.setString(1, modelNavn);
			resultset = statement.executeQuery();
			Bil bil = new BilImpl();
			while (resultset.next()) {
				bil.setBil_id(resultset.getInt("bil_id"));
				bil.setPris(resultset.getInt("pris"));
				bil.setModelnavn(resultset.getString("modelnavn"));
			}
			return bil;
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
	public List<Bil> listAlleBil(DataAccess dataaccess)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = dataaccess.getConnection().prepareStatement(SELECT_MANY);
			resultset = statement.executeQuery();
			List<Bil> list = new ArrayList<>();
			while (resultset.next()) {
				Bil bil = new BilImpl();
				bil.setBil_id(resultset.getInt("bil_id"));
				bil.setPris(resultset.getInt("pris"));
				bil.setModelnavn(resultset.getString("modelnavn"));
				list.add(bil);
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
