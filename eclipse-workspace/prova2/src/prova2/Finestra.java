package prova2;

import java.awt.BorderLayout;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Finestra {

	public static void main(String[] args) {
		JFrame finestra = new JFrame("Benedetta Fiorillo 1710531");
		
		JPanel pannello1 = new JPanel();
		finestra.add(pannello1, BorderLayout.NORTH);
		
		JLabel titolo1 = new JLabel("Server Address");
		pannello1.add(titolo1);
		
		JTextField area_indirizzo = new JTextField("192.168.51.90");
		pannello1.add(area_indirizzo);
		
		JLabel titolo2 = new JLabel("Port");
		pannello1.add(titolo2);
		
		JTextField area_porta = new JTextField("4040");
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
		
		JTextArea area_info = new JTextArea(20, 40);
		JScrollPane scroll_info = new JScrollPane(area_info);
		pannello2.add(scroll_info);
		area_info.setEditable(false);
		
		TitledBorder titolino = new TitledBorder("Informazioni");
		scroll_info.setBorder(titolino);
		
		JPanel pannello3 = new JPanel();
		finestra.add(pannello3, BorderLayout.SOUTH);
		
		JLabel titolo3 = new JLabel("Città: ");
		pannello3.add(titolo3);
		
		JTextField area_citta = new JTextField(10);
		pannello3.add(area_citta);
		
		JButton get = new JButton("Get");
		pannello3.add(get);
		get.addActionListener(agisci);
		get.setEnabled(false);
		
		JButton stop = new JButton("Stop");
		pannello3.add(stop);
		stop.addActionListener(agisci);
		stop.setEnabled(false);
		
		HashSet<String> lista_citta = new HashSet<String>();
		Collections.addAll(lista_citta, "bangkok", "dubai", "istanbul", "londra", "parigi");
		
		agisci.area_indirizzo = area_indirizzo;
		agisci.area_porta = area_porta;
		agisci.area_info = area_info;
		agisci.area_citta = area_citta;
		agisci.connect = connect;
		agisci.disconnect = disconnect;
		agisci.get = get;
		agisci.stop = stop;
		agisci.lista_citta = lista_citta;
		
		finestra.pack();
		finestra.setVisible(true);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

}
