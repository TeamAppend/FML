package dataaccess;
import com.ferrari.finances.dk.rki.*;

public interface Kreditvaerdighed {

	public Rating getKredigvaerdighed();

	public boolean getkvAcceptabel();
	
	public int getTillaegspoint();
	
	public void setKreditvaerdighed(String cpr);
}
