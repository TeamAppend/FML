package logik;

import java.sql.SQLException;
import java.util.List;

import domain.Bil;
import exceptions.BilDoesNotExist;

public interface BilLogik {

	public abstract Bil listBil(int bil_id) throws SQLException,
			BilDoesNotExist;
	
	public abstract Bil listBilModel(String modelNavn) throws SQLException,
	BilDoesNotExist;

	public abstract List<Bil> listAlleBil() throws SQLException, BilDoesNotExist;

}