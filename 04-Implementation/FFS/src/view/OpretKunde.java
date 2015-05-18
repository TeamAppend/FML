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
<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 0c53a704a3e491ec2274661a5e6b5e471cbfeb8e
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import domain.Kunde;
import domain.Postnummer;
import exceptions.CPRAllreadyExists;
import exceptions.KundeAllreadyExists;
import exceptions.PostnummerDoesNotExist;
import logik.FFSControllerImpl;
import logik.FFSObserver;
import logik.ValiderKundeLogik;
import logik.ValiderKundeLogikImpl;

<<<<<<< HEAD
public class OpretKunde extends JPanel implements FFSObserver, ActionListener,
		KeyListener, FocusListener {
=======
public class OpretKunde extends JPanel implements FFSObserver, ActionListener, KeyListener,
		FocusListener {
>>>>>>> 0c53a704a3e491ec2274661a5e6b5e471cbfeb8e
	private JTextField tfCPR = new JTextField(10), tfNavn = new JTextField(10),
			tfAdresse = new JTextField(10), tfPostnummer = new JTextField(10),
			tfBy = new JTextField(10), tfTelefon = new JTextField(10);
	private JButton btnFindKunde = new JButton("Find Kunde"),
			btnOpretKunde = new JButton("Opret Kunde");
	private Border blackBorder = new LineBorder(Color.BLACK),
			redBorder = new LineBorder(Color.RED),
			greenBorder = new LineBorder(Color.GREEN);
	private ValiderKundeLogik vkl = new ValiderKundeLogikImpl();
	private GridBagLayout layout;
	private FFSControllerImpl FFSc = new FFSControllerImpl();

	public OpretKunde() {
		FFSc.tilmeldObserver(this);
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
<<<<<<< HEAD

	public boolean telefonNrEksistererIkke(String s) throws SQLException {
		KundeLogik kl = new KundeLogikImpl();
		List<KundeImpl> list = kl.listKunde(s);
		return list.isEmpty();
	}

	public void hentKunde(String s) throws SQLException, PostnummerDoesNotExist {
		Kunde kunde = new KundeImpl();
		KundeLogik kl = new KundeLogikImpl();
		List<KundeImpl> list = kl.listKunde(s);
		kunde = list.get(0);
		tfNavn.setText(kunde.getKundenavn());
		tfAdresse.setText(kunde.getAdresse());
		tfPostnummer.setText(kunde.getPostnummer());

		PostnummerLogik pda = new PostnummerLogikImpl();
		PostnummerImpl postnummer = pda.listPostnummer(kunde.getPostnummer());
		String bynavn = postnummer.getBynavn();
		tfBy.setText(bynavn);

		tfCPR.setText("**********");

	}

	public void findKunde() {
		if (vkl.validerTelefon(tfTelefon.getText())) {
			btnFindKunde.setEnabled(false);
			try {
				if (telefonNrEksistererIkke(tfTelefon.getText())) {
					enableTekstfelter();
					blackBorders();
				} else {
					hentKunde(tfTelefon.getText());
=======
	
	public void blackBorders() {
		tfAdresse.setBorder(blackBorder);
		tfTelefon.setBorder(blackBorder);
		tfCPR.setBorder(blackBorder);
		tfNavn.setBorder(blackBorder);
		tfPostnummer.setBorder(blackBorder);
	}

	public void findKunde() {
		ValiderKundeLogik vkl = new ValiderKundeLogikImpl();
		if (vkl.validerTelefon(tfTelefon.getText())) {   //Overvej at lægge alt validering ind i view
			btnFindKunde.setEnabled(false);
			try {
				if (FFSc.telefonNrEksistererIkke(tfTelefon.getText())) {
					enableTekstfelter();
					blackBorders();
				} else {
					Kunde kunde = FFSc.hentKunde(tfTelefon.getText());
					Postnummer postnummer = FFSc.hentPostnummer(kunde.getPostnummer());
					tfCPR.setText("**********");
					tfNavn.setText(kunde.getKundenavn());
					tfAdresse.setText(kunde.getAdresse());
					tfPostnummer.setText(postnummer.getPostnummer());
					tfBy.setText(postnummer.getBynavn());
>>>>>>> 0c53a704a3e491ec2274661a5e6b5e471cbfeb8e
					disableTekstfelter();
					blackBorders();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (PostnummerDoesNotExist e1) {
				e1.printStackTrace();
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

<<<<<<< HEAD
	public void blackBorders() {
		tfAdresse.setBorder(blackBorder);
		tfTelefon.setBorder(blackBorder);
		tfCPR.setBorder(blackBorder);
		tfNavn.setBorder(blackBorder);
		tfPostnummer.setBorder(blackBorder);
=======
	public void kundeSuccessfuldtOprettet() {
		JOptionPane.showMessageDialog(null, "Kunde er oprettet!", "Success!",
				JOptionPane.INFORMATION_MESSAGE);
		disableTekstfelter();
>>>>>>> 0c53a704a3e491ec2274661a5e6b5e471cbfeb8e
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
				FFSc.opretKunde(telefon, cpr, navn, adresse, postnummer);
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
<<<<<<< HEAD
		if (source.equals(tfPostnummer)) {
			if (tfPostnummer.getText().length() == 4) {
				PostnummerLogik pl = new PostnummerLogikImpl();
				try {
					Postnummer pn = pl.listPostnummer(tfPostnummer.getText());
=======
		ValiderKundeLogik vkl = new ValiderKundeLogikImpl();
		if (source.equals(tfPostnummer)) {
			if (tfPostnummer.getText().length() == 4) {    //overvej at bruge validerings metode
				try {
					Postnummer pn = FFSc.hentPostnummer(tfPostnummer.getText());
>>>>>>> 0c53a704a3e491ec2274661a5e6b5e471cbfeb8e
					if (pn != null) {
						tfBy.setText(pn.getBynavn());
						tfPostnummer.setBorder(greenBorder);
					} else {
						tfBy.setText("");
						tfPostnummer.setBorder(redBorder);
					}
				} catch (SQLException | PostnummerDoesNotExist e) {
					e.printStackTrace();
				}

			} else
				tfBy.setText("");
		} else if (source.equals(tfTelefon)) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER
<<<<<<< HEAD
					&& vkl.validerTelefon(tfTelefon.getText())) {
				findKunde();
			} else {
				if (!vkl.validerTelefon(tfTelefon.getText()))
=======
					&& vkl.validerTelefon(tfTelefon.getText())) {  //validering
				findKunde();
			} else {
				if (!vkl.validerTelefon(tfTelefon.getText()))   //validering
>>>>>>> 0c53a704a3e491ec2274661a5e6b5e471cbfeb8e
					btnFindKunde.setEnabled(false);
				else
					btnFindKunde.setEnabled(true);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		/*JTextField source = (JTextField) arg0.getComponent();
		if (source.getBorder() == redBorder) {
			source.setBorder(blackBorder);
		}*/
	}

	@Override
<<<<<<< HEAD
	public void focusLost(FocusEvent arg0) {
		Object source = arg0.getSource();
		updateBorders(source);
=======
	public void focusLost(FocusEvent arg0) {				//MERE FUCKING VALIDERING
		JTextField source = (JTextField) arg0.getComponent();
		ValiderKundeLogik vkl = new ValiderKundeLogikImpl();
		if (source.equals(tfAdresse)) {
			if (!vkl.validerAdresse(tfAdresse.getText())) {
				tfAdresse.setBorder(redBorder);
			} else
				tfAdresse.setBorder(greenBorder);
		} else if (source.equals(tfCPR)) {
			if (!vkl.validerCPR(tfCPR.getText())) {
				tfCPR.setBorder(redBorder);
			} else
				tfCPR.setBorder(greenBorder);
		} else if (source.equals(tfNavn)) {
			if (!vkl.validerNavn(tfNavn.getText())) {
				tfNavn.setBorder(redBorder);
			} else
				tfNavn.setBorder(greenBorder);
		} else if (source.equals(tfTelefon)) {
			if (!vkl.validerTelefon(tfTelefon.getText())) {
				tfTelefon.setBorder(redBorder);
			} else
				tfTelefon.setBorder(greenBorder);
		} else if (source.equals(tfPostnummer)) {
			if (!vkl.validerPostnr(tfPostnummer.getText())) {
				tfPostnummer.setBorder(redBorder);
			} else
				tfPostnummer.setBorder(greenBorder);
		}
>>>>>>> 0c53a704a3e491ec2274661a5e6b5e471cbfeb8e
	}

	@Override
	public void update() {
<<<<<<< HEAD
	
	}
	
	public void updateBorders(Object source){
		if (!vkl.validerAdresse(tfAdresse.getText())) {
			tfAdresse.setBorder(redBorder);
		} else
			tfAdresse.setBorder(greenBorder);
		if (!vkl.validerCPR(tfCPR.getText())) {
			tfCPR.setBorder(redBorder);
		} else
			tfCPR.setBorder(greenBorder);
		if (!vkl.validerNavn(tfNavn.getText())) {
			tfNavn.setBorder(redBorder);
		} else
			tfNavn.setBorder(greenBorder);
		if (!vkl.validerTelefon(tfTelefon.getText())) {
			tfTelefon.setBorder(redBorder);
		} else
			tfTelefon.setBorder(greenBorder);
		if (!vkl.validerPostnr(tfPostnummer.getText())) {
			tfPostnummer.setBorder(redBorder);
		} else
			tfPostnummer.setBorder(greenBorder);
=======
		kundeSuccessfuldtOprettet();
		blackBorders();
		try {
			FFSc.hentKunde(tfTelefon.getText());
		} catch (SQLException | PostnummerDoesNotExist e) {
			e.printStackTrace();
		}
>>>>>>> 0c53a704a3e491ec2274661a5e6b5e471cbfeb8e
	}
}
