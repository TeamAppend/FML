package dataaccess;

import com.ferrari.finances.dk.rki.*;

public class KreditvaerdighedImpl implements Kreditvaerdighed {

	private Rating kreditvaerdighed; // ændret til en Rating fra String
	private CreditRator cR = CreditRator.i();
	private int tillægspoint = 0;
	private boolean kvAcceptabel = false;

	@Override
	public Rating getKredigvaerdighed() {
		return kreditvaerdighed;
	}

	@Override
	public void setKreditvaerdighed(String cpr) {
		Rating kreditvaerdighedTemp = cR.rate(cpr);
		switch (kreditvaerdighedTemp) {
		case A:
			tillægspoint += 1;
			kvAcceptabel = true;
			break;
			
		case B:
			tillægspoint += 2;
			kvAcceptabel = true;
			break;
			
		case C:
			tillægspoint += 3;
			kvAcceptabel = true;
			break;

		default:
			kvAcceptabel = false;
			break;
		}
				
		
		
	}
}
