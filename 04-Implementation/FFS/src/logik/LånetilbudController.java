package logik;

import java.sql.Timestamp;
import java.util.LinkedList;

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

	public void beregnLånetilbud() {
		java.util.Date date= new java.util.Date();
		Timestamp timestamp = new Timestamp(date.getTime());
	}
}
