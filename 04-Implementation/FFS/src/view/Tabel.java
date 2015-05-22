package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import logik.CSV_eksport;
import logik.CSV_eksportImpl;
import logik.FFSObserver;
import logik.FormatterLogik;
import logik.KundeController;
import logik.LånetilbudController;
import logik.LånetilbudLogik;
import logik.LånetilbudLogikImpl;
import domain.Lånetilbud;

public class Tabel extends JPanel implements FFSObserver, ActionListener {

	private GridBagLayout layout;
	private JTable table_1;
	private JScrollPane scrollpane;
	private KundeController kController = KundeController.instance();
	private LånetilbudController lController = LånetilbudController.instance();
	private JButton btnExport = new JButton("Export");

	public Tabel() {
		kController.tilmeldObserver(this);
		lController.tilmeldObserver(this);
		// frame properties
		setVisible(true);
		this.setPreferredSize(new Dimension(850, 260));

		layout = new GridBagLayout();
		setLayout(layout);

		GridBagConstraints con = new GridBagConstraints();
		Insets ins = new Insets(5, 5, 5, 5); // margin rund om objecterne

		TitledBorder title = new TitledBorder("Tidligere lånetilbud");
		setBorder(title);

		/*
		 * con = createGBC(0, 1, 1, 1); con.insets = ins; con.anchor =
		 * GridBagConstraints.SOUTH; add(tfFilNavn, con);
		 */

		con = createGBC(0, 1, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.SOUTHEAST;
		add(btnExport, con);
		btnExport.setEnabled(false);
		btnExport.addActionListener(this);

		visFlere(-1);
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

	public void visFlere(int kunde_id) {
		LånetilbudLogik ll = new LånetilbudLogikImpl();
		try {
			List<Lånetilbud> list = ll.listLånetilbud(kunde_id);
			visTabellen(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String formatTwoDigits(Number n) {
		NumberFormat format = DecimalFormat.getInstance();
		format.setRoundingMode(RoundingMode.FLOOR);
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(2);
		return format.format(n);
	}

	private String dotSeperator(Number n) {

		DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
		symbols.setGroupingSeparator('.');

		DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
		return formatter.format(n);
	}

	public void visTabellen(List<Lånetilbud> låneList) {
		if (this.scrollpane != null) {
			remove(scrollpane);
		}
		// , "Kundenavn", "Modelnavn", "Sælgernavn"
		TableModel datamodel = new AbstractTableModel() {
			String[] columnNames = { "Lånetilbud id", "Rentesats",
					"Tilbagebetalingsperiode", "Udbetaling", "ÅOP",
					"Oprettelsestidspunkt" };

			public String getColumnName(int col) {
				return columnNames[col].toString();
			}

			public int getColumnCount() {
				return 6;
			}

			public int getRowCount() {
				return låneList.size();
			}

			public Object getValueAt(int row, int col) {
				if (col == 0)
					return låneList.get(row).getLånetilbud_id();
				else if (col == 1)
					return FormatterLogik.formatTwoDigits(låneList.get(row).getRentesats()) + " %";
				else if (col == 2)
					return låneList.get(row).getTilbagebetalingsperiode();
				else if (col == 3)
					return FormatterLogik.dotSeperator(låneList.get(row).getUdbetaling()) + " Kr.";
				else if (col == 4)
					return FormatterLogik.formatTwoDigits(låneList.get(row).getÅOP()) + " %";
				else if (col == 5)
					return låneList.get(row).getOprettelsestidspunkt();
				else
					return null;
			}
		};

		table_1 = new JTable(datamodel);
		table_1.setBounds(0, 0, 100, 100);
		scrollpane = new JScrollPane(table_1);
		scrollpane.setPreferredSize(new Dimension(840, 200));
		add(scrollpane);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(120);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(170);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(121);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(106);
		table_1.getColumnModel().getColumn(5).setMinWidth(220);

		table_1.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {
							boolean rowsAreSelected = table_1
									.getSelectedRowCount() > 0;
							btnExport.setEnabled(rowsAreSelected);
						}
					}
				});

		this.setVisible(true);
		repaint();
		revalidate();
	}

	public void resetTable() {
		table_1 = new JTable();
		table_1.setBounds(0, 0, 100, 100);
		scrollpane = new JScrollPane(table_1);
		scrollpane.setPreferredSize(new Dimension(840, 200));
		add(scrollpane);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public void update(Object source, String s) {
		if (source instanceof KundeController) {
			if (kController.getKundeFundet()) {
				visFlere(kController.getKunde().getKunde_id());
			}
		}
		if (source instanceof LånetilbudController) {
			if (s.equals("opretLånetilbud")) {
				visFlere(kController.getKunde().getKunde_id());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton source = (JButton) arg0.getSource();
		if (source.equals(btnExport)) {
			String filplacering = JOptionPane.showInputDialog(null, "Indtast sti hvor filen skal gemmes","Input", JOptionPane.INFORMATION_MESSAGE);
			int i = (int) table_1.getModel().getValueAt(table_1.getSelectedRow(), 0);
			CSV_eksport csv = new CSV_eksportImpl();
			csv.hentData(i, filplacering);
		}
	}
}
