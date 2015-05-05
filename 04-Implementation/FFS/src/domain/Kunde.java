package domain;

public class Kunde {

	private int kunde_id;
	private String CPRnummer;
	private String navn;
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
	public String getCPRnummer() {
		return CPRnummer;
	}
	public void setCPRnummer(String cPRnummer) {
		CPRnummer = cPRnummer;
	}
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
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
