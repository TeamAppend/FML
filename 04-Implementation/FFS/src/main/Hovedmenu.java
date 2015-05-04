package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Hovedmenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hovedmenu frame = new Hovedmenu();
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
	public Hovedmenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hovedmenu");
		setBounds(100, 100, 267, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOpretBruger = new JButton("Opret l\u00E5n");
		btnOpretBruger.setBounds(50, 31, 139, 25);
		contentPane.add(btnOpretBruger);
		
		JButton button = new JButton("Opret bruger");
		button.setBounds(50, 84, 139, 25);
		contentPane.add(button);
		
		JButton btnRedigerbruger = new JButton("Rediger bruger");
		btnRedigerbruger.setBounds(50, 136, 139, 25);
		contentPane.add(btnRedigerbruger);
		
		JButton btnSletBruger = new JButton("Slet bruger");
		btnSletBruger.setBounds(50, 191, 139, 25);
		contentPane.add(btnSletBruger);
	}

}
