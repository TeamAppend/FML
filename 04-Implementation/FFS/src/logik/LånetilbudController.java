package logik;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;

import domain.CPRnummer;
import domain.CallBack;
import domain.Kreditværdighed;
import domain.KreditværdighedImpl;

public class LånetilbudController {
	private LinkedList<FFSObserver> observerListe = new LinkedList<>();

	public void tilmeldObserver(FFSObserver observer) {
		if (observer != null && !observerListe.contains(observer))
			observerListe.add(observer);
	}

	public void notifyObservers(String s) {
		for (FFSObserver obs : observerListe)
			obs.update(this, s);
	}

	public void beregnLånetilbud(int tilbageBetaling, double udbetaling, int bil_id, int sælger_id, int kunde_id, int cpr_id) {
		Kreditværdighed kv = new KreditværdighedImpl();
		CPRLogik cl = new CPRLogikImpl();
		CPRnummer cp = null;
		try {
			cp = cl.listCPR(cpr_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		kv.setKreditvaerdighed(cp.getCPRnummer(), new CallBack(){
			@Override
			public void onRequestComplete() {
				
			};
		});
		kv.getKredigvaerdighed();
		
		//ÅOP ((1 + RENTE I DEC) / antal terminer)^antal terminer - 1
		//rentesats
		java.util.Date date= new java.util.Date();
		Timestamp timestamp = new Timestamp(date.getTime());
	}
}
