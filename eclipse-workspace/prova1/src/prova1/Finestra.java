package prova1;
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Finestra {

	public static void main(String[] args) {
		JFrame finestra = new JFrame("Benedetta Fiorillo 1710531");
		
		JPanel pannello1 = new JPanel();
		finestra.add(pannello1, BorderLayout.NORTH);
		
		JLabel titolo1 = new JLabel("Server Address");
		pannello1.add(titolo1);
		
		JTextField area_indirizzo = new JTextField(15);
		pannello1.add(area_indirizzo);
		
		JLabel titolo2 = new JLabel("Port");
		pannello1.add(titolo2);
		
		JTextField area_porta = new JTextField(10);
		pannello1.add(area_porta);
		Ascoltatore agisci = new Ascoltatore();
		
		JButton connect = new JButton("Connect");
		pannello1.add(connect);
		connect.addActionListener(agisci);
		
		JButton disconnect = new JButton("Disconnect");
		pannello1.add(disconnect);
		disconnect.addActionListener(agisci);
		disconnect.setEnabled(false);
		
		JPanel pannello2 = new JPanel();
		finestra.add(pannello2, BorderLayout.CENTER);
		
		JTextArea area_usa = new JTextArea(5, 15);
		JScrollPane scroll_usa = new JScrollPane(area_usa);
		pannello2.add(scroll_usa);
		area_usa.setEditable(false);
		
		TitledBorder titolino1 = new TitledBorder("USA");
		scroll_usa.setBorder(titolino1);
		
		JTextArea area_italia = new JTextArea(5, 15);
		JScrollPane scroll_ita = new JScrollPane(area_italia);
		pannello2.add(scroll_ita);
		area_italia.setEditable(false);
		
		TitledBorder titolino2 = new TitledBorder("Italia");
		scroll_ita.setBorder(titolino2);
		
		JTextArea area_log = new JTextArea(5, 15);
		JScrollPane scroll_log = new JScrollPane(area_log);
		pannello2.add(scroll_log);
		area_log.setEditable(false);
		
		TitledBorder titolino3 = new TitledBorder("Log");
		scroll_log.setBorder(titolino3);
		
		JPanel pannello3 = new JPanel();
		finestra.add(pannello3, BorderLayout.SOUTH);
		
		JButton start = new JButton("Start");
		pannello3.add(start);
		start.addActionListener(agisci);
		start.setEnabled(false);
		
		JButton stop = new JButton("Stop");
		pannello3.add(stop);
		stop.addActionListener(agisci);
		stop.setEnabled(false);
		
		agisci.area_indirizzo = area_indirizzo;
		agisci.area_porta = area_porta;
		agisci.area_usa = area_usa;
		agisci.area_italia = area_italia;
		agisci.area_log = area_log;
		
		agisci.connect = connect;
		agisci.disconnect = disconnect;
		agisci.start = start;
		agisci.stop = stop;
		
		//192.168.51.90
		//4400

		finestra.pack();
		finestra.setVisible(true);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}