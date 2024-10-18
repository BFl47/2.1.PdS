package prova1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*;

public class Ascoltatore implements ActionListener {
	JTextField area_indirizzo;
	JTextField area_porta;
	JTextArea area_usa;
	JTextArea area_italia;
	JTextArea area_log;
	
	Socket canale;
	PrintWriter p;
	Scanner s;
	Thread t;
	
	JButton connect, disconnect, start, stop;
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Connect")) {
			System.out.println("connect");
			
			
			String indirizzo = this.area_indirizzo.getText();
			System.out.println(indirizzo);
			int porta = Integer.parseInt(this.area_porta.getText());
			System.out.println(porta);
			
			try {
				canale = new Socket(indirizzo, porta);
				System.out.println("server aperto");
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				start.setEnabled(true);
				area_indirizzo.setText("");
				area_porta.setText("");
				area_usa.setText("");
				area_italia.setText("");
				area_log.setText("");
				
				p = new PrintWriter(canale.getOutputStream());
				s = new Scanner(canale.getInputStream());

			} catch (IOException e1) {
				System.out.println("server non valido");
			}
		}
		
		if (e.getActionCommand().equals("Disconnect")) {
			System.out.println("disconnect");
			p.println("DISCONNECT");
			p.flush();
			try {
				canale.close();
				p.close();
				s.close();
				System.out.println("server chiuso");
				stop.setEnabled(false);
				start.setEnabled(false);
				disconnect.setEnabled(false);
				connect.setEnabled(true);
			} catch (IOException e1) {
				System.out.println("errore chiusura");
			}
			
		}
		
		if (e.getActionCommand().equals("Start")) {
			System.out.println("start");
					
			stop.setEnabled(true);
			start.setEnabled(false);
			disconnect.setEnabled(false);
			

			Esegui scarica = new Esegui();
			scarica.p = p;
			scarica.s = s;
			scarica.area_italia = area_italia;
			scarica.area_usa = area_usa;
			scarica.area_log = area_log;
			
			p.println("START");	
			p.flush();
			t = new Thread(scarica);	
			t.start();
			
			if (scarica.finito) {
				stop.setEnabled(false);
				start.setEnabled(true);
				disconnect.setEnabled(true);
			}
	
		}
		
		if (e.getActionCommand().equals("Stop")) {
			System.out.println("stop");
			p.println("STOP");
			p.flush();
			start.setEnabled(true);
			disconnect.setEnabled(true);
	
		}
	}
	
}