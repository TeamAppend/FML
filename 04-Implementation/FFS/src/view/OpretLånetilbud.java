package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

public class OpretLånetilbud extends JPanel implements FFSObserver {
	private JTextField tilbagebetalingsperiode = new JTextField(10);
	private JTextField udbetaling = new JTextField(10);
	private JComboBox<String> cbModelnavn = new JComboBox<>();
	private JComboBox<String> cbSælgernavn = new JComboBox<>();
	private JButton beregnLånetilbud = new JButton("Beregn lånetilbud");
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
		add(tilbagebetalingsperiode, con);

		con = createGBC(0, 1, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Udbetaling: "), con);
		con = createGBC(1, 1, 1, 1);
		con.insets = ins;
		add(udbetaling, con);

		con = createGBC(0, 2, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Model: "), con);
		con = createGBC(1, 2, 1, 1);
		con.insets = ins;
		add(cbModelnavn, con);
		
		con = createGBC(0, 3, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Sælger: "), con);
		con = createGBC(1, 3, 1, 1);
		con.insets = ins;
		add(cbSælgernavn, con);
		
		con = createGBC(2, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(beregnLånetilbud, con);
		
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
	public void update(Object source, String s) {
		if(source instanceof SælgerController){
			List<Sælger> list = sController.getListSælger();
			for(int i = 0; i<list.size(); i++){
				cbSælgernavn.addItem(list.get(i).getSælgerNavn());
			}
		}else if(source instanceof BilController){
			List<Bil> list = bController.getListBil();
			for(int i = 0; i<list.size(); i++){
				cbModelnavn.addItem(list.get(i).getModelnavn());
			}
		}
	}
}
