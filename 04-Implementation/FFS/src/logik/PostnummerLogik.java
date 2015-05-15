package logik;

import java.sql.SQLException;

import domain.Postnummer;
import domain.PostnummerImpl;
import exceptions.PostnummerDoesNotExist;

public interface PostnummerLogik {

	public abstract PostnummerImpl listPostnummer(String postnummer)
			throws SQLException, PostnummerDoesNotExist;

}