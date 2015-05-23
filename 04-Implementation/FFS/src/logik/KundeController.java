package logik;

import java.sql.SQLException;
import java.util.LinkedList;

import domain.CPRnummer;
import domain.CPRnummerImpl;
import domain.Kunde;
import domain.KundeImpl;
import exceptions.CPRAllreadyExistsException;
import exceptions.KundeAllreadyExistsException;
import exceptions.PostnummerDoesNotExistException;

public class KundeController {
	private static KundeController inst = null;
	private CPRnummer cprnummer;
	private Kunde kunde;
	private boolean kundeFundet = false;
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
			CPRAllreadyExistsException, KundeAllreadyExistsException, PostnummerDoesNotExistException {

		kundeFundet = false;
		
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
	
	public void hentKunde(int kunde_id){
		kunde = new KundeImpl();
		KundeLogik kl = new KundeLogikImpl();
		try {
			kunde = kl.hentKunde(kunde_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int cpr_id = kunde.getCPR_id();
		cprnummer = new CPRnummerImpl();
		cprnummer.setCPR_id(cpr_id);
		CPRLogik cl = new CPRLogikImpl();
		try {
			cprnummer.setCPRnummer(cl.listCPR(cpr_id).getCPRnummer());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		notifyObservers("hentKunde");
	}

	public void hentKunde(String telefon) throws SQLException,
			PostnummerDoesNotExistException {
		kunde = new KundeImpl();
		KundeLogik kl = new KundeLogikImpl();
		kunde = kl.listKunde(telefon);
		
		kundeFundet = true;

		notifyObservers("hentKunde");
	}

	public boolean telefonNrEksistererIkke(String telefon) throws SQLException {
		KundeLogik kl = new KundeLogikImpl();
		int count = kl.findUnique(telefon);
		if (count == 0){
			kundeFundet = false;
			return true;
		}else
			return false;
	}
	
	public boolean getKundeFundet(){
		return kundeFundet;
	}
	
	public void setKundeFundet(boolean b){
		kundeFundet = b;
		
		notifyObservers("setKundeFundet");
	}

	public CPRnummer getCprnummer() {
		return cprnummer;
	}

	public Kunde getKunde() {
		return kunde;
	}
}
