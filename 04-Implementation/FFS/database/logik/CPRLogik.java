package logik;

import java.sql.SQLException;
import java.util.List;

import domain.CPRnummer;
import domain.CPRnummerImpl;
import exceptions.CPRAllreadyExists;
import exceptions.CPRDoesNotExists;

public interface CPRLogik {

	public abstract void createCPR(CPRnummer CPRnummer) throws SQLException,
			CPRAllreadyExists;

	public abstract List<CPRnummerImpl> listCPR(int CPR_id) throws SQLException;

	public abstract int findUniqueCPR(String CPR) throws SQLException;

	public abstract void deleteCPR(int CPR_id) throws SQLException,
			CPRDoesNotExists;

}