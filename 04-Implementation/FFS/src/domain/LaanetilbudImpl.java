package domain;

public class LaanetilbudImpl implements Laanetilbud {
	private int laanetilbud_id;
	private double rentesats;
	private int tilbagebetalingsperiode;
	private double udbetaling;
	private double AAOP;
	private int kunde_id;
	private int bil_id;
	private int saelger_id;
	private String oprettelsestidspunkt;
	
	@Override
	public int getLaanetilbud_id() {
		return laanetilbud_id;
	}

	@Override
	public void setLaanetilbud_id(int laanetilbud_id) {
		this.laanetilbud_id = laanetilbud_id;
	}

	@Override
	public double getRentesats() {
		return rentesats;
	}

	@Override
	public void setRentesats(double rentesats) {
		this.rentesats = rentesats;
	}

	@Override
	public int getTilbagebetalingsperiode() {
		return tilbagebetalingsperiode;
	}

	@Override
	public void setTilbagebetalingsperiode(int tilbagebetalingsperiode) {
		this.tilbagebetalingsperiode = tilbagebetalingsperiode;
	}

	@Override
	public double getUdbetaling() {
		return udbetaling;
	}

	@Override
	public void setUdbetaling(double udbetaling) {
		this.udbetaling = udbetaling;
	}

	@Override
	public double getAAOP() {
		return AAOP;
	}

	@Override
	public void setAAOP(double aaOP) {
		AAOP = aaOP;
	}

	@Override
	public int getKunde_id() {
		return kunde_id;
	}

	@Override
	public void setKunde_id(int kunde_id) {
		this.kunde_id = kunde_id;
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
	public int getSaelger_id() {
		return saelger_id;
	}

	@Override
	public void setSaelger_id(int saelger_id) {
		this.saelger_id = saelger_id;
	}

	@Override
	public String getOprettelsestidspunkt() {
		return oprettelsestidspunkt;
	}

	@Override
	public void setOprettelsestidspunkt(String oprettelsestidspunkt) {
		this.oprettelsestidspunkt = oprettelsestidspunkt;
	}
}
