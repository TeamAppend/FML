package logik;

import java.util.LinkedList;

import domain.Bil;
import domain.CPRnummer;
import domain.Kreditværdighed;
import domain.Kunde;
import domain.Lånetilbud;
import domain.Postnummer;
import domain.RenteSats;
import domain.Sælger;

public class FFSControllerImpl{
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
	
	public void notifyObserver() {
		for (FFSObserver obs : observerListe)
            obs.update();
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
