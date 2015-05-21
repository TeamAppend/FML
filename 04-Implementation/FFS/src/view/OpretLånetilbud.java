package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logik.BilController;
import logik.FFSObserver;
import logik.LånetilbudController;
import logik.SælgerController;
import domain.Bil;
import domain.Sælger;

public class OpretLånetilbud extends JPanel implements FFSObserver,
		ActionListener {
	private JTextField tfTilbagebetalingsperiode = new JTextField(13);
	private JTextField tfUdbetaling = new JTextField(13);
	private JTextField tfPris = new JTextField(13);
	private JTextField tfLånebeløb = new JTextField(13);
	private JComboBox<String> cbModelnavn = new JComboBox<>();
	private JComboBox<String> cbSælgernavn = new JComboBox<>();
	private JButton btnBeregnLånetilbud = new JButton("Beregn lånetilbud");
	private SælgerController sController = new SælgerController();
	private BilController bController = new BilController();
	private LånetilbudController lController = new LånetilbudController();

	private GridBagLayout layout;

	public OpretLånetilbud() {
		sController.tilmeldObserver(this);
		bController.tilmeldObserver(this);
		lController.tilmeldObserver(this);
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
		add(tfTilbagebetalingsperiode, con);
		con = createGBC(2, 0, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("måneder "), con);

		con = createGBC(0, 1, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Udbetaling: "), con);
		con = createGBC(1, 1, 1, 1);
		con.insets = ins;
		add(tfUdbetaling, con);
		con = createGBC(2, 1, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("kr. "), con);

		con = createGBC(0, 2, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Modelnavn: "), con);
		con = createGBC(1, 2, 1, 1);
		con.insets = ins;
		cbModelnavn.setPreferredSize(new Dimension(150, 25));
		cbModelnavn.addActionListener(this);
		add(cbModelnavn, con);

		con = createGBC(0, 3, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Pris: "), con);
		con = createGBC(1, 3, 1, 1);
		con.insets = ins;
		tfPris.setEnabled(false);
		add(tfPris, con);
		con = createGBC(2, 3, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("kr. "), con);

		con = createGBC(0, 4, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Sælger: "), con);
		con = createGBC(1, 4, 1, 1);
		con.insets = ins;
		cbSælgernavn.setPreferredSize(new Dimension(150, 25));
		cbSælgernavn.addActionListener(this);
		add(cbSælgernavn, con);

		con = createGBC(0, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Maks lånebeløb: "), con);
		con = createGBC(1, 5, 1, 1);
		con.insets = ins;
		tfLånebeløb.setEnabled(false);
		add(tfLånebeløb, con);
		con = createGBC(2, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("kr. "), con);

		con = createGBC(2, 6, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(btnBeregnLånetilbud, con);
		btnBeregnLånetilbud.addActionListener(this);

		sController.fillSælgerComboBox();
		bController.fillBilComboBox();

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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (source.equals(cbModelnavn)) {
			if (cbModelnavn.getSelectedIndex() != 0) {
				String modelNavn = (String) cbModelnavn.getSelectedItem();
				bController.setBilPris(modelNavn);
			}
		} else if (source.equals(cbSælgernavn)) {
			if (cbSælgernavn.getSelectedIndex() != 0) {
				String sælgernavn = (String) cbSælgernavn.getSelectedItem();
				sController.setMaksLånebeløb(sælgernavn);
			}
		} else if(source.equals(btnBeregnLånetilbud)){
			int tilbageBetaling = Integer.parseInt(tfTilbagebetalingsperiode.getText());
			double udbetaling = Double.parseDouble(tfUdbetaling.getText());
			int bil_id = bController.getBil().getBil_id();
			int sælger_id = sController.getSælger().getSælger_id();
			//int kunde_id = kController.getKunde().getKunde_id();
			//ÅOP ((1 + RENTE I DEC) / antal terminer)^antal terminer - 1
			//rentesats
			//kunde_id
			lController.beregnLånetilbud();
		}
	}

	@Override
	public void update(Object source, String s) {
		if (source instanceof SælgerController) {
			if (s.equals("fillSælger")) {
				List<Sælger> list = sController.getListSælger();
				cbSælgernavn.addItem(" ");
				for (int i = 0; i < list.size(); i++) {
					cbSælgernavn.addItem(list.get(i).getSælgerNavn());
				}
			} else if (s.equals("setMaksLånebeløb")) {
				Sælger sælger = sController.getSælger();
				double pris = sælger.getBeløbsGrænse();
				tfLånebeløb.setText("" + pris);
			}
		} else if (source instanceof BilController) {
			if (s.equals("fillBil")) {
				List<Bil> list = bController.getListBil();
				cbModelnavn.addItem(" ");
				for (int i = 0; i < list.size(); i++) {
					cbModelnavn.addItem(list.get(i).getModelnavn());
				}
			} else if (s.equals("setBilPris")) {
				Bil bil = bController.getBil();
				double pris = bil.getPris();
				tfPris.setText("" + pris);
			}
		}
	}

}
