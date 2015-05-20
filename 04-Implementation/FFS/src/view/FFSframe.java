package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;

import logik.FFSController;
import logik.FFSObserver;

public class FFSframe extends JFrame implements FFSObserver {
	private OpretKunde opretKundePanel;
	private OpretLånetilbud opretLånetilbudPanel;
	private FFSController fController = new FFSController();

	private GridBagLayout layout;

	public FFSframe() {
		fController.tilmeldObserver(this);
		layout = new GridBagLayout();
		getContentPane().setLayout(layout);

		GridBagConstraints con = new GridBagConstraints();
		Insets ins = new Insets(25, 25, 25, 25); // margin rund om objecterne

		opretKundePanel = new OpretKunde();
		opretLånetilbudPanel = new OpretLånetilbud();

		con = createGBC(0, 0, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.NORTHWEST;
		add(opretKundePanel, con);

		con = createGBC(1, 0, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.NORTHWEST;
		add(opretLånetilbudPanel, con);

		// frame properties
		setTitle("Ferrari Financing System");
		setSize(1000, 400);
		setLocationRelativeTo(null);
		setVisible(true);
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

	@Override
	public void update(Object source, String s) {
		
	}

}
