package domain;

import com.ferrari.finances.dk.rki.Rating;

public interface Kreditvaerdighed {

	public Rating getKredigvaerdighed();

	public boolean getkvAcceptabel();
	
	public int getTillaegspoint();

	public void setKreditvaerdighed(String cpr, CallBack callBack);
}
