package logik;

import java.sql.SQLException;
import java.util.List;

import domain.Lånetilbud;
import exceptions.LånetilbudAllreadyExistsException;
import exceptions.LånetilbudDoesNotExistsException;

public interface LånetilbudLogik {

	public abstract void createLånetilbud(Lånetilbud lånetilbud)
			throws SQLException, LånetilbudAllreadyExistsException;

	public abstract List<Lånetilbud> listLånetilbud(int kunde_id)
			throws SQLException;

	public abstract void deleteLånetilbud(int lånetilbud_id)
			throws SQLException, LånetilbudDoesNotExistsException;

	public abstract Lånetilbud hentLånetilbud(int lånetilbud_id) throws SQLException;

}