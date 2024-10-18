package es6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerWeb {

	public static void main(String[] args) throws IOException {
		System.out.println("ServerWeb");
		ServerSocket ss = new ServerSocket(8000);
		System.out.println("attesa connessione");
		
		String r;
		
		Socket canale = ss.accept();
		System.out.println("connesso");
		Scanner s = new Scanner(canale.getInputStream());
		PrintWriter p = new PrintWriter(canale.getOutputStream());
		
		while(s.hasNextLine()) {
			r = s.nextLine();
			System.out.println("ricevuto dal client: " + r);
			if (r.equals("")) {
				p.println("HTTP/1.1 200 OK");
				p.println("");
				p.println("gatto");

				p.flush();
				
				break;
			}
		}		
		ss.close();	
	}
}
