package logik;

import java.sql.SQLException;

import domain.Bil;
import exceptions.BilDoesNotExist;

public interface BilLogik {

	public abstract Bil listBil(String bil) throws SQLException,
			BilDoesNotExist;

}