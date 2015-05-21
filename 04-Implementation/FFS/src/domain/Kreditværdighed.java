package domain;

import com.ferrari.finances.dk.rki.Rating;

public interface Kreditværdighed {

	public Rating getKredigværdighed();

	public boolean getkvAcceptabel();
	
	public int getTillægspoint();

	public void setKreditværdighed(String cpr, CallBack callBack);
}
