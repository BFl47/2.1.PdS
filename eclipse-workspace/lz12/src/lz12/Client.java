package lz12;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//Socket canale = new Socket("www.google.com", 80);
		System.out.println("Client");
		Socket canale = new Socket("localhost", 8080);
		
		PrintWriter p = new PrintWriter(canale.getOutputStream());
		Scanner s = new Scanner(canale.getInputStream());
		
		//p.println("GET / HTTP/1.0 \r\n");
		//p.println("\r\n");
		
		p.print("ciao\r\n");
		p.flush();
		
		String r;
		while (s.hasNextLine()) {
			r = s.nextLine();
			System.out.println("ricevuto dal server: " + r);
			
		}
		
		canale.close();
	}

}
