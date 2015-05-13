package domain;

import com.ferrari.finances.dk.rki.*;

public class KreditvaerdighedImpl implements Kreditvaerdighed {

	private Rating kreditvaerdighed;
	private CreditRator cR = CreditRator.i();
	private int tillaegspoint = 0;
	private boolean kvAcceptabel = false;

	@Override
	public Rating getKredigvaerdighed() {
		return kreditvaerdighed;
	}

	@Override
	public boolean getkvAcceptabel() {
		return kvAcceptabel;
	}

	@Override
	public int getTillaegspoint() {
		return tillaegspoint;
	}

	/**
	 * metoden henter kreditvaerdigheden fra CreditRator, som
	 * koeres i en traad, saetter tillaegspoint i forhold til kreditvaerdigheden
	 * 
	 * @see domain.Kreditvaerdighed#setKreditvaerdighed(java.lang.String,
	 *      domain.CallBack)
	 * @param "cpr" der angives det cprnummer som der oenskes kreditvaerdithed paa
	 * @param "callback" ideen med callback parameteren er at naar man kalder
	 *        metoden Overrider man metoden onRequestComplete() som saa
	 *        udefoeres naar vaerdierne er sat dette sker da metoden
	 *        onRequestComplete() bliver kaldt efter switchsaetningen
	 * 
	 * 
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
					kvAcceptabel = false; 
					break;
				}

				callBack.onRequestComplete();
			};
		};
		thread.start();
	}
}