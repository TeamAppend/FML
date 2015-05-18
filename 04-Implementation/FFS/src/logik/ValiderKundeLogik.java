package logik;

public interface ValiderKundeLogik {

	public abstract boolean validerCPR(String s);

	public abstract boolean validerNavn(String s);

	public abstract boolean validerAdresse(String s);

	public abstract boolean validerPostnr(String s);

	public abstract boolean validerTelefon(String s);

}