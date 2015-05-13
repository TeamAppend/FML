package domain;

public class LånetilbudImpl implements Lånetilbud {
	private int lånetilbud_id;
	private double rentesats;
	private int tilbagebetalingsperiode;
	private double udbetaling;
	private double ÅOP;
	private int kunde_id;
	private int bil_id;
	private int sælger_id;
	private String oprettelsestidspunkt;
	
	@Override
	public int getLånetilbud_id() {
		return lånetilbud_id;
	}

	@Override
	public void setLånetilbud_id(int lånetilbud_id) {
		this.lånetilbud_id = lånetilbud_id;
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
	public double getÅOP() {
		return ÅOP;
	}

	@Override
	public void setÅOP(double ÅOP) {
		ÅOP = ÅOP;
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
	public int getSælger_id() {
		return sælger_id;
	}

	@Override
	public void setSælger_id(int sælger_id) {
		this.sælger_id = sælger_id;
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
