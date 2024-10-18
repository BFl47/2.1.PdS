package prova2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.*;

public class Ascoltatore implements ActionListener {
	JTextField area_indirizzo, area_porta, area_citta;
	JTextArea area_info;
	JButton connect, disconnect, get, stop;
	
	Socket canale;
	public static PrintWriter p;
	public static Scanner s;
	Thread t;
	
	HashSet<String> lista_citta;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando == "Connect") {
			System.out.println("connect");
			String indirizzo = area_indirizzo.getText();
			int porta = Integer.parseInt(area_porta.getText());
			
			try {
				canale = new Socket(indirizzo, porta);
				System.out.println("canale aperto");
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				get.setEnabled(true);
				area_info.setText("");
				area_citta.setText("");
				
				p = new PrintWriter(canale.getOutputStream());
				s = new Scanner(canale.getInputStream());
			} catch (IOException e1) {
				System.out.println("server non valido");
			}
		}
		
		if (comando == "Disconnect") {
			System.out.println("disconnect");
			p.println("disconnect");
			p.flush();
			
			try {
				canale.close();
				p.close();
				s.close();
				System.out.println("canale chiuso: " + canale.isClosed());
				
				get.setEnabled(false);
				stop.setEnabled(false);
				disconnect.setEnabled(false);
				connect.setEnabled(true);
			} catch (IOException e1) {
				System.out.println("errore chiusura");
			}
		}
		
		if (comando == "Get") {
			System.out.println("get");
			
			String citta = area_citta.getText().toLowerCase();
			System.out.println("citta richiesta: " + citta);
			if (!lista_citta.contains(citta)) {
				JOptionPane.showMessageDialog(null, "inserire stringa non vuota, scegliere tra:"
						+ "bangkok, dubai, istanbul, londra o parigi");
				area_citta.setText("");
			}
			else {
				stop.setEnabled(true);
				get.setEnabled(false);
				disconnect.setEnabled(false);
				Eseguibile scarica = new Eseguibile();
				//scarica.p = p;
				//scarica.s = s;
				scarica.area_info = area_info;
				scarica.disconnect = disconnect;
				scarica.stop = stop;
				scarica.get = get;
				
				p.println("GET:" + citta);
				p.flush();
				t = new Thread(scarica);
				t.start();				
			}
			
		}
		
		if (comando == "Stop") {
			System.out.println("stop");
			p.println("STOP");
			p.flush();
			
			get.setEnabled(true);
			disconnect.setEnabled(true);
		}
	}

}
