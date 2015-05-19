package logik;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import domain.Bil;
import domain.CPRnummer;
import domain.CPRnummerImpl;
import domain.Kreditværdighed;
import domain.Kunde;
import domain.KundeImpl;
import domain.Lånetilbud;
import domain.Postnummer;
import domain.RenteSats;
import domain.Sælger;
import exceptions.CPRAllreadyExists;
import exceptions.KundeAllreadyExists;
import exceptions.PostnummerDoesNotExist;

public class FFSControllerOpretKundeImpl {
	private Bil bil;
	private CPRnummer cprnummer;
	private Kreditværdighed kreditværdighed;
	private Kunde kunde;
	private Lånetilbud lånetilbud;
	private Postnummer postnummer;
	private RenteSats rentesats;
	private Sælger sælger;
	private LinkedList<FFSObserver> observerListe = new LinkedList<>();

	public void tilmeldObserver(FFSObserver observer) {
		if (observer != null && !observerListe.contains(observer))
			observerListe.add(observer);
	}

	public void notifyObservers() {
		for (FFSObserver obs : observerListe)
			obs.update(this);
	}

	public Kunde hentKunde(String search) throws SQLException,
			PostnummerDoesNotExist {
		kunde = new KundeImpl();
		KundeLogik kl = new KundeLogikImpl();
		List<KundeImpl> list = kl.listKunde(search);
		kunde = list.get(0);
		return kunde;
	}

	public Postnummer hentPostnummer(String search) throws SQLException,
			PostnummerDoesNotExist {
		PostnummerLogik pl = new PostnummerLogikImpl();
		postnummer = pl.listPostnummer(search);
		return postnummer;
	}

	public void opretKunde(String telefon, String cpr, String navn,
			String adresse, String postnummer) throws SQLException,
			CPRAllreadyExists, KundeAllreadyExists, PostnummerDoesNotExist {

		cprnummer = new CPRnummerImpl();
		cprnummer.setCPRnummer(cpr);
		CPRLogik cl = new CPRLogikImpl();
		cl.createCPR(cprnummer);

		List<CPRnummerImpl> list = cl.listCPR(cpr);
		cprnummer = list.get(0);
		int cpr_id = cprnummer.getCPR_id();

		kunde = new KundeImpl();
		kunde.setCPR_id(cpr_id);
		kunde.setKundenavn(navn);
		kunde.setAdresse(adresse);
		kunde.setPostnummer(postnummer);
		kunde.setTelefon(telefon);

		KundeLogik kl = new KundeLogikImpl();
		kl.createKunde(kunde);

		notifyObservers();
	}

	public boolean telefonNrEksistererIkke(String s) throws SQLException {
		KundeLogik kl = new KundeLogikImpl();
		List<KundeImpl> list = kl.listKunde(s);
		return list.isEmpty();
	}

	public Bil getBil() {
		return bil;
	}

	public CPRnummer getCprnummer() {
		return cprnummer;
	}

	public Kreditværdighed getKreditværdighed() {
		return kreditværdighed;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public Lånetilbud getLånetilbud() {
		return lånetilbud;
	}

	public Postnummer getPostnummer() {
		return postnummer;
	}

	public RenteSats getRentesats() {
		return rentesats;
	}

	public Sælger getSælger() {
		return sælger;
	}
}
