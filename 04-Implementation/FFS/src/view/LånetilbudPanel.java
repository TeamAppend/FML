package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logik.BilController;
import logik.FFSObserver;
import logik.FormatterLogik;
import logik.KundeController;
import logik.LånetilbudController;
import logik.SælgerController;
import domain.Bil;
import domain.Sælger;

public class LånetilbudPanel extends JPanel implements FFSObserver,
		ActionListener {
	private JTextField tfTilbagebetalingsperiode = new JTextField(13);
	private JTextField tfUdbetaling = new JTextField(13);
	private JTextField tfPris = new JTextField(13);
	private JTextField tfMaksLånebeløb = new JTextField(13);
	private JComboBox<String> cbModelnavn = new JComboBox<>();
	private JComboBox<String> cbSælgernavn = new JComboBox<>();
	private JButton btnBeregnLånetilbud = new JButton("Beregn lånetilbud");
	private SælgerController sController = SælgerController.instance();
	private BilController bController = BilController.instance();
	private LånetilbudController lController = LånetilbudController.instance();
	private KundeController kController = KundeController.instance();
	private JLabel loadingIcon = new JLabel();

	private GridBagLayout layout;

	public LånetilbudPanel() {
		sController.tilmeldObserver(this);
		bController.tilmeldObserver(this);
		lController.tilmeldObserver(this);
		kController.tilmeldObserver(this);
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
		tfMaksLånebeløb.setEnabled(false);
		add(tfMaksLånebeløb, con);
		con = createGBC(2, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("kr. "), con);

		
		con = createGBC(1, 6, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.EAST;
		Image image = new ImageIcon(this.getClass().getResource("/loadingicon.gif")).getImage();
		loadingIcon.setIcon(new ImageIcon(image));
		loadingIcon.setVisible(false);
		add(loadingIcon, con);
		
		con = createGBC(2, 6, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(btnBeregnLånetilbud, con);
		btnBeregnLånetilbud.addActionListener(this);

		sController.fillSælgerComboBox();
		bController.fillBilComboBox();
		disableTekstFelter();

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
	

	private void disableTekstFelter() {
		tfTilbagebetalingsperiode.setEnabled(false);
		tfUdbetaling.setEnabled(false);
		cbModelnavn.setEnabled(false);
		cbSælgernavn.setEnabled(false);
		btnBeregnLånetilbud.setEnabled(false);
	}

	private void enableTekstFelter() {
		tfTilbagebetalingsperiode.setEnabled(true);
		tfUdbetaling.setEnabled(true);
		cbModelnavn.setEnabled(true);
		cbSælgernavn.setEnabled(true);
		btnBeregnLånetilbud.setEnabled(true);
	}
	
	private void resetFelter(){
		tfTilbagebetalingsperiode.setText("");
		tfUdbetaling.setText("");
		cbModelnavn.setSelectedIndex(0);
		tfPris.setText("");
		cbSælgernavn.setSelectedIndex(0);
		tfMaksLånebeløb.setText("");
	}
	
	protected boolean validerTilbagebetaling(String s) {
		boolean b = true;
		if (s.length() == 0)
			b = false;
		else if(Integer.parseInt(s) == 0)
			b = false;
		else if (!s.matches("[0-9]+"))
			b = false;

		return b;
	}
	
	protected boolean validerUdbetaling(String s) {
		boolean b = true;
		if (s.length() == 0)
			b = false;
		else if(Integer.parseInt(s) == 0)
			b = false;
		else if (!s.matches("[0-9]+"))
			b = false;

		return b;
	}
	
	protected boolean validerUdbetalingPris(String udbetaling, String pris){
		boolean b = true;
		double dUdbetaling = Double.parseDouble(udbetaling);
		double dPris = Double.parseDouble(pris);
		if (dPris <= dUdbetaling)
			b = false;

		return b;
	}
	
	protected boolean validerUdbetalingProcent(String udbetaling, String pris){
		boolean b = true;
		double dUdbetaling = Double.parseDouble(udbetaling);
		double dPris = Double.parseDouble(pris);
		if (dPris/5 >= dUdbetaling)
			b = false;

		return b;
	}
	
	protected boolean validerPris(String s) {
		boolean b = true;
		if (s.length() == 0)
			b = false;
		else if (!s.matches("[0-9.]+"))
			b = false;

		return b;
	}
	
	protected boolean validerLånebeløbMaks(String udbetaling, String pris, String maksLåneBeløb){
		boolean b = true;
		double dUdbetaling = Double.parseDouble(udbetaling);
		double dPris = Double.parseDouble(pris);
		double dMaksLånebeløb = Double.parseDouble(maksLåneBeløb);
		double lånebeløb = dPris - dUdbetaling;
		if (lånebeløb >= 1000000 && dMaksLånebeløb != 0)
			b = false;

		return b;
	}
	
	protected boolean validerLånebeløb(String s) {
		boolean b = true;
		if (s.length() == 0)
			b = false;
		else if (!s.matches("[0-9.]+"))
			b = false;

		return b;
	}
	
	protected boolean validerTekstfelter(String tilbagebetaling, String udbetaling, String pris,
			String maksLåneBeløb) {
		StringBuilder sb = new StringBuilder();
		boolean b = true;
		if (!validerTilbagebetaling(tilbagebetaling)) {
			sb.append("- Tilbagebetalingsperiode må kun indeholde tallene 0-9, og må ikke være tom \n");
			b = false;
		}
		if (!validerUdbetaling(udbetaling)) {
			sb.append("- Udbetaling må kun indeholde tallene 0-9, og må ikke være tom \n");
			b = false;
		}else if (!validerUdbetalingPris(udbetaling, pris)) {
			sb.append("- Udbetaling må ikke være højere end bilens pris \n");
			b = false;
		}else if (!validerUdbetalingProcent(udbetaling, pris)){
			sb.append("- Udbetalingen er under 20% af bilens pris \n");
			b = false;
		}
		if (!validerPris(pris)) {
			sb.append("- Modelnavn er ikke valgt \n");
			b = false;
		}
		if (!validerLånebeløb(maksLåneBeløb)) {
			sb.append("- Sælger er ikke valgt \n");
			b = false;
		}else if (!validerLånebeløbMaks(udbetaling, pris, maksLåneBeløb)) {
			sb.append("- Sælger kan ikke godkende lånebeløb over 1.000.000 kr. \n");
			b = false;
		}
		if (!b)
			JOptionPane.showMessageDialog(null, sb.toString(), "Fejl!",
					JOptionPane.ERROR_MESSAGE);
		return b;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (source.equals(cbModelnavn)) {
			if (cbModelnavn.getSelectedIndex() != 0) {
				String modelNavn = (String) cbModelnavn.getSelectedItem();
				bController.setBil(modelNavn);
				bController.setBilPris(modelNavn);
			}
		} else if (source.equals(cbSælgernavn)) {
			if (cbSælgernavn.getSelectedIndex() != 0) {
				String sælgernavn = (String) cbSælgernavn.getSelectedItem();
				sController.setSælger(sælgernavn);
				sController.setMaksLånebeløb(sælgernavn);
			}
		} else if (source.equals(btnBeregnLånetilbud)) {
			String tilbagebetaling = tfTilbagebetalingsperiode.getText();
			String udbetaling = tfUdbetaling.getText();
			String pris = tfPris.getText().replace(".", "");
			String maksLånebeløb = tfMaksLånebeløb.getText().replace(".", "");
			if(validerTekstfelter(tilbagebetaling, udbetaling, pris, maksLånebeløb)){
				int kunde_id = kController.getKunde().getKunde_id();
				lController.beregnLånetilbud(kunde_id);
				loadingIcon.setVisible(true);
				btnBeregnLånetilbud.setEnabled(false);
			}
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
				tfMaksLånebeløb.setText("" + FormatterLogik.dotSeperator(pris));
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
				tfPris.setText("" + FormatterLogik.dotSeperator(pris));
			}
		} else if (source instanceof LånetilbudController) {
			if ((s.equals("RenteSats") || s.equals("Kreditværdighed")) && lController.beggeFundet()) {
				if(lController.getkvAcceptabel()){
					int tilbageBetaling = Integer.parseInt(tfTilbagebetalingsperiode.getText());
					double udbetaling = Double.parseDouble(tfUdbetaling.getText());
					int bil_id = bController.getBil().getBil_id();
					int sælger_id = sController.getSælger().getSælger_id();
					double pris = bController.getBil().getPris();
					lController.opretLånetilbud(tilbageBetaling, udbetaling,bil_id, sælger_id, pris);
				}else{
					JOptionPane.showMessageDialog(null, "Kundens kreditværdighed er ikke tilstrækkelig","Success!", JOptionPane.ERROR_MESSAGE);
					loadingIcon.setVisible(false);
					btnBeregnLånetilbud.setEnabled(true);
				}
			}else if(s.equals("opretLånetilbud")){
				loadingIcon.setVisible(false);
				btnBeregnLånetilbud.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Lånetilbud er oprettet!","Success!", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (source instanceof KundeController){
			if (kController.getKundeFundet()){
				enableTekstFelter();
			}else{
				disableTekstFelter();
				resetFelter();
			}
		}
	}

}