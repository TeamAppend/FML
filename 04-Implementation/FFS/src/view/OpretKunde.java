package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

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

		con = createGBC(2, 6, 1, 1);
		con.insets = ins;
		add(opretKunde, con);

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
		navn.setEnabled(false);
		adresse.setEnabled(false);
		postnr.setEnabled(false);
		by.setEnabled(false);

	}
	
	private void enableTekstfelter() {
		navn.setEnabled(true);
		adresse.setEnabled(true);
		postnr.setEnabled(true);
		by.setEnabled(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == findKunde)
//			if (telefonNrEksisterer())
				disableTekstfelter();
			else 
				enableTekstfelter();

	}

}
