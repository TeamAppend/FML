package domain;

public class Laanetilbud {
	private int laanetilbud_id;
	private double rentesats;
	private int tilbagebetalingsperiode;
	private double udbetaling;
	private double AAOP;
	private int kunde_id;
	private int bil_id;
	private int saelger_id;
	private String oprettelsestidspunkt;
	
	
	public int getLaanetilbud_id() {
		return laanetilbud_id;
	}
	public void setLaanetilbud_id(int laanetilbud_id) {
		this.laanetilbud_id = laanetilbud_id;
	}
	public double getRentesats() {
		return rentesats;
	}
	public void setRentesats(double rentesats) {
		this.rentesats = rentesats;
	}
	public int getTilbagebetalingsperiode() {
		return tilbagebetalingsperiode;
	}
	public void setTilbagebetalingsperiode(int tilbagebetalingsperiode) {
		this.tilbagebetalingsperiode = tilbagebetalingsperiode;
	}
	public double getUdbetaling() {
		return udbetaling;
	}
	public void setUdbetaling(double udbetaling) {
		this.udbetaling = udbetaling;
	}
	public double getAAOP() {
		return AAOP;
	}
	public void setAAOP(double aaOP) {
		AAOP = aaOP;
	}
	public int getKunde_id() {
		return kunde_id;
	}
	public void setKunde_id(int kunde_id) {
		this.kunde_id = kunde_id;
	}
	public int getBil_id() {
		return bil_id;
	}
	public void setBil_id(int bil_id) {
		this.bil_id = bil_id;
	}
	public int getSaelger_id() {
		return saelger_id;
	}
	public void setSaelger_id(int saelger_id) {
		this.saelger_id = saelger_id;
	}
	public String getOprettelsestidspunkt() {
		return oprettelsestidspunkt;
	}
	public void setOprettelsestidspunkt(String oprettelsestidspunkt) {
		this.oprettelsestidspunkt = oprettelsestidspunkt;
	}

	
	
}
