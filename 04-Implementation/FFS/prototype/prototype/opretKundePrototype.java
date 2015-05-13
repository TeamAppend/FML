package prototype;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

import domain.CPRnummer;
import domain.CPRnummerImpl;
import domain.Kunde;
import domain.KundeImpl;
import domain.Postnummer;
import exceptions.CPRAllreadyExists;
import exceptions.KundeAllreadyExists;
import exceptions.PostnummerDoesNotExist;
import logik.CPRLogik;
import logik.CPRLogikImpl;
import logik.KundeLogik;
import logik.KundeLogikImpl;
import logik.PostnummerLogik;

public class opretKundePrototype extends JFrame {
	private static final long serialVersionUID = -6500507929852243379L;
	private JPanel contentPane;
	private JTextField tfNavn, tfCPR, tfAdresse, tfPostnummer, tfTelefon, tfBy;
	private JButton btnOpret, btnFindBy, btnOpretCpr;
	private Controller controller = new Controller();
	private KeyController kController = new KeyController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					opretKundePrototype frame = new opretKundePrototype();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public opretKundePrototype() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKundenavn = new JLabel("Navn");
		lblKundenavn.setBounds(12, 13, 98, 16);
		contentPane.add(lblKundenavn);
		
		JLabel lblCprnummer = new JLabel("CPR-Nummer");
		lblCprnummer.setBounds(12, 42, 98, 16);
		contentPane.add(lblCprnummer);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(12, 71, 98, 16);
		contentPane.add(lblAdresse);
		
		JLabel lblPostnummer = new JLabel("Postnummer");
		lblPostnummer.setBounds(12, 100, 98, 16);
		contentPane.add(lblPostnummer);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(12, 129, 98, 16);
		contentPane.add(lblTelefon);
		
		JLabel lblKommentar = new JLabel("Kommentar");
		lblKommentar.setBounds(12, 158, 98, 16);
		contentPane.add(lblKommentar);
		
		tfNavn = new JTextField();
		tfNavn.setText("Test");
		tfNavn.setBounds(122, 10, 256, 22);
		contentPane.add(tfNavn);
		tfNavn.setColumns(10);
		
		tfCPR = new JTextField();
		tfCPR.setText("1234567890");
		tfCPR.setColumns(10);
		tfCPR.setBounds(122, 39, 78, 22);
		contentPane.add(tfCPR);
		
		tfAdresse = new JTextField();
		tfAdresse.setText("Gadevej 1");
		tfAdresse.setColumns(10);
		tfAdresse.setBounds(122, 68, 256, 22);
		contentPane.add(tfAdresse);
		
		tfPostnummer = new JTextField();
		tfPostnummer.setText("7400");
		tfPostnummer.setColumns(10);
		tfPostnummer.setBounds(122, 97, 63, 22);
		contentPane.add(tfPostnummer);
		tfPostnummer.addKeyListener(kController);
		
		tfTelefon = new JTextField();
		tfTelefon.setText("+4512345678");
		tfTelefon.setColumns(10);
		tfTelefon.setBounds(122, 126, 89, 22);
		contentPane.add(tfTelefon);
		
		tfBy = new JTextField();
		tfBy.setEditable(false);
		tfBy.setColumns(10);
		tfBy.setBounds(197, 97, 181, 22);
		contentPane.add(tfBy);
		
		
		
		btnOpret = new JButton("Opret");
		btnOpret.setBounds(288, 256, 97, 25);
		contentPane.add(btnOpret);
		btnOpret.addActionListener(controller);
		
		btnFindBy = new JButton("Find by");
		btnFindBy.setBounds(181, 257, 97, 25);
		contentPane.add(btnFindBy);
		btnFindBy.addActionListener(controller);
		
		btnOpretCpr = new JButton("Opret CPR");
		btnOpretCpr.setBounds(72, 256, 97, 25);
		contentPane.add(btnOpretCpr);
		btnOpretCpr.addActionListener(controller);
	}
	
	public class Controller implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			if(source.equals(btnOpret)){
				String navn = tfNavn.getText();
				String CPR = tfCPR.getText();
				String adresse = tfAdresse.getText();
				String postnummer = tfPostnummer.getText();
				String by = tfBy.getText();
				String telefon = tfTelefon.getText();
				if(navn.length() > 0 && CPR.length() == 10 && adresse.length() > 0 && postnummer.length() == 4 && by.length() >0 && telefon.length() > 0){
					Kunde kunde = new KundeImpl();
					kunde.setKundenavn(navn);
					kunde.setAdresse(adresse);
					kunde.setPostnummer(postnummer);
					kunde.setTelefon(telefon);
					KundeLogik kl = new KundeLogikImpl();
					
					
					PostnummerLogik pl = new logik.PostnummerLogikImpl();
					try {
						String s = pl.listPostnummer(postnummer).getBynavn();
						tfBy.setText(s);
					} catch (SQLException e2) {
						e2.printStackTrace();
					} catch (PostnummerDoesNotExist e1) {
						e1.printStackTrace();
					}
					try {
						kl.createKunde(kunde);
						JOptionPane.showMessageDialog(null,	"Kunde er oprettet!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException | KundeAllreadyExists e1) {
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null,	"Noget er ikke udfyldt", "Fejl!", JOptionPane.ERROR_MESSAGE);
				}
			}else if(source.equals(btnFindBy)){
				String postnummer = tfPostnummer.getText();
				PostnummerLogik pl = new logik.PostnummerLogikImpl();
				try {
					Postnummer pn = pl.listPostnummer(postnummer);
					if(pn == null){
						tfBy.setText("Postnummer findes ikke");
					}else{
						String s = pn.getBynavn();
						tfBy.setText(s);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				} catch (PostnummerDoesNotExist e1) {
					JOptionPane.showMessageDialog(null,	"Postnummer findes ikke", "Fejl!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}else if(source.equals(btnOpretCpr)){
				CPRLogik cl = new CPRLogikImpl();
				CPRnummer cpr = new CPRnummerImpl();
				cpr.setCPRnummer(tfCPR.getText());
				try {
					int i = cl.findUniqueCPR(tfCPR.getText());
					if(i == 0){
						try {
							cl.createCPR(cpr);
							JOptionPane.showMessageDialog(null,	"CPR-nummer er oprettet!", "Success!", JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException | CPRAllreadyExists e1) {
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null,	"CPR-nummer findes allerede", "Fejl!", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public class KeyController implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if(tfPostnummer.getText().length() == 4){			
				String postnummer = tfPostnummer.getText();
				PostnummerLogik pl = new logik.PostnummerLogikImpl();
				try {
					Postnummer pn = pl.listPostnummer(postnummer);
					if(pn == null){
						tfBy.setText("Postnummer findes ikke");
					}else{
						String s = pn.getBynavn();
						tfBy.setText(s);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				} catch (PostnummerDoesNotExist e1) {
					JOptionPane.showMessageDialog(null,	"Postnummer findes ikke", "Fejl!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	}
}