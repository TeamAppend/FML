package prototype;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logik.CPRLogik;
import logik.CPRLogikImpl;
import domain.CPRnummer;

public class findCPRPrototype extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Controller controller = new Controller();
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					findCPRPrototype frame = new findCPRPrototype();
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
	public findCPRPrototype() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCprid = new JLabel("CPR_id");
		lblCprid.setBounds(12, 13, 77, 16);
		contentPane.add(lblCprid);
		
		textField = new JTextField();
		textField.setBounds(101, 10, 52, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCprnummer = new JLabel("CPRnummer");
		lblCprnummer.setBounds(12, 42, 77, 16);
		contentPane.add(lblCprnummer);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 45, 116, 22);
		contentPane.add(textField_1);
		
		btnNewButton = new JButton("HIT IT!");
		btnNewButton.setBounds(178, 101, 97, 25);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(controller);
	}
	
	public class Controller implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton source = (JButton) arg0.getSource();
			if(source.equals(btnNewButton)){
				int id = Integer.parseInt(textField.getText());
				CPRLogik cl = new CPRLogikImpl();
				try {
					CPRnummer cpr = cl.listCPR(id);
					System.out.println(cpr.getCPRnummer());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}
}
