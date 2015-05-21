package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import logik.FFSObserver;
import logik.KundeController;
import logik.LånetilbudController;
import logik.LånetilbudLogik;
import logik.LånetilbudLogikImpl;
import domain.Lånetilbud;

public class Tabel extends JPanel implements FFSObserver   {

	private GridBagLayout layout;
	private JTable table_1;
	private JScrollPane scrollpane;
	private KundeController kController = KundeController.instance();
	private LånetilbudController lController = LånetilbudController.instance();

	public Tabel() {
		kController.tilmeldObserver(this);
		lController.tilmeldObserver(this);
		// frame properties
		setVisible(true);
		this.setPreferredSize(new Dimension(850, 225));

		layout = new GridBagLayout();
		setLayout(layout);

		TitledBorder title = new TitledBorder("Lånetilbud");
		setBorder(title);

		
		

	}
	
	public void visFlere( int kunde_id) {
		LånetilbudLogik ll = new LånetilbudLogikImpl();
		try {
			List<Lånetilbud> list = ll.listLånetilbud(kunde_id);
			visTabellen(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void visTabellen( List<Lånetilbud> låneList ) {
		if (this.scrollpane != null) {
			remove(scrollpane);
		}
		//, "Kundenavn", "Modelnavn", "Sælgernavn"
		TableModel datamodel = new AbstractTableModel() {
			String[] columnNames = {"Lånetilbud id", "Rentesats", "Tilbagebetalingsperiode", "Udbetaling", "ÅOP", "Oprettelsestidspunkt"};
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
				if(col == 0)
					return låneList.get(row).getLånetilbud_id();
				else if(col == 1)
					return låneList.get(row).getRentesats();
				else if(col == 2)
					return låneList.get(row).getTilbagebetalingsperiode();
				else if(col == 3)
					return låneList.get(row).getUdbetaling();
				else if(col == 4)
					return låneList.get(row).getÅOP();
				else if(col == 5)
					return låneList.get(row).getOprettelsestidspunkt();
				else
					return null;
			}
		};
		
		
		table_1 = new JTable(datamodel);
		table_1.setBounds(0, 0, 100, 100);
		scrollpane = new JScrollPane(table_1);
		scrollpane.setPreferredSize(new Dimension(840,200));
		add(scrollpane);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		
		
		
		this.setVisible(true); 
		repaint();
		revalidate();
	}
	
	
	public void resetTable() {
		table_1 = new JTable();
		table_1.setBounds(0, 0, 100, 100);
		scrollpane = new JScrollPane(table_1);
		scrollpane.setPreferredSize(new Dimension(840,200));
		add(scrollpane);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public void update(Object source, String s) {
		if(source instanceof KundeController){
			if(kController.getKundeFundet()){
				visFlere(kController.getKunde().getKunde_id());
			}
		}if (source instanceof LånetilbudController) {
			if(s.equals("opretLånetilbud")){
				visFlere(kController.getKunde().getKunde_id());
			}
		}
	}	
}
