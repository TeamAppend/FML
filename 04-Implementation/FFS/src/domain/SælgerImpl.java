package domain;

public class SælgerImpl implements Sælger {
	private int sælger_id;
	private String sælgerNavn;
	private String rang;
	private double beløbsGrænse;
	

	@Override
	public int getSælger_id() {
		return sælger_id;
	}

	@Override
	public void setSælger_id(int sælger_id) {
		this.sælger_id = sælger_id;
	}

	@Override
	public String getSælgerNavn() {
		return sælgerNavn;
	}

	@Override
	public void setSælgerNavn(String sælgerNavn) {
		this.sælgerNavn = sælgerNavn;
	}

	@Override
	public String getRang() {
		return rang;
	}

	@Override
	public void setRang(String rang) {
		this.rang = rang;
	}

	@Override
	public double getBeløbsGrænse() {
		return beløbsGrænse;
	}

	@Override
	public void setBeløbsGrænse(double beløbsGrænse) {
		this.beløbsGrænse = beløbsGrænse;
	}
}
