package prova4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Ascoltatore implements ActionListener {
	MyFrame fn;
	Socket canale;
	PrintWriter p;
	Scanner s;
	
	public Ascoltatore(MyFrame fn) {
		this.fn = fn;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Connect")) {
			System.out.println("pulsante connect");
			try {
				this.startConnessione();
			} catch(IOException ex) {
				System.out.println("errore apertura connessione");
			}
			fn.connesso = true;
			fn.setBottoni();
			fn.area_image.setText("");
		} 
		
		else if (comando.equals("Disconnect")) {
			System.out.println("pulsante disconnect");
			p.println("disconnect");
			p.flush();
			
			try {
				this.closeConnessione();
			} catch (IOException ex) {
				System.out.println("errore chiusura connessione");
			}
			fn.connesso = false;
			fn.setBottoni();
		}
		
		else if (comando.equals("Stop")) {
			System.out.println("pulsante stop");
			fn.partito = false;
			fn.setBottoni();
			
			p.println("stop");
			p.flush();
		} 
		
		else {
			fn.partito = true;
			fn.setBottoni();
			
			String dainviare = dainviare(comando);
			System.out.println("start:" + dainviare);
			
			p.println("start:" + dainviare);
			p.flush();

			Eseguibile scarica = new Eseguibile(this.fn, comando, s);
			Thread t = new Thread(scarica);
			t.start();
		}

	}
	
	public void startConnessione() throws IOException {
		canale = new Socket(fn.area_indirizzo.getText(), Integer.parseInt(fn.area_porta.getText()));
		p = new PrintWriter(canale.getOutputStream());
		s = new Scanner(canale.getInputStream());
	}
	
	public void closeConnessione() throws IOException {
		p.close();
		s.close();
		canale.close();
	}
	
	public String dainviare(String comando) {
		String dainviare = "";
		if (comando.equals("Image 1")) {
			dainviare = "image1";
		} else if (comando.equals("Image 2")) {
			dainviare = "image2";
		} else if (comando.equals("Image 3")) {
			dainviare = "image3";
		} else if (comando.equals("Image 4")) {
			dainviare = "image4";
		} else if (comando.equals("Image 5")) {
			dainviare = "image5";
		} 
		return dainviare;
	}

}
