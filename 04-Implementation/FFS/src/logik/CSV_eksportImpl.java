package logik;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

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
	private Kunde kunde;
	private Sælger sælger;
	private Bil bil;
	private CPRnummer cpr;
	private Lånetilbud lånetilbud;
	private Postnummer postnummer;
	private boolean bilHentet = false, sælgerHentet = false,
			kundeHentet = false, postnummerHentet = false;
	private String filplacering = ".\\Lånetilbud.csv";

	public CSV_eksportImpl() {
		sController.tilmeldObserver(this);
		bController.tilmeldObserver(this);
		lController.tilmeldObserver(this);
		kController.tilmeldObserver(this);
		pController.tilmeldObserver(this);
	}

	@Override
	public void hentData(int lånetilbud_id, String filplacering) {
		if(filplacering != "")
			this.filplacering = filplacering;
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
		double beløbsgrænse = sælger.getBeløbsGrænse();

		String cprnummer = cpr.getCPRnummer();

		String bynavn = postnummer.getBynavn();

		String modelnavn = bil.getModelnavn();
		double pris = bil.getPris();

		double rentesats = lånetilbud.getRentesats();
		int tilbageBetalingsPeriode = lånetilbud.getTilbagebetalingsperiode();
		double udbetaling = lånetilbud.getUdbetaling();
		double ÅOP = lånetilbud.getÅOP();
		Timestamp oprettelsestidspunkt = lånetilbud.getOprettelsestidspunkt();

		String[] navne = { "Kundenavn: ", "Adresse: ", "Postnummer: ",
				"Bynavn: ", "Telefonnummer: ", "CPR nummer: ", "Sælgernavn: ",
				"Sælgerrang", "Beløbsgrænse: ", "Modelnavn: ", "Pris: ",
				"Rentesats: ", "Tilbagebetalingsperiode: ", "Udbetaling: ",
				"ÅOP: ", "Oprettelsestidspunkt: " };

		String[] værdier = { kundenavn, adresse, spostnummer, bynavn, telefon,
				cprnummer, sælgernavn, rang, dotSeperator(beløbsgrænse) + "", modelnavn,
				dotSeperator(pris) + "", formatTwoDigits(rentesats) + "", tilbageBetalingsPeriode + "",
				udbetaling + "", formatTwoDigits(ÅOP) + "", oprettelsestidspunkt + "" };
		toArraysTilStringBuffer(navne, værdier, tilbageBetalingsPeriode,
				udbetaling, rentesats, pris);
	}

	@Override
	public void toArraysTilStringBuffer(String[] navne, String[] værdier,
			int tilbageBetalingsPeriode, double udbetaling, double rentesats,
			double pris) {
		for (int i = 0; i < navne.length; i++) {
			append(navne[i]);
			lastValue(værdier[i]);
		}
		
		
		
		double ÅOP = 0;
		double startgæld = pris - udbetaling;
		double restgæld = startgæld;
		double afdrag = restgæld / tilbageBetalingsPeriode;
		double rente = 0;
		rentesats = rentesats / 100;
		double sum = 0;
		
		lastValue("");
		
		append("Termin");
		append("Restgæld");
		append("Afdrag");
		append("Rente");
		lastValue("Ydelse");
		for (int i = 0; i < tilbageBetalingsPeriode; i++) {
			rente = (rentesats / 12.000) * restgæld;
			sum += rente;
			restgæld -= afdrag;
			append((i+1)+"");
			append(formatTwoDigits(restgæld));
			append(formatTwoDigits(afdrag));
			append(formatTwoDigits(rente));
			lastValue((formatTwoDigits(afdrag + rente)));
		}
		double OP = sum / startgæld;
		ÅOP = (OP / (tilbageBetalingsPeriode / 12.000)) * 100;
		append("Total rente: ");
		append(formatTwoDigits(sum));
		lastValue("kr.");
		append("ÅOP: ");
		append(formatTwoDigits(ÅOP));
		lastValue("%");

		eksport(filplacering);
	}

	@Override
	public void eksport(String filnavn) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filnavn), "utf-8"))) {
			writer.write(sbToString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "CSV-fil er oprettet!","Success!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private String formatTwoDigits(Number n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        return format.format(n);
    }
	
	private String dotSeperator(Number n){
		
		DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
		symbols.setGroupingSeparator('.');

		DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
		return formatter.format(n);
	}

	@Override
	public void append(String s) {
		sb.append(s + ";");
	}

	@Override
	public void lastValue(String s) {
		sb.append(s + " \n");
	}

	@Override
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
				if (postnummerHentet && sælgerHentet && bilHentet
						&& kundeHentet) {
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
