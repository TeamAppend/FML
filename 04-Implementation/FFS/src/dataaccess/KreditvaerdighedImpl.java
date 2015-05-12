package dataaccess;

import com.ferrari.finances.dk.rki.*;

public class KreditvaerdighedImpl implements Kreditvaerdighed {

	private Rating kreditvaerdighed; // aendret til en Rating fra string
	private CreditRator cR = CreditRator.i();
	private int tillaegspoint = 0;
	private boolean kvAcceptabel = false;

	@Override
	public Rating getKredigvaerdighed() {
		return kreditvaerdighed;
	}
	
	public boolean getkvAcceptabel(){
		return kvAcceptabel;
	}
	
	public int getTillaegspoint(){
		return tillaegspoint;
	}

	//Overvej om alle cases skal have true/false

	
	/* (non-Javadoc)
	 * @see dataaccess.Kreditvaerdighed#setKreditvaerdighed(java.lang.String, dataaccess.CallBack)
	 * metoden koeres i en traad, ideen med callback parameteren er at naar man kalder metoden Overrider man metoden onRequestComplete() som saa udefoeres naar vaerdierne er sat
	 * dette sker da metoden  onRequestComplete() bliver kaldt efter switchsaetningen
	 */
	@Override
	public void setKreditvaerdighed(String cpr, CallBack callBack) {
		Thread thread = new Thread() {
			public void run() {
				kreditvaerdighed = cR.rate(cpr);
				switch (kreditvaerdighed) {
				case A:
					tillaegspoint = 1;
					kvAcceptabel = true;
					break;
					
				case B:
					tillaegspoint = 2;
					kvAcceptabel = true;
					break;
					
				case C:
					tillaegspoint = 3;
					kvAcceptabel = true;
					break;

				default:
					kvAcceptabel = false; // den er som standard false, det er mest for at fremhaeve at den ikke er acceptabel hvis den ikke er imellem A - C
					break;
				}
				
				callBack.onRequestComplete();
			};
		};
		thread.start();
	}
}