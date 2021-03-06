package logik;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import domain.Sælger;
import exceptions.SælgerDoesNotExistException;

public class SælgerController {
	private Sælger sælger;
	private static SælgerController inst = null;
	private List<Sælger> listSælger = new ArrayList<>();
	private LinkedList<FFSObserver> observerListe = new LinkedList<>();
	
	public static SælgerController instance() {
		if (inst == null)
			inst = new SælgerController();
		return inst;
	}
	
	private SælgerController(){}

	public void tilmeldObserver(FFSObserver observer) {
		if (observer != null && !observerListe.contains(observer))
			observerListe.add(observer);
	}

	public void notifyObservers(String s) {
		for (FFSObserver obs : observerListe)
			obs.update(this, s);
	}

	public void fillSælgerComboBox(){
		SælgerLogik sl = new SælgerLogikImpl();
		try {
			listSælger = sl.listAlleSælger();
		} catch (SQLException | SælgerDoesNotExistException e) {
			e.printStackTrace();
		}
		notifyObservers("fillSælger");
	}
	
	public void setMaksLånebeløb(String sælgernavn){
		SælgerLogik sl = new SælgerLogikImpl();
		try {
			sælger = sl.listSælgerBeløb(sælgernavn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		notifyObservers("setMaksLånebeløb");
	}
	
	public void hentSælger(int sælger_id){
		SælgerLogik sl = new SælgerLogikImpl();
		try {
			sælger = sl.listSælger(sælger_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		notifyObservers("hentSælger");
	}
	
	public Sælger getSælger(){
		return sælger;
	}
	
	public List<Sælger> getListSælger(){
		return listSælger;
	}

	public void setSælger(String sælgernavn) {
		SælgerLogik sl = new SælgerLogikImpl();
		try {
			sælger = sl.listSælgerBeløb(sælgernavn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
