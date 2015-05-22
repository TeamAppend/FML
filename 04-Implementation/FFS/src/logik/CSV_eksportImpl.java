package logik;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.table.TableModel;

import domain.Bil;
import domain.CPRnummer;
import domain.Kunde;
import domain.Lånetilbud;
import domain.Postnummer;
import domain.Sælger;
import exceptions.PostnummerDoesNotExist;

public class CSV_eksportImpl implements CSV_eksport, FFSObserver {
	private SælgerController sController = SælgerController.instance();
	private BilController bController = BilController.instance();
	private LånetilbudController lController = LånetilbudController.instance();
	private KundeController kController = KundeController.instance();
	private PostnummerController pController = PostnummerController.instance();
	private StringBuffer sb = new StringBuffer();
	private TableModel t;
	private int cCount = 0;
	private int rCount = 0;
	private Kunde kunde;
	private Sælger sælger;
	private Bil bil;
	private CPRnummer cpr;
	private Lånetilbud lånetilbud;
	private Postnummer postnummer;
	private boolean bilHentet = false, sælgerHentet = false,
			kundeHentet = false, postnummerHentet = false;

	public CSV_eksportImpl() {
		sController.tilmeldObserver(this);
		bController.tilmeldObserver(this);
		lController.tilmeldObserver(this);
		kController.tilmeldObserver(this);
		pController.tilmeldObserver(this);
	}

	public void hentData(int lånetilbud_id) {
		lånetilbud = lController.hentLånetilbud(lånetilbud_id);
		int kunde_id = lånetilbud.getKunde_id();
		int bil_id = lånetilbud.getBil_id();
		int sælger_id = lånetilbud.getSælger_id();

		kController.hentKunde(kunde_id);
		bController.hentBil(bil_id);
		sController.hentSælger(sælger_id);
		try {
			pController.hentPostnummer(kunde.getPostnummer());
		} catch (SQLException | PostnummerDoesNotExist e) {
			e.printStackTrace();
		}
	}

	private void opretLister() {
		String kundenavn = kunde.getKundenavn();
		String adresse = kunde.getAdresse();
		String spostnummer = kunde.getPostnummer();
		String telefon = kunde.getTelefon();

		String sælgernavn = sælger.getSælgerNavn();
		String rang = sælger.getRang();
		String beløbsgrænse = sælger.getBeløbsGrænse() + "";

		String cprnummer = cpr.getCPRnummer();

		String bynavn = postnummer.getBynavn();

		String modelnavn = bil.getModelnavn();
		String pris = bil.getPris() + "";

		String rentesats = lånetilbud.getRentesats() + "";
		String tilbagebetalingsPeriode = lånetilbud
				.getTilbagebetalingsperiode() + "";
		String udbetaling = lånetilbud.getUdbetaling() + "";
		String ÅOP = lånetilbud.getÅOP() + "";
		String oprettelsestidspunkt = lånetilbud.getOprettelsestidspunkt() + "";

		String[] navne = { "Kundenavn: ", "Adresse: ", "Postnummer: ",
				"Bynavn: ", "Telefonnummer: ", "CPR nummer: ", "Sælgernavn: ",
				"Sælgerrang", "Beløbsgrænse: ", "Modelnavn: ", "Pris: ",
				"Rentesats: ", "Tilbagebetalingsperiode: ", "Udbetaling: ",
				"ÅOP: ", "Oprettelsestidspunkt: " };

		String[] værdier = { kundenavn, adresse, spostnummer, bynavn, telefon,
				cprnummer, sælgernavn, rang, beløbsgrænse, modelnavn, pris,
				rentesats, tilbagebetalingsPeriode, udbetaling, ÅOP,
				oprettelsestidspunkt };
		toArraysTilStringBuffer(navne, værdier);
	}

	public void toArraysTilStringBuffer(String[] navne, String[] værdier) {
		for (int i = 0; i < navne.length; i++) {
			append(navne[i]);
			lastValue(værdier[i]);
		}
		System.out.println(sb.toString());
	}

	public String eksport() {
		tableToStringBuffer();
		String s = sbToString();
		return s;
	}

	public void tableToStringBuffer() {
		for (int i = 0; i < rCount; i++) {
			for (int x = 0; x < cCount - 1; x++) {
				append((String) t.getValueAt(x, i));
			}
			lastValue((String) t.getValueAt(cCount, i));
		}
	}

	public void append(String s) {
		sb.append(s + ",");
	}

	public void lastValue(String s) {
		sb.append(s + " \n");
	}

	public String sbToString() {
		return sb.toString();
	}

	@Override
	public void update(Object source, String s) {
		if (source instanceof KundeController) {
			if (s.equals("hentKunde")) {
				kunde = kController.getKunde();
				cpr = kController.getCprnummer();
				kundeHentet = true;
			}
		} else if (source instanceof BilController) {
			if (s.equals("hentBil")) {
				bil = bController.getBil();
				bilHentet = true;
			}
		} else if (source instanceof SælgerController) {
			if (s.equals("hentSælger")) {
				sælger = sController.getSælger();
				sælgerHentet = true;
			}
		} else if (source instanceof PostnummerController) {
			if (s.equals("hentPostnummer")) {
				postnummer = pController.getPostnummer();
				postnummerHentet = true;
				if(postnummerHentet && sælgerHentet && bilHentet && kundeHentet){
					opretLister();
					postnummerHentet = false;
					sælgerHentet = false;
					bilHentet = false;
					kundeHentet = false;
				}
			}
		}
	}
}
