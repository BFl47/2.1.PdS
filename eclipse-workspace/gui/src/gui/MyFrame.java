package gui;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {
	//pannello1
	public static JPanel pannello1 = new JPanel();
	public static JPasswordField campopw = new JPasswordField(5);
	public static JPanel griglia = new JPanel();
	public static JButton[] bottoni = new JButton[12];
	//pannello2
	public static JPanel pannello2 = new JPanel();
	public static JLabel titolo = new JLabel("Codice digitato");
	public static JTextArea areatesto = new JTextArea(2, 15);
	
	public MyFrame() {
		super("esercitazione 5");
		this.getContentPane().setLayout(new FlowLayout());
		//pannello1
		pannello1.setLayout(new BorderLayout());
		this.add(pannello1);
		
		pannello1.add(campopw, BorderLayout.NORTH);
		campopw.setEditable(false);
		campopw.setEchoChar('*');
		
		griglia.setLayout(new GridLayout(4,3));
		pannello1.add(griglia, BorderLayout.CENTER);
		griglia.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//pannello2
		pannello2.setLayout(new BorderLayout());
		this.add(pannello2);
		
		pannello2.add(titolo, BorderLayout.NORTH);
		titolo.setForeground(Color.blue);
		
		pannello2.add(areatesto, BorderLayout.SOUTH);
		areatesto.setEditable(false);
		areatesto.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//bottoni
		Ascoltatore agisci = new Ascoltatore();
		String[] descrizione = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "C", "0", "Mostra"};
		
		for (int i = 0; i < bottoni.length; i++) {
			bottoni[i] = new JButton(descrizione[i]);
			griglia.add(bottoni[i]);
			bottoni[i].addActionListener(agisci);
		}
		
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
