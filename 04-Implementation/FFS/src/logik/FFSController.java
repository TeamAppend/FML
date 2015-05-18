package logik;

import com.ferrari.finances.dk.rki.Rating;

import domain.Bil;
import domain.CPRnummer;
import domain.CallBack;
import domain.Kreditværdighed;
import domain.Kunde;
import domain.Lånetilbud;
import domain.Postnummer;
import domain.RenteSats;
import domain.Sælger;

public interface FFSController {

	// Metode fra linje 15 - 51 eksestere i Lånetilbud interfaces, samt
	// LånetilbudImpl klassen.
	public Lånetilbud getLånetilbud_id();

	public void setLånetilbud_id(int lånetilbud_id);

	// Denne metode eksistrere også i RenteSats klassen. Dette skal fixes.
	public Lånetilbud getRentesats();

	public void setRentesats(double rentesats);

	public Lånetilbud getTilbagebetalingsperiode();

	public void setTilbagebetalingsperiode(int tilbagebetalingsperiode);

	public Lånetilbud getUdbetaling();

	public void setUdbetaling(double udbetaling);

	public Lånetilbud getÅOP();

	public void setÅOP(double ÅOP);

	public Lånetilbud getKunde_id();

	public void setKunde_id(int kunde_id);

	public Lånetilbud getBil_id();

	public void setBil_id(int bil_id);

	public Lånetilbud getSælger_id();

	public void setSælger_id(int sælger_id);

	public Lånetilbud getOprettelsestidspunkt();

	public void setOprettelsestidspunkt(String oprettelsestidspunkt);

	// Metode fra linje 53 - 61 eksestere i Bil interfaces, samt
	// BilImpl klassen.
	//public Bil getBil_id();
	//getBil og setBil eksistere også Lånetilbud. dette skal fixes.
	//public void setBil_id(int bil_id);

	public Bil getPris();

	public void setPris(double pris);
	
	//CPR interface samt Klasse.
	public abstract CPRnummer getCPR_id();

	public abstract void setCPR_id(int cPR_id);

	public abstract CPRnummer getCPRnummer();

	public abstract void setCPRnummer(String cPRnummer);
	
	//Kridtværdighed interface samt klasse.
	public Kreditværdighed getKredigvaerdighed();

	public Kreditværdighed getkvAcceptabel();
	
	public Kreditværdighed getTillaegspoint();

	public void setKreditvaerdighed(String cpr, CallBack callBack);
	
	//Kunde Interface samt klasse.
	//public abstract int getKunde_id();

	//public abstract void setKunde_id(int kunde_id);

	//public abstract String getCPR_id();

	public abstract void setCPR_id(String CPR_id);

	public abstract Kunde getKundenavn();

	public abstract void setKundenavn(String kundenavn);

	public abstract Kunde getAdresse();

	public abstract void setAdresse(String adresse);

	public abstract Kunde getPostnummer();

	public abstract void setPostnummer(String postnummer);

	public abstract Kunde getTelefon();

	public abstract void setTelefon(String telefon);
	
	//Postnummer interface og klasse.
	//public abstract Postnummer getPostnummer();

	//public abstract void setPostnummer(String postnummer);

	public abstract Postnummer getBynavn();

	public abstract void setBynavn(String bynavn);

	//RenteSats interface og klasse.
	public abstract void setRenteSats(CallBack callBack);

	public abstract RenteSats getRenteSats();

	//Sælger interface og klasse.
	//public abstract int getSælger_id();

	//public abstract void setSælger_id(int sælger_id);

	public abstract Sælger getSælgerNavn();

	public abstract void setSælgerNavn(String sælgerNavn);

	public abstract Sælger getRang();

	public abstract void setRang(String rang);

	public abstract Sælger getBeløbsGrænse();

	public abstract void setBeløbsGrænse(double beløbsGrænse);
	
	//Observer metoder
	public void tilmeldObserver(FFSObserver observer);
	
	public void notifyObserver();
}
