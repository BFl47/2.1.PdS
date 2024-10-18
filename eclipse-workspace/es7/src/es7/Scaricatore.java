package es7;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JButton;

public class Scaricatore implements Runnable {
	boolean scaricando, interrompo, esecuzione;
	JButton bottone;
	
	@Override
	public void run() {
		this.esecuzione = true;
		
		try {
			Socket s = new Socket("www.google.it", 8080);

			try {
				s.setSoTimeout(10000);
				PrintWriter p = new PrintWriter(s.getOutputStream());
				Scanner sc = new Scanner(s.getInputStream());
				
				p.print("GET / \n\r\n\r");
				p.flush();
				
				String r;
				while (sc.hasNext()) {
					this.scaricando = true;
					r = sc.nextLine();
					System.out.println(r);
					if (this.interrompo) {
						p.close();
						sc.close();
						s.close();
						return;
					}
				}
				
			} catch (SocketTimeoutException e) {
				System.out.println("timeout server");
				s.close();
			}
			
		} catch (UnknownHostException e) {
			System.out.println("errore in host (server)");
		
		} catch (IOException e) {
			System.out.println("errore scaricamento");
		}
	}
}
