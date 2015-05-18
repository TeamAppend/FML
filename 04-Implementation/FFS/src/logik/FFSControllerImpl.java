package logik;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import domain.Bil;
import domain.CPRnummer;
import domain.Kreditværdighed;
import domain.Kunde;
import domain.Lånetilbud;
import domain.Postnummer;
import domain.RenteSats;
import domain.Sælger;

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
	
	public boolean validerTekstfelter(String telefon, String cpr, String navn, String adresse, String postnummer) {
		ValiderKundeLogik vkl = new ValiderKundeLogikImpl();
		StringBuilder sb = new StringBuilder();
		boolean b = true;
		if (!vkl.validerTelefon(telefon)) {
			sb.append("- Telefonnummer må kun indeholde tallene 0-9, og skal være 8 tegn \n");
			//telefon.setBorder(redBorder);
			b = false;
		}
		if (!vkl.validerCPR(cpr)) {
			sb.append("- CPR-nummer må kun indeholde tallene 0-9, og skal være 10 tegn \n");
			//cpr.setBorder(redBorder);
			b = false;
		}
		if (!vkl.validerNavn(navn)) {
			sb.append("- Kundenavn må kun indeholde a-å, og må ikke være tom \n");
			//navn.setBorder(redBorder);
			b = false;
		}
		if (!vkl.validerAdresse(adresse)) {
			sb.append("- Adresse må kun indeholde a-å og 0-9, og må ikke være tom \n");
			//adresse.setBorder(redBorder);
			b = false;
		}
		if (!vkl.validerPostnr(postnummer)) {
			sb.append("- Postnummer må kun indeholde tallene 0-9, og skal være 4 tegn \n");
			//postnr.setBorder(redBorder);
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
