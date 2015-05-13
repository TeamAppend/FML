package prototype;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

public class Test extends JFrame {

	private static final long serialVersionUID = -1519081899077388567L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtAndersLooft;
	private JTextField txtNyMlleveja;
	private JTextField textField_3;
	private JTextField txtHerning;
	private JTextField textField_5;
	private JTextField textField_2;
	private JTable table;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Opret lån");
		setBounds(100, 100, 513, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpr = new JLabel("CPR");
		lblCpr.setBounds(12, 187, 47, 16);
		contentPane.add(lblCpr);
		
		textField = new JTextField();
		textField.setText("0807911449");
		textField.setBounds(110, 184, 86, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("-------------------------------------------------------------------------------------------------------------------");
		label_1.setBounds(12, 42, 471, 16);
		contentPane.add(label_1);
		
		JLabel lblNavn = new JLabel("Navn");
		lblNavn.setBounds(12, 71, 86, 16);
		contentPane.add(lblNavn);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(12, 100, 86, 16);
		contentPane.add(lblAdresse);
		
		JLabel lblPostnummer = new JLabel("Postnummer");
		lblPostnummer.setBounds(12, 129, 86, 16);
		contentPane.add(lblPostnummer);
		
		JLabel lblBy = new JLabel("By");
		lblBy.setBounds(12, 158, 86, 16);
		contentPane.add(lblBy);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(12, 13, 86, 16);
		contentPane.add(lblTelefon);
		
		txtAndersLooft = new JTextField();
		txtAndersLooft.setText("Anders Looft");
		txtAndersLooft.setBounds(110, 68, 158, 22);
		contentPane.add(txtAndersLooft);
		txtAndersLooft.setColumns(10);
		
		txtNyMlleveja = new JTextField();
		txtNyMlleveja.setText("Ny M\u00F8llevej 14A 1tv");
		txtNyMlleveja.setColumns(10);
		txtNyMlleveja.setBounds(110, 97, 158, 22);
		contentPane.add(txtNyMlleveja);
		
		textField_3 = new JTextField();
		textField_3.setText("7400");
		textField_3.setColumns(10);
		textField_3.setBounds(110, 126, 66, 22);
		contentPane.add(textField_3);
		
		txtHerning = new JTextField();
		txtHerning.setText("Herning");
		txtHerning.setColumns(10);
		txtHerning.setBounds(110, 155, 158, 22);
		contentPane.add(txtHerning);
		
		textField_5 = new JTextField();
		textField_5.setText("60150191");
		textField_5.setColumns(10);
		textField_5.setBounds(110, 10, 86, 22);
		contentPane.add(textField_5);
		
		JButton btnBeregn = new JButton("Beregn");
		btnBeregn.setBounds(386, 270, 97, 25);
		contentPane.add(btnBeregn);
		
		JLabel lblUdbetaling = new JLabel("Bilens pris");
		lblUdbetaling.setBounds(12, 216, 86, 16);
		contentPane.add(lblUdbetaling);
		
		JLabel lblBetalingsperiode = new JLabel("Betalingsperiode");
		lblBetalingsperiode.setBounds(12, 274, 96, 16);
		contentPane.add(lblBetalingsperiode);
		
		textField_2 = new JTextField();
		textField_2.setText("5.600.000");
		textField_2.setColumns(10);
		textField_2.setBounds(110, 213, 86, 22);
		contentPane.add(textField_2);
		
		JTextField comboBox = new JTextField();
		comboBox.setBounds(110, 271, 50, 22);
		contentPane.add(comboBox);
		
		JLabel lblMneder = new JLabel("M\u00E5neder");
		lblMneder.setBounds(172, 274, 56, 16);
		contentPane.add(lblMneder);
		
		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Test"},
				{"Test2"},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"L\u00E5neplan"
			}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(280, 71, 203, 144);
		contentPane.add(table);
		
		JLabel label_2 = new JLabel("Udbetaling");
		label_2.setBounds(12, 245, 86, 16);
		contentPane.add(label_2);
		
		textField_4 = new JTextField();
		textField_4.setText("1.000.000");
		textField_4.setColumns(10);
		textField_4.setBounds(110, 242, 86, 22);
		contentPane.add(textField_4);
	}
}
