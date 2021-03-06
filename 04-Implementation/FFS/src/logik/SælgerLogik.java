package logik;

import java.sql.SQLException;
import java.util.List;

import domain.Sælger;
import exceptions.SælgerDoesNotExistException;

public interface SælgerLogik {

	public abstract Sælger listSælger(int sælger_id) throws SQLException;

	public abstract Sælger listSælgerBeløb(String sælgernavn) throws SQLException;

	public abstract List<Sælger> listAlleSælger() throws SQLException,
			SælgerDoesNotExistException;

}