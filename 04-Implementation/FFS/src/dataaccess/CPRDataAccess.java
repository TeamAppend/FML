package dataaccess;

import java.sql.SQLException;
import java.util.List;

import domain.CPRnummer;
import domain.CPRnummerImpl;
import exceptions.CPRAllreadyExists;
import exceptions.CPRDoesNotExists;

public interface CPRDataAccess {
	
	public void createCPR(DataAccess dataaccess, CPRnummer CPRnummer) throws SQLException, CPRAllreadyExists;
	
	public List<CPRnummerImpl> listCPR(DataAccess dataaccess, int CPR_id) throws SQLException;
	
	public List<CPRnummerImpl> listCPR(DataAccess dataaccess, String CPRnummer) throws SQLException;
	
	public int findUnique(DataAccess dataaccess, String CPR) throws SQLException;
	
	public void deleteCPR(DataAccess dataaccess, int CPR_id) throws SQLException, CPRDoesNotExists;
}
