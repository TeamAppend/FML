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
	@Override
	public void setKreditvaerdighed(String cpr) {
		Rating kreditvaerdighedTemp = cR.rate(cpr);
		switch (kreditvaerdighedTemp) {
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
			kvAcceptabel = false;
			break;
		}
	}
}
