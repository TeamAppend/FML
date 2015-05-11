package domain;

public class SaelgerImpl implements Saelger {
	private int saelger_id;
	private String saelgerNavn;
	private String rang;
	private double beloebsGraense;
	

	@Override
	public int getSaelger_id() {
		return saelger_id;
	}

	@Override
	public void setSaelger_id(int saelger_id) {
		this.saelger_id = saelger_id;
	}

	@Override
	public String getSaelgerNavn() {
		return saelgerNavn;
	}

	@Override
	public void setSaelgerNavn(String saelgerNavn) {
		this.saelgerNavn = saelgerNavn;
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
	public double getBeloebsGraense() {
		return beloebsGraense;
	}

	@Override
	public void setBeloebsGraense(double beloebsGraense) {
		this.beloebsGraense = beloebsGraense;
	}
}
