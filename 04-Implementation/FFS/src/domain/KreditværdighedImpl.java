package domain;

import com.ferrari.finances.dk.rki.*;

public class KreditværdighedImpl implements Kreditværdighed {

	private Rating kreditværdighed;
	private CreditRator cR = CreditRator.i();
	private int tillægspoint = 0;
	private boolean kvAcceptabel = false;

	@Override
	public Rating getKredigværdighed() {
		return kreditværdighed;
	}

	@Override
	public boolean getkvAcceptabel() {
		return kvAcceptabel;
	}

	@Override
	public int getTillægspoint() {
		return tillægspoint;
	}

	/**
	 * metoden henter kreditværdigheden fra CreditRator, som
	 * koeres i en traad, sætter tillægspoint i forhold til kreditværdigheden
	 * 
	 * @see domain.Kreditværdighed#setKreditværdighed(java.lang.String,
	 *      domain.CallBack)
	 * @param "cpr" der angives det cprnummer som der oenskes kreditværdithed paa
	 * @param "callback" ideen med callback parameteren er at naar man kalder
	 *        metoden Overrider man metoden onRequestComplete() som saa
	 *        udefoeres naar værdierne er sat dette sker da metoden
	 *        onRequestComplete() bliver kaldt efter switchsætningen
	 * 
	 * 
	 */
	@Override
	public void setKreditværdighed(String cpr, CallBack callBack) {
		Thread thread = new Thread() {
			public void run() {
				kreditværdighed = cR.rate(cpr);
				switch (kreditværdighed) {
				case A:
					tillægspoint = 1;
					kvAcceptabel = true;
					break;

				case B:
					tillægspoint = 2;
					kvAcceptabel = true;
					break;

				case C:
					tillægspoint = 3;
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