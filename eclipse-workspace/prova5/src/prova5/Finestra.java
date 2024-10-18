package prova5;

import java.awt.BorderLayout;

import javax.swing.*;

public class Finestra extends JFrame {
	//nord
	JTextField area_indirizzo = new JTextField("localhost");
	JTextField area_porta = new JTextField("4400");
	JButton connect = new JButton("Connect");
	JButton disconnect = new JButton("Disconnect");
	
	//centro
	JTextArea area_log = new JTextArea(10, 25);
	JTextArea area_artisti = new JTextArea(10, 25);
	
	//sud
	JButton artisti = new JButton("Artisti");
	JButton canzoni = new JButton("Canzoni");
	JButton stop = new JButton("Stop");
	
	boolean connesso = false;
	boolean partito = false;
	
	public Finestra() {
		super("nome cognome 1234567");
		Ascoltatore ascolta = new Ascoltatore(this);
		
		//nord
		JPanel nord = new JPanel();
		nord.add(new JLabel("Server Address"));
		nord.add(area_indirizzo);
		nord.add(new JLabel("Port"));
		nord.add(area_porta);
		nord.add(connect);
		nord.add(disconnect);
		
		this.add(nord, BorderLayout.NORTH);
		
		//centro
		JPanel centro = new JPanel();
		JScrollPane scroll_log = new JScrollPane(area_log);
		centro.add(scroll_log);
		JScrollPane scroll_artisti =new JScrollPane(area_artisti);
		centro.add(scroll_artisti);
		
		this.add(centro, BorderLayout.CENTER);
		
		//sud
		JPanel sud = new JPanel();
		sud.add(artisti);
		sud.add(canzoni);
		sud.add(stop);
		
		this.add(sud, BorderLayout.SOUTH);
		
		//ascoltatori
		connect.addActionListener(ascolta);
		disconnect.addActionListener(ascolta);
		artisti.addActionListener(ascolta);
		canzoni.addActionListener(ascolta);
		stop.addActionListener(ascolta);
		
		this.setBottoni();
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setBottoni() {
		if (!connesso) {
			connect.setEnabled(true);
			disconnect.setEnabled(false);
			artisti.setEnabled(false);
			canzoni.setEnabled(false);
			stop.setEnabled(false);
		}
		else {
			connect.setEnabled(false);
			if (!partito) {
				disconnect.setEnabled(true);
				stop.setEnabled(false);
			}
		}
	}
}
