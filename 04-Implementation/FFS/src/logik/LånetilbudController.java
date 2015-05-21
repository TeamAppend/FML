package logik;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;

import domain.CPRnummer;
import domain.CallBack;
import domain.Kreditværdighed;
import domain.KreditværdighedImpl;
import domain.Kunde;
import domain.Lånetilbud;
import domain.LånetilbudImpl;
import domain.RenteSats;
import domain.RenteSatsImpl;
import exceptions.LånetilbudAllreadyExists;

public class LånetilbudController {
	private LinkedList<FFSObserver> observerListe = new LinkedList<>();
	private boolean kreditFundet = false, renteFundet = false;
	private int tillægspoint;
	private double renteSats;
	private int kunde_id;

	public void tilmeldObserver(FFSObserver observer) {
		if (observer != null && !observerListe.contains(observer))
			observerListe.add(observer);
	}

	public void notifyObservers(String s) {
		for (FFSObserver obs : observerListe)
			obs.update(this, s);
	}
	
	public boolean beggeFundet(){
		return kreditFundet && renteFundet;
		
	}
	
	public int getTillægsPoint(){
		return tillægspoint;
	}
	
	public double getRenteSats(){
		return renteSats;
	}

	public void beregnLånetilbud(int kunde_id) {
		this.kunde_id = kunde_id;
		kreditFundet = false;
		renteFundet = false;
		Kreditværdighed kv = new KreditværdighedImpl();
		KundeController kc = KundeController.instance();
		Kunde kunde = kc.getKunde();
		CPRLogik cl = new CPRLogikImpl();
		CPRnummer cp = null;
		try {
			cp = cl.listCPR(kunde.getCPR_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		kv.setKreditværdighed(cp.getCPRnummer(), new CallBack(){
			@Override
			public void onRequestComplete() {
				kreditFundet = true;
				tillægspoint = kv.getTillægspoint();
				notifyObservers("Kreditværdighed");
			};
		});
		
		RenteSats rs = new RenteSatsImpl();
		rs.setRenteSats(new CallBack(){
			@Override
			public void onRequestComplete() {
				renteFundet = true;
				renteSats = rs.getRenteSats();
				notifyObservers("RenteSats");
			};
		});
	}
	
	public void opretLånetilbud(int tilbageBetaling, double udbetaling, int bil_id, int sælger_id){
		//ÅOP ((1 + RENTE I DEC) / antal terminer)^antal terminer - 1
		double ÅOP = Math.pow(((1+renteSats) / tilbageBetaling),(tilbageBetaling))-1;
		java.util.Date date= new java.util.Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		
		LånetilbudLogik lt = new LånetilbudLogikImpl();
		Lånetilbud lånetilbud = new LånetilbudImpl();
		lånetilbud.setRentesats(renteSats);
		lånetilbud.setTilbagebetalingsperiode(tilbageBetaling);
		lånetilbud.setUdbetaling(udbetaling);
		lånetilbud.setÅOP(ÅOP);
		lånetilbud.setKunde_id(kunde_id);
		lånetilbud.setBil_id(bil_id);
		lånetilbud.setSælger_id(sælger_id);
		lånetilbud.setOprettelsestidspunkt(timestamp);
		try {
			lt.createLånetilbud(lånetilbud);
		} catch (SQLException | LånetilbudAllreadyExists e) {
			e.printStackTrace();
		}
		notifyObservers("opretLånetilbud");
	}
}
