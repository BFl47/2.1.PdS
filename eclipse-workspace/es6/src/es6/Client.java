package es6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		System.out.println("Client");
		Socket canale = new Socket("127.0.0.1", 8000);
		PrintWriter p = new PrintWriter(canale.getOutputStream());
		Scanner s = new Scanner(canale.getInputStream());
			
		p.println("");
		p.flush();
			
		String r;
		while (s.hasNextLine()) {
			r = s.nextLine();
			System.out.println("ricevuto dal server: " + r);
		}
	
		canale.close();

	}

}
