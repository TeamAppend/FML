package logik;

import java.sql.SQLException;

import domain.Postnummer;
import exceptions.PostnummerDoesNotExist;

public interface PostnummerLogik {

	public abstract Postnummer listPostnummer(String postnummer)
			throws SQLException, PostnummerDoesNotExist;

}