package domain;

public interface Sælger {

	public abstract int getSælger_id();

	public abstract void setSælger_id(int sælger_id);

	public abstract String getSælgerNavn();

	public abstract void setSælgerNavn(String sælgerNavn);

	public abstract String getRang();

	public abstract void setRang(String rang);

	public abstract double getBeløbsGrænse();

	public abstract void setBeløbsGrænse(double beløbsGrænse);

}