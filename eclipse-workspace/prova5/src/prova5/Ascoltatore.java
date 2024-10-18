package prova5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Ascoltatore implements ActionListener {
	Finestra fn;
	Socket canale;
	PrintWriter p;
	Scanner s;
	
	public Ascoltatore(Finestra fn) {
		this.fn = fn;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Connect")) {
			System.out.println("pulsante connect");
			try {
				this.startConnessione();
			} catch (IOException ex) {
				System.out.println("errore apertura connessione");
				return;
			}
			fn.connesso = true;
			fn.setBottoni();
			fn.artisti.setEnabled(true);
			fn.area_artisti.setText("");
			fn.area_log.setText("");
		}
		else if (comando.equals("Disconnect")) {
			System.out.println("pulsante disconnect");
			p.println("disconnect");
			p.flush();
			try {
				this.endConnessione();
			} catch (IOException ex) {
				System.out.println("errore chiusura connessione");
				return;
			}
			fn.connesso = false;
			fn.setBottoni();
		
		}
		else if (comando.equals("Stop")) {
			System.out.println("pulsante stop");
			p.println("stop");
			p.flush();
			
			fn.partito = false;
			fn.setBottoni();
		}
		
		else {
			String dainviare = comando.toLowerCase();
			p.println(dainviare);
			p.flush();
			fn.area_artisti.setText("");
			fn.area_log.setText("");
			fn.artisti.setEnabled(false);
			fn.disconnect.setEnabled(false);
			fn.stop.setEnabled(true);
			
			Eseguibile scarica = new Eseguibile(this.fn, dainviare, s);
			Thread t = new Thread(scarica);
			t.start();
		}
		


	}
	
	public void startConnessione() throws NumberFormatException, UnknownHostException, IOException {
		canale = new Socket(fn.area_indirizzo.getText(), Integer.parseInt(fn.area_porta.getText()));
		p = new PrintWriter(canale.getOutputStream());
		s = new Scanner(canale.getInputStream());
	}
	
	public void endConnessione() throws IOException {
		p.close();
		s.close();
		canale.close();
	}

}
