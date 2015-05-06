package domain;

public class Kunde {

	private int kunde_id;
	private String CPR_id;
	private String kundenavn;
	private String adresse;
	private String postnummer;
	private String telefon;
	private String kommentar;

	public int getKunde_id() {
		return kunde_id;
	}
	public void setKunde_id(int kunde_id) {
		this.kunde_id = kunde_id;
	}
	public String getCPR_id() {
		return CPR_id;
	}
	public void setCPR_id(String CPR_id) {
		this.CPR_id = CPR_id;
	}
	public String getKundenavn() {
		return kundenavn;
	}
	public void setKundenavn(String kundenavn) {
		this.kundenavn = kundenavn;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPostnummer() {
		return postnummer;
	}
	public void setPostnummer(String postnummer) {
		this.postnummer = postnummer;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	
	
}
