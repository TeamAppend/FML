package logik;

public interface CSV_eksport {

	public abstract void hentData(int lånetilbud_id, String filplacering);

	public abstract void toArraysTilStringBuffer(String[] navne,
			String[] værdier, int tilbageBetalingsPeriode, double udbetaling,
			double rentesats, double pris);

	public abstract void eksport(String filnavn);

	public abstract void append(String s);

	public abstract void lastValue(String s);

	public abstract String sbToString();

	public abstract void update(Object source, String s);

}