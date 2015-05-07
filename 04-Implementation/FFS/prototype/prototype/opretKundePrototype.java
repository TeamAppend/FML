package prototype;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

import domain.Kunde;
import exceptions.KundeAllreadyExists;
import logik.KundeLogik;

public class opretKundePrototype extends JFrame {

	private JPanel contentPane;
	private JTextField tfNavn;
	private JTextField tfCPR;
	private JTextField tfAdresse;
	private JTextField tfPostnummer;
	private JTextField tfTelefon;
	private JTextField tfBy;
	private JButton btnOpret;
	private JTextPane tfKommentar;
	private Controller controller = new Controller();

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
		tfCPR.setBounds(122, 39, 98, 22);
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
		
		tfTelefon = new JTextField();
		tfTelefon.setText("12345678");
		tfTelefon.setColumns(10);
		tfTelefon.setBounds(122, 126, 79, 22);
		contentPane.add(tfTelefon);
		
		tfBy = new JTextField();
		tfBy.setText("Herning");
		tfBy.setColumns(10);
		tfBy.setBounds(197, 97, 181, 22);
		contentPane.add(tfBy);
		
		tfKommentar = new JTextPane();
		tfKommentar.setBounds(122, 158, 256, 85);
		contentPane.add(tfKommentar);
		
		btnOpret = new JButton("Opret");
		btnOpret.setBounds(288, 256, 97, 25);
		contentPane.add(btnOpret);
		btnOpret.addActionListener(controller);
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
				String by = tfBy.getText(); //Lav så den henter fra postnummer table
				String telefon = tfTelefon.getText();
				String kommentar = tfKommentar.getText();
				if(navn.length() > 0 && CPR.length() == 10 && adresse.length() > 0 && postnummer.length() == 4 && by.length() >0 && telefon.length() > 0){
					Kunde kunde = new Kunde();
					kunde.setKundenavn(navn);
					kunde.setAdresse(adresse);
					kunde.setPostnummer(postnummer);
					kunde.setTelefon(telefon);
					kunde.setKommentar(kommentar);
					KundeLogik kl = new KundeLogik();
					try {
						kl.createKunde(kunde);
						JOptionPane.showMessageDialog(null,	"Kunde er oprettet uden fejl!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException | KundeAllreadyExists e1) {
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null,	"Noget er ikke udfyldt", "Fejl!", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
	}
}

