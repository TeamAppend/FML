package domain;

public class Sælger {
	private int sælger_id;
	private String sælgerNavn;
	private String rang;
	private double beløbsGrænse;
	
	public int getSælger_id() {
		return sælger_id;
	}
	public void setSælger_id(int sælger_id) {
		this.sælger_id = sælger_id;
	}
	public String getSælgerNavn() {
		return sælgerNavn;
	}
	public void setSælgerNavn(String sælgerNavn) {
		this.sælgerNavn = sælgerNavn;
	}
	public String getRang() {
		return rang;
	}
	public void setRang(String rang) {
		this.rang = rang;
	}
	public double getBeløbsGrænse() {
		return beløbsGrænse;
	}
	public void setBeløbsGrænse(double beløbsGrænse) {
		this.beløbsGrænse = beløbsGrænse;
	}
	
	
}
