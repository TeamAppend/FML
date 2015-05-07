package domain;

public class Lånetilbud {
	private int lånetilbud_id;
	private double rentesats;
	private int tilbagebetalingsperiode;
	private double udbetaling;
	private double ÅOP;
	private int kunde_id;
	private int bil_id;
	private int sælger_id;
	private String oprettelsestidspunkt;
	
	
	public int getLånetilbud_id() {
		return lånetilbud_id;
	}
	public void setLånetilbud_id(int lånetilbud_id) {
		this.lånetilbud_id = lånetilbud_id;
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
	public double getÅOP() {
		return ÅOP;
	}
	public void setÅOP(double åOP) {
		ÅOP = åOP;
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
	public int getSælger_id() {
		return sælger_id;
	}
	public void setSælger_id(int sælger_id) {
		this.sælger_id = sælger_id;
	}
	public String getOprettelsestidspunkt() {
		return oprettelsestidspunkt;
	}
	public void setOprettelsestidspunkt(String oprettelsestidspunkt) {
		this.oprettelsestidspunkt = oprettelsestidspunkt;
	}

	
	
}
