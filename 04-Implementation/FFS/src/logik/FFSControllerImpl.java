package logik;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

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

public class FFSControllerImpl {
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
			obs.update();
	}

	public Kunde hentKunde(String search) throws SQLException,
			PostnummerDoesNotExist {
		Kunde kunde = new KundeImpl();
		KundeLogik kl = new KundeLogikImpl();
		List<KundeImpl> list = kl.listKunde(search);
		kunde = list.get(0);

		return kunde;
	}

	public Postnummer hentPostnummer(String search) throws SQLException,
			PostnummerDoesNotExist {
		PostnummerLogik pl = new PostnummerLogikImpl();
		Postnummer postnummer = pl.listPostnummer(search);
		return postnummer;
	}

	public void opretKunde(String telefon, String cpr, String navn,
			String adresse, String postnummer) throws SQLException,
			CPRAllreadyExists, KundeAllreadyExists, PostnummerDoesNotExist {
		if (validerTekstfelter(telefon, cpr, navn, adresse, postnummer)) {
			CPRnummer cprn = new CPRnummerImpl();
			cprn.setCPRnummer(cpr);
			CPRLogik cl = new CPRLogikImpl();
			cl.createCPR(cprn);

			List<CPRnummerImpl> list = cl.listCPR(cpr);
			CPRnummer cprn2 = list.get(0);
			int cpr_id = cprn2.getCPR_id();

			Kunde kunde = new KundeImpl();
			kunde.setCPR_id(cpr_id);
			kunde.setKundenavn(navn);
			kunde.setAdresse(adresse);
			kunde.setPostnummer(postnummer);
			kunde.setTelefon(telefon);

			KundeLogik kl = new KundeLogikImpl();
			kl.createKunde(kunde);

			notifyObservers();

		}
	}

	public boolean telefonNrEksistererIkke(String s) throws SQLException {
		KundeLogik kl = new KundeLogikImpl();
		List<KundeImpl> list = kl.listKunde(s);
		return list.isEmpty();
	}

	public boolean validerTekstfelter(String telefon, String cpr, String navn,
			String adresse, String postnummer) {
		ValiderKundeLogik vkl = new ValiderKundeLogikImpl();
		StringBuilder sb = new StringBuilder();
		boolean b = true;
		if (!vkl.validerTelefon(telefon)) {
			sb.append("- Telefonnummer må kun indeholde tallene 0-9, og skal være 8 tegn \n");
			b = false;
		}
		if (!vkl.validerCPR(cpr)) {
			sb.append("- CPR-nummer må kun indeholde tallene 0-9, og skal være 10 tegn \n");
			b = false;
		}
		if (!vkl.validerNavn(navn)) {
			sb.append("- Kundenavn må kun indeholde a-å, og må ikke være tom \n");
			b = false;
		}
		if (!vkl.validerAdresse(adresse)) {
			sb.append("- Adresse må kun indeholde a-å og 0-9, og må ikke være tom \n");
			b = false;
		}
		if (!vkl.validerPostnr(postnummer)) {
			sb.append("- Postnummer må kun indeholde tallene 0-9, og skal være 4 tegn \n");
			b = false;
		}
		if (!b)
			JOptionPane.showMessageDialog(null, sb.toString(), "Fejl!",
					JOptionPane.ERROR_MESSAGE);
		return b;

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
