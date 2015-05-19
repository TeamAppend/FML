package dataaccess;

import java.sql.SQLException;
import java.util.List;

import domain.SælgerImpl;;

public interface SælgerDataAccess {
	public List<SælgerImpl> listSælger(DataAccess dataaccess, String sælger) throws SQLException;
}
