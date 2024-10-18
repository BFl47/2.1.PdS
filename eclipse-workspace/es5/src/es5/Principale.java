package es5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class Principale {

	public static void main(String[] args) {
		JFrame finestra = new JFrame("Pannello digitale");
		finestra.getContentPane().setLayout(new FlowLayout());
		
		JPanel pannello1 = new JPanel();
		pannello1.setLayout(new BorderLayout());
		finestra.add(pannello1);
		
		JPasswordField pw = new JPasswordField(3);
		pannello1.add(pw, BorderLayout.NORTH);
		pw.setEditable(false);
		pw.setEchoChar('*');
		
		JPanel griglia = new JPanel();
		griglia.setLayout(new GridLayout(4,3));
		pannello1.add(griglia, BorderLayout.CENTER);
		griglia.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel pannello2 = new JPanel();
		pannello2.setLayout(new BorderLayout());
		finestra.add(pannello2);
		
		JLabel areatitolo = new JLabel("Codice digitato:");
		areatitolo.setForeground(Color.blue);
		pannello2.add(areatitolo, BorderLayout.NORTH);
		
		JTextArea areatesto = new JTextArea(4, 20);
		pannello2.add(areatesto, BorderLayout.SOUTH);
		areatesto.setEditable(false);
		areatesto.setBorder(BorderFactory.createLineBorder(Color.black));
		
		Agisci o = new Agisci();
		String[] descrizione = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "C", "0", "Mostra"};
		o.campopw = pw;
		o.testo = areatesto;
		
		JButton[] bottoni = new JButton[12];
		for (int i = 0; i < bottoni.length; i++) {
			bottoni[i] = new JButton(descrizione[i]);
			griglia.add(bottoni[i]);
			bottoni[i].addActionListener(o);
		}
	
		finestra.setVisible(true);
		finestra.pack();
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
