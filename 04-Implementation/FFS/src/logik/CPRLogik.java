package logik;

import java.sql.SQLException;

import domain.CPRnummer;
import exceptions.CPRAllreadyExists;
import exceptions.CPRDoesNotExists;

public interface CPRLogik {

	public abstract void createCPR(CPRnummer CPRnummer) throws SQLException,
			CPRAllreadyExists;

	public abstract CPRnummer listCPR(int CPR_id) throws SQLException;

	public abstract CPRnummer listCPR(String CPRnummer) throws SQLException;

	public abstract int findUniqueCPR(String CPR) throws SQLException;

	public abstract void deleteCPR(int CPR_id) throws SQLException,
			CPRDoesNotExists;

}