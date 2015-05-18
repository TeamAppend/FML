package logik;

import java.util.LinkedList;

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

public class FFSControllerImpl implements FFSController {
	
	private Bil bil;
	private CPRnummer cprnummer;
	private Kreditværdighed kreditværdighed;
	private Kunde kunde;
	private Lånetilbud lånetilbud;
	private Postnummer postnummer;
	private RenteSats rentesats;
	private Sælger sælger;
	private LinkedList<FFSObserver> observerListe = new LinkedList<>();

	@Override
	public void tilmeldObserver(FFSObserver observer) {
		if (observer != null && !observerListe.contains(observer))
            observerListe.add(observer);		
	}
	
	@Override
	public void notifyObserver() {
		for (FFSObserver obs : observerListe)
            obs.update();
	}
	
	@Override
	public Lånetilbud getLånetilbud_id() {
		return lånetilbud;
	}

	@Override
	public void setLånetilbud_id(int lånetilbud_id) {
	}

	//Metoden her er der 2 af. Det skal vi kigge på.
	@Override
	public Lånetilbud getRentesats() {
		return lånetilbud;
	}

	@Override
	public void setRentesats(double rentesats) {
	}

	@Override
	public Lånetilbud getTilbagebetalingsperiode() {
		return lånetilbud;
	}

	@Override
	public void setTilbagebetalingsperiode(int tilbagebetalingsperiode) {
	}

	@Override
	public Lånetilbud getUdbetaling() {
		return lånetilbud;
	}

	@Override
	public void setUdbetaling(double udbetaling) {
	}

	@Override
	public Lånetilbud getÅOP() {
		return lånetilbud;
	}

	@Override
	public void setÅOP(double ÅOP) {
	}

	@Override
	public Lånetilbud getKunde_id() {
		return lånetilbud;
	}

	@Override
	public void setKunde_id(int kunde_id) {
	}

	@Override
	public Lånetilbud getBil_id() {
		return lånetilbud;
	}

	@Override
	public void setBil_id(int bil_id) {
	}

	@Override
	public Lånetilbud getSælger_id() {
		return lånetilbud;
	}

	@Override
	public void setSælger_id(int sælger_id) {
	}

	@Override
	public Lånetilbud getOprettelsestidspunkt() {
		return lånetilbud;
	}

	@Override
	public void setOprettelsestidspunkt(String oprettelsestidspunkt) {
	}

	@Override
	public Bil getPris() {
		return bil;
	}

	@Override
	public void setPris(double pris) {
	}

	@Override
	public CPRnummer getCPR_id() {
		return cprnummer;
	}

	@Override
	public void setCPR_id(int cPR_id) {
	}

	@Override
	public CPRnummer getCPRnummer() {
		return cprnummer;
	}

	@Override
	public void setCPRnummer(String cPRnummer) {
	}

	@Override
	public Kreditværdighed getKredigvaerdighed() {
		return kreditværdighed;
	}

	@Override
	public Kreditværdighed getkvAcceptabel() {
		return kreditværdighed;
	}

	@Override
	public Kreditværdighed getTillaegspoint() {
		return kreditværdighed;
	}

	@Override
	public void setKreditvaerdighed(String cpr, CallBack callBack) {
	}

	@Override
	public void setCPR_id(String CPR_id) {
	}

	@Override
	public Kunde getKundenavn() {
		return kunde;
	}

	@Override
	public void setKundenavn(String kundenavn) {
	}

	@Override
	public Kunde getAdresse() {
		return kunde;
	}

	@Override
	public void setAdresse(String adresse) {
	}

	@Override
	public Kunde getPostnummer() {
		return kunde;
	}

	@Override
	public void setPostnummer(String postnummer) {
	}

	@Override
	public Kunde getTelefon() {
		return kunde;
	}

	@Override
	public void setTelefon(String telefon) {
	}

	@Override
	public Postnummer getBynavn() {
		return postnummer;
	}

	@Override
	public void setBynavn(String bynavn) {
	}

	@Override
	public void setRenteSats(CallBack callBack) {
	}

	@Override
	public RenteSats getRenteSats() {
		return rentesats;
	}

	@Override
	public Sælger getSælgerNavn() {
		return sælger;
	}

	@Override
	public void setSælgerNavn(String sælgerNavn) {
	}

	@Override
	public Sælger getRang() {
		return sælger;
	}

	@Override
	public void setRang(String rang) {
	}

	@Override
	public Sælger getBeløbsGrænse() {
		return sælger;
	}

	@Override
	public void setBeløbsGrænse(double beløbsGrænse) {
	}
}
