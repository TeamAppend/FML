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
import exceptions.LånetilbudAllreadyExistsException;

public class LånetilbudController {
	private static LånetilbudController inst = null;
	private LinkedList<FFSObserver> observerListe = new LinkedList<>();
	private boolean kreditFundet = false, renteFundet = false;
	private int tillægspoint;
	private double renteSats;
	private int kunde_id;
	private boolean kvAcceptabel;

	public static LånetilbudController instance() {
		if (inst == null)
			inst = new LånetilbudController();
		return inst;
	}
	
	private LånetilbudController(){}
	
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
	
	public boolean getkvAcceptabel(){
		return kvAcceptabel;
	}

	public void beregnLånetilbud(int kunde_id) {
		this.kunde_id = kunde_id;
		kreditFundet = false;
		renteFundet = false;
		KundeController kc = KundeController.instance();
		Kunde kunde = kc.getKunde();
		CPRLogik cl = new CPRLogikImpl();
		CPRnummer cp = null;
		Kreditværdighed kv = new KreditværdighedImpl();
		
		try {
			cp = cl.listCPR(kunde.getCPR_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		kv.setKreditværdighed(cp.getCPRnummer(), new CallBack(){
			@Override
			public void onRequestComplete() {
				kvAcceptabel = kv.getkvAcceptabel();
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
	
	public Lånetilbud hentLånetilbud(int lånetilbud_id){
		LånetilbudLogik ll = new LånetilbudLogikImpl();
		Lånetilbud lånetilbud = new LånetilbudImpl();
		try {
			lånetilbud = ll.hentLånetilbud(lånetilbud_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lånetilbud;
	}
	
	private double udregnÅOP(int tilbageBetalingsPeriode, double udbetaling, double rentesats, double pris){
		double ÅOP = 0;
		double startgæld = pris - udbetaling;
		double restgæld = startgæld;
		double afdrag = restgæld / tilbageBetalingsPeriode;
		double rente = 0;
		rentesats = rentesats/100;
		double sum = 0;
		for(int i = 0; i<tilbageBetalingsPeriode; i++){
			rente = (rentesats/12.000)*restgæld;
			sum += rente;
			restgæld -= afdrag;
		}
		double OP = sum/startgæld;
		ÅOP = (OP/(tilbageBetalingsPeriode/12.000))*100;
		return ÅOP;
	}
	
	public void opretLånetilbud(int tilbageBetalingsPeriode, double udbetaling, int bil_id, int sælger_id, double pris){
		java.util.Date date= new java.util.Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		
		if(udbetaling < pris/2)
			renteSats += 1;	
		if(tilbageBetalingsPeriode > 36)
			renteSats += 1;	
			
		double ÅOP = udregnÅOP(tilbageBetalingsPeriode, udbetaling, renteSats, pris);
		LånetilbudLogik lt = new LånetilbudLogikImpl();
		Lånetilbud lånetilbud = new LånetilbudImpl();
		lånetilbud.setRentesats(renteSats);
		lånetilbud.setTilbagebetalingsperiode(tilbageBetalingsPeriode);
		lånetilbud.setUdbetaling(udbetaling);
		lånetilbud.setÅOP(ÅOP);
		lånetilbud.setKunde_id(kunde_id);
		lånetilbud.setBil_id(bil_id);
		lånetilbud.setSælger_id(sælger_id);
		lånetilbud.setOprettelsestidspunkt(timestamp);
		try {
			lt.createLånetilbud(lånetilbud);
		} catch (SQLException | LånetilbudAllreadyExistsException e) {
			e.printStackTrace();
		}
		notifyObservers("opretLånetilbud");
	}
}
