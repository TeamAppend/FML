package logik;

import java.sql.SQLException;
import java.util.LinkedList;

import domain.Postnummer;
import exceptions.PostnummerDoesNotExist;

public class PostnummerController {
	private Postnummer postnummer;
	private LinkedList<FFSObserver> observerListe = new LinkedList<>();

	public void tilmeldObserver(FFSObserver observer) {
		if (observer != null && !observerListe.contains(observer))
			observerListe.add(observer);
	}

	public void notifyObservers(String s) {
		for (FFSObserver obs : observerListe)
			obs.update(this, s);
	}

	public void hentPostnummer(String search) throws SQLException,
			PostnummerDoesNotExist {
		PostnummerLogik pl = new PostnummerLogikImpl();
		postnummer = pl.listPostnummer(search);
		
		notifyObservers("hentPostnummer");
	}

	public Postnummer getPostnummer() {
		return postnummer;
	}
}
