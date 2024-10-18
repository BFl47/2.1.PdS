package prova4;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	//nord
	JTextField area_indirizzo = new JTextField("localhost");
	JTextField area_porta = new JTextField("4400");
	JButton connect = new JButton("Connect");
	JButton disconnect = new JButton("Disconnect");
	//centro
	JTextArea area_image = new JTextArea(25,80);
	//sud
	JButton[] bottoni = new JButton[5];
	JButton stop = new JButton("Stop");
	
	public boolean connesso = false;
	public boolean partito = false;
		
	public MyFrame() {
		super("nome cognome 1234567");
		Ascoltatore agisci = new Ascoltatore(this);
		
		//pannello nord
		JPanel pannelloNord = new JPanel();
		pannelloNord.add(new JLabel("Server Address"));
		pannelloNord.add(area_indirizzo);
		pannelloNord.add(new JLabel("Port"));
		pannelloNord.add(area_porta);
		pannelloNord.add(connect);
		pannelloNord.add(disconnect);
		
		this.add(pannelloNord, BorderLayout.NORTH);
		
		//centro
		area_image.setEditable(false);
		area_image.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		JScrollPane scroll = new JScrollPane(area_image);
		this.add(scroll, BorderLayout.CENTER);
		scroll.setBorder(new TitledBorder("Image"));
		
		//pannello sud
		JPanel pannelloSud = new JPanel();
		for (int i = 0; i < 5; i++) {
			int num = i+1;
			bottoni[i] = new JButton("Image " + num + "");
			pannelloSud.add(bottoni[i]);
			bottoni[i].addActionListener(agisci);
		}
		pannelloSud.add(stop);
	
		this.add(pannelloSud, BorderLayout.SOUTH);
		
		//listener
		connect.addActionListener(agisci);
		disconnect.addActionListener(agisci);
		stop.addActionListener(agisci);		
		
		this.setBottoni();
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setBottoni() {
		if (!connesso) {
			connect.setEnabled(true);
			disconnect.setEnabled(false);
			stop.setEnabled(false);
			for (int i = 0; i < 5; i++) 
				bottoni[i].setEnabled(false);
		}
		else {
			connect.setEnabled(false);
			if (!partito) {
				for (int i = 0; i < 5; i++) 
					bottoni[i].setEnabled(true);
				disconnect.setEnabled(true);
				stop.setEnabled(false);
			}
			else {
				stop.setEnabled(true);
				disconnect.setEnabled(false);
				for (int i = 0; i < 5; i++) 
					bottoni[i].setEnabled(false);
				
			}				
		}
	}
}
