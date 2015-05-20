package logik;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import domain.Bil;
import exceptions.BilDoesNotExist;

public class BilController {
	private Bil bil;
	private List<Bil> listBil = new ArrayList<>();
	private LinkedList<FFSObserver> observerListe = new LinkedList<>();

	public void tilmeldObserver(FFSObserver observer) {
		if (observer != null && !observerListe.contains(observer))
			observerListe.add(observer);
	}

	public void notifyObservers(String s) {
		for (FFSObserver obs : observerListe)
			obs.update(this, s);
	}

	public void fillBilComboBox(){
		BilLogik bl = new BilLogikImpl();
		try {
			listBil = bl.listAlleBil();
		} catch (SQLException | BilDoesNotExist e) {
			e.printStackTrace();
		}
		notifyObservers("fillBil");
	}
	
	public void setBilPris(String modelnavn){
		BilLogik bl = new BilLogikImpl();
		try {
			bil = bl.listBilModel(modelnavn);
		} catch (SQLException | BilDoesNotExist e) {
			e.printStackTrace();
		}
		
		notifyObservers("setBilPris");
	}
	
	public Bil getBil(){
		return bil;
	}
	
	public List<Bil> getListBil(){
		return listBil;
	}
}
