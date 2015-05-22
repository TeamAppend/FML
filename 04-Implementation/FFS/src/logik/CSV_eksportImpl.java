package logik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

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
	private String filPlacering = ".\\\\Lånetilbud.csv";

	public CSV_eksportImpl() {
		sController.tilmeldObserver(this);
		bController.tilmeldObserver(this);
		lController.tilmeldObserver(this);
		kController.tilmeldObserver(this);
		pController.tilmeldObserver(this);
	}

	@Override
	public void hentData(int lånetilbud_id, String filplacering) {
		File file = new File(filplacering);
		
		if (filplacering.trim().length() == 0 || !file.exists() || !file.isDirectory())
		{
			JOptionPane.showMessageDialog(null, "Stien er ikke gyldig. Prøv venligst igen.", "Ugyldig sti", JOptionPane.ERROR_MESSAGE);
			return; 
		}
			
		this.filPlacering = filplacering;
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
				cprnummer, sælgernavn, rang, FormatterLogik.dotSeperator(beløbsgrænse) + "", modelnavn,
				FormatterLogik.dotSeperator(pris) + "", FormatterLogik.formatTwoDigits(rentesats) + "", tilbageBetalingsPeriode + "",
				udbetaling + "", FormatterLogik.formatTwoDigits(ÅOP) + "", oprettelsestidspunkt + "" };
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
			append(FormatterLogik.formatTwoDigits(restgæld));
			append(FormatterLogik.formatTwoDigits(afdrag));
			append(FormatterLogik.formatTwoDigits(rente));
			lastValue((FormatterLogik.formatTwoDigits(afdrag + rente)));
		}
		double OP = sum / startgæld;
		ÅOP = (OP / (tilbageBetalingsPeriode / 12.000)) * 100;
		append("Total rente: ");
		append(FormatterLogik.formatTwoDigits(sum));
		lastValue("kr.");
		append("ÅOP: ");
		append(FormatterLogik.formatTwoDigits(ÅOP));
		lastValue("%");

		System.out.println(filPlacering + " - test");
		eksport(filPlacering);
	}
	
	@Override
	public void eksport(String filnavn) {
		Date d = new Date();

		// http://stackoverflow.com/a/4192287
		OutputStream os;
		try {
			os = new FileOutputStream(filnavn + "/LåneTilbud_" + kController.getKunde().getTelefon() + "_" + d.getTime() + ".csv");
		    os.write(239); // 239, 187 og 191 er "header values" som tilføjes til CSV-filen før der skrives text til filen. 
		    os.write(187); // Dette får excel til at forstå filen som at den skal dekode den med UTF-8 encoding. 
		    os.write(191); // Dette gør vi fordi Excel har en bug med at den ikke kan læse filer med UTF-8 encoding med mindre de skrives på en speciel måde.
		    PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));

		    w.print(sbToString());
		    w.flush();
		    w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		JOptionPane.showMessageDialog(null, "CSV-fil er oprettet!","Success!", JOptionPane.INFORMATION_MESSAGE);
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
