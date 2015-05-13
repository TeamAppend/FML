package domain;

public interface Laanetilbud {

	public abstract int getLaanetilbud_id();

	public abstract void setLaanetilbud_id(int laanetilbud_id);

	public abstract double getRentesats();

	public abstract void setRentesats(double rentesats);

	public abstract int getTilbagebetalingsperiode();

	public abstract void setTilbagebetalingsperiode(int tilbagebetalingsperiode);

	public abstract double getUdbetaling();

	public abstract void setUdbetaling(double udbetaling);

	public abstract double getAAOP();

	public abstract void setAAOP(double aaOP);

	public abstract int getKunde_id();

	public abstract void setKunde_id(int kunde_id);

	public abstract int getBil_id();

	public abstract void setBil_id(int bil_id);

	public abstract int getSaelger_id();

	public abstract void setSaelger_id(int saelger_id);

	public abstract String getOprettelsestidspunkt();

	public abstract void setOprettelsestidspunkt(String oprettelsestidspunkt);

}