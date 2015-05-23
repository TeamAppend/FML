package logik;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import domain.Bil;
import exceptions.BilDoesNotExistException;

public class BilController {
	private static BilController inst = null;
	private Bil bil;
	private List<Bil> listBil = new ArrayList<>();
	private LinkedList<FFSObserver> observerListe = new LinkedList<>();

	public static BilController instance() {
		if (inst == null)
			inst = new BilController();
		return inst;
	}
	
	private BilController(){}
	
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
		} catch (SQLException | BilDoesNotExistException e) {
			e.printStackTrace();
		}
		notifyObservers("fillBil");
	}
	
	public void setBilPris(String modelnavn){
		BilLogik bl = new BilLogikImpl();
		try {
			bil = bl.listBilModel(modelnavn);
		} catch (SQLException | BilDoesNotExistException e) {
			e.printStackTrace();
		}
		
		notifyObservers("setBilPris");
	}
	
	public void hentBil(int bil_id){
		BilLogik bl = new BilLogikImpl();
		try {
			bil = bl.listBil(bil_id);
		} catch (SQLException | BilDoesNotExistException e) {
			e.printStackTrace();
		}
		
		notifyObservers("hentBil");
	}
	
	public Bil getBil(){
		return bil;
	}
	
	public List<Bil> getListBil(){
		return listBil;
	}

	public void setBil(String modelNavn) {
		BilLogik bl = new BilLogikImpl();
		try {
			bil = bl.listBilModel(modelNavn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (BilDoesNotExistException e) {
			e.printStackTrace();
		}
	}
}
