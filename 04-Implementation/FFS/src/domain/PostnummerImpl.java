package domain;

public class PostnummerImpl implements Postnummer {
	private String postnummer;
	private String bynavn;
	

	@Override
	public String getPostnummer() {
		return postnummer;
	}

	@Override
	public void setPostnummer(String postnummer) {
		this.postnummer = postnummer;
	}

	@Override
	public String getBynavn() {
		return bynavn;
	}

	@Override
	public void setBynavn(String bynavn) {
		this.bynavn = bynavn;
	}	
}
