package domain;

public class KundeImpl implements Kunde {

	private int kunde_id;
	private int CPR_id;
	private String kundenavn;
	private String adresse;
	private String postnummer;
	private String telefon;

	
	@Override
	public int getKunde_id() {
		return kunde_id;
	}

	@Override
	public void setKunde_id(int kunde_id) {
		this.kunde_id = kunde_id;
	}

	@Override
	public int getCPR_id() {
		return CPR_id;
	}

	@Override
	public void setCPR_id(int CPR_id) {
		this.CPR_id = CPR_id;
	}

	@Override
	public String getKundenavn() {
		return kundenavn;
	}

	@Override
	public void setKundenavn(String kundenavn) {
		this.kundenavn = kundenavn;
	}

	@Override
	public String getAdresse() {
		return adresse;
	}

	@Override
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String getPostnummer() {
		return postnummer;
	}

	@Override
	public void setPostnummer(String postnummer) {
		this.postnummer = postnummer;
	}

	@Override
	public String getTelefon() {
		return telefon;
	}

	@Override
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
}
