package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import logik.FFSObserver;
import logik.KundeController;
import logik.PostnummerController;
import exceptions.CPRAllreadyExists;
import exceptions.KundeAllreadyExists;
import exceptions.PostnummerDoesNotExist;

public class OpretKunde extends JPanel implements FFSObserver, ActionListener,
		KeyListener, FocusListener {
	private JTextField tfCPR = new JTextField(10), tfNavn = new JTextField(10),
			tfAdresse = new JTextField(10), tfPostnummer = new JTextField(10),
			tfBy = new JTextField(10), tfTelefon = new JTextField(10);
	private JButton btnFindKunde = new JButton("Find Kunde"),
			btnOpretKunde = new JButton("Opret Kunde");
	private Border blackBorder = new LineBorder(null),
			redBorder = new LineBorder(Color.RED),
			greenBorder = new LineBorder(Color.GREEN);
	private GridBagLayout layout;
	private KundeController kController = KundeController.instance();
	private PostnummerController pController = PostnummerController.instance();

	public OpretKunde() {
		kController.tilmeldObserver(this);
		pController.tilmeldObserver(this);
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
		add(tfTelefon, con);
		tfTelefon.addKeyListener(this);
		tfTelefon.addFocusListener(this);

		con = createGBC(2, 0, 1, 1);
		con.insets = ins;
		btnFindKunde.addActionListener(this);
		add(btnFindKunde, con);
		btnFindKunde.setEnabled(false);

		con = createGBC(0, 1, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("CPR:"), con);
		con = createGBC(1, 1, 1, 1);
		con.insets = ins;
		add(tfCPR, con);
		tfCPR.setEnabled(false);
		tfCPR.addFocusListener(this);

		con = createGBC(0, 2, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Navn:"), con);
		con = createGBC(1, 2, 1, 1);
		con.insets = ins;
		add(tfNavn, con);
		tfNavn.setEnabled(false);
		tfNavn.addFocusListener(this);

		con = createGBC(0, 3, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Adresse:"), con);
		con = createGBC(1, 3, 1, 1);
		con.insets = ins;
		add(tfAdresse, con);
		tfAdresse.setEnabled(false);
		tfAdresse.addFocusListener(this);

		con = createGBC(0, 4, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Postnummer:"), con);
		con = createGBC(1, 4, 1, 1);
		con.insets = ins;
		add(tfPostnummer, con);
		tfPostnummer.setEnabled(false);
		tfPostnummer.addKeyListener(this);
		tfPostnummer.addFocusListener(this);

		con = createGBC(0, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("By:"), con);
		con = createGBC(1, 5, 1, 1);
		con.insets = ins;
		add(tfBy, con);
		tfBy.setEnabled(false);

		con = createGBC(2, 6, 1, 1);
		con.insets = ins;
		add(btnOpretKunde, con);
		btnOpretKunde.setEnabled(false);
		btnOpretKunde.addActionListener(this);

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

	/*
	 * SLUT PÅ UI OPBYGNING
	 */

	public void disableTekstfelter() {
		tfCPR.setEnabled(false);
		tfNavn.setEnabled(false);
		tfAdresse.setEnabled(false);
		tfPostnummer.setEnabled(false);
		btnOpretKunde.setEnabled(false);
	}

	public void enableTekstfelter() {
		tfCPR.setEnabled(true);
		tfNavn.setEnabled(true);
		tfAdresse.setEnabled(true);
		tfPostnummer.setEnabled(true);
		btnOpretKunde.setEnabled(true);
		clearTekstfelter();
	}

	public void clearTekstfelter() {
		tfCPR.setText("");
		tfNavn.setText("");
		tfAdresse.setText("");
		tfPostnummer.setText("");
		tfBy.setText("");
	}

	public void blackBorders() {
		tfAdresse.setBorder(blackBorder);
		tfTelefon.setBorder(blackBorder);
		tfCPR.setBorder(blackBorder);
		tfNavn.setBorder(blackBorder);
		tfPostnummer.setBorder(blackBorder);
	}

	public void findKunde() {
		if (validerTelefon(tfTelefon.getText())) {
			btnFindKunde.setEnabled(false);
			try {
				if (kController.telefonNrEksistererIkke(tfTelefon.getText())) {
					enableTekstfelter();
					blackBorders();
					kController.setKundeFundet(false);
				} else {
					kController.hentKunde(tfTelefon.getText());
				}
			} catch (SQLException | PostnummerDoesNotExist e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Telefonnummer må kun indeholde tallene 0-9, og skal være 8 tegn",
							"Fejl!", JOptionPane.ERROR_MESSAGE);
			tfTelefon.setBorder(redBorder);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnFindKunde) {
			findKunde();
		} else if (source.equals(btnOpretKunde)) {
			try {
				String telefon = tfTelefon.getText();
				String cpr = tfCPR.getText();
				String navn = tfNavn.getText();
				String adresse = tfAdresse.getText();
				String postnummer = tfPostnummer.getText();
				String bynavn = tfBy.getText();
				if (validerTekstfelter(telefon, cpr, navn, adresse, postnummer,
						bynavn))
					kController.opretKunde(telefon, cpr, navn, adresse, postnummer);
			} catch (SQLException | CPRAllreadyExists e1) {
				e1.printStackTrace();
			} catch (KundeAllreadyExists e1) {
				e1.printStackTrace();
			} catch (PostnummerDoesNotExist e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		JTextField source = (JTextField) arg0.getSource();
		if (source.equals(tfPostnummer)) {
			if (validerPostnummer(tfPostnummer.getText())) {
				try {
					pController.hentPostnummer(tfPostnummer.getText());
				} catch (SQLException | PostnummerDoesNotExist e) {
					e.printStackTrace();
				}

			} else {
				tfBy.setText("");
				tfPostnummer.setBorder(redBorder);
			}
		} else if (source.equals(tfTelefon)) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER
					&& validerTelefon(tfTelefon.getText())) {
				findKunde();
			} else {
				if (!validerTelefon(tfTelefon.getText()))
					btnFindKunde.setEnabled(false);
				else{
					btnFindKunde.setEnabled(true);
					
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		/*
		 * JTextField source = (JTextField) arg0.getComponent(); if
		 * (source.getBorder() == redBorder) { source.setBorder(blackBorder); }
		 */
	}

	public void focusLost(FocusEvent arg0) {
		JTextField source = (JTextField) arg0.getComponent();
		if (source.equals(tfAdresse)) {
			if (!validerAdresse(tfAdresse.getText())) {
				tfAdresse.setBorder(redBorder);
			} else
				tfAdresse.setBorder(greenBorder);
		} else if (source.equals(tfCPR)) {
			if (!validerCPR(tfCPR.getText())) {
				tfCPR.setBorder(redBorder);
			} else
				tfCPR.setBorder(greenBorder);
		} else if (source.equals(tfNavn)) {
			if (!validerNavn(tfNavn.getText())) {
				tfNavn.setBorder(redBorder);
			} else
				tfNavn.setBorder(greenBorder);
		} else if (source.equals(tfTelefon)) {
			if (!validerTelefon(tfTelefon.getText())) {
				tfTelefon.setBorder(redBorder);
			} else{
				if(!kController.getKundeFundet())
					tfTelefon.setBorder(greenBorder);
			}
		} else if (source.equals(tfPostnummer)) {
			if (!validerBy(tfBy.getText())) {
				tfPostnummer.setBorder(redBorder);
			}
		}
	}

	/*
	 * Validering
	 */

	protected boolean validerCPR(String s) {
		boolean b = true;
		if (s.length() != 10)
			b = false;
		else if (!s.matches("[0-9]+"))
			b = false;
		return b;
	}

	protected boolean validerNavn(String s) {
		boolean b = true;
		if (s.length() == 0)
			b = false;
		else if (!s.matches("[a-zA-ZæøåÆØÅ ]+"))
			b = false;
		return b;
	}

	protected boolean validerAdresse(String s) {
		boolean b = true;
		if (s.length() == 0)
			b = false;
		else if (!s.matches("[0-9a-zA-ZæøåÆØÅ. ]+"))
			b = false;
		return b;
	}

	protected boolean validerPostnummer(String s) {
		boolean b = true;
		if (s.length() != 4)
			b = false;
		else if (!s.matches("[0-9]+"))
			b = false;
		return b;
	}

	protected boolean validerTelefon(String s) {
		boolean b = true;
		if (s.length() != 8)
			b = false;
		else if (!s.matches("[0-9]+"))
			b = false;

		return b;
	}

	protected boolean validerBy(String s) {
		boolean b = true;
		if (s.length() == 0)
			b = false;

		return b;
	}

	protected boolean validerTekstfelter(String telefon, String cpr, String navn,
			String adresse, String postnummer, String bynavn) {
		StringBuilder sb = new StringBuilder();
		boolean b = true;
		if (!validerTelefon(telefon)) {
			sb.append("- Telefonnummer må kun indeholde tallene 0-9, og skal være 8 tegn \n");
			b = false;
		}
		if (!validerCPR(cpr)) {
			sb.append("- CPR-nummer må kun indeholde tallene 0-9, og skal være 10 tegn \n");
			b = false;
		}
		if (!validerNavn(navn)) {
			sb.append("- Kundenavn må kun indeholde a-å, og må ikke være tom \n");
			b = false;
		}
		if (!validerAdresse(adresse)) {
			sb.append("- Adresse må kun indeholde a-å og 0-9, og må ikke være tom \n");
			b = false;
		}
		if (!validerPostnummer(postnummer)) {
			sb.append("- Postnummer må kun indeholde tallene 0-9, og skal være 4 tegn \n");
			b = false;
		}else if (!validerBy(bynavn)) {
			sb.append("- Postnummer eksisterer ikke \n");
			b = false;
		}
		if (!b)
			JOptionPane.showMessageDialog(null, sb.toString(), "Fejl!",
					JOptionPane.ERROR_MESSAGE);
		return b;
	}

	/*
	 * Update
	 */

	@Override
	public void update(Object source, String s) {
		if (source instanceof KundeController) {
			if (s.equals("opretKunde")) {
				JOptionPane.showMessageDialog(null, "Kunde er oprettet!","Success!", JOptionPane.INFORMATION_MESSAGE);
				disableTekstfelter();
				findKunde();
			} else if (s.equals("hentKunde")) {
				tfCPR.setText("**********");
				tfNavn.setText(kController.getKunde().getKundenavn());
				tfAdresse.setText(kController.getKunde().getAdresse());
				tfPostnummer.setText(kController.getKunde().getPostnummer());
				try {
					pController.hentPostnummer(kController.getKunde().getPostnummer());
					tfBy.setText(pController.getPostnummer().getBynavn());
					disableTekstfelter();
					blackBorders();
				} catch (SQLException | PostnummerDoesNotExist e) {
					e.printStackTrace();
				}
			}
		} else if (source instanceof PostnummerController) {
			if (pController.getPostnummer() != null) {
				tfBy.setText(pController.getPostnummer().getBynavn());
				if (!validerBy(tfBy.getText())) {
					tfPostnummer.setBorder(redBorder);
				}else{
					tfPostnummer.setBorder(greenBorder);
				}
			} else {
				tfBy.setText("");
				tfPostnummer.setBorder(redBorder);
			}
		}
	}
}
