package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

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
		add(new JLabel("Tlf"), con);
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

		con = createGBC(0, 2, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Navn:"), con);
		con = createGBC(1, 2, 1, 1);
		con.insets = ins;
		add(navn, con);

		con = createGBC(0, 3, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Adresse:"), con);
		con = createGBC(1, 3, 1, 1);
		con.insets = ins;
		add(adresse, con);

		con = createGBC(0, 4, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("PostNr:"), con);
		con = createGBC(1, 4, 1, 1);
		con.insets = ins;
		add(postnr, con);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == findKunde){
			/*if (telefonNrEksisterer())
				disableTekstfelter();
			else 
				enableTekstfelter();*/
		}else if(source.equals(opretKunde)){
			validerTekstfelter();
		}

	}

}
