package logik;

import javax.swing.table.TableModel;

import domain.Bil;
import domain.CPRnummer;
import domain.Kunde;
import domain.Lånetilbud;
import domain.Sælger;

public class CSV_eksportImpl implements CSV_eksport, FFSObserver {
	private SælgerController sController = SælgerController.instance();
	private BilController bController = BilController.instance();
	private LånetilbudController lController = LånetilbudController.instance();
	private KundeController kController = KundeController.instance();
	private PostnummerController pController = PostnummerController.instance();
	private StringBuffer sb = new StringBuffer();
	private TableModel t;
	private int cCount = 0;
	private int rCount = 0;
	private Kunde kunde;
	private Sælger sælger;
	private Bil bil;
	private CPRnummer cpr;
	private Lånetilbud lånetilbud;
	private boolean bilHentet = false, sælgerHentet = false, kundeHentet = false;
	
	public CSV_eksportImpl(TableModel t){
		this.t = t;
		cCount = t.getColumnCount();
		rCount = t.getRowCount();
	}
	
	public CSV_eksportImpl(){
		sController.tilmeldObserver(this);
		bController.tilmeldObserver(this);
		lController.tilmeldObserver(this);
		kController.tilmeldObserver(this);
		pController.tilmeldObserver(this);
	}
	
	public void createTable(int lånetilbud_id){
		lånetilbud = lController.hentLånetilbud(lånetilbud_id);
		int kunde_id = lånetilbud.getKunde_id();
		int bil_id = lånetilbud.getBil_id();
		int sælger_id = lånetilbud.getSælger_id();
		
		kController.hentKunde(kunde_id);
		bController.hentBil(bil_id);
		sController.hentSælger(sælger_id);
	}
	
	private void test(){
		
	}
	
	public String eksport(){
		tableToStringBuffer();
		String s = sbToString();
		return s;
	}
	
	public void tableToStringBuffer(){
		for(int i = 0; i < rCount; i++){
			for(int x = 0; x < cCount - 1; x++){
				append((String) t.getValueAt(x, i));
			}
			lastValue((String) t.getValueAt(cCount, i));
		}
	}
	
	public void append(String s){
		sb.append(s + ",");
	}

	public void lastValue(String s){
		sb.append(s + " \n");
	}
	
	public String sbToString(){
		return sb.toString();
	}

	@Override
	public void update(Object source, String s) {
		if(source instanceof KundeController){
			if(s.equals("hentKunde")){
				kunde = kController.getKunde();
				cpr = kController.getCprnummer();
				kundeHentet = true;
				if(kundeHentet && bilHentet && sælgerHentet){
					test();
				}
			}
		}else if(source instanceof BilController){
			if(s.equals("hentBil")){
				bil = bController.getBil();
				bilHentet = true;
				if(kundeHentet && bilHentet && sælgerHentet){
					test();
				}
			}
		}else if(source instanceof SælgerController){
			if(s.equals("hentSælger")){
				sælger = sController.getSælger();
				sælgerHentet = true;
				if(kundeHentet && bilHentet && sælgerHentet){
					test();
				}
			}
		}
	}
}
