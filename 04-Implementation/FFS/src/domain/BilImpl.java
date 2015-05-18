package domain;

public class BilImpl implements Bil {
	private int bil_id;
	private double pris;
	private String modelnavn;

	@Override
	public String getModelnavn() {
		return modelnavn;
	}

	@Override
	public void setModelnavn(String modelnavn) {
		this.modelnavn = modelnavn;
	}

	@Override
	public int getBil_id() {
		return bil_id;
	}

	@Override
	public void setBil_id(int bil_id) {
		this.bil_id = bil_id;
	}

	@Override
	public double getPris() {
		return pris;
	}

	@Override
	public void setPris(double pris) {
		this.pris = pris;
	}
}
