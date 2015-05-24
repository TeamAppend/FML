package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class FFSframe extends JFrame{
	private KundePanel opretKundePanel;
	private LånetilbudPanel opretLånetilbudPanel;
	private TabelPanel tabel;

	private GridBagLayout layout;

	public FFSframe() {
		layout = new GridBagLayout();
		getContentPane().setLayout(layout);

		GridBagConstraints con = new GridBagConstraints();
		Insets ins = new Insets(25, 25, 25, 25); // margin rund om objecterne

		opretKundePanel = new KundePanel();
		opretLånetilbudPanel = new LånetilbudPanel();
		tabel = new TabelPanel();

		con = createGBC(0, 0, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.NORTHWEST;
		add(opretKundePanel, con);

		con = createGBC(1, 0, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.NORTHWEST;
		add(opretLånetilbudPanel, con);
		
		con = createGBC(0, 1, 200, 200);
		con.insets = ins;
		con.anchor = GridBagConstraints.NORTHWEST;
		add(tabel, con);

		// frame properties
		setTitle("Ferrari Financing System");
		setSize(975, 675); 
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

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
		getContentPane().add(component);
	}
}
