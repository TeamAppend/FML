package domain;

public interface Kunde {

	public abstract int getKunde_id();

	public abstract void setKunde_id(int kunde_id);

	public abstract String getCPR_id();

	public abstract void setCPR_id(String CPR_id);

	public abstract String getKundenavn();

	public abstract void setKundenavn(String kundenavn);

	public abstract String getAdresse();

	public abstract void setAdresse(String adresse);

	public abstract String getPostnummer();

	public abstract void setPostnummer(String postnummer);

	public abstract String getTelefon();

	public abstract void setTelefon(String telefon);

	public abstract String getKommentar();

	public abstract void setKommentar(String kommentar);

}