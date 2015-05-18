package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class OpretLånetilbud extends JPanel {
	private JTextField tilbagebetalingsperiode = new JTextField(10);
	private JTextField udbetaling = new JTextField(10);
	private JButton beregnLånetilbud = new JButton("Beregn lånetilbud");

	private GridBagLayout layout;

	public OpretLånetilbud() {
		// frame properties
		setVisible(true);

		layout = new GridBagLayout();
		setLayout(layout);

		TitledBorder titled = new TitledBorder("Lånetilbud");
		setBorder(titled);

		GridBagConstraints con = new GridBagConstraints();
		Insets ins = new Insets(5, 5, 5, 5); // margin rund om objecterne

		con = createGBC(0, 0, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Tilbagebetalingsperiode: "), con);
		con = createGBC(1, 0, 1, 1);
		con.insets = ins;
		add(tilbagebetalingsperiode, con);

		con = createGBC(0, 1, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Udbetaling: "), con);
		con = createGBC(1, 1, 1, 1);
		con.insets = ins;
		add(udbetaling, con);

		con = createGBC(2, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(beregnLånetilbud, con);

	}

	private GridBagConstraints createGBC(int x, int y, int width, int height) {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = x;
		gbc.gridy = y;

		gbc.gridwidth = width;
		gbc.gridheight = height;

		return gbc;
	}

	public void add(JComponent component, GridBagConstraints gbc) {
		layout.setConstraints(component, gbc);
		add(component);
	}
	

}
