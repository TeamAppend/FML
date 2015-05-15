package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dataaccess.PostnummerDataAccess;
import dataaccess.PostnummerDataAccessImpl;
import domain.CPRnummer;
import domain.CPRnummerImpl;
import domain.Kunde;
import domain.KundeImpl;
import domain.Postnummer;
import domain.PostnummerImpl;
import exceptions.CPRAllreadyExists;
import exceptions.KundeAllreadyExists;
import exceptions.PostnummerDoesNotExist;
import logik.CPRLogik;
import logik.CPRLogikImpl;
import logik.KundeLogik;
import logik.KundeLogikImpl;
import logik.PostnummerLogik;
import logik.PostnummerLogikImpl;
import logik.ValiderKundeLogik;
import logik.ValiderKundeLogikImpl;

public class OpretKunde extends JPanel implements ActionListener, KeyListener {
	private JTextField cpr = new JTextField(10);
	private JTextField navn = new JTextField(10);
	private JTextField adresse = new JTextField(10);
	private JTextField postnr = new JTextField(10);
	private JTextField by = new JTextField(10);
	private JTextField telefon = new JTextField(10);
	private JButton findKunde = new JButton("Find Kunde");
	private JButton opretKunde = new JButton("Opret Kunde");
	private Border blackBorder = new LineBorder(Color.BLACK);
	private Border redBorder = new LineBorder(Color.RED);

	private GridBagLayout layout;

	public OpretKunde() {
		// frame properties
		setVisible(true);

		layout = new GridBagLayout();
		setLayout(layout);

		TitledBorder title = new TitledBorder("Kunde");
		setBorder(title);

		GridBagConstraints con = new GridBagConstraints();
		Insets ins = new Insets(5, 5, 5, 5); // margin rund om objecterne

		con = createGBC(0, 0, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Telefon"), con);
		con = createGBC(1, 0, 1, 1);
		con.insets = ins;
		add(telefon, con);
		telefon.addKeyListener(this);
		con = createGBC(2, 0, 1, 1);
		con.insets = ins;
		findKunde.addActionListener(this);
		add(findKunde, con);

		con = createGBC(0, 1, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("CPR:"), con);
		con = createGBC(1, 1, 1, 1);
		con.insets = ins;
		add(cpr, con);
		cpr.setEnabled(false);

		con = createGBC(0, 2, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Navn:"), con);
		con = createGBC(1, 2, 1, 1);
		con.insets = ins;
		add(navn, con);
		navn.setEnabled(false);

		con = createGBC(0, 3, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Adresse:"), con);
		con = createGBC(1, 3, 1, 1);
		con.insets = ins;
		add(adresse, con);
		adresse.setEnabled(false);

		con = createGBC(0, 4, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Postnummer:"), con);
		con = createGBC(1, 4, 1, 1);
		con.insets = ins;
		add(postnr, con);
		postnr.setEnabled(false);
		postnr.addKeyListener(this);

		con = createGBC(0, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("By:"), con);
		con = createGBC(1, 5, 1, 1);
		con.insets = ins;
		add(by, con);
		by.setEnabled(false);

		con = createGBC(2, 6, 1, 1);
		con.insets = ins;
		add(opretKunde, con);
		opretKunde.setEnabled(false);
		opretKunde.addActionListener(this);

	}

	private GridBagConstraints createGBC(int x, int y, int width, int height) {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = x;
		gbc.gridy = y;

		gbc.gridwidth = width;
		gbc.gridheight = height;

		return gbc;
	}

	private void add(JComponent component, GridBagConstraints gbc) {
		layout.setConstraints(component, gbc);
		add(component);
	}

	private void disableTekstfelter() {
		cpr.setEnabled(false);
		navn.setEnabled(false);
		adresse.setEnabled(false);
		postnr.setEnabled(false);
		opretKunde.setEnabled(false);

	}

	private void enableTekstfelter() {
		cpr.setEnabled(true);
		navn.setEnabled(true);
		adresse.setEnabled(true);
		postnr.setEnabled(true);
		opretKunde.setEnabled(true);
		clearTekstfelter();
	}

	private void clearTekstfelter() {
		cpr.setText("");
		navn.setText("");
		adresse.setText("");
		postnr.setText("");
		by.setText("");
	}

	private boolean validerTekstfelter() {
		ValiderKundeLogik vkl = new ValiderKundeLogikImpl();
		StringBuilder sb = new StringBuilder();
		boolean b = true;
		if (!vkl.validerTelefon(telefon.getText())) {
			sb.append("- Telefonnummer må kun indeholde tallene 0-9, og skal være 8 tegn \n");
			telefon.setBorder(redBorder);
			b = false;
		}
		if (!vkl.validerCPR(cpr.getText())) {
			sb.append("- CPR-nummer må kun indeholde tallene 0-9, og skal være 10 tegn \n");
			b = false;
		}
		if (!vkl.validerNavn(navn.getText())) {
			sb.append("- Kundenavn må kun indeholde a-å, og må ikke være tom \n");
			b = false;
		}
		if (!vkl.validerAdresse(adresse.getText())) {
			sb.append("- Adresse må kun indeholde a-å og 0-9, og må ikke være tom \n");
			b = false;
		}
		if (!vkl.validerPostnr(postnr.getText())) {
			sb.append("- Postnummer må kun indeholde tallene 0-9, og skal være 4 tegn \n");
			b = false;
		}
		if (!b)
			JOptionPane.showMessageDialog(null, sb.toString(), "Fejl!",
					JOptionPane.ERROR_MESSAGE);
		return b;

	}

	private boolean telefonNrEksistererIkke(String s) throws SQLException {
		KundeLogik kl = new KundeLogikImpl();
		List<KundeImpl> list = kl.listKunde(s);
		return list.isEmpty();
	}

	private void hentKunde(String s) throws SQLException,
			PostnummerDoesNotExist {
		Kunde kunde = new KundeImpl();
		KundeLogik kl = new KundeLogikImpl();
		List<KundeImpl> list = kl.listKunde(s);
		kunde = list.get(0);
		navn.setText(kunde.getKundenavn());
		adresse.setText(kunde.getAdresse());
		postnr.setText(kunde.getPostnummer());

		PostnummerLogik pda = new PostnummerLogikImpl();
		PostnummerImpl postnummer = pda.listPostnummer(kunde.getPostnummer());
		String bynavn = postnummer.getBynavn();
		by.setText(bynavn);

		cpr.setText("**********");

	}

	private void findKunde() {
		ValiderKundeLogik vkl = new ValiderKundeLogikImpl();
		if (vkl.validerTelefon(telefon.getText())) {
			try {
				if (telefonNrEksistererIkke(telefon.getText()))
					enableTekstfelter();
				else {
					hentKunde(telefon.getText());
					disableTekstfelter();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (PostnummerDoesNotExist e1) {
				e1.printStackTrace();
			}
		} else
			JOptionPane
					.showMessageDialog(
							null,
							"Telefonnummer må kun indeholde tallene 0-9, og skal være 8 tegn",
							"Fejl!", JOptionPane.ERROR_MESSAGE);
	}

	private void opretKunde() throws SQLException, CPRAllreadyExists,
			KundeAllreadyExists {
		if (validerTekstfelter()) {
			CPRnummer cprn = new CPRnummerImpl();
			cprn.setCPRnummer(cpr.getText());
			CPRLogik cl = new CPRLogikImpl();
			cl.createCPR(cprn);

			List<CPRnummerImpl> list = cl.listCPR(cpr.getText());
			CPRnummer cprn2 = list.get(0);
			int cpr_id = cprn2.getCPR_id();

			Kunde kunde = new KundeImpl();
			kunde.setCPR_id(cpr_id);
			kunde.setKundenavn(navn.getText());
			kunde.setAdresse(adresse.getText());
			kunde.setPostnummer(postnr.getText());
			kunde.setTelefon(telefon.getText());

			KundeLogik kl = new KundeLogikImpl();
			kl.createKunde(kunde);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == findKunde) {
			findKunde();
		} else if (source.equals(opretKunde)) {
			try {
				opretKunde();
			} catch (SQLException | CPRAllreadyExists e1) {
				e1.printStackTrace();
			} catch (KundeAllreadyExists e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (telefon.hasFocus()) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				findKunde();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(postnr.hasFocus()){
			if(postnr.getText().length() == 4 ){
				PostnummerLogik pl = new PostnummerLogikImpl();
				try {
					Postnummer pn = pl.listPostnummer(postnr.getText());
					if(pn != null){
						by.setText(pn.getBynavn());
					}else
						by.setText("");
				} catch (SQLException | PostnummerDoesNotExist e) {
					e.printStackTrace();
				}
				
			}else
				by.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
