package logik;

import java.sql.SQLException;
import java.util.List;

import domain.Lånetilbud;
import exceptions.LånetilbudAllreadyExists;
import exceptions.LånetilbudDoesNotExists;

public interface LånetilbudLogik {

	public abstract void createLånetilbud(Lånetilbud lånetilbud)
			throws SQLException, LånetilbudAllreadyExists;

	public abstract List<Lånetilbud> listLånetilbud(int kunde_id)
			throws SQLException;

	public abstract void deleteLånetilbud(int lånetilbud_id)
			throws SQLException, LånetilbudDoesNotExists;

	public abstract Lånetilbud hentLånetilbud(int lånetilbud_id) throws SQLException;

}