package domain;

public interface Kunde {

	public abstract int getKunde_id();

	public abstract void setKunde_id(int kunde_id);

	public abstract int getCPR_id();

	public abstract void setCPR_id(int CPR_id);

	public abstract String getKundenavn();

	public abstract void setKundenavn(String kundenavn);

	public abstract String getAdresse();

	public abstract void setAdresse(String adresse);

	public abstract String getPostnummer();

	public abstract void setPostnummer(String postnummer);

	public abstract String getTelefon();

	public abstract void setTelefon(String telefon);
}