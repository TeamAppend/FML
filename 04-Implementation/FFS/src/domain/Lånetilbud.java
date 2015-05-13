package domain;

public interface Lånetilbud {

	public abstract int getLånetilbud_id();

	public abstract void setLånetilbud_id(int lånetilbud_id);

	public abstract double getRentesats();

	public abstract void setRentesats(double rentesats);

	public abstract int getTilbagebetalingsperiode();

	public abstract void setTilbagebetalingsperiode(int tilbagebetalingsperiode);

	public abstract double getUdbetaling();

	public abstract void setUdbetaling(double udbetaling);

	public abstract double getÅOP();

	public abstract void setÅOP(double ÅOP);

	public abstract int getKunde_id();

	public abstract void setKunde_id(int kunde_id);

	public abstract int getBil_id();

	public abstract void setBil_id(int bil_id);

	public abstract int getSælger_id();

	public abstract void setSælger_id(int sælger_id);

	public abstract String getOprettelsestidspunkt();

	public abstract void setOprettelsestidspunkt(String oprettelsestidspunkt);

}