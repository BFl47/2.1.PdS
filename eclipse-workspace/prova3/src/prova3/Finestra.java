package prova3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class Finestra extends JFrame {
	
	JButton start = new JButton("Start");
	JTextField addressField = new JTextField("localhost");
	JTextField portField = new JTextField("4400");
	JButton interrompi = new JButton("Interrompi");
	JButton connect = new JButton("Connect");
	JButton disconnect = new JButton("Disconnect");
	JButton clear = new JButton("Clear");
	ColoredButton[] bottoni = new ColoredButton[5];
	
	public boolean connesso = false;
	public boolean partito = false;
	
	public Finestra() {
		super("nome cognome 1234567");
		Ascoltatore ascolta = new Ascoltatore(this);
		//pannello1
		JPanel pannello1 = new JPanel();
		pannello1.add(start);
		pannello1.add(new JLabel("IP Address"));
		pannello1.add(addressField);
		pannello1.add(new JLabel("Port"));
		pannello1.add(portField);
		pannello1.add(interrompi);
		
		this.add(pannello1, BorderLayout.NORTH);
	
		//pannello2
		JPanel pannello2 = new JPanel(new GridLayout(1,5));
		for (int i = 0; i < 5; i++) {
			bottoni[i] = new ColoredButton(i + "", Color.LIGHT_GRAY);
			pannello2.add(bottoni[i]);
		}
		
		this.add(pannello2, BorderLayout.CENTER);
		
		//pannello3
		JPanel pannello3 = new JPanel();
		pannello3.add(connect);
		pannello3.add(disconnect);
		pannello3.add(clear);
		
		this.add(pannello3, BorderLayout.SOUTH);
		
		//listener
		start.addActionListener(ascolta);
		interrompi.addActionListener(ascolta);
		connect.addActionListener(ascolta);
		disconnect.addActionListener(ascolta);
		clear.addActionListener(ascolta);
		
		this.setBottoni();
		this.setSize(700, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setBottoni() {
		if (!connesso) {
			connect.setEnabled(true);
			clear.setEnabled(true);
			start.setEnabled(false);
			disconnect.setEnabled(false);
			interrompi.setEnabled(false);
		}
		else {
			connect.setEnabled(false);
			if (partito) {
				start.setEnabled(false);
				interrompi.setEnabled(true);
				clear.setEnabled(false);
				disconnect.setEnabled(false);
			}
			else {
				start.setEnabled(true);
				interrompi.setEnabled(false);
				clear.setEnabled(true);
				disconnect.setEnabled(true);
			}
		}
	}

	public static void main(String[] args) {
		Finestra finestra = new Finestra();

	}

}
