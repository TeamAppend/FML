package logik;

import java.sql.SQLException;
import java.util.List;

import domain.Sælger;
import exceptions.SælgerDoesNotExist;

public interface SælgerLogik {

	public abstract Sælger listSælger(String sælger_id) throws SQLException;

	public abstract List<Sælger> listAlleSælger() throws SQLException,
			SælgerDoesNotExist;

}