package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import domain.KundeImpl;
import logik.KundeLogik;
import logik.KundeLogikImpl;
import logik.ValiderKundeLogik;
import logik.ValiderKundeLogikImpl;

public class OpretKunde extends JPanel implements ActionListener {
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

	}
	
	private void enableTekstfelter() {
		cpr.setEnabled(true);
		navn.setEnabled(true);
		adresse.setEnabled(true);
		postnr.setEnabled(true);

	}
	
	private void validerTekstfelter(){
		ValiderKundeLogik vkl = new ValiderKundeLogikImpl();
		StringBuilder sb = new StringBuilder();
		boolean b = false;
		if(!vkl.validerTelefon(telefon.getText())){
			sb.append("- Telefonnummer må kun indeholde tallene 0-9, og skal være 8 tegn \n");
			telefon.setBorder(redBorder);
			b = true;
		}if(!vkl.validerCPR(cpr.getText())){
			sb.append("- CPR-nummer må kun indeholde tallene 0-9, og skal være 10 tegn \n");
			b = true;
		}if(!vkl.validerNavn(navn.getText())){
			sb.append("- Kundenavn må kun indeholde a-å, og må ikke være tom \n");
			b = true;
		}if(!vkl.validerAdresse(adresse.getText())){
			sb.append("- Adresse må kun indeholde a-å og 0-9, og må ikke være tom \n");
			b = true;
		}if(!vkl.validerPostnr(postnr.getText())){
			sb.append("- Postnummer må kun indeholde tallene 0-9, og skal være 4 tegn \n");
			b = true;
		}
		if(b)
			JOptionPane.showMessageDialog(null,	sb.toString(), "Fejl!", JOptionPane.ERROR_MESSAGE);
		else{
			JOptionPane.showMessageDialog(null,	"Kunde er oprettet!", "Success!", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public boolean telefonNrEksistererIkke(String s) throws SQLException{
		KundeLogik kl = new KundeLogikImpl();
		List<KundeImpl> list = kl.listKunde(s);
		return list.isEmpty();
	}
	
	public void hentKunde(){
		//hent kunde
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == findKunde){
			 try {
				if(telefonNrEksistererIkke(telefon.getText()))
					 enableTekstfelter();
				 else{ 
					 hentKunde();
					 disableTekstfelter();
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(source.equals(opretKunde)){
			validerTekstfelter();
		}

	}

}
