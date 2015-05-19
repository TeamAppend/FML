package dataaccess;

import java.sql.SQLException;

import domain.Postnummer;

public interface PostnummerDataAccess {
	public Postnummer listPostnummer(DataAccess dataaccess, String postnummer) throws SQLException;
}
