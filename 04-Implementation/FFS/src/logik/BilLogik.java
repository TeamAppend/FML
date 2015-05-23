package logik;

import java.sql.SQLException;
import java.util.List;

import domain.Bil;
import exceptions.BilDoesNotExistException;

public interface BilLogik {

	public abstract Bil listBil(int bil_id) throws SQLException,
			BilDoesNotExistException;
	
	public abstract Bil listBilModel(String modelNavn) throws SQLException,
	BilDoesNotExistException;

	public abstract List<Bil> listAlleBil() throws SQLException, BilDoesNotExistException;

}