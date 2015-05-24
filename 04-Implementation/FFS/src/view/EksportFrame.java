package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logik.CSV_eksport;
import logik.CSV_eksportImpl;

public class EksportFrame extends JFrame implements ActionListener {
	private JTextField tfSti = new JTextField(25);
	private int lånetilbud_id; 
	private JButton ok = new JButton("Ok");
	private JButton cancel = new JButton("Cancel"); 
	private GridBagLayout layout;
	private Tabel tabel;
	
	public void setVariabler(int lånetilbud_id, Tabel tabel) {
		this.lånetilbud_id = lånetilbud_id;
		this.tabel = tabel;
	}

	public EksportFrame(){
		
		layout = new GridBagLayout();
		setLayout(layout);
		
		
		GridBagConstraints con = new GridBagConstraints();
		Insets ins = new Insets(5, 5, 5, 5);
		
		
		con = createGBC(0, 0, 2, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Indtast sti til en mappe hvor filen skal gemmes"), con);
		
		con = createGBC(0, 1, 6, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		tfSti.setText(System.getProperty("user.home") + "\\Desktop");  //henter stien til desktop til default sti
		add(tfSti, con);

		con = createGBC(0, 2, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.EAST;
		add(ok, con);
		ok.addActionListener(this);
		
		con = createGBC(1, 2, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(cancel, con);
		cancel.addActionListener(this);
		
		
		setVisible(true);
		setSize(350, 150);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		
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

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(ok)){
			File file = new File(tfSti.getText());
			
			if (tfSti.getText().trim().length() == 0 || !file.exists() || !file.isDirectory())
			{
				JOptionPane.showMessageDialog(null, "- Stien er ikke gyldig. \n- Stien skal pege på en mappe i dit filsystem", "Ugyldig sti", JOptionPane.ERROR_MESSAGE);
				tabel.enableEksportBtn();
			} else {
				CSV_eksport csv = new CSV_eksportImpl();
				csv.hentData(lånetilbud_id, tfSti.getText());
			}
			
			setVisible(false); //you can't see me!
			dispose();
		}
		if (e.getSource().equals(cancel)) {
			tabel.enableEksportBtn();
			setVisible(false); //you can't see me!
			dispose();
		}
			
	}

}
