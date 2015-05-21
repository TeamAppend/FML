package logik;

import java.sql.SQLException;
import java.util.LinkedList;

import domain.CPRnummer;
import domain.CPRnummerImpl;
import domain.Kunde;
import domain.KundeImpl;
import exceptions.CPRAllreadyExists;
import exceptions.KundeAllreadyExists;
import exceptions.PostnummerDoesNotExist;

public class KundeController {
	private static KundeController inst = null;
	private CPRnummer cprnummer;
	private Kunde kunde;
	private LinkedList<FFSObserver> observerListe = new LinkedList<>();

	public static KundeController instance() {
		if (inst == null)
			inst = new KundeController();
		return inst;
	}
	
	private KundeController(){}

	public void tilmeldObserver(FFSObserver observer) {
		if (observer != null && !observerListe.contains(observer))
			observerListe.add(observer);
	}

	public void notifyObservers(String s) {
		for (FFSObserver obs : observerListe)
			obs.update(this, s);
	}

	public void opretKunde(String telefon, String cpr, String navn,
			String adresse, String postnummer) throws SQLException,
			CPRAllreadyExists, KundeAllreadyExists, PostnummerDoesNotExist {

		cprnummer = new CPRnummerImpl();
		cprnummer.setCPRnummer(cpr);
		CPRLogik cl = new CPRLogikImpl();
		cl.createCPR(cprnummer);

		CPRnummer cprnummer = cl.listCPR(cpr);
		int cpr_id = cprnummer.getCPR_id();

		kunde = new KundeImpl();
		kunde.setCPR_id(cpr_id);
		kunde.setKundenavn(navn);
		kunde.setAdresse(adresse);
		kunde.setPostnummer(postnummer);
		kunde.setTelefon(telefon);

		KundeLogik kl = new KundeLogikImpl();
		kl.createKunde(kunde);

		notifyObservers("opretKunde");
	}

	public void hentKunde(String telefon) throws SQLException,
			PostnummerDoesNotExist {
		kunde = new KundeLogikImpl().hentKunde(telefon);

		notifyObservers("hentKunde");
	}

	public boolean telefonNrEksistererIkke(String telefon) throws SQLException {
		KundeLogik kl = new KundeLogikImpl();
		int count = kl.findUnique(telefon);
		if (count == 0)
			return true;
		else
			return false;
	}

	public CPRnummer getCprnummer() {
		return cprnummer;
	}

	public Kunde getKunde() {
		return kunde;
	}
}
