package prova3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Ascoltatore implements ActionListener {
	Finestra fn = null;
	Socket canale;
	PrintWriter p;
	Scanner s;

	public Ascoltatore(Finestra fn) {
		this.fn = fn;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Start")) {
			
			for (ColoredButton cd: this.fn.bottoni) {
				if (cd.getDigit().equals("")) {
					JOptionPane.showMessageDialog(null, "inserire tutti i numeri");
					return;
				}
			}
			for (ColoredButton cd: this.fn.bottoni) {
				cd.changeColor(Color.LIGHT_GRAY);
			}
			this.fn.partito = true;
			this.fn.setBottoni();
			
			p.println("start");
			p.flush();
			
			Eseguibile leggidati = new Eseguibile(s, this.fn);
			Thread t = new Thread(leggidati);
			t.start();
			
		}
		
		else if (comando.equals("Interrompi")) {
			this.fn.partito = false;
			this.fn.setBottoni();
			
			p.println("interrompi");
			p.flush();
		}
		
		else if (comando.equals("Connect")) {
			try {
				this.setupConnessione();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "impossibile connettersi al server");
				return;
			}
			this.fn.connesso = true;
			this.fn.setBottoni();			
		}
		
		else if (comando.equals("Disconnect")) {
			try {
				this.chiudiConnessione();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "impossibile chiudere la connessione");
				return;
			}
			this.fn.connesso = false;
			this.fn.setBottoni();
		}
		
		else if (comando.equals("Clear")) {
			for (ColoredButton cd: this.fn.bottoni) {
				cd.changeColor(Color.LIGHT_GRAY);
				cd.setTextDigit("");
			}
		}

	}
	
	public void setupConnessione() throws IOException {
		canale = new Socket(this.fn.addressField.getText(), Integer.parseInt(this.fn.portField.getText()));
		p = new PrintWriter(canale.getOutputStream());
		s = new Scanner(canale.getInputStream());
	}
	
	public void chiudiConnessione() throws IOException {
		p.close();
		s.close();
		canale.close();
	}

}
