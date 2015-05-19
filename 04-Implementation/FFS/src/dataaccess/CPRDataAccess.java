package dataaccess;

import java.sql.SQLException;

import domain.CPRnummer;
import exceptions.CPRAllreadyExists;
import exceptions.CPRDoesNotExists;

public interface CPRDataAccess {
	
	public void createCPR(DataAccess dataaccess, CPRnummer CPRnummer) throws SQLException, CPRAllreadyExists;
	
	public CPRnummer listCPR(DataAccess dataaccess, int CPR_id) throws SQLException;
	
	//public CPRnummer listCPR(DataAccess dataaccess, String CPRnummer) throws SQLException;
	
	public int findUnique(DataAccess dataaccess, String CPR) throws SQLException;
	
	public void deleteCPR(DataAccess dataaccess, int CPR_id) throws SQLException, CPRDoesNotExists;
}
